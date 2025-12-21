package app.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import app.model.collectible.ComposantExterne;
import app.model.collectible.Mod;
import app.model.collectible.ModArcheo;
import app.model.collectible.ModDeclenchement;
import app.model.collectible.Reacteur;

public class CollectionCollectible {

    // Reacteur
    private static Map<Integer, Reacteur> mapReacteur = new HashMap<>();
    private static List<Reacteur> reacteurs = new ArrayList<>();
    public static void addReacteur(Reacteur reacteur) {
        reacteurs.add(reacteur);
        mapReacteur.put(reacteur.getIdReacteur(), reacteur);
    }
    public static List<Reacteur> getReacteur() {
        return reacteurs;
    }
    public static Reacteur getReacteurById(int id) {
        return mapReacteur.get(id);
    }
    public static Reacteur getReacteurByName(String name) {
        for(Reacteur reacteur : reacteurs) {
            if(reacteur.getNom().equals(name)) {
                return reacteur;
            }
        }
        return null;
    }
    public static void sortReacteur() {
        Collections.sort(reacteurs);
    }
    public static Set<Integer> getMapReacteurKeys() {
        return mapReacteur.keySet();
    }

    /* ---------------------------------------------------------------------------------------------------- */

    // Composants Externes
    private static Map<Integer, ComposantExterne> mapComposantExterne = new HashMap<>();
    private static List<ComposantExterne> composantExternes = new ArrayList<>();
    public static void addComposantExterne(ComposantExterne composantExterne) {
        composantExternes.add(composantExterne);
        mapComposantExterne.put(composantExterne.getIdComposantExterne(), composantExterne);
    }
    public static List<ComposantExterne> getComposantExterne() {
        return composantExternes;
    }
    public static ComposantExterne getComposantExterneById(int id) {
        return mapComposantExterne.get(id);
    }
    public static ComposantExterne getComposantExterneByName(String name) {
        for(ComposantExterne composantExterne : composantExternes) {
            if(composantExterne.getNom().equals(name)) {
                return composantExterne;
            }
        }
        return null;
    }
    public static void sortComposantExterne() {
        Collections.sort(composantExternes);
    }
    public static Set<Integer> getMapComposantExterneKeys() {
        return mapComposantExterne.keySet();
    }

    /* ---------------------------------------------------------------------------------------------------- */

    // Mod
    private static Map<Integer, Mod> mapMod = new HashMap<>();
    private static Map<String, List<Mod>> mods = new HashMap<>();

    public static void initialize() {
        for(String type : Mod.TYPE_MOD) {
            mods.put(type, new ArrayList<>());
        }
    }

    public static void addMod(Mod mod) {
        List<Mod> mods_liste;
        if(getMod(mod.getType()) != null) {
            mods_liste = getMod(mod.getType());
        } else {
            mods_liste = new ArrayList<>();
        }
        mods_liste.add(mod);
        mods.put(mod.getType(), mods_liste);
        mapMod.put(mod.getIdMod(), mod);
    }

    public static Mod getModById(int id) {
        return mapMod.get(id);
    }

    public static List<Mod> getAllMod() {
        List<Mod> allMod = new ArrayList<>();
        for(String type : mods.keySet()) {
            allMod.addAll(getMod(type));
        }
        return allMod;
    }

    public static List<Mod> getMod(String type) {
        return mods.get(type);
    }
    
    public static void sortMod(String type) {
        // List<Mod> mods_liste = getMod(type);
        // Collections.sort(mods_liste);
    }

    /* ---------------------------------------------------------------------------------------------------- */

    // Mods Archeonique
    private static Map<Integer, ModArcheo> mapModArcheo = new HashMap<>();
    private static List<ModArcheo> modArcheos = new ArrayList<>();
    private static Map<String, List<ModArcheo>> mapPolariteModArcheo = new HashMap<>();
    public static void addModArcheo(ModArcheo modArcheo) {
        modArcheos.add(modArcheo);
        mapModArcheo.put(modArcheo.getIdModArcheo(), modArcheo);
        if(!mapPolariteModArcheo.keySet().contains(modArcheo.getPolarite())) {
            mapPolariteModArcheo.put(modArcheo.getPolarite(), new ArrayList<>());
        }
        mapPolariteModArcheo.get(modArcheo.getPolarite()).add(modArcheo);
    }
    public static List<ModArcheo> getModArcheo() {
        return modArcheos;
    }
    public static ModArcheo getModArcheoById(int id) {
        return mapModArcheo.get(id);
    }
    public static ModArcheo getModArcheoByName(String name) {
        for(ModArcheo modArcheo : modArcheos) {
            if(modArcheo.getNom().equals(name)) {
                return modArcheo;
            }
        }
        return null;
    }
    public static void sortModArcheo() {
        Collections.sort(modArcheos);
    }
    public static Set<Integer> getMapModArcheoKeys() {
        return mapModArcheo.keySet();
    }
    public static List<ModArcheo> getModArcheoBypolarite(String polarite) {
        return mapPolariteModArcheo.get(polarite);
    }


    /* ---------------------------------------------------------------------------------------------------- */

    // Mods Declenchement
    private static Map<Integer, ModDeclenchement> mapModDeclenchement = new HashMap<>();
    private static List<ModDeclenchement> modDeclenchements = new ArrayList<>();
    public static void addModDeclenchement(ModDeclenchement modDeclenchement) {
        modDeclenchements.add(modDeclenchement);
        mapModDeclenchement.put(modDeclenchement.getIdModDeclenchement(), modDeclenchement);
    }
    public static List<ModDeclenchement> getModDeclenchement() {
        return modDeclenchements;
    }
    public static ModDeclenchement getModDeclenchementById(int id) {
        return mapModDeclenchement.get(id);
    }
    public static ModDeclenchement getModDeclenchementByName(String name) {
        for(ModDeclenchement modDeclenchement : modDeclenchements) {
            if(modDeclenchement.getNom().equals(name)) {
                return modDeclenchement;
            }
        }
        return null;
    }
    public static void sortmodDeclenchement() {
        Collections.sort(modDeclenchements);
    }
    public static Set<Integer> getMapModDeclenchementKeys() {
        return mapModDeclenchement.keySet();
    }
}