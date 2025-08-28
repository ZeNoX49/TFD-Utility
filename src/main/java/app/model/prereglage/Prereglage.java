package app.model.prereglage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import app.Collection.CollectionProgression;
import app.pojo.prereglage.BuildArcheoPOJO;
import app.pojo.prereglage.PrereglagePOJO;

public class Prereglage {

    public final static List<String> MOT_CLE = new ArrayList<>();
    static {
        MOT_CLE.add("General");
        MOT_CLE.add("Farm");
        MOT_CLE.add("Colosse");
        MOT_CLE.add("Érosion du Néant");
        MOT_CLE.add("...");
    }

    private String auteur;
    private String nom;
    private String date;
    private String motCle;

    private Integer idDescendant;
    private BuildDescendant buildDescendant;

    private Integer idArme1;
    private BuildArme buildArme1;

    private Integer idArme2;
    private BuildArme buildArme2;

    private Integer idArme3;
    private BuildArme buildArme3;

    private BuildArcheonique buildArcheo;
    private Integer idModArcheo1;
    private Integer idModArcheo2;
    private List<String> attributSaison;

    private Integer idAcolyte;
    private Integer idVehicule;

    private ConfigReacteur configReacteur;
    private ConfigComposantExterne configAuxiliaire;
    private ConfigComposantExterne configDetecteur;
    private ConfigComposantExterne configMemoire;
    private ConfigComposantExterne configProcesseur;

    public Prereglage() {
        auteur = "";
        nom = "";
        date = "00/00/0000";
        motCle = "";

        idDescendant = null;
        buildDescendant = new BuildDescendant();

        idArme1 = null;
        buildArme1 = new BuildArme("arme1");

        idArme2 = null;
        buildArme2 = new BuildArme("arme2");

        idArme3 = null;
        buildArme3 = new BuildArme("arme3");

        buildArcheo = new BuildArcheonique();
        idModArcheo1 = null;
        idModArcheo2 = null;
        attributSaison = new ArrayList<>();

        idAcolyte = null;
        idVehicule = null;

        configReacteur = new ConfigReacteur();
        configAuxiliaire = new ConfigComposantExterne();
        configDetecteur = new ConfigComposantExterne();
        configMemoire = new ConfigComposantExterne();
        configProcesseur = new ConfigComposantExterne();
    }

    public Prereglage(PrereglagePOJO prereglagePOJO) {
        auteur = prereglagePOJO.auteur;
        nom = prereglagePOJO.nom;
        date = prereglagePOJO.date;
        motCle = prereglagePOJO.mot_cle;

        idDescendant = prereglagePOJO.id_descendant;
        buildDescendant = new BuildDescendant(prereglagePOJO.build_descendant);

        idArme1 = prereglagePOJO.id_arme1;
        buildArme1 = new BuildArme(prereglagePOJO.build_arme1);

        idArme2 = prereglagePOJO.id_arme2;
        buildArme2 = new BuildArme(prereglagePOJO.build_arme2);

        idArme3 = prereglagePOJO.id_arme3;
        buildArme3 = new BuildArme(prereglagePOJO.build_arme3);

        buildArcheo = new BuildArcheonique();
        if(prereglagePOJO.build_archeo != null) {
            for(BuildArcheoPOJO buildArcheoPOJO : prereglagePOJO.build_archeo) {
                buildArcheo.addNode(buildArcheoPOJO.key);
            }
        }
        idModArcheo1 = prereglagePOJO.id_mod_archeo1;
        idModArcheo2 = prereglagePOJO.id_mod_archeo2;
        attributSaison = new ArrayList<>();
        if(prereglagePOJO.attribut_saison != null) {
            attributSaison.addAll(Arrays.asList(prereglagePOJO.attribut_saison));
        }

        idAcolyte = prereglagePOJO.id_acolyte;
        idVehicule = prereglagePOJO.id_vehicule;

        configReacteur = new ConfigReacteur(prereglagePOJO.config_reacteur);
        configAuxiliaire = new ConfigComposantExterne(prereglagePOJO.config_auxiliaire);
        configDetecteur = new ConfigComposantExterne(prereglagePOJO.config_detecteur);
        configMemoire = new ConfigComposantExterne(prereglagePOJO.config_memoire);
        configProcesseur = new ConfigComposantExterne(prereglagePOJO.config_processeur);
    }

    /* ----- SETTER ----- */
    public void setAuteur(String auteur)                                     { this.auteur = auteur; }
    public void setNom(String nom)                                           { this.nom = nom; }
    public void setDate(String date)                                         { this.date = date; }
    public void setMotCle(String motCle)                                     { this.motCle = motCle; }
    public void setIdDescendant(Integer idDescendant)                        { this.idDescendant = idDescendant; }
    public void setBuildDescendant(BuildDescendant buildDescendant)          { this.buildDescendant = buildDescendant; }
    public void setIdArme1(Integer idArme1)                                  { this.idArme1 = idArme1; }
    public void setBuildArme1(BuildArme buildArme1)                          { this.buildArme1 = buildArme1; }
    public void setIdArme2(Integer idArme2)                                  { this.idArme2 = idArme2; }
    public void setBuildArme2(BuildArme buildArme2)                          { this.buildArme2 = buildArme2; }
    public void setIdArme3(Integer idArme3)                                  { this.idArme3 = idArme3; }
    public void setBuildArme3(BuildArme buildArme3)                          { this.buildArme3 = buildArme3; }
    public void setBuildArcheo(BuildArcheonique buildArcheo)                 { this.buildArcheo = buildArcheo; }
    public void setIdModArcheo1(Integer idModArcheo1)                        { this.idModArcheo1 = idModArcheo1; }
    public void setIdModArcheo2(Integer idModArcheo2)                        { this.idModArcheo2 = idModArcheo2; }
    public void addAttributSaison(String attribut)                           { attributSaison.add(attribut); }
    public void removeAttributSaison(String attribut)                        { attributSaison.remove(attribut); }
    public void setIdAcolyte(Integer idAcolyte)                              { this.idAcolyte = idAcolyte; }
    public void setIdVehicule(Integer idVehicule)                            { this.idVehicule = idVehicule; }
    public void setConfigReacteur(ConfigReacteur configReacteur)             { this.configReacteur = configReacteur; }
    public void setConfigAuxiliaire(ConfigComposantExterne configAuxiliaire) { this.configAuxiliaire = configAuxiliaire; }
    public void setConfigDetecteur(ConfigComposantExterne configDetecteur)   { this.configDetecteur = configDetecteur; }
    public void setConfigMemoire(ConfigComposantExterne configMemoire)       { this.configMemoire = configMemoire; }
    public void setConfigProcesseur(ConfigComposantExterne configProcesseur) { this.configProcesseur = configProcesseur; }

    /* ----- GETTER ----- */
    public String getAuteur()                           { return auteur; }
    public String getNom()                              { return nom; }
    public String getDate()                             { return date; }
    public String getMotCle()                           { return motCle; }
    public Integer getIdDescendant()                    { return idDescendant; }
    public BuildDescendant getBuildDescendant()         { return buildDescendant; }
    public Integer getIdArme1()                         { return idArme1; }
    public BuildArme getBuildArme1()                    { return buildArme1; }
    public Integer getIdArme2()                         { return idArme2; }
    public BuildArme getBuildArme2()                    { return buildArme2; }
    public Integer getIdArme3()                         { return idArme3; }
    public BuildArme getBuildArme3()                    { return buildArme3; }
    public BuildArcheonique getBuildArcheo()            { return buildArcheo; }
    public Integer getIdModArcheo1()                    { return idModArcheo1; }
    public Integer getIdModArcheo2()                    { return idModArcheo2; }
    public List<String> getAttributSaison()             { return attributSaison; }
    public Integer getIdAcolyte()                       { return idAcolyte; }
    public Integer getIdVehicule()                      { return idVehicule; }
    public ConfigReacteur getConfigReacteur()           { return configReacteur; }
    public ConfigComposantExterne getConfigAuxiliaire() { return configAuxiliaire; }
    public ConfigComposantExterne getConfigDetecteur()  { return configDetecteur; }
    public ConfigComposantExterne getConfigMemoire()    { return configMemoire; }
    public ConfigComposantExterne getConfigProcesseur() { return configProcesseur; }

    // --- Comparators ---
    public static final Comparator<Prereglage> COMPARATEUR_AUTEUR =
        Comparator.comparing(p -> p.auteur, String.CASE_INSENSITIVE_ORDER);

    public static final Comparator<Prereglage> COMPARATEUR_NOM =
        Comparator.comparing(p -> p.nom, String.CASE_INSENSITIVE_ORDER);

    public static final Comparator<Prereglage> COMPARATEUR_DATE =
        Comparator.comparing(p -> LocalDate.parse(p.date, DateTimeFormatter.ofPattern("dd/MM/yyyy")));


    public static final Comparator<Prereglage> COMPARATEUR_DESCENDANT =
        Comparator.comparing(
            p -> CollectionProgression.getDescendantById(p.idDescendant).getName(),
            String.CASE_INSENSITIVE_ORDER
        );

    public static final Comparator<Prereglage> COMPARATEUR_MOTCLE =
        Comparator.comparing(p -> p.motCle, String.CASE_INSENSITIVE_ORDER);

    /**
     * Méthode utilitaire pour obtenir un Comparator selon un type.
     */
    public static Comparator<Prereglage> getComparator(String typeCompare) {
        switch (typeCompare.toLowerCase()) {
            case "auteur": return COMPARATEUR_AUTEUR;
            case "nom": return COMPARATEUR_NOM;
            case "date": return COMPARATEUR_DATE;
            case "descendant": return COMPARATEUR_DESCENDANT;
            case "motcle": return COMPARATEUR_MOTCLE;
            default:
                throw new IllegalArgumentException("Type de comparaison inconnu : " + typeCompare);
        }
    }

    public Prereglage duppliquer() {
        Prereglage newPrereglage = new Prereglage();
        newPrereglage.auteur = this.auteur;
        newPrereglage.nom = this.nom;
        newPrereglage.date = this.date;
        newPrereglage.motCle = this.motCle;
        newPrereglage.idDescendant = this.idDescendant;
        newPrereglage.buildDescendant = this.buildDescendant;
        newPrereglage.idArme1 = this.idArme1;
        newPrereglage.buildArme1 = this.buildArme1;
        newPrereglage.idArme2 = this.idArme2;
        newPrereglage.buildArme2 = this.buildArme2;
        newPrereglage.idArme3 = this.idArme3;
        newPrereglage.buildArme3 = this.buildArme3;
        newPrereglage.idAcolyte = this.idAcolyte;
        newPrereglage.idVehicule = this.idVehicule;
        newPrereglage.configReacteur = this.configReacteur;
        newPrereglage.configAuxiliaire = this.configAuxiliaire;
        newPrereglage.configDetecteur = this.configDetecteur;
        newPrereglage.configMemoire = this.configMemoire;
        newPrereglage.configProcesseur = this.configProcesseur;
        return newPrereglage;
    }

}