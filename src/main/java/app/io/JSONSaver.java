package app.io;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

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

public class JSONSaver {
    private static final String PATH_PROGRESSION_DEV = System.getProperty("user.dir") + "\\src\\main\\resources\\json\\dev\\progression.json";
    
    private static final String PATH_PROGRESSION_USER = System.getProperty("user.dir") + "\\src\\main\\resources\\json\\user\\progression.json";
    
    private static ObjectMapper objectMapper;

    public static void save() {
        objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        saveProgression();
    }

    private static void saveProgression() {
        DevProgressionPOJO devProgressionPOJO = new DevProgressionPOJO();
        UserProgressionPOJO userProgressionPOJO = new UserProgressionPOJO();

        List<DevDescendantPOJO> devDescendantList = new ArrayList<>();
        List<UserDescendantPOJO> userDescendantList = new ArrayList<>();
        for (Descendant descendant : CollectionProgression.getDescendant()) {
            devDescendantList.add(new DevDescendantPOJO(descendant));
            userDescendantList.add(new UserDescendantPOJO(descendant));
        }
        devProgressionPOJO.descendants = devDescendantList.toArray(DevDescendantPOJO[]::new);
        userProgressionPOJO.descendants = userDescendantList.toArray(UserDescendantPOJO[]::new);

        List<DevArmePOJO> devArmeList = new ArrayList<>(); 
        List<UserArmePOJO> userArmeList = new ArrayList<>(); 
        for (Arme arme : CollectionProgression.getArme()) {
            devArmeList.add(new DevArmePOJO(arme));
            userArmeList.add(new UserArmePOJO(arme));
        }
        devProgressionPOJO.armes = devArmeList.toArray(DevArmePOJO[]::new);
        userProgressionPOJO.armes = userArmeList.toArray(UserArmePOJO[]::new);

        List<DevAcolytePOJO> devAcolyteList = new ArrayList<>(); 
        List<UserAcolytePOJO> userAcolyteList = new ArrayList<>(); 
        for (Acolyte acolyte : CollectionProgression.getAcolyte()) {
            devAcolyteList.add(new DevAcolytePOJO(acolyte));
            userAcolyteList.add(new UserAcolytePOJO(acolyte));
        }
        devProgressionPOJO.acolytes = devAcolyteList.toArray(DevAcolytePOJO[]::new);
        userProgressionPOJO.acolytes = userAcolyteList.toArray(UserAcolytePOJO[]::new);

        List<DevVehiculePOJO> devVehiculeList = new ArrayList<>(); 
        List<UserVehiculePOJO> userVehiculeList = new ArrayList<>(); 
        for (Vehicule vehicule : CollectionProgression.getVehicule()) {
            devVehiculeList.add(new DevVehiculePOJO(vehicule));
            userVehiculeList.add(new UserVehiculePOJO(vehicule));
        }
        devProgressionPOJO.vehicules = devVehiculeList.toArray(DevVehiculePOJO[]::new);
        userProgressionPOJO.vehicules = userVehiculeList.toArray(UserVehiculePOJO[]::new);

        try {
            File file = new File(PATH_PROGRESSION_DEV);
            objectMapper.writeValue(file, devProgressionPOJO);
            System.out.println("Sauvegarde JSON réussie vers " + PATH_PROGRESSION_DEV);
        } catch (IOException e) {
            System.err.println("\nErreur lors de l'écriture du fichier JSON : " + e.getMessage() + "\n");
        }

        try {
            File file = new File(PATH_PROGRESSION_USER);
            objectMapper.writeValue(file, userProgressionPOJO);
            System.out.println("Sauvegarde JSON réussie vers " + PATH_PROGRESSION_USER);
        } catch (IOException e) {
            System.err.println("\nErreur lors de l'écriture du fichier JSON : " + e.getMessage() + "\n");
        }
    }

}