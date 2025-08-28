package app.controller.card.collectible;

import java.util.HashMap;

import app.model.collectible.Mod;
import app.model.collectible.ModArcheo;
import app.util.ImageManager;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class ControllerModArcheoCard {
    private ModArcheo modArcheo;
    private ImageManager imageManager = ImageManager.getInstance();

    @FXML private ComboBox<PolariteItem> cbPolariteModArcheo;
    @FXML private TextField imgLinkModArcheo;
    @FXML private ImageView imgModArcheo;
    @FXML private TextField nameModArcheo;

    @FXML 
    private void initialize() {
        cbPolariteModArcheo.getItems().clear();
        for(String key : Mod.POLARITE.get("noir").keySet()) {
            Image img = imageManager.getImage(Mod.POLARITE.get("noir").get(key), 15, 15);
            cbPolariteModArcheo.getItems().add(new PolariteItem(key, img));
        }

        cbPolariteModArcheo.setCellFactory(param -> new ListCell<>() {
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

        cbPolariteModArcheo.setButtonCell(new ListCell<>() {
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