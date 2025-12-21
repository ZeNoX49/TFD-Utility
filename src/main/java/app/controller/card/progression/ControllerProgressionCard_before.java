package app.controller.card.progression;

import java.util.List;

import app.model.progression.Acolyte;
import app.model.progression.Arme;
import app.model.progression.Descendant;
import app.model.progression.Progression;
import app.model.progression.Vehicule;
import app.util.combobox_item.AmeliorationArmeItem;
import app.util.combobox_item.ComboboxItem;
import app.util.combobox_item.PolariteItem;
import app.util.manager.ImageManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class ControllerProgressionCardDescendant extends ControllerProgressionCard {
    private static ImageManager imageManager = ImageManager.getInstance();

    private Progression progression;
    private Descendant descendant;
    private Arme arme;
    private Acolyte acolyte;
    private Vehicule vehicule;
    private String type = "";

    // Tout les trucs de base
    @FXML private TextField name, imageLink;
    @FXML private ImageView image;
    @FXML private GridPane gridPane;
    @FXML private Label necessaire, construit, schema;
    @FXML private Label mat_1, mat_2, mat_3, mat_4;
    @FXML private Label necessaire_1, necessaire_2, necessaire_3, necessaire_4;
    @FXML private Spinner<String> construit_1, construit_2, construit_3, construit_4;
    @FXML private Spinner<String> schema_1, schema_2, schema_3;

    // Acolytes et Descendants
    private CheckBox craft;

    // Descendants et Armes
    @FXML private HBox hboxSupp;

    // Descendants
    private ComboBox<PolariteItem> typeArcheo1, typeArcheo2, typeArcheo3, typeArcheo4;

    // Armes
    @FXML private ComboBox<String> typeArme;
    private Spinner<String> nbCraft = createSpinnerArme();
    private ComboBox<AmeliorationArmeItem> typeAmelio1, typeAmelio2, typeAmelio3, typeAmelio4, typeAmelio5;

    @FXML
    private void initialize() {
        setupSpinners();   // Pour ne pas avoir de problème
    }

    public void setProgression(Progression progression, String type) {
        this.type = type;
        this.progression = progression;
        setupSpinners();

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

        schema_1.getValueFactory().setValue(Integer.toString(progression.getSchema_1()));
        schema_2.getValueFactory().setValue(Integer.toString(progression.getSchema_2()));
        schema_3.getValueFactory().setValue(Integer.toString(progression.getSchema_3()));
        
        construit_1.getValueFactory().setValue(Integer.toString(progression.getConstruit_1()));
        construit_2.getValueFactory().setValue(Integer.toString(progression.getConstruit_2()));
        construit_3.getValueFactory().setValue(Integer.toString(progression.getConstruit_3()));
        construit_4.getValueFactory().setValue(Integer.toString(progression.getConstruit_4()));

        // Acolyte
        if(type.equals("a")) {
            acolyte = (Acolyte)progression;
            craft = createCheckbox();
            craft.setSelected(progression.isCraft());
            gridPane.add(craft, 0, 0);
            GridPane.setHalignment(craft, HPos.CENTER);
            GridPane.setValignment(craft, VPos.CENTER);
        }

        // Descendant
        else if(type.equals("d")) {
            descendant = (Descendant)progression;
            craft = createCheckbox();
            craft.setSelected(progression.isCraft());
            gridPane.add(craft, 0, 0);
            GridPane.setHalignment(craft, HPos.CENTER);
            GridPane.setValignment(craft, VPos.CENTER);

            this.typeArcheo1 = this.createCombobox(95);
            this.typeArcheo2 = this.createCombobox(95);
            this.typeArcheo3 = this.createCombobox(95);
            this.typeArcheo4 = this.createCombobox(95);

            ComboboxItem factory = new ComboboxItem();
            List<ComboBox<PolariteItem>> amelios = List.of(typeArcheo1, typeArcheo2, typeArcheo3, typeArcheo4);
            for (ComboBox<PolariteItem> combo : amelios) {
                factory.setupComboBox(combo, 17);
                combo.getItems().addAll(PolariteItem.getItems());
            }
            hboxSupp.getChildren().addAll(typeArcheo1, typeArcheo2, typeArcheo3, typeArcheo4);
            setupCombobox();
        }

        // Arme
        else if(type.equals("w")) {
            arme = (Arme)progression;
            typeArme.setDisable(false);
            typeArme.setVisible(true);
            typeArme.getItems().addAll(Arme.TYPE_ARME);
            typeArme.setValue(arme.getTypeArme());
            gridPane.add(nbCraft, 0, 0);
            nbCraft.getValueFactory().setValue(String.valueOf(arme.getNbCraft()));
            setColorCompleted(nbCraft);

            typeAmelio1 = createCombobox(76);
            typeAmelio2 = createCombobox(76);
            typeAmelio3 = createCombobox(76);
            typeAmelio4 = createCombobox(76);
            typeAmelio5 = createCombobox(76);

            ComboboxItem factory = new ComboboxItem();
            List<ComboBox<AmeliorationArmeItem>> amelios = List.of(typeAmelio1, typeAmelio2, typeAmelio3, typeAmelio4, typeAmelio5);
            for (ComboBox<AmeliorationArmeItem> combo : amelios) {
                factory.setupComboBox(combo, 17);
                combo.getItems().addAll(AmeliorationArmeItem.getItems());
            }
            hboxSupp.getChildren().addAll(typeAmelio1, typeAmelio2, typeAmelio3, typeAmelio4, typeAmelio5);
            setupCombobox();
        }

        // Véhicule
        else if(type.equals("v")) {
            vehicule = (Vehicule)progression;
            craft = createCheckbox();
            craft.setSelected(progression.isCraft());
            gridPane.add(craft, 0, 0);
            GridPane.setHalignment(craft, HPos.CENTER);
            GridPane.setValignment(craft, VPos.CENTER);
        }

        addListeners();
        setColor();
        for(Spinner spinner : new Spinner[] {construit_1, construit_2, construit_3, construit_4, schema_1, schema_2, schema_3}) {
            setColorCompleted(spinner);
        }
    }

    private CheckBox createCheckbox() {
        CheckBox checkBox = new CheckBox();
        checkBox.setStyle("-fx-font-size: 15px;");
        return checkBox;
    }

    private Spinner<String> createSpinnerArme() {
        Spinner<String> spinner = new Spinner<>();
        spinner.setMinHeight(34);
        spinner.setPrefHeight(34);
        spinner.setMaxHeight(34);
        return spinner;
    }

    private ComboBox createCombobox(int width) {
        ComboBox cb = new ComboBox();
        cb.setMinSize(width, 25);
        cb.setPrefSize(width, 25);
        cb.setMaxSize(width, 25);
        return cb;
    }

    private void addListeners() {
        name.textProperty().addListener((_, _, newValue) -> progression.setName(newValue));
        imageLink.textProperty().addListener((_, _, newValue) -> {
            progression.setImage(newValue);
            image.setImage(imageManager.getImage(newValue, 100, 100));
        });

        schema_1.valueProperty().addListener((_, _, newValue) -> {
            progression.setSchema_1(String.valueOf(newValue));
            setColorCompleted(schema_1);
            if (Integer.parseInt(newValue) < Integer.parseInt(construit_1.getValue())) {
                construit_1.getValueFactory().setValue(newValue);
            }
        });
        schema_2.valueProperty().addListener((_, _, newValue) -> {
            progression.setSchema_2(String.valueOf(newValue));
            setColorCompleted(schema_2);
            if (Integer.parseInt(newValue) < Integer.parseInt(construit_2.getValue())) {
                construit_2.getValueFactory().setValue(newValue);
            }
        });
        schema_3.valueProperty().addListener((_, _, newValue) -> {
            progression.setSchema_3(String.valueOf(newValue));
            setColorCompleted(schema_3);
            if (Integer.parseInt(newValue) < Integer.parseInt(construit_3.getValue())) {
                construit_3.getValueFactory().setValue(newValue);
            }
        });

        construit_1.valueProperty().addListener((_, _, newValue) -> {
            progression.setConstruit_1(String.valueOf(newValue));
            setColorCompleted(construit_1);
            if(Integer.parseInt(newValue) > Integer.parseInt(schema_1.getValue())) {
                schema_1.getValueFactory().setValue(newValue);
            }
        });
        construit_2.valueProperty().addListener((_, _, newValue) -> {
            progression.setConstruit_2(String.valueOf(newValue));
            setColorCompleted(construit_2);
            if (Integer.parseInt(newValue) > Integer.parseInt(schema_2.getValue())) {
                schema_2.getValueFactory().setValue(newValue);
            }
        });
        construit_3.valueProperty().addListener((_, _, newValue) -> {
            progression.setConstruit_3(String.valueOf(newValue));
            setColorCompleted(construit_3);
            if (Integer.parseInt(newValue) > Integer.parseInt(schema_3.getValue())) {
                schema_3.getValueFactory().setValue(newValue);
            }
        });
        construit_4.valueProperty().addListener((_, _, newValue) ->  {
            progression.setConstruit_4(String.valueOf(newValue));
            setColorCompleted(construit_4);
        });

        // Acolyte
        if(type.equals("a")) {
            craft.selectedProperty().addListener((_, _, _) -> {
                acolyte.setCraft(craft.isSelected());
                if(craft.isSelected()) {
                    construit_1.getValueFactory().setValue("1");
                    construit_2.getValueFactory().setValue("1");
                    construit_3.getValueFactory().setValue("1");
                    construit_4.getValueFactory().setValue("1");
                }
                else {
                    schema_1.getValueFactory().setValue("0");
                    schema_2.getValueFactory().setValue("0");
                    schema_3.getValueFactory().setValue("0");
                    construit_4.getValueFactory().setValue("0");
                }
            });
        }

        // Descendant
        if(type.equals("d")) {
            craft.selectedProperty().addListener((_, _, _) -> {
                descendant.setCraft(craft.isSelected());
                if(craft.isSelected()) {
                    construit_1.getValueFactory().setValue("1");
                    construit_2.getValueFactory().setValue("1");
                    construit_3.getValueFactory().setValue("1");
                    construit_4.getValueFactory().setValue("1");
                }
                else {
                    schema_1.getValueFactory().setValue("0");
                    schema_2.getValueFactory().setValue("0");
                    schema_3.getValueFactory().setValue("0");
                    construit_4.getValueFactory().setValue("0");
                }
            });
            
            typeArcheo1.valueProperty().addListener((_, _, newVal) ->  descendant.setTypeModArcheonique1(newVal.getPolarite()) );
            typeArcheo2.valueProperty().addListener((_, _, newVal) ->  descendant.setTypeModArcheonique2(newVal.getPolarite()) );
            typeArcheo3.valueProperty().addListener((_, _, newVal) ->  descendant.setTypeModArcheonique3(newVal.getPolarite()) );
            typeArcheo4.valueProperty().addListener((_, _, newVal) ->  descendant.setTypeModArcheonique4(newVal.getPolarite()) );
        }

        // Arme
        else if(type.equals("w")) {
            nbCraft.valueProperty().addListener((_, _, newValue) -> {
                arme.setNbCraft(String.valueOf(newValue));
                setColorCompleted(nbCraft);
                progression.setCraft(newValue.equals("5"));
                if(Integer.parseInt(newValue) > Integer.parseInt(construit_1.getValue())) { construit_1.getValueFactory().setValue(newValue); }
                if(Integer.parseInt(newValue) > Integer.parseInt(construit_2.getValue())) { construit_2.getValueFactory().setValue(newValue); }
                if(Integer.parseInt(newValue) > Integer.parseInt(construit_3.getValue())) { construit_3.getValueFactory().setValue(newValue); }
                if(Integer.parseInt(newValue) > Integer.parseInt(construit_4.getValue())) { construit_4.getValueFactory().setValue(newValue); }
            });
            
            typeArme.valueProperty().addListener((_, _, newVal) ->  arme.setTypeArme(newVal) );
            typeAmelio1.valueProperty().addListener((_, _, newVal) ->  arme.setAmelioration1(newVal.getName()) );
            typeAmelio2.valueProperty().addListener((_, _, newVal) ->  arme.setAmelioration2(newVal.getName()) );
            typeAmelio3.valueProperty().addListener((_, _, newVal) ->  arme.setAmelioration3(newVal.getName()) );
            typeAmelio4.valueProperty().addListener((_, _, newVal) ->  arme.setAmelioration4(newVal.getName()) );
            typeAmelio5.valueProperty().addListener((_, _, newVal) ->  arme.setAmelioration5(newVal.getName()) );
        }

        // Véhicule
        if(type.equals("v")) {
            craft.selectedProperty().addListener((_, _, _) -> {
                vehicule.setCraft(craft.isSelected());
                if(craft.isSelected()) {
                    construit_1.getValueFactory().setValue("1");
                    construit_2.getValueFactory().setValue("1");
                    construit_3.getValueFactory().setValue("1");
                    construit_4.getValueFactory().setValue("1");
                }
                else {
                    schema_1.getValueFactory().setValue("0");
                    schema_2.getValueFactory().setValue("0");
                    schema_3.getValueFactory().setValue("0");
                    construit_4.getValueFactory().setValue("0");
                }
            });
        }
    }
    
    private void setupSpinners() {
        ObservableList<String> values;
        if(type.equals("d") || type.equals("a") || type.equals("v")) {
            values = FXCollections.observableArrayList("0", "1");
        }
        else if(type.equals("w")) {
            values = FXCollections.observableArrayList("0", "1", "2", "3", "4", "5");
            SpinnerValueFactory<String> factory = new SpinnerValueFactory.ListSpinnerValueFactory<>(values);
            nbCraft.setValueFactory(factory);
            nbCraft.setEditable(false);
        }
        else {
            values = FXCollections.observableArrayList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        }

        for (Spinner<String> spinner : new Spinner[] {construit_1, construit_2, construit_3, construit_4, schema_1, schema_2, schema_3}) {
            SpinnerValueFactory<String> factory = new SpinnerValueFactory.ListSpinnerValueFactory<>(values);
            spinner.setValueFactory(factory);
            spinner.setEditable(false);
        }
    }

    private void setupCombobox() {
        if (type.equals("d")) {
            typeArcheo1.setValue(PolariteItem.getItemByPolarite(descendant.getTypeModArcheonique1()));
            typeArcheo2.setValue(PolariteItem.getItemByPolarite(descendant.getTypeModArcheonique2()));
            typeArcheo3.setValue(PolariteItem.getItemByPolarite(descendant.getTypeModArcheonique3()));
            typeArcheo4.setValue(PolariteItem.getItemByPolarite(descendant.getTypeModArcheonique4()));
        } 
        
        else if (type.equals("w")) {
            typeArme.setValue(arme.getTypeArme());
            typeAmelio1.setValue(AmeliorationArmeItem.getAmeliorationByKey(arme.getAmelioration1()));
            typeAmelio2.setValue(AmeliorationArmeItem.getAmeliorationByKey(arme.getAmelioration2()));
            typeAmelio3.setValue(AmeliorationArmeItem.getAmeliorationByKey(arme.getAmelioration3()));
            typeAmelio4.setValue(AmeliorationArmeItem.getAmeliorationByKey(arme.getAmelioration4()));
            typeAmelio5.setValue(AmeliorationArmeItem.getAmeliorationByKey(arme.getAmelioration5()));
        }
    }

    // Couleur des textes
    private void setColor() {
        String color = "#3f7abd";
        if(type.equals("w") || (type.equals("d") && descendant.getName().contains("Ultime"))) {
            color = "#d8c519";
        }

        for(Label label : new Label[] {mat_1, mat_2, mat_3, mat_4, necessaire, construit, schema, necessaire_1, necessaire_2, necessaire_3, necessaire_4}) {
            label.setStyle("-fx-text-fill: " + color + ";");
        }

        name.setStyle("-fx-text-fill: #c8c8c8; -fx-background-color: transparent");
        imageLink.setStyle("-fx-text-fill: #c8c8c8; -fx-background-color: transparent");
    }

    // Couleur du spinner (pour les armes)
    private void setColorCompleted(Spinner spinner) {
        boolean completed = type.equals("w") ? spinner.getValue().equals("5") : spinner.getValue().equals("1");
        String color = completed ? "#24e624" : "#c8c8c8";
        spinner.getEditor().setStyle(
            "-fx-text-fill: " + color + ";" + 
            "-fx-font-size: 18px;" +
            "-fx-alignment: center;" +
            "-fx-background-color: #282828;"
        );
    }
}