package app.controller.card.prereglage;

import java.io.IOException;

import app.Collection.CollectionCollectible;
import app.Collection.CollectionProgression;
import app.Main;
import app.controller.page.ControllerPrereglagePage;
import app.model.prereglage.Prereglage;
import app.util.manager.ImageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class ControllerPrereglageCard {
    private ImageManager imageManager = ImageManager.getInstance();
    private Prereglage prereglage;

    @FXML private Label motCle, auteur, nom, date;
    @FXML private ImageView descendant;
    @FXML private ImageView arme1, arme2, arme3;
    @FXML private ImageView acolyte;
    @FXML private ImageView reacteur;
    @FXML private ImageView auxiliaire, detecteur, memoire, processeur;

    public void setPrereglage(Prereglage prereglage) {
        this.prereglage = prereglage;
        motCle.setText(prereglage.getMotCle());
        auteur.setText(prereglage.getAuteur());
        nom.setText(prereglage.getNom());
        date.setText(prereglage.getDate());
        if(prereglage.getIdDescendant() != null) {
            descendant.setImage(imageManager.getImage(CollectionProgression.getDescendantById(prereglage.getIdDescendant()).getImage(), 100, 100));
        }
        if(prereglage.getIdArme1() != null) {
            arme1.setImage(imageManager.getImage(CollectionProgression.getArmeById(prereglage.getIdArme1()).getImage(), 100, 100));
        }
        if(prereglage.getIdArme2() != null) {
            arme2.setImage(imageManager.getImage(CollectionProgression.getArmeById(prereglage.getIdArme2()).getImage(), 100, 100));
        }
        if(prereglage.getIdArme3() != null) {
            arme3.setImage(imageManager.getImage(CollectionProgression.getArmeById(prereglage.getIdArme3()).getImage(), 100, 100));
        }
        if(prereglage.getIdAcolyte() != null) {
            acolyte.setImage(imageManager.getImage(CollectionProgression.getAcolyteById(prereglage.getIdAcolyte()).getImage(), 100, 100));
        }
        if(prereglage.getConfigReacteur().getIdReacteur() != null) {
            reacteur.setImage(imageManager.getImage(CollectionCollectible.getReacteurById(prereglage.getConfigReacteur().getIdReacteur()).getImg(), 100, 100));
        }
        if(prereglage.getConfigAuxiliaire().getIdComposantExterne() != null) {
            auxiliaire.setImage(imageManager.getImage(CollectionCollectible.getComposantExterneById(prereglage.getConfigAuxiliaire().getIdComposantExterne()).getAuxiliaire().getImg(), 100, 100));
        }
        if(prereglage.getConfigDetecteur().getIdComposantExterne() != null) {
            detecteur.setImage(imageManager.getImage(CollectionCollectible.getComposantExterneById(prereglage.getConfigDetecteur().getIdComposantExterne()).getDetecteur().getImg(), 100, 100));
        }
        if(prereglage.getConfigMemoire().getIdComposantExterne() != null) {
            memoire.setImage(imageManager.getImage(CollectionCollectible.getComposantExterneById(prereglage.getConfigMemoire().getIdComposantExterne()).getMemoire().getImg(), 100, 100));
        }
        if(prereglage.getConfigProcesseur().getIdComposantExterne() != null) {
            processeur.setImage(imageManager.getImage(CollectionCollectible.getComposantExterneById(prereglage.getConfigProcesseur().getIdComposantExterne()).getProcesseur().getImg(), 100, 100));
        }
    }

    @FXML
    void modifyPrereglage(ActionEvent event) throws IOException {
        Main.switchScene("prereglagePage.fxml");
        ControllerPrereglagePage controller = (ControllerPrereglagePage) Main.getCurrentController();
        controller.setPrereglage(prereglage);
    }

}