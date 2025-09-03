package app.io;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import app.Collection.CollectionCollectible;
import app.Collection.CollectionPrereglage;
import app.Collection.CollectionProgression;
import app.model.collectible.ComposantExterne;
import app.model.collectible.Mod;
import app.model.collectible.ModArcheo;
import app.model.collectible.ModDeclenchement;
import app.model.collectible.Reacteur;
import app.model.prereglage.BuildArme;
import app.model.prereglage.BuildDescendant;
import app.model.prereglage.ConfigComposantExterne;
import app.model.prereglage.ConfigReacteur;
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
import app.pojo.prereglage.BuildArcheoPOJO;
import app.pojo.prereglage.BuildArmePOJO;
import app.pojo.prereglage.BuildDescendantPOJO;
import app.pojo.prereglage.BuildPOJO;
import app.pojo.prereglage.ConfigComposantExternePOJO;
import app.pojo.prereglage.ConfigReacteurPOJO;
import app.pojo.prereglage.PrereglagePOJO;
import app.pojo.prereglage.PrereglagesPOJO;
import app.pojo.progression.AcolytePOJO;
import app.pojo.progression.ArmePOJO;
import app.pojo.progression.DescendantPOJO;
import app.pojo.progression.ProgressionPOJO;
import app.pojo.progression.VehiculePOJO;

public class JSONSaver {
    private static final String PATH_PROGRESSION = System.getProperty("user.dir") + "\\src\\main\\resources\\json\\progression.json";
    private static final String PATH_COLLECTIBLE = System.getProperty("user.dir") + "\\src\\main\\resources\\json\\collectible.json";
    private static final String PATH_PREREGLAGE = System.getProperty("user.dir") + "\\src\\main\\resources\\json\\prereglage.json";

    private static ObjectMapper objectMapper;

    public static void save() {
        objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        saveProgression();
        saveCollectible();
        savePrereglage();
    }

    private static void saveProgression() {
        ProgressionPOJO progressionPOJO = new ProgressionPOJO();

        List<DescendantPOJO> descendantList = new ArrayList<>();
        for (Descendant descendant : CollectionProgression.getDescendant()) {
            DescendantPOJO descendantPOJO = new DescendantPOJO();
            descendantPOJO.id_descendant = descendant.getIdDescendant();
            descendantPOJO.nom = descendant.getName();
            descendantPOJO.img = descendant.getImage();
            descendantPOJO.cellule_owned = descendant.getConstruit_1();
            descendantPOJO.cellule_schema = descendant.getSchema_1();
            descendantPOJO.stabilisateur_owned = descendant.getConstruit_2();
            descendantPOJO.stabilisateur_schema = descendant.getSchema_2();
            descendantPOJO.catalyseur_owned = descendant.getConstruit_3();
            descendantPOJO.catalyseur_schema = descendant.getSchema_3();
            descendantPOJO.code_owned = descendant.getConstruit_4();
            descendantPOJO.isCraft = descendant.isCraft() ? "y" : "n";
            descendantPOJO.type_mod_archeonique_1 = descendant.getTypeModArcheonique1();
            descendantPOJO.type_mod_archeonique_2 = descendant.getTypeModArcheonique2();
            descendantPOJO.type_mod_archeonique_3 = descendant.getTypeModArcheonique3();
            descendantPOJO.type_mod_archeonique_4 = descendant.getTypeModArcheonique4();
            descendantList.add(descendantPOJO);
        }
        progressionPOJO.descendants = descendantList.toArray(new DescendantPOJO[0]);

        List<ArmePOJO> armeList = new ArrayList<>();
        for (Arme weapon : CollectionProgression.getArme()) {
            ArmePOJO weaponPOJO = new ArmePOJO();
            weaponPOJO.id_arme = weapon.getIdArme();
            weaponPOJO.nom = weapon.getName();
            weaponPOJO.img = weapon.getImage();
            weaponPOJO.polymere_owned = weapon.getConstruit_1();
            weaponPOJO.polymere_schema = weapon.getSchema_1();
            weaponPOJO.fibre_owned = weapon.getConstruit_2();
            weaponPOJO.fibre_schema = weapon.getSchema_2();
            weaponPOJO.nanotubes_owned = weapon.getConstruit_3();
            weaponPOJO.nanotubes_schema = weapon.getSchema_3();
            weaponPOJO.schema = weapon.getConstruit_4();
            weaponPOJO.nbCraft = weapon.getNbCraft();
            weaponPOJO.type_arme = weapon.getTypeArme();
            weaponPOJO.amelioration_1 = weapon.getAmelioration1();
            weaponPOJO.amelioration_2 = weapon.getAmelioration2();
            weaponPOJO.amelioration_3 = weapon.getAmelioration3();
            weaponPOJO.amelioration_4 = weapon.getAmelioration4();
            weaponPOJO.amelioration_5 = weapon.getAmelioration5();
            armeList.add(weaponPOJO);
        }
        progressionPOJO.armes = armeList.toArray(new ArmePOJO[0]);

        List<AcolytePOJO> acolyteList = new ArrayList<>();
        for (Acolyte acolyte : CollectionProgression.getAcolyte()) {
            AcolytePOJO acolytePOJO = new AcolytePOJO();
            acolytePOJO.id_acolyte = acolyte.getIdAcolyte();
            acolytePOJO.nom = acolyte.getName();
            acolytePOJO.img = acolyte.getImage();
            acolytePOJO.cellule_owned = acolyte.getConstruit_1();
            acolytePOJO.cellule_schema = acolyte.getSchema_1();
            acolytePOJO.stabilisateur_owned = acolyte.getConstruit_2();
            acolytePOJO.stabilisateur_schema = acolyte.getSchema_2();
            acolytePOJO.catalyseur_owned = acolyte.getConstruit_3();
            acolytePOJO.catalyseur_schema = acolyte.getSchema_3();
            acolytePOJO.code_owned = acolyte.getConstruit_4();
            acolytePOJO.isCraft = acolyte.isCraft() ? "y" : "n";
            acolyteList.add(acolytePOJO);
        }
        progressionPOJO.acolytes = acolyteList.toArray(new AcolytePOJO[0]);

        List<VehiculePOJO> vehiculeList = new ArrayList<>();
        for (Vehicule vehicule : CollectionProgression.getVehicule()) {
            VehiculePOJO vehiculePOJO = new VehiculePOJO();
            vehiculePOJO.id_vehicule = vehicule.getIdVehicule();
            vehiculePOJO.nom = vehicule.getName();
            vehiculePOJO.img = vehicule.getImage();
            vehiculePOJO.moteur_owned = vehicule.getConstruit_1();
            vehiculePOJO.moteur_schema = vehicule.getSchema_1();
            vehiculePOJO.commande_owned = vehicule.getConstruit_2();
            vehiculePOJO.commande_schema = vehicule.getSchema_2();
            vehiculePOJO.schema_owned = vehicule.getConstruit_3();
            vehiculePOJO.schema_schema = vehicule.getSchema_3();
            vehiculePOJO.systeme_owned = vehicule.getConstruit_4();
            vehiculePOJO.isCraft = vehicule.isCraft() ? "y" : "n";
            vehiculeList.add(vehiculePOJO);
        }
        progressionPOJO.vehicules = vehiculeList.toArray(new VehiculePOJO[0]);

        try {
            File file = new File(PATH_PROGRESSION);
            objectMapper.writeValue(file, progressionPOJO);
            System.out.println("Sauvegarde JSON réussie vers " + PATH_PROGRESSION);
        } catch (IOException e) {
            System.err.println("\nErreur lors de l'écriture du fichier JSON : " + e.getMessage() + "\n");
        }
    }

    private static void saveCollectible() {
        CollectiblePOJO collectiblePOJO = new CollectiblePOJO();

        List<ReacteurPOJO> reacteurList = new ArrayList<>();
        for (Reacteur reacteur : CollectionCollectible.getReacteur()) {
            ReacteurPOJO reacteurPOJO = new ReacteurPOJO();
            reacteurPOJO.id_reacteur = reacteur.getIdReacteur();
            reacteurPOJO.nom = reacteur.getNom();
            reacteurPOJO.img = reacteur.getImg();
            reacteurList.add(reacteurPOJO);
        }
        collectiblePOJO.reacteurs = reacteurList.toArray(new ReacteurPOJO[0]);

        List<ComposantExternePOJO> composantExterneList = new ArrayList<>();
        for (ComposantExterne composantExterne : CollectionCollectible.getComposantExterne()) {
            ComposantExternePOJO composantExternePOJO = new ComposantExternePOJO();
            composantExternePOJO.id_composantExterne = composantExterne.getIdComposantExterne();
            composantExternePOJO.nom = composantExterne.getNom();
            composantExternePOJO.img_auxiliaire = composantExterne.getImgAuxiliaire();
            composantExternePOJO.stat_auxiliaire = composantExterne.getStatAuxiliaire();
            composantExternePOJO.val_stat_auxiliaire = composantExterne.getValStatAuxiliaire();
            composantExternePOJO.img_detecteur = composantExterne.getImgDetecteur();
            composantExternePOJO.stat_detecteur = composantExterne.getStatDetecteur();
            composantExternePOJO.val_stat_detecteur = composantExterne.getValStatDetecteur();
            composantExternePOJO.img_memoire = composantExterne.getImgMemoire();
            composantExternePOJO.stat_memoire = composantExterne.getStatMemoire();
            composantExternePOJO.val_stat_memoire = composantExterne.getValStatMemoire();
            composantExternePOJO.img_processeur = composantExterne.getImgProcesseur();
            composantExternePOJO.stat_processeur = composantExterne.getStatProcesseur();
            composantExternePOJO.val_stat_processeur = composantExterne.getValStatProcesseur();
            composantExterneList.add(composantExternePOJO);
        } 
        collectiblePOJO.composantExternes = composantExterneList.toArray(new ComposantExternePOJO[0]);

        List<ModPOJO> modList = new ArrayList<>();
        for (Mod mod : CollectionCollectible.getAllMod()) {
            ModPOJO modPOJO = new ModPOJO();
            modPOJO.id_mod = mod.getIdMod();
            modPOJO.nom = mod.getNom();
            modPOJO.cout_max = mod.getCoutMax();
            modPOJO.niveaux_max = mod.getNiveauMax();
            modPOJO.polarite = mod.getPolarite();
            modPOJO.type = mod.getType();
            modPOJO.img = mod.getImg();
            modPOJO.mot_cle = mod.getMotCle();
            modList.add(modPOJO);
        }
        collectiblePOJO.mods = modList.toArray(new ModPOJO[0]);

        List<ModArcheoPOJO> modArcheoList = new ArrayList<>();
        for (ModArcheo modArcheo : CollectionCollectible.getModArcheo()) {
            ModArcheoPOJO modArcheoPOJO = new ModArcheoPOJO();
            modArcheoPOJO.id_modArcheo = modArcheo.getIdModArcheo();
            modArcheoPOJO.nom = modArcheo.getNom();
            modArcheoPOJO.polarite = modArcheo.getPolarite();
            modArcheoPOJO.img = modArcheo.getImg();
            modArcheoList.add(modArcheoPOJO);
        }
        collectiblePOJO.modsArcheo = modArcheoList.toArray(new ModArcheoPOJO[0]);

        List<ModDeclenchementPOJO> modDeclenchementList = new ArrayList<>();
        for (ModDeclenchement modDeclenchement : CollectionCollectible.getModDeclenchement()) {
            ModDeclenchementPOJO modDeclenchementPOJO = new ModDeclenchementPOJO();
            modDeclenchementPOJO.nom = modDeclenchement.getNom();
            modDeclenchementPOJO.id_modDeclenchement = modDeclenchement.getIdModDeclenchement();
            modDeclenchementPOJO.nb_stat = modDeclenchement.getNbStat();
            modDeclenchementPOJO.polarite = modDeclenchement.getPolarite();
            modDeclenchementPOJO.img = modDeclenchement.getImg();
            modDeclenchementList.add(modDeclenchementPOJO);
        }
        collectiblePOJO.modsDeclenchement = modDeclenchementList.toArray(new ModDeclenchementPOJO[0]);

        try {
            File file = new File(PATH_COLLECTIBLE);
            objectMapper.writeValue(file, collectiblePOJO);
            System.out.println("Sauvegarde JSON réussie vers " + PATH_COLLECTIBLE);
        } catch (IOException e) {
            System.err.println("\nErreur lors de l'écriture du fichier JSON : " + e.getMessage() + "\n");
        }
    }

    private static void savePrereglage() {
        PrereglagesPOJO prereglagesPOJO = new PrereglagesPOJO();

        List<PrereglagePOJO> prereglageList = new ArrayList<>();
        for (Prereglage prereglage : CollectionPrereglage.getPrereglage()) {
            PrereglagePOJO prereglagePOJO = new PrereglagePOJO();

            // Information de base
            prereglagePOJO.auteur = prereglage.getAuteur();
            prereglagePOJO.nom = prereglage.getNom();
            prereglagePOJO.date = prereglage.getDate();
            prereglagePOJO.mot_cle = prereglage.getMotCle();

            // Descendant + Build
            prereglagePOJO.id_descendant = prereglage.getIdDescendant();
            BuildDescendant buildDescendant = prereglage.getBuildDescendant();
            BuildPOJO buildPOJO_descendant = new BuildPOJO();
            buildPOJO_descendant.type = buildDescendant.getType();
            buildPOJO_descendant.id_mod_1 = buildDescendant.getIdMod1();
            buildPOJO_descendant.polarite_mod_1 = buildDescendant.getPolariteMod1();
            buildPOJO_descendant.id_mod_2 = buildDescendant.getIdMod2();
            buildPOJO_descendant.polarite_mod_2 = buildDescendant.getPolariteMod2();
            buildPOJO_descendant.id_mod_3 = buildDescendant.getIdMod3();
            buildPOJO_descendant.polarite_mod_3 = buildDescendant.getPolariteMod3();
            buildPOJO_descendant.id_mod_4 = buildDescendant.getIdMod4();
            buildPOJO_descendant.polarite_mod_4 = buildDescendant.getPolariteMod4();
            buildPOJO_descendant.id_mod_5 = buildDescendant.getIdMod5();
            buildPOJO_descendant.polarite_mod_5 = buildDescendant.getPolariteMod5();
            buildPOJO_descendant.id_mod_6 = buildDescendant.getIdMod6();
            buildPOJO_descendant.polarite_mod_6 = buildDescendant.getPolariteMod6();
            buildPOJO_descendant.id_mod_7 = buildDescendant.getIdMod7();
            buildPOJO_descendant.polarite_mod_7 = buildDescendant.getPolariteMod7();
            buildPOJO_descendant.id_mod_8 = buildDescendant.getIdMod8();
            buildPOJO_descendant.polarite_mod_8 = buildDescendant.getPolariteMod8();
            buildPOJO_descendant.id_mod_9 = buildDescendant.getIdMod9();
            buildPOJO_descendant.polarite_mod_9 = buildDescendant.getPolariteMod9();
            buildPOJO_descendant.id_mod_10 = buildDescendant.getIdMod10();
            buildPOJO_descendant.polarite_mod_10 = buildDescendant.getPolariteMod10();

            BuildDescendantPOJO buildDescendantPOJO = new BuildDescendantPOJO();
            buildDescendantPOJO.id_mod_descendant = buildDescendant.getIdModDescendant();
            buildDescendantPOJO.polarite_mod_descendant = buildDescendant.getPolariteModDescendant();
            buildDescendantPOJO.id_mod_secondaire = buildDescendant.getIdModSecondaire();
            buildDescendantPOJO.polarite_mod_secondaire = buildDescendant.getPolariteModSecondaire();
            buildDescendantPOJO.id_mod_declenchement = buildDescendant.getIdModDeclenchement();

            buildPOJO_descendant.build_descendant = buildDescendantPOJO;
            buildPOJO_descendant.build_arme = null;

            prereglagePOJO.build_descendant = buildPOJO_descendant;

            // Arme 1 + Build
            prereglagePOJO.id_arme1 = prereglage.getIdArme1();
            BuildArme buildArme1 = prereglage.getBuildArme1();
            BuildPOJO buildPOJO_arme1 = new BuildPOJO();
            buildPOJO_arme1.type = buildArme1.getType();
            buildPOJO_arme1.id_mod_1 = buildArme1.getIdMod1();
            buildPOJO_arme1.polarite_mod_1 = buildArme1.getPolariteMod1();
            buildPOJO_arme1.id_mod_2 = buildArme1.getIdMod2();
            buildPOJO_arme1.polarite_mod_2 = buildArme1.getPolariteMod2();
            buildPOJO_arme1.id_mod_3 = buildArme1.getIdMod3();
            buildPOJO_arme1.polarite_mod_3 = buildArme1.getPolariteMod3();
            buildPOJO_arme1.id_mod_4 = buildArme1.getIdMod4();
            buildPOJO_arme1.polarite_mod_4 = buildArme1.getPolariteMod4();
            buildPOJO_arme1.id_mod_5 = buildArme1.getIdMod5();
            buildPOJO_arme1.polarite_mod_5 = buildArme1.getPolariteMod5();
            buildPOJO_arme1.id_mod_6 = buildArme1.getIdMod6();
            buildPOJO_arme1.polarite_mod_6 = buildArme1.getPolariteMod6();
            buildPOJO_arme1.id_mod_7 = buildArme1.getIdMod7();
            buildPOJO_arme1.polarite_mod_7 = buildArme1.getPolariteMod7();
            buildPOJO_arme1.id_mod_8 = buildArme1.getIdMod8();
            buildPOJO_arme1.polarite_mod_8 = buildArme1.getPolariteMod8();
            buildPOJO_arme1.id_mod_9 = buildArme1.getIdMod9();
            buildPOJO_arme1.polarite_mod_9 = buildArme1.getPolariteMod9();
            buildPOJO_arme1.id_mod_10 = buildArme1.getIdMod10();
            buildPOJO_arme1.polarite_mod_10 = buildArme1.getPolariteMod10();

            buildPOJO_arme1.build_descendant = null;

            BuildArmePOJO buildArme1POJO = new BuildArmePOJO();
            buildArme1POJO.amelio_1 = buildArme1.getAmelio1();
            buildArme1POJO.amelio_2 = buildArme1.getAmelio2();
            buildArme1POJO.amelio_3 = buildArme1.getAmelio3();
            buildArme1POJO.amelio_4 = buildArme1.getAmelio4();
            buildArme1POJO.amelio_5 = buildArme1.getAmelio5();
            buildArme1POJO.attribut_1 = buildArme1.getAttribut1();
            buildArme1POJO.attribut_2 = buildArme1.getAttribut2();
            buildArme1POJO.attribut_3 = buildArme1.getAttribut3();
            buildArme1POJO.attribut_4 = buildArme1.getAttribut4();

            buildPOJO_arme1.build_arme = buildArme1POJO;

            prereglagePOJO.build_arme1 = buildPOJO_arme1;

            // Arme 2 + Build
            prereglagePOJO.id_arme2 = prereglage.getIdArme2();
            BuildArme buildArme2 = prereglage.getBuildArme2();
            BuildPOJO buildPOJO_arme2 = new BuildPOJO();
            buildPOJO_arme2.type = buildArme2.getType();
            buildPOJO_arme2.id_mod_1 = buildArme2.getIdMod1();
            buildPOJO_arme2.polarite_mod_1 = buildArme2.getPolariteMod1();
            buildPOJO_arme2.id_mod_2 = buildArme2.getIdMod2();
            buildPOJO_arme2.polarite_mod_2 = buildArme2.getPolariteMod2();
            buildPOJO_arme2.id_mod_3 = buildArme2.getIdMod3();
            buildPOJO_arme2.polarite_mod_3 = buildArme2.getPolariteMod3();
            buildPOJO_arme2.id_mod_4 = buildArme2.getIdMod4();
            buildPOJO_arme2.polarite_mod_4 = buildArme2.getPolariteMod4();
            buildPOJO_arme2.id_mod_5 = buildArme2.getIdMod5();
            buildPOJO_arme2.polarite_mod_5 = buildArme2.getPolariteMod5();
            buildPOJO_arme2.id_mod_6 = buildArme2.getIdMod6();
            buildPOJO_arme2.polarite_mod_6 = buildArme2.getPolariteMod6();
            buildPOJO_arme2.id_mod_7 = buildArme2.getIdMod7();
            buildPOJO_arme2.polarite_mod_7 = buildArme2.getPolariteMod7();
            buildPOJO_arme2.id_mod_8 = buildArme2.getIdMod8();
            buildPOJO_arme2.polarite_mod_8 = buildArme2.getPolariteMod8();
            buildPOJO_arme2.id_mod_9 = buildArme2.getIdMod9();
            buildPOJO_arme2.polarite_mod_9 = buildArme2.getPolariteMod9();
            buildPOJO_arme2.id_mod_10 = buildArme2.getIdMod10();
            buildPOJO_arme2.polarite_mod_10 = buildArme2.getPolariteMod10();
            
            buildPOJO_arme2.build_descendant = null;

            BuildArmePOJO buildArme2POJO = new BuildArmePOJO();
            buildArme2POJO.amelio_1 = buildArme2.getAmelio1();
            buildArme2POJO.amelio_2 = buildArme2.getAmelio2();
            buildArme2POJO.amelio_3 = buildArme2.getAmelio3();
            buildArme2POJO.amelio_4 = buildArme2.getAmelio4();
            buildArme2POJO.amelio_5 = buildArme2.getAmelio5();
            buildArme2POJO.attribut_1 = buildArme2.getAttribut1();
            buildArme2POJO.attribut_2 = buildArme2.getAttribut2();
            buildArme2POJO.attribut_3 = buildArme2.getAttribut3();
            buildArme2POJO.attribut_4 = buildArme2.getAttribut4();

            buildPOJO_arme2.build_arme = buildArme2POJO;

            prereglagePOJO.build_arme2 = buildPOJO_arme2;

            // Arme 3 + Build
            prereglagePOJO.id_arme3 = prereglage.getIdArme3();
            BuildArme buildArme3 = prereglage.getBuildArme3();
            BuildPOJO buildPOJO_arme3 = new BuildPOJO();
            buildPOJO_arme3.type = buildArme3.getType();
            buildPOJO_arme3.id_mod_1 = buildArme3.getIdMod1();
            buildPOJO_arme3.polarite_mod_1 = buildArme3.getPolariteMod1();
            buildPOJO_arme3.id_mod_2 = buildArme3.getIdMod2();
            buildPOJO_arme3.polarite_mod_2 = buildArme3.getPolariteMod2();
            buildPOJO_arme3.id_mod_3 = buildArme3.getIdMod3();
            buildPOJO_arme3.polarite_mod_3 = buildArme3.getPolariteMod3();
            buildPOJO_arme3.id_mod_4 = buildArme3.getIdMod4();
            buildPOJO_arme3.polarite_mod_4 = buildArme3.getPolariteMod4();
            buildPOJO_arme3.id_mod_5 = buildArme3.getIdMod5();
            buildPOJO_arme3.polarite_mod_5 = buildArme3.getPolariteMod5();
            buildPOJO_arme3.id_mod_6 = buildArme3.getIdMod6();
            buildPOJO_arme3.polarite_mod_6 = buildArme3.getPolariteMod6();
            buildPOJO_arme3.id_mod_7 = buildArme3.getIdMod7();
            buildPOJO_arme3.polarite_mod_7 = buildArme3.getPolariteMod7();
            buildPOJO_arme3.id_mod_8 = buildArme3.getIdMod8();
            buildPOJO_arme3.polarite_mod_8 = buildArme3.getPolariteMod8();
            buildPOJO_arme3.id_mod_9 = buildArme3.getIdMod9();
            buildPOJO_arme3.polarite_mod_9 = buildArme3.getPolariteMod9();
            buildPOJO_arme3.id_mod_10 = buildArme3.getIdMod10();
            buildPOJO_arme3.polarite_mod_10 = buildArme3.getPolariteMod10();

            buildPOJO_arme3.build_descendant = null;

            BuildArmePOJO buildArme3POJO = new BuildArmePOJO();
            buildArme3POJO.amelio_1 = buildArme3.getAmelio1();
            buildArme3POJO.amelio_2 = buildArme3.getAmelio2();
            buildArme3POJO.amelio_3 = buildArme3.getAmelio3();
            buildArme3POJO.amelio_4 = buildArme3.getAmelio4();
            buildArme3POJO.amelio_5 = buildArme3.getAmelio5();
            buildArme3POJO.attribut_1 = buildArme3.getAttribut1();
            buildArme3POJO.attribut_2 = buildArme3.getAttribut2();
            buildArme3POJO.attribut_3 = buildArme3.getAttribut3();
            buildArme3POJO.attribut_4 = buildArme3.getAttribut4();

            buildPOJO_arme3.build_arme = buildArme3POJO;

            prereglagePOJO.build_arme3 = buildPOJO_arme3;

            // Build Archeonique
            List<BuildArcheoPOJO> buildArcheoList = new ArrayList<>();
            for(String k : prereglage.getBuildArcheo().getNodesActivated()) {
                BuildArcheoPOJO buildArcheoPOJO = new BuildArcheoPOJO();
                buildArcheoPOJO.key = k;
                buildArcheoList.add(buildArcheoPOJO);
            }
            prereglagePOJO.build_archeo = buildArcheoList.toArray(new BuildArcheoPOJO[0]);
            prereglagePOJO.id_mod_archeo1 = null;
            prereglagePOJO.id_mod_archeo2 = null;

            List<String> attributList = new ArrayList<>();
            for(String attribut : prereglage.getAttributSaison()) {
                attributList.add(attribut);
            }
            prereglagePOJO.attribut_saison = attributList.toArray(new String[0]);

            // Acolyte et Vehicule
            prereglagePOJO.id_acolyte = prereglage.getIdAcolyte();
            prereglagePOJO.id_vehicule = prereglage.getIdVehicule();

            // Reacteur
            ConfigReacteur configReacteur = prereglage.getConfigReacteur();
            ConfigReacteurPOJO configReacteurPOJO = new ConfigReacteurPOJO();
            configReacteurPOJO.id_reacteur = configReacteur.getIdReacteur();
            configReacteurPOJO.attribut1 = configReacteur.getAttribut1();
            configReacteurPOJO.attribut2 = configReacteur.getAttribut2();
            prereglagePOJO.config_reacteur = configReacteurPOJO;

            // Auxiliaire
            ConfigComposantExterne configComposantExterne_auxiliaire = prereglage.getConfigAuxiliaire();
            ConfigComposantExternePOJO configComposantExternePOJO_auxiliaire = new ConfigComposantExternePOJO();
            configComposantExternePOJO_auxiliaire.id_composantExterne = configComposantExterne_auxiliaire.getIdComposantExterne();
            configComposantExternePOJO_auxiliaire.amelio1 = configComposantExterne_auxiliaire.getAmelio1();
            configComposantExternePOJO_auxiliaire.amelio2 = configComposantExterne_auxiliaire.getAmelio2();
            configComposantExternePOJO_auxiliaire.attribut1 = configComposantExterne_auxiliaire.getAttribut1();
            configComposantExternePOJO_auxiliaire.attribut2 = configComposantExterne_auxiliaire.getAttribut2();
            prereglagePOJO.config_auxiliaire = configComposantExternePOJO_auxiliaire;

            // Detecteur
            ConfigComposantExterne configComposantExterne_detecteur = prereglage.getConfigDetecteur();
            ConfigComposantExternePOJO configComposantExternePOJO_detecteur = new ConfigComposantExternePOJO();
            configComposantExternePOJO_detecteur.id_composantExterne = configComposantExterne_detecteur.getIdComposantExterne();
            configComposantExternePOJO_detecteur.amelio1 = configComposantExterne_detecteur.getAmelio1();
            configComposantExternePOJO_detecteur.amelio2 = configComposantExterne_detecteur.getAmelio2();
            configComposantExternePOJO_detecteur.attribut1 = configComposantExterne_detecteur.getAttribut1();
            configComposantExternePOJO_detecteur.attribut2 = configComposantExterne_detecteur.getAttribut2();
            prereglagePOJO.config_detecteur = configComposantExternePOJO_detecteur;

            // Memoire
            ConfigComposantExterne configComposantExterne_memoire = prereglage.getConfigMemoire();
            ConfigComposantExternePOJO configComposantExternePOJO_memoire = new ConfigComposantExternePOJO();
            configComposantExternePOJO_memoire.id_composantExterne = configComposantExterne_memoire.getIdComposantExterne();
            configComposantExternePOJO_memoire.amelio1 = configComposantExterne_memoire.getAmelio1();
            configComposantExternePOJO_memoire.amelio2 = configComposantExterne_memoire.getAmelio2();
            configComposantExternePOJO_memoire.attribut1 = configComposantExterne_memoire.getAttribut1();
            configComposantExternePOJO_memoire.attribut2 = configComposantExterne_memoire.getAttribut2();
            prereglagePOJO.config_memoire = configComposantExternePOJO_memoire;

            // Processeur
            ConfigComposantExterne configComposantExterne_processeur = prereglage.getConfigProcesseur();
            ConfigComposantExternePOJO configComposantExternePOJO_processeur = new ConfigComposantExternePOJO();
            configComposantExternePOJO_processeur.id_composantExterne = configComposantExterne_processeur.getIdComposantExterne();
            configComposantExternePOJO_processeur.amelio1 = configComposantExterne_processeur.getAmelio1();
            configComposantExternePOJO_processeur.amelio2 = configComposantExterne_processeur.getAmelio2();
            configComposantExternePOJO_processeur.attribut1 = configComposantExterne_processeur.getAttribut1();
            configComposantExternePOJO_processeur.attribut2 = configComposantExterne_processeur.getAttribut2();
            prereglagePOJO.config_processeur = configComposantExternePOJO_processeur;

            // Ajout du prereglage dans la liste
            prereglageList.add(prereglagePOJO);
        }
        prereglagesPOJO.prereglages = prereglageList.toArray(new PrereglagePOJO[0]);

        try {
            File file = new File(PATH_PREREGLAGE);
            objectMapper.writeValue(file, prereglagesPOJO);
            System.out.println("Sauvegarde JSON réussie vers " + PATH_PREREGLAGE);
        } catch (IOException e) {
            System.err.println("\nErreur lors de l'écriture du fichier JSON : " + e.getMessage() + "\n");
        }
    }

}