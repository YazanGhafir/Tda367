package com.CEYMChat.View;

import com.CEYMChat.UserDisplayInfo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import java.io.IOException;
/**
 * Creates a GUI element for friends.
 */
public class FriendListItem {
    @FXML
    private AnchorPane friendPane;
    @FXML
    private Label friendUsername;
    @FXML
    private Circle onlineIndicator;
    @FXML
    private ImageView friendImg;
    @FXML
    private ImageView friendIndicator;

    private UserDisplayInfo uInfo = new UserDisplayInfo();


    public FriendListItem(UserDisplayInfo uInfo) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("View/friendListItem.fxml"));
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.friendUsername.setText(uInfo.getUsername());
    }

    public void toggleFriend(){     // Is called when you press the friendIndicator image
        uInfo.setIsFriend(!uInfo.getIsFriend());
        if(uInfo.getIsFriend()) {
            friendIndicator.setImage(new Image("friend.png"));
        }else if(!uInfo.getIsFriend()){
            friendIndicator.setImage(new Image("notFriend.png"));
        }
    }

    public void setFriend() {       // correctly sets the image of friendIndicator incase of a forced change in the isFriend variable
        if(uInfo.getIsFriend()) {
            friendIndicator.setImage(new Image("friend.png"));
        }else if(!uInfo.getIsFriend()){
            friendIndicator.setImage(new Image("notFriend.png"));
        }
    }

    /** Getters and setters **/
    public Label getFriendUsername() {
        return friendUsername;
    }

    public AnchorPane getFriendPane() {
        return friendPane;
    }

    public void setUInfo(UserDisplayInfo uInfo){
        this.uInfo = uInfo;
    }

    public UserDisplayInfo getUInfo(){
        return uInfo;
    }
}
