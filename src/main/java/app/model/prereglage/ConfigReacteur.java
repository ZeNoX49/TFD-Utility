package app.model.prereglage;

import app.pojo.prereglage.ConfigReacteurPOJO;

public class ConfigReacteur {

    private Integer idReacteur;
    private String attribut1;
    private String attribut2;

    public ConfigReacteur() {
        idReacteur = null;
        attribut1 = "";
        attribut2 = "";
    }

    public ConfigReacteur(ConfigReacteurPOJO configReacteurPOJO) {
        idReacteur = configReacteurPOJO.id_reacteur;
        attribut1 = configReacteurPOJO.attribut1;
        attribut2 = configReacteurPOJO.attribut2;
    }

    /* ----- SETTER ----- */
    public void setIdReacteur(Integer idReacteur) { this.idReacteur = idReacteur; }
    public void setAttribut1(String attribut1) { this.attribut1 = attribut1; }
    public void setAttribut2(String attribut2) { this.attribut2 = attribut2; }

    /* ----- GETTER ----- */
    public Integer getIdReacteur() { return idReacteur; }
    public String getAttribut1() { return attribut1; }
    public String getAttribut2() { return attribut2; }
    
}