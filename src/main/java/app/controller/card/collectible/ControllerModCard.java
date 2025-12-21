package app.controller.card.collectible;

import app.model.collectible.Mod;
import app.util.combobox_item.ComboboxItem;
import app.util.combobox_item.PolariteItem;
import app.util.manager.ImageManager;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class ControllerModCard {
    private Mod mod;
    private ImageManager imageManager = ImageManager.getInstance();

    @FXML private TextArea nomMod;
    @FXML private TextField coutMaxMod, niveauMaxMod, imgLinkMod, txtfieldmotcle;
    @FXML private ImageView imageMod;
    @FXML private ComboBox<PolariteItem> polariteMod;

    @FXML
    private void initialize() {
        ComboboxItem factory = new ComboboxItem();
        factory.setupComboBox(polariteMod, 15);
        polariteMod.getItems().addAll(PolariteItem.getItems());
    }

    public void setMod(Mod mod) {
        this.mod = mod;
        nomMod.setText(mod.getNom());
        coutMaxMod.setText(mod.getCoutMax());
        imgLinkMod.setText(mod.getImg());
        imageMod.setImage(imageManager.getImage(mod.getImg(), 140, 190));
        niveauMaxMod.setText(mod.getNiveauMax());
        txtfieldmotcle.setText(mod.getMotCle());
        polariteMod.setValue(PolariteItem.getItemByPolarite(mod.getPolarite()));

        addListeners();
    }

    private void addListeners() {
        nomMod.textProperty().addListener((_, _, newValue) -> mod.setNom(newValue));
        coutMaxMod.textProperty().addListener((_, _, newValue) -> mod.setCoutMax(newValue));
        txtfieldmotcle.textProperty().addListener((_, _, newValue) -> mod.setMotCle(newValue));

        niveauMaxMod.textProperty().addListener((_, _, newVal) -> {
            mod.setNiveauMax(newVal);
            String imgLink = imgLinkMod.getText();
            if(imgLink != null && !imgLink.contains("&enchant_level=") && !imgLink.isEmpty()) {
                imgLink += "&enchant_level=%s:%s".formatted(newVal, newVal);
                mod.setImg(imgLink);
                imageMod.setImage(imageManager.getImage(imgLink, 140, 190));
            }
        });

        polariteMod.valueProperty().addListener((_, _, newVal) -> {
            mod.setPolarite(newVal.getPolarite());
        });

        imgLinkMod.textProperty().addListener((_, _, newValue) -> {
            mod.setImg(newValue);
            imageMod.setImage(imageManager.getImage(newValue, 140, 190));
        });
    }

}