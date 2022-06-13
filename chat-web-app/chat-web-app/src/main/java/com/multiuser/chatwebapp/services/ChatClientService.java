package com.multiuser.chatwebapp.services;

import com.multiuser.chatwebapp.dtos.MessageDto;
import com.multiuser.chatwebapp.dtos.PersonDto;

import java.io.IOException;

public interface ChatClientService {

    void connect(PersonDto personDto) throws IOException;

    public void sendMessage(MessageDto messageDto) throws IOException;

}
