package app.model.progression;

import app.model.ID;
import app.pojo.progression.dev.DevVehiculePOJO;
import app.pojo.progression.user.UserVehiculePOJO;

public class Vehicule extends Progression {

    private static int id = -1;
    
    private final int idVehicule;

    private void setVehicule() {
        setMat_1("Lévitation");
        setMat_2("Moteur");
        setMat_3("Commande");
        setMat_4("Code");
        setNecessaire(1);
    }

    public Vehicule() {
        id++;
        this.idVehicule = id;
        setVehicule();
        setName("");
        setImage("");
        setConstruit_1(0);
        setConstruit_2(0);
        setConstruit_3(0);
        setConstruit_4(0);
        setSchema_1(0);
        setSchema_2(0);
        setSchema_3(0);
        setCraft(false);
    }

    public Vehicule(DevVehiculePOJO devVehiculePOJO, UserVehiculePOJO userVehiculePOJO) {
        id = ID.updateID(id, devVehiculePOJO.id_vehicule);
        this.idVehicule = devVehiculePOJO.id_vehicule;
        setVehicule();
        setName(devVehiculePOJO.nom);
        setImage(devVehiculePOJO.img);
        setConstruit_1(userVehiculePOJO.moteur_owned);
        setConstruit_2(userVehiculePOJO.commande_owned);
        setConstruit_3(userVehiculePOJO.schema_owned);
        setConstruit_4(userVehiculePOJO.systeme_owned);
        setSchema_1(userVehiculePOJO.moteur_schema);
        setSchema_2(userVehiculePOJO.commande_schema);
        setSchema_3(userVehiculePOJO.schema_schema);
        setCraft(userVehiculePOJO.isCraft);
    }

    public int getIdVehicule() { return idVehicule; }
}
