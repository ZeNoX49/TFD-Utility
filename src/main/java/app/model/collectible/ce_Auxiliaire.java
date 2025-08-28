package app.model.collectible;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ce_Auxiliaire {
    public static final Map<String, String> ATTRIBUT_AUXILIAIRE;
    static {
        ATTRIBUT_AUXILIAIRE = new LinkedHashMap<>();
        
        ATTRIBUT_AUXILIAIRE.put("PV max", "914");

        ATTRIBUT_AUXILIAIRE.put("Récupération de PM hors combat", "2.454");
        ATTRIBUT_AUXILIAIRE.put("Durée hors combat", "0.318");

        ATTRIBUT_AUXILIAIRE.put("Modificateurs du taux d'obtention de modules", "0.191");
        ATTRIBUT_AUXILIAIRE.put("Modificateur du taux d'obtention d'éclats de Kuiper", "0.168");

        ATTRIBUT_AUXILIAIRE.put("Résistance au feu", "3680");
    }

    public static final List<String> ATTRIBUT_AUXILIAIRE_ACTIF = new ArrayList<>();
    static {
        ATTRIBUT_AUXILIAIRE_ACTIF.add("PV max");
        ATTRIBUT_AUXILIAIRE_ACTIF.add("Récupération de PM hors combat");
        // ATTRIBUT_AUXILIAIRE_ACTIF.add("Durée hors combat");
    }

    /* -------------------------------------------------- */

    private String img;
    private String stat;
    private String valStat;

    public ce_Auxiliaire() {
        this.img = "";
        this.stat = "";
        this.valStat = "";
    }

    public ce_Auxiliaire(String img, String stat, String valStat) {
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