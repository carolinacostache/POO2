package repository;

import model.Client;
import model.Account;
import java.sql.*;
import java.util.Optional;

public class BankRepository {
    private static BankRepository instance;

    private BankRepository() {}

    public static BankRepository getInstance() {
        if (instance == null) {
            instance = new BankRepository();
        }
        return instance;
    }

    public void insertData(Connection connection, Client client) {
        String sql = """
        INSERT INTO bank_clients (name, address, active_status)
        VALUES (?, ?, ?)
    """;

        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, client.getName());
            ps.setString(2, client.getAddress());
            ps.setBoolean(3, client.isActiveStatus());

            int insertedRows = ps.executeUpdate();
            if (insertedRows > 0) {
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) {
                        long generatedId = keys.getLong(1);
                        client.setId(generatedId);
                        System.out.println("Client " + generatedId + " added to the bank's database!");
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Optional<Client> getClient(Connection connection, long id) {
        String sql = """
            SELECT *
            FROM bank_clients bc
            WHERE bc.id = ?
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    String address = rs.getString("address");
                    boolean activeStatus = rs.getBoolean("active_status");

                    return Optional.of(new Client(id, name, address, activeStatus));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    public void updateClient(Connection connection, Client client) {
        String sql = """
            UPDATE bank_clients
            SET name = ?, address = ?, active_status = ?
            WHERE id = ?
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, client.getName());
            ps.setString(2, client.getAddress());
            ps.setBoolean(3, client.isActiveStatus());
            ps.setLong(4, client.getId());

            int updatedRows = ps.executeUpdate();
            System.out.println(updatedRows + " rows updated.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteClient(Connection connection, long id) {
        String sql = """
            DELETE FROM bank_clients
            WHERE id = ?
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            int deletedRows = ps.executeUpdate();
            System.out.println(deletedRows + " rows deleted.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cleanUp(Connection connection) {
        String sqlAccounts = "TRUNCATE TABLE bank_accounts CASCADE";
        String sqlClients = "TRUNCATE TABLE bank_clients CASCADE";

        try (PreparedStatement ps1 = connection.prepareStatement(sqlAccounts);
             PreparedStatement ps2 = connection.prepareStatement(sqlClients)) {

            ps1.executeUpdate();
            ps2.executeUpdate();

            System.out.println("All data deleted from bank_accounts and bank_clients.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public void insertAccount(Connection connection, Account account) {
        String sql = """
        INSERT INTO bank_accounts (client_id, account_type, balance, currency, active)
        VALUES (?, ?, ?, ?, ?)
    """;

        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, account.getClientId());
            ps.setString(2, account.getAccountType());
            ps.setBigDecimal(3, account.getBalance());
            ps.setString(4, account.getCurrency());
            ps.setBoolean(5, account.isActive());

            int insertedRows = ps.executeUpdate();
            if (insertedRows > 0) {
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) {
                        long generatedId = keys.getLong(1);
                        account.setId(generatedId);
                        System.out.println("Account inserted with ID: " + generatedId);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    // GET bank_account by ID
    public Optional<Account> getAccount(Connection connection, long id) {
        String sql = "SELECT * FROM bank_accounts WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Account(
                            rs.getLong("id"),
                            rs.getLong("client_id"),
                            rs.getString("account_type"),
                            rs.getBigDecimal("balance"),
                            rs.getString("currency"),
                            rs.getBoolean("active")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    public void updateAccount(Connection connection, Account account) {
        String sql = """
        UPDATE bank_accounts
        SET client_id = ?, account_type = ?, balance = ?, currency = ?, active = ?
        WHERE id = ?
    """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, account.getClientId());
            ps.setString(2, account.getAccountType());
            ps.setBigDecimal(3, account.getBalance());
            ps.setString(4, account.getCurrency());
            ps.setBoolean(5, account.isActive());
            ps.setLong(6, account.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void deleteAccount(Connection connection, long id) {
        String sql = "DELETE FROM bank_accounts WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
