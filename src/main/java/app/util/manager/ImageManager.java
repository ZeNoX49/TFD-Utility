package app.util.manager;

import java.util.HashMap;
import java.util.Map;

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
    
    private static Map<ImgVar, Image> cache = new HashMap<>();
    public Image getImage(String urlImage, int sizeX, int sizeY) {
        ImgVar imgVar = new ImgVar(urlImage, sizeX, sizeY);
        if(cache.containsKey(imgVar)) {
            return cache.get(imgVar);
        }
        try {
            Image image = new Image(urlImage, sizeX, sizeY, true, true);
            cache.put(imgVar, image);
            return image;
        } catch (Exception e) {
            Main.addTextErreur("chargement de l'image : " + urlImage, "ImageManager", "getImage");
            return new Image(getClass().getResource("/img/Erreur404.png").toExternalForm(), 111, 73, true, true);
        }
    }
}

class ImgVar {
    private String imgLink;
    private int sizeX;
    private int sizeY;

    public ImgVar(String imgLink, int sizeX, int sizeY) {
        this.imgLink = imgLink;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ImgVar imgVar = (ImgVar) obj;
        return this.imgLink.equals(imgVar.imgLink)
               && this.sizeX == imgVar.sizeX
               && this.sizeY == imgVar.sizeY;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(imgLink, sizeX, sizeY);
    }
}