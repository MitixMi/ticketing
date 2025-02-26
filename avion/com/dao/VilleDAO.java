package avion.com.dao;

import avion.com.models.Ville;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VilleDAO {

    private Connection connection;

    public VilleDAO(Connection connection) {
        this.connection = connection;
    }

    public void ajouterVille(Ville ville) throws SQLException {
        String query = "INSERT INTO ville (nom) VALUES (?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, ville.getNom());
        stmt.executeUpdate();
    }

    public List<Ville> getAllVilles() throws SQLException {
        List<Ville> villes = new ArrayList<>();
        String query = "SELECT * FROM ville";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            villes.add(new Ville(
                    rs.getInt("id"),
                    rs.getString("nom")
            ));
        }
        return villes;
    }

    public void modifierVille(Ville ville) throws SQLException {
        String query = "UPDATE ville SET nom=? WHERE id=?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, ville.getNom());
        stmt.setInt(2, ville.getId());
        stmt.executeUpdate();
    }

    public void supprimerVille(int id) throws SQLException {
        String query = "DELETE FROM ville WHERE id=?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
}

