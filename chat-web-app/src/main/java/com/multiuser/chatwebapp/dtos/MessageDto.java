package com.multiuser.chatwebapp.dtos;

public class MessageDto {

    private String textMsg;
    private String personName;

    public MessageDto(){}

    public MessageDto(String textMsg, String personName){
        this.textMsg = textMsg;
        this.personName = personName;
    }

    public String getTextMsg() {
        return textMsg;
    }

    public void setTextMsg(String textMsg) {
        this.textMsg = textMsg;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }
}

