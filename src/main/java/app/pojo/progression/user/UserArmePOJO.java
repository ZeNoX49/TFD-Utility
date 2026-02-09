package app.pojo.progression.user;

import app.model.progression.Arme;

public class UserArmePOJO {
    
    public int id_arme;

    public int polymere_owned;
    public int polymere_schema;
    public int fibre_owned;
    public int fibre_schema;
    public int nanotubes_owned;
    public int nanotubes_schema;
    public int schema;
    public int nbCraft;
    
    public UserArmePOJO(int id) {
        this.id_arme = id;
        this.polymere_owned = 0;
        this.polymere_schema = 0;
        this.fibre_owned = 0;
        this.fibre_schema = 0;
        this.nanotubes_owned = 0;
        this.nanotubes_schema = 0;
        this.schema = 0;
        this.nbCraft = 0;
    }

    public UserArmePOJO(Arme arme) {
        this.id_arme = arme.getIdArme();
        this.polymere_owned = arme.getSchema_1();
        this.polymere_schema = arme.getConstruit_1();
        this.fibre_owned = arme.getSchema_2();
        this.fibre_schema = arme.getConstruit_2();
        this.nanotubes_owned = arme.getSchema_3();
        this.nanotubes_schema = arme.getConstruit_3();
        this.schema = arme.getConstruit_4();
        this.nbCraft = arme.getNbCraft();
    }
}