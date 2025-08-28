package app.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.Collection.CollectionCollectible;
import app.Collection.CollectionPrereglage;
import app.Main;
import app.controller.card.prereglage.ControllerModCardDisplay;
import app.controller.card.prereglage.ControllerPrereglageCard;
import app.model.collectible.Mod;
import app.model.prereglage.Prereglage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class PrereglageManager extends Manager {
    private static PrereglageManager instance;
    public static PrereglageManager getInstance() {
        if(instance == null) {
            instance = new PrereglageManager();
        }
        return instance;
    }
    
    private Map<Pane, Prereglage> prereglagePane;
    private List<Prereglage> prereglages;
    private List<HBox> hbox_prereglage;
    private Map<String, List<HBox>> liste_hbox_mod;
    private List<HBox> hbox_rechercheMod;

    public void initialize() throws IOException {
        prereglagePane = new HashMap<>();
        cardPrereglage();

        liste_hbox_mod = new HashMap<>();
        for(String type : Mod.TYPE_MOD) { 
            liste_hbox_mod.put(type, new ArrayList<>());
            // cardMod(type);
        }
    }

    /* ------------------------------------------------------------------------------------------------------------------- */

    public void cardPrereglage() throws IOException {
        prereglages = new ArrayList<>(CollectionPrereglage.getPrereglage());
        Main.addTextLoad("\nPrereglages :");
        hbox_prereglage = makePrereglageCard();
    }

    public List<HBox> makePrereglageCard() throws IOException {
        List<HBox> hboxs = new ArrayList<>();
        HBox hbox = createHBox(20, 1305, 267);

        for (Prereglage prereglage : CollectionPrereglage.getPrereglage()) {
            Main.addTextLoad(" - " + prereglage.getNom());

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/card/prereglage/prereglageCard.fxml"));
            Pane cardPane = loader.load();
            ControllerPrereglageCard controller = loader.getController();
            controller.setPrereglage(prereglage);

            prereglagePane.put(cardPane, prereglage);
            cardPane.setUserData(controller);

            hbox.getChildren().add(cardPane);
    		if(hbox.getChildren().size() == 2) {
    			hboxs.add(hbox);
    			hbox = createHBox(20, 1305, 267);
    		}
        }
        if(!hbox.getChildren().isEmpty()) {
            hboxs.add(hbox);
        }

        return hboxs;
    }

    public void addNewPrereglageCard(Prereglage prereglage) throws IOException {
        HBox hbox;
        boolean toAdd;
        if(hbox_prereglage.get(hbox_prereglage.size() - 1).getChildren().size() != 2) {
            hbox = hbox_prereglage.get(hbox_prereglage.size() - 1);
            toAdd = false;
        }
        else {
            hbox = createHBox(20, 1305, 267);
            toAdd = true;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/card/prereglage/PrereglageCard.fxml"));
        Pane cardPane = loader.load();
        ControllerPrereglageCard controller = loader.getController();
        controller.setPrereglage(prereglage);

        prereglagePane.put(cardPane, prereglage);
        cardPane.setUserData(controller);

        hbox.getChildren().add(cardPane);
        if(toAdd) {
            hbox_prereglage.add(hbox);
        }
    }

    public void refreshPrereglageCard() throws IOException {
        for (HBox hbox : hbox_prereglage) {
            for (Node cardPane : hbox.getChildren()) {
                ControllerPrereglageCard controller = (ControllerPrereglageCard) cardPane.getUserData();
                controller.setPrereglage(prereglagePane.get((Pane) cardPane));
            }
        }
    }

    /* ------------------------------------------------------------------------------------------------------------------- */

    public void cardMod(String type) throws IOException {
        Main.addTextLoad("\nMod display " + type + " :");
        liste_hbox_mod.put(type, makeModCard(type));
    }

    private List<HBox> makeModCard(String type) throws IOException {
        List<HBox> hboxs = new ArrayList<>();
        HBox hbox = createHBox(16, 1475, 180);

        for(Mod mod : CollectionCollectible.getMod(type)) {
            Main.addTextLoad(" - " + mod.getNom());

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/card/prereglage/modCardDisplay.fxml"));
            Pane cardPane = loader.load();
            ControllerModCardDisplay controller = loader.getController();
            controller.setMod(mod);

            hbox.getChildren().add(cardPane);
            if(hbox.getChildren().size() == 10) {
                hboxs.add(hbox);
                hbox = createHBox(16, 1475, 180);
            }
        }
        if(!hbox.getChildren().isEmpty()) {
            hboxs.add(hbox);
        }

        return hboxs;
    }

    public List<HBox> getHBoxRechercheModCard(String type, String recherche) throws IOException {
        List<HBox> hboxs = new ArrayList<>();
        HBox hbox = createHBox(16, 1475, 180);

        for(Mod mod : CollectionCollectible.getMod(type)) {
            if(mod.getNom().contains(recherche) || mod.getMotCle().contains(recherche)) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/card/prereglage/modCardDisplay.fxml"));
                Pane cardPane = loader.load();
                ControllerModCardDisplay controller = loader.getController();
                controller.setMod(mod);

                hbox.getChildren().add(cardPane);
                if(hbox.getChildren().size() == 10) {
                    hboxs.add(hbox);
                    hbox = createHBox(16, 1475, 180);
                }
            }
        }
        if(!hbox.getChildren().isEmpty()) {
            hboxs.add(hbox);
        }

        return hboxs;
    }

    /* ------------------------------------------------------------------------------------------------------------------- */

    /* ---- GETTERS ----- */
    public List<HBox> getHBoxPrereglage() {
        return hbox_prereglage;
    }
    public List<HBox> getHBoxMod(String type) {
        return liste_hbox_mod.get(type);
    }
}
