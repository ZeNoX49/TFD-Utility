package app.model.collectible;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ce_Detecteur {
    public static final Map<String, String> ATTRIBUT_DETECTEUR;
    static {
        ATTRIBUT_DETECTEUR = new LinkedHashMap<>();
        
        ATTRIBUT_DETECTEUR.put("PM max", "159");

        ATTRIBUT_DETECTEUR.put("Récupération de PM en combat", "0.287");
        ATTRIBUT_DETECTEUR.put("Modificateur de récupération des PV", "0.085");

        ATTRIBUT_DETECTEUR.put("Modificateur du taux d'obtention de consommables", "0.191");
        ATTRIBUT_DETECTEUR.put("Modificateur d'EXP gagnée par le personnage", "0.191");

        ATTRIBUT_DETECTEUR.put("Résistance au froid", "3680");
    }

    public static final List<String> ATTRIBUT_DETECTEUR_ACTIF = new ArrayList<>();
    static {
        ATTRIBUT_DETECTEUR_ACTIF.add("PM max");
        ATTRIBUT_DETECTEUR_ACTIF.add("Récupération de PM en combat");
        ATTRIBUT_DETECTEUR_ACTIF.add("Modificateur du taux d'obtention de consommables");
    }

    /* -------------------------------------------------- */

    private String img;
    private String stat;
    private String valStat;

    public ce_Detecteur() {
        this.img = "";
        this.stat = "";
        this.valStat = "";
    }

    public ce_Detecteur(String img, String stat, String valStat) {
        this.img = img;
        this.stat = stat;
        this.valStat = valStat;
    }

    public void setImg(String img) { this.img = img; }
    public void setStat(String stat) { this.stat = stat; }
    public void setValStat(String valStat) { this.valStat = valStat; }

    public String getImg() { return img; }
    public String getStat() { return stat; }
    public String getValStat() { return valStat; }
}
