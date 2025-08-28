package app.model.collectible;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ce_Memoire {
    public static final Map<String, String> ATTRIBUT_MEMOIRE;
    static {
        ATTRIBUT_MEMOIRE = new LinkedHashMap<>();
        
        ATTRIBUT_MEMOIRE.put("DEF", "4249");

        ATTRIBUT_MEMOIRE.put("Modificateur de récupération des PM", "0.16");
        ATTRIBUT_MEMOIRE.put("Récupération de bouclier en combat", "5.033");

        ATTRIBUT_MEMOIRE.put("Modificateur du rayon de l'Opsid", "0.766");

        ATTRIBUT_MEMOIRE.put("Modificateur d'aptitudes aux armes gagnées", "0.191");
        ATTRIBUT_MEMOIRE.put("Modificateur du rayon d'obtention d'or", "0.191");

        ATTRIBUT_MEMOIRE.put("Résistance électrique", "3680");
    }

    public static final List<String> ATTRIBUT_MEMOIRE_ACTIF = new ArrayList<>();
    static {
        ATTRIBUT_MEMOIRE_ACTIF.add("DEF");
        ATTRIBUT_MEMOIRE_ACTIF.add("Modificateur de récupération des PM");
    }

    /* -------------------------------------------------- */

    private String img;
    private String stat;
    private String valStat;

    public ce_Memoire() {
        this.img = "";
        this.stat = "";
        this.valStat = "";
    }

    public ce_Memoire(String img, String stat, String valStat) {
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
