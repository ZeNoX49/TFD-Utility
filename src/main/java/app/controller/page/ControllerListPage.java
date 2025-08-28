package app.controller.page;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.Collection.CollectionCollectible;
import app.Collection.CollectionPrereglage;
import app.Collection.CollectionProgression;
import app.Main;
import app.ProgressionManager;
import app.model.collectible.ComposantExterne;
import app.model.collectible.Mod;
import app.model.collectible.ModArcheo;
import app.model.collectible.ModDeclenchement;
import app.model.prereglage.Prereglage;
import app.model.progression.Acolyte;
import app.model.progression.Arme;
import app.model.progression.Descendant;
import app.model.progression.Vehicule;
import app.util.CollectibleManager;
import app.util.PrereglageManager;
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

    private final Map<String, Button> liste_buttons = new HashMap<>();
    private final Map<String, Runnable> actionsProgression = new HashMap<>();
    private final Map<String, Runnable> actionsCollectible = new HashMap<>();
    private final Map<String, Runnable> actionsPrereglage = new HashMap<>();

    @FXML
    public void initialize() throws IOException {
        switch (type) {
            case "progression" -> initProgression();
            case "collectible" -> initCollectible();
            case "prereglage" -> initPrereglage();
        }
    }

    private void initProgression() throws IOException {
        actionsProgression.put("d", this::useDescendant);
        actionsProgression.put("w", this::useArme);
        actionsProgression.put("a", this::useAcolyte);
        actionsProgression.put("v", this::useVehicule);

        addButtonToVBox(vbox_button, "d", "Descendants", this::useDescendant);
        addButtonToVBox(vbox_button, "w", "Armes", this::useArme);
        addButtonToVBox(vbox_button, "a", "Acolytes", this::useAcolyte);
        addButtonToVBox(vbox_button, "v", "Véhicules", this::useVehicule);

        switch (type_actif) {
            case "d" -> useDescendant();
            case "w" -> useArme();
            case "a" -> useAcolyte();
            case "v" -> useVehicule();
            default -> useDescendant();
        }
    }

    private void initCollectible() throws IOException {
        actionsCollectible.put("r", this::useReacteur);
        actionsCollectible.put("ce", this::useComposantExterne);
        actionsCollectible.put("ma", this::useModArcheo);
        actionsCollectible.put("md", this::useModDeclenchement);

        addButtonToVBox(vbox_button, "r", "Réacteurs", this::useReacteur);
        addButtonToVBox(vbox_button, "ce", "Composants Externes", this::useComposantExterne);

        addSpaceInVBox();

        addButtonToVBox(vbox_button, "ma", "Mods Archéoniques", this::useModArcheo);
        addButtonToVBox(vbox_button, "md", "Mods Déclenchements", this::useModDeclenchement);

        addSpaceInVBox();

        type_actif = Mod.TYPE_MOD.get(0);
        for (String modType : Mod.TYPE_MOD) {
            addButtonToVBox(vbox_button, modType, modType, () -> {
                type_actif = modType;
                useMod(type_actif);
            });
        }
        // Active le premier bouton au démarrage
        setButton(type_actif);
        useMod(type_actif);
    }

    private void initPrereglage() throws IOException {
        actionsPrereglage.put("auteur", () -> refreshPrereglage("auteur"));
        actionsPrereglage.put("nom", () -> refreshPrereglage("nom"));
        actionsPrereglage.put("date", () -> refreshPrereglage("date"));
        actionsPrereglage.put("descendant", () -> refreshPrereglage("descendant"));
        actionsPrereglage.put("motcle", () -> refreshPrereglage("motcle"));

        addButtonToVBox(vbox_button, "auteur", "Auteur", () -> refreshPrereglage("auteur"));
        addButtonToVBox(vbox_button, "nom", "Nom", () -> refreshPrereglage("nom"));
        addButtonToVBox(vbox_button, "date", "Date", () -> refreshPrereglage("date"));
        addButtonToVBox(vbox_button, "descendant", "Descendant", () -> refreshPrereglage("descendant"));
        addButtonToVBox(vbox_button, "motcle", "Mot clé", () -> refreshPrereglage("motcle"));

        usePrereglage();
    }

    @FXML
    private void retour() throws IOException {
        Main.loadHomePage();
    }

    @FXML
    private void addNew() throws IOException {
        switch (type) {
            case "progression" -> addNewProgression();
            case "collectible" -> addNewCollectible();
            case "prereglage" -> {
                Prereglage p = new Prereglage();
                CollectionPrereglage.addPrereglage(p);
                prereglageManager.addNewPrereglageCard(p);
                usePrereglage();
            }
        }
    }

    private void addNewProgression() throws IOException {
        if (isButtonDisabled("d")) {
            Descendant d = new Descendant();
            CollectionProgression.addDescendant(d);
            progressionManager.addNewProgressionCard(progressionManager.getHBoxDescendant(), d, "d");
            useDescendant();
        } else if (isButtonDisabled("w")) {
            Arme a = new Arme();
            CollectionProgression.addArme(a);
            progressionManager.addNewProgressionCard(progressionManager.getHBoxWeapon(), a, "w");
            useArme();
        } else if (isButtonDisabled("a")) {
            Acolyte ac = new Acolyte();
            CollectionProgression.addAcolyte(ac);
            progressionManager.addNewProgressionCard(progressionManager.getHBoxAcolyte(), ac, "a");
            useAcolyte();
        } else if (isButtonDisabled("v")) {
            Vehicule v = new Vehicule();
            CollectionProgression.addVehicule(v);
            progressionManager.addNewProgressionCard(progressionManager.getHBoxVehicule(), v, "v");
            useVehicule();
        }
    }

    private void addNewCollectible() throws IOException {
        if (isButtonDisabled("ce")) {
            ComposantExterne ce = new ComposantExterne();
            CollectionCollectible.addComposantExterne(ce);
            collectibleManager.addNewComposantExterneCard(ce);
            useComposantExterne();
        }
        else if (isButtonDisabled("ma")) {
            ModArcheo ma = new ModArcheo();
            CollectionCollectible.addModArcheo(ma);
            collectibleManager.addNewModArcheoCard(ma);
            useModArcheo();
        }
        else if (isButtonDisabled("md")) {
            ModDeclenchement md = new ModDeclenchement();
            CollectionCollectible.addModDeclenchement(md);
            collectibleManager.addNewModDeclenchementCard(md);
            useModDeclenchement();
        }
        else {
            Mod m = new Mod();
            m.setType(type_actif);
            CollectionCollectible.addMod(m);
            collectibleManager.addNewModCard(type_actif, m);
            useMod(type_actif);
        }
    }

    @FXML
    private void refresh() throws IOException {
        switch (type) {
            case "progression" -> actionsProgression.getOrDefault(getCurrentCode(), () -> {}).run();
            case "collectible" -> actionsCollectible.getOrDefault(getCurrentCode(), () -> {}).run();
            case "prereglage" -> actionsPrereglage.getOrDefault(getCurrentCode(), () -> {}).run();
        }
    }

    private void refreshPrereglage(String sortKey) {
        try {
            setButton(sortKey);
            CollectionPrereglage.sortPrereglage(sortKey);
            prereglageManager.cardPrereglage();
            usePrereglage();
        } catch (IOException e) { e.printStackTrace(); }
    }

    // --- Méthodes use ---
    private void useDescendant() { displayCard(progressionManager.getHBoxDescendant()); setButton("d"); }
    private void useArme() { displayCard(progressionManager.getHBoxWeapon()); setButton("w"); }
    private void useAcolyte() { displayCard(progressionManager.getHBoxAcolyte()); setButton("a"); }
    private void useVehicule() { displayCard(progressionManager.getHBoxVehicule()); setButton("v"); }
    
    private void useReacteur() { displayCard(collectibleManager.getHBoxReacteur()); setButton("r"); }
    private void useComposantExterne() { displayCard(collectibleManager.getHBoxComposantExterne()); setButton("ce"); }
    private void useModArcheo() { displayCard(collectibleManager.getHBoxModArcheo()); setButton("ma"); }
    private void useModDeclenchement() { displayCard(collectibleManager.getHBoxModDeclenchement()); setButton("md"); }
    private void useMod(String type) {
        displayCard(collectibleManager.getHBoxMod(type));
        liste_buttons.forEach((k, btn) -> btn.setDisable(k.equals(type)));
    }

    private void usePrereglage() { displayCard(prereglageManager.getHBoxPrereglage()); }

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

    // --- Méthode utilitaire pour ajouter un bouton ---
    private Button addButtonToVBox(VBox vbox, String code, String label, Runnable action) {
        Button btn = createButton(label);
        btn.setOnAction(e -> { 
            try { 
                action.run(); 
                setButton(code); // Désactive tous les autres et active celui-ci
            } catch (Exception ex) { ex.printStackTrace(); } 
        });
        vbox.getChildren().add(btn);
        liste_buttons.put(code, btn); // Ajout dans la Map
        return btn;
    }

    private void addSpaceInVBox() {
        Pane pane = new Pane();
        pane.setPrefSize(100, 25);
        vbox_button.getChildren().add(pane);
    }

    private void displayCard(List<HBox> hboxs) {
        vboxList.getChildren().setAll(hboxs);
    }

    // --- Méthode pour vérifier si un bouton est actif ---
    private boolean isButtonDisabled(String code) {
        Button btn = liste_buttons.get(code);
        return btn != null && btn.isDisable();
    }

    private String getCurrentCode() {
        // retourne le code du bouton actif selon type_actif
        return switch (type) {
            case "progression" -> actionsProgression.entrySet().stream().filter(e -> isButtonDisabled(e.getKey())).map(Map.Entry::getKey).findFirst().orElse("");
            case "collectible" -> actionsCollectible.entrySet().stream().filter(e -> isButtonDisabled(e.getKey())).map(Map.Entry::getKey).findFirst().orElse("");
            case "prereglage" -> actionsPrereglage.entrySet().stream().filter(e -> isButtonDisabled(e.getKey())).map(Map.Entry::getKey).findFirst().orElse("");
            default -> "";
        };
    }

    // --- Méthode pour activer/désactiver boutons ---
    private void setButton(String activeCode) {
        liste_buttons.forEach((code, btn) -> btn.setDisable(code.equals(activeCode)));
    }
}