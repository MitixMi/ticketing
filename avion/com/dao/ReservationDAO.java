package avion.com.dao;

import avion.com.models.Promotion;
import avion.com.models.Reservation;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {
    private Connection connection;
    private PromotionDAO promotionDAO;

    public ReservationDAO(Connection connection, PromotionDAO promotionDAO) {
        this.connection = connection;
        this.promotionDAO = promotionDAO;
    }

    // 🔹 Ajouter une réservation
    public void reserverVol(int volId, int utilisateurId, String typeSiege) throws SQLException {
        Promotion promo = promotionDAO.getPromotionByVol(volId);
        double prixFinal = 0;

        String getPrixSQL = "SELECT prix FROM vol WHERE id = ?";
        PreparedStatement prixStmt = connection.prepareStatement(getPrixSQL);
        prixStmt.setInt(1, volId);
        ResultSet rs = prixStmt.executeQuery();

        if (rs.next()) {
            prixFinal = rs.getDouble("prix");
        }

        if (promo != null && promo.getNbSiegePromo() > 0) {
            prixFinal -= prixFinal * (promo.getPourcentageReduction() / 100.0);
            promo.setNbSiegePromo(promo.getNbSiegePromo() - 1);

            // Mettre à jour le nombre de sièges promo dans la base de données
            String updatePromoSQL = "UPDATE promotion SET nb_siege_promo = ? WHERE id = ?";
            PreparedStatement updatePromoStmt = connection.prepareStatement(updatePromoSQL);
            updatePromoStmt.setInt(1, promo.getNbSiegePromo());
            updatePromoStmt.setInt(2, promo.getId());
            updatePromoStmt.executeUpdate();
        }

        String sql = "INSERT INTO reservation (vol_id, utilisateur_id, type_siege, prix_final) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, volId);
        stmt.setInt(2, utilisateurId);
        stmt.setString(3, typeSiege);
        stmt.setDouble(4, prixFinal);
        stmt.executeUpdate();
    }



    // 🔹 Récupérer les réservations d'un utilisateur
    public List<Reservation> getReservationsByUser(int utilisateurId) throws SQLException {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservation WHERE utilisateur_id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, utilisateurId);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            reservations.add(new Reservation(
                    rs.getInt("id"),
                    rs.getInt("vol_id"),
                    rs.getInt("utilisateur_id"),
                    rs.getString("type_siege"),
                    rs.getTimestamp("date_reservation"),
                    rs.getString("statut")
            ));
        }
        return reservations;
    }

    // 🔹 Annuler une réservation
    public void annulerReservation(int reservationId) throws SQLException {
        String sql = "UPDATE reservation SET statut = 'annulée' WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, reservationId);
        stmt.executeUpdate();
    }

    public boolean peutReserver(int volId) throws SQLException {
        String sql = "SELECT date_depart FROM vol WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, volId);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            Timestamp dateDepart = rs.getTimestamp("date_depart");
            long heuresRestantes = (dateDepart.getTime() - System.currentTimeMillis()) / (1000 * 60 * 60);
            return heuresRestantes > 3; // ✅ On interdit la réservation si le vol part dans moins de 3 heures.
        }
        return false;
    }

    public boolean peutAnnuler(int reservationId) throws SQLException {
        String sql = "SELECT v.date_depart FROM reservation r JOIN vol v ON r.vol_id = v.id WHERE r.id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, reservationId);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            Timestamp dateDepart = rs.getTimestamp("date_depart");
            long heuresRestantes = (dateDepart.getTime() - System.currentTimeMillis()) / (1000 * 60 * 60);
            return heuresRestantes > 6; // ✅ On interdit l’annulation si le vol part dans moins de 6 heures.
        }
        return false;
    }

    // 🔹 Récupérer les réservations d'un utilisateur
    public List<Reservation> getReservationsByUserId(int utilisateurId) throws SQLException {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservation WHERE utilisateur_id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, utilisateurId);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            reservations.add(new Reservation(
                    rs.getInt("id"),
                    rs.getInt("vol_id"),
                    rs.getInt("utilisateur_id"),
                    rs.getString("type_siege"),
                    rs.getTimestamp("date_reservation"),
                    rs.getString("statut"),
                    rs.getDouble("prix_final")  // Ajout du champ prix_final
            ));
        }
        return reservations;
    }


}
