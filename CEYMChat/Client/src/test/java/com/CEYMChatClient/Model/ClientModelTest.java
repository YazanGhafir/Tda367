package com.CEYMChatClient.Model;

import com.CEYMChatLib.Message;
import com.CEYMChatLib.MessageFactory;
import com.CEYMChatLib.UserInfo;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ClientModelTest {

    private static ClientModel model = new ClientModel();
   static private List<Message<String>> testList = new ArrayList<>();

    /** creates a virtual saved messages */
    @BeforeClass
   static public void mockUpSavedMessages(){
        UserInfo testUserInfo1 = new UserInfo();
        UserInfo testUserInfo2 = new UserInfo();
        testUserInfo1.setUsername("test1");
        testUserInfo2.setUsername("test2");
        model.addReceivedMessage(MessageFactory.createStringMessage("Hello World",testUserInfo1,"test2"));
        model.addReceivedMessage(MessageFactory.createStringMessage("Hello World2",testUserInfo1,"test2"));
        model.addSentMessage(MessageFactory.createStringMessage("Hello World3",testUserInfo2,"test1"));
        model.addSentMessage(MessageFactory.createStringMessage("Hello World4",testUserInfo2,"test1"));
        testList.add(MessageFactory.createStringMessage("Hello World",testUserInfo1,"test2"));
        testList.add(MessageFactory.createStringMessage("Hello World2",testUserInfo1,"test2"));
        testList.add(MessageFactory.createStringMessage("Hello World3",testUserInfo2,"test1"));
        testList.add(MessageFactory.createStringMessage("Hello World4",testUserInfo2,"test1"));
        model.saveSendMessages("messages/sent.csv");
        model.saveReceivedMessages("messages/received.csv");

    }

    /** saves the virtual messages to a certain file locally */
    @Test
    public void saveMessagesToFile() {
        try {
            model.saveArrayListToFile(testList,"messages/test.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        File testFile = new File("messages/test.csv");
        boolean exists = testFile.exists();
        assertTrue("MessageFile created successfully", exists);
    }


    /** saves the received messages to a certain files */
    @Test
    public void saveReceivedMessages() {
        model.saveReceivedMessages("messages/received.csv");
        //Passes if no exception
    }


    /** saves and sends the messages */
    @Test
    public void saveSendMessages() {
        model.saveSendMessages("messages/sent.csv");
        //Passes if no exception

    }

    /** loads the saved sended messages */
    @Test
    public void loadSavedSentMessage() throws IOException {
        List<String> expected = new ArrayList<>();
        List<String> actual = new ArrayList<>();
        String[] expectedArray = {"test2: ", "Hello World3", "test2: ", "Hello World4"};
        expected.addAll(Arrays.asList(expectedArray));
        actual = model.loadSavedMessages("messages/sent.csv");
        assertEquals("Loaded messages match expected value",expected,actual);

        //Overwrites the test-files
        FileWriter writer = new FileWriter("messages/sent.csv");
        writer.write("");
    }


    /** loads the saved recieved messages */
    @Test
    public void loadSavedReceivedMessage() throws IOException {
        List<String> expected = new ArrayList<>();
        List<String> actual = new ArrayList<>();
        String[] expectedArray = {"test1: ", "Hello World", "test1: ", "Hello World2"};
        expected.addAll(Arrays.asList(expectedArray));
        actual = model.loadSavedMessages("messages/received.csv");
        assertEquals("Loaded messages match expected value",expected,actual);

        //Overwrites the test-files
        FileWriter writer = new FileWriter("messages/received.csv");
        writer.write("");

    }
}