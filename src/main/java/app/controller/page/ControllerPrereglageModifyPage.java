package app.controller.page;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import app.Collection.CollectionCollectible;
import app.Collection.CollectionPrereglage;
import app.Collection.CollectionProgression;
import app.Main;
import app.controller.card.prereglage.ControllerModArcheoCardDisplay;
import app.controller.card.prereglage.ControllerModCardDisplay;
import app.controller.card.prereglage.ControllerModDeclenchementCardDisplay;
import app.model.collectible.ComposantExterne;
import app.model.collectible.Mod;
import app.model.collectible.Reacteur;
import app.model.collectible.ce_Auxiliaire;
import app.model.collectible.ce_Detecteur;
import app.model.collectible.ce_Memoire;
import app.model.collectible.ce_Processeur;
import app.model.prereglage.BuildArcheonique;
import app.model.prereglage.BuildArme;
import app.model.prereglage.BuildDescendant;
import app.model.prereglage.ConfigComposantExterne;
import app.model.prereglage.ConfigReacteur;
import app.model.prereglage.GridArcheo;
import app.model.prereglage.GridArcheoManager;
import app.model.prereglage.Prereglage;
import app.model.progression.Acolyte;
import app.model.progression.Arme;
import app.model.progression.Descendant;
import app.model.progression.Vehicule;
import app.util.manager.ImageManager;
import app.util.manager.PrereglageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class ControllerPrereglageModifyPage {

    private ImageManager imageManager = ImageManager.getInstance();
    private PrereglageManager prereglageManager = PrereglageManager.getInstance();
    private Prereglage prereglage;
    
    @FXML private ComboBox<String> cbMotCle;
    @FXML private TextField auteur, nom, date;

    // Descendant
    @FXML private GridPane gridModDescendant;
    @FXML private ImageView imgDescendant;
    @FXML private StackPane spModDeclenchement;
    private ControllerModDeclenchementCardDisplay controllerModDeclenchement;

    // Arme 1
    @FXML private ImageView imgArme1;
    @FXML private GridPane gridModArme1;
    @FXML private ImageView img_amelio1_arme1, img_amelio2_arme1, img_amelio3_arme1, img_amelio4_arme1, img_amelio5_arme1;
    @FXML private Label label_amelio1_arme1, label_amelio2_arme1, label_amelio3_arme1, label_amelio4_arme1, label_amelio5_arme1;
    @FXML private Label label_amelio1_arme1_stat, label_amelio2_arme1_stat, label_amelio3_arme1_stat, label_amelio4_arme1_stat, label_amelio5_arme1_stat;
    @FXML private Label label_attribut1_arme1, label_attribut2_arme1, label_attribut3_arme1, label_attribut4_arme1;
    @FXML private Label label_attribut1_arme1_stat, label_attribut2_arme1_stat, label_attribut3_arme1_stat, label_attribut4_arme1_stat;

    // Arme 2
    @FXML private ImageView imgArme2;
    @FXML private GridPane gridModArme2;
    @FXML private ImageView img_amelio1_arme2, img_amelio2_arme2, img_amelio3_arme2, img_amelio4_arme2, img_amelio5_arme2;
    @FXML private Label label_amelio1_arme2, label_amelio2_arme2, label_amelio3_arme2, label_amelio4_arme2, label_amelio5_arme2;
    @FXML private Label label_amelio1_arme2_stat, label_amelio2_arme2_stat, label_amelio3_arme2_stat, label_amelio4_arme2_stat, label_amelio5_arme2_stat;
    @FXML private Label label_attribut1_arme2, label_attribut2_arme2, label_attribut3_arme2, label_attribut4_arme2;
    @FXML private Label label_attribut1_arme2_stat, label_attribut2_arme2_stat, label_attribut3_arme2_stat, label_attribut4_arme2_stat;


    // Arme 3
    @FXML private ImageView imgArme3;
    @FXML private GridPane gridModArme3;
    @FXML private ImageView img_amelio1_arme3, img_amelio2_arme3, img_amelio3_arme3, img_amelio4_arme3, img_amelio5_arme3;
    @FXML private Label label_amelio1_arme3, label_amelio2_arme3, label_amelio3_arme3, label_amelio4_arme3, label_amelio5_arme3;
    @FXML private Label label_amelio1_arme3_stat, label_amelio2_arme3_stat, label_amelio3_arme3_stat, label_amelio4_arme3_stat, label_amelio5_arme3_stat;
    @FXML private Label label_attribut1_arme3, label_attribut2_arme3, label_attribut3_arme3, label_attribut4_arme3;
    @FXML private Label label_attribut1_arme3_stat, label_attribut2_arme3_stat, label_attribut3_arme3_stat, label_attribut4_arme3_stat;

    // BuildArcheo
    @FXML private StackPane spBuildArcheo;
    private GridArcheo gridArcheo;
    @FXML private StackPane spModArcheo1, spModArcheo2;
    @FXML private Label labelModArcheo1, labelModArcheo2;
    private ControllerModArcheoCardDisplay controllerModArcheo1, controllerModArcheo2;
    @FXML private GridPane gridSaison;

    // Acolyte + Vehicule
    @FXML private ImageView imgAcolyte, imgVehicule;
    @FXML private ComboBox<String> cbAcolyte, cbVehicule;

    // Reacteur
    @FXML private ImageView imgReacteur;
    @FXML private Label labelReacteurArmeEquipe1, labelReacteurArmeEquipe2, labelReacteurArmeEquipe3;
    @FXML private Label labelReacteurAttribut1, labelReacteurAttribut2;
    @FXML private Label labelReacteurAttribut1_stat, labelReacteurAttribut2_stat;

    // ce Auxiliaire
    @FXML private Label labelNomAuxiliaire;
    @FXML private ImageView imgAuxiliaire;
    @FXML private Label label_attribut0_auxiliaire, label_attribut0_auxiliaire_stat;
    @FXML private Label label_amelio1_auxiliaire, label_amelio2_auxiliaire;
    @FXML private Label label_amelio1_auxiliaire_stat, label_amelio2_auxiliaire_stat;
    @FXML private Label label_attribut1_auxiliaire, label_attribut2_auxiliaire;
    @FXML private Label label_attribut1_auxiliaire_stat, label_attribut2_auxiliaire_stat;

    // ce Detecteur
    @FXML private Label labelNomDetecteur;
    @FXML private ImageView imgDetecteur;
    @FXML private Label label_attribut0_detecteur, label_attribut0_detecteur_stat;
    @FXML private Label label_amelio1_detecteur, label_amelio2_detecteur;
    @FXML private Label label_amelio1_detecteur_stat, label_amelio2_detecteur_stat;
    @FXML private Label label_attribut1_detecteur, label_attribut2_detecteur;
    @FXML private Label label_attribut1_detecteur_stat, label_attribut2_detecteur_stat;

    // ce Memoire
    @FXML private Label labelNomMemoire;
    @FXML private ImageView imgMemoire;
    @FXML private Label label_attribut0_memoire, label_attribut0_memoire_stat;
    @FXML private Label label_amelio1_memoire, label_amelio2_memoire;
    @FXML private Label label_amelio1_memoire_stat, label_amelio2_memoire_stat;
    @FXML private Label label_attribut1_memoire, label_attribut2_memoire;
    @FXML private Label label_attribut1_memoire_stat, label_attribut2_memoire_stat;

    // ce Processeur
    @FXML private Label labelNomProcesseur;
    @FXML private ImageView imgProcesseur;
    @FXML private Label label_attribut0_processeur ,label_attribut0_processeur_stat;
    @FXML private Label label_amelio1_processeur, label_amelio2_processeur;
    @FXML private Label label_amelio1_processeur_stat, label_amelio2_processeur_stat;
    @FXML private Label label_attribut1_processeur, label_attribut2_processeur;
    @FXML private Label label_attribut1_processeur_stat, label_attribut2_processeur_stat;

    /* ------------------------------------------------------------------------------------------------------------------------- */

    @FXML
    private void initialize() throws IOException {
        cbMotCle.getItems().addAll(Prereglage.MOT_CLE);

        FXMLLoader loaderModDeclenchement = new FXMLLoader(getClass().getResource("/fxml/card/prereglage/modDeclenchementCardDisplay.fxml"));
        Pane cardPaneModDeclenchement = loaderModDeclenchement.load();
        controllerModDeclenchement = loaderModDeclenchement.getController();
        controllerModDeclenchement.setModVide();
        cardPaneModDeclenchement.setScaleX(0.8);
        cardPaneModDeclenchement.setScaleY(0.8);
        spModDeclenchement.getChildren().add(cardPaneModDeclenchement);

        FXMLLoader loaderModArcheo1 = new FXMLLoader(getClass().getResource("/fxml/card/prereglage/modArcheoCardDisplay.fxml"));
        Pane cardPaneModArcheo1 = loaderModArcheo1.load();
        controllerModArcheo1 = loaderModArcheo1.getController();
        controllerModArcheo1.setModArcheoVide();
        spModArcheo1.getChildren().add(cardPaneModArcheo1);

        FXMLLoader loaderModArcheo2 = new FXMLLoader(getClass().getResource("/fxml/card/prereglage/modArcheoCardDisplay.fxml"));
        Pane cardPaneModArcheo2 = loaderModArcheo2.load();
        controllerModArcheo2 = loaderModArcheo2.getController();
        controllerModArcheo2.setModArcheoVide();
        spModArcheo2.getChildren().add(cardPaneModArcheo2);

        for(int y = 0; y < 5; y++) {
            for(int x = 0; x < 4; x++) {
                ImageView img = new ImageView(imageManager.getImage(getClass().getResource("/img/cercle.png").toExternalForm(), 15, 15));
                gridSaison.add(img, x, y);
                GridPane.setHalignment(img, HPos.CENTER);
                GridPane.setValignment(img, VPos.CENTER);
            }
        }

        List<String> acolyteList = new ArrayList<>();
        for(int id : CollectionProgression.getMapAcolyteKeys()) {
            acolyteList.add(CollectionProgression.getAcolyteById(id).getName());
        }
        Collections.sort(acolyteList);
        cbAcolyte.getItems().addAll(acolyteList);

        List<String> vehiculeList = new ArrayList<>();
        for(int id : CollectionProgression.getMapVehiculeKeys()) {
            vehiculeList.add(CollectionProgression.getVehiculeById(id).getName());
        }
        Collections.sort(vehiculeList);
        cbVehicule.getItems().addAll(vehiculeList);
    }

    /* ------------------------------------------------------------------------------------------------------------------------- */

    public void setPrereglage(Prereglage prereglage) throws IOException {
        this.prereglage = prereglage;
        cbMotCle.setValue(prereglage.getMotCle());
        auteur.setText(prereglage.getAuteur());
        nom.setText(prereglage.getNom());
        date.setText(prereglage.getDate());

        // Descendant
        Integer idDescendant = prereglage.getIdDescendant();
        if(idDescendant != null) {
            Descendant descendant = CollectionProgression.getDescendantById(idDescendant);
            imgDescendant.setImage(imageManager.getImage(descendant.getImage(), 150, 150));

            BuildDescendant buildDescendant = prereglage.getBuildDescendant();
            Integer idModDeclenchement = buildDescendant.getIdModDeclenchement();
            if(idModDeclenchement != null) {
                controllerModDeclenchement.setMod(CollectionCollectible.getModDeclenchementById(idModDeclenchement));
            }

            createModDisplay(buildDescendant.getIdModDescendant(),  buildDescendant.getPolariteModDescendant(), gridModDescendant, 0, 0);
            createModDisplay(buildDescendant.getIdMod1(),           buildDescendant.getPolariteMod1(),          gridModDescendant, 1, 0);
            createModDisplay(buildDescendant.getIdMod2(),           buildDescendant.getPolariteMod2(),          gridModDescendant, 2, 0);
            createModDisplay(buildDescendant.getIdMod3(),           buildDescendant.getPolariteMod3(),          gridModDescendant, 3, 0);
            createModDisplay(buildDescendant.getIdMod4(),           buildDescendant.getPolariteMod4(),          gridModDescendant, 4, 0);
            createModDisplay(buildDescendant.getIdMod5(),           buildDescendant.getPolariteMod5(),          gridModDescendant, 5, 0);
            createModDisplay(buildDescendant.getIdModSecondaire(),  buildDescendant.getPolariteModSecondaire(), gridModDescendant, 0, 1);
            createModDisplay(buildDescendant.getIdMod6(),           buildDescendant.getPolariteMod6(),          gridModDescendant, 1, 1);
            createModDisplay(buildDescendant.getIdMod7(),           buildDescendant.getPolariteMod7(),          gridModDescendant, 2, 1);
            createModDisplay(buildDescendant.getIdMod8(),           buildDescendant.getPolariteMod8(),          gridModDescendant, 3, 1);
            createModDisplay(buildDescendant.getIdMod9(),           buildDescendant.getPolariteMod9(),          gridModDescendant, 4, 1);
            createModDisplay(buildDescendant.getIdMod10(),          buildDescendant.getPolariteMod10(),         gridModDescendant, 5, 1);
        }

        // Arme 1
        Integer idArme1 = prereglage.getIdArme1();
        if(idArme1 != null) {
            Arme arme1 = CollectionProgression.getArmeById(idArme1);
            imgArme1.setImage(imageManager.getImage(arme1.getImage(), 150, 150));
            img_amelio1_arme1.setImage(imageManager.getImage(Arme.AMELIORATION_IMG.get(arme1.getAmelioration1()), 18, 18));
            img_amelio2_arme1.setImage(imageManager.getImage(Arme.AMELIORATION_IMG.get(arme1.getAmelioration2()), 18, 18));
            img_amelio3_arme1.setImage(imageManager.getImage(Arme.AMELIORATION_IMG.get(arme1.getAmelioration3()), 18, 18));
            img_amelio4_arme1.setImage(imageManager.getImage(Arme.AMELIORATION_IMG.get(arme1.getAmelioration4()), 18, 18));
            img_amelio5_arme1.setImage(imageManager.getImage(Arme.AMELIORATION_IMG.get(arme1.getAmelioration5()), 18, 18));
            
            BuildArme buildArme1 = prereglage.getBuildArme1();
            if(!buildArme1.getAmelio1().isEmpty()) {
                label_amelio1_arme1.setText(buildArme1.getAmelio1());
                label_amelio1_arme1_stat.setText(Arme.AMELIORATION.get(arme1.getAmelioration1()).get(buildArme1.getAmelio1()));
            }
            if(!buildArme1.getAmelio2().isEmpty()) {
                label_amelio2_arme1.setText(buildArme1.getAmelio2());
                label_amelio2_arme1_stat.setText(Arme.AMELIORATION.get(arme1.getAmelioration2()).get(buildArme1.getAmelio2()));
            }
            if(!buildArme1.getAmelio3().isEmpty()) {
                label_amelio3_arme1.setText(buildArme1.getAmelio3());
                label_amelio3_arme1_stat.setText(Arme.AMELIORATION.get(arme1.getAmelioration3()).get(buildArme1.getAmelio3()));
            }
            if(!buildArme1.getAmelio4().isEmpty()) {
                label_amelio4_arme1.setText(buildArme1.getAmelio4());
                label_amelio4_arme1_stat.setText(Arme.AMELIORATION.get(arme1.getAmelioration4()).get(buildArme1.getAmelio4()));
            }
            if(!buildArme1.getAmelio5().isEmpty()) {
                label_amelio5_arme1.setText(buildArme1.getAmelio5());
                label_amelio5_arme1_stat.setText(Arme.AMELIORATION.get(arme1.getAmelioration5()).get(buildArme1.getAmelio5()));
            }
            if(!buildArme1.getAttribut1().isEmpty()) {
                label_attribut1_arme1.setText(buildArme1.getAttribut1());
                label_attribut1_arme1_stat.setText(Arme.ATTRIBUT.get(buildArme1.getAttribut1()));
            }
            if(!buildArme1.getAttribut2().isEmpty()) {
                label_attribut2_arme1.setText(buildArme1.getAttribut2());
                label_attribut2_arme1_stat.setText(Arme.ATTRIBUT.get(buildArme1.getAttribut2()));
            }
            if(!buildArme1.getAttribut3().isEmpty()) {
                label_attribut3_arme1.setText(buildArme1.getAttribut3());
                label_attribut3_arme1_stat.setText(Arme.ATTRIBUT.get(buildArme1.getAttribut3()));
            }
            if(!buildArme1.getAttribut4().isEmpty()) {
                label_attribut4_arme1.setText(buildArme1.getAttribut4());
                label_attribut4_arme1_stat.setText(Arme.ATTRIBUT.get(buildArme1.getAttribut4()));
            }

            createModDisplay(buildArme1.getIdMod1(),  buildArme1.getPolariteMod1(),  gridModArme1, 0, 0);
            createModDisplay(buildArme1.getIdMod2(),  buildArme1.getPolariteMod2(),  gridModArme1, 1, 0);
            createModDisplay(buildArme1.getIdMod3(),  buildArme1.getPolariteMod3(),  gridModArme1, 2, 0);
            createModDisplay(buildArme1.getIdMod4(),  buildArme1.getPolariteMod4(),  gridModArme1, 3, 0);
            createModDisplay(buildArme1.getIdMod5(),  buildArme1.getPolariteMod5(),  gridModArme1, 4, 0);
            createModDisplay(buildArme1.getIdMod6(),  buildArme1.getPolariteMod6(),  gridModArme1, 0, 1);
            createModDisplay(buildArme1.getIdMod7(),  buildArme1.getPolariteMod7(),  gridModArme1, 1, 1);
            createModDisplay(buildArme1.getIdMod8(),  buildArme1.getPolariteMod8(),  gridModArme1, 2, 1);
            createModDisplay(buildArme1.getIdMod9(),  buildArme1.getPolariteMod9(),  gridModArme1, 3, 1);
            createModDisplay(buildArme1.getIdMod10(), buildArme1.getPolariteMod10(), gridModArme1, 4, 1);
        }

        // Arme 2
        Integer idArme2 = prereglage.getIdArme2();
        if(idArme2 != null) {
            Arme arme2 = CollectionProgression.getArmeById(idArme2);
            imgArme2.setImage(imageManager.getImage(arme2.getImage(), 150, 150));
            img_amelio1_arme2.setImage(imageManager.getImage(Arme.AMELIORATION_IMG.get(arme2.getAmelioration1()), 18, 18));
            img_amelio2_arme2.setImage(imageManager.getImage(Arme.AMELIORATION_IMG.get(arme2.getAmelioration2()), 18, 18));
            img_amelio3_arme2.setImage(imageManager.getImage(Arme.AMELIORATION_IMG.get(arme2.getAmelioration3()), 18, 18));
            img_amelio4_arme2.setImage(imageManager.getImage(Arme.AMELIORATION_IMG.get(arme2.getAmelioration4()), 18, 18));
            img_amelio5_arme2.setImage(imageManager.getImage(Arme.AMELIORATION_IMG.get(arme2.getAmelioration5()), 18, 18));

            BuildArme buildArme2 = prereglage.getBuildArme2();
            if(!buildArme2.getAmelio1().isEmpty()) {
                label_amelio1_arme2.setText(buildArme2.getAmelio1());
                label_amelio1_arme2_stat.setText(Arme.AMELIORATION.get(arme2.getAmelioration1()).get(buildArme2.getAmelio1()));
            }
            if(!buildArme2.getAmelio2().isEmpty()) {
                label_amelio2_arme2.setText(buildArme2.getAmelio2());
                label_amelio2_arme2_stat.setText(Arme.AMELIORATION.get(arme2.getAmelioration2()).get(buildArme2.getAmelio2()));
            }
            if(!buildArme2.getAmelio3().isEmpty()) {
                label_amelio3_arme2.setText(buildArme2.getAmelio3());
                label_amelio3_arme2_stat.setText(Arme.AMELIORATION.get(arme2.getAmelioration3()).get(buildArme2.getAmelio3()));
            }
            if(!buildArme2.getAmelio4().isEmpty()) {
                label_amelio4_arme2.setText(buildArme2.getAmelio4());
                label_amelio4_arme2_stat.setText(Arme.AMELIORATION.get(arme2.getAmelioration4()).get(buildArme2.getAmelio4()));
            }
            if(!buildArme2.getAmelio5().isEmpty()) {
                label_amelio5_arme2.setText(buildArme2.getAmelio5());
                label_amelio5_arme2_stat.setText(Arme.AMELIORATION.get(arme2.getAmelioration5()).get(buildArme2.getAmelio5()));
            }
            if(!buildArme2.getAttribut1().isEmpty()) {
                label_attribut1_arme2.setText(buildArme2.getAttribut1());
                label_attribut1_arme2_stat.setText(Arme.ATTRIBUT.get(buildArme2.getAttribut1()));
            }
            if(!buildArme2.getAttribut2().isEmpty()) {
                label_attribut2_arme2.setText(buildArme2.getAttribut2());
                label_attribut2_arme2_stat.setText(Arme.ATTRIBUT.get(buildArme2.getAttribut2()));
            }
            if(!buildArme2.getAttribut3().isEmpty()) {
                label_attribut3_arme2.setText(buildArme2.getAttribut3());
                label_attribut3_arme2_stat.setText(Arme.ATTRIBUT.get(buildArme2.getAttribut3()));
            }
            if(!buildArme2.getAttribut4().isEmpty()) {
                label_attribut4_arme2.setText(buildArme2.getAttribut4());
                label_attribut4_arme2_stat.setText(Arme.ATTRIBUT.get(buildArme2.getAttribut4()));
            }

            createModDisplay(buildArme2.getIdMod1(),  buildArme2.getPolariteMod1(),  gridModArme2, 0, 0);
            createModDisplay(buildArme2.getIdMod2(),  buildArme2.getPolariteMod2(),  gridModArme2, 1, 0);
            createModDisplay(buildArme2.getIdMod3(),  buildArme2.getPolariteMod3(),  gridModArme2, 2, 0);
            createModDisplay(buildArme2.getIdMod4(),  buildArme2.getPolariteMod4(),  gridModArme2, 3, 0);
            createModDisplay(buildArme2.getIdMod5(),  buildArme2.getPolariteMod5(),  gridModArme2, 4, 0);
            createModDisplay(buildArme2.getIdMod6(),  buildArme2.getPolariteMod6(),  gridModArme2, 0, 1);
            createModDisplay(buildArme2.getIdMod7(),  buildArme2.getPolariteMod7(),  gridModArme2, 1, 1);
            createModDisplay(buildArme2.getIdMod8(),  buildArme2.getPolariteMod8(),  gridModArme2, 2, 1);
            createModDisplay(buildArme2.getIdMod9(),  buildArme2.getPolariteMod9(),  gridModArme2, 3, 1);
            createModDisplay(buildArme2.getIdMod10(), buildArme2.getPolariteMod10(), gridModArme2, 4, 1);
        }

        // Arme 3
        Integer idArme3 = prereglage.getIdArme3();
        if(idArme3 != null) {
            Arme arme3 = CollectionProgression.getArmeById(idArme3);
            imgArme3.setImage(imageManager.getImage(arme3.getImage(), 150, 150));
            img_amelio1_arme3.setImage(imageManager.getImage(Arme.AMELIORATION_IMG.get(arme3.getAmelioration1()), 18, 18));
            img_amelio2_arme3.setImage(imageManager.getImage(Arme.AMELIORATION_IMG.get(arme3.getAmelioration2()), 18, 18));
            img_amelio3_arme3.setImage(imageManager.getImage(Arme.AMELIORATION_IMG.get(arme3.getAmelioration3()), 18, 18));
            img_amelio4_arme3.setImage(imageManager.getImage(Arme.AMELIORATION_IMG.get(arme3.getAmelioration4()), 18, 18));
            img_amelio5_arme3.setImage(imageManager.getImage(Arme.AMELIORATION_IMG.get(arme3.getAmelioration5()), 18, 18));

            BuildArme buildArme3 = prereglage.getBuildArme3();
            if(!buildArme3.getAmelio1().isEmpty()) {
                label_amelio1_arme3.setText(buildArme3.getAmelio1());
                label_amelio1_arme3_stat.setText(Arme.AMELIORATION.get(arme3.getAmelioration1()).get(buildArme3.getAmelio1()));
            }
            if(!buildArme3.getAmelio2().isEmpty()) {
                label_amelio2_arme3.setText(buildArme3.getAmelio2());
                label_amelio2_arme3_stat.setText(Arme.AMELIORATION.get(arme3.getAmelioration2()).get(buildArme3.getAmelio2()));
            }
            if(!buildArme3.getAmelio3().isEmpty()) {
                label_amelio3_arme3.setText(buildArme3.getAmelio3());
                label_amelio3_arme3_stat.setText(Arme.AMELIORATION.get(arme3.getAmelioration3()).get(buildArme3.getAmelio3()));
            }
            if(!buildArme3.getAmelio4().isEmpty()) {
                label_amelio4_arme3.setText(buildArme3.getAmelio4());
                label_amelio4_arme3_stat.setText(Arme.AMELIORATION.get(arme3.getAmelioration4()).get(buildArme3.getAmelio4()));
            }
            if(!buildArme3.getAmelio5().isEmpty()) {
                label_amelio5_arme3.setText(buildArme3.getAmelio5());
                label_amelio5_arme3_stat.setText(Arme.AMELIORATION.get(arme3.getAmelioration5()).get(buildArme3.getAmelio5()));
            }
            if(!buildArme3.getAttribut1().isEmpty()) {
                label_attribut1_arme3.setText(buildArme3.getAttribut1());
                label_attribut1_arme3_stat.setText(Arme.ATTRIBUT.get(buildArme3.getAttribut1()));
            }
            if(!buildArme3.getAttribut2().isEmpty()) {
                label_attribut2_arme3.setText(buildArme3.getAttribut2());
                label_attribut2_arme3_stat.setText(Arme.ATTRIBUT.get(buildArme3.getAttribut2()));
            }
            if(!buildArme3.getAttribut3().isEmpty()) {
                label_attribut3_arme3.setText(buildArme3.getAttribut3());
                label_attribut3_arme3_stat.setText(Arme.ATTRIBUT.get(buildArme3.getAttribut3()));
            }
            if(!buildArme3.getAttribut4().isEmpty()) {
                label_attribut4_arme3.setText(buildArme3.getAttribut4());
                label_attribut4_arme3_stat.setText(Arme.ATTRIBUT.get(buildArme3.getAttribut4()));
            }

            createModDisplay(buildArme3.getIdMod1(),  buildArme3.getPolariteMod1(),  gridModArme3, 0, 0);
            createModDisplay(buildArme3.getIdMod2(),  buildArme3.getPolariteMod2(),  gridModArme3, 1, 0);
            createModDisplay(buildArme3.getIdMod3(),  buildArme3.getPolariteMod3(),  gridModArme3, 2, 0);
            createModDisplay(buildArme3.getIdMod4(),  buildArme3.getPolariteMod4(),  gridModArme3, 3, 0);
            createModDisplay(buildArme3.getIdMod5(),  buildArme3.getPolariteMod5(),  gridModArme3, 4, 0);
            createModDisplay(buildArme3.getIdMod6(),  buildArme3.getPolariteMod6(),  gridModArme3, 0, 1);
            createModDisplay(buildArme3.getIdMod7(),  buildArme3.getPolariteMod7(),  gridModArme3, 1, 1);
            createModDisplay(buildArme3.getIdMod8(),  buildArme3.getPolariteMod8(),  gridModArme3, 2, 1);
            createModDisplay(buildArme3.getIdMod9(),  buildArme3.getPolariteMod9(),  gridModArme3, 3, 1);
            createModDisplay(buildArme3.getIdMod10(), buildArme3.getPolariteMod10(), gridModArme3, 4, 1);
        }

        // Build Archeonique
        BuildArcheonique buildArcheo = new BuildArcheonique();
        gridArcheo = new GridArcheo();
        GridArcheoManager gridArcheoManager = new GridArcheoManager(prereglage.getBuildArcheo(), gridArcheo, 14);

        if(idDescendant != null) {
            gridArcheo.setDescendant(CollectionProgression.getDescendantById(idDescendant));
        }
        spBuildArcheo.getChildren().add(gridArcheoManager.getGridPane());

        Integer idModArcheo1 = prereglage.getIdModArcheo1();
        if(idModArcheo1 != null) {
            controllerModArcheo1.setModArcheo(CollectionCollectible.getModArcheoById(idModArcheo1));
        }
        Integer idModArcheo2 = prereglage.getIdModArcheo2();
        if(idModArcheo2 != null) {
            controllerModArcheo2.setModArcheo(CollectionCollectible.getModArcheoById(idModArcheo2));
        }

        for(String attribut : prereglage.getAttributSaison()) {
            int[] loc = gridArcheo.getIntFromKey(attribut);
            ImageView img = new ImageView(imageManager.getImage(getClass().getResource("/img/cercleVert.png").toExternalForm(), 15, 15));
            gridSaison.add(img, loc[0], loc[1]);
        }

        // Acolyte + Vehicule
        Acolyte acolyte = CollectionProgression.getAcolyteById(prereglage.getIdAcolyte());
        if(acolyte != null) {
            imgAcolyte.setImage(imageManager.getImage(acolyte.getImage(), 125, 125));
            cbAcolyte.setValue(acolyte.getName());
        }

        Vehicule vehicule = CollectionProgression.getVehiculeById(prereglage.getIdVehicule());
        if(vehicule != null) {
            imgVehicule.setImage(imageManager.getImage(vehicule.getImage(), 125, 125));
            cbVehicule.setValue(vehicule.getName());
        }

        // Reacteur
        if(idArme1 != null) {
            labelReacteurArmeEquipe1.setText(CollectionProgression.getArmeById(idArme1).getTypeArme());
        }
        if(idArme2 != null) {
            labelReacteurArmeEquipe2.setText(CollectionProgression.getArmeById(idArme2).getTypeArme());
        }
        if(idArme3 != null) {
            labelReacteurArmeEquipe3.setText(CollectionProgression.getArmeById(idArme3).getTypeArme());
        }
        ConfigReacteur configReacteur = prereglage.getConfigReacteur();
        if(configReacteur.getIdReacteur() != null) {
            imgReacteur.setImage(imageManager.getImage(CollectionCollectible.getReacteurById(configReacteur.getIdReacteur()).getImg(), 150, 150));
        }
        if(configReacteur.getAttribut1() != null) {
            labelReacteurAttribut1.setText(configReacteur.getAttribut1());
            labelReacteurAttribut1_stat.setText(Reacteur.ATTRIBUT.get(configReacteur.getAttribut1()));
        }
        if(configReacteur.getAttribut2() != null) {
            labelReacteurAttribut2.setText(configReacteur.getAttribut2());
            labelReacteurAttribut2_stat.setText(Reacteur.ATTRIBUT.get(configReacteur.getAttribut2()));
        }

        // ce Auxiliaire
        ConfigComposantExterne configAuxiliaire = prereglage.getConfigAuxiliaire();
        Integer idAuxiliaire = configAuxiliaire.getIdComposantExterne();
        if(idAuxiliaire != null) {
            ComposantExterne composantExterne_auxiliaire = CollectionCollectible.getComposantExterneById(idAuxiliaire);
            imgAuxiliaire.setImage(imageManager.getImage(composantExterne_auxiliaire.getAuxiliaire().getImg(), 150, 150));
            labelNomAuxiliaire.setText(composantExterne_auxiliaire.getNom());
            label_attribut0_auxiliaire.setText(composantExterne_auxiliaire.getAuxiliaire().getStat());
            label_attribut0_auxiliaire_stat.setText(composantExterne_auxiliaire.getAuxiliaire().getValStat());
            if(!configAuxiliaire.getAmelio1().isEmpty()) {
                label_amelio1_auxiliaire.setText(configAuxiliaire.getAmelio1());
                label_amelio1_auxiliaire_stat.setText(ComposantExterne.AMELIORATION.get("amélioration de la récupération").get(configAuxiliaire.getAmelio1()));
            }
            if(!configAuxiliaire.getAmelio2().isEmpty()) {
                label_amelio2_auxiliaire.setText(configAuxiliaire.getAmelio2());
                label_amelio2_auxiliaire_stat.setText(ComposantExterne.AMELIORATION.get("amélioration de la défense").get(configAuxiliaire.getAmelio2()));
            }
            if(!configAuxiliaire.getAttribut1().isEmpty()) {
                label_attribut1_auxiliaire.setText(configAuxiliaire.getAttribut1());
                label_attribut1_auxiliaire_stat.setText(ce_Auxiliaire.ATTRIBUT_AUXILIAIRE.get(configAuxiliaire.getAttribut1()));
            }
            if(!configAuxiliaire.getAttribut2().isEmpty()) {
                label_attribut2_auxiliaire.setText(configAuxiliaire.getAttribut2());
                label_attribut2_auxiliaire_stat.setText(ce_Auxiliaire.ATTRIBUT_AUXILIAIRE.get(configAuxiliaire.getAttribut2()));
            }
        }

        // ce Detecteur
        ConfigComposantExterne configDetecteur = prereglage.getConfigDetecteur();
        Integer idDetecteur = configDetecteur.getIdComposantExterne();
        if(idDetecteur != null) {
            ComposantExterne composantExterne_detecteur = CollectionCollectible.getComposantExterneById(idDetecteur);
            imgDetecteur.setImage(imageManager.getImage(composantExterne_detecteur.getDetecteur().getImg(), 150, 150));
            labelNomDetecteur.setText(composantExterne_detecteur.getNom());
            label_attribut0_detecteur.setText(composantExterne_detecteur.getDetecteur().getStat());
            label_attribut0_detecteur_stat.setText(composantExterne_detecteur.getDetecteur().getValStat());
            if(!configDetecteur.getAmelio1().isEmpty()) {
                label_amelio1_detecteur.setText(configDetecteur.getAmelio1());
                label_amelio1_detecteur_stat.setText(ComposantExterne.AMELIORATION.get("amélioration de la récupération").get(configDetecteur.getAmelio1()));
            }
            if(!configDetecteur.getAmelio2().isEmpty()) {
                label_amelio2_detecteur.setText(configDetecteur.getAmelio2());
                label_amelio2_detecteur_stat.setText(ComposantExterne.AMELIORATION.get("amélioration de la défense").get(configDetecteur.getAmelio2()));
            }
            if(!configDetecteur.getAttribut1().isEmpty()) {
                label_attribut1_detecteur.setText(configDetecteur.getAttribut1());
                label_attribut1_detecteur_stat.setText(ce_Detecteur.ATTRIBUT_DETECTEUR.get(configDetecteur.getAttribut1()));
            }
            if(!configDetecteur.getAttribut2().isEmpty()) {
                label_attribut2_detecteur.setText(configDetecteur.getAttribut2());
                label_attribut2_detecteur_stat.setText(ce_Detecteur.ATTRIBUT_DETECTEUR.get(configDetecteur.getAttribut2()));
            }
        }

        // ce Memoire
        ConfigComposantExterne configMemoire = prereglage.getConfigMemoire();
        Integer idMemoire = configMemoire.getIdComposantExterne();
        if(idMemoire != null) {
            ComposantExterne composantExterne_memoire = CollectionCollectible.getComposantExterneById(idMemoire);
            imgMemoire.setImage(imageManager.getImage(composantExterne_memoire.getMemoire().getImg(), 150, 150));
            labelNomMemoire.setText(composantExterne_memoire.getNom());
            label_attribut0_memoire.setText(composantExterne_memoire.getMemoire().getStat());
            label_attribut0_memoire_stat.setText(composantExterne_memoire.getMemoire().getValStat());
            if(!configMemoire.getAmelio1().isEmpty()) {
                label_amelio1_memoire.setText(configMemoire.getAmelio1());
                label_amelio1_memoire_stat.setText(ComposantExterne.AMELIORATION.get("amélioration de la récupération").get(configMemoire.getAmelio1()));
            }
            if(!configMemoire.getAmelio2().isEmpty()) {
                label_amelio2_memoire.setText(configMemoire.getAmelio2());
                label_amelio2_memoire_stat.setText(ComposantExterne.AMELIORATION.get("amélioration de la défense").get(configMemoire.getAmelio2()));
            }
            if(!configMemoire.getAttribut1().isEmpty()) {
                label_attribut1_memoire.setText(configMemoire.getAttribut1());
                label_attribut1_memoire_stat.setText(ce_Memoire.ATTRIBUT_MEMOIRE.get(configMemoire.getAttribut1()));
            }
            if(!configMemoire.getAttribut2().isEmpty()) {
                label_attribut2_memoire.setText(configMemoire.getAttribut2());
                label_attribut2_memoire_stat.setText(ce_Memoire.ATTRIBUT_MEMOIRE.get(configMemoire.getAttribut2()));
            }
        }

        // ce Processeur
        ConfigComposantExterne configProcesseur = prereglage.getConfigProcesseur();
        Integer idProcesseur = configProcesseur.getIdComposantExterne();
        if(idProcesseur != null) {
            ComposantExterne composantExterne_processeur = CollectionCollectible.getComposantExterneById(idProcesseur);
            imgProcesseur.setImage(imageManager.getImage(composantExterne_processeur.getProcesseur().getImg(), 150, 150));
            labelNomProcesseur.setText(composantExterne_processeur.getNom());
            label_attribut0_processeur.setText(composantExterne_processeur.getProcesseur().getStat());
            label_attribut0_processeur_stat.setText(composantExterne_processeur.getProcesseur().getValStat());
            if(!configProcesseur.getAmelio1().isEmpty()) {
                label_amelio1_processeur.setText(configProcesseur.getAmelio1());
                label_amelio1_processeur_stat.setText(ComposantExterne.AMELIORATION.get("amélioration de la récupération").get(configProcesseur.getAmelio1()));
            }
            if(!configProcesseur.getAmelio2().isEmpty()) {
                label_amelio2_processeur.setText(configProcesseur.getAmelio2());
                label_amelio2_processeur_stat.setText(ComposantExterne.AMELIORATION.get("amélioration de la défense").get(configProcesseur.getAmelio2()));
            }
            if(!configProcesseur.getAttribut1().isEmpty()) {
                label_attribut1_processeur.setText(configProcesseur.getAttribut1());
                label_attribut1_processeur_stat.setText(ce_Processeur.ATTRIBUT_PROCESSEUR.get(configProcesseur.getAttribut1()));
            }
            if(!configProcesseur.getAttribut2().isEmpty()) {
                label_attribut2_processeur.setText(configProcesseur.getAttribut2());
                label_attribut2_processeur_stat.setText(ce_Processeur.ATTRIBUT_PROCESSEUR.get(configProcesseur.getAttribut2()));
            }
        }

        addListeners();
    }

    @FXML
    void retour(ActionEvent event) throws IOException {
        prereglageManager.refreshPrereglageCard();
        Main.switchScene("listpage.fxml");
    }

    @FXML
    void duppliquer(ActionEvent event) throws IOException {
        Prereglage newPrereglage = prereglage.duppliquer();
        CollectionPrereglage.addPrereglage(newPrereglage);
        prereglageManager.addNewPrereglageCard(newPrereglage);
        retour(event);
    }

    @FXML
    void delete(ActionEvent event) {
        // TODO: fct delete
    }

    @FXML
    void modifyDescendant(ActionEvent event) throws IOException {
        Main.switchScene("buildPage.fxml"); // "modifyBuildPage.fxml"
        ControllerBuildPage controller = (ControllerBuildPage) Main.getCurrentController();
        controller.setDescendant(prereglage);
    }

    @FXML
    void modifyArme1(ActionEvent event) throws IOException {
        Main.switchScene("buildPage.fxml");
        ControllerBuildPage controller = (ControllerBuildPage) Main.getCurrentController();
        controller.setArme(prereglage, 1);
    }

    @FXML
    void modifyArme2(ActionEvent event) throws IOException {
        Main.switchScene("buildPage.fxml");
        ControllerBuildPage controller = (ControllerBuildPage) Main.getCurrentController();
        controller.setArme(prereglage, 2);
    }

    @FXML
    void modifyArme3(ActionEvent event) throws IOException {
        Main.switchScene("buildPage.fxml");
        ControllerBuildPage controller = (ControllerBuildPage) Main.getCurrentController();
        controller.setArme(prereglage, 3);
    }

    @FXML
    void modifyBuildArcheo(ActionEvent event) {
        Integer idDescendant = prereglage.getIdDescendant();
        if(idDescendant != null) {
            Main.switchScene("buildArcheoPage.fxml");
            ControllerBuildArcheoPage controller = (ControllerBuildArcheoPage) Main.getCurrentController();
            controller.setPrereglage(prereglage);
        }
    }

    @FXML
    void modifyReacteurCompExt(ActionEvent event) {
        // Main.switchScene("modifyComposantExternePage.fxml");
        // ControllerModifyComposantExternePage controller = (ControllerModifyComposantExternePage) Main.getCurrentController();
        // controller.setPrereglage(prereglage);
    }

    private void addListeners() {
        cbMotCle.valueProperty().addListener((_, _, newValue) -> prereglage.setMotCle(newValue));
        auteur.textProperty().addListener((_, _, newValue) -> prereglage.setAuteur(newValue));
        nom.textProperty().addListener((_, _, newValue) -> prereglage.setNom(newValue));
        date.textProperty().addListener((_, _, newValue) -> prereglage.setDate(newValue));
        cbAcolyte.valueProperty().addListener((_, _, newValue) -> { 
            Acolyte acolyte = CollectionProgression.getAcolyteByName(newValue);
            prereglage.setIdAcolyte(acolyte.getIdAcolyte());
            imgAcolyte.setImage(imageManager.getImage(acolyte.getImage(), 125, 125));
        });
        cbVehicule.valueProperty().addListener((_, _, newValue) -> { 
            Vehicule vehicule = CollectionProgression.getVehiculeByName(newValue);
            prereglage.setIdVehicule(vehicule.getIdVehicule());
            imgVehicule.setImage(imageManager.getImage(vehicule.getImage(), 125, 125));
        });
    }

    private void createModDisplay(Integer idMod, String polarite, GridPane grid, int col, int row) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/card/prereglage/modCardDisplay.fxml"));
        Pane cardPane = loader.load();
        cardPane.setScaleX(0.5);
        cardPane.setScaleY(0.5);

        ControllerModCardDisplay controller = loader.getController();
        if(idMod == null) {
            controller.setModVide();
        }
        else {
            Mod mod = CollectionCollectible.getModById(idMod);
            controller.setMod(mod);
            if(mod.getPolarite().equals(polarite)) {
                controller.setPolariteVert();
            }
        }

        grid.add(cardPane, col, row);
        GridPane.setHalignment(cardPane, HPos.CENTER);
        GridPane.setValignment(cardPane, VPos.CENTER);
    }

}