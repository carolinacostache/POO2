import config.ConnectionProvider;
import model.Account;
import model.Client;
import repository.BankRepository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        BankRepository repository = BankRepository.getInstance();

        try (Connection connection = ConnectionProvider.getConnection()) {
            repository.cleanUp(connection);

            Client client = new Client(null, "Carolina Andreea", "Str. Fericirii nr 100", true);
            repository.insertData(connection, client);
            System.out.println("Inserted client: " + client);

            Account account = new Account(null, client.getId(), "Student", new BigDecimal("3000.00"), "LEI", true);
            repository.insertAccount(connection, account);
            System.out.println("Inserted account: " + account);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
