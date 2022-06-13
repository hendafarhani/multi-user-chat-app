package com.multiuser.chatwebapp.services.impl;

import com.chat.server.models.MessageModel;
import com.chat.server.models.PersonModel;
import com.multiuser.chatwebapp.ChatWebAppApplication;
import com.multiuser.chatwebapp.dtos.MessageDto;
import com.multiuser.chatwebapp.dtos.PersonDto;
import com.multiuser.chatwebapp.enums.MessageTypeEnum;
import com.multiuser.chatwebapp.services.ChatClientService;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Service
public class ChatClientServiceImpl implements ChatClientService {

    @Override
    public void connect(PersonDto personDto) throws IOException {
        Map<String, String> map = new HashMap<>();
        //Send connection details to chat-server.
        //chat-server in its turn communicates with the database
        map.put(MessageTypeEnum.CONNECT.getType(), personDto.getName());
        ChatWebAppApplication.chatClient.getObjectOutputStream().writeObject(map);
    }

    @Override
    public void sendMessage(MessageDto messageDto) throws IOException {
        //Send message to chat-server
        Map<String, MessageModel> map = new HashMap<>();
        MessageModel messageModel = new MessageModel();
        messageModel.setTextMsg(messageDto.getTextMsg());
        messageModel.setPerson(new PersonModel());
        messageModel.getPerson().setName(messageDto.getPersonName());
        map.put(MessageTypeEnum.RECEIVED_MESSAGE.getType(), messageModel);
        ChatWebAppApplication.chatClient.getObjectOutputStream().writeObject(map);
    }
}
