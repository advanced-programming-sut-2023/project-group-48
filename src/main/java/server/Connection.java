package server;

import client.SessionTokenPacket;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.*;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Connection extends Thread{
    Socket socket;
    final DataInputStream dataInputStream;
    final DataOutputStream dataOutputStream;


    public static ArrayList<String> tokens = new ArrayList<>();
    private User user ;
    private SessionTokenPacket sessionTokenPacket;

    boolean successfullyIntroduced = false;

    RequestOnline request;


    public Connection(Socket socket) throws IOException {
        System.out.println("New connection form: " + socket.getInetAddress() + ":" + socket.getPort());
        this.socket = socket;
        this.dataInputStream = new DataInputStream(socket.getInputStream());
        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());


    }

    private void get_intro() {
        String intro = null;
        try {
            intro = dataInputStream.readUTF();

            if(intro.equals("json")){
                ArrayList<User> newUsers = new ArrayList<>();
                newUsers = new Gson().fromJson(dataInputStream.readUTF(), new TypeToken<ArrayList<User>>(){}.getType());
                for (User newUser : newUsers) {
                    if(Server.getUserByUsername(newUser.getUsername()) == null){
                        Server.onlineUsers.add(newUser);
                    }
                }
                Server.updateAllUsersJsonFile();
                dataOutputStream.writeUTF(new Gson().toJson(Server.onlineUsers));
                System.out.println("Users File Updated!");
                return;
            }

            sessionTokenPacket = new Gson().fromJson(intro, SessionTokenPacket.class);
            user = sessionTokenPacket.getUser();
            for (User onlineUser : Server.onlineUsers) {
                if(onlineUser.getUsername().equals(user.getUsername())){
                    if(DigestUtils.sha256Hex(user.getUsername()+user.getEncryptedPassword()).equals(sessionTokenPacket.getToken())) {
                        user = onlineUser;
                        dataOutputStream.writeUTF("success");
                        setLastSeenOnline();
                        return;
                    }else{
                        dataOutputStream.writeUTF("fail");
                        return;
                    }
                }
            }
            if(Server.getUserByUsername(user.getUsername()) != null){
                user = Server.getUserByUsername(user.getUsername());
                if(DigestUtils.sha256Hex(user.getUsername()+user.getEncryptedPassword()).equals(sessionTokenPacket.getToken())){
                    dataOutputStream.writeUTF("success");
                    Server.onlineUsers.add(user);
                    setLastSeenOnline();
                    tokens.add(sessionTokenPacket.getToken());
                    successfullyIntroduced = true;
                }else{
                    dataOutputStream.writeUTF("fail");
                }
            }else{
                dataOutputStream.writeUTF("fail");
            }
        }catch (Exception e) {
            System.out.println("Connection to " + socket.getInetAddress() + ":" + socket.getPort() + " lost.");
        }
    }


    @Override
    public synchronized void run() {
        while (!successfullyIntroduced) {
            get_intro();
        }


        while(true){
            try {
                String data = dataInputStream.readUTF();
                request = new Gson().fromJson(data, RequestOnline.class);

                if(request.logout){
                    logout();
                    return;
                }
                if(request.sendMap){
                    deliverMap(user.getUsername(), request.username, request.map);
                    return;
                }
                if(request.sendMessage){
                    deliverMessage(request.roomId, request.message);
                }
                if(request.makeRoom){
                    makeRoom(request.roomId, request.username);
                }
                if(request.editMessage){
                    editMessage(request.roomId, request.message, request.editedMessage);
                }
                if(request.deleteMessage){
                    deleteMessage(request.roomId, request.message);
                }
                if(request.seenMessage){
                    seenMessage(request.roomId);
                }
                if(request.reactedMessage){
                    reactedMessage(request.roomId, request.message, request.reaction);
                }
                if(request.getAllRooms){
                    getAllRooms();
                }
                if(request.updateUser){
                    updateUser(request.user);
                }
                if(request.sendFriendRequest){
                    sendFriendRequest(request.username, request.username2);
                }
                if(request.makeGameRoom){
                    startGame(request.username,request.roomId);
                }

            } catch (IOException e) {
                System.out.println("Connection to " + socket.getInetAddress() + ":" + socket.getPort() + " lost.");
                setLastSeenOffline();
                throw new RuntimeException(e);
            }
        }
    }



    public void logout() {
        try {
            Server.connections.remove(this);
            socket.close();
            setLastSeenOffline();
            tokens.remove(sessionTokenPacket.getToken());
            setLastSeenOffline();
        } catch (IOException e) {
            System.out.println("Connection to " + socket.getInetAddress() + ":" + socket.getPort() + " lost.");
            throw new RuntimeException(e);
        }
    }

    public void setLastSeenOffline(){
        for (User onlineUser : Server.onlineUsers) {
            if(onlineUser.getUsername().equals(user.getUsername())){
                onlineUser.lastSeen = java.time.LocalTime.now().toString().substring(0,5);
                updateAllRooms();
            }
        }
    }

    public void setLastSeenOnline(){
        for (User onlineUser : Server.onlineUsers) {
            if(onlineUser.getUsername().equals(user.getUsername())){
                onlineUser.lastSeen = "online";
                updateAllRooms();
            }
        }
    }

    public void updateAllRooms(){
        for (Room room : Server.rooms) {
            for (String user : room.users) {
                if(user.equals(this.user.getUsername())){
                    updateRoom(room);
                }
            }
        }
    }


    public void deliverMap(String sender, String receiver, SavableMap map){
        RequestOnline request = new RequestOnline();
        request.setReceiveMap(sender, map);
        String json = new Gson().toJson(request);
        for (Connection connection : Server.connections) {
            if(connection.user.getUsername().equals(receiver)){
                try {
                    connection.dataOutputStream.writeUTF(json);
                } catch (IOException e) {
                    System.out.println("Connection to " + socket.getInetAddress() + ":" + socket.getPort() + " lost.");
                    throw new RuntimeException(e);
                }
            }
        }
    }


    public void deliverMessage(String roomId, TextMessage message){
        Room room = Server.getRoomByID(roomId);
        if(room == null){
            return;
        }
        if(room.roomID.equals("0")){
            room.messages.add(message);
//            RequestOnline request = new RequestOnline();
//            request.setReceiveMessage(message, roomId);
//            String json = new Gson().toJson(request);
//            for (Connection connection : Server.connections) {
//                try {
//                    connection.dataOutputStream.writeUTF(json);
//                } catch (IOException e) {
//                    System.out.println("Connection to " + socket.getInetAddress() + ":" + socket.getPort() + " lost.");
//                    throw new RuntimeException(e);
//                }
//            }
            updateRoom(room);
            return;
        }else{
            for (String username : room.users) {
                for (Connection connection : Server.connections) {
                    if(connection.user.getUsername().equals(username)){
                        room.messages.add(message);
//                        RequestOnline request = new RequestOnline();
//                        request.setReceiveMessage(message, roomId);
//                        String json = new Gson().toJson(request);
//                        try {
//                            connection.dataOutputStream.writeUTF(json);
//                        } catch (IOException e) {
//                            System.out.println("Connection to " + socket.getInetAddress() + ":" + socket.getPort() + " lost.");
//                            throw new RuntimeException(e);
//                        }
                        updateRoom(room);
                        return;
                    }
                }
            }
        }
    }

    public void makeRoom(String roomId, String username){
        for (Room room : Server.rooms) {
            if(room.roomID.equals(roomId)){
                room.users.add(username);
                return;
            }
        }
        Room room = new Room();
        room.roomID = roomId;
        room.users.add(username);
        Server.rooms.add(room);

        for (Connection connection : Server.connections) {
            if(connection.user.getUsername().equals(username)){
                RequestOnline request = new RequestOnline();
                request.setJoinRoom(room);
                String json = new Gson().toJson(request);
                try {
                    connection.dataOutputStream.writeUTF(json);
                } catch (IOException e) {
                    System.out.println("Connection to " + socket.getInetAddress() + ":" + socket.getPort() + " lost.");
                    throw new RuntimeException(e);
                }
            }
        }
        Server.updateRoomsJsonFile();
    }



    public void updateRoom(Room room){
        for (Connection connection : Server.connections) {
            for (String username : room.users) {
                if(connection.user.getUsername().equals(username)){
                    RequestOnline request = new RequestOnline();
                    request.setUpdateRoom(room);
                    String json = new Gson().toJson(request);
                    try {
                        connection.dataOutputStream.writeUTF(json);
                    } catch (IOException e) {
                        System.out.println("Connection to " + socket.getInetAddress() + ":" + socket.getPort() + " lost.");
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }


    public void editMessage(String roomId, TextMessage message, String editedMessage) {
        Room room = Server.getRoomByID(roomId);
        if(room == null){
            return;
        }
        for (TextMessage textMessage : room.messages) {
            if(textMessage.message.equals(message.message) && textMessage.sentTime.equals(message.sentTime) && textMessage.username.equals(message.username)) {
                textMessage.message = editedMessage;
                updateRoom(room);
                return;
            }
        }
    }

    public void deleteMessage(String roomId, TextMessage message) {
        Room room = Server.getRoomByID(roomId);
        if(room == null){
            return;
        }
        for (TextMessage textMessage : room.messages) {
            if(textMessage.message.equals(message.message) && textMessage.sentTime.equals(message.sentTime) && textMessage.username.equals(message.username)) {
                room.messages.remove(textMessage);
                updateRoom(room);
                return;
            }
        }
    }

    public void seenMessage(String roomId){
        Room room = Server.getRoomByID(roomId);
        if(room == null){
            return;
        }
        for (TextMessage message : room.messages) {
            message.seen = true;
        }
        updateRoom(room);
        return;
    }


    public void reactedMessage(String roomId, TextMessage message, int reaction) {
        Room room = Server.getRoomByID(roomId);
        if(room == null){
            return;
        }
        for (TextMessage textMessage : room.messages) {
            if(textMessage.message.equals(message.message) && textMessage.sentTime.equals(message.sentTime) && textMessage.username.equals(message.username)) {
                textMessage.reaction = reaction;
                updateRoom(room);
                return;
            }
        }
    }


    public void getAllRooms(){
        RequestOnline request = new RequestOnline();
        request.getAllRoomsAnswer(Server.rooms);
        String json = new Gson().toJson(request);
        try {
            dataOutputStream.writeUTF(json);
        } catch (IOException e) {
            System.out.println("Connection to " + socket.getInetAddress() + ":" + socket.getPort() + " lost.");
            throw new RuntimeException(e);
        }
    }

    public void updateUser(User user){
        this.user = user;
        updateAllRooms();
    }

    public void sendFriendRequest(String username1, String username2){
        for (Connection connection : Server.connections) {
            if(connection.user.getUsername().equals(username2)){
                RequestOnline request = new RequestOnline();
                request.setReceiveFriendRequest(username1);
                String json = new Gson().toJson(request);
                try {
                    connection.dataOutputStream.writeUTF(json);
                } catch (IOException e) {
                    System.out.println("Connection to " + socket.getInetAddress() + ":" + socket.getPort() + " lost.");
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void startGame(String username, String roomId){
        GameRoom gameRoom = new GameRoom();
        gameRoom.roomID = roomId;
        gameRoom.users = Server.getRoomByID(roomId).users;
        Server.gameRooms.add(gameRoom);
    }



}
