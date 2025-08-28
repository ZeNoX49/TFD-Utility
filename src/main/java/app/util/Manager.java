package app.util;

import javafx.scene.layout.HBox;

/**
 * Classe abstraite de base pour tous les Managers.
 * Fournit des méthodes utilitaires pour gérer les cartes et les conteneurs.
 */
public abstract class Manager {

    // public List<HBox> getCard(List<Object> objects, String txtChargement, int[] hboxData) throws IOException {
    //     Main.addTextLoad("\n" + txtChargement + " :");
    //     return createCard(objects, "d", hboxData);
    // }

    // private List<HBox> createCard(List<Object> objects, String type, int[] hboxData) {
    //     List<HBox> hboxs = new ArrayList<>();
    //     HBox hbox = createHBox(hboxData[0], hboxData[1], hboxData[2]);

    //     for (Object object : objects) {
    //         Main.addTextLoad(" - " + object.());
    //     return new ArrayList<>();
    // }
        

    /* ------------------------------------------------------------------------------------------------------------------- */

    public HBox createHBox(int spacing, int width, int height) {
        HBox hbox = new HBox();
        hbox.setSpacing(spacing);
        hbox.setPrefSize(width, height);
        hbox.setMinSize(width, height);
        hbox.setMaxSize(width, height);
        hbox.setStyle("-fx-background-color: #282828");
        return hbox;
    }

}