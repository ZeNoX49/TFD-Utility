package app.util.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import app.Collection.CollectionProgression;
import app.Main;
import app.controller.card.progression.ControllerProgressionCard;
import app.model.progression.Progression;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class ProgressionManager extends Manager {
    private static ProgressionManager instance;
    public static ProgressionManager getInstance() {
        if(instance == null) {
            instance = new ProgressionManager();
        }
        return instance;
    }

    /* -------------------------------------------------- */
    
    private int[] hboxData = new int[] {45, 1305, 235};
    private List<HBox> hbox_descendant, hbox_weapon, hbox_acolyte, hbox_vehicule;

    /* ----- Getters -----  */
    public List<HBox> getHBoxDescendant() throws IOException {
        if(hbox_descendant == null || hbox_descendant.isEmpty()) {
            hbox_descendant = setProgressionCard(new ArrayList<>(CollectionProgression.getDescendant()), "Descendants", "d");
        }
        return hbox_descendant;
    }
    public List<HBox> getHBoxWeapon() throws IOException {
        if(hbox_weapon == null || hbox_weapon.isEmpty()) {
            hbox_weapon = setProgressionCard(new ArrayList<>(CollectionProgression.getArme()), "Armes", "w");
        }
        return hbox_weapon;
    }
    public List<HBox> getHBoxAcolyte() throws IOException {
        if(hbox_acolyte == null || hbox_acolyte.isEmpty()) {
            hbox_acolyte = setProgressionCard(new ArrayList<>(CollectionProgression.getAcolyte()), "Acolytes", "a");
        }
        return hbox_acolyte;
    }
    public List<HBox> getHBoxVehicule() throws IOException {
        if(hbox_vehicule == null || hbox_vehicule.isEmpty()) {
            hbox_vehicule = setProgressionCard(new ArrayList<>(CollectionProgression.getVehicule()), "Vehicules", "v");
        }
        return hbox_vehicule;
    }

    // Affichage
    private List<HBox> setProgressionCard(List<Progression> progressions, String txtChargement, String type) throws IOException {
        Main.addTextLoad("\n" + txtChargement + " :");
        return createProgressionCard(progressions, type);
    }

    // Création des cartes
    public List<HBox> createProgressionCard(List<Progression> progressions, String type) throws IOException {
        List<HBox> hboxs = new ArrayList<>();
        HBox hbox = createHBox(hboxData[0], hboxData[1], hboxData[2]);

        for (Progression progression : progressions) {
            Main.addTextLoad(" - " + progression.getName());

            Pane cardPane = getCardPane(progression, type);

            hbox.getChildren().add(cardPane);
    		if(hbox.getChildren().size() == 3) {
    			hboxs.add(hbox);
                hbox = createHBox(hboxData[0], hboxData[1], hboxData[2]);
    		}
        }
        if(!hbox.getChildren().isEmpty()) {
            hboxs.add(hbox);
        }

        return hboxs;
    }

    // Ajout d'une nouvelle carte
    public void addNewProgressionCard(List<HBox> hbox_list, Progression progression, String type) throws IOException {
        HBox hbox = hbox_list.get(hbox_list.size() - 1);
        if(hbox.getChildren().size() == 3) {
            hbox = createHBox(hboxData[0], hboxData[1], hboxData[2]);
            hbox_list.add(hbox);
        }

        Pane cardPane = getCardPane(progression, type);
        hbox.getChildren().add(cardPane);
    }

    // Obtenir une carte
    private Pane getCardPane(Progression progression, String type) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/card/progression/progressionCard.fxml"));
        Pane cardPane = loader.load();
        ControllerProgressionCard controller = loader.getController();
        controller.setProgression(progression, type);
        return cardPane;
    }

}