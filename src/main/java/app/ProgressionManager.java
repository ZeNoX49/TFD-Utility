package app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import app.Collection.CollectionProgression;
import app.controller.card.progression.ControllerProgressionCard;
import app.model.progression.Progression;
import app.util.Manager;
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
    
    private int[] hboxData = new int[] {45, 1305, 235};
    private List<HBox> hbox_descendant, hbox_weapon, hbox_acolyte, hbox_vehicule;

    public void initialize() throws IOException {
        setCardDescendant();
        setCardWeapon();
        setCardAcolyte();
        setCardVehicule();
    }

    public void setCardDescendant() throws IOException {
        hbox_descendant = setProgressionCard(new ArrayList<>(CollectionProgression.getDescendant()), "Descendants", "d");
    }
    public void setCardWeapon() throws IOException {
        hbox_weapon = setProgressionCard(new ArrayList<>(CollectionProgression.getArme()), "Armes", "w");
    }
    public void setCardAcolyte() throws IOException {
        hbox_acolyte = setProgressionCard(new ArrayList<>(CollectionProgression.getAcolyte()), "Acolytes", "a");
    }
    public void setCardVehicule() throws IOException {
        hbox_vehicule = setProgressionCard(new ArrayList<>(CollectionProgression.getVehicule()), "Vehicules", "v");
    }

    private List<HBox> setProgressionCard(List<Progression> progressions, String txtChargement, String type) throws IOException {
        Main.addTextLoad("\n" + txtChargement + " :");
        return createProgressionCard(progressions, type);
    }

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

    public void addNewProgressionCard(List<HBox> hbox_list, Progression progression, String type) throws IOException {
        HBox hbox;
        boolean toAdd;
        if(hbox_list.get(hbox_list.size() - 1).getChildren().size() != 3) {
            hbox = hbox_list.get(hbox_list.size() - 1);
            toAdd = false;
        }
        else {
            hbox = createHBox(hboxData[0], hboxData[1], hboxData[2]);
            toAdd = true;
        }

        Pane cardPane = getCardPane(progression, type);

        hbox.getChildren().add(cardPane);
        if(toAdd) {
            hbox_list.add(hbox);
        }
    }

    /* ---- GETTERS ----- */
    private Pane getCardPane(Progression progression, String type) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/card/progression/progressionCard.fxml"));
        Pane cardPane = loader.load();
        ControllerProgressionCard controller = loader.getController();
        controller.setProgression(progression, type);
        return cardPane;
    }

    public List<HBox> getHBoxDescendant() {
        return hbox_descendant;
    }
    public List<HBox> getHBoxWeapon() {
        return hbox_weapon;
    }
    public List<HBox> getHBoxAcolyte() {
        return hbox_acolyte;
    }
    public List<HBox> getHBoxVehicule() {
        return hbox_vehicule;
    }

}