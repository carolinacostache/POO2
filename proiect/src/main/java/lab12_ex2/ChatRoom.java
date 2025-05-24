package main.java.lab12_ex2;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ChatRoom {
    private final List<Message> messages = new CopyOnWriteArrayList<>();

    public void postMessage(String username, String message) {
        Message chatMessage = new Message(username, message);
        messages.add(chatMessage);
        System.out.println("Posted: " + chatMessage);
    }

    public List<Message> getMessages() {
        return messages;
    }
}
