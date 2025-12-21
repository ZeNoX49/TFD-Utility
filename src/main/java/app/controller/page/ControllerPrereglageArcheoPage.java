package app.controller.page;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import app.Main;
import app.collection.CollectionCollectible;
import app.collection.CollectionProgression;
import app.controller.card.prereglage.ControllerModArcheoCardDisplay;
import app.model.collectible.Mod;
import app.model.collectible.ModArcheo;
import app.model.prereglage.BuildArcheonique;
import app.model.prereglage.GridArcheo;
import app.model.prereglage.GridArcheoManager;
import app.model.prereglage.Prereglage;
import app.model.progression.Descendant;
import app.util.manager.ImageManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ControllerPrereglageArcheoPage {
    private ImageManager imageManager = ImageManager.getInstance();

    private Prereglage prereglage;
    private BuildArcheonique buildArcheo;
    private GridArcheoManager gridArcheoManager;
    private GridPane grid;
    private int cout = 0;

    @FXML private Label labelCout;
    @FXML private StackPane spModArcheo;

    @FXML private VBox vboxArcheo1, vboxArcheo2, vboxArcheo3, vboxArcheo4;
    private Map<String, ControllerModArcheoCardDisplay> mapModArcheo = new HashMap<>();
    @FXML private ImageView polariteArcheo1, polariteArcheo2, polariteArcheo3, polariteArcheo4;
    @FXML private StackPane spArcheo1, spArcheo2, spArcheo3, spArcheo4;
    private ControllerModArcheoCardDisplay controllerModArcheo1, controllerModArcheo2, controllerModArcheo3, controllerModArcheo4;
    @FXML private ComboBox<String> cbArcheo1, cbArcheo2, cbArcheo3, cbArcheo4;

    @FXML private GridPane gridSaison;
    private int nbSaisonActivated = 0;
    private Map<Integer, Boolean> mapSaison = new HashMap<>();
    private Map<String, Boolean> mapSaisonActivated = new HashMap<>();

    @FXML
    private void initialize() throws IOException {
        labelCout.setText(String.valueOf(cout));

        vboxArcheo1.setDisable(true);
        vboxArcheo2.setDisable(true);
        vboxArcheo3.setDisable(true);
        vboxArcheo4.setDisable(true);

        spArcheo1.getChildren().add(createCardArcheoDisplay(1));
        spArcheo2.getChildren().add(createCardArcheoDisplay(2));
        spArcheo3.getChildren().add(createCardArcheoDisplay(3));
        spArcheo4.getChildren().add(createCardArcheoDisplay(4));
    }

    private Pane createCardArcheoDisplay(int index) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/card/prereglage/modArcheoCardDisplay.fxml"));
        Pane cardPane = loader.load();
        ControllerModArcheoCardDisplay controller = loader.getController();
        controller.setModArcheoVide();
        cardPane.setScaleX(1.7);
        cardPane.setScaleY(1.7);

        switch(index) {
            case 1 -> controllerModArcheo1 = controller;
            case 2 -> controllerModArcheo2 = controller;
            case 3 -> controllerModArcheo3 = controller;
            case 4 -> controllerModArcheo4 = controller;
        }
        return cardPane;
    }

    public void setPrereglage(Prereglage prereglage) {
        this.prereglage = prereglage;

        // grid et cout
        GridArcheo gridArcheo = new GridArcheo();
        buildArcheo = prereglage.getBuildArcheo();
        gridArcheoManager = new GridArcheoManager(buildArcheo, gridArcheo, 35);
        grid = gridArcheoManager.getGridPane();
        spModArcheo.getChildren().add(grid);

        gridArcheo.setDescendant(CollectionProgression.getDescendantById(prereglage.getIdDescendant()));

        for(String key : buildArcheo.getNodes()) {
            Button btn = new Button();
            btn.setPrefSize(35, 35);
            btn.setStyle("-fx-background-color: transparent");
            btn.setOnAction((_) -> onClickBuild(key) );

            int[] loc = gridArcheo.getIntFromKey(key);
            grid.add(btn, loc[0], loc[1]);
        }

        updateCout();

        // Mod archéonique
        Descendant descendant;
        descendant = CollectionProgression.getDescendantById(prereglage.getIdDescendant());

        if(buildArcheo.getNodesActivated().contains("2_0")) {
            vboxArcheo1.setDisable(false);
        }
        else if(buildArcheo.getNodesActivated().contains("18_0")) {
            vboxArcheo2.setDisable(false);
        }
        else if(buildArcheo.getNodesActivated().contains("2_20")) {
            vboxArcheo3.setDisable(false);
        }
        else if(buildArcheo.getNodesActivated().contains("18_20")) {
            vboxArcheo4.setDisable(false);
        }

        polariteArcheo1.setImage(imageManager.getImage(Mod.POLARITE.get("blanc").get(descendant.getTypeModArcheonique1()), 75, 75));
        mapModArcheo.put(descendant.getTypeModArcheonique1(), controllerModArcheo1);
        polariteArcheo2.setImage(imageManager.getImage(Mod.POLARITE.get("blanc").get(descendant.getTypeModArcheonique2()), 75, 75));
        mapModArcheo.put(descendant.getTypeModArcheonique2(), controllerModArcheo2);
        polariteArcheo3.setImage(imageManager.getImage(Mod.POLARITE.get("blanc").get(descendant.getTypeModArcheonique3()), 75, 75));
        mapModArcheo.put(descendant.getTypeModArcheonique3(), controllerModArcheo3);
        polariteArcheo4.setImage(imageManager.getImage(Mod.POLARITE.get("blanc").get(descendant.getTypeModArcheonique4()), 75, 75));
        mapModArcheo.put(descendant.getTypeModArcheonique4(), controllerModArcheo4);

        cbArcheo1.setItems(getNameModArcheo(CollectionCollectible.getModArcheoBypolarite(descendant.getTypeModArcheonique1())));
        cbArcheo2.setItems(getNameModArcheo(CollectionCollectible.getModArcheoBypolarite(descendant.getTypeModArcheonique2())));
        cbArcheo3.setItems(getNameModArcheo(CollectionCollectible.getModArcheoBypolarite(descendant.getTypeModArcheonique3())));
        cbArcheo4.setItems(getNameModArcheo(CollectionCollectible.getModArcheoBypolarite(descendant.getTypeModArcheonique4())));

        List<String> listPolarite = new ArrayList<>();
        listPolarite.add(descendant.getTypeModArcheonique1());
        listPolarite.add(descendant.getTypeModArcheonique2());
        listPolarite.add(descendant.getTypeModArcheonique3());
        listPolarite.add(descendant.getTypeModArcheonique4());

        Integer idModArcheo1 = prereglage.getIdModArcheo1();
        if(idModArcheo1 != null) {
            ModArcheo modArcheo1 = CollectionCollectible.getModArcheoById(prereglage.getIdModArcheo1());
            for(String polarite1 : listPolarite) {
                if(polarite1.equals(modArcheo1.getPolarite())) {
                    mapModArcheo.get(modArcheo1.getPolarite()).setModArcheo(modArcheo1);
                    break;
                }
            }
        }

        Integer idModArcheo2 = prereglage.getIdModArcheo2();
        if(idModArcheo2 != null) {
            ModArcheo modArcheo2 = CollectionCollectible.getModArcheoById(prereglage.getIdModArcheo2());
            for(String polarite2 : listPolarite) {
                if(polarite2.equals(modArcheo2.getPolarite())) {
                    mapModArcheo.get(modArcheo2.getPolarite()).setModArcheo(modArcheo2);
                    break;
                }
            }
        }

        // saison
        for(int y = 0; y < 5; y++) {
            int Y = y;
            mapSaison.put(Y, false);
            for(int x = 0; x < 4; x++) {
                String key = gridArcheo.getKeyFromInt(x, y);
                mapSaisonActivated.put(key, false);

                ImageView img = new ImageView(imageManager.getImage(getClass().getResource("/img/cercle.png").toExternalForm(), 50, 50));
                Button btn = new Button();
                btn.setPrefSize(50, 50);
                btn.setStyle("-fx-background-color: transparent");
                btn.setGraphic(img);
                btn.setOnAction((_) -> onClickSaison(btn, key, Y));
                gridSaison.add(btn, x, y);

                GridPane.setHalignment(btn, HPos.CENTER);
                GridPane.setValignment(btn, VPos.CENTER);

                if(prereglage.getAttributSaison().contains(key)) {
                    onClickSaison(btn, key, Y);
                }
            }
        }

        addListeners();
    }
    
    private ObservableList<String> getNameModArcheo(List<ModArcheo> modArcheoList) {
        List<String> noms = new LinkedList<>();
        for(ModArcheo mod : modArcheoList) {
            noms.add(mod.getNom());
        }
        return FXCollections.observableArrayList(noms);
    }

    private void onClickBuild(String key) {
        int cout_node = getCout(key);

        if(!buildArcheo.getNodesActivated().contains(key)) {
            if((cout + cout_node) <= 40 || cout_node == 0) {
                gridArcheoManager.activateNode(key);
                cout += cout_node;
            }
        }
        else {
            gridArcheoManager.deactivateNode(key);
            cout -= cout_node;
        }
        labelCout.setText(String.valueOf(cout));

        if(key.equals("2_0")) {
            changeStateVBox(1);
        }

        else if(key.equals("18_0")) {
            changeStateVBox(2);
        }

        else if(key.equals("2_20")) {
            changeStateVBox(3);
        }

        else if(key.equals("18_20")) {
            changeStateVBox(4);
        }
    }

    private void changeStateVBox(int index) {
        VBox vbox = null;
        ControllerModArcheoCardDisplay controller = null;
        ComboBox<String> cbArcheo = null;
        switch (index) {
            case 1 -> {
                vbox = vboxArcheo1;
                controller = controllerModArcheo1;
                cbArcheo = cbArcheo1;
            }
            case 2 -> {
                vbox = vboxArcheo2;
                controller = controllerModArcheo2;
                cbArcheo = cbArcheo2;
            }
            case 3 -> {
                vbox = vboxArcheo3;
                controller = controllerModArcheo3;
                cbArcheo = cbArcheo3;
            }
            case 4 -> {
                vbox = vboxArcheo4;
                controller = controllerModArcheo4;
                cbArcheo = cbArcheo4;
            }
        }

        Integer idModArcheo1 = prereglage.getIdModArcheo1();
        Integer idModArcheo2 = prereglage.getIdModArcheo2();

        if(vbox.isDisable()) {
            vbox.setDisable(false);
        }
        else {
            vbox.setDisable(true);
            controller.setModArcheoVide();
            cbArcheo.setValue(null);

            if(idModArcheo1 == controller.getModArcheo().getIdModArcheo()) {
                if(idModArcheo2 != null) {
                    prereglage.setIdModArcheo1(null);
                }
                else {
                    prereglage.setIdModArcheo1(idModArcheo2);
                    prereglage.setIdModArcheo2(null);
                }
            }
            else if(idModArcheo2 == controller.getModArcheo().getIdModArcheo()){
                prereglage.setIdModArcheo2(null);
            }
            else {
                Main.addTextErreur("IdModArcheo", "ControllerBuildArcheoPage", "changeStateVBox");
            }
        }
    }

    private void onClickSaison(Button btn, String key, int y) {
        if(!mapSaisonActivated.get(key)) {
            if(!mapSaison.get(y) && nbSaisonActivated < 3) {
                btn.setGraphic(new ImageView(imageManager.getImage(getClass().getResource("/img/cercleVert.png").toExternalForm(), 50, 50)));
                if(!prereglage.getAttributSaison().contains(key)) {
                    prereglage.addAttributSaison(key);
                }
                nbSaisonActivated++;
                mapSaison.put(y, true);
                mapSaisonActivated.put(key, true);
            }
        } 
        else {
            btn.setGraphic(new ImageView(imageManager.getImage(getClass().getResource("/img/cercle.png").toExternalForm(), 50, 50)));
            prereglage.removeAttributSaison(key);
            nbSaisonActivated--;
            mapSaison.put(y, false);
            mapSaisonActivated.put(key, false);
        }
    }

    private void updateCout() {
        for(String key : buildArcheo.getNodesActivated()) {
            cout += getCout(key);
        }
        labelCout.setText(String.valueOf(cout));
    }

    private int getCout(String key) {
        if(key.equals("2_0") || key.equals("18_0") || key.equals("2_20") || key.equals("18_20")) {
            return 0;
        }
        else if(key.equals("1_10") || key.equals("19_10") || key.equals("4_2") || key.equals("16_2") || key.equals("4_18") || key.equals("16_18")) {
            return 5;
        }
        return 1;
    }

    @FXML
    void retour(ActionEvent event) throws IOException {
        Main.switchScene("prereglagePage.fxml");
        ControllerPrereglagePage controller = (ControllerPrereglagePage) Main.getCurrentController();
        controller.setPrereglage(prereglage);
    }

    private void addListeners() {
        setupArcheoListener(cbArcheo1, controllerModArcheo1);
        setupArcheoListener(cbArcheo2, controllerModArcheo2);
        setupArcheoListener(cbArcheo3, controllerModArcheo3);
        setupArcheoListener(cbArcheo4, controllerModArcheo4);
    }

    private void setupArcheoListener(ComboBox<String> cb, ControllerModArcheoCardDisplay controller) {
        cb.valueProperty().addListener((_, oldValue, newValue) -> {
            if(newValue != null) {
                Integer idModArcheo1 = prereglage.getIdModArcheo1();
                Integer idModArcheo2 = prereglage.getIdModArcheo2();

                ModArcheo oldMod = CollectionCollectible.getModArcheoByName(oldValue);
                controller.setModArcheo(CollectionCollectible.getModArcheoByName(newValue));

                if(idModArcheo1 == null || idModArcheo1 == oldMod.getIdModArcheo()) {
                    prereglage.setIdModArcheo1(controller.getModArcheo().getIdModArcheo());
                }
                else if(idModArcheo2 == null || idModArcheo2 == oldMod.getIdModArcheo()) {
                    prereglage.setIdModArcheo2(controller.getModArcheo().getIdModArcheo());
                }
                else {
                    Main.addTextErreur("IdModArcheo", "ControllerBuildArcheoPage", "addListeners");
                }
            }
        });
    }

}
