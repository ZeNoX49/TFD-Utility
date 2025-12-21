package app.io;

import java.io.File;
import java.io.FileWriter;
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
import app.pojo.progression.dev.DevAcolytePOJO;
import app.pojo.progression.dev.DevArmePOJO;
import app.pojo.progression.dev.DevDescendantPOJO;
import app.pojo.progression.dev.DevProgressionPOJO;
import app.pojo.progression.dev.DevVehiculePOJO;
import app.pojo.progression.user.UserAcolytePOJO;
import app.pojo.progression.user.UserArmePOJO;
import app.pojo.progression.user.UserDescendantPOJO;
import app.pojo.progression.user.UserProgressionPOJO;
import app.pojo.progression.user.UserVehiculePOJO;

public class JSONLoader {
    private static final String PATH_PROGRESSION_DEV = System.getProperty("user.dir") + "\\src\\main\\resources\\json\\dev\\progression.json";
    private static final String PATH_COLLECTIBLE_DEV = System.getProperty("user.dir") + "\\src\\main\\resources\\json\\dev\\collectible.json";

    private static final String PATH_PROGRESSION_USER = System.getProperty("user.dir") + "\\src\\main\\resources\\json\\user\\progression.json";
    private static final String PATH_PREREGLAGE_USER = System.getProperty("user.dir") + "\\src\\main\\resources\\json\\user\\prereglage.json";

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
            File devFile = new File(PATH_PROGRESSION_DEV);
            if (!devFile.exists()) {
                System.err.println("Repertoire courant : " + System.getProperty("user.dir"));
                System.err.println("Erreur : Le fichier " + PATH_PROGRESSION_DEV + " est introuvable.");
                return;
            }
            var devProgressionPOJO = objectMapper.readValue(devFile, DevProgressionPOJO.class);

            UserProgressionPOJO userProgressionPOJO;
            File userFile = new File(PATH_PROGRESSION_USER);
            if (userFile.exists()) {
                userProgressionPOJO = objectMapper.readValue(devFile, UserProgressionPOJO.class);
            } else {
                try {
                    FileWriter writer = new FileWriter(PATH_PROGRESSION_USER);
                    writer.write("{}");
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }

                userProgressionPOJO = new UserProgressionPOJO();

                for(int i = 0; i < devProgressionPOJO.descendants.length; i++) {
                    DevDescendantPOJO descendant = devProgressionPOJO.descendants[i];
                    userProgressionPOJO.descendants[i].id_descendant = descendant.id_descendant;
                    userProgressionPOJO.descendants[i].cellule_owned = 0;
                    userProgressionPOJO.descendants[i].cellule_schema = 0;
                    userProgressionPOJO.descendants[i].stabilisateur_owned = 0;
                    userProgressionPOJO.descendants[i].stabilisateur_schema = 0;
                    userProgressionPOJO.descendants[i].catalyseur_owned = 0;
                    userProgressionPOJO.descendants[i].catalyseur_schema = 0;
                    userProgressionPOJO.descendants[i].code_owned = 0;
                    userProgressionPOJO.descendants[i].isCraft = false;
                }

                for(int i = 0; i < devProgressionPOJO.armes.length; i++) {
                    DevArmePOJO descendant = devProgressionPOJO.armes[i];
                    userProgressionPOJO.armes[i].id_arme = descendant.id_arme;
                    userProgressionPOJO.armes[i].polymere_owned = 0;
                    userProgressionPOJO.armes[i].polymere_schema = 0;
                    userProgressionPOJO.armes[i].fibre_owned = 0;
                    userProgressionPOJO.armes[i].fibre_schema = 0;
                    userProgressionPOJO.armes[i].nanotubes_owned = 0;
                    userProgressionPOJO.armes[i].nanotubes_schema = 0;
                    userProgressionPOJO.armes[i].schema = 0;
                    userProgressionPOJO.armes[i].nbCraft = 0;
                }

                for(int i = 0; i < devProgressionPOJO.acolytes.length; i++) {
                    DevAcolytePOJO descendant = devProgressionPOJO.acolytes[i];
                    userProgressionPOJO.acolytes[i].id_acolyte = descendant.id_acolyte;
                    userProgressionPOJO.acolytes[i].cellule_owned = 0;
                    userProgressionPOJO.acolytes[i].cellule_schema = 0;
                    userProgressionPOJO.acolytes[i].stabilisateur_owned = 0;
                    userProgressionPOJO.acolytes[i].stabilisateur_schema = 0;
                    userProgressionPOJO.acolytes[i].catalyseur_owned = 0;
                    userProgressionPOJO.acolytes[i].catalyseur_schema = 0;
                    userProgressionPOJO.acolytes[i].code_owned = 0;
                    userProgressionPOJO.acolytes[i].isCraft = false;
                }

                for(int i = 0; i < devProgressionPOJO.vehicules.length; i++) {
                    DevVehiculePOJO descendant = devProgressionPOJO.vehicules[i];
                    userProgressionPOJO.vehicules[i].id_vehicule = descendant.id_vehicule;
                    userProgressionPOJO.vehicules[i].moteur_owned = 0;
                    userProgressionPOJO.vehicules[i].moteur_schema = 0;
                    userProgressionPOJO.vehicules[i].commande_owned = 0;
                    userProgressionPOJO.vehicules[i].commande_schema = 0;
                    userProgressionPOJO.vehicules[i].schema_owned = 0;
                    userProgressionPOJO.vehicules[i].schema_schema = 0;
                    userProgressionPOJO.vehicules[i].systeme_owned = 0;
                    userProgressionPOJO.vehicules[i].isCraft = false;
                }
            }

            // Descendants
            for(int i = 0; i < devProgressionPOJO.descendants.length; i++) {
                DevDescendantPOJO devDescendant = devProgressionPOJO.descendants[i];
                UserDescendantPOJO userDescendant = userProgressionPOJO.descendants[i];
                Descendant descendant = new Descendant(devDescendant, userDescendant);
                CollectionProgression.addDescendant(descendant);
            }
                
            // Weapons
            for(int i = 0; i < devProgressionPOJO.armes.length; i++) {
                DevArmePOJO devArme = devProgressionPOJO.armes[i];
                UserArmePOJO userArme = userProgressionPOJO.armes[i];
                Arme arme = new Arme(devArme, userArme);
                CollectionProgression.addArme(arme);
            }

            // Acolytes
            for(int i = 0; i < devProgressionPOJO.acolytes.length; i++) {
                DevAcolytePOJO devAcolyte = devProgressionPOJO.acolytes[i];
                UserAcolytePOJO userAcolyte = userProgressionPOJO.acolytes[i];
                Acolyte acolyte = new Acolyte(devAcolyte, userAcolyte);
                CollectionProgression.addAcolyte(acolyte);
            }

            // Vehicules
            for(int i = 0; i < devProgressionPOJO.vehicules.length; i++) {
                DevVehiculePOJO devVehicule = devProgressionPOJO.vehicules[i];
                UserVehiculePOJO userVehicule = userProgressionPOJO.vehicules[i];
                Vehicule vehicule = new Vehicule(devVehicule, userVehicule);
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
            File file = new File(PATH_COLLECTIBLE_DEV);

            if (!file.exists()) {
                System.err.println("Repertoire courant : " + System.getProperty("user.dir"));
                System.err.println("Erreur : Le fichier " + PATH_COLLECTIBLE_DEV + " est introuvable.");
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
            File file = new File(PATH_PREREGLAGE_USER);

            if (!file.exists()) {
                System.err.println("Repertoire courant : " + System.getProperty("user.dir"));
                System.err.println("Erreur : Le fichier " + PATH_PREREGLAGE_USER + " est introuvable.");
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