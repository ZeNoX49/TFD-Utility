package app.controller.card.progression;

import java.util.List;

import app.model.progression.Descendant;
import app.model.progression.Progression;
import app.util.combobox_item.ComboboxItem;
import app.util.combobox_item.PolariteItem;
import app.util.manager.ImageManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.GridPane;

public class ControllerProgressionCardDescendant extends ControllerProgressionCard {
    private static ImageManager imageManager = ImageManager.getInstance();

    private Descendant descendant;

    private CheckBox craft;
    private ComboBox<PolariteItem> typeArcheo1, typeArcheo2, typeArcheo3, typeArcheo4;

    @Override
    public void setProgression(Progression progression) {
        this.descendant = (Descendant)progression;
        super.showDescendantCard();
        setupSpinners();

        super.setProgression(progression);

        craft = createCheckbox();
        craft.setSelected(progression.isCraft());
        super.gridPane.add(craft, 0, 0);
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

        addListeners();
        setColor();
        for(Spinner<Integer> spinner : allSpinners) {
            setColorCompleted(spinner);
        }
    }

    @Override
    public void addListeners() {
        name.textProperty().addListener((_, _, newValue) -> descendant.setName(newValue));
        imageLink.textProperty().addListener((_, _, newValue) -> {
            descendant.setImage(newValue);
            image.setImage(imageManager.getImage(newValue, 100, 100));
        });

        schema_1.valueProperty().addListener((_, _, newValue) -> {
            descendant.setSchema_1(newValue);
            setColorCompleted(schema_1);
            if (newValue < construit_1.getValue()) {
                construit_1.getValueFactory().setValue(newValue);
            }
        });
        schema_2.valueProperty().addListener((_, _, newValue) -> {
            descendant.setSchema_2(newValue);
            setColorCompleted(schema_2);
            if (newValue < construit_2.getValue()) {
                construit_2.getValueFactory().setValue(newValue);
            }
        });
        schema_3.valueProperty().addListener((_, _, newValue) -> {
            descendant.setSchema_3(newValue);
            setColorCompleted(schema_3);
            if (newValue < construit_3.getValue()) {
                construit_3.getValueFactory().setValue(newValue);
            }
        });

        construit_1.valueProperty().addListener((_, _, newValue) -> {
            descendant.setConstruit_1(newValue);
            setColorCompleted(construit_1);
            if(newValue > schema_1.getValue()) {
                schema_1.getValueFactory().setValue(newValue);
            }
        });
        construit_2.valueProperty().addListener((_, _, newValue) -> {
            descendant.setConstruit_2(newValue);
            setColorCompleted(construit_2);
            if (newValue > schema_2.getValue()) {
                schema_2.getValueFactory().setValue(newValue);
            }
        });
        construit_3.valueProperty().addListener((_, _, newValue) -> {
            descendant.setConstruit_3(newValue);
            setColorCompleted(construit_3);
            if (newValue > schema_3.getValue()) {
                schema_3.getValueFactory().setValue(newValue);
            }
        });
        construit_4.valueProperty().addListener((_, _, newValue) ->  {
            descendant.setConstruit_4(newValue);
            setColorCompleted(construit_4);
        });

        craft.selectedProperty().addListener((_, _, _) -> {
            descendant.setCraft(craft.isSelected());
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
        
        typeArcheo1.valueProperty().addListener((_, _, newVal) ->  descendant.setTypeModArcheonique1(newVal.getPolarite()) );
        typeArcheo2.valueProperty().addListener((_, _, newVal) ->  descendant.setTypeModArcheonique2(newVal.getPolarite()) );
        typeArcheo3.valueProperty().addListener((_, _, newVal) ->  descendant.setTypeModArcheonique3(newVal.getPolarite()) );
        typeArcheo4.valueProperty().addListener((_, _, newVal) ->  descendant.setTypeModArcheonique4(newVal.getPolarite()) );
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

    private void setupCombobox() {
        typeArcheo1.setValue(PolariteItem.getItemByPolarite(descendant.getTypeModArcheonique1()));
        typeArcheo2.setValue(PolariteItem.getItemByPolarite(descendant.getTypeModArcheonique2()));
        typeArcheo3.setValue(PolariteItem.getItemByPolarite(descendant.getTypeModArcheonique3()));
        typeArcheo4.setValue(PolariteItem.getItemByPolarite(descendant.getTypeModArcheonique4()));
    }

    private void setColor() {
        super.setColor(this.descendant.getName().contains("Ultime") ? "#d8c519" : "#3f7abd");
    }

    private void setColorCompleted(Spinner<Integer> spinner) {
        super.setColorCompleted(spinner, (spinner.getValue() == 1));
    }
}