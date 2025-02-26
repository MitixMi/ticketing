package avion.com.dao;

import avion.com.models.Promotion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PromotionDAO {
    private Connection connection;

    public PromotionDAO(Connection connection) {
        this.connection = connection;
    }

    public void ajouterPromotion(int volId, int pourcentageReduction, int nbSiegePromo) throws SQLException {
        String sql = "INSERT INTO promotion (vol_id, pourcentage_reduction, nb_siege_promo) VALUES (?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, volId);
        stmt.setInt(2, pourcentageReduction);
        stmt.setInt(3, nbSiegePromo);
        stmt.executeUpdate();
    }

    public Promotion getPromotionByVol(int volId) throws SQLException {
        String sql = "SELECT * FROM promotion WHERE vol_id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, volId);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new Promotion(rs.getInt("id"), rs.getInt("vol_id"), rs.getInt("pourcentage_reduction"), rs.getInt("nb_siege_promo"));
        }
        return null;
    }

    public List<Promotion> getAllPromotions() throws SQLException {
        List<Promotion> promotions = new ArrayList<>();
        String sql = "SELECT * FROM promotion";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            promotions.add(new Promotion(
                    rs.getInt("id"),
                    rs.getInt("vol_id"),
                    rs.getInt("pourcentage_reduction"),
                    rs.getInt("nb_siege_promo")
            ));
        }
        return promotions;
    }

}

