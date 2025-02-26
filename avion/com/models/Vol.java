package avion.com.models;

import java.util.Date;

public class Vol {
    private int id;
    private int avionId;
    private int villeDepartId;
    private int villeArriveeId;
    private Date dateDepart;
    private Date dateArrivee;
    private double prix;
    private String villeDepartNom; // Nom de la ville de départ
    private String villeArriveeNom; // Nom de la ville d'arrivée

    public Vol(int id, int avionId, int villeDepartId, int villeArriveeId, Date dateDepart, Date dateArrivee, double prix) {
        this.id = id;
        this.avionId = avionId;
        this.villeDepartId = villeDepartId;
        this.villeArriveeId = villeArriveeId;
        this.dateDepart = dateDepart;
        this.dateArrivee = dateArrivee;
        this.prix = prix;
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getAvionId() {
        return avionId;
    }

    public int getVilleDepartId() {
        return villeDepartId;
    }

    public int getVilleArriveeId() {
        return villeArriveeId;
    }

    public Date getDateDepart() {
        return dateDepart;
    }

    public Date getDateArrivee() {
        return dateArrivee;
    }

    public double getPrix() {
        return prix;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setAvionId(int avionId) {
        this.avionId = avionId;
    }

    public void setVilleDepartId(int villeDepartId) {
        this.villeDepartId = villeDepartId;
    }

    public void setVilleArriveeId(int villeArriveeId) {
        this.villeArriveeId = villeArriveeId;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    public void setDateArrivee(Date dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    // Getters et setters pour les nouveaux champs
    public String getVilleDepartNom() {
        return villeDepartNom;
    }

    public void setVilleDepartNom(String villeDepartNom) {
        this.villeDepartNom = villeDepartNom;
    }

    public String getVilleArriveeNom() {
        return villeArriveeNom;
    }

    public void setVilleArriveeNom(String villeArriveeNom) {
        this.villeArriveeNom = villeArriveeNom;
    }
}
