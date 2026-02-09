package app.pojo.progression.dev;

import app.model.progression.Acolyte;

public class DevAcolytePOJO {
    
    public int id_acolyte;
    public String nom;
    public String img;

    public DevAcolytePOJO(Acolyte acolyte) {
        this.id_acolyte = acolyte.getIdAcolyte();
        this.nom = acolyte.getName();
        this.img = acolyte.getImage();
    }
    
}
