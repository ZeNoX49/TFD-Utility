package app.model.collectible;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ce_Processeur {
    public static final Map<String, String> ATTRIBUT_PROCESSEUR;
    static {
        ATTRIBUT_PROCESSEUR = new LinkedHashMap<>();
        
        ATTRIBUT_PROCESSEUR.put("Bouclier max", "283");

        ATTRIBUT_PROCESSEUR.put("Modificateur de récupération du bouclier", "0.085");

        ATTRIBUT_PROCESSEUR.put("Durée d'affichage de l'Opsid", "0.972");

        ATTRIBUT_PROCESSEUR.put("Modificateur du taux d'obtention d'équipement", "0.191");
        ATTRIBUT_PROCESSEUR.put("Modificateur du rayon d'obtention des objets", "0.191");

        ATTRIBUT_PROCESSEUR.put("Résistance aux toxines", "3680");
    }

    public static final List<String> ATTRIBUT_PROCESSEUR_ACTIF = new ArrayList<>();
    static {
        ATTRIBUT_PROCESSEUR_ACTIF.add("Bouclier max");
        // ATTRIBUT_PROCESSEUR_ACTIF.add("Modificateur de récupération du bouclier");
        ATTRIBUT_PROCESSEUR_ACTIF.add("Modificateur du taux d'obtention d'équipement");
    }

    /* -------------------------------------------------- */

    private String img;
    private String stat;
    private String valStat;

    public ce_Processeur() {
        this.img = "";
        this.stat = "";
        this.valStat = "";
    }

    public ce_Processeur(String img, String stat, String valStat) {
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
