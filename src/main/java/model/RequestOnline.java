package model;

import java.util.ArrayList;

public class RequestOnline {

    public boolean logout = false;
    public boolean sendMap = false;
    public boolean receiveMap = false;

    public boolean sendMessage = false;
    public boolean receiveMessage = false;

    public boolean makeRoom = false;

    public boolean joinRoom = false;
    public boolean joinGameRoom = false;

    public boolean updateRoom = false;

    public boolean updateGameRoom = false;

    public boolean editMessage = false;

    public boolean deleteMessage = false;

    public boolean seenMessage = false;

    public boolean reactedMessage = false;

    public boolean getAllRooms = false;

    public boolean getAllRoomsAnswer = false;

    public boolean updateUser = false;

    public boolean sendFriendRequest = false;

    public boolean receiveFriendRequest = false;

    public boolean makeGameRoom = false;

    public boolean getAllGameRooms = false;

    public boolean setAllGameRooms = false;

    public boolean getJoinGameRoom = false;

    public boolean updateUserFile = false;

    public String username = "";
    public String username2 = "";

    public SavableMap map = null;
    public TextMessage message;
    public String editedMessage;
    public String roomId = "";
    public Room room;

    public int maxPlayers = 0;

    public GameRoom gameRoom;

    public User user;

    public ArrayList<Room> rooms = new ArrayList<>();

    public ArrayList<GameRoom> gameRooms = new ArrayList<>();

    public ArrayList<User> users = new ArrayList<>();

    public int reaction = 0;


    public void setSendMap(String username, SavableMap map){
        sendMap = true;
        this.username = username;
        this.map = map;
    }

    public void setReceiveMap(String username, SavableMap map){
        receiveMap = true;
        this.username = username;
        this.map = map;
    }


    public void setLogout() {
        logout = true;
    }

    public void setSendMessage(String username, String message, String roomId) {
        TextMessage textMessage = new TextMessage();
        textMessage.username = username;
        textMessage.message = message;
        textMessage.sentTime = java.time.LocalTime.now().toString().substring(0,5);
        this.roomId = roomId;
        this.message = textMessage;
    	sendMessage = true;
    }

    public void setReceiveMessage(TextMessage message, String roomId) {
    	this.message = message;
    	this.roomId = roomId;
    	receiveMessage = true;
    }

    public void setMakeRoom(String username, String roomId) {
        makeRoom = true;
        this.username = username;
        this.roomId = roomId;
    }

    public void setJoinRoom(Room room) {
        joinRoom = true;
        this.room = room;
    }

    public void setUpdateRoom(Room room){
        updateRoom = true;
        this.room = room;
    }

    public void setUpdateGameRoom(GameRoom gameRoom){
        updateRoom = true;
        this.gameRoom = gameRoom;
    }

    public void setEditMessage(TextMessage message, String editedMessage ,String roomId) {
    	this.message = message;
        this.editedMessage = editedMessage;
    	this.roomId = roomId;
    	editMessage = true;
    }

    public void setDeleteMessage(TextMessage message, String roomID) {
        this.message = message;
        this.roomId = roomID;
        deleteMessage = true;
    }

    public void setSeenMessage(String roomId){
        this.roomId = roomId;
        seenMessage = true;
    }

    public void setReaction(String roomId, TextMessage message, int reaction){
        this.message = message;
        this.roomId = roomId;
        this.reaction = reaction;
        reactedMessage = true;
    }

    public void setGetAllRooms() {
    	getAllRooms = true;
    }

    public void getAllRoomsAnswer(ArrayList<Room> rooms){
        getAllRoomsAnswer = true;
        this.rooms = rooms;
    }

    public void setMe(User user){
        updateUser = true;
        this.user = user;
    }

    public void setSendFriendRequest(String username, String username2){
        this.username = username;
        this.username2 = username2;
        sendFriendRequest = true;
    }

    public void setReceiveFriendRequest(User user){
        this.user = user;
        receiveFriendRequest = true;
    }

    public void makeGameRoom(String username, String roomId, int maxPlayers){
    	makeGameRoom = true;
        this.username = username;
        this.roomId = roomId;
        this.maxPlayers = maxPlayers;
    }

    public void setJoinGameRoom(GameRoom gameRoom) {
        joinGameRoom = true;
        this.gameRoom = gameRoom;
    }
    public void getJoinGameRoom(String gameRoomName) {
        getJoinGameRoom = true;
        this.roomId = gameRoomName;
    }

    public void getAllGameRooms(){
        getAllGameRooms = true;
    }

    public void setAllGameRooms(ArrayList<GameRoom> gameRooms){
        setAllGameRooms = true;
        this.gameRooms = gameRooms;
    }


    public void updateUserFile(ArrayList<User> users){
        updateUserFile = true;
        this.users = users;
    }
}
