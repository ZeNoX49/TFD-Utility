package app.controller.page;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import app.Collection.CollectionCollectible;
import app.Collection.CollectionProgression;
import app.Main;
import app.controller.card.prereglage.ControllerModCardDisplay;
import app.controller.card.prereglage.ControllerModDeclenchementCardDisplay;
import app.model.prereglage.BuildArme;
import app.model.prereglage.Prereglage;
import app.model.progression.Arme;
import app.model.progression.Descendant;
import app.util.combobox_item.ComboboxItem;
import app.util.combobox_item.PolariteItem;
import app.util.manager.ImageManager;
import app.util.manager.PrereglageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ControllerPrereglageBuildPage {

    private PrereglageManager prereglageManager = PrereglageManager.getInstance();
    private ImageManager imageManager = ImageManager.getInstance();
    private Prereglage prereglage;
    private String type;
    private Map<ControllerModCardDisplay, Pane> cards;

    @FXML private ImageView imgPrincipal;
    @FXML private ComboBox<String> cbPrincipal;

    /* DESCENDANT */
    @FXML private VBox vboxDeclenchement;
    @FXML private StackPane spModDeclenchement;
    @FXML private ComboBox<String> cbModDeclenchement;
    private ControllerModDeclenchementCardDisplay controllerModDeclenchement;
    private Map<String, Integer> mapModDeclenchement;

    /* MOD */
    @FXML private StackPane spMod;
    private GridPane gridMod;
    private Map<Integer, Map<Integer, StackPane>> stackpaneGrid = new HashMap<>();
    private ComboBox<PolariteItem> cbPolariteMod1, cbPolariteMod2, cbPolariteMod3, cbPolariteMod4, cbPolariteMod5, cbPolariteMod6, cbPolariteMod7, cbPolariteMod8, cbPolariteMod9, cbPolariteMod10, cbPolariteModDescendant, cbPolariteModSecondaire;
    private ControllerModCardDisplay modDisplay1, modDisplay2, modDisplay3, modDisplay4, modDisplay5, modDisplay6, modDisplay7, modDisplay8, modDisplay9, modDisplay10, modDisplayDescendant, modDisplaySecondaire;

    @FXML private VBox vboxMod;
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

        cards = new LinkedHashMap<>();

        // Mod
        cbPolariteMod1 = new ComboBox<>();
        cbPolariteMod2 = new ComboBox<>();
        cbPolariteMod3 = new ComboBox<>();
        cbPolariteMod4 = new ComboBox<>();
        cbPolariteMod5 = new ComboBox<>();
        cbPolariteMod6 = new ComboBox<>();
        cbPolariteMod7 = new ComboBox<>();
        cbPolariteMod8 = new ComboBox<>();
        cbPolariteMod9 = new ComboBox<>();
        cbPolariteMod10 = new ComboBox<>();
        cbPolariteModDescendant = new ComboBox<>();
        cbPolariteModSecondaire = new ComboBox<>();

        for(ComboBox<PolariteItem> cb : new ComboBox[] {cbPolariteMod1, cbPolariteMod2, cbPolariteMod3, cbPolariteMod4, cbPolariteMod5, cbPolariteMod6, cbPolariteMod7, cbPolariteMod8, cbPolariteMod9, cbPolariteMod10, cbPolariteModDescendant, cbPolariteModSecondaire}) {
            cb.setPrefSize(150, 25);
            ComboboxItem factory = new ComboboxItem();
            factory.setupComboBox(cb, 15); // méthode utilitaire qui configure un ComboBox existant
            cb.getItems().addAll(PolariteItem.getItems());
        }
    }

    public void setDescendant(Prereglage prereglage) throws IOException {
        this.prereglage = prereglage;
        type = "descendant";

        VBox vbox = createGridMod(6);
        spMod.getChildren().add(vbox);
        gridMod = (GridPane) vbox.getChildren().get(1);

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
            modDisplayDescendant = createModDisplay(prereglage.getBuildDescendant().getIdModDescendant(), 0, 0);
        }
        if(prereglage.getBuildDescendant().getIdMod1() != null) {
            modDisplay1 = createModDisplay(prereglage.getBuildDescendant().getIdMod1(), 1, 0);
        }
        if(prereglage.getBuildDescendant().getIdMod2() != null) {
            modDisplay2 = createModDisplay(prereglage.getBuildDescendant().getIdMod2(), 2, 0);
        }
        if(prereglage.getBuildDescendant().getIdMod3() != null) {
            modDisplay3 = createModDisplay(prereglage.getBuildDescendant().getIdMod3(), 3, 0);
        }
        if(prereglage.getBuildDescendant().getIdMod4() != null) {
            modDisplay4 = createModDisplay(prereglage.getBuildDescendant().getIdMod4(), 4, 0);
        }
        if(prereglage.getBuildDescendant().getIdMod5() != null) {
            modDisplay5 = createModDisplay(prereglage.getBuildDescendant().getIdMod5(), 5, 0);
        }

        if(prereglage.getBuildDescendant().getIdMod6() != null) {
            modDisplaySecondaire = createModDisplay(prereglage.getBuildDescendant().getIdModSecondaire(), 0, 1);
        }
        if(prereglage.getBuildDescendant().getIdMod6() != null) {
            modDisplay6 = createModDisplay(prereglage.getBuildDescendant().getIdMod6(), 1, 1);
        }
        if(prereglage.getBuildDescendant().getIdMod7() != null) {
            modDisplay7 = createModDisplay(prereglage.getBuildDescendant().getIdMod7(), 2, 1);
        }
        if(prereglage.getBuildDescendant().getIdMod8() != null) {
            modDisplay8 = createModDisplay(prereglage.getBuildDescendant().getIdMod8(), 3, 1);
        }
        if(prereglage.getBuildDescendant().getIdMod9() != null) {
            modDisplay9 = createModDisplay(prereglage.getBuildDescendant().getIdMod9(), 4, 1);
        }
        if(prereglage.getBuildDescendant().getIdMod10() != null) {
            modDisplay10 = createModDisplay(prereglage.getBuildDescendant().getIdMod10(), 5, 1);
        }

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

        Integer idModDeclenchement = prereglage.getBuildDescendant().getIdModDeclenchement();
        if(idModDeclenchement != null) {
            cbModDeclenchement.setValue(CollectionCollectible.getModDeclenchementById(idModDeclenchement).getNom().replace("\n", " "));
            controllerModDeclenchement.setMod(CollectionCollectible.getModDeclenchementById(idModDeclenchement));
        }
        
        cards = prereglageManager.getCardMod("Mod Descendant");
        vboxMod.getChildren().addAll(prereglageManager.makeBoxModCard(cards));

        addListeners();

        cbPolariteModDescendant.setValue(PolariteItem.getItemByPolarite(prereglage.getBuildDescendant().getPolariteModDescendant()));
        cbPolariteMod1.setValue(PolariteItem.getItemByPolarite(prereglage.getBuildDescendant().getPolariteMod1()));
        cbPolariteMod2.setValue(PolariteItem.getItemByPolarite(prereglage.getBuildDescendant().getPolariteMod2()));
        cbPolariteMod3.setValue(PolariteItem.getItemByPolarite(prereglage.getBuildDescendant().getPolariteMod3()));
        cbPolariteMod4.setValue(PolariteItem.getItemByPolarite(prereglage.getBuildDescendant().getPolariteMod4()));
        cbPolariteMod5.setValue(PolariteItem.getItemByPolarite(prereglage.getBuildDescendant().getPolariteMod5()));
        cbPolariteModSecondaire.setValue(PolariteItem.getItemByPolarite(prereglage.getBuildDescendant().getPolariteModSecondaire()));
        cbPolariteMod6.setValue(PolariteItem.getItemByPolarite(prereglage.getBuildDescendant().getPolariteMod6()));
        cbPolariteMod7.setValue(PolariteItem.getItemByPolarite(prereglage.getBuildDescendant().getPolariteMod7()));
        cbPolariteMod8.setValue(PolariteItem.getItemByPolarite(prereglage.getBuildDescendant().getPolariteMod8()));
        cbPolariteMod9.setValue(PolariteItem.getItemByPolarite(prereglage.getBuildDescendant().getPolariteMod9()));
        cbPolariteMod10.setValue(PolariteItem.getItemByPolarite(prereglage.getBuildDescendant().getPolariteMod10()));
    }
    
    public void setArme(Prereglage prereglage, int numArme) throws IOException {
        this.prereglage = prereglage;

        VBox vbox = createGridMod(5);
        spMod.getChildren().add(vbox);
        gridMod = (GridPane) vbox.getChildren().get(1);

        vboxStatArme.setDisable(false);
        vboxStatArme.setVisible(true);

        List<String> armeList = new ArrayList<>();
        for(int id : CollectionProgression.getMapArmeKeys()) {
            armeList.add(CollectionProgression.getArmeById(id).getName());
        }
        Collections.sort(armeList);
        cbPrincipal.getItems().addAll(armeList);

        cbAttribut1.getItems().addAll(Arme.ATTRIBUT.keySet());
        cbAttribut2.getItems().addAll(Arme.ATTRIBUT.keySet());
        cbAttribut3.getItems().addAll(Arme.ATTRIBUT.keySet());
        cbAttribut4.getItems().addAll(Arme.ATTRIBUT.keySet());

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

            // cards = prereglageManager.getCardMod(Mod.TYPE_MOD_POUR_ARME.get(arme.getTypeArme()));
            // vboxMod.getChildren().addAll(prereglageManager.makeBoxModCard(cards));
        }
        
        addListeners();
    }

    private void something(String ameliorationArme, ImageView imgView, ComboBox cbAmelio, String ameliorationBuild, Label labelAmelioStat) {
        if(!ameliorationArme.isEmpty()) {
            imgView.setImage(imageManager.getImage(Arme.AMELIORATION_IMG.get(ameliorationArme), 25, 25));
            cbAmelio.getItems().addAll(Arme.AMELIORATION.get(ameliorationArme).keySet());
            if(!ameliorationBuild.isEmpty()) {
                cbAmelio.setValue(ameliorationBuild);
                labelAmelioStat.setText(Arme.AMELIORATION.get(ameliorationArme).get(ameliorationBuild));
            }
        }
    }

    private void addListeners() {
        setupListenerCbPolarite(cbPolariteMod1, modDisplay1);
        setupListenerCbPolarite(cbPolariteMod2, modDisplay2);
        setupListenerCbPolarite(cbPolariteMod3, modDisplay3);
        setupListenerCbPolarite(cbPolariteMod4, modDisplay4);
        setupListenerCbPolarite(cbPolariteMod5, modDisplay5);
        setupListenerCbPolarite(cbPolariteMod6, modDisplay6);
        setupListenerCbPolarite(cbPolariteMod7, modDisplay7);
        setupListenerCbPolarite(cbPolariteMod8, modDisplay8);
        setupListenerCbPolarite(cbPolariteMod9, modDisplay9);
        setupListenerCbPolarite(cbPolariteMod10, modDisplay10);

        // Descendant
        if(type.equals("descendant")) {
            setupListenerCbPolarite(cbPolariteModDescendant, modDisplayDescendant);
            setupListenerCbPolarite(cbPolariteModSecondaire, modDisplaySecondaire);

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

    private void setupListenerCbPolarite(ComboBox<PolariteItem> cb, ControllerModCardDisplay mod) {
        cb.valueProperty().addListener((_, _, newPolariteItem) -> {
            if(mod != null) {
                if(newPolariteItem.getPolarite().equals(mod.getMod().getPolarite())) {
                    mod.setPolariteVert();
                } else {
                    mod.setPolariteBlanc();
                }
            }
        });
    }

    @FXML
    void retour(ActionEvent event) throws IOException {
        Main.switchScene("prereglagePage.fxml");
        ControllerPrereglagePage controller = (ControllerPrereglagePage) Main.getCurrentController();
        controller.setPrereglage(prereglage);
    }

    @FXML
    void delete(ActionEvent event) {
        // TODO: fct delete
    }

    @FXML
    void recherche(ActionEvent event) throws IOException {
        vboxMod.getChildren().clear();
        if(!txtfieldResearch.getText().isEmpty()) {
            vboxMod.getChildren().addAll(prereglageManager.getHBoxRechercheModCard(cards, txtfieldResearch.getText()));
        }
        else {
            vboxMod.getChildren().addAll(prereglageManager.makeBoxModCard(cards));
        }
    }

    private VBox createGridMod(int size) {
        for(int x = 0; x < 2; x++) {
            stackpaneGrid.put(x, new HashMap<>());
            for(int y = 0; y < size; y++) {
                StackPane sp = new StackPane();
                sp.setMinSize(100, 130);
                sp.setPrefSize(100, 130);
                sp.setMaxSize(100, 130);
                stackpaneGrid.get(x).put(y, sp);
            }
        }

        List<ComboBox<PolariteItem>> haut = new LinkedList<>();
        List<ComboBox<PolariteItem>> bas = new LinkedList<>();
        if (size == 6) {
            haut.add(cbPolariteModDescendant);
            bas.add(cbPolariteModSecondaire);
        }
        haut.addAll(Arrays.asList(cbPolariteMod1, cbPolariteMod2, cbPolariteMod3, cbPolariteMod4, cbPolariteMod5));
        bas.addAll(Arrays.asList(cbPolariteMod6, cbPolariteMod7, cbPolariteMod8, cbPolariteMod9, cbPolariteMod10));

        VBox vboxPrincipal = new VBox();
        vboxPrincipal.setSpacing(5);
        vboxPrincipal.setPrefSize(100 * size, 325);

        HBox hboxHaut = new HBox();
        hboxHaut.setPrefSize(100 * size, 25);
        hboxHaut.setSpacing(5);
        hboxHaut.setPadding(new Insets(0, 5, 0, 5));
        hboxHaut.getChildren().addAll(haut);

        HBox hboxBas = new HBox();
        hboxBas.setPrefSize(100 * size, 25);
        hboxBas.setSpacing(5);
        hboxBas.setPadding(new Insets(0, 5, 0, 5));
        hboxBas.getChildren().addAll(bas);

        GridPane grid = new GridPane();
        grid.setMinSize(100 * size, 260);
        grid.setPrefSize(100 * size, 260);
        grid.setMaxSize(100 * size, 260);

        vboxPrincipal.getChildren().add(hboxHaut);
        vboxPrincipal.getChildren().add(grid);
        vboxPrincipal.getChildren().add(hboxBas);

        return vboxPrincipal;
    }

    private ControllerModCardDisplay createModDisplay(int idMod, int col, int row) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/card/prereglage/modCardDisplay.fxml"));
        Pane cardPane = loader.load();
        cardPane.setScaleX(0.75);
        cardPane.setScaleY(0.75);

        ControllerModCardDisplay controller = loader.getController();
        controller.setMod(CollectionCollectible.getModById(idMod));

        StackPane sp = stackpaneGrid.get(row).get(col);
        sp.getChildren().clear();
        sp.getChildren().add(cardPane);

        gridMod.add(sp, col, row);
        GridPane.setHalignment(cardPane, HPos.CENTER);
        GridPane.setValignment(cardPane, VPos.CENTER);

        return controller;
    }

}