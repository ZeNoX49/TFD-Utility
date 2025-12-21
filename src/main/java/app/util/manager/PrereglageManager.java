package app.util.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import app.Main;
import app.collection.CollectionCollectible;
import app.collection.CollectionPrereglage;
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

    /* -------------------------------------------------- */
    
    private Map<Pane, Prereglage> prereglagePane = new LinkedHashMap<>();
    private List<HBox> hbox_prereglage;

    // récupérer hbox_prereglage + la créer si elle n'existe pas
    public List<HBox> getHBoxPrereglage() throws IOException {
        if(hbox_prereglage == null || hbox_prereglage.isEmpty()) {
            Main.addTextLoad("\nPrereglages :");
            hbox_prereglage = makePrereglageCard();
        }
        return hbox_prereglage;
    }

    // Créer les préréglage
    private List<HBox> makePrereglageCard() throws IOException {
        List<HBox> hboxs = new ArrayList<>();
        HBox hbox = createHBox(20, 1305, 267);
        hboxs.add(hbox);

        for (Prereglage prereglage : CollectionPrereglage.getPrereglage()) {
            Main.addTextLoad(" - " + prereglage.getNom());

            Pane cardPane = getCardPanePrereglage(prereglage);

            hbox.getChildren().add(cardPane);
    		if(hbox.getChildren().size() == 2) {
                hbox = createHBox(20, 1305, 267);
    			hboxs.add(hbox);
    		}
        }

        return hboxs;
    }

    // Ajouter un préréglage
    public void addNewPrereglageCard(Prereglage prereglage) throws IOException {
        HBox hbox = hbox_prereglage.get(hbox_prereglage.size() - 1);
        if(hbox.getChildren().size() == 2) {
            hbox = createHBox(20, 1305, 267);
            hbox_prereglage.add(hbox);
        }

        Pane cardPane = getCardPanePrereglage(prereglage);
        hbox.getChildren().add(cardPane);
    }

    // refresh l'ordre des préréglages à chaque fois que l'on revient sur la liste
    public void refreshPrereglageCard() throws IOException {
        for (HBox hbox : hbox_prereglage) {
            for (Node cardPane : hbox.getChildren()) {
                ControllerPrereglageCard controller = (ControllerPrereglageCard) cardPane.getUserData();
                controller.setPrereglage(prereglagePane.get((Pane) cardPane));
            }
        }
    }

    // Obtenir une carte préréglage
    private Pane getCardPanePrereglage(Prereglage prereglage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/card/prereglage/PrereglageCard.fxml"));
        Pane cardPane = loader.load();
        ControllerPrereglageCard controller = loader.getController();
        controller.setPrereglage(prereglage);

        prereglagePane.put(cardPane, prereglage);
        cardPane.setUserData(controller);

        return cardPane;
    }

    /* ------------------------------------------------------------------------------------------------------------------- */

    private Map<String, Map<ControllerModCardDisplay, Pane>> liste_card_mod = new LinkedHashMap<>();

    // récupérer liste_card_mod + le créer si elle n'existe pas
    public Map<ControllerModCardDisplay, Pane> getCardMod(String type) throws IOException {
        if(!liste_card_mod.containsKey(type)) {
            Main.addTextLoad("\nMod display " + type + " :");
            makeListModCard(type);
        }
        return liste_card_mod.get(type);
    }

    private void makeListModCard(String type) throws IOException {
        Map<ControllerModCardDisplay, Pane> cards = new LinkedHashMap<>();

        for(Mod mod : CollectionCollectible.getMod(type)) {
            Main.addTextLoad(" - " + mod.getNom());

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/card/prereglage/modCardDisplay.fxml"));
            Pane cardPane = loader.load();
            ControllerModCardDisplay controller = loader.getController();
            controller.setMod(mod);

            cards.put(controller, cardPane);
        }

        liste_card_mod.put(type, cards);
    }

    public List<HBox> makeBoxModCard(Map<ControllerModCardDisplay, Pane> cards) throws IOException {
        List<HBox> hboxs = new ArrayList<>();
        HBox hbox = createHBox(16, 1475, 180);
        hboxs.add(hbox);

        for(Pane cardPane : cards.values()) {
            hbox.getChildren().add(cardPane);
            if(hbox.getChildren().size() == 10) {
                hbox = createHBox(16, 1475, 180);
                hboxs.add(hbox);
            }
        }

        return hboxs;
    }

    public List<HBox> getHBoxRechercheModCard(Map<ControllerModCardDisplay, Pane> cards, String recherche) throws IOException {
        List<HBox> hboxs = new ArrayList<>();
        HBox hbox = createHBox(16, 1475, 180);
        hboxs.add(hbox);

        for(ControllerModCardDisplay ctrl : cards.keySet()) {
            Mod mod = ctrl.getMod();
            if(mod.getNom().contains(recherche) || mod.getMotCle().contains(recherche)) {
                hbox.getChildren().add(cards.get(ctrl));
                if(hbox.getChildren().size() == 10) {
                    hbox = createHBox(16, 1475, 180);
                    hboxs.add(hbox);
                }
            }
        }

        return hboxs;
    }

}