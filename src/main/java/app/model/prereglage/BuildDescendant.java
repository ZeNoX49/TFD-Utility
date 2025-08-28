package app.model.prereglage;

import app.pojo.prereglage.BuildPOJO;

public class BuildDescendant extends Build {

    private Integer idModDescendant, idModSecondaire;
    private String polariteModDescendant, polariteModSecondaire;
    private Integer idModDeclenchement;

    public BuildDescendant() {
        super();

        setType("descendant");

        idModDescendant = null;
        polariteModDescendant = "None";
        idModSecondaire = null;
        polariteModSecondaire = "None";
        idModDeclenchement = null;
    }

    public BuildDescendant(BuildPOJO buildPOJO) {
        super(buildPOJO);

        setType("descendant");

        idModDescendant = buildPOJO.build_descendant.id_mod_descendant;
        polariteModDescendant = buildPOJO.build_descendant.polarite_mod_descendant;
        idModSecondaire = buildPOJO.build_descendant.id_mod_secondaire;
        polariteModSecondaire = buildPOJO.build_descendant.polarite_mod_secondaire;
        idModDeclenchement = buildPOJO.build_descendant.id_mod_declenchement;
    }

    /* ----- SETTER ----- */
    public void setIdModDescendant(Integer idModDescendant) { this.idModDescendant = idModDescendant; }
    public void setPolariteModDescendant(String polariteModDescendant) { this.polariteModDescendant = polariteModDescendant; }
    public void setIdModSecondaire(Integer idModSecondaire) { this.idModSecondaire = idModSecondaire; }
    public void setPolariteModSecondaire(String polariteModSecondaire) { this.polariteModSecondaire = polariteModSecondaire; }
    public void setIdModDeclenchement(Integer idModDeclenchement) { this.idModDeclenchement = idModDeclenchement; }

    /* ----- GETTER ----- */
    public Integer getIdModDescendant() { return idModDescendant; }
    public String getPolariteModDescendant() { return polariteModDescendant; }
    public Integer getIdModSecondaire() { return idModSecondaire; }
    public String getPolariteModSecondaire() { return polariteModSecondaire; }
    public Integer getIdModDeclenchement() { return idModDeclenchement; }
}