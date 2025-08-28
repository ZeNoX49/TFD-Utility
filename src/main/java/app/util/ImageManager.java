package app.util;

import app.Main;
import javafx.scene.image.Image;

public class ImageManager {
    private static ImageManager instance;
    public static ImageManager getInstance() {
        if(instance == null) {
            instance = new ImageManager();
        }
        return instance;
    }
    
    public Image getImage(String urlImage, int sizeX, int sizeY) {
        try {
            return new Image(urlImage, sizeX, sizeY, true, true);
        } catch (Exception e) {
            Main.addTextErreur("chargement de l'image : " + urlImage, "ImageManager", "getImage");
            return new Image(getClass().getResource("/img/Erreur404.png").toExternalForm(), 111, 73, true, true);
        }
    }
}
