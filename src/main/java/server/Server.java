package server;

import client.Client;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import controller.Controller;
import model.GameRoom;
import model.Room;
import model.TextMessage;
import model.User;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    public static ArrayList<Connection> connections = new ArrayList<>();
    public static ArrayList<Room> rooms = new ArrayList<>();

    public static ArrayList<GameRoom> gameRooms = new ArrayList<>();
    public static ArrayList<User> onlineUsers = new ArrayList<>();

//    public static Controller controller;

    public static ArrayList<User> savedUsers = new ArrayList<>();
    static {
        File usersFile = new File("Users.json");
        try {
            if (usersFile.exists()) {
                BufferedReader fileReader = new BufferedReader(new FileReader(usersFile));
                savedUsers = new Gson().fromJson(fileReader, new TypeToken<ArrayList<User>>() {
                }.getType());
                fileReader.close();
            }
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }
    static {
        Room room = new Room();
        room.roomID = "0";
        rooms.add(room);
    }
    public static void setServer(int mainPort){
        try {
            ServerSocket serverSocket = new ServerSocket(mainPort);
            while (true) {
                Socket socket = serverSocket.accept();
                Connection connection = new Connection(socket);
                connections.add(connection);
                connection.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Room getRoomByID(String roomID) {
        for (Room room : Server.rooms) {
            if (room.roomID.equals(roomID)) {
                return room;
            }
        }
        return null;
    }

    public static void updateRoomsJsonFile() {
        try {
            FileWriter fileWriter = new FileWriter("AllRooms.json");
            fileWriter.write(new Gson().toJson(Server.rooms));
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getRoomsJsonFile() {
        try {
            FileReader fileReader = new FileReader("AllRooms.json");
            Server.rooms = new Gson().fromJson(fileReader, new TypeToken<ArrayList<Room>>() {
            }.getType());
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateGameRoomsJsonFile(){
        try {
            FileWriter fileWriter = new FileWriter("AllGameRooms.json");
            fileWriter.write(new Gson().toJson(Server.gameRooms));
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getGameRoomsJsonFile(){
        try {
            FileReader fileReader = new FileReader("AllGameRooms.json");
            Server.gameRooms = new Gson().fromJson(fileReader, new TypeToken<ArrayList<GameRoom>>(){}.getType());
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateAllUsersJsonFile() {
        try {
            FileWriter fileWriter = new FileWriter("AllUsersServer.json");
            fileWriter.write(new Gson().toJson(Server.onlineUsers));
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getAllUsersJsonFile() {
        try {
            FileReader fileReader = new FileReader("AllUsersServer.json");
            Server.onlineUsers = new Gson().fromJson(fileReader, new TypeToken<ArrayList<User>>() {
            }.getType());
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static User getUserByUsername(String username) {
        for (User user : savedUsers) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

//    public static void mergeOnlineWithSavedUsers(){
//        for (User user : onlineUsers) {
//            for (User savedUser : savedUsers) {
//                if (user.getUsername().equals(savedUser.getUsername())) {
//
//                }
//
//                }
//        }
//    }
}
