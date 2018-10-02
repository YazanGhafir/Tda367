package com.CEYMChat;
import javafx.scene.image.Image;
import java.io.File;


/**
 * Factory for creating Message(s) of predefined types.
 */
public class MessageFactory {


    public static Message<String> createStringMessage(String data, String user){
        return new Message(data, user);
    }
    public static Message<Image> createImageMessage(Image data, String user){
        return new Message(data, user);
    }
    public static Message<File> createFileMessage(File data, String user) {
        return new Message(data, user);
    }
    public static Message<Command> createCommandMessage(Command data, String user){
        return new Message(data, user);
    }
}
