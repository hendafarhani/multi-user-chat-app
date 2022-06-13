package com.chat.server.enums;

public enum MessageTypeEnum {

    HISTORY_OF_MESSAGES("historyOfMessages"), SENT_MESSAGE("sentMsg"),
    CONNECT("connect"), RECEIVED_MESSAGE("receivedMsg");

    private String type;

    MessageTypeEnum(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
