package com.CEYMChat;
import com.CEYMChat.Model.ClientModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import javax.ws.rs.client.Client;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;


public class ClientController {
    ClientModel model = ClientModel.getModelInstance();



    /**
     * Private constructor with getControllerInstance()
     * to ensure only one controller is ever created (Singleton pattern)
     * **/

    @FXML
    TextArea chatWindow;
    @FXML
    Button sendButton;
    @FXML
    TextField loginTextField;
    @FXML
    Button loginButton;
    @FXML
    Button connectButton;
    @FXML
    TextField chatBox;

    @FXML
    TextArea sendWindow;
    @FXML
    TextArea receiveWindow;
    @FXML
    TextField sendToTextField;



    Parent login;
    Stage loginStage = new Stage();



    /**
     * Captures input from user and send makes use of model to send message
     */
    public void sendString() throws IOException {
        String toSend = chatBox.getText();
        chatBox.setText("");
        model.sendStringMessage(toSend, sendToTextField.getText());
        sendWindow.appendText("Me: "+toSend+"\n");
    }

    public void connectToServer(MouseEvent mouseEvent) {
        try{

            URL url = Paths.get("Client/src/main/resources/View/login.fxml").toUri().toURL();
            login = FXMLLoader.load(url);
            loginStage.initModality(Modality.APPLICATION_MODAL);
            loginStage.initStyle(StageStyle.UTILITY);
            loginStage.setTitle("Login");
            loginStage.setScene(new Scene(login));
            loginStage.show();
            model.connectToServer();
            toggleChatBox();
            connectButton.setDisable(true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void login(){
        try {
            model.sendCommandMessage("setUser", loginTextField.getText());
            model.setUser(loginTextField.getText());
            Window window = loginButton.getScene().getWindow();
            window.hide();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void toggleChatBox(){
        if (chatBox.isEditable())
            chatBox.setEditable(false);
        else{
            chatBox.setEditable(true);
        }
    }

    public void displayNewMessage(String s) {
        chatWindow.appendText(s + "\n");
    }
    public void requestChat(){
        try {
            model.sendCommandMessage("requestChat","user2");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
