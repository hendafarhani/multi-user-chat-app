package com.chat.server.repositories.Message;

import com.chat.server.enums.DatabaseTypeEnum;
import com.chat.server.repositories.repositoriesImpl.MessageRepositoryImpl;

public class MessageRepositoryFactory {

    public MessageRepository messageRepository;

    public MessageRepositoryFactory(String databaseType){
                this.messageRepository = DatabaseTypeEnum.SQL.getType().equals(databaseType)? new  MessageRepositoryImpl(): null;
    }
}
