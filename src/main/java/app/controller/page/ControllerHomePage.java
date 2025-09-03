package app.controller.page;

import java.io.IOException;

import app.Main;
import app.util.manager.CollectibleManager;
import app.util.manager.PrereglageManager;
import app.util.manager.ProgressionManager;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.util.Duration;

public class ControllerHomePage {

    @FXML private TextArea areaLoad, areaError;
    @FXML private Button btn_progression, btn_collectible, btn_prereglage;
    @FXML private Button btn_loadProgression, btn_loadCollectible, btn_loadPrereglage;

    @FXML
    void loadProgression(ActionEvent event) {
        addLoad("\nChargement de la progression en cours");
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(_ -> {
            ProgressionManager baseManager = ProgressionManager.getInstance();
            try {
                baseManager.initialize();
            } catch (IOException e) {
                e.printStackTrace();
            }
            btn_progression.setDisable(false);
        });
        pause.play();
        btn_loadProgression.setDisable(true);
    }

    @FXML
    void loadCollectible(ActionEvent event) {
        addLoad("\nChargement des collectibles en cours");
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(_ -> {
            CollectibleManager collectibleManager = CollectibleManager.getInstance();
            try {
                collectibleManager.initialize();
            } catch (IOException e) {
                e.printStackTrace();
            }
            btn_collectible.setDisable(false);
        });
        pause.play();
        btn_loadCollectible.setDisable(true);
    }

    @FXML
    void loadPrereglage(ActionEvent event) {
        addLoad("\nChargement des préréglages en cours");
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(_ -> {
            PrereglageManager prereglageManager = PrereglageManager.getInstance();
            try {
                prereglageManager.initialize();
            } catch (IOException e) {
                e.printStackTrace();
            }
            btn_prereglage.setDisable(false);
        });
        pause.play();
        btn_loadPrereglage.setDisable(true);
    }

    @FXML
    void goToProgression(ActionEvent event) {
        ControllerListPage.type = "progression";
        ControllerListPage.type_actif = "w";
        Main.switchScene("listPage.fxml");
    }

    @FXML
    void goToCollectible(ActionEvent event) {
        ControllerListPage.type = "collectible";
        Main.switchScene("listPage.fxml");
    }

    @FXML
    void goToPrereglage(ActionEvent event) {
        ControllerListPage.type = "prereglage";
        Main.switchScene("listPage.fxml");
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