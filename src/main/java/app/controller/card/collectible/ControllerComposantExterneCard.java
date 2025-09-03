package app.controller.card.collectible;

import app.model.collectible.ComposantExterne;
import app.util.manager.ImageManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class ControllerComposantExterneCard {
    private ComposantExterne externalComponent;
    public static ImageManager imageManager = ImageManager.getInstance();

    private static ObservableList<String> attribut =        FXCollections.observableArrayList(ComposantExterne.ATTRIBUT.keySet());
    private static ObservableList<String> pv_max =          FXCollections.observableArrayList(ComposantExterne.ATTRIBUT.get("PV max"));
    private static ObservableList<String> def =             FXCollections.observableArrayList(ComposantExterne.ATTRIBUT.get("DEF"));
    private static ObservableList<String> bouclier_max =    FXCollections.observableArrayList(ComposantExterne.ATTRIBUT.get("Bouclier max"));

    @FXML private TextField name;

    @FXML private ImageView imgAuxiliaire;
    @FXML private ComboBox<String> statAuxiliaire, valStatAuxiliaire;
    @FXML private TextField imgLinkAuxiliaire;

    @FXML private ImageView imgDetecteur;
    @FXML private ComboBox<String> statDetecteur, valStatDetecteur;
    @FXML private TextField imgLinkDetecteur;

    @FXML private ImageView imgMemoire;
    @FXML private ComboBox<String> statMemoire, valStatMemoire;
    @FXML private TextField imgLinkMemoire;

    @FXML private ImageView imgProcesseur;
    @FXML private ComboBox<String> statProcesseur, valStatProcesseur;
    @FXML private TextField imgLinkProcesseur;

    @FXML
    private void initialize() {
        statAuxiliaire.setItems(attribut);
        statDetecteur.setItems(attribut); 
        statMemoire.setItems(attribut); 
        statProcesseur.setItems(attribut);
    }

    @FXML
    private void onAuxiliaire() {
        valStatAuxiliaire.setDisable(false);
        updateStatCombo(statAuxiliaire, valStatAuxiliaire, "Auxiliaire");
    }

    @FXML
    private void onDetecteur() {
        valStatDetecteur.setDisable(false);
        updateStatCombo(statDetecteur, valStatDetecteur, "Detecteur");
    }

    @FXML
    private void onMemoire() {
        valStatMemoire.setDisable(false);
        updateStatCombo(statMemoire, valStatMemoire, "Memoire");
    }

    @FXML
    private void onProcesseur() {
        valStatProcesseur.setDisable(false);
        updateStatCombo(statProcesseur, valStatProcesseur, "Processeur");
    }

    private void updateStatCombo(ComboBox<String> statSelector, ComboBox<String> valStatCombo, String contextName) {
        String value = statSelector.getValue();
        ObservableList<String> itemsToSet;

        switch (value) {
            case "PV max":
                itemsToSet = pv_max;
                break;
            case "DEF":
                itemsToSet = def;
                break;
            case "Bouclier max":
                itemsToSet = bouclier_max;
                break;
            default:
                return;
        }
        valStatCombo.setItems(itemsToSet);
        // Optionnel : remettre la valeur sélectionnée à null ou la première valeur
        valStatCombo.getSelectionModel().clearSelection();
    }

    public void setComposantExterne(ComposantExterne extComp) {
        externalComponent = extComp;
        name.setText(extComp.getNom());

        imgLinkAuxiliaire.setText(extComp.getImgAuxiliaire());
        imgAuxiliaire.setImage(imageManager.getImage(imgLinkAuxiliaire.getText(), 140, 100));
        if(extComp.getStatAuxiliaire()!= null && !extComp.getStatAuxiliaire().equals("")) {
            statAuxiliaire.setValue(extComp.getStatAuxiliaire());
            onAuxiliaire();
            if(extComp.getValStatAuxiliaire()!= null && !extComp.getValStatAuxiliaire().equals("")) {
                valStatAuxiliaire.setValue(extComp.getValStatAuxiliaire());
            }
        }

        imgLinkDetecteur.setText(extComp.getImgDetecteur());
        imgDetecteur.setImage(imageManager.getImage(imgLinkDetecteur.getText(), 140, 100));
        if(extComp.getStatDetecteur()!= null && !extComp.getStatDetecteur().equals("")) {
            statDetecteur.setValue(extComp.getStatDetecteur());
            onDetecteur();
            if(extComp.getValStatDetecteur()!= null && !extComp.getValStatDetecteur().equals("")) {
                valStatDetecteur.setValue(extComp.getValStatDetecteur());
            }
        }

        imgLinkMemoire.setText(extComp.getImgMemoire());
        imgMemoire.setImage(imageManager.getImage(imgLinkMemoire.getText(), 140, 100));
        if(extComp.getStatMemoire()!= null && !extComp.getStatMemoire().equals("")) {
            statMemoire.setValue(extComp.getStatMemoire());
            onMemoire();
            if(extComp.getValStatMemoire()!= null && !extComp.getValStatMemoire().equals("")) {
                valStatMemoire.setValue(extComp.getValStatMemoire());
            }
        }

        imgLinkProcesseur.setText(extComp.getImgProcesseur());
        imgProcesseur.setImage(imageManager.getImage(imgLinkProcesseur.getText(), 140, 100));
        if(extComp.getStatProcesseur()!= null && !extComp.getStatProcesseur().equals("")) {
            statProcesseur.setValue(extComp.getStatProcesseur());
            onProcesseur();
            if(extComp.getValStatProcesseur()!= null && !extComp.getValStatProcesseur().equals("")) {
                valStatProcesseur.setValue(extComp.getValStatProcesseur());
            }
        }

        addListeners();
    }

    private void addListeners() {
        name.textProperty().addListener((_, _, newValue) -> externalComponent.setNom(newValue));

        statAuxiliaire.valueProperty().addListener((_, _, newValue) -> externalComponent.setStatAuxiliaire(newValue));
        valStatAuxiliaire.valueProperty().addListener((_, _, newValue) -> externalComponent.setValStatAuxiliaire(newValue));
        imgLinkAuxiliaire.textProperty().addListener((_, _, newValue) -> {
            externalComponent.setImgAuxiliaire(newValue);
            imgAuxiliaire.setImage(imageManager.getImage(newValue, 140, 100));
        });

        statDetecteur.valueProperty().addListener((_, _, newValue) -> externalComponent.setStatDetecteur(newValue));
        valStatDetecteur.valueProperty().addListener((_, _, newValue) -> externalComponent.setValStatDetecteur(newValue));
        imgLinkDetecteur.textProperty().addListener((_, _, newValue) -> {
            externalComponent.setImgDetecteur(newValue);
            imgDetecteur.setImage(imageManager.getImage(newValue, 140, 100));
        });

        statMemoire.valueProperty().addListener((_, _, newValue) -> externalComponent.setStatMemoire(newValue));
        valStatMemoire.valueProperty().addListener((_, _, newValue) -> externalComponent.setValStatMemoire(newValue));
        imgLinkMemoire.textProperty().addListener((_, _, newValue) -> {
            externalComponent.setImgMemoire(newValue);
            imgMemoire.setImage(imageManager.getImage(newValue, 140, 100));
        });

        statProcesseur.valueProperty().addListener((_, _, newValue) -> externalComponent.setStatProcesseur(newValue));
        valStatProcesseur.valueProperty().addListener((_, _, newValue) -> externalComponent.setValStatProcesseur(newValue));
        imgLinkProcesseur.textProperty().addListener((_, _, newValue) -> {
            externalComponent.setImgProcesseur(newValue);
            imgProcesseur.setImage(imageManager.getImage(newValue, 140, 100));
        });
    }

}