package avion.com.models;

import java.sql.Timestamp;

public class Reservation {
    private int id;
    private int volId;
    private int utilisateurId;
    private String typeSiege;
    private Timestamp dateReservation;
    private String statut;
    private double prixFinal; // Ajout de ce champ

    public Reservation(int id, int volId, int utilisateurId, String typeSiege, Timestamp dateReservation, String statut) {
        this.id = id;
        this.volId = volId;
        this.utilisateurId = utilisateurId;
        this.typeSiege = typeSiege;
        this.dateReservation = dateReservation;
        this.statut = statut;
    }

    public Reservation(int id, int volId, int utilisateurId, String typeSiege, Timestamp dateReservation, String statut, double prixFinal) {
        this.id = id;
        this.volId = volId;
        this.utilisateurId = utilisateurId;
        this.typeSiege = typeSiege;
        this.dateReservation = dateReservation;
        this.statut = statut;
        this.prixFinal = prixFinal;
    }

    // Getters et Setters
    public int getId() { return id; }
    public int getVolId() { return volId; }
    public int getUtilisateurId() { return utilisateurId; }
    public String getTypeSiege() { return typeSiege; }
    public Timestamp getDateReservation() { return dateReservation; }
    public String getStatut() { return statut; }
    public double getPrixFinal() { return prixFinal; } // Ajout de ce getter

    public void setId(int id) { this.id = id; }
    public void setVolId(int volId) { this.volId = volId; }
    public void setUtilisateurId(int utilisateurId) { this.utilisateurId = utilisateurId; }
    public void setTypeSiege(String typeSiege) { this.typeSiege = typeSiege; }
    public void setDateReservation(Timestamp dateReservation) { this.dateReservation = dateReservation; }
    public void setStatut(String statut) { this.statut = statut; }
    public void setPrixFinal(double prixFinal) { this.prixFinal = prixFinal; } // Ajout de ce setter
}
