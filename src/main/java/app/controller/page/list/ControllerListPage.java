package app.controller.page.list;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public abstract class ControllerListPage {
    @FXML protected VBox vboxList, vbox_button;
    @FXML protected Button button_refresh, button_plus;

    public static String type;
    public static String type_actif;
    private Map<String, Button> liste_buttons = new HashMap<>();

    @FXML
    public void initialize() throws IOException {
        button_plus.setDisable(!Main.devMode);
        this.initializeType();
    }
    
    /**
     * appeler dans initialize() pour initialiser la page selon son type
     */
    public abstract void initializeType() throws IOException;

    @FXML
    private void retour() throws IOException {
        Main.loadHomePage();
    }

    @FXML
    private void addNew() throws IOException {
        this.addNewType();
    }

    public abstract void addNewType() throws IOException;

    @FXML
    private void refresh() throws IOException {
        System.out.println(type + " -> " + type_actif);
        this.refreshType();
    }

    public abstract void refreshType() throws IOException;

    // --- Outils ---
    public Button createButton(String txt) {
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
    public void setupButton(Map<String, IOAction> map, String label, IOAction action) {
        type_actif = label;
        map.put(label, action);
        this.addButtonToVBox(label, action);
    }

    // --- Méthode utilitaire pour ajouter un bouton --- //
    private Button addButtonToVBox(String label, IOAction action) {
        Button btn = createButton(label);
        btn.setOnAction(_ -> {
            try { 
                action.run(); 
                type_actif = label;
                this.setButton(); // Désactive tous les autres et active celui-ci
            } catch (IOException e) { e.printStackTrace(); } 
        });
        vbox_button.getChildren().add(btn);
        liste_buttons.put(label, btn); // Ajout dans la Map
        return btn;
    }

    public void addSpaceInVBox() {
        Pane pane = new Pane();
        pane.setPrefSize(100, 25);
        vbox_button.getChildren().add(pane);
    }

    // --- Méthode pour afficher les cartes --- //
    public void displayCard(List<HBox> hboxs) {
        vboxList.getChildren().setAll(hboxs);
    }

    // --- Méthode pour activer/désactiver boutons --- //
    public void setButton() {
        liste_buttons.forEach((code, btn) -> btn.setDisable(code.equals(type_actif)));
    }
}

// Pour éviter les bugs
interface IOAction {
    void run() throws IOException;
}