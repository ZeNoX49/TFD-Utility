package app.model.prereglage;

import app.pojo.prereglage.BuildPOJO;

public class BuildArme extends Build {

    private String amelio1, amelio2, amelio3, amelio4, amelio5;
    private String attribut1, attribut2, attribut3, attribut4;

    public BuildArme(String type) {
        super();
        
        setType(type);

        amelio1 = "";
        amelio2 = "";
        amelio3 = "";
        amelio4 = "";
        amelio5 = "";

        attribut1 = "";
        attribut2 = "";
        attribut3 = "";
        attribut4 = "";
    }

    public BuildArme(BuildPOJO buildPOJO) {
        super(buildPOJO);
        
        setType("arme");

        amelio1 = buildPOJO.build_arme.amelio_1;
        amelio2 = buildPOJO.build_arme.amelio_2;
        amelio3 = buildPOJO.build_arme.amelio_3;
        amelio4 = buildPOJO.build_arme.amelio_4;
        amelio5 = buildPOJO.build_arme.amelio_5;

        attribut1 = buildPOJO.build_arme.attribut_1;
        attribut2 = buildPOJO.build_arme.attribut_2;
        attribut3 = buildPOJO.build_arme.attribut_3;
        attribut4 = buildPOJO.build_arme.attribut_4;
    }

    /* ----- SETTER ----- */
    public void setAmelio1(String amelio1) { this.amelio1 = amelio1; }
    public void setAmelio2(String amelio2) { this.amelio2 = amelio2; }
    public void setAmelio3(String amelio3) { this.amelio3 = amelio3; }
    public void setAmelio4(String amelio4) { this.amelio4 = amelio4; }
    public void setAmelio5(String amelio5) { this.amelio5 = amelio5; }
    public void setAttribut1(String attribut1) { this.attribut1 = attribut1; }
    public void setAttribut2(String attribut2) { this.attribut2 = attribut2; }
    public void setAttribut3(String attribut3) { this.attribut3 = attribut3; }
    public void setAttribut4(String attribut4) { this.attribut4 = attribut4; }
    
    /* ----- GETTER ----- */ 
    public String getAmelio1() { return amelio1; }
    public String getAmelio2() { return amelio2; }
    public String getAmelio3() { return amelio3; }
    public String getAmelio4() { return amelio4; }
    public String getAmelio5() { return amelio5; }
    public String getAttribut1() { return attribut1; }
    public String getAttribut2() { return attribut2; }
    public String getAttribut3() { return attribut3; }
    public String getAttribut4() { return attribut4; }


    
}
