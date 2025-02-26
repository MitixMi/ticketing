package avion.com.dao;

import avion.com.models.Avion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AvionDAO {

    private Connection connection;

    public AvionDAO(Connection connection) {
        this.connection = connection;
    }

    public void ajouterAvion(Avion avion) throws SQLException {
        String query = "INSERT INTO avion (modele, nb_siege_business, nb_siege_eco, date_fabrication) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, avion.getModele());
        stmt.setInt(2, avion.getNbSiegeBusiness());
        stmt.setInt(3, avion.getNbSiegeEco());
        stmt.setDate(4, new Date(avion.getDateFabrication().getTime()));
        stmt.executeUpdate();
    }

    public List<Avion> getAllAvions() throws SQLException {
        List<Avion> avions = new ArrayList<>();
        String query = "SELECT * FROM avion";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            avions.add(new Avion(
                    rs.getInt("id"),
                    rs.getString("modele"),
                    rs.getInt("nb_siege_business"),
                    rs.getInt("nb_siege_eco"),
                    rs.getDate("date_fabrication")
            ));
        }
        return avions;
    }

    public void modifierAvion(Avion avion) throws SQLException {
        String query = "UPDATE avion SET modele=?, nb_siege_business=?, nb_siege_eco=?, date_fabrication=? WHERE id=?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, avion.getModele());
        stmt.setInt(2, avion.getNbSiegeBusiness());
        stmt.setInt(3, avion.getNbSiegeEco());
        stmt.setDate(4, new Date(avion.getDateFabrication().getTime()));
        stmt.setInt(5, avion.getId());
        stmt.executeUpdate();
    }

    public void supprimerAvion(int id) throws SQLException {
        String query = "DELETE FROM avion WHERE id=?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
}

