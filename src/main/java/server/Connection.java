package server;

import client.SessionTokenPacket;
import com.google.gson.Gson;
import controller.Controller;
import model.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class Connection extends Thread{
    Socket socket;
    final DataInputStream dataInputStream;
    final DataOutputStream dataOutputStream;

    public static ArrayList<User> users = new ArrayList<>();
    public static ArrayList<String> tokens = new ArrayList<>();
    private User user ;
    private SessionTokenPacket sessionTokenPacket;
    Controller controller;
    boolean successfullyIntroduced = false;


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
            sessionTokenPacket = new Gson().fromJson(intro, SessionTokenPacket.class);
            user = sessionTokenPacket.getUser();
            if(controller.getGame().getUserByUsername(user.getUsername()) != null){
                user = controller.getGame().getUserByUsername(user.getUsername());
                if(DigestUtils.sha256Hex(user.getUsername()+user.getEncryptedPassword()).equals(sessionTokenPacket.getToken())){
                    dataOutputStream.writeUTF("success");
                    users.add(user);
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


        try {
            String data = dataInputStream.readUTF();



        } catch (IOException e) {
            System.out.println("Connection to " + socket.getInetAddress() + ":" + socket.getPort() + " lost.");
            throw new RuntimeException(e);
        }

    }


    public void logout() {
        try {
            socket.close();
            users.remove(user);
            tokens.remove(sessionTokenPacket.getToken());
        } catch (IOException e) {
            System.out.println("Connection to " + socket.getInetAddress() + ":" + socket.getPort() + " lost.");
            throw new RuntimeException(e);
        }
    }





}
