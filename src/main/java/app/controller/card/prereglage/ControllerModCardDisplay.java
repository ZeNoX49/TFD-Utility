package app.controller.card.prereglage;

import app.model.collectible.Mod;
import app.util.ImageManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class ControllerModCardDisplay {
    private ImageManager imageManager = ImageManager.getInstance();
    private Mod mod;

    @FXML private ImageView imgMod;
    @FXML private ImageView imgpolarite;
    @FXML private Label labelCout;
    @FXML private Label labelNom;

    public void setModVide() {
        labelNom.setText("");
        labelCout.setText("");
        imgpolarite.setVisible(false);
        imgMod.setImage(imageManager.getImage(getClass().getResource("/img/slotVide/mod.png").toExternalForm(), 133, 180));
    }

    public void setMod(Mod mod) {
        this.mod = mod;
        labelNom.setText(mod.getNom());
        imgpolarite.setVisible(true);
        setPolariteBlanc();
        imgMod.setImage(imageManager.getImage(mod.getImg(), 133, 180));
    }

    public void setPolariteBlanc() {
        labelCout.setText(mod.getCoutMax());
        labelCout.setTextFill(Color.web("#c8c8c8"));
        imgpolarite.setImage(imageManager.getImage(Mod.POLARITE.get("blanc").get(mod.getPolarite()), 25, 21));
    }

    public void setPolariteVert() {
        int resultat = (int) Math.ceil((double) Integer.parseInt(mod.getCoutMax()) / 2);
        labelCout.setText(String.valueOf(resultat));
        labelCout.setTextFill(Color.web("#00ff00"));
        imgpolarite.setImage(imageManager.getImage(Mod.POLARITE.get("vert").get(mod.getPolarite()), 25, 21));
    }

    public Mod getMod() {
        return mod;
    }

}
