package app;

import java.io.IOException;

import app.controller.page.ControllerHomePage;
import app.io.JSONLoader;
import app.io.JSONSaver;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static Stage primaryStage;

    private static ControllerHomePage controllerHomePage;
    private static  Scene homeScene;

    private static Object currentController;

    public static boolean devMode = false;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        JSONLoader.load();
        
        // Charger la page d'accueil une seule fois
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/page/homePage.fxml"));
        homeScene = new Scene(loader.load());
        controllerHomePage = loader.getController();

        primaryStage.setOnCloseRequest(_ -> {
            JSONSaver.save();
            Platform.exit();
        });

        primaryStage.setScene(homeScene);
        primaryStage.setTitle("The First Descendant - Utilitaire");
        primaryStage.show();
    }

    public static void main(String[] args) { launch(args); }

    /* ---------------------------------------------------------------------------- */
    
    public static void switchScene(String fichier) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/page/" + fichier));
            Scene scene = new Scene(loader.load());

            currentController = loader.getController();
            
            primaryStage.setTitle("The First Descendant - Utilitaire");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            System.err.println("Erreur de chargement de la scène : " + fichier);
            e.printStackTrace();
        }
    }

    public static void loadHomePage() {
        primaryStage.setTitle("The First Descendant - Utilitaire");
        primaryStage.setScene(homeScene);
        primaryStage.show();
    }

    public static void addTextLoad(String text) {
        controllerHomePage.addLoad(text);
        System.out.println(text);
    }

    public static void addTextErreur(String type, String classe, String fonction) {
        String text = "Erreur %s | %s -> %s()".formatted(type, classe, fonction);
        controllerHomePage.addErreur(text);
        System.err.println(text);
    }

    public static Object getCurrentController() {
        return currentController;
    }

}