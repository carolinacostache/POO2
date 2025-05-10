package service;

import config.ConnectionProvider;
import exception.ClientNotFoundException;
import model.Client;
import repository.BankRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class BankService {

    private final BankRepository bankRepository = BankRepository.getInstance();

    public BankService() {}

    public void insertData(Client client) {
        try (Connection connection = ConnectionProvider.getConnection()) {
            bankRepository.insertData(connection, client);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Client getClient(long id) {
        try (Connection connection = ConnectionProvider.getConnection()) {
            Optional<Client> result = bankRepository.getClient(connection, id);
            return result.orElseThrow(ClientNotFoundException::new);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateClient(Client client) {
        try (Connection connection = ConnectionProvider.getConnection()) {
            bankRepository.updateClient(connection, client);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteClient(long id) {
        try (Connection connection = ConnectionProvider.getConnection()) {
            bankRepository.deleteClient(connection, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cleanUp() {
        try (Connection connection = ConnectionProvider.getConnection()) {
            bankRepository.cleanUp(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
