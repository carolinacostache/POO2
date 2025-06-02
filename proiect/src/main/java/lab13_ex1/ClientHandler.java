package main.java.lab13_ex1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

public class ClientHandler extends Thread {
    private Socket socket;
    private String username;
    private PrintWriter out;
    private BufferedReader in;
    private ConcurrentHashMap<String, ClientHandler> userMap;
    private LinkedList<String> messageHistory;

    public ClientHandler(Socket socket, ConcurrentHashMap<String, ClientHandler> userMap, LinkedList<String> messageHistory) {
        this.socket = socket;
        this.userMap = userMap;
        this.messageHistory = messageHistory;
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                out.println("Please enter an username:");
                String name = in.readLine();
                if (name == null || name.trim().isEmpty()) continue;
                synchronized (userMap) {
                    if (!userMap.containsKey(name)) {
                        username = name;
                        userMap.put(username, this);
                        break;
                    } else {
                        out.println("Username isn't available.");
                    }
                }
            }

            ChatServer.broadcast("[server]: " + username + " has joined the chat.");
            for (String msg : ChatServer.getMessageHistory()) {
                sendMessage("[history] " + msg);
            }

            String message;
            while ((message = in.readLine()) != null) {
                if (message.startsWith("/msg ")) {
                    String[] split = message.split(" ", 3);
                    if (split.length >= 3) {
                        String recipient = split[1];
                        String msgBody = split[2];
                        ChatServer.privateMessage(username, recipient, msgBody);
                    } else {
                        sendMessage("Usage: /msg username message");
                    }
                } else {
                    ChatServer.broadcast(username + ": " + message);
                }
            }
        } catch (IOException e) {
            System.out.println("Error handling client: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (username != null) {
                ChatServer.removeUser(username);
                ChatServer.broadcast("[server]: " + username + " has left the chat.");
            }
        }
    }
}