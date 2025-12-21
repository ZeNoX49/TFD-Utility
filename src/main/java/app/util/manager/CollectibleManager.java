package app.util.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.Collection.CollectionCollectible;
import app.Main;
import app.controller.card.collectible.ControllerComposantExterneCard;
import app.controller.card.collectible.ControllerModArcheoCard;
import app.controller.card.collectible.ControllerModCard;
import app.controller.card.collectible.ControllerModDeclenchementCard;
import app.controller.card.collectible.ControllerReacteurCard;
import app.model.collectible.ComposantExterne;
import app.model.collectible.Mod;
import app.model.collectible.ModArcheo;
import app.model.collectible.ModDeclenchement;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class CollectibleManager extends Manager {
    private static CollectibleManager instance;
    public static CollectibleManager getInstance() {
        if(instance == null) {
            instance = new CollectibleManager();
        }
        return instance;
    }

    /* ---------- Réacteur ---------- */
    private List<HBox> hbox_reacteur;

    public List<HBox> getHBoxReacteur() throws IOException {
        if(hbox_reacteur == null || hbox_reacteur.isEmpty()) {
            setCardReacteur();
        }
        return hbox_reacteur;
    }

    public void setCardReacteur() throws IOException {
        Main.addTextLoad("\nRéacteurs :");
        // Charger le FXML et récupérer le contrôleur
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/card/collectible/reacteurCard.fxml"));
        Pane cardPane = loader.load();
        ControllerReacteurCard controller = loader.getController();
        controller.setReacteur();
        // Ajouter la carte dans un HBox
        HBox hbox = new HBox(cardPane);
        // Initialiser la liste et ajouter le HBox
        hbox_reacteur = new ArrayList<>();
        hbox_reacteur.add(hbox);
    }

    /* ---------- Composant externe ---------- */
    private List<HBox> hbox_composantExterne;

    public List<HBox> getHBoxComposantExterne() throws IOException {
        if(hbox_reacteur == null || hbox_reacteur.isEmpty()) {
            setCardcomposantExterne();
        }
        return hbox_composantExterne;
    }

    public void setCardcomposantExterne() throws IOException {
        Main.addTextLoad("\nComposants Externes :");
        hbox_composantExterne = createComposantExterneCard();
    }

    public List<HBox> createComposantExterneCard() throws IOException {
        List<HBox> hboxs = new ArrayList<>();
        HBox hbox = createHBox(45, 1305, 210);
        hboxs.add(hbox);
        for(ComposantExterne composantExterne : CollectionCollectible.getComposantExterne()) {
            Main.addTextLoad(" - " + composantExterne.getNom());
            Pane cardPane = getCardPaneComposantexterne(composantExterne);
            hbox.getChildren().add(cardPane);
    		if(hbox.getChildren().size() == 2) {
                hbox = createHBox(45, 1305, 210);
    			hboxs.add(hbox);
    		}
        }

        return hboxs;
    }

    public void addNewComposantExterneCard(ComposantExterne composantExterne) throws IOException {
        HBox hbox = hbox_composantExterne.get(hbox_composantExterne.size() - 1);
        if(hbox.getChildren().size() == 2) {
            hbox = createHBox(45, 1305, 210);
            hbox_composantExterne.add(hbox);
        }
        Pane cardPane = getCardPaneComposantexterne(composantExterne);
        hbox.getChildren().add(cardPane);
    }

    private Pane getCardPaneComposantexterne(ComposantExterne composantExterne) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/card/collectible/composantExterneCard.fxml"));
        Pane cardPane = loader.load();
        ControllerComposantExterneCard controller = loader.getController();
        controller.setComposantExterne(composantExterne);
        return cardPane;
    }

    /* ---------- Mod archéonique ---------- */
    private List<HBox> hbox_modArcheo;

    public List<HBox> getHBoxModArcheo() throws IOException {
        if(hbox_modArcheo == null || hbox_modArcheo.isEmpty()) {
            setCardModArcheo();
        }
        return hbox_modArcheo;
    }

    public void setCardModArcheo() throws IOException {
        Main.addTextLoad("\nMods Archeoniques :");
        hbox_modArcheo = createModArcheoCard();
    }

     public List<HBox> createModArcheoCard() throws IOException {
        List<HBox> hboxs = new ArrayList<>();
        HBox hbox = createHBox(25, 1305, 290);
        hboxs.add(hbox);
        for (ModArcheo modArcheo : CollectionCollectible.getModArcheo()) {
            Main.addTextLoad(" - " + modArcheo.getNom());
            Pane cardPane = getCardPaneModArcheo(modArcheo);
            hbox.getChildren().add(cardPane);
    		if(hbox.getChildren().size() == 8) {
                hbox = createHBox(25, 1305, 290);
    			hboxs.add(hbox);
    		}
        }
        return hboxs;
    }

    public void addNewModArcheoCard(ModArcheo modArcheo) throws IOException {
        HBox hbox = hbox_modArcheo.get(hbox_modArcheo.size() - 1);
        if(hbox.getChildren().size() == 8) {
            hbox = createHBox(25, 1305, 290);
            hbox_modArcheo.add(hbox);
        }
        Pane cardPane = getCardPaneModArcheo(modArcheo);
        hbox.getChildren().add(cardPane);
    }

    private Pane getCardPaneModArcheo(ModArcheo modArcheo) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/card/collectible/modArcheoCard.fxml"));
        Pane cardPane = loader.load();
        ControllerModArcheoCard controller = loader.getController();
        controller.setModArcheo(modArcheo);
        return cardPane;
    }

    /* ---------- Mod déclenchement ---------- */
    private List<HBox> hbox_modDeclenchement;

    public List<HBox> getHBoxModDeclenchement() throws IOException {
        if(hbox_modDeclenchement == null || hbox_modDeclenchement.isEmpty()) {
            setCardModDeclenchement();
        }
        return hbox_modDeclenchement;
    }

    public void setCardModDeclenchement() throws IOException {
        Main.addTextLoad("\nMods Déclenchements :");
        hbox_modDeclenchement = createModDeclenchementCard();
    }

     public List<HBox> createModDeclenchementCard() throws IOException {
        List<HBox> hboxs = new ArrayList<>();
        HBox hbox = createHBox(25, 1305, 220);
        hboxs.add(hbox);
        for (ModDeclenchement modDeclenchement : CollectionCollectible.getModDeclenchement()) {
            Main.addTextLoad(" - " + modDeclenchement.getNom());
            Pane cardPane = getCardPaneModDeclenchement(modDeclenchement);
            hbox.getChildren().add(cardPane);
    		if(hbox.getChildren().size() == 8) {
                hbox = createHBox(25, 1305, 220);
    			hboxs.add(hbox);
    		}
        }
        return hboxs;
    }

    public void addNewModDeclenchementCard(ModDeclenchement modDeclenchement) throws IOException {
        HBox hbox = hbox_modDeclenchement.get(hbox_modDeclenchement.size() - 1);
        if(hbox.getChildren().size() == 8) {
            hbox = createHBox(25, 1305, 220);
            hbox_modDeclenchement.add(hbox);
        }
        Pane cardPane = getCardPaneModDeclenchement(modDeclenchement);
        hbox.getChildren().add(cardPane);
    }

    private Pane getCardPaneModDeclenchement(ModDeclenchement modDeclenchement) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/card/collectible/modDeclenchementCard.fxml"));
        Pane cardPane = loader.load();
        ControllerModDeclenchementCard controller = loader.getController();
        controller.setModDeclenchement(modDeclenchement);
        return cardPane;
    }

    /* ---------- Mods ---------- */
    private Map<String, List<HBox>> liste_hbox_mod = new HashMap<>();

    public List<HBox> getHBoxMod(String type) throws IOException {
        if(!liste_hbox_mod.containsKey(type)) {
            setCardMod(type);
        }
        return liste_hbox_mod.get(type);
    }

    public void setCardMod(String type) throws IOException {
        Main.addTextLoad("\n" + type + " :");
        liste_hbox_mod.put(type, createModCard(type));
    }

    private List<HBox> createModCard(String type) throws IOException {
        List<HBox> hboxs = new ArrayList<>();
        HBox hbox = createHBox(25, 1305, 260);
        hboxs.add(hbox);
        for(Mod mod : CollectionCollectible.getMod(type)) {
            Main.addTextLoad(" - " + mod.getNom());
            Pane cardPane = getCardPaneMod(mod);
            hbox.getChildren().add(cardPane);
            if(hbox.getChildren().size() == 8) {
                hbox = createHBox(25, 1305, 260);
                hboxs.add(hbox);
            }
        }
        return hboxs;
    }

    public void addNewModCard(String type, Mod mod) throws IOException {
        HBox hbox = liste_hbox_mod.get(type).get(liste_hbox_mod.get(type).size() - 1);
        if(hbox.getChildren().size() == 8) {
            hbox = createHBox(25, 1305, 260);
            liste_hbox_mod.get(type).add(hbox);
        }
        Pane cardPane = getCardPaneMod(mod);
        hbox.getChildren().add(cardPane);
    }

    private Pane getCardPaneMod(Mod mod) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/card/collectible/modCard.fxml"));
        Pane cardPane = loader.load();
        ControllerModCard controller = loader.getController();
        controller.setMod(mod);
        return cardPane;
    }
          
}