package app.model.collectible;

import app.model.ID;
import app.pojo.collectible.ModDeclenchementPOJO;

public class ModDeclenchement implements Comparable<ModDeclenchement> {
    private static int id = -1;

    private int idModDeclenchement;
    private String nom;
    private int nbStat;
    private String polarite;
    private String img;

    public ModDeclenchement() {
        id++;
        this.idModDeclenchement = id;
        this.nom = "";
        this.nbStat = 0;
        this.polarite = "None";
        this.img = "";
    }

    public ModDeclenchement(ModDeclenchementPOJO modDeclenchementPOJO) {
        id = ID.updateID(id, modDeclenchementPOJO.id_modDeclenchement);
        this.idModDeclenchement = modDeclenchementPOJO.id_modDeclenchement;
        this.nom = modDeclenchementPOJO.nom;
        this.nbStat = modDeclenchementPOJO.nb_stat;
        this.polarite = modDeclenchementPOJO.polarite;
        this.img = modDeclenchementPOJO.img;
    }

    // SETTERS
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setNbStat(int nbStat) {
        this.nbStat = nbStat;
    }
    public void setPolarite(String polarite) {
        this.polarite = polarite;
    }
    public void setImg(String img) {
        this.img = img;
    }

    // GETTERS
    public int    getIdModDeclenchement()   { return idModDeclenchement; }
    public String getNom() {
        return nom;
    }
    public int getNbStat() {
        return nbStat;
    }
    public String getPolarite() {
        return polarite;
    }
    public String getImg() {
        return img;
    }

    @Override
    public int compareTo(ModDeclenchement autre) {
        return this.nom.compareToIgnoreCase(autre.nom);
    }

}