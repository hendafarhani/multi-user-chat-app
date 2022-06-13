package com.chat.server.repositories.Message;

import com.chat.server.entities.Message;

import java.util.List;

public interface MessageRepository {

     void save(Message message);

     List<Message> loadAll();
}
