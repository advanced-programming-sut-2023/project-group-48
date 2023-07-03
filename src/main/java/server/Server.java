package server;

import model.TextMessage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    public static ArrayList<Connection> connections = new ArrayList<>();
    public static ArrayList<TextMessage> publicChatMessages = new ArrayList<>();
    public Server(int port){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true){
                Socket socket = serverSocket.accept();
                Connection connection = new Connection(socket);
                connections.add(connection);
                connection.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
