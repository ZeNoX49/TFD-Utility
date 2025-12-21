package app.controller.card.progression;

import java.util.List;

import app.model.progression.Arme;
import app.model.progression.Progression;
import app.util.combobox_item.AmeliorationArmeItem;
import app.util.combobox_item.ComboboxItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class ControllerProgressionCardArme extends ControllerProgressionCard {
    private Arme arme;
    private Spinner<Integer> nbCraft = createSpinnerArme();
    private ComboBox<AmeliorationArmeItem> typeAmelio1, typeAmelio2, typeAmelio3, typeAmelio4, typeAmelio5;

    @Override
    public void setProgression(Progression progression) {
        this.arme = (Arme) progression;
        super.showArmeCard();
        setupSpinners();
        super.setProgression(progression);

        typeArme.getItems().addAll(Arme.TYPE_ARME);
        typeArme.setValue(arme.getTypeArme());
        gridPane.add(nbCraft, 0, 0);
        nbCraft.getValueFactory().setValue(arme.getNbCraft());
        setColorCompleted(nbCraft, arme.getNbCraft() == 5);

        typeAmelio1 = this.createCombobox(76);
        typeAmelio2 = this.createCombobox(76);
        typeAmelio3 = this.createCombobox(76);
        typeAmelio4 = this.createCombobox(76);
        typeAmelio5 = this.createCombobox(76);

        ComboboxItem factory = new ComboboxItem();
        List<ComboBox<AmeliorationArmeItem>> amelios = List.of(typeAmelio1, typeAmelio2, typeAmelio3, typeAmelio4, typeAmelio5);
        for (ComboBox<AmeliorationArmeItem> combo : amelios) {
            factory.setupComboBox(combo, 17);
            combo.getItems().addAll(AmeliorationArmeItem.getItems());
        }
        hboxSupp.getChildren().addAll(typeAmelio1, typeAmelio2, typeAmelio3, typeAmelio4, typeAmelio5);
        setupCombobox();

        addListeners();
        setColor();
        for (Spinner<Integer> spinner : allSpinners) {
            setColorCompleted(spinner, spinner.getValue() == 5);
        }
    }

    @Override
    public void addListeners() {
        name.textProperty().addListener((_, _, newValue) -> arme.setName(newValue));
        imageLink.textProperty().addListener((_, _, newValue) -> {
            arme.setImage(newValue);
            image.setImage(imageManager.getImage(newValue, 100, 100));
        });

        schema_1.valueProperty().addListener((_, _, newValue) -> {
            arme.setSchema_1(newValue);
            setColorCompleted(schema_1, newValue == 5);
            if (newValue < construit_1.getValue()) {
                construit_1.getValueFactory().setValue(newValue);
            }
        });
        schema_2.valueProperty().addListener((_, _, newValue) -> {
            arme.setSchema_2(newValue);
            setColorCompleted(schema_2, newValue == 5);
            if (newValue < construit_2.getValue()) {
                construit_2.getValueFactory().setValue(newValue);
            }
        });
        schema_3.valueProperty().addListener((_, _, newValue) -> {
            arme.setSchema_3(newValue);
            setColorCompleted(schema_3, newValue == 5);
            if (newValue < construit_3.getValue()) {
                construit_3.getValueFactory().setValue(newValue);
            }
        });

        construit_1.valueProperty().addListener((_, _, newValue) -> {
            arme.setConstruit_1(newValue);
            setColorCompleted(construit_1, newValue == 5);
            if (newValue > schema_1.getValue()) {
                schema_1.getValueFactory().setValue(newValue);
            }
        });
        construit_2.valueProperty().addListener((_, _, newValue) -> {
            arme.setConstruit_2(newValue);
            setColorCompleted(construit_2, newValue == 5);
            if (newValue > schema_2.getValue()) {
                schema_2.getValueFactory().setValue(newValue);
            }
        });
        construit_3.valueProperty().addListener((_, _, newValue) -> {
            arme.setConstruit_3(newValue);
            setColorCompleted(construit_3, newValue == 5);
            if (newValue > schema_3.getValue()) {
                schema_3.getValueFactory().setValue(newValue);
            }
        });
        construit_4.valueProperty().addListener((_, _, newValue) -> {
            arme.setConstruit_4(newValue);
            setColorCompleted(construit_4, newValue == 5);
        });

        nbCraft.valueProperty().addListener((_, _, newValue) -> {
            arme.setNbCraft(newValue);
            setColorCompleted(nbCraft, newValue == 5);
            arme.setCraft(newValue == 5);
            if (newValue > construit_1.getValue()) construit_1.getValueFactory().setValue(newValue);
            if (newValue > construit_2.getValue()) construit_2.getValueFactory().setValue(newValue);
            if (newValue > construit_3.getValue()) construit_3.getValueFactory().setValue(newValue);
            if (newValue > construit_4.getValue()) construit_4.getValueFactory().setValue(newValue);
        });
        
        typeArme.valueProperty().addListener((_, _, newVal) -> arme.setTypeArme(newVal));
        typeAmelio1.valueProperty().addListener((_, _, newVal) -> arme.setAmelioration1(newVal.getName()));
        typeAmelio2.valueProperty().addListener((_, _, newVal) -> arme.setAmelioration2(newVal.getName()));
        typeAmelio3.valueProperty().addListener((_, _, newVal) -> arme.setAmelioration3(newVal.getName()));
        typeAmelio4.valueProperty().addListener((_, _, newVal) -> arme.setAmelioration4(newVal.getName()));
        typeAmelio5.valueProperty().addListener((_, _, newVal) -> arme.setAmelioration5(newVal.getName()));
    }
    
    @Override
    public void setupSpinners() {
        ObservableList<Integer> values = FXCollections.observableArrayList(0, 1, 2, 3, 4, 5);
        nbCraft.setValueFactory(new SpinnerValueFactory.ListSpinnerValueFactory<>(values));
        nbCraft.setEditable(false);
        
        for (Spinner<Integer> spinner : allSpinners) {
            SpinnerValueFactory<Integer> factory = new SpinnerValueFactory.ListSpinnerValueFactory<>(values);
            spinner.setValueFactory(factory);
            spinner.setEditable(false);
        }
    }

    private void setupCombobox() {
        typeArme.setValue(arme.getTypeArme());
        typeAmelio1.setValue(AmeliorationArmeItem.getAmeliorationByKey(arme.getAmelioration1()));
        typeAmelio2.setValue(AmeliorationArmeItem.getAmeliorationByKey(arme.getAmelioration2()));
        typeAmelio3.setValue(AmeliorationArmeItem.getAmeliorationByKey(arme.getAmelioration3()));
        typeAmelio4.setValue(AmeliorationArmeItem.getAmeliorationByKey(arme.getAmelioration4()));
        typeAmelio5.setValue(AmeliorationArmeItem.getAmeliorationByKey(arme.getAmelioration5()));
    }

    private void setColor() {
        super.setColor("#d8c519");
    }
}