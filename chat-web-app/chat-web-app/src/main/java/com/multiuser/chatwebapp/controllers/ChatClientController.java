package com.multiuser.chatwebapp.controllers;


import com.multiuser.chatwebapp.dtos.MessageDto;
import com.multiuser.chatwebapp.dtos.PersonDto;
import com.multiuser.chatwebapp.services.ChatClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/chat-client")
public class ChatClientController {

    @Autowired
    ChatClientService chatClientService;

    @RequestMapping(value="/connect", method = RequestMethod.POST)
    public boolean connect( @RequestBody PersonDto personDto) throws IOException {
        chatClientService.connect(personDto);
        return true;
    }

    @RequestMapping(value="/send", method = RequestMethod.POST)
    public void send( @RequestBody MessageDto messageDto) throws IOException {
        chatClientService.sendMessage(messageDto);
    }

}
