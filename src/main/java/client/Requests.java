package client;

import model.SavableMap;

public class Requests {
    boolean sendMap = false;
    boolean receiveMap = false;




    String username;
    SavableMap map;
    public void setSendMap(String username, SavableMap map){
        sendMap = true;
        this.username = username;
        this.map = map;
    }
}
