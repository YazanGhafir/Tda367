package com.CEYMChat.Model;

import com.CEYMChat.Message;
import com.CEYMChat.MessageFactory;
import com.CEYMChat.Services.IWriter;
import com.CEYMChat.Services.Writer;
import com.CEYMChat.UserDisplayInfo;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class User {

    private String username;
    private IWriter writer;
    private Socket socket;
    private List<UserDisplayInfo> friends = new ArrayList();


    /** Getters and setters */
    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public Socket getSocket() {
        return socket;
    }

    public IWriter getWriter() {
        return writer;
    }

    public List<UserDisplayInfo> getFriends(){
        return friends;
    }

    public void initIO(Socket socket, ServerModel model) {
        this.writer = new Writer(socket);
        this.socket = socket;
    }


    /**
     * Syncs the users list of registered friends with
     * a list received by its corresponding client
     */
    public void syncFriends(Message m){
        List<UserDisplayInfo> receivedList = ((List<UserDisplayInfo>)(m.getData()));
        Boolean add = true;
        for(UserDisplayInfo uInfo : receivedList) {
            for(UserDisplayInfo friends:friends){
                if(uInfo.getUsername().equals(friends.getUsername())){
                    add = false;
                }
            }
            if(add){
                uInfo.setIsFriend(true);
                friends.add(uInfo);
            }
        }
    }


    /**
     * Merges this users friends with another list
     * of users so that they can both be sent to a client
     */
    public Message checkFriends(Message m) {
        List<UserDisplayInfo> listToSend = ((List<UserDisplayInfo>) (m.getData()));
        for (UserDisplayInfo friends : friends) {
            Boolean add = true;
            for (UserDisplayInfo uInfo : listToSend) {
                if (uInfo.getUsername().equals(friends.getUsername())) {
                    add = false;
                }
            }
            if (add) {
                friends.setIsFriend(true);
                listToSend.add(friends);
            }
        }
        return MessageFactory.createFriendInfoList(listToSend, m.getSender(), m.getReceiver());
    }


    /**
     *  Writes a message to the outputStream so
     *  that the client can read it from their inputStream
     */
    public void sendMessage(Message m) {
        writer.setOutMessage(m);
    }

    public void addFriends(UserDisplayInfo u) {
        friends.add(u);
    }

    public void removeFriends(UserDisplayInfo toRemove) {
        for (UserDisplayInfo uInfo : friends) {
            if (toRemove.getUsername() == uInfo.getUsername()) {
                friends.remove(uInfo);
                return;
            }
        }
    }



}
