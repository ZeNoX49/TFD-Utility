package app.Collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import app.model.progression.Acolyte;
import app.model.progression.Arme;
import app.model.progression.Descendant;
import app.model.progression.Vehicule;

public class CollectionProgression {

    // Descendant
    private static Map<Integer, Descendant> mapDescendant = new HashMap<>();
    private static List<Descendant> descendants = new ArrayList<>();
    public static void addDescendant(Descendant descendant) {
        descendants.add(descendant);
        mapDescendant.put(descendant.getIdDescendant(), descendant);
    }
    public static List<Descendant> getDescendant() {
        return descendants;
    }
    public static Descendant getDescendantById(int id) {
        return mapDescendant.get(id);
    }
    public static Descendant getDescendantByName(String name) {
        for(Descendant descendant : descendants) {
            if(descendant.getName().equals(name)) {
                return descendant;
            }
        }
        return null;
    }
    public static void sortDescendant() {
        Collections.sort(descendants);
    }
    public static Set<Integer> getMapDescendantKeys() {
        return mapDescendant.keySet();
    }

    /* ---------------------------------------------------------------------------------------------------- */

    // Arme
    private static Map<Integer, Arme> mapArme = new HashMap<>();
    private static List<Arme> armes = new ArrayList<>();
    public static void addArme(Arme arme) {
        armes.add(arme);
        mapArme.put(arme.getIdArme(), arme);
    }
    public static List<Arme> getArme() {
        return armes;
    }
    public static Arme getArmeById(int id) {
        return mapArme.get(id);
    }
    public static Arme getArmeByName(String name) {
        for(Arme arme : armes) {
            if(arme.getName().equals(name)) {
                return arme;
            }
        }
        return null;
    }
    public static void sortArme() {
        Collections.sort(armes);
    }
    public static Set<Integer> getMapArmeKeys() {
        return mapArme.keySet();
    }

    /* ---------------------------------------------------------------------------------------------------- */

    // Acolyte
    private static Map<Integer, Acolyte> mapAcolyte = new HashMap<>();
    private static List<Acolyte> acolytes = new ArrayList<>();
    public static void addAcolyte(Acolyte acolyte) {
        acolytes.add(acolyte);
        mapAcolyte.put(acolyte.getIdAcolyte(), acolyte);
    }
    public static List<Acolyte> getAcolyte() {
        return acolytes;
    }
    public static Acolyte getAcolyteById(int id) {
        return mapAcolyte.get(id);
    }
    public static Acolyte getAcolyteByName(String name) {
        for(Acolyte acolyte : acolytes) {
            if(acolyte.getName().equals(name)) {
                return acolyte;
            }
        }
        return null;
    }
    public static void sortAcolyte() {
        Collections.sort(acolytes);
    }
    public static Set<Integer> getMapAcolyteKeys() {
        return mapAcolyte.keySet();
    }

    /* ---------------------------------------------------------------------------------------------------- */

    // Vehicule
    private static Map<Integer, Vehicule> mapVehicule = new HashMap<>();
    private static List<Vehicule> vehicules = new ArrayList<>();
    public static void addVehicule(Vehicule vehicule) {
        vehicules.add(vehicule);
        mapVehicule.put(vehicule.getIdVehicule(), vehicule);
    }
    public static List<Vehicule> getVehicule() {
        return vehicules;
    }
    public static Vehicule getVehiculeById(int id) {
        return mapVehicule.get(id);
    }
    public static Vehicule getVehiculeByName(String name) {
        for(Vehicule vehicule : vehicules) {
            if(vehicule.getName().equals(name)) {
                return vehicule;
            }
        }
        return null;
    }
    public static void sortVehicule() {
        Collections.sort(vehicules);
    }
    public static Set<Integer> getMapVehiculeKeys() {
        return mapVehicule.keySet();
    }
    
}