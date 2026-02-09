package app.model.progression;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import app.Main;
import app.model.ID;
import app.pojo.progression.dev.DevArmePOJO;
import app.pojo.progression.user.UserArmePOJO;

public class Arme extends Progression {

    public static final List<String> TYPE_ARME;
    static {
        TYPE_ARME = new ArrayList<>();
        TYPE_ARME.add("Fusil d'assaut");
        TYPE_ARME.add("Pistolet");
        TYPE_ARME.add("Fusil tactique");
        TYPE_ARME.add("Revolver");
        TYPE_ARME.add("Fusil éclaireur");
        TYPE_ARME.add("Fusil de chasse");
        TYPE_ARME.add("Fusil de précision");
        TYPE_ARME.add("Pistolet-mitrailleur");
        TYPE_ARME.add("Lanceur");
        TYPE_ARME.add("Mitrailleuse");
        TYPE_ARME.add("Fusil à rayons");
        TYPE_ARME.add("Arme de mêlée puisante");
    }

    public static final Map<String, Map<String, String>> AMELIORATION;
    static {
        AMELIORATION = new LinkedHashMap<>();

        // amélioration de puissance de feu
        AMELIORATION.put("amélioration de puissance de feu", new LinkedHashMap<>());
        AMELIORATION.get("amélioration de puissance de feu").put("ATQ des armes à feu / d'explosion X", "80%");
        AMELIORATION.get("amélioration de puissance de feu").put("ATQ bonus des armes à feu X", "550%");

        // amélioration d'attribut
        AMELIORATION.put("amélioration d'attribut", new LinkedHashMap<>());
        AMELIORATION.get("amélioration d'attribut").put("ATQ de feu X", "184%");
        AMELIORATION.get("amélioration d'attribut").put("ATQ de froid X", "184%");
        AMELIORATION.get("amélioration d'attribut").put("ATQ d'électricité X", "184%");
        AMELIORATION.get("amélioration d'attribut").put("ATQ toxique X", "184%");

        // amélioration du taux critique
        AMELIORATION.put("amélioration du taux critique", new LinkedHashMap<>());
        AMELIORATION.get("amélioration du taux critique").put("Dégat de faiblesse X", "62%");
        AMELIORATION.get("amélioration du taux critique").put("Taux de coups multiples X", "24%");
        AMELIORATION.get("amélioration du taux critique").put("Dégats des coups multiples X", "72%");
        AMELIORATION.get("amélioration du taux critique").put("Taux de coup critique des armes à feu X", "68%");
        AMELIORATION.get("amélioration du taux critique").put("Dégats critique des armes à feu X", "101%");

        // amélioration tactique
        AMELIORATION.put("amélioration tactique", new LinkedHashMap<>());
        AMELIORATION.get("amélioration tactique").put("Taux de déclenchement de l'effet X", "120%");
        AMELIORATION.get("amélioration tactique").put("Munitions par chargeur X", "150%");
        AMELIORATION.get("amélioration tactique").put("Cadence de tir X", "20%");
        AMELIORATION.get("amélioration tactique").put("Modification du temps de rechargement X", "0.74%");

        // amélioration de précision
        AMELIORATION.put("amélioration de précision", new LinkedHashMap<>());
        AMELIORATION.get("amélioration de précision").put("Recul X", "-0.92%");
        AMELIORATION.get("amélioration de précision").put("Précision de tir au jugé X", "92%");
        AMELIORATION.get("amélioration de précision").put("Vitesse de changement d'arme X", "0.62");
        AMELIORATION.get("amélioration de précision").put("Vitesse de sprint X", "20%");
        AMELIORATION.get("amélioration de précision").put("Taux d'augmentation du chargement du grappin X", "-0.46%");
        AMELIORATION.get("amélioration de précision").put("Précision en visée X", "92%");

        // amélioration spéciale
        AMELIORATION.put("amélioration spéciale", new LinkedHashMap<>());
        AMELIORATION.get("amélioration spéciale").put("ATQ bonus der armes à feu (Légion des Ténèbres) X", "550%");
        AMELIORATION.get("amélioration spéciale").put("ATQ bonus der armes à feu (Ordre de la Vérité) X", "550%");
        AMELIORATION.get("amélioration spéciale").put("ATQ bonus der armes à feu (Légion de l'Immortalité) X", "550%");

        // amélioration commune
        AMELIORATION.put("amélioration commune", new LinkedHashMap<>());
        for(String amelio : AMELIORATION.keySet()) {
            if(!AMELIORATION.get(amelio).keySet().isEmpty()) {
                for(String k : AMELIORATION.get(amelio).keySet()) {
                     AMELIORATION.get("amélioration commune").put(k, AMELIORATION.get(amelio).get(k));
                }
            }
        }
    }

    public static final Map<String, String> AMELIORATION_IMG;
    static {
        AMELIORATION_IMG = new LinkedHashMap<>();
        AMELIORATION_IMG.put("amélioration commune",             Main.class.getResource("/img/amelioration/arme/commun.png").toExternalForm());
        AMELIORATION_IMG.put("amélioration de puissance de feu", Main.class.getResource("/img/amelioration/arme/malachite.png").toExternalForm());
        AMELIORATION_IMG.put("amélioration d'attribut",          Main.class.getResource("/img/amelioration/arme/xantique.png").toExternalForm());
        AMELIORATION_IMG.put("amélioration du taux critique",    Main.class.getResource("/img/amelioration/arme/almadin.png").toExternalForm());
        AMELIORATION_IMG.put("amélioration tactique",            Main.class.getResource("/img/amelioration/arme/ceruleen.png").toExternalForm());
        AMELIORATION_IMG.put("amélioration de précision",        Main.class.getResource("/img/amelioration/arme/rutile.png").toExternalForm());
        AMELIORATION_IMG.put("amélioration spéciale",            Main.class.getResource("/img/amelioration/arme/special.png").toExternalForm());
    }

    public static final Map<String, String> ATTRIBUT;   // nom / valeur max
    static {
        ATTRIBUT = new LinkedHashMap<>();
        ATTRIBUT.put("ATQ des armes à feu", "12.2%");
        ATTRIBUT.put("ATQ de feu", "1731");
        ATTRIBUT.put("ATQ de foird", "1731");
        ATTRIBUT.put("ATQ d'électricté", "1731");
        ATTRIBUT.put("ATQ toxique", "1731");
        ATTRIBUT.put("Dégâts de faiblesse", "12%");
        ATTRIBUT.put("Taux de coup critique des armes à feu", "12.2%");
        ATTRIBUT.put("Dégâts critiques des armes à feu", "24.7%");
        ATTRIBUT.put("Taux de déclenchement de l'effet", "24%");
        ATTRIBUT.put("Munitions par chargeur", "12%");
        ATTRIBUT.put("Recul", "-0.124x");
        ATTRIBUT.put("Précision de tir au jugé", "12.4%");
        ATTRIBUT.put("Vitesse de changement d'arme", "16.5%");
        ATTRIBUT.put("ATQ bonus des armes à feu (Colosse)", "3462");
        ATTRIBUT.put("ATQ bonus des armes à feu (Légion des Ténèbres)", "3462");
        ATTRIBUT.put("ATQ bonus des armes à feu (Ordre de la Vérité)", "3462");
        ATTRIBUT.put("ATQ bonus des armes à feu (Légion de l'Immortalité)", "3462");
        ATTRIBUT.put("Taux de coup multiples(+)", "12.3%");
        ATTRIBUT.put("Dégâts des coups multiples", "25.8%");
    }

    /* -------------------------------------------------- */

    private static int id = 0;
    
    private final int idArme;
    private int nbCraft;
    private String typeArme;
    private String amelioration1;
    private String amelioration2;
    private String amelioration3;
    private String amelioration4;
    private String amelioration5;

    private void setArme() {
        this.setMat_1("Polymère");
        this.setMat_2("Fibre");
        this.setMat_3("Nano-tube");
        this.setMat_4("schéma");
        this.setNecessaire(5);
    }

    public Arme() {
        id++;
        this.idArme = id;
        this.setArme();
        this.setName("");
        this.setImage("");
        this.setConstruit_1(0);
        this.setConstruit_2(0);
        this.setConstruit_3(0);
        this.setConstruit_4(0);
        this.setSchema_1(0);
        this.setSchema_2(0);
        this.setSchema_3(0);
        this.nbCraft = 0;
        this.setCraft(false);
        this.typeArme = "";
        this.amelioration1 = "";
        this.amelioration2 = "";
        this.amelioration3 = "";
        this.amelioration4 = "";
        this.amelioration5 = "";
    }

    public Arme(DevArmePOJO devArmePOJO, UserArmePOJO userArmePOJO) {
        id = ID.updateID(id, devArmePOJO.id_arme);
        this.idArme = devArmePOJO.id_arme;
        this.setArme();
        this.setName(devArmePOJO.nom);
        this.setImage(devArmePOJO.img);
        this.setConstruit_1(userArmePOJO.polymere_owned);
        this.setConstruit_2(userArmePOJO.fibre_owned);
        this.setConstruit_3(userArmePOJO.nanotubes_owned);
        this.setConstruit_4(userArmePOJO.schema);
        this.setSchema_1(userArmePOJO.polymere_schema);
        this.setSchema_2(userArmePOJO.fibre_schema);
        this.setSchema_3(userArmePOJO.nanotubes_schema);
        this.nbCraft = userArmePOJO.nbCraft;
        this.setCraft(this.isCraft());
        this.typeArme = devArmePOJO.type_arme;
        this.amelioration1 = devArmePOJO.amelioration_1;
        this.amelioration2 = devArmePOJO.amelioration_2;
        this.amelioration3 = devArmePOJO.amelioration_3;
        this.amelioration4 = devArmePOJO.amelioration_4;
        this.amelioration5 = devArmePOJO.amelioration_5;
    }

    public void setNbCraft(int nbCraft)            { this.nbCraft = nbCraft; }
    public void setTypeArme(String type)              { this.typeArme = type; }
    public void setAmelioration1(String amelioration) { this.amelioration1 = amelioration; }
    public void setAmelioration2(String amelioration) { this.amelioration2 = amelioration; }
    public void setAmelioration3(String amelioration) { this.amelioration3 = amelioration; }
    public void setAmelioration4(String amelioration) { this.amelioration4 = amelioration; }
    public void setAmelioration5(String amelioration) { this.amelioration5 = amelioration; }

    public int    getIdArme()        { return this.idArme; }
    public int    getNbCraft()       { return this.nbCraft; }
    public String getTypeArme()      { return this.typeArme; }
    public String getAmelioration1() { return this.amelioration1; }
    public String getAmelioration2() { return this.amelioration2; }
    public String getAmelioration3() { return this.amelioration3; }
    public String getAmelioration4() { return this.amelioration4; }
    public String getAmelioration5() { return this.amelioration5; }

    @Override
    public boolean isCraft() {
        return this.nbCraft == this.getNecessaire();
    }

}