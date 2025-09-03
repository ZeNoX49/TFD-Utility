package app.util.combobox_item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import app.model.progression.Arme;
import app.util.manager.ImageManager;
import javafx.scene.image.Image;

public class AmeliorationArmeItem implements HasImage {
    static ImageManager imageManager = ImageManager.getInstance();
    
    private static HashMap<String, AmeliorationArmeItem> hashMap = new HashMap<>();
    private final String name;
    private final Image image;

    static {
        for(String key :Arme.AMELIORATION_IMG.keySet()) {
            Image img = imageManager.getImage(Arme.AMELIORATION_IMG.get(key), 20, 20);
            new AmeliorationArmeItem(key, img);
        }
    }

    public AmeliorationArmeItem(String name, Image image) {
        this.name = name;
        this.image = image;
        hashMap.put(name, this);
    }

    public String getName() {
        return name;
    }

    @Override
    public Image getImage() {
        return image;
    }

    public static AmeliorationArmeItem getAmeliorationByKey(String key) {
        return hashMap.get(key);
    }

    public static List<AmeliorationArmeItem> getItems() {
        return new ArrayList<>(hashMap.values());
    }

    @Override
    public String toString() {
        return ""; // <-- évite que du texte s'affiche dans le ComboBox
    }
}
