package app.model.collectible;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import app.model.ID;
import app.pojo.collectible.ComposantExternePOJO;

public class ComposantExterne implements Comparable<ComposantExterne> {
    public static final Map<String, List<String>> ATTRIBUT;
    static {
        ATTRIBUT = new HashMap<>();
        
        ATTRIBUT.put("PV max", new ArrayList<>());
        ATTRIBUT.get("PV max").add("484");
        ATTRIBUT.get("PV max").add("646");

        ATTRIBUT.put("DEF", new ArrayList<>());
        ATTRIBUT.get("DEF").add("3512");
        ATTRIBUT.get("DEF").add("4683");

        ATTRIBUT.put("Bouclier max", new ArrayList<>());
        ATTRIBUT.get("Bouclier max").add("206");
        ATTRIBUT.get("Bouclier max").add("275");
    }

    // public static final Map<String, String> AMELIORATION_IMG;
    // static {
    //     AMELIORATION_IMG = new LinkedHashMap<>();
    //     AMELIORATION_IMG.put("amélioration de la récupération", Main.class.getResource("/img/amelioration/composantExterne/malachite.png").toExternalForm());
    //     AMELIORATION_IMG.put("amélioration de la défense",      Main.class.getResource("/img/amelioration/composantExterne/xantique.png").toExternalForm());
    // }

    public static final Map<String, Map<String, String>> AMELIORATION;
    static {
        AMELIORATION = new LinkedHashMap<>();

        // amélioration de la récupération
        AMELIORATION.put("amélioration de la récupération", new LinkedHashMap<>());
        AMELIORATION.get("amélioration de la récupération").put("PV max", "37.5%");
        AMELIORATION.get("amélioration de la récupération").put("Modificateur de récupération de PV", "0.058");
        AMELIORATION.get("amélioration de la récupération").put("Bouclier max", "28%");
        AMELIORATION.get("amélioration de la récupération").put("Récupération de bouclier hors combat", "12.48");
        AMELIORATION.get("amélioration de la récupération").put("Récupération de bouclier en combat", "3.75");

        // amélioration de la défense
        AMELIORATION.put("amélioration de la défense", new LinkedHashMap<>());
        AMELIORATION.get("amélioration de la défense").put("DEF", "7500");
        AMELIORATION.get("amélioration de la défense").put("Résistance au feu", "75%");
        AMELIORATION.get("amélioration de la défense").put("Résistance au froid", "75%");
        AMELIORATION.get("amélioration de la défense").put("Résistance électrique", "75%");
        AMELIORATION.get("amélioration de la défense").put("Résistance aux toxines", "75%");
    }

    /* -------------------------------------------------- */

    private static int id = -1;

    private int idComposantExterne;
    private String nom;
    private ce_Auxiliaire auxiliaire;
    private ce_Detecteur detecteur;
    private ce_Memoire memoire;
    private ce_Processeur processeur;

    public ComposantExterne() {
        id++;
        this.idComposantExterne = id;
        this.nom = "";
        this.auxiliaire = new ce_Auxiliaire();
        this.detecteur = new ce_Detecteur();
        this.memoire = new ce_Memoire();
        this.processeur = new ce_Processeur();
    }

    public ComposantExterne(ComposantExternePOJO composantExternePOJO) {
        id = ID.updateID(id, composantExternePOJO.id_composantExterne);
        this.idComposantExterne = composantExternePOJO.id_composantExterne;
        this.nom = composantExternePOJO.nom;
        this.auxiliaire = new ce_Auxiliaire(
            composantExternePOJO.img_auxiliaire,
            composantExternePOJO.stat_auxiliaire,
            composantExternePOJO.val_stat_auxiliaire
        );
        this.detecteur = new ce_Detecteur(
            composantExternePOJO.img_detecteur,
            composantExternePOJO.stat_detecteur,
            composantExternePOJO.val_stat_detecteur
        );
        this.memoire = new ce_Memoire(
            composantExternePOJO.img_memoire,
            composantExternePOJO.stat_memoire,
            composantExternePOJO.val_stat_memoire
        );
        this.processeur = new ce_Processeur(
            composantExternePOJO.img_processeur,
            composantExternePOJO.stat_processeur,
            composantExternePOJO.val_stat_processeur
        );
    }

    // SETTER
    public void setNom(String nom)                              { this.nom = nom; }
    public void setImgAuxiliaire(String imgAuxiliaire)          { this.auxiliaire.setImg(imgAuxiliaire); }
    public void setStatAuxiliaire(String statAuxiliaire)        { this.auxiliaire.setStat(statAuxiliaire); }
    public void setValStatAuxiliaire(String valStatAuxiliaire)  { this.auxiliaire.setValStat(valStatAuxiliaire); }
    public void setImgDetecteur(String imgDetecteur)            { this.detecteur.setImg(imgDetecteur); }
    public void setStatDetecteur(String statDetecteur)          { this.detecteur.setStat(statDetecteur); }
    public void setValStatDetecteur(String valStatDetecteur)    { this.detecteur.setValStat(valStatDetecteur); }
    public void setImgMemoire(String imgMemoire)                { this.memoire.setImg(imgMemoire); }
    public void setStatMemoire(String statMemoire)              { this.memoire.setStat(statMemoire); }
    public void setValStatMemoire(String valStatMemoire)        { this.memoire.setValStat(valStatMemoire); }
    public void setImgProcesseur(String imgProcesseur)          { this.processeur.setImg(imgProcesseur); }
    public void setStatProcesseur(String statProcesseur)        { this.processeur.setStat(statProcesseur); }
    public void setValStatProcesseur(String valStatProcesseur)  { this.processeur.setValStat(valStatProcesseur); }
    
    // GETTER
    public int    getIdComposantExterne()   { return idComposantExterne; }
    public String getNom()                  { return nom; }
    public ce_Auxiliaire getAuxiliaire()    { return auxiliaire; }
    public String getImgAuxiliaire()        { return auxiliaire.getImg(); }
    public String getStatAuxiliaire()       { return auxiliaire.getStat(); }
    public String getValStatAuxiliaire()    { return auxiliaire.getValStat(); }
    public ce_Detecteur getDetecteur()      { return detecteur; }
    public String getImgDetecteur()         { return detecteur.getImg(); }
    public String getStatDetecteur()        { return detecteur.getStat(); }
    public String getValStatDetecteur()     { return detecteur.getValStat(); }
    public ce_Memoire getMemoire()          { return memoire; }
    public String getImgMemoire()           { return memoire.getImg(); }
    public String getStatMemoire()          { return memoire.getStat(); }
    public String getValStatMemoire()       { return memoire.getValStat(); }
    public ce_Processeur getProcesseur()    { return processeur; }
    public String getImgProcesseur()        { return processeur.getImg(); }
    public String getStatProcesseur()       { return processeur.getStat(); }
    public String getValStatProcesseur()    { return processeur.getValStat(); }

    @Override
    public int compareTo(ComposantExterne autre) {
        return this.nom.compareToIgnoreCase(autre.nom);
    }
}