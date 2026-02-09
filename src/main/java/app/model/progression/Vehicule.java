package app.model.progression;

import app.model.ID;
import app.pojo.progression.dev.DevVehiculePOJO;
import app.pojo.progression.user.UserVehiculePOJO;

public class Vehicule extends Progression {

    private static int id = 0;
    
    private final int idVehicule;

    private void setVehicule() {
        this.setMat_1("Lévitation");
        this.setMat_2("Moteur");
        this.setMat_3("Commande");
        this.setMat_4("Code");
        this.setNecessaire(1);
    }

    public Vehicule() {
        id++;
        this.idVehicule = id;
        this.setVehicule();
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
    }

    public Vehicule(DevVehiculePOJO devVehiculePOJO, UserVehiculePOJO userVehiculePOJO) {
        id = ID.updateID(id, devVehiculePOJO.id_vehicule);
        this.idVehicule = devVehiculePOJO.id_vehicule;
        this.setVehicule();
        this.setName(devVehiculePOJO.nom);
        this.setImage(devVehiculePOJO.img);
        this.setConstruit_1(userVehiculePOJO.moteur_owned);
        this.setConstruit_2(userVehiculePOJO.commande_owned);
        this.setConstruit_3(userVehiculePOJO.schema_owned);
        this.setConstruit_4(userVehiculePOJO.systeme_owned);
        this.setSchema_1(userVehiculePOJO.moteur_schema);
        this.setSchema_2(userVehiculePOJO.commande_schema);
        this.setSchema_3(userVehiculePOJO.schema_schema);
        this.setCraft(userVehiculePOJO.isCraft);
    }

    public int getIdVehicule() { return idVehicule; }
}
