package com.chat.server.enums;

public enum DatabaseTypeEnum {

    SQL("sql");

    private String type;

    DatabaseTypeEnum(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
