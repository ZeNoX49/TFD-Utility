package app.model.prereglage;

import java.util.HashMap;
import java.util.Map;

import app.Main;
import app.model.collectible.Mod;
import app.model.progression.Descendant;
import app.util.manager.ImageManager;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class GridArcheo {
    private final static Map<String, String> IMG_COULEUR = new HashMap<>();
    static {
        IMG_COULEUR.put("commun", Main.class.getResource("/img/buildArcheo/couleur/buildArcheo_commun.png").toExternalForm());
        IMG_COULEUR.put("rare", Main.class.getResource("/img/buildArcheo/couleur/buildArcheo_rare.png").toExternalForm());
        IMG_COULEUR.put("legendaire", Main.class.getResource("/img/buildArcheo/couleur/buildArcheo_legendaire.png").toExternalForm());
        IMG_COULEUR.put("base", Main.class.getResource("/img/buildArcheo/couleur/buildArcheo_base.png").toExternalForm());
        IMG_COULEUR.put("mod", Main.class.getResource("/img/buildArcheo/couleur/buildArcheo_mod.png").toExternalForm());
        IMG_COULEUR.put("ajout", Main.class.getResource("/img/buildArcheo/couleur/buildArcheo_ajout.png").toExternalForm());
    }

    private final static Map<String, String> IMG_SYMBOLE = new HashMap<>();
    static {
        IMG_SYMBOLE.put("Accuracy", Main.class.getResource("/img/buildArcheo/symbole/Icon_NodeIcon_Accuracy.png").toExternalForm());
        IMG_SYMBOLE.put("AdvantageCoefficient", Main.class.getResource("/img/buildArcheo/symbole/Icon_NodeIcon_AdvantageCoefficient.png").toExternalForm());
        IMG_SYMBOLE.put("AllElementalDEF", Main.class.getResource("/img/buildArcheo/symbole/Icon_NodeIcon_AllElementalDEF.png").toExternalForm());
        IMG_SYMBOLE.put("ATK", Main.class.getResource("/img/buildArcheo/symbole/Icon_NodeIcon_ATK.png").toExternalForm());
        IMG_SYMBOLE.put("CriticalATKIncrease", Main.class.getResource("/img/buildArcheo/symbole/Icon_NodeIcon_CriticalATKIncrease.png").toExternalForm());
        IMG_SYMBOLE.put("CriticalChance", Main.class.getResource("/img/buildArcheo/symbole/Icon_NodeIcon_CriticalChance.png").toExternalForm());
        IMG_SYMBOLE.put("DEF", Main.class.getResource("/img/buildArcheo/symbole/Icon_NodeIcon_DEF.png").toExternalForm());
        IMG_SYMBOLE.put("DEFBlazer", Main.class.getResource("/img/buildArcheo/symbole/Icon_NodeIcon_DEFBlazer.png").toExternalForm());
        IMG_SYMBOLE.put("DEFDemonic", Main.class.getResource("/img/buildArcheo/symbole/Icon_NodeIcon_DEFDemonic.png").toExternalForm());
        IMG_SYMBOLE.put("DEFElectricity", Main.class.getResource("/img/buildArcheo/symbole/Icon_NodeIcon_DEFElectricity.png").toExternalForm());
        IMG_SYMBOLE.put("DEFGlacier", Main.class.getResource("/img/buildArcheo/symbole/Icon_NodeIcon_DEFGlacier.png").toExternalForm());
        // IMG_SYMBOLE.put("FireInterval", Main.class.getResource("/img/buildArcheo/symbole/Icon_NodeIcon_FireInterval.png").toExternalForm());
        // IMG_SYMBOLE.put("InvokeChanceElementalSE", Main.class.getResource("/img/buildArcheo/symbole/Icon_NodeIcon_InvokeChanceElementalSE.png").toExternalForm());
        IMG_SYMBOLE.put("MaxEnergyShield", Main.class.getResource("/img/buildArcheo/symbole/Icon_NodeIcon_MaxEnergyShield.png").toExternalForm());
        // IMG_SYMBOLE.put("MaxEnhancedRoundsCoefficient", Main.class.getResource("/img/buildArcheo/symbole/Icon_NodeIcon_MaxEnhancedRoundsCoefficient.png").toExternalForm());
        // IMG_SYMBOLE.put("MaxGeneralRoundsCoefficient", Main.class.getResource("/img/buildArcheo/symbole/Icon_NodeIcon_MaxGeneralRoundsCoefficient.png").toExternalForm());
        // IMG_SYMBOLE.put("MaxHighpowerRoundsCoefficient", Main.class.getResource("/img/buildArcheo/symbole/Icon_NodeIcon_MaxHighpowerRoundsCoefficient.png").toExternalForm());
        IMG_SYMBOLE.put("MaxHP", Main.class.getResource("/img/buildArcheo/symbole/Icon_NodeIcon_MaxHP.png").toExternalForm());
        // IMG_SYMBOLE.put("MaxImpactRoundsCoefficient", Main.class.getResource("/img/buildArcheo/symbole/Icon_NodeIcon_MaxImpactRoundsCoefficient.png").toExternalForm());
        IMG_SYMBOLE.put("MaxMP", Main.class.getResource("/img/buildArcheo/symbole/Icon_NodeIcon_MaxMP.png").toExternalForm());
        IMG_SYMBOLE.put("MaxRoundsCoefficient", Main.class.getResource("/img/buildArcheo/symbole/Icon_NodeIcon_MaxRoundsCoefficient.png").toExternalForm());
        IMG_SYMBOLE.put("MultiHitChance", Main.class.getResource("/img/buildArcheo/symbole/Icon_NodeIcon_MultiHitChance.png").toExternalForm());
        IMG_SYMBOLE.put("MultiHitChance_MultiHitDamageIncrease", Main.class.getResource("/img/buildArcheo/symbole/Icon_NodeIcon_MultiHitChance_MultiHitDamageIncrease.png").toExternalForm());
        IMG_SYMBOLE.put("MultiHitDamageIncrease", Main.class.getResource("/img/buildArcheo/symbole/Icon_NodeIcon_MultiHitDamageIncrease.png").toExternalForm());
        IMG_SYMBOLE.put("NaturalRecoveryEnergyShield", Main.class.getResource("/img/buildArcheo/symbole/Icon_NodeIcon_NaturalRecoveryEnergyShield.png").toExternalForm());
        // IMG_SYMBOLE.put("NaturalRecoveryHp", Main.class.getResource("/img/buildArcheo/symbole/Icon_NodeIcon_NaturalRecoveryHp.png").toExternalForm());
        IMG_SYMBOLE.put("NaturalRecoveryMp", Main.class.getResource("/img/buildArcheo/symbole/Icon_NodeIcon_NaturalRecoveryMp.png").toExternalForm());
        IMG_SYMBOLE.put("ReloadSpeed", Main.class.getResource("/img/buildArcheo/symbole/Icon_NodeIcon_ReloadSpeed.png").toExternalForm());
        // IMG_SYMBOLE.put("RoundsPerMagazine", Main.class.getResource("/img/buildArcheo/symbole/Icon_NodeIcon_RoundsPerMagazine.png").toExternalForm());
        IMG_SYMBOLE.put("RunSpeed", Main.class.getResource("/img/buildArcheo/symbole/Icon_NodeIcon_RunSpeed.png").toExternalForm());
        IMG_SYMBOLE.put("SkillATKCoefficient", Main.class.getResource("/img/buildArcheo/symbole/Icon_NodeIcon_SkillATKCoefficient.png").toExternalForm());
        IMG_SYMBOLE.put("SkillATKPower", Main.class.getResource("/img/buildArcheo/symbole/Icon_NodeIcon_SkillATKPower.png").toExternalForm());
        IMG_SYMBOLE.put("SkillCooltimeCoefficient", Main.class.getResource("/img/buildArcheo/symbole/Icon_NodeIcon_SkillCooltimeCoefficient.png").toExternalForm());
        IMG_SYMBOLE.put("SkillCostCoefficient", Main.class.getResource("/img/buildArcheo/symbole/Icon_NodeIcon_SkillCostCoefficient.png").toExternalForm());
        IMG_SYMBOLE.put("SkillCriticalATKIncrease", Main.class.getResource("/img/buildArcheo/symbole/Icon_NodeIcon_SkillCriticalATKIncrease.png").toExternalForm());
        IMG_SYMBOLE.put("SkillCriticalChance", Main.class.getResource("/img/buildArcheo/symbole/Icon_NodeIcon_SkillCriticalChance.png").toExternalForm());
        IMG_SYMBOLE.put("SkillDurationIncrease", Main.class.getResource("/img/buildArcheo/symbole/Icon_NodeIcon_SkillDurationIncrease.png").toExternalForm());
        IMG_SYMBOLE.put("SkillScaleCoefficient", Main.class.getResource("/img/buildArcheo/symbole/Icon_NodeIcon_SkillScaleCoefficient.png").toExternalForm());
        IMG_SYMBOLE.put("TakeFinalDamageCoefficient", Main.class.getResource("/img/buildArcheo/symbole/Icon_NodeIcon_TakeFinalDamageCoefficient.png").toExternalForm());
        IMG_SYMBOLE.put("TakeHpRecoveryCoefficient", Main.class.getResource("/img/buildArcheo/symbole/Icon_NodeIcon_TakeHpRecoveryCoefficient.png").toExternalForm());
        IMG_SYMBOLE.put("TakeMpRecoveryCoefficient", Main.class.getResource("/img/buildArcheo/symbole/Icon_NodeIcon_TakeMpRecoveryCoefficient.png").toExternalForm());
        IMG_SYMBOLE.put("TypeBossATKBonus", Main.class.getResource("/img/buildArcheo/symbole/Icon_NodeIcon_TypeBossATKBonus.png").toExternalForm());
        IMG_SYMBOLE.put("TypeBossSkillATKBonus", Main.class.getResource("/img/buildArcheo/symbole/Icon_NodeIcon_TypeBossSkillATKBonus.png").toExternalForm());
        IMG_SYMBOLE.put("WeaknessDamageIncrease", Main.class.getResource("/img/buildArcheo/symbole/Icon_NodeIcon_WeaknessDamageIncrease.png").toExternalForm());
    }

    /* ------------------------------------------------------------------------------------------- */

    private ImageManager imageManager = ImageManager.getInstance();
    
    private final Map<String, ImageView> mapGridCouleur = new HashMap<>();

    private GridPane grid;
    private int size;

    public GridPane getGrid(int size) {
        this.size = size;
        grid = new GridPane();
        grid.setMinSize(21*size, 21*size);
        grid.setPrefSize(21*size, 21*size);
        grid.setMaxSize(21*size, 21*size);
        grid.setAlignment(Pos.CENTER);

        setImage("commun", "ATK", 2, 1);
        setImage("commun", "ATK", 2, 2);
        setImage("rare", "ATK", 2, 8);
        setImage("commun", "ATK", 2, 9);
        setImage("commun", "ATK", 2, 10);
        setImage("commun", "ATK", 3, 0);
        setImage("commun", "ATK", 3, 8);
        setImage("commun", "ATK", 4, 0);
        setImage("legendaire", "ATK", 4, 2);
        setImage("commun", "ATK", 4, 3);
        setImage("commun", "ATK", 4, 4);
        setImage("commun", "ATK", 4, 7);
        setImage("commun", "ATK", 4, 8);
        setImage("commun", "ATK", 4, 12);
        setImage("commun", "ATK", 5, 0);
        setImage("commun", "ATK", 5, 2);
        setImage("rare", "ATK", 6, 0);
        setImage("commun", "ATK", 6, 1);
        setImage("commun", "ATK", 6, 2);
        setImage("commun", "ATK", 6, 6);
        setImage("commun", "ATK", 8, 1);
        setImage("commun", "ATK", 8, 4);
        setImage("commun", "ATK", 8, 7);
        setImage("commun", "ATK", 8, 8);
        setImage("commun", "ATK", 8, 9);
        setImage("commun", "ATK", 8, 11);
        setImage("commun", "ATK", 9, 1);
        setImage("commun", "ATK", 9, 4);
        setImage("commun", "ATK", 9, 8);
        setImage("commun", "ATK", 9, 10);
        setImage("rare", "ATK", 10, 4);
        setImage("commun", "Accuracy", 5, 8);
        setImage("rare", "Accuracy", 6, 8);
        setImage("commun", "Accuracy", 6, 9);
        setImage("commun", "AdvantageCoefficient", 4, 5);
        setImage("rare", "AdvantageCoefficient", 4, 6);
        setImage("commun", "AdvantageCoefficient", 5, 6);
        setImage("rare", "AllElementalDEF", 10, 16);
        setImage("commun", "CriticalATKIncrease", 7, 2);
        setImage("rare", "CriticalATKIncrease", 8, 2);
        setImage("commun", "CriticalATKIncrease", 8, 3);
        setImage("rare", "CriticalChance", 6, 4);
        setImage("commun", "CriticalChance", 6, 5);
        setImage("commun", "CriticalChance", 7, 4);
        setImage("commun", "DEF", 3, 20);
        setImage("commun", "DEF", 4, 20);
        setImage("commun", "DEF", 13, 16);
        setImage("commun", "DEF", 14, 15);
        setImage("rare", "DEF", 14, 16);
        setImage("commun", "DEF", 16, 20);
        setImage("commun", "DEF", 17, 20);
        setImage("rare", "DEFBlazer", 2, 16);
        setImage("commun", "DEFBlazer", 2, 17);
        setImage("commun", "DEFBlazer", 3, 16);
        setImage("commun", "DEFBlazer", 15, 14);
        setImage("rare", "DEFBlazer", 16, 14);
        setImage("commun", "DEFBlazer", 16, 15);
        setImage("commun", "DEFDemonic", 5, 20);
        setImage("commun", "DEFDemonic", 6, 19);
        setImage("rare", "DEFDemonic", 6, 20);
        setImage("commun", "DEFDemonic", 12, 17);
        setImage("rare", "DEFDemonic", 12, 18);
        setImage("commun", "DEFDemonic", 13, 18);
        setImage("rare", "DEFElectricity", 4, 14);
        setImage("commun", "DEFElectricity", 4, 15);
        setImage("commun", "DEFElectricity", 5, 14);
        setImage("commun", "DEFElectricity", 17, 16);
        setImage("rare", "DEFElectricity", 18, 16);
        setImage("commun", "DEFElectricity", 18, 17);
        setImage("commun", "DEFGlacier", 7, 18);
        setImage("commun", "DEFGlacier", 8, 17);
        setImage("rare", "DEFGlacier", 8, 18);
        setImage("commun", "DEFGlacier", 14, 19);
        setImage("rare", "DEFGlacier", 14, 20);
        setImage("commun", "DEFGlacier", 15, 20);
        setImage("commun", "MaxEnergyShield", 2, 18);
        setImage("commun", "MaxEnergyShield", 2, 19);
        setImage("commun", "MaxEnergyShield", 4, 13);
        setImage("commun", "MaxEnergyShield", 4, 16);
        setImage("commun", "MaxEnergyShield", 4, 17);
        setImage("commun", "MaxEnergyShield", 5, 18);
        setImage("commun", "MaxEnergyShield", 6, 14);
        setImage("commun", "MaxEnergyShield", 6, 18);
        setImage("commun", "MaxEnergyShield", 8, 12);
        setImage("commun", "MaxEnergyShield", 8, 13);
        setImage("commun", "MaxEnergyShield", 8, 16);
        setImage("commun", "MaxEnergyShield", 8, 19);
        setImage("commun", "MaxEnergyShield", 9, 12);
        setImage("commun", "MaxEnergyShield", 9, 16);
        setImage("commun", "MaxEnergyShield", 9, 19);
        setImage("rare", "MaxEnergyShield", 10, 19);
        setImage("commun", "MaxHP", 10, 11);
        setImage("rare", "MaxHP", 10, 12);
        setImage("commun", "MaxHP", 11, 12);
        setImage("commun", "MaxHP", 11, 16);
        setImage("commun", "MaxHP", 11, 19);
        setImage("commun", "MaxHP", 12, 12);
        setImage("commun", "MaxHP", 12, 13);
        setImage("commun", "MaxHP", 12, 16);
        setImage("commun", "MaxHP", 12, 19);
        setImage("commun", "MaxHP", 14, 14);
        setImage("commun", "MaxHP", 14, 18);
        setImage("commun", "MaxHP", 15, 18);
        setImage("commun", "MaxHP", 16, 13);
        setImage("commun", "MaxHP", 16, 16);
        setImage("commun", "MaxHP", 16, 17);
        setImage("legendaire", "MaxHP", 16, 18);
        setImage("commun", "MaxHP", 18, 18);
        setImage("commun", "MaxHP", 18, 19);
        setImage("rare", "MaxMP", 10, 1);
        setImage("commun", "MaxMP", 10, 9);
        setImage("commun", "MaxMP", 11, 1);
        setImage("commun", "MaxMP", 11, 4);
        setImage("commun", "MaxMP", 11, 8);
        setImage("commun", "MaxMP", 12, 1);
        setImage("commun", "MaxMP", 12, 4);
        setImage("commun", "MaxMP", 12, 7);
        setImage("commun", "MaxMP", 12, 9);
        setImage("rare", "MaxMP", 14, 0);
        setImage("commun", "MaxMP", 14, 1);
        setImage("commun", "MaxMP", 14, 2);
        setImage("commun", "MaxMP", 14, 6);
        setImage("commun", "MaxMP", 15, 0);
        setImage("commun", "MaxMP", 15, 2);
        setImage("commun", "MaxMP", 15, 6);
        setImage("commun", "MaxMP", 16, 0);
        setImage("commun", "MaxMP", 16, 3);
        setImage("commun", "MaxMP", 16, 4);
        setImage("commun", "MaxMP", 16, 5);
        setImage("rare", "MaxMP", 16, 6);
        setImage("commun", "MaxMP", 17, 0);
        setImage("commun", "MaxRoundsCoefficient", 2, 3);
        setImage("rare", "MaxRoundsCoefficient", 2, 4);
        setImage("commun", "MaxRoundsCoefficient", 3, 4);
        setImage("commun", "MultiHitChance", 2, 11);
        setImage("rare", "MultiHitChance", 2, 12);
        setImage("commun", "MultiHitChance", 3, 12);
        setImage("legendaire", "MultiHitDamageIncrease", 1, 10);
        setImage("commun", "MultiHitDamageIncrease", 4, 9);
        setImage("rare", "MultiHitDamageIncrease", 4, 10);
        setImage("commun", "MultiHitDamageIncrease", 4, 11);
        setImage("commun", "NaturalRecoveryEnergyShield", 7, 14);
        setImage("rare", "NaturalRecoveryEnergyShield", 8, 14);
        setImage("commun", "NaturalRecoveryEnergyShield", 8, 15);
        setImage("rare", "NaturalRecoveryMp", 14, 8);
        setImage("commun", "NaturalRecoveryMp", 14, 9);
        setImage("commun", "NaturalRecoveryMp", 15, 8);
        setImage("commun", "ReloadSpeed", 5, 12);
        setImage("commun", "ReloadSpeed", 6, 11);
        setImage("rare", "ReloadSpeed", 6, 12);
        setImage("commun", "RunSpeed", 6, 15);
        setImage("rare", "RunSpeed", 6, 16);
        setImage("commun", "RunSpeed", 7, 16);
        setImage("legendaire", "SkillATKCoefficient", 19, 10);
        setImage("commun", "SkillATKPower", 11, 10);
        setImage("commun", "SkillATKPower", 12, 8);
        setImage("rare", "SkillATKPower", 12, 10);
        setImage("commun", "SkillATKPower", 12, 11);
        setImage("commun", "SkillATKPower", 13, 10);
        setImage("commun", "SkillATKPower", 14, 10);
        setImage("commun", "SkillATKPower", 16, 7);
        setImage("commun", "SkillATKPower", 16, 8);
        setImage("commun", "SkillATKPower", 16, 9);
        setImage("rare", "SkillATKPower", 16, 10);
        setImage("commun", "SkillATKPower", 16, 11);
        setImage("commun", "SkillATKPower", 16, 12);
        setImage("commun", "SkillATKPower", 17, 4);
        setImage("commun", "SkillATKPower", 18, 1);
        setImage("commun", "SkillATKPower", 18, 2);
        setImage("commun", "SkillATKPower", 18, 3);
        setImage("rare", "SkillATKPower", 18, 4);
        setImage("commun", "SkillATKPower", 18, 10);
        setImage("legendaire", "SkillCooltimeCoefficient", 16, 2);
        setImage("commun", "SkillCostCoefficient", 14, 11);
        setImage("rare", "SkillCostCoefficient", 14, 12);
        setImage("commun", "SkillCostCoefficient", 15, 12);
        setImage("rare", "SkillCriticalATKIncrease", 12, 2);
        setImage("commun", "SkillCriticalATKIncrease", 12, 3);
        setImage("commun", "SkillCriticalATKIncrease", 13, 2);
        setImage("commun", "SkillCriticalChance", 13, 4);
        setImage("rare", "SkillCriticalChance", 14, 4);
        setImage("commun", "SkillCriticalChance", 14, 5);
        setImage("commun", "SkillDurationIncrease", 12, 5);
        setImage("rare", "SkillDurationIncrease", 12, 6);
        setImage("commun", "SkillDurationIncrease", 13, 6);
        setImage("commun", "SkillScaleCoefficient", 17, 12);
        setImage("commun", "SkillScaleCoefficient", 18, 11);
        setImage("rare", "SkillScaleCoefficient", 18, 12);
        setImage("legendaire", "TakeFinalDamageCoefficient", 4, 18);
        setImage("rare", "TakeHpRecoveryCoefficient", 12, 14);
        setImage("commun", "TakeHpRecoveryCoefficient", 12, 15);
        setImage("commun", "TakeHpRecoveryCoefficient", 13, 14);
        setImage("rare", "TakeMpRecoveryCoefficient", 10, 8);
        setImage("commun", "TypeBossATKBonus", 7, 6);
        setImage("commun", "TypeBossATKBonus", 8, 5);
        setImage("rare", "TypeBossATKBonus", 8, 6);
        setImage("commun", "TypeBossSkillATKBonus", 17, 8);
        setImage("rare", "TypeBossSkillATKBonus", 18, 8);
        setImage("commun", "TypeBossSkillATKBonus", 18, 9);
        setImage("commun", "WeaknessDamageIncrease", 6, 10);
        setImage("commun", "WeaknessDamageIncrease", 7, 10);
        setImage("rare", "WeaknessDamageIncrease", 8, 10);

        addImage(IMG_COULEUR, "base", 10, 10, mapGridCouleur);
        addImage(IMG_COULEUR, "ajout", 0, 10, mapGridCouleur);
        addImage(IMG_COULEUR, "ajout", 10, 0, mapGridCouleur);
        addImage(IMG_COULEUR, "ajout", 10, 20, mapGridCouleur);
        addImage(IMG_COULEUR, "ajout", 20, 10, mapGridCouleur);

        setImage("mod", null, 2, 0);
        setImage("mod", null, 18, 0);
        setImage("mod", null, 2, 20);
        setImage("mod", null, 18, 20);

        return grid;
    }

    private void setImage(String couleur, String symbole, int col, int row) {
        String key = getKeyFromInt(col, row);
        BuildArcheonique.addGlobalNode(key);

        addImage(IMG_COULEUR, couleur, col, row, mapGridCouleur);
        if(symbole != null) {
            addImage(IMG_SYMBOLE, symbole, col, row, null);
        }

        changeState(key, true);
    }

    private void addImage(Map<String, String> imageMap, String type, int col, int row, Map<String, ImageView> gridMap) {
        ImageView img = new ImageView(imageManager.getImage(imageMap.get(type), size, size));
        img.setFitWidth(size);
        img.setFitHeight(size);

        grid.add(img, col, row);
        if(gridMap != null) {
            gridMap.put(getKeyFromInt(col, row), img);
        }

        GridPane.setHalignment(img, HPos.CENTER);
        GridPane.setValignment(img, VPos.CENTER);
    }

    public void changeState(String key, boolean inactive) {
        if (!mapGridCouleur.containsKey(key)) return;

        ColorAdjust saturation = new ColorAdjust();
        saturation.setSaturation(inactive ? -1 : 0); // gris si inactif
        mapGridCouleur.get(key).setEffect(saturation);
    }

    public void setDescendant(Descendant descendant) {
        setPolariteDescendant(descendant.getTypeModArcheonique1(), 2, 0);
        setPolariteDescendant(descendant.getTypeModArcheonique2(), 18, 0);
        setPolariteDescendant(descendant.getTypeModArcheonique3(), 2, 20);
        setPolariteDescendant(descendant.getTypeModArcheonique4(), 18, 20);
    }

    private void setPolariteDescendant(String polarite, int col, int row) {
        int resize = (int) Math.round(size * 0.75);
        ImageView img = new ImageView(imageManager.getImage(Mod.POLARITE.get("blanc").get(polarite), resize, resize));
        img.setFitWidth(resize);
        img.setFitHeight(resize);

        grid.add(img, col, row);

        GridPane.setHalignment(img, HPos.CENTER);
        GridPane.setValignment(img, VPos.CENTER);
    }

    /* --------------------------------- */

    public String getKeyFromInt(int col, int row) {
        return "%d_%d".formatted(col, row);
    }
    public int[] getIntFromKey(String key) {
        String[] k = key.split("_");
        return new int[] {Integer.parseInt(k[0]), Integer.parseInt(k[1])};
    }

}