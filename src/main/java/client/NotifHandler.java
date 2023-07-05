package client;

import com.google.gson.Gson;
import model.*;

import java.io.DataInputStream;
import java.io.IOException;

public class NotifHandler extends Thread{

    private DataInputStream dataInputStream;

    public NotifHandler(DataInputStream dataInputStream) {
        this.dataInputStream = dataInputStream;
    }
    @Override
    public synchronized void run() {
        while (true) {
            try {
                String data = dataInputStream.readUTF();
                RequestOnline request;
                request = new Gson().fromJson(data, RequestOnline.class);

                if(request.receiveMap) {
                    SavableMap map = request.map;
                    map.sentFromOthers = true;
                    map.accepted = false;   //TODO: accepted graphics
                    MapMethods.saveMap(map);
                }
                if(request.receiveMessage) {
                    TextMessage message = request.message;
                    for (Room room : Client.rooms) {
                        if(room.roomID.equals(request.roomId)) {
                            room.messages.add(message);
                            room.shouldUpdate = true;
                            break;
                        }
                    }
                }
                if(request.joinRoom) {
                    Client.rooms.add(request.room);
                }
                if(request.joinGameRoom) {
                    Client.gameRooms.add(request.gameRoom);
                }
                if(request.updateRoom){
                    Client.setRoomByID(request.room.roomID, request.room);
//                    System.out.println("room updated2");
//                    for (Room room : Client.rooms) {
//                        if(room.roomID.equals(request.room.roomID)) {
//                            room = request.room;
//                            room.shouldUpdate = true;
//                            System.out.println("room updated3");
//                            System.out.println(room);
//                            System.out.println(room.messages);
//                            System.out.println(Client.rooms.get(0).messages);
//                            System.out.println(Client.rooms);
//                            break;
//                        }
//                    }
//                    if(Client.rooms.size() > 0) {
//                        Client.rooms.remove(0);
//                        Client.rooms.add(request.room);
//                        if(Client.rooms.get(0).messages.size() > 0) {
//                            System.out.println("room updated4: " + Client.rooms.get(0).messages.get(0).message);
//                        }
//                    }
                }
                if(request.updateGameRoom){
                    for (GameRoom gameRoom : Client.gameRooms) {
                        if(gameRoom.roomID.equals(request.gameRoom.roomID)) {
                            gameRoom = request.gameRoom;
                            gameRoom.shouldUpdate = true;
                            break;
                        }
                    }
                }
                if(request.getAllRoomsAnswer){
                    Client.rooms = request.rooms;
                    System.out.println("rooms received");
                    System.out.println(Client.rooms.get(0));
                }
                if(request.setAllGameRooms){
                    Client.gameRooms = request.gameRooms;
                }
                if(request.receiveFriendRequest){
                    Client.friendRequests.add(request.user);
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

