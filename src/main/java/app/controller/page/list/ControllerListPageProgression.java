package app.controller.page.list;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import app.collection.CollectionProgression;
import app.model.progression.Acolyte;
import app.model.progression.Arme;
import app.model.progression.Descendant;
import app.model.progression.Vehicule;
import app.util.manager.ProgressionManager;

public class ControllerListPageProgression extends ControllerListPage {
    private final ProgressionManager progressionManager = ProgressionManager.getInstance();

    private final Map<String, IOAction> actionsProgression = new HashMap<>();

    @Override
    public void initializeType() throws IOException {
        setupButton(actionsProgression, "Descendants", this::useDescendant);
        setupButton(actionsProgression, "Armes", this::useArme);
        setupButton(actionsProgression, "Acolytes", this::useAcolyte);
        setupButton(actionsProgression, "Véhicules", this::useVehicule);

        switch (type_actif) {
            case "Descendants" -> useDescendant();
            case "Armes" -> useArme();
            case "Acolytes" -> useAcolyte();
            case "Véhicules" -> useVehicule();
            default -> useDescendant();
        }
    }

    /**
     * Ajouter une carte dans progression
     * @throws IOException
     */
    @Override
    public void addNewType() throws IOException {
        switch (type_actif) {
            case "Descendants" -> {
                Descendant d = new Descendant();
                CollectionProgression.addDescendant(d);
                progressionManager.addNewProgressionCard(progressionManager.getHBoxDescendant(), d, "d");
                useDescendant();
            }
            case "Armes" -> {
                Arme a = new Arme();
                CollectionProgression.addArme(a);
                progressionManager.addNewProgressionCard(progressionManager.getHBoxWeapon(), a, "w");
                useArme();
            }
            case "Acolytes" -> {
                Acolyte ac = new Acolyte();
                CollectionProgression.addAcolyte(ac);
                progressionManager.addNewProgressionCard(progressionManager.getHBoxAcolyte(), ac, "a");
                useAcolyte();
            }
            case "Véhicules" -> {
                Vehicule v = new Vehicule();
                CollectionProgression.addVehicule(v);
                progressionManager.addNewProgressionCard(progressionManager.getHBoxVehicule(), v, "v");
                useVehicule();
            }
        }
    }

    @Override
    public void refreshType() throws IOException {
        switch (type_actif) {
            case "Descendants" -> CollectionProgression.sortDescendant();
            case "Armes" -> CollectionProgression.sortArme();
            case "Acolyte" -> CollectionProgression.sortAcolyte();
            case "Véhicule" -> CollectionProgression.sortVehicule();
        }
        actionsProgression.get(type_actif).run();
    }

    private void useDescendant() throws IOException { displayCard(progressionManager.getHBoxDescendant()); super.setButton(); }
    private void useArme() throws IOException{ displayCard(progressionManager.getHBoxWeapon()); super.setButton(); }
    private void useAcolyte() throws IOException{ displayCard(progressionManager.getHBoxAcolyte()); super.setButton(); }
    private void useVehicule() throws IOException{ displayCard(progressionManager.getHBoxVehicule()); super.setButton(); }
}