package app.pojo.progression.dev;

import app.model.progression.Descendant;

public class DevDescendantPOJO {

    public int id_descendant;
    public String nom;
    public String img;
    public String type_mod_archeonique_1;
    public String type_mod_archeonique_2;
    public String type_mod_archeonique_3;
    public String type_mod_archeonique_4;
    
    public DevDescendantPOJO(Descendant descendant) {
        this.id_descendant = descendant.getIdDescendant();
        this.nom = descendant.getName();
        this.img = descendant.getImage();
        this.type_mod_archeonique_1 = descendant.getTypeModArcheonique1();
        this.type_mod_archeonique_2 = descendant.getTypeModArcheonique2();
        this.type_mod_archeonique_3 = descendant.getTypeModArcheonique3();
        this.type_mod_archeonique_4 = descendant.getTypeModArcheonique4();
    }

}