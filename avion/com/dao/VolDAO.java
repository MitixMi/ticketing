package avion.com.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import avion.com.models.Vol;

public class VolDAO {

    private Connection connection;

    public VolDAO(Connection connection) {
        this.connection = connection;
    }

    public void ajouterVol(Vol vol) throws SQLException {
        String query = "INSERT INTO vol (avion_id, ville_depart_id, ville_arrivee_id, date_depart, date_arrivee, prix) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, vol.getAvionId());
        stmt.setInt(2, vol.getVilleDepartId());
        stmt.setInt(3, vol.getVilleArriveeId());
        stmt.setTimestamp(4, new Timestamp(vol.getDateDepart().getTime()));
        stmt.setTimestamp(5, new Timestamp(vol.getDateArrivee().getTime()));
        stmt.setDouble(6, vol.getPrix());
        stmt.executeUpdate();
    }

    public List<Vol> getAllVols() throws SQLException {
        List<Vol> vols = new ArrayList<>();
        String query = "SELECT * FROM vol";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            vols.add(new Vol(
                    rs.getInt("id"),
                    rs.getInt("avion_id"),
                    rs.getInt("ville_depart_id"),
                    rs.getInt("ville_arrivee_id"),
                    rs.getTimestamp("date_depart"),
                    rs.getTimestamp("date_arrivee"),
                    rs.getDouble("prix")
            ));
        }
        return vols;
    }

    public void modifierVol(Vol vol) throws SQLException {
        String query = "UPDATE vol SET avion_id=?, ville_depart_id=?, ville_arrivee_id=?, date_depart=?, date_arrivee=?, prix=? WHERE id=?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, vol.getAvionId());
        stmt.setInt(2, vol.getVilleDepartId());
        stmt.setInt(3, vol.getVilleArriveeId());
        stmt.setTimestamp(4, new Timestamp(vol.getDateDepart().getTime()));
        stmt.setTimestamp(5, new Timestamp(vol.getDateArrivee().getTime()));
        stmt.setDouble(6, vol.getPrix());
        stmt.setInt(7, vol.getId());
        stmt.executeUpdate();
    }

    public void supprimerVol(int id) throws SQLException {
        String query = "DELETE FROM vol WHERE id=?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    public List<Vol> searchVols(Integer villeDepartId, Integer villeArriveeId, String dateDepart, Double maxPrix) {
        List<Vol> vols = new ArrayList<>();
        try {
            String sql = "SELECT * FROM vol WHERE 1=1";
            if (villeDepartId != null) sql += " AND ville_depart_id = ?";
            if (villeArriveeId != null) sql += " AND ville_arrivee_id = ?";
            if (dateDepart != null && !dateDepart.isEmpty()) sql += " AND DATE(date_depart) = ?";
            if (maxPrix != null) sql += " AND prix <= ?";

            PreparedStatement stmt = connection.prepareStatement(sql);

            int index = 1;
            if (villeDepartId != null) stmt.setInt(index++, villeDepartId);
            if (villeArriveeId != null) stmt.setInt(index++, villeArriveeId);
            if (dateDepart != null && !dateDepart.isEmpty()) stmt.setString(index++, dateDepart);
            if (maxPrix != null) stmt.setDouble(index++, maxPrix);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Vol vol = new Vol(
                        rs.getInt("id"),
                        rs.getInt("avion_id"),
                        rs.getInt("ville_depart_id"),
                        rs.getInt("ville_arrivee_id"),
                        rs.getTimestamp("date_depart"),
                        rs.getTimestamp("date_arrivee"),
                        rs.getDouble("prix")
                );
                vols.add(vol);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vols;
    }
}

