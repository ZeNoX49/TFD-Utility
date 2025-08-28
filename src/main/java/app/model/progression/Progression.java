package app.model.progression;

public class Progression implements Comparable<Progression> {
    private String name;
    private String image;
    
    private String mat_1;
    private String mat_2;
    private String mat_3;
    private String mat_4;

    private String necessaire;

    private String construit_1;
    private String construit_2;
    private String construit_3;
    private String construit_4;

    private String schema_1;
    private String schema_2;
    private String schema_3;

    private boolean craft;

    // SETTER
    public void setName(String name) { this.name = name; }
    public void setImage(String image) { this.image = image; }

    public void setMat_1(String mat_1) { this.mat_1 = mat_1; }
    public void setMat_2(String mat_2) { this.mat_2 = mat_2; }
    public void setMat_3(String mat_3) { this.mat_3 = mat_3; }
    public void setMat_4(String mat_4) { this.mat_4 = mat_4; }

    public void setNecessaire(String necessaire) { this.necessaire = necessaire; }

    public void setConstruit_1(String construit_1) { this.construit_1 = construit_1; }
    public void setConstruit_2(String construit_2) { this.construit_2 = construit_2; }
    public void setConstruit_3(String construit_3) { this.construit_3 = construit_3; }
    public void setConstruit_4(String construit_4) { this.construit_4 = construit_4; }

    public void setSchema_1(String schema_1) { this.schema_1 = schema_1; }
    public void setSchema_2(String schema_2) { this.schema_2 = schema_2; }
    public void setSchema_3(String schema_3) { this.schema_3 = schema_3; }

    public void setCraft(boolean craft) { this.craft = craft; }

    // GETTER
    public String getName() { return name; }
    public String getImage() { return image; }

    public String getMat_1() { return mat_1; }
    public String getMat_2() { return mat_2; }
    public String getMat_3() { return mat_3; }
    public String getMat_4() { return mat_4; }

    public String getNecessaire() { return necessaire; }

    public String getConstruit_1() { return construit_1; }
    public String getConstruit_2() { return construit_2; }
    public String getConstruit_3() { return construit_3; }
    public String getConstruit_4() { return construit_4; }

    public String getSchema_1() { return schema_1; }
    public String getSchema_2() { return schema_2; }
    public String getSchema_3() { return schema_3; }
    
    public boolean isCraft() { return craft; }

    @Override
    public int compareTo(Progression autre) {
        return this.name.compareToIgnoreCase(autre.name);
    }

}