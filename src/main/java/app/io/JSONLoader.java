package app.io;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import app.Collection.CollectionCollectible;
import app.Collection.CollectionPrereglage;
import app.Collection.CollectionProgression;
import app.model.collectible.ComposantExterne;
import app.model.collectible.Mod;
import app.model.collectible.ModArcheo;
import app.model.collectible.ModDeclenchement;
import app.model.collectible.Reacteur;
import app.model.prereglage.Prereglage;
import app.model.progression.Acolyte;
import app.model.progression.Arme;
import app.model.progression.Descendant;
import app.model.progression.Vehicule;
import app.pojo.collectible.CollectiblePOJO;
import app.pojo.collectible.ComposantExternePOJO;
import app.pojo.collectible.ModArcheoPOJO;
import app.pojo.collectible.ModDeclenchementPOJO;
import app.pojo.collectible.ModPOJO;
import app.pojo.collectible.ReacteurPOJO;
import app.pojo.prereglage.PrereglagePOJO;
import app.pojo.prereglage.PrereglagesPOJO;
import app.pojo.progression.AcolytePOJO;
import app.pojo.progression.ArmePOJO;
import app.pojo.progression.DescendantPOJO;
import app.pojo.progression.ProgressionPOJO;
import app.pojo.progression.VehiculePOJO;

public class JSONLoader {
    private static final String PATH_PROGRESSION = System.getProperty("user.dir") + "\\src\\main\\resources\\json\\progression.json";
    private static final String PATH_COLLECTIBLE = System.getProperty("user.dir") + "\\src\\main\\resources\\json\\collectible.json";
    private static final String PATH_PREREGLAGE = System.getProperty("user.dir") + "\\src\\main\\resources\\json\\prereglage.json";

    private static ObjectMapper objectMapper;

    public static void load() throws IOException {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        loadProgression();
        loadCollectible();
        loadPrereglage();
    }

    private static void loadProgression() throws IOException {
        try {
            File file = new File(PATH_PROGRESSION);

            if (!file.exists()) {
                System.err.println("Repertoire courant : " + System.getProperty("user.dir"));
                System.err.println("Erreur : Le fichier " + PATH_PROGRESSION + " est introuvable.");
                return;
            }

            ProgressionPOJO progressionPOJO = objectMapper.readValue(file, ProgressionPOJO.class);

            // Descendants
            for (DescendantPOJO descendantPOJO : progressionPOJO.descendants) {
                Descendant descendant = new Descendant(descendantPOJO);
                CollectionProgression.addDescendant(descendant);
            }
                
            // Weapons
            for (ArmePOJO armePOJO : progressionPOJO.armes) {
                Arme arme = new Arme(armePOJO);
                CollectionProgression.addArme(arme);
            }

            // Acolytes
            for (AcolytePOJO acolytePOJO : progressionPOJO.acolytes) {
                Acolyte acolyte = new Acolyte(acolytePOJO);
                CollectionProgression.addAcolyte(acolyte);
            }

            // Vehicules
            for (VehiculePOJO vehiculePOJO : progressionPOJO.vehicules) {
                Vehicule vehicule = new Vehicule(vehiculePOJO);
                CollectionProgression.addVehicule(vehicule);
            }

        } catch (JsonProcessingException e) {
            System.err.println("Erreur de traitement JSON : " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Erreur de lecture du fichier de donnees : " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void loadCollectible() throws IOException {
        try {
            File file = new File(PATH_COLLECTIBLE);

            if (!file.exists()) {
                System.err.println("Repertoire courant : " + System.getProperty("user.dir"));
                System.err.println("Erreur : Le fichier " + PATH_COLLECTIBLE + " est introuvable.");
                return;
            }

            CollectiblePOJO collectiblePOJO = objectMapper.readValue(file, CollectiblePOJO.class);

            // Réacteurs
            for (ReacteurPOJO reacteurPOJO : collectiblePOJO.reacteurs) {
                Reacteur reacteur = new Reacteur(reacteurPOJO);
                CollectionCollectible.addReacteur(reacteur);
            }

            // Composants Externes
            for (ComposantExternePOJO composantExternePOJO : collectiblePOJO.composantExternes) {
                ComposantExterne composantExterne = new ComposantExterne(composantExternePOJO);
                CollectionCollectible.addComposantExterne(composantExterne);
            }

            // Mods
            for (ModPOJO modPOJO : collectiblePOJO.mods) {
                Mod mod = new Mod(modPOJO);
                CollectionCollectible.addMod(mod);
            }

            // Mods Archeo
            for (ModArcheoPOJO modArcheoPOJO : collectiblePOJO.modsArcheo) {
                ModArcheo modArcheo = new ModArcheo(modArcheoPOJO);
                CollectionCollectible.addModArcheo(modArcheo);
            }

            // Mods Declenchement
            for (ModDeclenchementPOJO modDeclenchementPOJO : collectiblePOJO.modsDeclenchement) {
                ModDeclenchement modDeclenchement = new ModDeclenchement(modDeclenchementPOJO);
                CollectionCollectible.addModDeclenchement(modDeclenchement);
            }

        } catch (JsonProcessingException e) {
            System.err.println("Erreur de traitement JSON : " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Erreur de lecture du fichier de donnees : " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void loadPrereglage() throws IOException {
        try {
            File file = new File(PATH_PREREGLAGE);

            if (!file.exists()) {
                System.err.println("Repertoire courant : " + System.getProperty("user.dir"));
                System.err.println("Erreur : Le fichier " + PATH_PREREGLAGE + " est introuvable.");
                return;
            }

            PrereglagesPOJO prereglagesPOJO = objectMapper.readValue(file, PrereglagesPOJO.class);

            // Prereglage
            for (PrereglagePOJO prereglagePOJO : prereglagesPOJO.prereglages) {
                Prereglage prereglage = new Prereglage(prereglagePOJO);
                CollectionPrereglage.addPrereglage(prereglage);
            }

        } catch (JsonProcessingException e) {
            System.err.println("Erreur de traitement JSON : " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Erreur de lecture du fichier de donnees : " + e.getMessage());
            e.printStackTrace();
        }
    }

}