package app.controller.card.progression;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import app.model.collectible.Mod;
import app.model.progression.Acolyte;
import app.model.progression.Arme;
import app.model.progression.Descendant;
import app.model.progression.Progression;
import app.model.progression.Vehicule;
import app.util.ImageManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class ControllerProgressionCard {
    public static ImageManager imageManager = ImageManager.getInstance();

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
    private ComboBox<ArcheoDescendant> typeArcheo1, typeArcheo2, typeArcheo3, typeArcheo4;

    // Armes
    @FXML private ComboBox<String> typeArme;
    private Spinner<String> nbCraft = createSpinnerArme();
    private ComboBox<AmeliorationArme> typeAmelio1, typeAmelio2, typeAmelio3, typeAmelio4, typeAmelio5;

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

        necessaire_1.setText(progression.getNecessaire());
        necessaire_2.setText(progression.getNecessaire());
        necessaire_3.setText(progression.getNecessaire());
        necessaire_4.setText(progression.getNecessaire());

        schema_1.getValueFactory().setValue(progression.getSchema_1());
        schema_2.getValueFactory().setValue(progression.getSchema_2());
        schema_3.getValueFactory().setValue(progression.getSchema_3());
        
        construit_1.getValueFactory().setValue(progression.getConstruit_1());
        construit_2.getValueFactory().setValue(progression.getConstruit_2());
        construit_3.getValueFactory().setValue(progression.getConstruit_3());
        construit_4.getValueFactory().setValue(progression.getConstruit_4());

        if(type.equals("a")) {
            acolyte = (Acolyte)progression;
            craft = createCheckbox();
            craft.setSelected(progression.isCraft());
            gridPane.add(craft, 0, 0);
            gridPane.setHalignment(craft, HPos.CENTER);
            gridPane.setValignment(craft, VPos.CENTER);
        }

        else if(type.equals("d")) {
            descendant = (Descendant)progression;
            craft = createCheckbox();
            craft.setSelected(progression.isCraft());
            gridPane.add(craft, 0, 0);
            gridPane.setHalignment(craft, HPos.CENTER);
            gridPane.setValignment(craft, VPos.CENTER);
            typeArcheo1 = createComoboxDescendant(descendant.getTypeModArcheonique1());
            typeArcheo2 = createComoboxDescendant(descendant.getTypeModArcheonique2());
            typeArcheo3 = createComoboxDescendant(descendant.getTypeModArcheonique3());
            typeArcheo4 = createComoboxDescendant(descendant.getTypeModArcheonique4());
            hboxSupp.getChildren().addAll(typeArcheo1, typeArcheo2, typeArcheo3, typeArcheo4);
            initializeComboBoxes(
                new ComboBox[]{typeArcheo1, typeArcheo2, typeArcheo3, typeArcheo4},
                Mod.POLARITE.get("noir"),
                key -> new ArcheoDescendant(key, imageManager.getImage(Mod.POLARITE.get("noir").get(key), 18, 18))
            );
            setupCombobox();
        }

        else if(type.equals("w")) {
            arme = (Arme)progression;
            typeArme.setDisable(false);
            typeArme.setVisible(true);
            typeArme.getItems().addAll(Arme.TYPE_ARME);
            typeArme.setValue(arme.getTypeArme());
            gridPane.add(nbCraft, 0, 0);
            nbCraft.getValueFactory().setValue(arme.getNbCraft());
            setColorCompleted(nbCraft);
            typeAmelio1 = createComboboxArme(arme.getAmelioration1());
            typeAmelio2 = createComboboxArme(arme.getAmelioration2());
            typeAmelio3 = createComboboxArme(arme.getAmelioration3());
            typeAmelio4 = createComboboxArme(arme.getAmelioration4());
            typeAmelio5 = createComboboxArme(arme.getAmelioration5());
            hboxSupp.getChildren().addAll(typeAmelio1, typeAmelio2, typeAmelio3, typeAmelio4, typeAmelio5);
            initializeComboBoxes(
                new ComboBox[]{typeAmelio1, typeAmelio2, typeAmelio3, typeAmelio4, typeAmelio5},
                Arme.AMELIORATION_IMG,
                key -> new AmeliorationArme(key, imageManager.getImage(Arme.AMELIORATION_IMG.get(key), 18, 18))
            );
            setupCombobox();
        }

        else if(type.equals("v")) {
            vehicule = (Vehicule)progression;
            craft = createCheckbox();
            craft.setSelected(progression.isCraft());
            gridPane.add(craft, 0, 0);
            gridPane.setHalignment(craft, HPos.CENTER);
            gridPane.setValignment(craft, VPos.CENTER);
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

    private ComboBox<ArcheoDescendant> createComoboxDescendant(String amelio) {
        ComboBox<ArcheoDescendant> comboBox = new ComboBox<>();
        comboBox.setMinWidth(95);
        comboBox.setPrefWidth(95);
        comboBox.setMaxWidth(95);

        if (amelio != null) {
            comboBox.setValue(ArcheoDescendant.getArcheoByKey(amelio));
        }

        return comboBox;
    }

    private ComboBox<AmeliorationArme> createComboboxArme(String amelio) {
        ComboBox<AmeliorationArme> comboBox = new ComboBox<>();
        comboBox.setMinWidth(76);
        comboBox.setPrefWidth(76);
        comboBox.setMaxWidth(76);

        if (amelio != null) {
            comboBox.setValue(AmeliorationArme.getAmeliorationByey(amelio));
        }

        return comboBox;
    }

    private <T> void initializeComboBoxes(ComboBox<T>[] comboBoxes, Map<String, String> polariteMap, Function<String, T> itemCreator) {
        for (ComboBox<T> comboBox : comboBoxes) {
            comboBox.getItems().clear();
            for (String key : polariteMap.keySet()) {
                T item = itemCreator.apply(key);
                comboBox.getItems().add(item);
            }

            comboBox.setCellFactory(param -> new ListCell<>() {
                private final ImageView imageView = new ImageView();
                private final StackPane pane = new StackPane(imageView);
                { pane.setAlignment(Pos.CENTER); }  // centre horizontalement ET verticalement

                @Override
                protected void updateItem(T item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(null);
                    if (empty || item == null) {
                        setGraphic(null);
                    } else {
                        Image image = (item instanceof ArcheoDescendant ad) ? ad.getImage() :
                                    (item instanceof AmeliorationArme aa) ? aa.getImage() : null;
                        if (image != null) {
                            imageView.setImage(image);
                            imageView.setFitWidth(18);
                            imageView.setFitHeight(18);
                            setGraphic(pane);
                        } else {
                            setGraphic(null);
                        }
                    }
                }
            });
            comboBox.setButtonCell(comboBox.getCellFactory().call(null));
        }
    }

    private void addListeners() {
        name.textProperty().addListener((_, _, newValue) -> progression.setName(newValue));
        imageLink.textProperty().addListener((_, _, newValue) -> {
            progression.setImage(newValue);
            image.setImage(imageManager.getImage(newValue, 100, 100));
        });

        schema_1.valueProperty().addListener((_, _, newValue) -> {
            progression.setSchema_1(newValue);
            setColorCompleted(schema_1);
            if (Integer.parseInt(newValue) < Integer.parseInt(construit_1.getValue())) {
                construit_1.getValueFactory().setValue(newValue);
            }
        });
        schema_2.valueProperty().addListener((_, _, newValue) -> {
            progression.setSchema_2(newValue);
            setColorCompleted(schema_2);
            if (Integer.parseInt(newValue) < Integer.parseInt(construit_2.getValue())) {
                construit_2.getValueFactory().setValue(newValue);
            }
        });
        schema_3.valueProperty().addListener((_, _, newValue) -> {
            progression.setSchema_3(newValue);
            setColorCompleted(schema_3);
            if (Integer.parseInt(newValue) < Integer.parseInt(construit_3.getValue())) {
                construit_3.getValueFactory().setValue(newValue);
            }
        });

        construit_1.valueProperty().addListener((_, _, newValue) -> {
            progression.setConstruit_1(newValue);
            setColorCompleted(construit_1);
            if(Integer.parseInt(newValue) > Integer.parseInt(schema_1.getValue())) {
                schema_1.getValueFactory().setValue(newValue);
            }
        });
        construit_2.valueProperty().addListener((_, _, newValue) -> {
            progression.setConstruit_2(newValue);
            setColorCompleted(construit_2);
            if (Integer.parseInt(newValue) > Integer.parseInt(schema_2.getValue())) {
                schema_2.getValueFactory().setValue(newValue);
            }
        });
        construit_3.valueProperty().addListener((_, _, newValue) -> {
            progression.setConstruit_3(newValue);
            setColorCompleted(construit_3);
            if (Integer.parseInt(newValue) > Integer.parseInt(schema_3.getValue())) {
                schema_3.getValueFactory().setValue(newValue);
            }
        });
        construit_4.valueProperty().addListener((_, _, newValue) ->  {
            progression.setConstruit_4(newValue);
            setColorCompleted(construit_4);
        });

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
            
            typeArcheo1.valueProperty().addListener((_, _, newVal) ->  descendant.setTypeModArcheonique1(newVal.getName()) );
            typeArcheo2.valueProperty().addListener((_, _, newVal) ->  descendant.setTypeModArcheonique2(newVal.getName()) );
            typeArcheo3.valueProperty().addListener((_, _, newVal) ->  descendant.setTypeModArcheonique3(newVal.getName()) );
            typeArcheo4.valueProperty().addListener((_, _, newVal) ->  descendant.setTypeModArcheonique4(newVal.getName()) );
        }

        else if(type.equals("w")) {
            nbCraft.valueProperty().addListener((_, _, newValue) -> {
                arme.setNbCraft(newValue);
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
            setComboBoxSelection(typeArcheo1, descendant.getTypeModArcheonique1());
            setComboBoxSelection(typeArcheo2, descendant.getTypeModArcheonique2());
            setComboBoxSelection(typeArcheo3, descendant.getTypeModArcheonique3());
            setComboBoxSelection(typeArcheo4, descendant.getTypeModArcheonique4());
        } 
        
        else if (type.equals("w")) {
            typeArme.setValue(arme.getTypeArme());

            setComboBoxSelection(typeAmelio1, arme.getAmelioration1());
            setComboBoxSelection(typeAmelio2, arme.getAmelioration2());
            setComboBoxSelection(typeAmelio3, arme.getAmelioration3());
            setComboBoxSelection(typeAmelio4, arme.getAmelioration4());
            setComboBoxSelection(typeAmelio5, arme.getAmelioration5());
        }
    }

    private <T> void setComboBoxSelection(ComboBox<T> comboBox, String targetName) {
        for (T item : comboBox.getItems()) {
            if (item instanceof ArcheoDescendant archeo && archeo.getName().equals(targetName)) {
                comboBox.setValue(item);
                return;
            }
            if (item instanceof AmeliorationArme amelio && amelio.getName().equals(targetName)) {
                comboBox.setValue(item);
                return;
            }
            if (item instanceof String str && str.equals(targetName)) {
                comboBox.setValue(item);
                return;
            }
        }
    }

    private void setColor() {
        String color;
        if(type.equals("w") || (type.equals("d") && descendant.getName().contains("Ultime"))) {
            color = "#d8c519";
        }
        else {
            color = "#3f7abd";
        }

        mat_1.setStyle("-fx-text-fill: " + color + ";");
        mat_2.setStyle("-fx-text-fill: " + color + ";");
        mat_3.setStyle("-fx-text-fill: " + color + ";");
        mat_4.setStyle("-fx-text-fill: " + color + ";");
        necessaire.setStyle("-fx-text-fill: " + color + ";");
        construit.setStyle("-fx-text-fill: " + color + ";");
        schema.setStyle("-fx-text-fill: " + color + ";");
        necessaire_1.setStyle("-fx-text-fill: " + color + ";");
        necessaire_2.setStyle("-fx-text-fill: " + color + ";");
        necessaire_3.setStyle("-fx-text-fill: " + color + ";");
        necessaire_4.setStyle("-fx-text-fill: " + color + ";");

        name.setStyle("-fx-text-fill: #c8c8c8; -fx-background-color: transparent");
        imageLink.setStyle("-fx-text-fill: #c8c8c8; -fx-background-color: transparent");
    }

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

class ArcheoDescendant {
    private final String name;
    private final Image image;
    private static HashMap<String, ArcheoDescendant> hashMap = new HashMap<>();

    public ArcheoDescendant(String name, Image image) {
        this.name = name;
        this.image = image;
        hashMap.put(name, this);
    }

    public String getName() {
        return name;
    }

    public Image getImage() {
        return image;
    }

    public static ArcheoDescendant getArcheoByKey(String key) {
        return hashMap.get(key);
    }

    @Override
    public String toString() {
        return ""; // <-- empêche l'affichage de texte dans la ComboBox
    }
}

class AmeliorationArme {
    private final String name;
    private final Image image;
    private static HashMap<String, AmeliorationArme> hashMap = new HashMap<>();

    public AmeliorationArme(String name, Image image) {
        this.name = name;
        this.image = image;
        hashMap.put(name, this);
    }

    public String getName() {
        return name;
    }

    public Image getImage() {
        return image;
    }

    public static AmeliorationArme getAmeliorationByey(String key) {
        return hashMap.get(key);
    }

    @Override
    public String toString() {
        return ""; // <-- empêche l'affichage de texte dans la ComboBox
    }
}