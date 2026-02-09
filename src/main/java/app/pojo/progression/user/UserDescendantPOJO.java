package app.pojo.progression.user;

import app.model.progression.Descendant;

public class UserDescendantPOJO {

    public int id_descendant;
    public int cellule_owned;
    public int cellule_schema;
    public int stabilisateur_owned;
    public int stabilisateur_schema;
    public int catalyseur_owned;
    public int catalyseur_schema;
    public int code_owned;
    public boolean isCraft;
    
    public UserDescendantPOJO(int id) {
        this.id_descendant = id;
        this.cellule_owned = 0;
        this.cellule_schema = 0;
        this.stabilisateur_owned = 0;
        this.stabilisateur_schema = 0;
        this.catalyseur_owned = 0;
        this.catalyseur_schema = 0;
        this.code_owned = 0;
        this.isCraft = false;
    }

    public UserDescendantPOJO(Descendant descendant) {
        this.id_descendant = descendant.getIdDescendant();
        this.cellule_owned = descendant.getSchema_1();
        this.cellule_schema = descendant.getConstruit_1();
        this.stabilisateur_owned = descendant.getSchema_2();
        this.stabilisateur_schema = descendant.getConstruit_2();
        this.catalyseur_owned = descendant.getSchema_3();
        this.catalyseur_schema = descendant.getConstruit_3();
        this.code_owned = descendant.getConstruit_4();
        this.isCraft = descendant.isCraft();
    }
}