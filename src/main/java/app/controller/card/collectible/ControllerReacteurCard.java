package app.controller.card.collectible;

import java.util.HashMap;

import app.Main;
import app.collection.CollectionCollectible;
import app.model.collectible.Reacteur;
import app.util.manager.ImageManager;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class ControllerReacteurCard {
    private ImageManager imageManager = ImageManager.getInstance();

    @FXML private TextField imgLink_Electrique_Dimension;
    @FXML private TextField imgLink_Electrique_Fusion;
    @FXML private TextField imgLink_Electrique_Singulier;
    @FXML private TextField imgLink_Electrique_Tech;
    @FXML private TextField imgLink_Feu_Dimension;
    @FXML private TextField imgLink_Feu_Fusion;
    @FXML private TextField imgLink_Feu_Singulier;
    @FXML private TextField imgLink_Feu_Tech;
    @FXML private TextField imgLink_Froid_Dimension;
    @FXML private TextField imgLink_Froid_Fusion;
    @FXML private TextField imgLink_Froid_Singulier;
    @FXML private TextField imgLink_Froid_Tech;
    @FXML private TextField imgLink_Neutre_Dimension;
    @FXML private TextField imgLink_Neutre_Fusion;
    @FXML private TextField imgLink_Neutre_Singulier;
    @FXML private TextField imgLink_Neutre_Tech;
    @FXML private TextField imgLink_Poison_Dimension;
    @FXML private TextField imgLink_Poison_Fusion;
    @FXML private TextField imgLink_Poison_Singulier;
    @FXML private TextField imgLink_Poison_Tech;

    @FXML private ImageView img_Electrique_Dimension;
    @FXML private ImageView img_Electrique_Fusion;
    @FXML private ImageView img_Electrique_Singulier;
    @FXML private ImageView img_Electrique_Tech;
    @FXML private ImageView img_Feu_Dimension;
    @FXML private ImageView img_Feu_Fusion;
    @FXML private ImageView img_Feu_Singulier;
    @FXML private ImageView img_Feu_Tech;
    @FXML private ImageView img_Froid_Dimension;
    @FXML private ImageView img_Froid_Fusion;
    @FXML private ImageView img_Froid_Singulier;
    @FXML private ImageView img_Froid_Tech;
    @FXML private ImageView img_Neutre_Dimension;
    @FXML private ImageView img_Neutre_Fusion;
    @FXML private ImageView img_Neutre_Singulier;
    @FXML private ImageView img_Neutre_Tech;
    @FXML private ImageView img_Poison_Dimension;
    @FXML private ImageView img_Poison_Fusion;
    @FXML private ImageView img_Poison_Singulier;
    @FXML private ImageView img_Poison_Tech;

    private HashMap<String, TextField> mapTextField = new HashMap<>();
    private HashMap<String, ImageView> mapImageView = new HashMap<>();
    private HashMap<String, Reacteur> mapReacteur = new HashMap<>();

    @FXML
    private void initialize() {
        mapTextField.put("Eléctrique Dimension", imgLink_Electrique_Dimension);
        mapTextField.put("Eléctrique Fusion", imgLink_Electrique_Fusion);
        mapTextField.put("Eléctrique Singulier", imgLink_Electrique_Singulier);
        mapTextField.put("Eléctrique Tech", imgLink_Electrique_Tech);
        mapTextField.put("Feu Dimension", imgLink_Feu_Dimension);
        mapTextField.put("Feu Fusion", imgLink_Feu_Fusion);
        mapTextField.put("Feu Singulier", imgLink_Feu_Singulier);
        mapTextField.put("Feu Tech", imgLink_Feu_Tech);
        mapTextField.put("Froid Dimension", imgLink_Froid_Dimension);
        mapTextField.put("Froid Fusion", imgLink_Froid_Fusion);
        mapTextField.put("Froid Singulier", imgLink_Froid_Singulier);
        mapTextField.put("Froid Tech", imgLink_Froid_Tech);
        mapTextField.put("Neutre Dimension", imgLink_Neutre_Dimension);
        mapTextField.put("Neutre Fusion", imgLink_Neutre_Fusion);
        mapTextField.put("Neutre Singulier", imgLink_Neutre_Singulier);
        mapTextField.put("Neutre Tech", imgLink_Neutre_Tech);
        mapTextField.put("Poison Dimension", imgLink_Poison_Dimension);
        mapTextField.put("Poison Fusion", imgLink_Poison_Fusion);
        mapTextField.put("Poison Singulier", imgLink_Poison_Singulier);
        mapTextField.put("Poison Tech", imgLink_Poison_Tech);

        mapImageView.put("Eléctrique Dimension", img_Electrique_Dimension);
        mapImageView.put("Eléctrique Fusion", img_Electrique_Fusion);
        mapImageView.put("Eléctrique Singulier", img_Electrique_Singulier);
        mapImageView.put("Eléctrique Tech", img_Electrique_Tech);
        mapImageView.put("Feu Dimension", img_Feu_Dimension);
        mapImageView.put("Feu Fusion", img_Feu_Fusion);
        mapImageView.put("Feu Singulier", img_Feu_Singulier);
        mapImageView.put("Feu Tech", img_Feu_Tech);
        mapImageView.put("Froid Dimension", img_Froid_Dimension);
        mapImageView.put("Froid Fusion", img_Froid_Fusion);
        mapImageView.put("Froid Singulier", img_Froid_Singulier);
        mapImageView.put("Froid Tech", img_Froid_Tech);
        mapImageView.put("Neutre Dimension", img_Neutre_Dimension);
        mapImageView.put("Neutre Fusion", img_Neutre_Fusion);
        mapImageView.put("Neutre Singulier", img_Neutre_Singulier);
        mapImageView.put("Neutre Tech", img_Neutre_Tech);
        mapImageView.put("Poison Dimension", img_Poison_Dimension);
        mapImageView.put("Poison Fusion", img_Poison_Fusion);
        mapImageView.put("Poison Singulier", img_Poison_Singulier);
        mapImageView.put("Poison Tech", img_Poison_Tech);
    }

    public void setReacteur() {
        for(Reacteur reacteur : CollectionCollectible.getReacteur()) {
            Main.addTextLoad(" - " + reacteur.getNom());
            mapReacteur.put(reacteur.getNom(), reacteur);
            mapTextField.get(reacteur.getNom()).setText(reacteur.getImg());
            mapImageView.get(reacteur.getNom()).setImage(imageManager.getImage(reacteur.getImg(), 225, 225));
        }

        addListeners();
    }

    private void addListeners() {
        imgLink_Electrique_Dimension.textProperty().addListener((_, _, newValue) -> {
            String key = getKeyBeValue(imgLink_Electrique_Dimension);
            mapReacteur.get(key).setImg(newValue);
            img_Electrique_Dimension.setImage(imageManager.getImage(newValue, 225, 225));
        });
        imgLink_Electrique_Fusion.textProperty().addListener((_, _, newValue) -> {
            String key = getKeyBeValue(imgLink_Electrique_Fusion);
            mapReacteur.get(key).setImg(newValue);
            img_Electrique_Fusion.setImage(imageManager.getImage(newValue, 225, 225));
        });
        imgLink_Electrique_Singulier.textProperty().addListener((_, _, newValue) -> {
            String key = getKeyBeValue(imgLink_Electrique_Singulier);
            mapReacteur.get(key).setImg(newValue);
            img_Electrique_Singulier.setImage(imageManager.getImage(newValue, 225, 225));
        });
        imgLink_Electrique_Tech.textProperty().addListener((_, _, newValue) -> {
            String key = getKeyBeValue(imgLink_Electrique_Tech);
            mapReacteur.get(key).setImg(newValue);
            img_Electrique_Tech.setImage(imageManager.getImage(newValue, 225, 225));
        });
        imgLink_Feu_Dimension.textProperty().addListener((_, _, newValue) -> {
            String key = getKeyBeValue(imgLink_Feu_Dimension);
            mapReacteur.get(key).setImg(newValue);
            img_Feu_Dimension.setImage(imageManager.getImage(newValue, 225, 225));
        });
        imgLink_Feu_Fusion.textProperty().addListener((_, _, newValue) -> {
            String key = getKeyBeValue(imgLink_Feu_Fusion);
            mapReacteur.get(key).setImg(newValue);
            img_Feu_Fusion.setImage(imageManager.getImage(newValue, 225, 225));
        });
        imgLink_Feu_Singulier.textProperty().addListener((_, _, newValue) -> {
            String key = getKeyBeValue(imgLink_Feu_Singulier);
            mapReacteur.get(key).setImg(newValue);
            img_Feu_Singulier.setImage(imageManager.getImage(newValue, 225, 225));
        });
        imgLink_Feu_Tech.textProperty().addListener((_, _, newValue) -> {
            String key = getKeyBeValue(imgLink_Feu_Tech);
            mapReacteur.get(key).setImg(newValue);
            img_Feu_Tech.setImage(imageManager.getImage(newValue, 225, 225));
        });
        imgLink_Froid_Dimension.textProperty().addListener((_, _, newValue) -> {
            String key = getKeyBeValue(imgLink_Froid_Dimension);
            mapReacteur.get(key).setImg(newValue);
            img_Froid_Dimension.setImage(imageManager.getImage(newValue, 225, 225));
        });
        imgLink_Froid_Fusion.textProperty().addListener((_, _, newValue) -> {
            String key = getKeyBeValue(imgLink_Froid_Fusion);
            mapReacteur.get(key).setImg(newValue);
            img_Froid_Fusion.setImage(imageManager.getImage(newValue, 225, 225));
        });
        imgLink_Froid_Singulier.textProperty().addListener((_, _, newValue) -> {
            String key = getKeyBeValue(imgLink_Froid_Singulier);
            mapReacteur.get(key).setImg(newValue);
            img_Froid_Singulier.setImage(imageManager.getImage(newValue, 225, 225));
        });
        imgLink_Froid_Tech.textProperty().addListener((_, _, newValue) -> {
            String key = getKeyBeValue(imgLink_Froid_Tech);
            mapReacteur.get(key).setImg(newValue);
            img_Froid_Tech.setImage(imageManager.getImage(newValue, 225, 225));
        });
        imgLink_Neutre_Dimension.textProperty().addListener((_, _, newValue) -> {
            String key = getKeyBeValue(imgLink_Neutre_Dimension);
            mapReacteur.get(key).setImg(newValue);
            img_Neutre_Dimension.setImage(imageManager.getImage(newValue, 225, 225));
        });
        imgLink_Neutre_Fusion.textProperty().addListener((_, _, newValue) -> {
            String key = getKeyBeValue(imgLink_Neutre_Fusion);
            mapReacteur.get(key).setImg(newValue);
            img_Neutre_Fusion.setImage(imageManager.getImage(newValue, 225, 225));
        });
        imgLink_Neutre_Singulier.textProperty().addListener((_, _, newValue) -> {
            String key = getKeyBeValue(imgLink_Neutre_Singulier);
            mapReacteur.get(key).setImg(newValue);
            img_Neutre_Singulier.setImage(imageManager.getImage(newValue, 225, 225));
        });
        imgLink_Neutre_Tech.textProperty().addListener((_, _, newValue) -> {
            String key = getKeyBeValue(imgLink_Neutre_Tech);
            mapReacteur.get(key).setImg(newValue);
            img_Neutre_Tech.setImage(imageManager.getImage(newValue, 225, 225));
        });
        imgLink_Poison_Dimension.textProperty().addListener((_, _, newValue) -> {
            String key = getKeyBeValue(imgLink_Poison_Dimension);
            mapReacteur.get(key).setImg(newValue);
            img_Poison_Dimension.setImage(imageManager.getImage(newValue, 225, 225));
        });
        imgLink_Poison_Fusion.textProperty().addListener((_, _, newValue) -> {
            String key = getKeyBeValue(imgLink_Poison_Fusion);
            mapReacteur.get(key).setImg(newValue);
            img_Poison_Fusion.setImage(imageManager.getImage(newValue, 225, 225));
        });
        imgLink_Poison_Singulier.textProperty().addListener((_, _, newValue) -> {
            String key = getKeyBeValue(imgLink_Poison_Singulier);
            mapReacteur.get(key).setImg(newValue);
            img_Poison_Singulier.setImage(imageManager.getImage(newValue, 225, 225));
        });
        imgLink_Poison_Tech.textProperty().addListener((_, _, newValue) -> {
            String key = getKeyBeValue(imgLink_Poison_Tech);
            mapReacteur.get(key).setImg(newValue);
            img_Poison_Tech.setImage(imageManager.getImage(newValue, 225, 225));
        });
    }

    private String getKeyBeValue(TextField tf) {
        for(String key : mapTextField.keySet()) {
            if(mapTextField.get(key).equals(tf)) {
                return key;
            }
        }
        return null;
    }

}