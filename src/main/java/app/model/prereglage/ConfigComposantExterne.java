package app.model.prereglage;

import app.pojo.prereglage.ConfigComposantExternePOJO;

public class ConfigComposantExterne {
    
    private Integer idComposantExterne;
    private String amelio1;
    private String amelio2;
    private String attribut1;
    private String attribut2;

    public ConfigComposantExterne() {
        idComposantExterne = null;
        amelio1 = "";
        amelio2 = "";
        attribut1 = "";
        attribut2 = "";
    }

    public ConfigComposantExterne(ConfigComposantExternePOJO configComposantExternePOJO) {
        idComposantExterne = configComposantExternePOJO.id_composantExterne;
        amelio1 = configComposantExternePOJO.amelio1;
        amelio2 = configComposantExternePOJO.amelio2;
        attribut1 = configComposantExternePOJO.attribut1;
        attribut2 = configComposantExternePOJO.attribut2;
    }

    /* ----- SETTER ----- */
    public void setIdComposantExterne(Integer idComposantExterne) { this.idComposantExterne = idComposantExterne; }
    public void setAmelio1(String amelio1) { this.amelio1 = amelio1; }
    public void setAmelio2(String amelio2) { this.amelio2 = amelio2; }
    public void setAttribut1(String attribut1) { this.attribut1 = attribut1; }
    public void setAttribut2(String attribut2) { this.attribut2 = attribut2; }

    /* ----- GETTER ----- */
    public Integer getIdComposantExterne() { return idComposantExterne; }
    public String getAmelio1() { return amelio1; }
    public String getAmelio2() { return amelio2; }
    public String getAttribut1() { return attribut1; }
    public String getAttribut2() { return attribut2; }

}