package com.chat.server.serverChat;

import com.chat.server.models.MessageModel;
import com.chat.server.models.PersonModel;
import com.chat.server.entities.Message;
import com.chat.server.entities.Person;
import com.chat.server.enums.DatabaseTypeEnum;
import com.chat.server.enums.MessageTypeEnum;
import com.chat.server.repositories.Message.MessageRepositoryFactory;
import com.chat.server.repositories.Person.PersonRepositoryFactory;
import org.modelmapper.ModelMapper;

import java.io.*;
import java.net.Socket;
import java.util.*;

public class ServerWorker extends Thread {

    PersonRepositoryFactory personRepositoryFactory;
    MessageRepositoryFactory messageRepositoryFactory;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;
    private final Socket clientSocket;
    private final Server server;

    public ServerWorker(Server server, Socket clientSocket) {
        this.server = server;
        this.clientSocket = clientSocket;
        this.personRepositoryFactory = new PersonRepositoryFactory(DatabaseTypeEnum.SQL.getType());
        this.messageRepositoryFactory = new MessageRepositoryFactory(DatabaseTypeEnum.SQL.getType());
    }

    @Override
    public void run() {
        try {
            handleConnection(clientSocket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleConnection(Socket clientSocket) throws IOException {
        try {
            objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            while (true) {
                Map map = (Map) objectInputStream.readObject();
                if (MessageTypeEnum.CONNECT.getType().equals(map.keySet().stream().findFirst().get())) {
                    String personName = (String) (map.get(MessageTypeEnum.CONNECT.getType()));
                   //Check if user already exists in the database
                    Person person = personRepositoryFactory.personRepository.loadByName(personName);
                    if (person == null) {
                    //If user isn't registered save it to database
                        addPerson(personName);
                        person = new Person();
                        person.setName(personName);
                    }
                    //Retrieve history of messages of all users
                    List<Message> messageList = messageRepositoryFactory.messageRepository.loadAll();
                    sendHistoryOfMessages(messageList, person);
                } else if (MessageTypeEnum.RECEIVED_MESSAGE.getType().equals(map.keySet().stream().findFirst().get())) {
                   //save message to database
                    Message message = addMessages((MessageModel) (map.get(MessageTypeEnum.RECEIVED_MESSAGE.getType())));
                    //Send message to all users
                    sendMessage(message);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            clientSocket.close();
        }
    }

    private void addPerson(String personName) throws Exception {
        Person person = new Person();
        person.setName(personName);
        personRepositoryFactory.personRepository.save(person);
    }

    private void sendHistoryOfMessages(List<Message> messageList, Person person) {
        ModelMapper modelMapper = new ModelMapper();
        List<MessageModel> messageDtoList = new ArrayList<>();
        messageList.stream().forEach(message -> {
            MessageModel messageDto = new MessageModel();
            messageDto.setTextMsg(message.getTextMsg());
            messageDto.setDate(message.getDate());
            PersonModel personDto = new PersonModel();
            modelMapper.map(message.getPerson(), personDto);
            messageDto.setPerson(personDto);
            messageDtoList.add(messageDto);
        });

        PersonModel personDto = new PersonModel(person.getName(), messageDtoList);
        Map map = new HashMap();
        map.put(MessageTypeEnum.HISTORY_OF_MESSAGES.getType(), personDto);
        List<ServerWorker> workerList = server.getWorkerList();
        workerList.stream().forEach(
                serverWorker -> {
                    try {
                        serverWorker.objectOutputStream.writeObject(map);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    private Message addMessages(MessageModel messageDto) throws Exception {
        Message message = new Message();
        Person person = personRepositoryFactory.personRepository.loadPersonByName(messageDto.getPerson().getName());
        message.setPerson(person);
        message.setTextMsg(messageDto.getTextMsg());
        message.setDate(new Date());
        messageRepositoryFactory.messageRepository.save(message);
        return message;
    }

    private void sendMessage(Message message) {
        ModelMapper modelMapper = new ModelMapper();
        MessageModel messageDto = new MessageModel();
        modelMapper.map(message, messageDto);
        Map<String, MessageModel> map = new HashMap();
        map.put(MessageTypeEnum.SENT_MESSAGE.getType(), messageDto);
        List<ServerWorker> workerList = server.getWorkerList();
        for (ServerWorker worker : workerList) {
            try {
                System.out.println(map.get(MessageTypeEnum.SENT_MESSAGE.getType()).getTextMsg());
                worker.objectOutputStream.writeObject(map);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}


