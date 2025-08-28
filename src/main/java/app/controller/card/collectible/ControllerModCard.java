package app.controller.card.collectible;

import java.util.HashMap;

import app.model.collectible.Mod;
import app.util.ImageManager;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class ControllerModCard {
    private Mod mod;
    private ImageManager imageManager = ImageManager.getInstance();

    @FXML private TextArea nomMod;
    @FXML private TextField coutMaxMod, niveauMaxMod, imgLinkMod, txtfieldmotcle;
    @FXML private ImageView imageMod;
    @FXML private ComboBox<PolariteItem> polariteMod;

    @FXML 
    private void initialize() {
        polariteMod.getItems().clear();
        for(String key : Mod.POLARITE.get("noir").keySet()) {
            Image img = imageManager.getImage(Mod.POLARITE.get("noir").get(key), 15, 15);
            polariteMod.getItems().add(new PolariteItem(key, img));
        }

        polariteMod.setCellFactory(param -> new ListCell<>() {
            private final ImageView imageView = new ImageView();
            private final StackPane pane = new StackPane(imageView);
            { pane.setAlignment(Pos.CENTER); }  // centre horizontalement ET verticalement

            @Override
            protected void updateItem(PolariteItem item, boolean empty) {
                super.updateItem(item, empty);
                setText(null);
                if (empty || item == null || item.getImage() == null) {
                    setGraphic(null);
                } else {
                    imageView.setImage(item.getImage());
                    imageView.setFitWidth(15);
                    imageView.setFitHeight(15);
                    setGraphic(pane);
                }
            }
        });

        polariteMod.setButtonCell(new ListCell<>() {
            private final ImageView imageView = new ImageView();
            private final StackPane pane = new StackPane(imageView);
            { pane.setAlignment(Pos.CENTER); }  // centre horizontalement ET verticalement

            @Override
            protected void updateItem(PolariteItem item, boolean empty) {
                super.updateItem(item, empty);
                setText(null);
                if (empty || item == null || item.getImage() == null) {
                    setGraphic(null);
                } else {
                    imageView.setImage(item.getImage());
                    imageView.setFitWidth(15);
                    imageView.setFitHeight(15);
                    setGraphic(pane);
                }
            }
        });
    }

    public void setMod(Mod mod) {
        this.mod = mod;
        nomMod.setText(mod.getNom());
        coutMaxMod.setText(mod.getCoutMax());
        imgLinkMod.setText(mod.getImg());
        imageMod.setImage(imageManager.getImage(mod.getImg(), 140, 190));
        niveauMaxMod.setText(mod.getNiveauMax());
        txtfieldmotcle.setText(mod.getMotCle());

        // Affichage de l'image sélectionnée
        String polariteKey = mod.getPolarite();
        if (polariteKey != null) {
            polariteMod.setValue(PolariteItem.getItemByPolarite(polariteKey));
        }

        addListeners();
    }

    private void addListeners() {
        nomMod.textProperty().addListener((_, _, newValue) -> mod.setNom(newValue));
        coutMaxMod.textProperty().addListener((_, _, newValue) -> mod.setCoutMax(newValue));
        txtfieldmotcle.textProperty().addListener((_, _, newValue) -> mod.setMotCle(newValue));

        niveauMaxMod.textProperty().addListener((_, _, newVal) -> {
            mod.setNiveauMax(newVal);
            if(!imgLinkMod.getText().contains("&enchant_level=") || imgLinkMod.getText() == null) {
                String imgLink = imgLinkMod.getText() + "&enchant_level=%s:%s".formatted(newVal, newVal);
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

class PolariteItem {
    private final String polarite;
    private final Image image;
    private static HashMap<String, PolariteItem> hashMap = new HashMap<>();

    public PolariteItem(String polarite, Image image) {
        this.polarite = polarite;
        this.image = image;
        hashMap.put(polarite, this);
    }

    public String getPolarite() {
        return polarite;
    }

    public Image getImage() {
        return image;
    }

    public static PolariteItem getItemByPolarite(String polarite) {
        return hashMap.get(polarite);
    }

    @Override
    public String toString() {
        return ""; // <-- empêche l'affichage de texte dans la ComboBox
    }
}