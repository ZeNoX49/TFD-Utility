package app.model.progression;

import app.model.ID;
import app.pojo.progression.dev.DevDescendantPOJO;
import app.pojo.progression.user.UserDescendantPOJO;

public class Descendant extends Progression {

    private static int id = 0;
    
    private final int idDescendant;
    private String typeModArcheonique1;
    private String typeModArcheonique2;
    private String typeModArcheonique3;
    private String typeModArcheonique4;

    private void setDescendant() {
        this.setMat_1("Cellule");
        this.setMat_2("Stabilisateur");
        this.setMat_3("Catalyseur");
        this.setMat_4("Code");
        this.setNecessaire(1);
    }

    public Descendant() {
        id++;
        this.idDescendant = id;
        this.setDescendant();
        this.setName("");
        this.setImage("");
        this.setConstruit_1(0);
        this.setConstruit_2(0);
        this.setConstruit_3(0);
        this.setConstruit_4(0);
        this.setSchema_1(0);
        this.setSchema_2(0);
        this.setSchema_3(0);
        this.setCraft(false);
        this.typeModArcheonique1 = "";
        this.typeModArcheonique2 = "";
        this.typeModArcheonique3 = "";
        this.typeModArcheonique4 = "";
    }

    public Descendant(DevDescendantPOJO devDescendantPOJO, UserDescendantPOJO userDescendantPOJO) {
        id = ID.updateID(id, devDescendantPOJO.id_descendant);
        this.idDescendant = devDescendantPOJO.id_descendant;
        this.setDescendant();
        this.setName(devDescendantPOJO.nom);
        this.setImage(devDescendantPOJO.img);
        this.setConstruit_1(userDescendantPOJO.cellule_owned);
        this.setConstruit_2(userDescendantPOJO.stabilisateur_owned);
        this.setConstruit_3(userDescendantPOJO.catalyseur_owned);
        this.setConstruit_4(userDescendantPOJO.code_owned);
        this.setSchema_1(userDescendantPOJO.cellule_schema);
        this.setSchema_2(userDescendantPOJO.stabilisateur_schema);
        this.setSchema_3(userDescendantPOJO.catalyseur_schema);
        this.setCraft(userDescendantPOJO.isCraft);
        this.typeModArcheonique1 = devDescendantPOJO.type_mod_archeonique_1;
        this.typeModArcheonique2 = devDescendantPOJO.type_mod_archeonique_2;
        this.typeModArcheonique3 = devDescendantPOJO.type_mod_archeonique_3;
        this.typeModArcheonique4 = devDescendantPOJO.type_mod_archeonique_4;
    }

    public void setTypeModArcheonique1(String type) { this.typeModArcheonique1 = type; }
    public void setTypeModArcheonique2(String type) { this.typeModArcheonique2 = type; }
    public void setTypeModArcheonique3(String type) { this.typeModArcheonique3 = type; }
    public void setTypeModArcheonique4(String type) { this.typeModArcheonique4 = type; }

    public int    getIdDescendant()         { return idDescendant; }
    public String getTypeModArcheonique1()  { return typeModArcheonique1; }
    public String getTypeModArcheonique2()  { return typeModArcheonique2; }
    public String getTypeModArcheonique3()  { return typeModArcheonique3; }
    public String getTypeModArcheonique4()  { return typeModArcheonique4; }

}