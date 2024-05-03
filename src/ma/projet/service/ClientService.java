package ma.projet.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ma.projet.beans.Client;
import ma.projet.connexion.Connexion;
import ma.projet.dao.IDao;

public class ClientService implements IDao<Client> {

    @Override
    public boolean create(Client client) {
        String sql = "INSERT INTO client (nom, prenom) VALUES (?, ?)";
        try (Connection connection = Connexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, client.getNom());
            statement.setString(2, client.getPrenom());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la création du client : " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Client client) {
        String sql = "DELETE FROM client WHERE id = ?";
        try (Connection connection = Connexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, client.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression du client : " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Client client) {
        String sql = "UPDATE client SET nom = ?, prenom = ? WHERE id = ?";
        try (Connection connection = Connexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, client.getNom());
            statement.setString(2, client.getPrenom());
            statement.setInt(3, client.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour du client : " + e.getMessage());
            return false;
        }
    }

    @Override
    public Client findById(int id) {
        String sql = "SELECT * FROM client WHERE id = ?";
        try (Connection connection = Connexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Client(resultSet.getInt("id"),
                            resultSet.getString("nom"),
                            resultSet.getString("prenom"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche du client par ID : " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM client";
        try (Connection connection = Connexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                clients.add(new Client(resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom")));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de la liste des clients : " + e.getMessage());
        }
        return clients;
    }
}
