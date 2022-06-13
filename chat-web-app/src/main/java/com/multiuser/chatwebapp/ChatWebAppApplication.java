package com.multiuser.chatwebapp;

import com.multiuser.chatwebapp.ChatClientServer.ChatClient;
import com.multiuser.chatwebapp.enums.MessageTypeEnum;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.io.IOException;
import java.util.Map;


@SpringBootApplication
public class ChatWebAppApplication {


    public static ChatClient chatClient;

    public static void main(String[] args) {
        ConfigurableApplicationContext appContext = SpringApplication.run(ChatWebAppApplication.class, args);
        SimpMessagingTemplate messagingTemplate = appContext.getBean(SimpMessagingTemplate.class);
        try {
            chatClient = new ChatClient();
            if (!chatClient.connect()) {
                System.err.println("Connect failed.");
            } else {
                while (true) {
                    try {
                        readMessageLoop(chatClient, messagingTemplate);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // To read messages from the server
    private static void readMessageLoop(ChatClient chatClient, SimpMessagingTemplate messagingTemplate) throws IOException, ClassNotFoundException {
        Map map = (Map) chatClient.getObjectInputStream().readObject();
        if (MessageTypeEnum.HISTORY_OF_MESSAGES.getType().equals(map.keySet().stream().findFirst().get())) {
            messagingTemplate.convertAndSend("/chat/history", map.get(MessageTypeEnum.HISTORY_OF_MESSAGES.getType()));
        } else if (MessageTypeEnum.SENT_MESSAGE.getType().equals(map.keySet().stream().findFirst().get())) {
            messagingTemplate.convertAndSend("/chat/msgSent", map.get(MessageTypeEnum.SENT_MESSAGE.getType()));
        }
    }
}