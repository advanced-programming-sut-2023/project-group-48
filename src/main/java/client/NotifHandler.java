package client;

import com.google.gson.Gson;
import model.MapMethods;
import model.RequestOnline;
import model.SavableMap;

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

                if(request.receiveMap){

                    SavableMap map = request.map;
                    map.sentFromOthers = true;
                    map.accepted = false;   //TODO: accepted graphics
                    MapMethods.saveMap(map);
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

