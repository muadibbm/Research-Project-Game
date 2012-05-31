import java.lang.Integer;

import java.util.Map;
import java.util.HashMap;

import playn.core.GroupLayer;

public class Graph {

    private Map<Integer, Node> nodes;
    private Map<Integer, Edge> edges;

    public Graph(GroupLayer graphLayer) {
        nodes = new HashMap<Integer, Node>();
        edges = new HashMap<Integer, Edge>();
    }
}
