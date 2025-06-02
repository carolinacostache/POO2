package main.java.lab13_ex1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ChatServer {
    private static final int PORT = 12345;
    private static ConcurrentHashMap<String, ClientHandler> userMap = new ConcurrentHashMap<>();
    private static LinkedList<String> messageHistory = new LinkedList<>();
    private static final int MAX_HISTORY = 20;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Chat server started on port " + PORT);

        while (true) {
            Socket socket = serverSocket.accept();
            new ClientHandler(socket, userMap, messageHistory).start();
        }
    }

    public static synchronized void broadcast(String message) {
        addToHistory(message);
        for (ClientHandler handler : userMap.values()) {
            handler.sendMessage(message);
        }
    }

    public static synchronized void privateMessage(String from, String to, String message) {
        ClientHandler recipient = userMap.get(to);
        if (recipient != null) {
            recipient.sendMessage("[private] " + from + ": " + message);
        } else {
            ClientHandler sender = userMap.get(from);
            if (sender != null) {
                sender.sendMessage("username '" + to + "' not found.");
            }
        }
    }

    public static synchronized void addUser(String username, ClientHandler handler) {
        userMap.put(username, handler);
    }

    public static synchronized void removeUser(String username) {
        userMap.remove(username);
    }

    public static synchronized boolean usernameExists(String username) {
        return userMap.containsKey(username);
    }

    public static synchronized List<String> getMessageHistory() {
        return new ArrayList<>(messageHistory);
    }

    private static synchronized void addToHistory(String message) {
        if (messageHistory.size() >= MAX_HISTORY) {
            messageHistory.removeFirst();
        }
        messageHistory.add(message);
    }
}