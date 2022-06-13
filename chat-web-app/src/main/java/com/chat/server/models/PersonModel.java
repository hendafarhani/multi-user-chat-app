package com.chat.server.models;

import java.io.Serializable;
import java.util.List;

public class PersonModel implements Serializable {

    public static final long serialVersionUID = 3958139253525830748L;

    private String name;
    private List<MessageModel> messages;

    public PersonModel(){
    }

    public PersonModel(String name, List<MessageModel> messages){
        this.name = name;
        this.messages = messages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MessageModel> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageModel> messages) {
        this.messages = messages;
    }
}
