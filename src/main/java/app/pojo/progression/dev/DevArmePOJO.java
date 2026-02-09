package app.pojo.progression.dev;

import app.model.progression.Arme;

public class DevArmePOJO {
    
    public int id_arme;
    public String nom;
    public String img;

    public String type_arme;

    public String amelioration_1;
    public String amelioration_2;
    public String amelioration_3;
    public String amelioration_4;
    public String amelioration_5;
    
    public DevArmePOJO(Arme arme) {
        this.id_arme = arme.getIdArme();
        this.nom = arme.getName();
        this.img = arme.getImage();
        this.type_arme = arme.getTypeArme();
        this.amelioration_1 = arme.getAmelioration1();
        this.amelioration_2 = arme.getAmelioration2();
        this.amelioration_3 = arme.getAmelioration3();
        this.amelioration_4 = arme.getAmelioration4();
        this.amelioration_5 = arme.getAmelioration5();
    }
}