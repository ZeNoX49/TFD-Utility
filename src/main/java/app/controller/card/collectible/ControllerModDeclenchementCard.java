package app.controller.card.collectible;

import app.model.collectible.ModDeclenchement;
import app.util.manager.ImageManager;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class ControllerModDeclenchementCard {
    private ModDeclenchement modDeclenchement;
    private ImageManager imageManager = ImageManager.getInstance();

    @FXML private ImageView imageModDeclenchement;
    @FXML private TextField imgLinkModDeclenchement;
    @FXML private TextField nbStatModDeclenchement;
    @FXML private TextArea nomModDeclenchement;

    public void setModDeclenchement(ModDeclenchement modDeclenchement) {
        this.modDeclenchement = modDeclenchement;
        nomModDeclenchement.setText(modDeclenchement.getNom());
        nbStatModDeclenchement.setText(String.valueOf(modDeclenchement.getNbStat()));
        imgLinkModDeclenchement.setText(modDeclenchement.getImg());
        imageModDeclenchement.setImage(imageManager.getImage(modDeclenchement.getImg(), 70, 70));

        addListeners();
    }

    private void addListeners() {
        nomModDeclenchement.textProperty().addListener((_, _, newValue) -> modDeclenchement.setNom(newValue));
        nbStatModDeclenchement.textProperty().addListener((_, _, newValue) -> modDeclenchement.setNbStat(Integer.parseInt(newValue)));

        imgLinkModDeclenchement.textProperty().addListener((_, _, newValue) -> {
            modDeclenchement.setImg(newValue);
            imageModDeclenchement.setImage(imageManager.getImage(newValue, 70, 70));
        });
    }

}