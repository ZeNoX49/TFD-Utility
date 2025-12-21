package app.controller.card.progression;

import app.model.progression.Progression;
import app.model.progression.Vehicule;
import app.util.manager.ImageManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.GridPane;

public class ControllerProgressionCardVehicule extends ControllerProgressionCard {
    private static ImageManager imageManager = ImageManager.getInstance();

    private Vehicule vehicule;

    private CheckBox craft;

    public void setProgression(Progression progression, String type) {
        vehicule = (Vehicule)progression;
        super.showVehiculeCard();
        setupSpinners();

        super.setProgression(progression);

        craft = createCheckbox();
        craft.setSelected(progression.isCraft());
        gridPane.add(craft, 0, 0);
        GridPane.setHalignment(craft, HPos.CENTER);
        GridPane.setValignment(craft, VPos.CENTER);

        addListeners();
        setColor();
        for(Spinner<Integer> spinner : allSpinners) {
            setColorCompleted(spinner);
        }
    }

    @Override
    public void addListeners() {
        name.textProperty().addListener((_, _, newValue) -> vehicule.setName(newValue));
        imageLink.textProperty().addListener((_, _, newValue) -> {
            vehicule.setImage(newValue);
            image.setImage(imageManager.getImage(newValue, 100, 100));
        });

        schema_1.valueProperty().addListener((_, _, newValue) -> {
            vehicule.setSchema_1(newValue);
            setColorCompleted(schema_1);
            if (newValue < construit_1.getValue()) {
                construit_1.getValueFactory().setValue(newValue);
            }
        });
        schema_2.valueProperty().addListener((_, _, newValue) -> {
            vehicule.setSchema_2(newValue);
            setColorCompleted(schema_2);
            if (newValue < construit_2.getValue()) {
                construit_2.getValueFactory().setValue(newValue);
            }
        });
        schema_3.valueProperty().addListener((_, _, newValue) -> {
            vehicule.setSchema_3(newValue);
            setColorCompleted(schema_3);
            if (newValue < construit_3.getValue()) {
                construit_3.getValueFactory().setValue(newValue);
            }
        });

        construit_1.valueProperty().addListener((_, _, newValue) -> {
            vehicule.setConstruit_1(newValue);
            setColorCompleted(construit_1);
            if(newValue > schema_1.getValue()) {
                schema_1.getValueFactory().setValue(newValue);
            }
        });
        construit_2.valueProperty().addListener((_, _, newValue) -> {
            vehicule.setConstruit_2(newValue);
            setColorCompleted(construit_2);
            if (newValue > schema_2.getValue()) {
                schema_2.getValueFactory().setValue(newValue);
            }
        });
        construit_3.valueProperty().addListener((_, _, newValue) -> {
            vehicule.setConstruit_3(newValue);
            setColorCompleted(construit_3);
            if (newValue > schema_3.getValue()) {
                schema_3.getValueFactory().setValue(newValue);
            }
        });
        construit_4.valueProperty().addListener((_, _, newValue) ->  {
            vehicule.setConstruit_4(newValue);
            setColorCompleted(construit_4);
        });

        craft.selectedProperty().addListener((_, _, _) -> {
            vehicule.setCraft(craft.isSelected());
            if(craft.isSelected()) {
                construit_1.getValueFactory().setValue(1);
                construit_2.getValueFactory().setValue(1);
                construit_3.getValueFactory().setValue(1);
                construit_4.getValueFactory().setValue(1);
            }
            else {
                schema_1.getValueFactory().setValue(0);
                schema_2.getValueFactory().setValue(0);
                schema_3.getValueFactory().setValue(0);
                construit_4.getValueFactory().setValue(0);
            }
        });
    }
    
    @Override
    public void setupSpinners() {
        ObservableList<Integer> values = FXCollections.observableArrayList(0, 1);
        SpinnerValueFactory<Integer> factory = new SpinnerValueFactory.ListSpinnerValueFactory<>(values);

        for (Spinner<Integer> spinner : allSpinners) {
            spinner.setValueFactory(factory);
            spinner.setEditable(false);
        }
    }

    // Couleur des textes
    private void setColor() {
        super.setColor("#3f7abd");
    }

    // Couleur du spinner (pour les armes)
    private void setColorCompleted(Spinner<Integer> spinner) {
        super.setColorCompleted(spinner, (spinner.getValue() == 1));
    }
}