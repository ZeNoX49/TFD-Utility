package app.pojo.progression.user;

import app.model.progression.Vehicule;

public class UserVehiculePOJO {
    
    public int id_vehicule;
    public int moteur_owned;
    public int moteur_schema;
    public int commande_owned;
    public int commande_schema;
    public int schema_owned;
    public int schema_schema;
    public int systeme_owned;
    public boolean isCraft;
    
    public UserVehiculePOJO(int id) {
        this.id_vehicule = id;
        this.moteur_owned = 0;
        this.moteur_schema = 0;
        this.commande_owned = 0;
        this.commande_schema = 0;
        this.schema_owned = 0;
        this.schema_schema = 0;
        this.systeme_owned = 0;
        this.isCraft = false;
    }

    public UserVehiculePOJO(Vehicule vehicule) {
        this.id_vehicule = vehicule.getIdVehicule();
        this.moteur_owned = vehicule.getSchema_1();
        this.moteur_schema = vehicule.getConstruit_1();
        this.commande_owned = vehicule.getSchema_2();
        this.commande_schema = vehicule.getConstruit_2();
        this.schema_owned = vehicule.getSchema_3();
        this.schema_schema = vehicule.getConstruit_3();
        this.systeme_owned = vehicule.getConstruit_4();
        this.isCraft = vehicule.isCraft();
    }
}