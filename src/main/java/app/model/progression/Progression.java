package app.model.progression;

public class Progression implements Comparable<Progression> {
    private String name;
    private String image;
    
    private String mat_1;
    private String mat_2;
    private String mat_3;
    private String mat_4;

    private int necessaire;

    private int construit_1;
    private int construit_2;
    private int construit_3;
    private int construit_4;

    private int schema_1;
    private int schema_2;
    private int schema_3;

    private boolean craft;

    // SETTER
    public void setName(String name) { this.name = name; }
    public void setImage(String image) { this.image = image; }

    public void setMat_1(String mat_1) { this.mat_1 = mat_1; }
    public void setMat_2(String mat_2) { this.mat_2 = mat_2; }
    public void setMat_3(String mat_3) { this.mat_3 = mat_3; }
    public void setMat_4(String mat_4) { this.mat_4 = mat_4; }

    public void setNecessaire(int necessaire) { this.necessaire = necessaire; }

    public void setConstruit_1(int construit_1) { this.construit_1 = construit_1; }
    public void setConstruit_2(int construit_2) { this.construit_2 = construit_2; }
    public void setConstruit_3(int construit_3) { this.construit_3 = construit_3; }
    public void setConstruit_4(int construit_4) { this.construit_4 = construit_4; }

    public void setSchema_1(int schema_1) { this.schema_1 = schema_1; }
    public void setSchema_2(int schema_2) { this.schema_2 = schema_2; }
    public void setSchema_3(int schema_3) { this.schema_3 = schema_3; }

    public void setCraft(boolean craft) { this.craft = craft; }

    // GETTER
    public String getName() { return this.name; }
    public String getImage() { return this.image; }

    public String getMat_1() { return this.mat_1; }
    public String getMat_2() { return this.mat_2; }
    public String getMat_3() { return this.mat_3; }
    public String getMat_4() { return this.mat_4; }

    public int getNecessaire() { return this.necessaire; }

    public int getConstruit_1() { return this.construit_1; }
    public int getConstruit_2() { return this.construit_2; }
    public int getConstruit_3() { return this.construit_3; }
    public int getConstruit_4() { return this.construit_4; }

    public int getSchema_1() { return this.schema_1; }
    public int getSchema_2() { return this.schema_2; }
    public int getSchema_3() { return this.schema_3; }
    
    public boolean isCraft() { return this.craft; }

    @Override
    public int compareTo(Progression autre) {
        return this.name.compareToIgnoreCase(autre.name);
    }

}