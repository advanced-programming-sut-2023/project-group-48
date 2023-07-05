package model;

import server.Server;

import java.util.ArrayList;

public class Room {
    public ArrayList<TextMessage> messages = new ArrayList<>();
    public ArrayList<String> users = new ArrayList<>();
    public String roomID;

    public boolean shouldUpdate = false;

    public boolean isForGame = false;

    public int maxPlayers = 0;


    public void seenAllMessages() {
        for (TextMessage message : messages) {
            message.seen = true;
        }
    }
}
