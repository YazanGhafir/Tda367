package com.CEYMChatLib;

import java.io.Serializable;

/** Generic class that works in conjunction with MessageFactory to create messages that are sent and received by the server/client. */

public class Message<T> implements Serializable {
    private String sender;
    private String receiver;
    private T data;


    /** Potential constructors */
    protected Message(T data, String sender, String receiver){
        this.data = data;
        this.sender = sender;
        this.receiver = receiver;
    }


    protected Message(T data, String sender){
        this.data = data;
        this.sender = sender;

    }


    protected Message(T data){
        this.data = data;
    }


    /** Getters and setters **/
    public String getSender() {
        return sender;
    }

    public T getData(){

        return this.data;
    }

    public Class getType(){
        return data.getClass();
    }
    public String getReceiver() {
        return receiver;
    }

}