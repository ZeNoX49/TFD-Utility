package app.model.collectible;

import app.model.ID;
import app.pojo.collectible.ModArcheoPOJO;

public class ModArcheo implements Comparable<ModArcheo> {
    private static int id = -1;

    private int idModArcheo;
    private String nom;
    private String polarite;
    private String img;

    public ModArcheo() {
        id++;
        this.idModArcheo = id;
        this.nom = "";
        this.polarite = "None";
        this.img = "";
    }

    public ModArcheo(ModArcheoPOJO modArcheoPOJO) {
        id = ID.updateID(id, modArcheoPOJO.id_modArcheo);
        this.idModArcheo = modArcheoPOJO.id_modArcheo;
        this.nom = modArcheoPOJO.nom;
        this.polarite = modArcheoPOJO.polarite;
        this.img = modArcheoPOJO.img;
    }

    // SETTERS
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setPolarite(String polarite) {
        this.polarite = polarite;
    }
    public void setImg(String img) {
        this.img = img;
    }

    // GETTERS
    public int    getIdModArcheo()   { return idModArcheo; }
    public String getNom() {
        return nom;
    }
    public String getPolarite() {
        return polarite;
    }
    public String getImg() {
        return img;
    }

    @Override
    public int compareTo(ModArcheo autre) {
        return this.nom.compareToIgnoreCase(autre.nom);
    }
}