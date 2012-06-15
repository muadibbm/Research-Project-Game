package com.domain.project.core.graph;

import static playn.core.PlayN.*;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.ResourceCallback;

enum Isomer {cis, c, trans, t};
enum EdgeType {W, w, H, h, S, s}; 

public class Edge {
    private ImageLayer layer;

    private int id; //line number in file
    private Isomer isomer;
    private int n1;
    private int n2;
    private EdgeType e1;
    private EdgeType e2;

    public Edge(int id, Isomer isomer, int n1, int n2, EdgeType e1, EdgeType e2) {
        this.id = id;
        this.n1 = n1;
        this.n2 = n2;
        this.isomer = isomer;
        this.e1 = e1;
        this.e2 = e2;
    }

    public int getID() {
        return id;
    }

    @Override
    public String toString() {
        return "Edge ID: " + id + "\t(" + isomer + e1 + e2 + " between " + n1 + " - " + n2 + ")";
    }

}
