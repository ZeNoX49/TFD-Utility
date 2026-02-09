package app.pojo.progression.user;

import app.model.progression.Acolyte;

public class UserAcolytePOJO {
    
    public int id_acolyte;
    public int cellule_owned;
    public int cellule_schema;
    public int stabilisateur_owned;
    public int stabilisateur_schema;
    public int catalyseur_owned;
    public int catalyseur_schema;
    public int code_owned;
    public boolean isCraft;
    
    public UserAcolytePOJO(int id) {
        this.id_acolyte = id;
        this.cellule_owned = 0;
        this.cellule_schema = 0;
        this.stabilisateur_owned = 0;
        this.stabilisateur_schema = 0;
        this.catalyseur_owned = 0;
        this.catalyseur_schema = 0;
        this.code_owned = 0;
        this.isCraft = false;
    }

    public UserAcolytePOJO(Acolyte acolyte) {
        this.id_acolyte = acolyte.getIdAcolyte();
        this.cellule_owned = acolyte.getSchema_1();
        this.cellule_schema = acolyte.getConstruit_1();
        this.stabilisateur_owned = acolyte.getSchema_2();
        this.stabilisateur_schema = acolyte.getConstruit_2();
        this.catalyseur_owned = acolyte.getSchema_3();
        this.catalyseur_schema = acolyte.getConstruit_3();
        this.code_owned = acolyte.getConstruit_4();
        this.isCraft = acolyte.isCraft();
    }
}
