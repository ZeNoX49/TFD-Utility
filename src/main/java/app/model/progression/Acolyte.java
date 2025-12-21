package app.model.progression;

import app.model.ID;
import app.pojo.progression.dev.DevAcolytePOJO;
import app.pojo.progression.user.UserAcolytePOJO;

public class Acolyte extends Progression {

    private static int id = -1;
    
    private final int idAcolyte;

    private void setAcolyte() {
        setMat_1("Cellule");
        setMat_2("Stabilisateur");
        setMat_3("Catalyseur");
        setMat_4("Code");
        setNecessaire(1);
    }

    public Acolyte() {
        id++;
        this.idAcolyte = id;
        setAcolyte();
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

    public Acolyte(DevAcolytePOJO devAcolytePOJO, UserAcolytePOJO userAcolytePOJO) {
        id = ID.updateID(id, devAcolytePOJO.id_acolyte);
        this.idAcolyte = devAcolytePOJO.id_acolyte;
        setAcolyte();
        setName(devAcolytePOJO.nom);
        setImage(devAcolytePOJO.img);
        setConstruit_1(userAcolytePOJO.cellule_owned);
        setConstruit_2(userAcolytePOJO.stabilisateur_owned);
        setConstruit_3(userAcolytePOJO.catalyseur_owned);
        setConstruit_4(userAcolytePOJO.code_owned);
        setSchema_1(userAcolytePOJO.cellule_schema);
        setSchema_2(userAcolytePOJO.stabilisateur_schema);
        setSchema_3(userAcolytePOJO.catalyseur_schema);
        setCraft(userAcolytePOJO.isCraft);
    }

    public int getIdAcolyte() { return idAcolyte; }
}
