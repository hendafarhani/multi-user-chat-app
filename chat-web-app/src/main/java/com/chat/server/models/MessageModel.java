package com.chat.server.models;

import java.io.Serializable;
import java.util.Date;

public class MessageModel implements Serializable {

    public static final long serialVersionUID = 2497899571976689777L;

    private String textMsg;
    private PersonModel person;
    private Date date;

    public MessageModel() {
    }

    public MessageModel(String textMsg, PersonModel person, Date date) {
        this.textMsg = textMsg;
        this.person = person;
        this.date = date;
    }

    public String getTextMsg() {
        return textMsg;
    }

    public void setTextMsg(String textMsg) {
        this.textMsg = textMsg;
    }

    public PersonModel getPerson() {
        return person;
    }

    public void setPerson(PersonModel person) {
        this.person = person;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
