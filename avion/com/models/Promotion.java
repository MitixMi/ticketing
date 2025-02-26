package avion.com.models;

public class Promotion {
    private int id;
    private int volId;
    private int pourcentageReduction;
    private int nbSiegePromo;

    public Promotion(int id, int volId, int pourcentageReduction, int nbSiegePromo) {
        this.id = id;
        this.volId = volId;
        this.pourcentageReduction = pourcentageReduction;
        this.nbSiegePromo = nbSiegePromo;
    }

    public int getId() { return id; }
    public int getVolId() { return volId; }
    public int getPourcentageReduction() { return pourcentageReduction; }
    public int getNbSiegePromo() { return nbSiegePromo; }

    public void setId(int id) { this.id = id; }
    public void setVolId(int volId) { this.volId = volId; }
    public void setPourcentageReduction(int pourcentageReduction) { this.pourcentageReduction = pourcentageReduction; }
    public void setNbSiegePromo(int nbSiegePromo) { this.nbSiegePromo = nbSiegePromo; }
}

