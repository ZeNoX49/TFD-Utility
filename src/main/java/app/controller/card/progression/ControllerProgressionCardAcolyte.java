package app.controller.card.progression;

import app.model.progression.Acolyte;
import app.model.progression.Progression;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.GridPane;

public class ControllerProgressionCardAcolyte extends ControllerProgressionCard {
    private Acolyte acolyte;
    private CheckBox craft;

    @Override
    public void setProgression(Progression progression) {
        this.acolyte = (Acolyte) progression;
        super.showAcolyteCard();
        setupSpinners();
        super.setProgression(progression);

        craft = createCheckbox();
        craft.setSelected(progression.isCraft());
        gridPane.add(craft, 0, 0);
        GridPane.setHalignment(craft, HPos.CENTER);
        GridPane.setValignment(craft, VPos.CENTER);

        addListeners();
        setColor();
        for (Spinner<Integer> spinner : allSpinners) {
            setColorCompleted(spinner, spinner.getValue() == 1);
        }
    }

    @Override
    public void addListeners() {
        name.textProperty().addListener((_, _, newValue) -> acolyte.setName(newValue));
        imageLink.textProperty().addListener((_, _, newValue) -> {
            acolyte.setImage(newValue);
            image.setImage(imageManager.getImage(newValue, 100, 100));
        });

        schema_1.valueProperty().addListener((_, _, newValue) -> {
            acolyte.setSchema_1(newValue);
            setColorCompleted(schema_1, newValue == 1);
            if (newValue < construit_1.getValue()) {
                construit_1.getValueFactory().setValue(newValue);
            }
        });
        schema_2.valueProperty().addListener((_, _, newValue) -> {
            acolyte.setSchema_2(newValue);
            setColorCompleted(schema_2, newValue == 1);
            if (newValue < construit_2.getValue()) {
                construit_2.getValueFactory().setValue(newValue);
            }
        });
        schema_3.valueProperty().addListener((_, _, newValue) -> {
            acolyte.setSchema_3(newValue);
            setColorCompleted(schema_3, newValue == 1);
            if (newValue < construit_3.getValue()) {
                construit_3.getValueFactory().setValue(newValue);
            }
        });

        construit_1.valueProperty().addListener((_, _, newValue) -> {
            acolyte.setConstruit_1(newValue);
            setColorCompleted(construit_1, newValue == 1);
            if (newValue > schema_1.getValue()) {
                schema_1.getValueFactory().setValue(newValue);
            }
        });
        construit_2.valueProperty().addListener((_, _, newValue) -> {
            acolyte.setConstruit_2(newValue);
            setColorCompleted(construit_2, newValue == 1);
            if (newValue > schema_2.getValue()) {
                schema_2.getValueFactory().setValue(newValue);
            }
        });
        construit_3.valueProperty().addListener((_, _, newValue) -> {
            acolyte.setConstruit_3(newValue);
            setColorCompleted(construit_3, newValue == 1);
            if (newValue > schema_3.getValue()) {
                schema_3.getValueFactory().setValue(newValue);
            }
        });
        construit_4.valueProperty().addListener((_, _, newValue) -> {
            acolyte.setConstruit_4(newValue);
            setColorCompleted(construit_4, newValue == 1);
        });

        craft.selectedProperty().addListener((_, _, _) -> {
            acolyte.setCraft(craft.isSelected());
            if (craft.isSelected()) {
                construit_1.getValueFactory().setValue(1);
                construit_2.getValueFactory().setValue(1);
                construit_3.getValueFactory().setValue(1);
                construit_4.getValueFactory().setValue(1);
            } else {
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
        
        for (Spinner<Integer> spinner : allSpinners) {
            SpinnerValueFactory<Integer> factory = new SpinnerValueFactory.ListSpinnerValueFactory<>(values);
            spinner.setValueFactory(factory);
            spinner.setEditable(false);
        }
    }

    private void setColor() {
        super.setColor("#3f7abd");
    }
}