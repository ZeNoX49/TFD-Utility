package app.model.prereglage;

import app.pojo.prereglage.BuildPOJO;

public class Build {

    private String type;
    private Integer idMod1, idMod2, idMod3, idMod4, idMod5, idMod6, idMod7, idMod8, idMod9, idMod10;
    private String polariteMod1, polariteMod2, polariteMod3, polariteMod4, polariteMod5, polariteMod6, polariteMod7, polariteMod8, polariteMod9, polariteMod10;
    // les polarités

    public Build() {
        idMod1 = null;
        idMod2 = null;
        idMod3 = null;
        idMod4 = null;
        idMod5 = null;
        idMod6 = null;
        idMod7 = null;
        idMod8 = null;
        idMod9 = null;
        idMod10 = null;
        
        polariteMod1 = "None";
        polariteMod2 = "None";
        polariteMod3 = "None";
        polariteMod4 = "None";
        polariteMod5 = "None";
        polariteMod6 = "None";
        polariteMod7 = "None";
        polariteMod8 = "None";
        polariteMod9 = "None";
        polariteMod10 = "None";
    }

    public Build(BuildPOJO buildPOJO) {
        type = buildPOJO.type;

        idMod1 = buildPOJO.id_mod_1;
        idMod2 = buildPOJO.id_mod_2;
        idMod3 = buildPOJO.id_mod_3;
        idMod4 = buildPOJO.id_mod_4;
        idMod5 = buildPOJO.id_mod_5;
        idMod6 = buildPOJO.id_mod_6;
        idMod7 = buildPOJO.id_mod_7;
        idMod8 = buildPOJO.id_mod_8;
        idMod9 = buildPOJO.id_mod_9;
        idMod10 = buildPOJO.id_mod_10;

        polariteMod1 = buildPOJO.polarite_mod_1;
        polariteMod2 = buildPOJO.polarite_mod_2;
        polariteMod3 = buildPOJO.polarite_mod_3;
        polariteMod4 = buildPOJO.polarite_mod_4;
        polariteMod5 = buildPOJO.polarite_mod_5;
        polariteMod6 = buildPOJO.polarite_mod_6;
        polariteMod7 = buildPOJO.polarite_mod_7;
        polariteMod8 = buildPOJO.polarite_mod_8;
        polariteMod9 = buildPOJO.polarite_mod_9;
        polariteMod10 = buildPOJO.polarite_mod_10;
    }

    /* ----- SETTER ----- */
    public void setType(String type) { this.type = type; }

    public void setIdMod1(Integer idMod1) { this.idMod1 = idMod1; }
    public void setIdMod2(Integer idMod2) { this.idMod2 = idMod2; }
    public void setIdMod3(Integer idMod3) { this.idMod3 = idMod3; }
    public void setIdMod4(Integer idMod4) { this.idMod4 = idMod4; }
    public void setIdMod5(Integer idMod5) { this.idMod5 = idMod5; }
    public void setIdMod6(Integer idMod6) { this.idMod6 = idMod6; }
    public void setIdMod7(Integer idMod7) { this.idMod7 = idMod7; }
    public void setIdMod8(Integer idMod8) { this.idMod8 = idMod8; }
    public void setIdMod9(Integer idMod9) { this.idMod9 = idMod9; }
    public void setIdMod10(Integer idMod10) { this.idMod10 = idMod10; }

    public void setPolariteMod1(String polariteMod1) { this.polariteMod1 = polariteMod1; }
    public void setPolariteMod2(String polariteMod2) { this.polariteMod2 = polariteMod2; }
    public void setPolariteMod3(String polariteMod3) { this.polariteMod3 = polariteMod3; }
    public void setPolariteMod4(String polariteMod4) { this.polariteMod4 = polariteMod4; }
    public void setPolariteMod5(String polariteMod5) { this.polariteMod5 = polariteMod5; }
    public void setPolariteMod6(String polariteMod6) { this.polariteMod6 = polariteMod6; }
    public void setPolariteMod7(String polariteMod7) { this.polariteMod7 = polariteMod7; }
    public void setPolariteMod8(String polariteMod8) { this.polariteMod8 = polariteMod8; }
    public void setPolariteMod9(String polariteMod9) { this.polariteMod9 = polariteMod9; }
    public void setPolariteMod10(String polariteMod10) { this.polariteMod10 = polariteMod10; }

    /* ----- GETTER ----- */
    public String getType() { return type; }

    public Integer getIdMod1() { return idMod1; }
    public Integer getIdMod2() { return idMod2; }
    public Integer getIdMod3() { return idMod3; }
    public Integer getIdMod4() { return idMod4; }
    public Integer getIdMod5() { return idMod5; }
    public Integer getIdMod6() { return idMod6; }
    public Integer getIdMod7() { return idMod7; }
    public Integer getIdMod8() { return idMod8; }
    public Integer getIdMod9() { return idMod9; }
    public Integer getIdMod10() { return idMod10; }

    public String getPolariteMod1() { return polariteMod1; }
    public String getPolariteMod2() { return polariteMod2; }
    public String getPolariteMod3() { return polariteMod3; }
    public String getPolariteMod4() { return polariteMod4; }
    public String getPolariteMod5() { return polariteMod5; }
    public String getPolariteMod6() { return polariteMod6; }
    public String getPolariteMod7() { return polariteMod7; }
    public String getPolariteMod8() { return polariteMod8; }
    public String getPolariteMod9() { return polariteMod9; }
    public String getPolariteMod10() { return polariteMod10; }
    
}
