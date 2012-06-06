package com.domain.project.core;

import static playn.core.PlayN.*;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.ResourceCallback;

enum Isomer {cis, c, trans, t};
enum EdgeType {W, w, H, h, S, s}; 

public class Edge {
    private String img;

    private ImageLayer layer;

    private int id; //line number in file
    private Isomer isomer;
    private EdgeType e1;
    private EdgeType e2;

    public Edge(int id, Isomer isomer, EdgeType e1, EdgeType e2) {
        this.id = id;
        this.isomer = isomer;
        this.e1 = e1;
        this.e2 = e2;
    }

    public int getID() {
        return id;
    }

    @Override
    public String toString() {
        return id + "(" + isomer + e1 + e2 + ")";
    }

}
