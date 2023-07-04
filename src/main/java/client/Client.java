package client;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import controller.Controller;
import javafx.scene.text.Text;
import model.*;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
    final DataInputStream dataInputStream;
    final DataOutputStream dataOutputStream;

    private Controller controller;
    private User user;

    public static ArrayList<Room> rooms = new ArrayList<>();

    public Client(String host, int port) throws IOException {
        System.out.println("Starting Client service...");

        Socket socket = new Socket(host, port);
        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream = new DataOutputStream(socket.getOutputStream());

    }

    public void startConnection(){
        System.out.println("Connecting to server...");
        user = controller.getGame().getCurrentUser();
        String input;
        while(true) {
            SessionTokenPacket sessionTokenPacket = new SessionTokenPacket(user);
            sessionTokenPacket.setToken(DigestUtils.sha256Hex(user.getUsername()+user.getEncryptedPassword()));
            try {
                dataOutputStream.writeUTF(new Gson().toJson(sessionTokenPacket));
                input = dataInputStream.readUTF();
                if(input.equals("success")){
                    break;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Connected to server!");
    }

    public void updateUsersFile(){
        try {
            dataOutputStream.writeUTF("json");
            dataOutputStream.writeUTF(new Gson().toJson(controller.getGame().getUsers()));
            ArrayList<User> users = new Gson().fromJson(dataInputStream.readUTF(), new TypeToken<ArrayList<User>>(){}.getType());
            controller.getGame().setUsers(users);

            System.out.println("Users File Updated!");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void logout(){
        RequestOnline requestOnline = new RequestOnline();
        requestOnline.setLogout();
    }


    public void sendMap(String username, SavableMap map){
        RequestOnline requestOnline = new RequestOnline();
        requestOnline.setSendMap(username, map);
        try {
            dataOutputStream.writeUTF(new Gson().toJson(requestOnline));
            System.out.println("Map sent!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(String roomId, String message){
        RequestOnline requestOnline = new RequestOnline();
        requestOnline.setSendMessage(user.getUsername(),message,roomId);
        try {
            dataOutputStream.writeUTF(new Gson().toJson(requestOnline));
            System.out.println("Message sent!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void newRoom(String username){
        String roomID = user.getUsername() + rooms.size();
        RequestOnline requestOnline = new RequestOnline();
        requestOnline.setMakeRoom(username, roomID);
        try {
            dataOutputStream.writeUTF(new Gson().toJson(requestOnline));
            System.out.println("Room made!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addToRoom(String roomId, String username){
        RequestOnline requestOnline = new RequestOnline();
        requestOnline.setMakeRoom(username, roomId);
        try {
            dataOutputStream.writeUTF(new Gson().toJson(requestOnline));
            System.out.println("User added to room!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void editMessage(String roomId, TextMessage message, String editedMessage){
        RequestOnline requestOnline = new RequestOnline();
        requestOnline.setEditMessage(message, editedMessage, roomId);
        try {
            dataOutputStream.writeUTF(new Gson().toJson(requestOnline));
            System.out.println("Message edited!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteMessage(String roomID, TextMessage message){
        RequestOnline requestOnline = new RequestOnline();
        requestOnline.setDeleteMessage(message, roomID);
        try {
            dataOutputStream.writeUTF(new Gson().toJson(requestOnline));
            System.out.println("Message deleted!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void seenMessage(String roomId){
        RequestOnline requestOnline = new RequestOnline();
        requestOnline.setSeenMessage(roomId);
        try {
            dataOutputStream.writeUTF(new Gson().toJson(requestOnline));
            System.out.println("Messages seen!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setReaction(String roomId, TextMessage message, int reaction){
        RequestOnline requestOnline = new RequestOnline();
        requestOnline.setReaction(roomId, message, reaction);
        try {
            dataOutputStream.writeUTF(new Gson().toJson(requestOnline));
            System.out.println("Reaction set!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateAllRooms(){
        RequestOnline requestOnline = new RequestOnline();
        requestOnline.setGetAllRooms();
        try {
            dataOutputStream.writeUTF(new Gson().toJson(requestOnline));
            System.out.println("All rooms updated!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateMe(){
        RequestOnline requestOnline = new RequestOnline();
        requestOnline.setMe(user);
        try {
            dataOutputStream.writeUTF(new Gson().toJson(requestOnline));
            System.out.println("this user updated!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }








}
