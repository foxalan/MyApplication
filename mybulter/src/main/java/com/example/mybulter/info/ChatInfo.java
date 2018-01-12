package com.example.mybulter.info;





public class ChatInfo {

    private String context;
    private String data;
    private int type;
    private Enum<Type> typeEnum;

    public ChatInfo(String context, String data, Enum<Type> typeEnum) {
        this.context = context;
        this.data = data;
        this.typeEnum = typeEnum;
    }

    public ChatInfo(){

    }

    public ChatInfo(String context, String data, int type) {
        this.context = context;
        this.data = data;
        this.type = type;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setTypeEnum(Enum<Type> typeEnum) {
        this.typeEnum = typeEnum;
    }

    public Enum<Type> getTypeEnum() {
        return typeEnum;
    }


}
