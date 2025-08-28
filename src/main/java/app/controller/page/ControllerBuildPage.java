package app.controller.page;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.Collection.CollectionCollectible;
import app.Collection.CollectionProgression;
import app.Main;
import app.controller.card.prereglage.ControllerModCardDisplay;
import app.controller.card.prereglage.ControllerModDeclenchementCardDisplay;
import app.model.collectible.Mod;
import app.model.prereglage.BuildArme;
import app.model.prereglage.Prereglage;
import app.model.progression.Arme;
import app.model.progression.Descendant;
import app.util.ImageManager;
import app.util.PrereglageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ControllerBuildPage {

    private PrereglageManager prereglageManager = PrereglageManager.getInstance();
    private ImageManager imageManager = ImageManager.getInstance();
    Prereglage prereglage;
    String type;

    @FXML private ImageView imgPrincipal;
    @FXML private ComboBox<String> cbPrincipal;

    /* DESCENDANT */
    @FXML private VBox vboxDeclenchement;
    @FXML private StackPane spModDeclenchement;
    @FXML private ComboBox<String> cbModDeclenchement;
    private ControllerModDeclenchementCardDisplay controllerModDeclenchement;
    private Map<String, Integer> mapModDeclenchement;

    /* MOD */
    @FXML private VBox vboxMod;
    @FXML private GridPane gridMod;
    @FXML private ComboBox<PolariteItem> cbPolariteMod1, cbPolariteMod2, cbPolariteMod3, cbPolariteMod4, cbPolariteMod5, cbPolariteMod6, cbPolariteMod7, cbPolariteMod8, cbPolariteMod9, cbPolariteMod10, cbPolariteModDescendant, cbPolariteModSecondaire;
    private ControllerModCardDisplay modDisplay1, modDisplay2, modDisplay3, modDisplay4, modDisplay5, modDisplay6, modDisplay7, modDisplay8, modDisplay9, modDisplay10, modDisplayDescendant, modDisplaySecondaire;
    @FXML private TextField txtfieldResearch;
    
    /* ARME */
    @FXML private VBox vboxStatArme;
    // Amelioration
    @FXML private ImageView imgAmelio1, imgAmelio2, imgAmelio3, imgAmelio4, imgAmelio5;
    @FXML private ComboBox cbAmelio1, cbAmelio2, cbAmelio3, cbAmelio4, cbAmelio5;
    @FXML private Label labelAmelioStat1, labelAmelioStat2, labelAmelioStat3, labelAmelioStat4, labelAmelioStat5;
    // Attribut
    @FXML private ComboBox cbAttribut1, cbAttribut2, cbAttribut3, cbAttribut4;
    @FXML private Label labelAttributStat1, labelAttributStat2, labelAttributStat3, labelAttributStat4;
    

    @FXML
    private void initialize() throws Exception {
        vboxDeclenchement.setDisable(true);
        vboxDeclenchement.setVisible(false);

        vboxStatArme.setDisable(true);
        vboxStatArme.setVisible(false);

        // Mod
        for(ComboBox<PolariteItem> cb : new ComboBox[] {cbPolariteMod1, cbPolariteMod2, cbPolariteMod3, cbPolariteMod4, cbPolariteMod5, cbPolariteMod6, cbPolariteMod7, cbPolariteMod8, cbPolariteMod9, cbPolariteMod10}) {
            for(String key : Mod.POLARITE.get("noir").keySet()) {
                Image img = imageManager.getImage(Mod.POLARITE.get("noir").get(key), 15, 15);
                cb.getItems().add(new PolariteItem(key, img));
            }

            cb.setCellFactory(param -> new ListCell<>() {
                private final ImageView imageView = new ImageView();
                private final StackPane pane = new StackPane(imageView);
                { pane.setAlignment(Pos.CENTER); }  // centre horizontalement ET verticalement

                @Override
                protected void updateItem(PolariteItem item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(null);
                    if (empty || item == null || item.getImage() == null) {
                        setGraphic(null);
                    } else {
                        imageView.setImage(item.getImage());
                        imageView.setFitWidth(15);
                        imageView.setFitHeight(15);
                        setGraphic(pane);
                    }
                }
            });

            cb.setButtonCell(new ListCell<>() {
                private final ImageView imageView = new ImageView();
                private final StackPane pane = new StackPane(imageView);
                { pane.setAlignment(Pos.CENTER); }  // centre horizontalement ET verticalement

                @Override
                protected void updateItem(PolariteItem item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(null);
                    if (empty || item == null || item.getImage() == null) {
                        setGraphic(null);
                    } else {
                        imageView.setImage(item.getImage());
                        imageView.setFitWidth(15);
                        imageView.setFitHeight(15);
                        setGraphic(pane);
                    }
                }
            });

        }

        // Descendant
        mapModDeclenchement = new HashMap<>();
        List<String> modDeclenchementList = new ArrayList<>();
        for(int id : CollectionCollectible.getMapModDeclenchementKeys()) {
            String nomBase = CollectionCollectible.getModDeclenchementById(id).getNom();
            String nom = nomBase.replace("\n", " ");
            mapModDeclenchement.put(nom, id);
            modDeclenchementList.add(nom);
        }
        Collections.sort(modDeclenchementList);
        cbModDeclenchement.getItems().addAll(modDeclenchementList);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/card/prereglage/modDeclenchementCardDisplay.fxml"));
        Pane cardPane = loader.load();
        controllerModDeclenchement = loader.getController();
        controllerModDeclenchement.setModVide();
        cardPane.setScaleX(1.12);
        cardPane.setScaleY(1.12);
        spModDeclenchement.getChildren().add(cardPane);

        // Arme
        cbAttribut1.getItems().addAll(Arme.ATTRIBUT.keySet());
        cbAttribut2.getItems().addAll(Arme.ATTRIBUT.keySet());
        cbAttribut3.getItems().addAll(Arme.ATTRIBUT.keySet());
        cbAttribut4.getItems().addAll(Arme.ATTRIBUT.keySet());
    }

    public void setDescendant(Prereglage prereglage) throws IOException {
        this.prereglage = prereglage;
        type = "descendant";

        vboxDeclenchement.setDisable(false);
        vboxDeclenchement.setVisible(true);

        List<String> descendantList = new ArrayList<>();
        for(int id : CollectionProgression.getMapDescendantKeys()) {
            descendantList.add(CollectionProgression.getDescendantById(id).getName());
        }
        Collections.sort(descendantList);
        cbPrincipal.getItems().addAll(descendantList);

        if (prereglage.getIdDescendant() != null) {
            Descendant descendant = CollectionProgression.getDescendantById(prereglage.getIdDescendant());
            cbPrincipal.setValue(descendant.getName());
            imgPrincipal.setImage(imageManager.getImage(descendant.getImage(), 250, 250));
        }

        if(prereglage.getBuildDescendant().getIdMod1() != null) {
            modDisplay1 = createModDisplay(prereglage.getBuildDescendant().getIdMod1(), 0, 0);
        }
        if(prereglage.getBuildDescendant().getIdMod2() != null) {
            modDisplay2 = createModDisplay(prereglage.getBuildDescendant().getIdMod2(), 1, 0);
        }
        if(prereglage.getBuildDescendant().getIdMod3() != null) {
            modDisplay3 = createModDisplay(prereglage.getBuildDescendant().getIdMod3(), 2, 0);
        }
        if(prereglage.getBuildDescendant().getIdMod4() != null) {
            modDisplay4 = createModDisplay(prereglage.getBuildDescendant().getIdMod4(), 3, 0);
        }
        if(prereglage.getBuildDescendant().getIdMod5() != null) {
            modDisplay5 = createModDisplay(prereglage.getBuildDescendant().getIdMod5(), 4, 0);
        }
        if(prereglage.getBuildDescendant().getIdMod6() != null) {
            modDisplay6 = createModDisplay(prereglage.getBuildDescendant().getIdMod6(), 0, 1);
        }
        if(prereglage.getBuildDescendant().getIdMod7() != null) {
            modDisplay7 = createModDisplay(prereglage.getBuildDescendant().getIdMod7(), 1, 1);
        }
        if(prereglage.getBuildDescendant().getIdMod8() != null) {
            modDisplay8 = createModDisplay(prereglage.getBuildDescendant().getIdMod8(), 2, 1);
        }
        if(prereglage.getBuildDescendant().getIdMod9() != null) {
            modDisplay9 = createModDisplay(prereglage.getBuildDescendant().getIdMod9(), 3, 1);
        }
        if(prereglage.getBuildDescendant().getIdMod10() != null) {
            modDisplay10 = createModDisplay(prereglage.getBuildDescendant().getIdMod10(), 4, 1);
        }

        Integer idModDeclenchement = prereglage.getBuildDescendant().getIdModDeclenchement();
        if(idModDeclenchement != null) {
            cbModDeclenchement.setValue(CollectionCollectible.getModDeclenchementById(idModDeclenchement).getNom().replace("\n", " "));
            controllerModDeclenchement.setMod(CollectionCollectible.getModDeclenchementById(idModDeclenchement));
        }
        
        vboxMod.getChildren().addAll(prereglageManager.getHBoxMod("Mod Descendant"));

        addListeners();

        cbPolariteMod1.setValue(PolariteItem.getItemByPolarite(prereglage.getBuildDescendant().getPolariteMod1()));
        cbPolariteMod2.setValue(PolariteItem.getItemByPolarite(prereglage.getBuildDescendant().getPolariteMod2()));
        cbPolariteMod3.setValue(PolariteItem.getItemByPolarite(prereglage.getBuildDescendant().getPolariteMod3()));
        cbPolariteMod4.setValue(PolariteItem.getItemByPolarite(prereglage.getBuildDescendant().getPolariteMod4()));
        cbPolariteMod5.setValue(PolariteItem.getItemByPolarite(prereglage.getBuildDescendant().getPolariteMod5()));
        cbPolariteMod6.setValue(PolariteItem.getItemByPolarite(prereglage.getBuildDescendant().getPolariteMod6()));
        cbPolariteMod7.setValue(PolariteItem.getItemByPolarite(prereglage.getBuildDescendant().getPolariteMod7()));
        cbPolariteMod8.setValue(PolariteItem.getItemByPolarite(prereglage.getBuildDescendant().getPolariteMod8()));
        cbPolariteMod9.setValue(PolariteItem.getItemByPolarite(prereglage.getBuildDescendant().getPolariteMod9()));
        cbPolariteMod10.setValue(PolariteItem.getItemByPolarite(prereglage.getBuildDescendant().getPolariteMod10()));
    }
    
    public void setArme(Prereglage prereglage, int numArme) {
        this.prereglage = prereglage;

        vboxStatArme.setDisable(false);
        vboxStatArme.setVisible(true);

        List<String> armeList = new ArrayList<>();
        for(int id : CollectionProgression.getMapArmeKeys()) {
            armeList.add(CollectionProgression.getArmeById(id).getName());
        }
        Collections.sort(armeList);
        cbPrincipal.getItems().addAll(armeList);

        Arme arme = null;
        BuildArme buildArme = null;
        switch (numArme) {
            case 1 :
                if (prereglage.getIdArme1() != null) {
                    arme = CollectionProgression.getArmeById(prereglage.getIdArme1());
                    buildArme = prereglage.getBuildArme1();
                    type = "arme1";
                }
                break;
            case 2 :
                if (prereglage.getIdArme2() != null) {
                    arme = CollectionProgression.getArmeById(prereglage.getIdArme2());
                    buildArme = prereglage.getBuildArme2();
                    type = "arme2";
                }
                break;
            case 3 :
                if (prereglage.getIdArme3() != null) {
                    arme = CollectionProgression.getArmeById(prereglage.getIdArme3());
                    buildArme = prereglage.getBuildArme3();
                    type = "arme3";
                }
                break;
        }
        if (arme != null) {
            cbPrincipal.setValue(arme.getName());
            imgPrincipal.setImage(imageManager.getImage(arme.getImage(), 250, 250));

            if(!arme.getAmelioration1().isEmpty()) {
                imgAmelio1.setImage(imageManager.getImage(Arme.AMELIORATION_IMG.get(arme.getAmelioration1()), 25, 25));
                cbAmelio1.getItems().addAll(Arme.AMELIORATION.get(arme.getAmelioration1()).keySet());
                if(!buildArme.getAmelio1().isEmpty()) {
                    cbAmelio1.setValue(buildArme.getAmelio1());
                    labelAmelioStat1.setText(Arme.AMELIORATION.get(arme.getAmelioration1()).get(buildArme.getAmelio1()));
                }
            }
            if(!arme.getAmelioration2().isEmpty()) {
                imgAmelio2.setImage(imageManager.getImage(Arme.AMELIORATION_IMG.get(arme.getAmelioration2()), 25, 25));
                cbAmelio2.getItems().addAll(Arme.AMELIORATION.get(arme.getAmelioration2()).keySet());
                if(!buildArme.getAmelio2().isEmpty()) {
                    cbAmelio2.setValue(buildArme.getAmelio2());
                    labelAmelioStat2.setText(Arme.AMELIORATION.get(arme.getAmelioration2()).get(buildArme.getAmelio2()));
                }
            }
            if(!arme.getAmelioration3().isEmpty()) {
                imgAmelio3.setImage(imageManager.getImage(Arme.AMELIORATION_IMG.get(arme.getAmelioration3()), 25, 25));
                cbAmelio3.getItems().addAll(Arme.AMELIORATION.get(arme.getAmelioration3()).keySet());
                if(!buildArme.getAmelio3().isEmpty()) {
                    cbAmelio3.setValue(buildArme.getAmelio3());
                    labelAmelioStat3.setText(Arme.AMELIORATION.get(arme.getAmelioration3()).get(buildArme.getAmelio3()));
                }
            }
            if(!arme.getAmelioration4().isEmpty()) {
                imgAmelio4.setImage(imageManager.getImage(Arme.AMELIORATION_IMG.get(arme.getAmelioration4()), 25, 25));
                cbAmelio4.getItems().addAll(Arme.AMELIORATION.get(arme.getAmelioration4()).keySet());
                if(!buildArme.getAmelio4().isEmpty()) {
                    cbAmelio4.setValue(buildArme.getAmelio4());
                    labelAmelioStat4.setText(Arme.AMELIORATION.get(arme.getAmelioration4()).get(buildArme.getAmelio4()));
                }
            }
            if(!arme.getAmelioration5().isEmpty()) {
                imgAmelio5.setImage(imageManager.getImage(Arme.AMELIORATION_IMG.get(arme.getAmelioration5()), 25, 25));
                cbAmelio5.getItems().addAll(Arme.AMELIORATION.get(arme.getAmelioration5()).keySet());
                if(!buildArme.getAmelio5().isEmpty()) {
                    cbAmelio5.setValue(buildArme.getAmelio5());
                    labelAmelioStat5.setText(Arme.AMELIORATION.get(arme.getAmelioration5()).get(buildArme.getAmelio5()));
                }
            }

            if(!buildArme.getAttribut1().isEmpty()) {
                cbAttribut1.setValue(buildArme.getAttribut1());
                labelAttributStat1.setText(Arme.ATTRIBUT.get(buildArme.getAttribut1()));
            }
            if(!buildArme.getAttribut2().isEmpty()) {
                cbAttribut2.setValue(buildArme.getAttribut2());
                labelAttributStat2.setText(Arme.ATTRIBUT.get(buildArme.getAttribut2()));
            }
            if(!buildArme.getAttribut3().isEmpty()) {
                cbAttribut3.setValue(buildArme.getAttribut3());
                labelAttributStat3.setText(Arme.ATTRIBUT.get(buildArme.getAttribut3()));
            }
            if(!buildArme.getAttribut4().isEmpty()) {
                cbAttribut4.setValue(buildArme.getAttribut4());
                labelAttributStat4.setText(Arme.ATTRIBUT.get(buildArme.getAttribut4()));
            }

            cbPolariteMod1.setValue(PolariteItem.getItemByPolarite(buildArme.getPolariteMod1()));
            cbPolariteMod2.setValue(PolariteItem.getItemByPolarite(buildArme.getPolariteMod2()));
            cbPolariteMod3.setValue(PolariteItem.getItemByPolarite(buildArme.getPolariteMod3()));
            cbPolariteMod4.setValue(PolariteItem.getItemByPolarite(buildArme.getPolariteMod4()));
            cbPolariteMod5.setValue(PolariteItem.getItemByPolarite(buildArme.getPolariteMod5()));
            cbPolariteMod6.setValue(PolariteItem.getItemByPolarite(buildArme.getPolariteMod6()));
            cbPolariteMod7.setValue(PolariteItem.getItemByPolarite(buildArme.getPolariteMod7()));
            cbPolariteMod8.setValue(PolariteItem.getItemByPolarite(buildArme.getPolariteMod8()));
            cbPolariteMod9.setValue(PolariteItem.getItemByPolarite(buildArme.getPolariteMod9()));
            cbPolariteMod10.setValue(PolariteItem.getItemByPolarite(buildArme.getPolariteMod10()));

            vboxMod.getChildren().addAll(prereglageManager.getHBoxMod(Mod.TYPE_MOD_POUR_ARME.get(arme.getTypeArme())));
        }
        
        addListeners();
    }

    private void addListeners() {
        cbPolariteMod1.valueProperty().addListener((_, _, newPolariteItem) -> {
            if(modDisplay1 != null) {
                if(newPolariteItem.getPolarite().equals(modDisplay1.getMod().getPolarite())) {
                    modDisplay1.setPolariteVert();
                } else {
                    modDisplay1.setPolariteBlanc();
                }
            }
        });
        cbPolariteMod2.valueProperty().addListener((_, _, newPolariteItem) -> {
            if(modDisplay2 != null) {
                if(newPolariteItem.getPolarite().equals(modDisplay2.getMod().getPolarite())) {
                    modDisplay2.setPolariteVert();
                } else {
                    modDisplay2.setPolariteBlanc();
                }
            }
        });
        cbPolariteMod3.valueProperty().addListener((_, _, newPolariteItem) -> {
            if(modDisplay3 != null) {
                if(newPolariteItem.getPolarite().equals(modDisplay3.getMod().getPolarite())) {
                    modDisplay3.setPolariteVert();
                } else {
                    modDisplay3.setPolariteBlanc();
                }
            }
        });
        cbPolariteMod4.valueProperty().addListener((_, _, newPolariteItem) -> {
            if(modDisplay4 != null) {
                if(newPolariteItem.getPolarite().equals(modDisplay4.getMod().getPolarite())) {
                    modDisplay4.setPolariteVert();
                } else {
                    modDisplay4.setPolariteBlanc();
                }
            }
        });
        cbPolariteMod5.valueProperty().addListener((_, _, newPolariteItem) -> {
            if(modDisplay5 != null) {
                if(newPolariteItem.getPolarite().equals(modDisplay5.getMod().getPolarite())) {
                    modDisplay5.setPolariteVert();
                } else {
                    modDisplay5.setPolariteBlanc();
                }
            }
        });
        cbPolariteMod6.valueProperty().addListener((_, _, newPolariteItem) -> {
            if(modDisplay6 != null) {
                if(newPolariteItem.getPolarite().equals(modDisplay6.getMod().getPolarite())) {
                    modDisplay6.setPolariteVert();
                } else {
                    modDisplay6.setPolariteBlanc();
                }
            }
        });
        cbPolariteMod7.valueProperty().addListener((_, _, newPolariteItem) -> {
            if(modDisplay7 != null) {
                if(newPolariteItem.getPolarite().equals(modDisplay7.getMod().getPolarite())) {
                    modDisplay7.setPolariteVert();
                } else {
                    modDisplay7.setPolariteBlanc();
                }
            }
        });
        cbPolariteMod8.valueProperty().addListener((_, _, newPolariteItem) -> {
            if(modDisplay8 != null) {
                if(newPolariteItem.getPolarite().equals(modDisplay8.getMod().getPolarite())) {
                    modDisplay8.setPolariteVert();
                } else {
                    modDisplay8.setPolariteBlanc();
                }
            }
        });
        cbPolariteMod9.valueProperty().addListener((_, _, newPolariteItem) -> {
            if(modDisplay9 != null) {
                if(newPolariteItem.getPolarite().equals(modDisplay9.getMod().getPolarite())) {
                    modDisplay9.setPolariteVert();
                } else {
                    modDisplay9.setPolariteBlanc();
                }
            }
        });
        cbPolariteMod10.valueProperty().addListener((_, _, newPolariteItem) -> {
            if(modDisplay10 != null) {
                if(newPolariteItem.getPolarite().equals(modDisplay10.getMod().getPolarite())) {
                    modDisplay10.setPolariteVert();
                } else {
                    modDisplay10.setPolariteBlanc();
                }
            }
        });

        // Descendant
        if(type.equals("descendant")) {
            cbPrincipal.valueProperty().addListener((_, _, newVal) -> {
                Descendant descendant = CollectionProgression.getDescendantByName(newVal);
                prereglage.setIdDescendant(descendant.getIdDescendant());
                imgPrincipal.setImage(imageManager.getImage(descendant.getImage(), 250, 250));
            });

            cbModDeclenchement.valueProperty().addListener((_, _, newVal) -> {
                prereglage.getBuildDescendant().setIdModDeclenchement(mapModDeclenchement.get(newVal));
                controllerModDeclenchement.setMod(CollectionCollectible.getModDeclenchementById(mapModDeclenchement.get(newVal)));
            });
        }

        // Arme 1
        if(type.equals("arme1")) {
            cbPrincipal.valueProperty().addListener((_, _, newVal) -> {
                Arme arme = CollectionProgression.getArmeByName(newVal);
                prereglage.setIdArme1(arme.getIdArme());
                imgPrincipal.setImage(imageManager.getImage(arme.getImage(), 250, 250));
            });
        }

        // Arme 2
        if(type.equals("arme2")) {
            cbPrincipal.valueProperty().addListener((_, _, newVal) -> {
                Arme arme = CollectionProgression.getArmeByName(newVal);
                prereglage.setIdArme2(arme.getIdArme());
                imgPrincipal.setImage(imageManager.getImage(arme.getImage(), 250, 250));
            });
        }

        // Arme 3
        if(type.equals("arme3")) {
            cbPrincipal.valueProperty().addListener((_, _, newVal) -> {
                Arme arme = CollectionProgression.getArmeByName(newVal);
                prereglage.setIdArme3(arme.getIdArme());
                imgPrincipal.setImage(imageManager.getImage(arme.getImage(), 250, 250));
            });
        }
    }

    @FXML
    void retour(ActionEvent event) throws IOException {
        Main.switchScene("prereglageModifyPage.fxml");
        ControllerPrereglageModifyPage controller = (ControllerPrereglageModifyPage) Main.getCurrentController();
        controller.setPrereglage(prereglage);
    }

    @FXML
    void delete(ActionEvent event) {
        // TODO: fct delete
    }

    @FXML
    void recherche(ActionEvent event) throws IOException {
        String typeMod = null;
        switch (type) {
            case "descendant" -> typeMod = "Mod Descendant";
            case "arme1" -> typeMod = CollectionProgression.getArmeById(prereglage.getIdArme1()).getTypeArme();
            case "arme2" -> typeMod = CollectionProgression.getArmeById(prereglage.getIdArme2()).getTypeArme();
            case "arme3" -> typeMod = CollectionProgression.getArmeById(prereglage.getIdArme3()).getTypeArme();
        }

        vboxMod.getChildren().clear();
        if(!txtfieldResearch.getText().isEmpty()) {
            vboxMod.getChildren().addAll(prereglageManager.getHBoxRechercheModCard(typeMod, txtfieldResearch.getText()));
        }
        else {
            vboxMod.getChildren().addAll(prereglageManager.getHBoxMod(typeMod));
        }
    }

    private ControllerModCardDisplay createModDisplay(int idMod, int col, int row) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/card/prereglage/modCardDisplay.fxml"));
        Pane cardPane = loader.load();
        cardPane.setScaleX(0.75);
        cardPane.setScaleY(0.75);

        ControllerModCardDisplay controller = loader.getController();
        controller.setMod(CollectionCollectible.getModById(idMod));

        gridMod.add(cardPane, col, row);
        GridPane.setHalignment(cardPane, HPos.CENTER);
        GridPane.setValignment(cardPane, VPos.CENTER);

        return controller;
    }


}

class PolariteItem {
    private final String polarite;
    private final Image image;
    private static HashMap<String, PolariteItem> hashMap = new HashMap<>();

    public PolariteItem(String polarite, Image image) {
        this.polarite = polarite;
        this.image = image;
        hashMap.put(polarite, this);
    }

    public String getPolarite() {
        return polarite;
    }

    public Image getImage() {
        return image;
    }

    public static PolariteItem getItemByPolarite(String polarite) {
        return hashMap.get(polarite);
    }

    @Override
    public String toString() {
        return "";
    }
}