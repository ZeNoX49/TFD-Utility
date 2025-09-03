package app.controller.card.collectible;

import app.model.collectible.ModArcheo;
import app.util.combobox_item.ComboboxItem;
import app.util.combobox_item.PolariteItem;
import app.util.manager.ImageManager;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class ControllerModArcheoCard {
    private ModArcheo modArcheo;
    private ImageManager imageManager = ImageManager.getInstance();

    @FXML private ComboBox<PolariteItem> cbPolariteModArcheo;
    @FXML private TextField imgLinkModArcheo;
    @FXML private ImageView imgModArcheo;
    @FXML private TextField nameModArcheo;

    @FXML 
    private void initialize() {
        ComboboxItem factory = new ComboboxItem();
        factory.setupComboBox(cbPolariteModArcheo, 15); // méthode utilitaire qui configure un ComboBox existant
        cbPolariteModArcheo.getItems().addAll(PolariteItem.getItems());
    }

    public void setModArcheo(ModArcheo modArcheo) {
        this.modArcheo = modArcheo;
        nameModArcheo.setText(modArcheo.getNom());
        imgLinkModArcheo.setText(modArcheo.getImg());
        imgModArcheo.setImage(imageManager.getImage(modArcheo.getImg(), 85, 85));

        // Affichage de l'image sélectionnée
        String polariteKey = modArcheo.getPolarite();
        if (polariteKey != null) {
            cbPolariteModArcheo.setValue(PolariteItem.getItemByPolarite(polariteKey));
        }

        addListeners();
    }

    private void addListeners() {
        nameModArcheo.textProperty().addListener((_, _, newValue) -> modArcheo.setNom(newValue));

        cbPolariteModArcheo.valueProperty().addListener((_, _, newVal) -> {
            modArcheo.setPolarite(newVal.getPolarite());
        });

        imgLinkModArcheo.textProperty().addListener((_, _, newValue) -> {
            modArcheo.setImg(newValue);
            imgModArcheo.setImage(imageManager.getImage(newValue, 85, 85));
        });
    }

}