import java.lang.Integer;

import java.util.Map;
import java.util.HashMap;

public class GameMap {

    private Map<Integer, Node> nodes;
    private Map<Integer, Edge> edges;

    public GameMap() {
        nodes = new HashMap<Integer, Node>();
        edges = new HashMap<Integer, Edge>();
    }
}
