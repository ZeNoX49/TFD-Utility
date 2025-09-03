package app.util.combobox_item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import app.model.collectible.Mod;
import app.util.manager.ImageManager;
import javafx.scene.image.Image;

public class PolariteItem implements HasImage {
    static ImageManager imageManager = ImageManager.getInstance();

    private static HashMap<String, PolariteItem> hashMap = new HashMap<>();
    private final String polarite;
    private final Image image;

    static {
        for(String key : Mod.POLARITE.get("noir").keySet()) {
            Image img = imageManager.getImage(Mod.POLARITE.get("noir").get(key), 20, 20);
            new PolariteItem(key, img);
        }
    }

    public PolariteItem(String polarite, Image image) {
        this.polarite = polarite;
        this.image = image;
        hashMap.put(polarite, this);
    }

    public String getPolarite() {
        return polarite;
    }

    @Override
    public Image getImage() {
        return image;
    }

    public static PolariteItem getItemByPolarite(String polarite) {
        return hashMap.get(polarite);
    }

    public static List<PolariteItem> getItems() {
        return new ArrayList<>(hashMap.values());
    }

    @Override
    public String toString() {
        return ""; // <-- évite que du texte s'affiche dans le ComboBox
    }
}