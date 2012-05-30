import static playn.core.PlayN.*;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.ResourceCallback;

enum Isomer {CIS, C, TRANS, T};
enum EdgeType {W, w, H, h, S, s}; 

public class Edge {
    private String img;

    private ImageLayer layer;

    private int n1_id;
    private int n2_id;
    private Isomer isomer;
    private EdgeType e1;
    private EdgeType e2;


    public Edge() {
        this.n1_id = -1;
        this.n2_id = -1;
    }

    public Edge(int n1, int n2, Isomer i, EdgeType e1, EdgeType e2) {
        this.n1_id = n1;
        this.n2_id = n2;
        this.isomer = i;
        this.e1 = e1;
        this.e2 = e2;
    }


}
