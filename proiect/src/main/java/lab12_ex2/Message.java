package main.java.lab12_ex2;

import java.time.LocalDateTime;

public class Message {
    private final String username;
    private final String message;
    private final LocalDateTime timestamp;

    public Message(String username, String message) {
        this.username = username;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "[" + timestamp + "] " + username + ": " + message;
    }
}
