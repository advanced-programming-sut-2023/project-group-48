package client;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import controller.Controller;
import javafx.scene.text.Text;
import model.*;
import org.apache.commons.codec.digest.DigestUtils;
import server.Server;

import java.io.*;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
    private static DataInputStream dataInputStream;
    private static DataOutputStream dataOutputStream;

    private static Controller controller;
    private static User user;

    public static ArrayList<Room> rooms = new ArrayList<>();

    public static ArrayList<GameRoom> gameRooms = new ArrayList<>();

    public static ArrayList<GameRoom> gameRoomsAll = new ArrayList<>();

    public static ArrayList<User> friends = new ArrayList<>();
    public static ArrayList<User> friendRequests = new ArrayList<>();


    public static void startClient(String host, int port) throws IOException {
        System.out.println("Starting Client service...");

        Socket socket = new Socket(host, port);
        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream = new DataOutputStream(socket.getOutputStream());

    }

    public static void startConnection(){
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

            NotifHandler notifHandler = new NotifHandler(dataInputStream);
            notifHandler.start();
        }
        System.out.println("Connected to server!");
    }

    public static void updateUsersFile(){
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

    public static void logout(){
        RequestOnline requestOnline = new RequestOnline();
        requestOnline.setLogout();
    }


    public static void sendMap(String username, SavableMap map){
        RequestOnline requestOnline = new RequestOnline();
        requestOnline.setSendMap(username, map);
        try {
            dataOutputStream.writeUTF(new Gson().toJson(requestOnline));
            System.out.println("Map sent!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sendMessage(String roomId, String message){
        RequestOnline requestOnline = new RequestOnline();
        requestOnline.setSendMessage(user.getUsername(),message,roomId);
        try {
            dataOutputStream.writeUTF(new Gson().toJson(requestOnline));
            System.out.println("Message sent!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void newRoom(String username){
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

    public static void addToRoom(String roomId, String username){
        RequestOnline requestOnline = new RequestOnline();
        requestOnline.setMakeRoom(username, roomId);
        try {
            dataOutputStream.writeUTF(new Gson().toJson(requestOnline));
            System.out.println("User added to room!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void editMessage(String roomId, TextMessage message, String editedMessage){
        RequestOnline requestOnline = new RequestOnline();
        requestOnline.setEditMessage(message, editedMessage, roomId);
        try {
            dataOutputStream.writeUTF(new Gson().toJson(requestOnline));
            System.out.println("Message edited!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteMessage(String roomID, TextMessage message){
        RequestOnline requestOnline = new RequestOnline();
        requestOnline.setDeleteMessage(message, roomID);
        try {
            dataOutputStream.writeUTF(new Gson().toJson(requestOnline));
            System.out.println("Message deleted!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void seenMessage(String roomId){
        RequestOnline requestOnline = new RequestOnline();
        requestOnline.setSeenMessage(roomId);
        try {
            dataOutputStream.writeUTF(new Gson().toJson(requestOnline));
            System.out.println("Messages seen!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setReaction(String roomId, TextMessage message, int reaction){
        RequestOnline requestOnline = new RequestOnline();
        requestOnline.setReaction(roomId, message, reaction);
        try {
            dataOutputStream.writeUTF(new Gson().toJson(requestOnline));
            System.out.println("Reaction set!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateAllRooms(){
        RequestOnline requestOnline = new RequestOnline();
        requestOnline.setGetAllRooms();
        try {
            dataOutputStream.writeUTF(new Gson().toJson(requestOnline));
            System.out.println("All rooms updated!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateMe(){
        RequestOnline requestOnline = new RequestOnline();
        requestOnline.setMe(user);
        try {
            dataOutputStream.writeUTF(new Gson().toJson(requestOnline));
            System.out.println("this user updated!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setFriendRequest(String username){
        RequestOnline requestOnline = new RequestOnline();
        requestOnline.setSendFriendRequest(user.getUsername(), username);
        try {
            dataOutputStream.writeUTF(new Gson().toJson(requestOnline));
            System.out.println("Friend request sent!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void answerFriendRequest(String username, boolean answer){
        for (User friend : friends) {
            if(friend.getUsername().equals(username)){
                ArrayList<User> removerequests = new ArrayList<>();
                for (User friendRequest : friendRequests) {
                    if(friendRequest.getUsername().equals(username)){
                        removerequests.add(friendRequest);
                    }
                }
                friendRequests.removeAll(removerequests);
                return;
            }
        }
        ArrayList<User> removerequests = new ArrayList<>();
        for (User friendRequest : friendRequests) {
            if(friendRequest.getUsername().equals(username)){
                removerequests.add(friendRequest);
            }
        }
        friends.add(removerequests.get(0));
        friendRequests.removeAll(removerequests);
    }

    public static void updateFriendFiles(){
        try {
            FileWriter fileWriter = new FileWriter("Friends.json");
            fileWriter.write(new Gson().toJson(Client.friends));
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getFriendFiles(){
        try {
            FileReader fileReader = new FileReader("Friends.json");
            Client.friends = new Gson().fromJson(fileReader, new TypeToken<ArrayList<User>>(){}.getType());
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void makeGameRoom(int maxPlayers){
        RequestOnline requestOnline = new RequestOnline();
        requestOnline.makeGameRoom(user.getUsername(), user.getUsername() + gameRooms.size(), maxPlayers);
        try {
            dataOutputStream.writeUTF(new Gson().toJson(requestOnline));
            System.out.println("Game Room made!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void getAllGameRooms(){
        RequestOnline requestOnline = new RequestOnline();
        requestOnline.getAllGameRooms();
        try {
            dataOutputStream.writeUTF(new Gson().toJson(requestOnline));
            System.out.println("Game Room made!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void joinGameRoom(String roomID){
        RequestOnline requestOnline = new RequestOnline();
        requestOnline.getJoinGameRoom(roomID);
        try {
            dataOutputStream.writeUTF(new Gson().toJson(requestOnline));
            System.out.println("Game Room made!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }










}
