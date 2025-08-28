package app.model.progression;

import app.model.ID;
import app.pojo.progression.VehiculePOJO;

public class Vehicule extends Progression {

    private static int id = -1;
    
    private int idVehicule;

    private void setVehicule() {
        setMat_1("Lévitation");
        setMat_2("Moteur");
        setMat_3("Commande");
        setMat_4("Code");
        setNecessaire("1");
    }

    public Vehicule() {
        id++;
        this.idVehicule = id;
        setVehicule();
        setName("");
        setImage("");
        setConstruit_1("0");
        setConstruit_2("0");
        setConstruit_3("0");
        setConstruit_4("0");
        setSchema_1("0");
        setSchema_2("0");
        setSchema_3("0");
        setCraft(false);
    }

    public Vehicule(VehiculePOJO vehiculePOJO) {
        id = ID.updateID(id, vehiculePOJO.id_vehicule);
        this.idVehicule = vehiculePOJO.id_vehicule;
        setVehicule();
        setName(vehiculePOJO.nom);
        setImage(vehiculePOJO.img);
        setConstruit_1(vehiculePOJO.moteur_owned);
        setConstruit_2(vehiculePOJO.commande_owned);
        setConstruit_3(vehiculePOJO.schema_owned);
        setConstruit_4(vehiculePOJO.systeme_owned);
        setSchema_1(vehiculePOJO.moteur_schema);
        setSchema_2(vehiculePOJO.commande_schema);
        setSchema_3(vehiculePOJO.schema_schema);
        setCraft(vehiculePOJO.isCraft.equals("y"));
    }

    public int getIdVehicule() { return idVehicule; }
}
