package app.controller.card.progression;

import app.model.progression.Progression;
import app.util.manager.ImageManager;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public abstract class ControllerProgressionCard {
    public static ImageManager imageManager = ImageManager.getInstance();
    
    @FXML public TextField name, imageLink;
    @FXML public ImageView image;
    @FXML public GridPane gridPane;
    @FXML public Label necessaire, construit, schema;
    @FXML public Label mat_1, mat_2, mat_3, mat_4;
    @FXML public Label necessaire_1, necessaire_2, necessaire_3, necessaire_4;
    @FXML public Spinner<Integer> construit_1, construit_2, construit_3, construit_4;
    @FXML public Spinner<Integer> schema_1, schema_2, schema_3;
    @FXML public HBox hboxSupp;
    @FXML public ComboBox<String> typeArme;
    
    public Spinner<Integer>[] allSpinners;

    @FXML
    public void initialize() {
        allSpinners = new Spinner[]{construit_1, construit_2, construit_3, construit_4, schema_1, schema_2, schema_3};
        setupSpinners();
    }

    public void setProgression(Progression progression) {
        name.setText(progression.getName());
        imageLink.setText(progression.getImage());
        image.setImage(imageManager.getImage(progression.getImage(), 100, 100));

        mat_1.setText(progression.getMat_1());
        mat_2.setText(progression.getMat_2());
        mat_3.setText(progression.getMat_3());
        mat_4.setText(progression.getMat_4());

        necessaire_1.setText(Integer.toString(progression.getNecessaire()));
        necessaire_2.setText(Integer.toString(progression.getNecessaire()));
        necessaire_3.setText(Integer.toString(progression.getNecessaire()));
        necessaire_4.setText(Integer.toString(progression.getNecessaire()));

        schema_1.getValueFactory().setValue(progression.getSchema_1());
        schema_2.getValueFactory().setValue(progression.getSchema_2());
        schema_3.getValueFactory().setValue(progression.getSchema_3());
        
        construit_1.getValueFactory().setValue(progression.getConstruit_1());
        construit_2.getValueFactory().setValue(progression.getConstruit_2());
        construit_3.getValueFactory().setValue(progression.getConstruit_3());
        construit_4.getValueFactory().setValue(progression.getConstruit_4());
    }

    protected CheckBox createCheckbox() {
        CheckBox checkBox = new CheckBox();
        checkBox.setStyle("-fx-font-size: 15px;");
        return checkBox;
    }

    protected <T> ComboBox<T> createCombobox(int width) {
        ComboBox<T> cb = new ComboBox<>();
        cb.setMinSize(width, 25);
        cb.setPrefSize(width, 25);
        cb.setMaxSize(width, 25);
        return cb;
    }

    protected Spinner<Integer> createSpinnerArme() {
        Spinner<Integer> spinner = new Spinner<>();
        spinner.setMinHeight(34);
        spinner.setPrefHeight(34);
        spinner.setMaxHeight(34);
        return spinner;
    }

    public abstract void addListeners();
    public abstract void setupSpinners();
    
    protected void setColor(String color) {
        for(Label label : new Label[]{mat_1, mat_2, mat_3, mat_4, necessaire, construit, schema, necessaire_1, necessaire_2, necessaire_3, necessaire_4}) {
            label.setStyle("-fx-text-fill: " + color + ";");
        }
        name.setStyle("-fx-text-fill: #c8c8c8; -fx-background-color: transparent");
        imageLink.setStyle("-fx-text-fill: #c8c8c8; -fx-background-color: transparent");
    }

    protected void setColorCompleted(Spinner<Integer> spinner, boolean completed) {
        String color = completed ? "#24e624" : "#c8c8c8";
        spinner.getEditor().setStyle(
            "-fx-text-fill: " + color + ";" + 
            "-fx-font-size: 18px;" +
            "-fx-alignment: center;" +
            "-fx-background-color: #282828;"
        );
    }

    /* Gestion de l'affichage des cartes */
    private void showCard(boolean showHboxSupp, boolean showTypeArme) {
        hboxSupp.setVisible(showHboxSupp);
        hboxSupp.setDisable(!showHboxSupp);
        typeArme.setVisible(showTypeArme);
        typeArme.setDisable(!showTypeArme);
    }

    protected void showDescendantCard() { showCard(true, false); }
    protected void showArmeCard() { showCard(true, true); }
    protected void showAcolyteCard() { showCard(false, false); }
    protected void showVehiculeCard() { showCard(false, false); }
}