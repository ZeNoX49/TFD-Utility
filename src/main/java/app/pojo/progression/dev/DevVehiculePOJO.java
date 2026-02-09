package app.pojo.progression.dev;

import app.model.progression.Vehicule;

public class DevVehiculePOJO {
    
    public int id_vehicule;
    public String nom;
    public String img;
    
    public DevVehiculePOJO(Vehicule vehicule) {
        this.id_vehicule = vehicule.getIdVehicule();
        this.nom = vehicule.getName();
        this.img = vehicule.getImage();
    }
}