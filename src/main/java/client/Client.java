package client;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import controller.Controller;
import model.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
    final DataInputStream dataInputStream;
    final DataOutputStream dataOutputStream;

    private Controller controller;
    private User user;

    public Client(String host, int port) throws IOException {
        System.out.println("Starting Client service...");

        Socket socket = new Socket(host, port);
        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream = new DataOutputStream(socket.getOutputStream());


    }

    public void startConnection(){
        System.out.println("Connecting to server...");
        user = controller.getGame().getCurrentUser();
        String input;
        while(true) {
            SessionTokenPacket sessionTokenPacket = new SessionTokenPacket(user);
            sessionTokenPacket.setToken(DigestUtils.sha256Hex(user.getUsername()+user.getEncryptedPassword()));
            try {
                dataOutputStream.writeUTF(new Gson().toJson(sessionTokenPacket));
                input = dataInputStream.readUTF();
                if(input.equals("success")){
                    break;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Connected to server!");
    }

    public void updateUsersFile(){
        try {
            dataOutputStream.writeUTF("json");
            dataOutputStream.writeUTF(new Gson().toJson(controller.getGame().getUsers()));
            ArrayList<User> users = new Gson().fromJson(dataInputStream.readUTF(), new TypeToken<ArrayList<User>>(){}.getType());
            controller.getGame().setUsers(users);

            System.out.println("Users File Updated!");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }







}
