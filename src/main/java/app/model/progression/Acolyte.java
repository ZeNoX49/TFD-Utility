package app.model.progression;

import app.model.ID;
import app.pojo.progression.dev.DevAcolytePOJO;
import app.pojo.progression.user.UserAcolytePOJO;

public class Acolyte extends Progression {

    private static int id = 0;
    
    private final int idAcolyte;

    private void setAcolyte() {
        this.setMat_1("Cellule");
        this.setMat_2("Stabilisateur");
        this.setMat_3("Catalyseur");
        this.setMat_4("Code");
        this.setNecessaire(1);
    }

    public Acolyte() {
        id++;
        this.idAcolyte = id;
        this.setAcolyte();
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

    public Acolyte(DevAcolytePOJO devAcolytePOJO, UserAcolytePOJO userAcolytePOJO) {
        id = ID.updateID(id, devAcolytePOJO.id_acolyte);
        this.idAcolyte = devAcolytePOJO.id_acolyte;
        this.setAcolyte();
        this.setName(devAcolytePOJO.nom);
        this.setImage(devAcolytePOJO.img);
        this.setConstruit_1(userAcolytePOJO.cellule_owned);
        this.setConstruit_2(userAcolytePOJO.stabilisateur_owned);
        this.setConstruit_3(userAcolytePOJO.catalyseur_owned);
        this.setConstruit_4(userAcolytePOJO.code_owned);
        this.setSchema_1(userAcolytePOJO.cellule_schema);
        this.setSchema_2(userAcolytePOJO.stabilisateur_schema);
        this.setSchema_3(userAcolytePOJO.catalyseur_schema);
        this.setCraft(userAcolytePOJO.isCraft);
    }

    public int getIdAcolyte() { return idAcolyte; }
}
