package avion.com.models;

import java.util.Date;

public class Avion {
    private int id;
    private String modele;
    private int nbSiegeBusiness;
    private int nbSiegeEco;
    private Date dateFabrication;

    public Avion(int id, String modele, int nbSiegeBusiness, int nbSiegeEco, Date dateFabrication) {
        this.id = id;
        this.modele = modele;
        this.nbSiegeBusiness = nbSiegeBusiness;
        this.nbSiegeEco = nbSiegeEco;
        this.dateFabrication = dateFabrication;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public int getNbSiegeBusiness() {
        return nbSiegeBusiness;
    }

    public void setNbSiegeBusiness(int nbSiegeBusiness) {
        this.nbSiegeBusiness = nbSiegeBusiness;
    }

    public int getNbSiegeEco() {
        return nbSiegeEco;
    }

    public void setNbSiegeEco(int nbSiegeEco) {
        this.nbSiegeEco = nbSiegeEco;
    }

    public Date getDateFabrication() {
        return dateFabrication;
    }

    public void setDateFabrication(Date dateFabrication) {
        this.dateFabrication = dateFabrication;
    }
}

