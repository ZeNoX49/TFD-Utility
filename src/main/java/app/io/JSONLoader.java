package app.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import app.collection.CollectionProgression;
import app.model.progression.Acolyte;
import app.model.progression.Arme;
import app.model.progression.Descendant;
import app.model.progression.Vehicule;
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

    private static final String PATH_PROGRESSION_USER = System.getProperty("user.dir") + "\\src\\main\\resources\\json\\user\\progression.json";

    private static ObjectMapper objectMapper;

    public static void load() throws IOException {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        loadProgression();
    }

    private static void loadProgression() throws IOException {
        try {
            File devFile = new File(PATH_PROGRESSION_DEV);
            if (!devFile.exists()) {
                System.err.println("Repertoire courant : " + System.getProperty("user.dir"));
                System.err.println("Erreur : Le fichier " + PATH_PROGRESSION_DEV + " est introuvable.");
                return;
            }
            DevProgressionPOJO devProgressionPOJO = objectMapper.readValue(devFile, DevProgressionPOJO.class);

            UserProgressionPOJO userProgressionPOJO = null;
            File userFile = new File(PATH_PROGRESSION_USER);
            if (userFile.exists()) {
                userProgressionPOJO = objectMapper.readValue(devFile, UserProgressionPOJO.class);
            } else {
                if(!writeUserProgression(userProgressionPOJO, devProgressionPOJO)) return;
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

    /**
     * 
     * @param userProgressionPOJO
     * @param devProgressionPOJO
     * @return
     */
    private static boolean writeUserProgression(UserProgressionPOJO userProgressionPOJO, DevProgressionPOJO devProgressionPOJO) {
        try {
            FileWriter writer = new FileWriter(PATH_PROGRESSION_USER);
            writer.write("{}");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        userProgressionPOJO = new UserProgressionPOJO();

        userProgressionPOJO.descendants = new UserDescendantPOJO[devProgressionPOJO.descendants.length];
        for(int i = 0; i < devProgressionPOJO.descendants.length; i++) {
            userProgressionPOJO.descendants[i] = new UserDescendantPOJO(devProgressionPOJO.descendants[i].id_descendant);
        }

        userProgressionPOJO.armes = new UserArmePOJO[devProgressionPOJO.armes.length];
        for(int i = 0; i < devProgressionPOJO.armes.length; i++) {
            userProgressionPOJO.armes[i] = new UserArmePOJO(devProgressionPOJO.armes[i].id_arme);
        }

        userProgressionPOJO.acolytes = new UserAcolytePOJO[devProgressionPOJO.acolytes.length];
        for(int i = 0; i < devProgressionPOJO.acolytes.length; i++) {
            userProgressionPOJO.acolytes[i] = new UserAcolytePOJO(devProgressionPOJO.acolytes[i].id_acolyte);
        }

        userProgressionPOJO.vehicules = new UserVehiculePOJO[devProgressionPOJO.vehicules.length];
        for(int i = 0; i < devProgressionPOJO.vehicules.length; i++) {
            userProgressionPOJO.vehicules[i] = new UserVehiculePOJO(devProgressionPOJO.vehicules[i].id_vehicule);
        }

        return true;
    }

}