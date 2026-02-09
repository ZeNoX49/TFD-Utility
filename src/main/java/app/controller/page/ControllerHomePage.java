package app.controller.page;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import app.Main;
import app.controller.page.list.ControllerListPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class ControllerHomePage {

    private final static List<String> LIST_PROGRESSION = new ArrayList<>();
    static {
        LIST_PROGRESSION.add("Descendants");
        LIST_PROGRESSION.add("Armes");
        LIST_PROGRESSION.add("Acolyte");
        LIST_PROGRESSION.add("Véhicule");
    }

    // private final static List<String> LIST_COLLECTIBLE = new ArrayList<>();
    // static {
    //     LIST_COLLECTIBLE.add("Réacteurs");
    //     LIST_COLLECTIBLE.add("Composants Externes");        
    //     LIST_COLLECTIBLE.add("Mods Archéoniques");
    //     LIST_COLLECTIBLE.add("Mods Déclenchements");
    //     for (String modType : Mod.TYPE_MOD) {
    //         LIST_COLLECTIBLE.add(modType);
    //     }
    // }

    @FXML private ComboBox<String> cbProgression, cbCollectible, cbMode;
    @FXML private TextArea areaLoad, areaError;

    @FXML
    private void initialize() {
        cbMode.getItems().add("Utilisateur");
        cbMode.getItems().add("Administrateur");
        cbMode.setValue(Main.devMode ? "Administrateur" : "Utilisateur");
        cbMode.valueProperty().addListener((_, _, newMode) -> {
            Main.devMode = newMode.equals("Administrateur");
        });

        cbProgression.getItems().addAll(LIST_PROGRESSION);
        cbProgression.setValue("Armes");

        // cbCollectible.getItems().addAll(LIST_COLLECTIBLE);
        // cbCollectible.setValue("Composants Externes");
    }

    @FXML
    void goToProgression(ActionEvent event) throws IOException {
        ControllerListPage.type = "progression";
        ControllerListPage.type_actif = cbProgression.getValue();
        Main.switchScene("listPage.fxml");
    }

    @FXML
    void goToCollectible(ActionEvent event) throws IOException {
        // ControllerListPage.type = "collectible";
        // ControllerListPage.type_actif = cbCollectible.getValue();
        // Main.switchScene("listPage.fxml");
    }

    @FXML
    void goToPrereglage(ActionEvent event) {
        // ControllerListPage.type = "prereglage";
        // Main.switchScene("listPage.fxml");
    }

    public void addLoad(String text) {
        if(areaLoad.getText().equals("")) { areaLoad.setText(text); }
        else { areaLoad.setText(areaLoad.getText() + "\n" + text); }
    }

    public void addErreur(String text) {
        if(areaError.getText().equals("")) { areaError.setText(text); }
        else { areaError.setText(areaError.getText() + "\n" + text); }
    }

}