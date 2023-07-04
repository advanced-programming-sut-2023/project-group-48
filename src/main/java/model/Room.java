package model;

import server.Server;

import java.util.ArrayList;

public class Room {
    public ArrayList<TextMessage> messages = new ArrayList<>();
    public ArrayList<String> users = new ArrayList<>();
    public String roomID;

    public boolean shouldUpdate = false;






}
