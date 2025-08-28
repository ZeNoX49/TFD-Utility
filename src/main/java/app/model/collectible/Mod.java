package app.model.collectible;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import app.Main;
import app.model.ID;
import app.pojo.collectible.ModPOJO;

public class Mod implements Comparable<Mod> {
    public static final Map<String, Map<String, String>> POLARITE;
    static {
        POLARITE = new HashMap<>();
        
        POLARITE.put("noir", new LinkedHashMap<>());
        POLARITE.get("noir").put("None",       Main.class.getResource("/img/polarite/noir/none.png").toExternalForm());
        POLARITE.get("noir").put("Céruléen",   Main.class.getResource("/img/polarite/noir/ceruleen.png").toExternalForm());
        POLARITE.get("noir").put("Almadin",    Main.class.getResource("/img/polarite/noir/almadin.png").toExternalForm());
        POLARITE.get("noir").put("Malachite",  Main.class.getResource("/img/polarite/noir/malachite.png").toExternalForm());
        POLARITE.get("noir").put("Xantique",   Main.class.getResource("/img/polarite/noir/xantique.png").toExternalForm());
        POLARITE.get("noir").put("Rutile",     Main.class.getResource("/img/polarite/noir/rutile.png").toExternalForm());

        POLARITE.put("blanc", new LinkedHashMap<>());
        POLARITE.get("blanc").put("None",      Main.class.getResource("/img/polarite/blanc/none.png").toExternalForm());
        POLARITE.get("blanc").put("Céruléen",  Main.class.getResource("/img/polarite/blanc/ceruleen.png").toExternalForm());
        POLARITE.get("blanc").put("Almadin",   Main.class.getResource("/img/polarite/blanc/almadin.png").toExternalForm());
        POLARITE.get("blanc").put("Malachite", Main.class.getResource("/img/polarite/blanc/malachite.png").toExternalForm());
        POLARITE.get("blanc").put("Xantique",  Main.class.getResource("/img/polarite/blanc/xantique.png").toExternalForm());
        POLARITE.get("blanc").put("Rutile",    Main.class.getResource("/img/polarite/blanc/rutile.png").toExternalForm());

        POLARITE.put("vert", new LinkedHashMap<>());
        POLARITE.get("vert").put("None",       Main.class.getResource("/img/polarite/vert/none.png").toExternalForm());
        POLARITE.get("vert").put("Céruléen",   Main.class.getResource("/img/polarite/vert/ceruleen.png").toExternalForm());
        POLARITE.get("vert").put("Almadin",    Main.class.getResource("/img/polarite/vert/almadin.png").toExternalForm());
        POLARITE.get("vert").put("Malachite",  Main.class.getResource("/img/polarite/vert/malachite.png").toExternalForm());
        POLARITE.get("vert").put("Xantique",   Main.class.getResource("/img/polarite/vert/xantique.png").toExternalForm());
        POLARITE.get("vert").put("Rutile",     Main.class.getResource("/img/polarite/vert/rutile.png").toExternalForm());

        
    }

    public static final List<String> TYPE_MOD;
    static {
        TYPE_MOD = new ArrayList<>();
        TYPE_MOD.add("Mod Descendant");
        TYPE_MOD.add("Mod Armes standard");
        TYPE_MOD.add("Mod Armes spéciales");
        TYPE_MOD.add("Mod Armes percutantes");
        TYPE_MOD.add("Mod Armes lourdes");
    }

    public static final Map<String, String> TYPE_MOD_POUR_ARME;
    static {
        TYPE_MOD_POUR_ARME = new HashMap<>();

        // Mod Armes standard
        TYPE_MOD_POUR_ARME.put("Fusil d'assaut", "Mod Armes standard");
        TYPE_MOD_POUR_ARME.put("Pistolet", "Mod Armes standard");
        TYPE_MOD_POUR_ARME.put("Pistolet-mitrailleur", "Mod Armes standard");
        TYPE_MOD_POUR_ARME.put("Mitrailleuse", "Mod Armes standard");

        // Mod Armes spéciales
        TYPE_MOD_POUR_ARME.put("Fusil tactique", "Mod Armes spéciales");
        TYPE_MOD_POUR_ARME.put("Fusil à rayons", "Mod Armes spéciales");

        // Mod Armes percutantes
        TYPE_MOD_POUR_ARME.put("Revolver", "Mod Armes percutantes");
        TYPE_MOD_POUR_ARME.put("Fusil éclaireur", "Mod Armes percutantes");

        // Mod Armes lourdes
        TYPE_MOD_POUR_ARME.put("Fusil de chasse", "Mod Armes lourdes");
        TYPE_MOD_POUR_ARME.put("Fusil de précision", "Mod Armes lourdes");
        TYPE_MOD_POUR_ARME.put("Lanceur", "Mod Armes lourdes");
    }

    /* -------------------------------------------------- */

    private static int id = -1;

    private int idMod;
    private String nom;
    private String coutMax;
    private String niveauMax;
    private String polarite;
    private String type;
    private String img;
    private String motCle;

    public Mod() {
        id++;
        this.idMod = id;
        this.nom = "";
        this.coutMax = "";
        this.niveauMax = "";
        this.polarite = "None";
        this.type = "";
        this.img = "";
        this.motCle = "";
    }

    public Mod(ModPOJO modPOJO) {
        id = ID.updateID(id, modPOJO.id_mod);
        this.idMod = modPOJO.id_mod;
        this.nom = modPOJO.nom;
        this.coutMax = modPOJO.cout_max;
        this.niveauMax = modPOJO.niveaux_max;
        this.polarite = modPOJO.polarite;
        this.type = modPOJO.type;
        this.img = modPOJO.img;
        this.motCle = modPOJO.mot_cle;
    }

    // SETTERS
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setCoutMax(String coutMax) {
        this.coutMax = coutMax;
    }
    public void setNiveauMax(String niveauMax) {
        this.niveauMax = niveauMax;
    }
    public void setPolarite(String polarite) {
        this.polarite = polarite;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public void setMotCle(String motCle) {
        this.motCle = motCle;
    }

    // GETTERS
    public int getIdMod() {
        return idMod;
    }
    public String getNom() {
        return nom;
    }
    public String getCoutMax() {
        return coutMax;
    }
    public String getNiveauMax() {
        return niveauMax;
    }
    public String getPolarite() {
        return polarite;
    }
    public String getType() {
        return type;
    }
    public String getImg() {
        return img;
    }
    public String getMotCle() {
        return motCle;
    }

    @Override
    public int compareTo(Mod autre) {
        return this.nom.compareToIgnoreCase(autre.nom);
    }

}