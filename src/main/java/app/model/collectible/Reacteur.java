package app.model.collectible;

import java.util.LinkedHashMap;
import java.util.Map;

import app.model.ID;
import app.pojo.collectible.ReacteurPOJO;

public class Reacteur implements Comparable<Reacteur> {

    public static final Map<String, String> ATTRIBUT;   // nom / valeur max
    static {
        ATTRIBUT = new LinkedHashMap<>();
        ATTRIBUT.put("Coût des compétences", "-0.041");
        ATTRIBUT.put("Délai de réutilisation des compétences", "-0.074");
        ATTRIBUT.put("Durée des compétences augmentées", "0.106");
        ATTRIBUT.put("Portée des compétences", "0.258");
        ATTRIBUT.put("Taux de coup critique des compétences", "33%");
        ATTRIBUT.put("Dégat critiques des compétences", "33%");
        ATTRIBUT.put("Boost de puissances des compétences neutres", "0.085");
        ATTRIBUT.put("Boost de puissances des compétences de feu", "0.085");
        ATTRIBUT.put("Boost de puissances des compétences de froid", "0.085");
        ATTRIBUT.put("Boost de puissances des compétences électriques", "0.085");
        ATTRIBUT.put("Boost de puissances des compétences toxiques", "0.085");
        ATTRIBUT.put("Boost de puissances des compétences de fusion", "0.085");
        ATTRIBUT.put("Boost de puissances des compétences de type Singulier", "0.085");
        ATTRIBUT.put("Boost de puissances des compétences dimensionelles", "0.085");
        ATTRIBUT.put("Boost de puissances des compétences de type Tech", "0.085");
        ATTRIBUT.put("Modificateurs de soins", "0.085");
        ATTRIBUT.put("Puissance des attaques secondaires", "19.1%");
        ATTRIBUT.put("Bonus d'ATQ des compétences (Colosses)", "2633.561");
        ATTRIBUT.put("Bonus d'ATQ des compétences (Légion des ténèbres)", "2633.561");
        ATTRIBUT.put("Bonus d'ATQ des compétences (Ordre de la vérité)", "2633.561");
        ATTRIBUT.put("Bonus d'ATQ des compétences (Légion de l'immortalité)", "2633.561");
    }

    /* -------------------------------------------------- */

    private static int id = -1;
    
    private int idReacteur;
    private String nom;
    private String img;

    public Reacteur() {
        id++;
        this.idReacteur = id;
        this.nom = "";
        this.img = "";
    }

    public Reacteur(ReacteurPOJO reacteurPOJO) {
        id = ID.updateID(id, reacteurPOJO.id_reacteur);
        this.idReacteur = reacteurPOJO.id_reacteur;
        this.nom = reacteurPOJO.nom;
        this.img = reacteurPOJO.img;
    }

    public void setNom(String nom) { this.nom = nom; }
    public void setImg(String img) { this.img = img; }

    public int    getIdReacteur()   { return idReacteur; }
    public String getNom()          { return nom; }
    public String getImg()          { return img; }

    @Override
    public int compareTo(Reacteur autre) {
        return this.nom.compareToIgnoreCase(autre.nom);
    }
}