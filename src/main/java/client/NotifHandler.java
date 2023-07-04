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
                if(request.updateRoom){
                    for (Room room : Client.rooms) {
                        if(room.roomID.equals(request.room.roomID)) {
                            room = request.room;
                            room.shouldUpdate = true;
                            break;
                        }
                    }
                }
                if(request.getAllRoomsAnswer){
                    Client.rooms = request.rooms;
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

