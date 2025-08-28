package app.model.progression;

import app.model.ID;
import app.pojo.progression.AcolytePOJO;

public class Acolyte extends Progression {

    private static int id = -1;
    
    private int idAcolyte;

    private void setAcolyte() {
        setMat_1("Cellule");
        setMat_2("Stabilisateur");
        setMat_3("Catalyseur");
        setMat_4("Code");
        setNecessaire("1");
    }

    public Acolyte() {
        id++;
        this.idAcolyte = id;
        setAcolyte();
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

    public Acolyte(AcolytePOJO acolytePOJO) {
        id = ID.updateID(id, acolytePOJO.id_acolyte);
        this.idAcolyte = acolytePOJO.id_acolyte;
        setAcolyte();
        setName(acolytePOJO.nom);
        setImage(acolytePOJO.img);
        setConstruit_1(acolytePOJO.cellule_owned);
        setConstruit_2(acolytePOJO.stabilisateur_owned);
        setConstruit_3(acolytePOJO.catalyseur_owned);
        setConstruit_4(acolytePOJO.code_owned);
        setSchema_1(acolytePOJO.cellule_schema);
        setSchema_2(acolytePOJO.stabilisateur_schema);
        setSchema_3(acolytePOJO.catalyseur_schema);
        setCraft(acolytePOJO.isCraft.equals("y"));
    }

    public int getIdAcolyte() { return idAcolyte; }
}
