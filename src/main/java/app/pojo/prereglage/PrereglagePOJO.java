package app.pojo.prereglage;

public class PrereglagePOJO {

    public String auteur;
    public String nom;
    public String date;
    public String mot_cle;
    public Integer id_descendant;
    public BuildPOJO build_descendant;
    public Integer id_arme1;
    public BuildPOJO build_arme1;
    public Integer id_arme2;
    public BuildPOJO build_arme2;
    public Integer id_arme3;
    public BuildPOJO build_arme3;
    public BuildArcheoPOJO[] build_archeo;
    public Integer id_mod_archeo1;
    public Integer id_mod_archeo2;
    public String[] attribut_saison;
    public Integer id_acolyte;
    public Integer id_vehicule;
    public ConfigReacteurPOJO config_reacteur;
    public ConfigComposantExternePOJO config_auxiliaire;
    public ConfigComposantExternePOJO config_detecteur;
    public ConfigComposantExternePOJO config_memoire;
    public ConfigComposantExternePOJO config_processeur;
    
}