package app.controller.card.prereglage;

import app.model.collectible.Mod;
import app.model.collectible.ModArcheo;
import app.util.manager.ImageManager;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class ControllerModArcheoCardDisplay {
    private ImageManager imageManager = ImageManager.getInstance();
    private ModArcheo modArcheo;

    @FXML private ImageView imgModArcheo;
    @FXML private ImageView imgPolarite;


    public void setModArcheo(ModArcheo modArcheo) {
        this.modArcheo = modArcheo;
        imgModArcheo.setImage(imageManager.getImage(modArcheo.getImg(), 64, 64));
        imgPolarite.setImage(imageManager.getImage(Mod.POLARITE.get("blanc").get(modArcheo.getPolarite()), 20, 20));
    }

    public void setModArcheoVide() {
        imgModArcheo.setImage(null);
        imgPolarite.setImage(null);
    }

    public ModArcheo getModArcheo() {
        return modArcheo;
    }

}
