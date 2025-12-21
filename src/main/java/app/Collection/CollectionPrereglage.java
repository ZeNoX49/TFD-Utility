package app.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import app.model.prereglage.Prereglage;

public class CollectionPrereglage {

    // Prereglages
    private static List<Prereglage> prereglages = new ArrayList<>();
    public static void addPrereglage(Prereglage prereglage) {
        prereglages.add(prereglage);
    }
    public static void removePrereglage(Prereglage prereglage) {
        prereglages.remove(prereglage);
    }
    public static List<Prereglage> getPrereglage() {
        return prereglages;
    }
    public static void sortPrereglage(String type) {
        Collections.sort(prereglages, Prereglage.getComparator(type));
    }
    
}