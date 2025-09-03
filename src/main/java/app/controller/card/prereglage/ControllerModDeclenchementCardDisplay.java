package app.controller.card.prereglage;

import app.model.collectible.ModDeclenchement;
import app.util.manager.ImageManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class ControllerModDeclenchementCardDisplay {
    private ImageManager imageManager = ImageManager.getInstance();
    private ModDeclenchement modDeclenchement;

    @FXML private Label labelNomModDeclenchement;
    @FXML private ImageView imgModDeclenchement;
    @FXML private ImageView imgStat1;
    @FXML private ImageView imgStat2;
    @FXML private ImageView imgStat3;
    @FXML private ImageView imgStat4;
    private ImageView[] imList;

    @FXML
    private void initialize() {
        imList = new ImageView[] {imgStat1, imgStat2, imgStat3, imgStat4};
    }

    public void setModVide() {
        labelNomModDeclenchement.setText("");
        imgModDeclenchement.setImage(null);
        for(int i = 0; i < 4; i++) {
            imList[i].setVisible(false);
        }
    }

    public void setMod(ModDeclenchement modDeclenchement) {
        this.modDeclenchement = modDeclenchement;
        labelNomModDeclenchement.setText(modDeclenchement.getNom());
        imgModDeclenchement.setImage(imageManager.getImage(modDeclenchement.getImg(), 70, 70));
        for(int i = 0; i < 4; i++) {
            imList[i].setVisible(i < modDeclenchement.getNbStat());
        }
    }

    public ModDeclenchement getModDeclenchement() {
        return modDeclenchement;
    }

}
