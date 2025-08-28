package app.model.prereglage;

import java.util.ArrayList;
import java.util.List;

public class BuildArcheonique {

    private static List<String> nodes = new ArrayList<>();

    private List<String> nodesActivated = new ArrayList<>();

    public static void addGlobalNode(String key) {
        if(!nodes.contains(key)) {
            nodes.add(key);
        }
    }

    public void addNode(String key) {
        if(!nodesActivated.contains(key)) {
            nodesActivated.add(key);
        }
    }

    public void removeNode(String key) {
        if(nodesActivated.contains(key)) {
            nodesActivated.remove(key);
        }
    }

    public void setNode(String key, boolean active) {
        if(active) {
            addNode(key);
        } else {
            removeNode(key);
        }
    }

    public List<String> getNodes() {
        return nodes;
    }

    public List<String> getNodesActivated() {
        return nodesActivated;
    }
    
}