package model;

public class RequestOnline {

    public boolean logout = false;
    public boolean sendMap = false;
    public boolean receiveMap = false;

    public boolean sendMessage = false;



    public String username = "";
    public SavableMap map = null;
    public String message = "";
    public String


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

    public void setSendMessage(String username, String message, String roomid) {
        this.username = username;
        this.message = message;
    	sendMessage = true;
    }
}
