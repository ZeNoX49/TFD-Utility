package app.controller.page;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.Main;
import app.collection.CollectionCollectible;
import app.collection.CollectionPrereglage;
import app.collection.CollectionProgression;
import app.model.collectible.ComposantExterne;
import app.model.collectible.Mod;
import app.model.collectible.ModArcheo;
import app.model.collectible.ModDeclenchement;
import app.model.prereglage.Prereglage;
import app.model.progression.Acolyte;
import app.model.progression.Arme;
import app.model.progression.Descendant;
import app.model.progression.Vehicule;
import app.util.manager.CollectibleManager;
import app.util.manager.PrereglageManager;
import app.util.manager.ProgressionManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class ControllerListPage {
    private final ProgressionManager progressionManager = ProgressionManager.getInstance();
    private final CollectibleManager collectibleManager = CollectibleManager.getInstance();
    private final PrereglageManager prereglageManager = PrereglageManager.getInstance();

    public static String type;
    public static String type_actif;

    @FXML private VBox vboxList, vbox_button;
    @FXML private Button button_refresh, button_plus;

    private Map<String, Button> liste_buttons = new HashMap<>();
    private final Map<String, IOAction> actionsProgression = new HashMap<>();
    private final Map<String, IOAction> actionsCollectible = new HashMap<>();
    private final Map<String, IOAction> actionsPrereglage = new HashMap<>();

    /* ------------------------- INITIALISATION ------------------------- */
    @FXML
    public void initialize() throws IOException {
        switch (type) {
            case "progression" -> initProgression();
            case "collectible" -> initCollectible();
            case "prereglage" -> initPrereglage();
        }
    }

    // Initialisation de la progression
    private void initProgression() throws IOException {
        setupButton(actionsProgression, "Descendants", this::useDescendant);
        setupButton(actionsProgression, "Armes", this::useArme);
        setupButton(actionsProgression, "Acolytes", this::useAcolyte);
        setupButton(actionsProgression, "Véhicules", this::useVehicule);

        switch (type_actif) {
            case "Descendants" -> useDescendant();
            case "Armes" -> useArme();
            case "Acolytes" -> useAcolyte();
            case "Véhicules" -> useVehicule();
            default -> useDescendant();
        }
    }

    // Initialisation des collectible
    private void initCollectible() throws IOException {
        setupButton(actionsCollectible, "Réacteurs", this::useReacteur);
        setupButton(actionsCollectible, "Composants Externes", this::useComposantExterne);

        addSpaceInVBox();
        
        setupButton(actionsCollectible, "Mods Archéoniques", this::useModArcheo);
        setupButton(actionsCollectible, "Mods Déclenchements", this::useModDeclenchement);

        addSpaceInVBox();

        for (String modType : Mod.TYPE_MOD) {
            setupButton(actionsCollectible, modType, () -> useMod(modType));
        }

        switch (type_actif) {
            case "Réacteurs" -> useReacteur();
            case "Composants Externes" -> useComposantExterne();
            case "Mods Archéoniques" -> useModArcheo();
            case "Mods Déclenchements" -> useModDeclenchement();
            default -> useMod(type_actif);
        }
    }

    // Initialisation des prereglages
    private void initPrereglage() throws IOException {
        setupButton(actionsPrereglage, "Auteur", () -> refreshPrereglage());
        setupButton(actionsPrereglage, "Nom", () -> refreshPrereglage());
        setupButton(actionsPrereglage, "Date", () -> refreshPrereglage());
        setupButton(actionsPrereglage, "Descendant", () -> refreshPrereglage());
        setupButton(actionsPrereglage, "Mot clé", () -> refreshPrereglage());

        usePrereglage();
    }

    /* ------------------------- UTILITAIRES FXML ------------------------- */
    @FXML
    private void retour() throws IOException {
        Main.loadHomePage();
    }

    @FXML
    private void addNew() throws IOException {
        switch (type) {
            case "progression" -> addNewProgression();
            case "collectible" -> addNewCollectible();
            case "prereglage" -> {   // Ajouter une carte dans préréglage
                Prereglage p = new Prereglage();
                CollectionPrereglage.addPrereglage(p);
                prereglageManager.addNewPrereglageCard(p);
                usePrereglage();
            }
        }
    }

    // Ajouter une carte dans progression
    private void addNewProgression() throws IOException {
        switch (type_actif) {
            case "Descendants" -> {
                Descendant d = new Descendant();
                CollectionProgression.addDescendant(d);
                progressionManager.addNewProgressionCard(progressionManager.getHBoxDescendant(), d, "d");
                useDescendant();
            }
            case "Armes" -> {
                Arme a = new Arme();
                CollectionProgression.addArme(a);
                progressionManager.addNewProgressionCard(progressionManager.getHBoxWeapon(), a, "w");
                useArme();
            }
            case "Acolytes" -> {
                Acolyte ac = new Acolyte();
                CollectionProgression.addAcolyte(ac);
                progressionManager.addNewProgressionCard(progressionManager.getHBoxAcolyte(), ac, "a");
                useAcolyte();
            }
            case "Véhicules" -> {
                Vehicule v = new Vehicule();
                CollectionProgression.addVehicule(v);
                progressionManager.addNewProgressionCard(progressionManager.getHBoxVehicule(), v, "v");
                useVehicule();
            }
        }
    }

    // Ajouter une carte dans collectible
    private void addNewCollectible() throws IOException {
        switch (type_actif) {
            case "Réacteurs"  -> {}
            case "Composants Externes" -> {
                ComposantExterne ce = new ComposantExterne();
                CollectionCollectible.addComposantExterne(ce);
                collectibleManager.addNewComposantExterneCard(ce);
                useComposantExterne();
            }
            case "Mods Archéoniques" -> {
                ModArcheo ma = new ModArcheo();
                CollectionCollectible.addModArcheo(ma);
                collectibleManager.addNewModArcheoCard(ma);
                useModArcheo();
            }
            case "Mods Déclenchements" -> {
                ModDeclenchement md = new ModDeclenchement();
                CollectionCollectible.addModDeclenchement(md);
                collectibleManager.addNewModDeclenchementCard(md);
                useModDeclenchement();
            }
            default -> {
                Mod m = new Mod();
                m.setType(type_actif);
                CollectionCollectible.addMod(m);
                collectibleManager.addNewModCard(type_actif, m);
                useMod(type_actif);
            }
        }
    }

    @FXML
    private void refresh() throws IOException {
        System.out.println(type + " -> " + type_actif);
        switch (type) {
            case "progression" -> {
                switch (type_actif) {
                    case "Descendants" -> CollectionProgression.sortDescendant();
                    case "Armes" -> CollectionProgression.sortArme();
                    case "Acolyte" -> CollectionProgression.sortAcolyte();
                    case "Véhicule" -> CollectionProgression.sortVehicule();
                }
                actionsProgression.get(type_actif).run();
            }
            case "collectible" -> {
                switch (type_actif) {
                    case "Réacteurs" -> CollectionCollectible.sortReacteur();
                    case "Composants Externes" -> CollectionCollectible.sortComposantExterne();
                    case "Mods Archéoniques" -> CollectionCollectible.sortModArcheo();
                    case "Mods Déclenchements" -> CollectionCollectible.sortmodDeclenchement();
                    default -> CollectionCollectible.sortMod(type_actif);
                }
                actionsCollectible.get(type_actif).run();
            }
            case "prereglage" -> refreshPrereglage();
        }
    }

    private void refreshPrereglage() {
        try {
            setButton();
            CollectionPrereglage.sortPrereglage(type_actif);
            prereglageManager.refreshPrereglageCard();
            usePrereglage();
        } catch (IOException e) { e.printStackTrace(); }
    }

    /* ------------------------- METHODES USE ------------------------- */
    // Progression
    private void useDescendant() throws IOException { displayCard(progressionManager.getHBoxDescendant()); setButton(); }
    private void useArme() throws IOException{ displayCard(progressionManager.getHBoxWeapon()); setButton(); }
    private void useAcolyte() throws IOException{ displayCard(progressionManager.getHBoxAcolyte()); setButton(); }
    private void useVehicule() throws IOException{ displayCard(progressionManager.getHBoxVehicule()); setButton(); }
    
    // Collectible
    private void useReacteur() throws IOException{ displayCard(collectibleManager.getHBoxReacteur()); setButton(); }
    private void useComposantExterne() throws IOException{ displayCard(collectibleManager.getHBoxComposantExterne()); setButton(); }
    private void useModArcheo() throws IOException{ displayCard(collectibleManager.getHBoxModArcheo()); setButton(); }
    private void useModDeclenchement() throws IOException{ displayCard(collectibleManager.getHBoxModDeclenchement()); setButton(); }
    private void useMod(String type) throws IOException {
        displayCard(collectibleManager.getHBoxMod(type));
        liste_buttons.forEach((k, btn) -> btn.setDisable(k.equals(type)));
    }

    // Prereglage
    private void usePrereglage() throws IOException { displayCard(prereglageManager.getHBoxPrereglage()); }

    // --- Outils ---
    private Button createButton(String txt) {
        Button button = new Button();
        button.setText(txt);
        button.setPrefSize(200, 50);
        button.setMinSize(200, 50);
        button.setMaxSize(200, 50);
        button.setTextAlignment(TextAlignment.CENTER);
        button.setFont(new Font("System", 20));
        return button;
    }

    // --- Méthode pour initialiser les boutons et la map --- //
    private void setupButton(Map<String, IOAction> map, String label, IOAction action) {
        type_actif = label;
        map.put(label, action);
        addButtonToVBox(label, action);
    }

    // --- Méthode utilitaire pour ajouter un bouton --- //
    private Button addButtonToVBox(String label, IOAction action) {
        Button btn = createButton(label);
        btn.setOnAction(_ -> {
            try { 
                action.run(); 
                type_actif = label;
                setButton(); // Désactive tous les autres et active celui-ci
            } catch (IOException e) { e.printStackTrace(); } 
        });
        vbox_button.getChildren().add(btn);
        liste_buttons.put(label, btn); // Ajout dans la Map
        return btn;
    }

    private void addSpaceInVBox() {
        Pane pane = new Pane();
        pane.setPrefSize(100, 25);
        vbox_button.getChildren().add(pane);
    }

    // --- Méthode pour afficher les cartes --- //
    private void displayCard(List<HBox> hboxs) {
        vboxList.getChildren().setAll(hboxs);
    }

    // --- Méthode pour activer/désactiver boutons --- //
    private void setButton() {
        liste_buttons.forEach((code, btn) -> btn.setDisable(code.equals(type_actif)));
    }
}

// Pour éviter les bugs
interface IOAction {
    void run() throws IOException;
}