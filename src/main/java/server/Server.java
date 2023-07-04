package server;

import client.Client;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import controller.Controller;
import model.GameRoom;
import model.Room;
import model.TextMessage;
import model.User;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    public static ArrayList<Connection> connections = new ArrayList<>();
    public static ArrayList<Room> rooms = new ArrayList<>();

    public static ArrayList<GameRoom> gameRooms = new ArrayList<>();
    public static ArrayList<User> onlineUsers = new ArrayList<>();

    public static Controller controller;

    static {
        Room room = new Room();
        room.roomID = "0";
        rooms.add(room);
    }

    public static void setServer(int mainPort) {
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
        for (User user : controller.getGame().getUsers()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
