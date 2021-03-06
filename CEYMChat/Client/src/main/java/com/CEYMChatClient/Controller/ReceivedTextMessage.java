package com.CEYMChatClient.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;


/**
 *  * Controller for textMessageReciever.fxml
 * * Creates a GUI element for a received text message.
 */
public class ReceivedTextMessage implements IFXMLController {

    @FXML
    public AnchorPane rMessagePane;
    @FXML
    public Label rMessageTextLabel;
    String rMessage;

    /**
     * constructor with initialized the received message
     * @param rMessage the received message
     * @throws IOException
     */
    public ReceivedTextMessage(String rMessage) throws IOException {

        this.rMessage = rMessage;
        load();
        this.rMessageTextLabel.setText(rMessage);
    }

    @Override
    public void load() {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("View/textMessageReciever.fxml"));
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    public AnchorPane getPane(){
        return rMessagePane;
    }
}
