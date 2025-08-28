package app.util;

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
    
    private List<HBox> hbox_reacteur, hbox_composantExterne, hbox_modArcheo, hbox_modDeclenchement;
    private Map<String, List<HBox>> liste_hbox_mod;

    public void initialize() throws IOException {
        liste_hbox_mod = new HashMap<>();

        setCardReacteur();
        setCardcomposantExterne();

        setCardModArcheo();
        setCardModDeclenchement();

        for(String k : Mod.TYPE_MOD) { 
            liste_hbox_mod.put(k, new ArrayList<>());
            setCardMod(k);
        }
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

    public void setCardcomposantExterne() throws IOException {
        Main.addTextLoad("\nComposants Externes :");
        hbox_composantExterne = createComposantExterneCard();
    }

    public void setCardModArcheo() throws IOException {
        Main.addTextLoad("\nMods Archeoniques :");
        hbox_modArcheo = createModArcheoCard();
    }

    public void setCardModDeclenchement() throws IOException {
        Main.addTextLoad("\nMods Déclenchements :");
        hbox_modDeclenchement = createModDeclenchementCard();
    }

    public void setCardMod(String type) throws IOException {
        Main.addTextLoad("\n" + type + " :");
        liste_hbox_mod.put(type, createModCard(type));
    }

    public List<HBox> createComposantExterneCard() throws IOException {
        List<HBox> hboxs = new ArrayList<>();
        HBox hbox = createHBox(45, 1305, 210);

        for (ComposantExterne composantExterne : CollectionCollectible.getComposantExterne()) {
            Main.addTextLoad(" - " + composantExterne.getNom());

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/card/collectible/composantExterneCard.fxml"));
            Pane cardPane = loader.load();
            ControllerComposantExterneCard controller = loader.getController();
            controller.setComposantExterne(composantExterne);

            hbox.getChildren().add(cardPane);
    		if(hbox.getChildren().size() == 2) {
    			hboxs.add(hbox);
    			hbox = createHBox(45, 1305, 210);
    		}
        }
        if(!hbox.getChildren().isEmpty()) {
            hboxs.add(hbox);
        }

        return hboxs;
    }

    public void addNewComposantExterneCard(ComposantExterne composantExterne) throws IOException {
        HBox hbox;
        boolean toAdd;
        if(hbox_composantExterne.get(hbox_composantExterne.size() - 1).getChildren().size() != 2) {
            hbox = hbox_composantExterne.get(hbox_composantExterne.size() - 1);
            toAdd = false;
        }
        else {
            hbox = createHBox(45, 1305, 210);
            toAdd = true;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/card/collectible/composantExterneCard.fxml"));
        Pane cardPane = loader.load();
        ControllerComposantExterneCard controller = loader.getController();
        controller.setComposantExterne(composantExterne);

        hbox.getChildren().add(cardPane);
        if(toAdd) {
            hbox_composantExterne.add(hbox);
        }
    }

     public List<HBox> createModArcheoCard() throws IOException {
        List<HBox> hboxs = new ArrayList<>();
        HBox hbox = createHBox(25, 1305, 290);

        for (ModArcheo modArcheo : CollectionCollectible.getModArcheo()) {
            Main.addTextLoad(" - " + modArcheo.getNom());

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/card/collectible/modArcheoCard.fxml"));
            Pane cardPane = loader.load();
            ControllerModArcheoCard controller = loader.getController();
            controller.setModArcheo(modArcheo);

            hbox.getChildren().add(cardPane);
    		if(hbox.getChildren().size() == 8) {
    			hboxs.add(hbox);
    			hbox = createHBox(25, 1305, 290);
    		}
        }
        if(!hbox.getChildren().isEmpty()) {
            hboxs.add(hbox);
        }

        return hboxs;
    }

    public void addNewModArcheoCard(ModArcheo modArcheo) throws IOException {
        HBox hbox;
        boolean toAdd;
        if(hbox_modArcheo.get(hbox_modArcheo.size() - 1).getChildren().size() != 8) {
            hbox = hbox_modArcheo.get(hbox_modArcheo.size() - 1);
            toAdd = false;
        }
        else {
            hbox = createHBox(25, 1305, 290);
            toAdd = true;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/card/collectible/modArcheoCard.fxml"));
        Pane cardPane = loader.load();
        ControllerModArcheoCard controller = loader.getController();
        controller.setModArcheo(modArcheo);

        hbox.getChildren().add(cardPane);
        if(toAdd) {
            hbox_modArcheo.add(hbox);
        }
    }

     public List<HBox> createModDeclenchementCard() throws IOException {
        List<HBox> hboxs = new ArrayList<>();
        HBox hbox = createHBox(25, 1305, 220);

        for (ModDeclenchement modDeclenchement : CollectionCollectible.getModDeclenchement()) {
            Main.addTextLoad(" - " + modDeclenchement.getNom());

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/card/collectible/modDeclenchementCard.fxml"));
            Pane cardPane = loader.load();
            ControllerModDeclenchementCard controller = loader.getController();
            controller.setModDeclenchement(modDeclenchement);

            hbox.getChildren().add(cardPane);
    		if(hbox.getChildren().size() == 8) {
    			hboxs.add(hbox);
    			hbox = createHBox(25, 1305, 220);
    		}
        }
        if(!hbox.getChildren().isEmpty()) {
            hboxs.add(hbox);
        }

        return hboxs;
    }

    public void addNewModDeclenchementCard(ModDeclenchement ModDeclenchement) throws IOException {
        HBox hbox;
        boolean toAdd;
        if(hbox_modDeclenchement.get(hbox_modDeclenchement.size() - 1).getChildren().size() != 8) {
            hbox = hbox_modDeclenchement.get(hbox_modDeclenchement.size() - 1);
            toAdd = false;
        }
        else {
            hbox = createHBox(25, 1305, 220);
            toAdd = true;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/card/collectible/modDeclenchementCard.fxml"));
        Pane cardPane = loader.load();
        ControllerModDeclenchementCard controller = loader.getController();
        controller.setModDeclenchement(ModDeclenchement);

        hbox.getChildren().add(cardPane);
        if(toAdd) {
            hbox_modDeclenchement.add(hbox);
        }
    }

    private List<HBox> createModCard(String type) throws IOException {
        List<HBox> hboxs = new ArrayList<>();
        HBox hbox = createHBox(25, 1305, 260);

        for(Mod mod : CollectionCollectible.getMod(type)) {
            Main.addTextLoad(" - " + mod.getNom());

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/card/collectible/modCard.fxml"));
            Pane cardPane = loader.load();
            ControllerModCard controller = loader.getController();
            controller.setMod(mod);

            hbox.getChildren().add(cardPane);
            if(hbox.getChildren().size() == 8) {
                hboxs.add(hbox);
                hbox = createHBox(25, 1305, 260);
            }
        }
        if(!hbox.getChildren().isEmpty()) {
            hboxs.add(hbox);
        }

        return hboxs;
    }

    public void addNewModCard(String type, Mod mod) throws IOException {
        HBox hbox;
        boolean toAdd;

        HBox lastHbox = liste_hbox_mod.get(type).get(liste_hbox_mod.get(type).size() - 1);
        if(lastHbox.getChildren().size() < 8) {
            hbox = lastHbox;
            toAdd = false;
        }
        else {
            hbox = createHBox(25, 1305, 260);
            toAdd = true;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/card/collectible/modCard.fxml"));
        Pane cardPane = loader.load();
        ControllerModCard controller = loader.getController();
        controller.setMod(mod);

        hbox.getChildren().add(cardPane);
        if(toAdd) {
            liste_hbox_mod.get(type).add(hbox);
        }
    }

    /* ---- GETTERS ----- */
    public List<HBox> getHBoxReacteur() {
        return hbox_reacteur;
    }
    public List<HBox> getHBoxComposantExterne() {
        return hbox_composantExterne;
    }
    public List<HBox> getHBoxModArcheo() {
        return hbox_modArcheo;
    }
    public List<HBox> getHBoxModDeclenchement() {
        return hbox_modDeclenchement;
    }
    public List<HBox> getHBoxMod(String type) {
        return liste_hbox_mod.get(type);
    }
      
}