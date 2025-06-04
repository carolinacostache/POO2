package main.java.oop2_project.audit;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuditService {

    private static AuditService instance;
    private static final String FILE_PATH = "audit.csv";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    private AuditService() {
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            if (writer.getEncoding() != null && writer.toString().isEmpty()) {
                writer.append("action,timestamp\n");
            }
        } catch (IOException e) {
            System.out.println("Could not initialize audit file: " + e.getMessage());
        }
    }

    public static AuditService getInstance() {
        if (instance == null) {
            instance = new AuditService();
        }
        return instance;
    }

    public void log(String action) {
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            String timestamp = LocalDateTime.now().format(formatter);
            writer.append(action).append(",").append(timestamp).append("\n");
        } catch (IOException e) {
            System.out.println("Failed to write audit log: " + e.getMessage());
        }
    }
}
