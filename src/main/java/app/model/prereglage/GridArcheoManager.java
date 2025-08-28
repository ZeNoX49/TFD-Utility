package app.model.prereglage;

import javafx.scene.layout.GridPane;

public class GridArcheoManager {
    private final BuildArcheonique build;
    private final GridArcheo gridArcheo;
    private final GridPane gridPane;

    public GridArcheoManager(BuildArcheonique build, GridArcheo gridArcheo, int size) {
        this.build = build;
        this.gridArcheo = gridArcheo;
        this.gridPane = gridArcheo.getGrid(size);

        for(String key : build.getNodesActivated()) {
            gridArcheo.changeState(key, false);
        }
    }

    public void activateNode(String key) {
        build.addNode(key);
        gridArcheo.changeState(key, false);
    }
    
    public void deactivateNode(String key) {
        build.removeNode(key);
        gridArcheo.changeState(key, true);
    }
    
    public GridPane getGridPane() {
        return gridPane;
    }
}