package com.domain.project.core.graph;

import java.util.List;
import java.util.ArrayList;

import static playn.core.PlayN.*;

import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.ResourceCallback;

enum Nucleotide {A, ADENINE, T, THYMINE, U, URACIL, G, GUANINE, C, CYTOSINE};
    

public class Node {
    private String img = "images/temp/pea.png";  //path to image file *** dependent on city?? 

    private ImageLayer layer;

    private int id;
    private Nucleotide nucleotide;
    private List<Node> neighbors;
    private Tuple2f coordinates;

    public Node(int id, Nucleotide nucl) {
        this.id = id;
        this.nucleotide = nucl;
        this.neighbors = new ArrayList<Node>();
        this.coordinates = new Tuple2f();
    }

    public Node(int id, Nucleotide nucl, final GroupLayer graphLayer) {
        this.id = id;
        this.nucleotide = nucl;
        this.neighbors = new ArrayList<Node>();
        this.coordinates = new Tuple2f();

        Image image = assets().getImage(img); //hardcoded image for now
        layer = graphics().createImageLayer(image);

        image.addCallback(new ResourceCallback<Image>() {
            @Override
            public void done(Image image) {
                layer.setOrigin(image.width() / 2.0f, image.height() / 2.0f);
                graphLayer.add(layer);
            }

            @Override
            public void error(Throwable e) {
                log().error("error loading node", e);
            }
        });
    }

    public void placeNode(float x, float y) {
        coordinates.x = x;
        coordinates.y = y;
        layer.setTranslation(coordinates.x, coordinates.y);
    }

    public void addNeighbor(Node n) {
        neighbors.add(n);
    }

    public int getID() {
        return id;
    }

    public Nucleotide getNucleotide() {
        return nucleotide;
    }

    @Override
    public String toString() {
        String ret = "Node ID: " + Integer.toString(id) + "(" + nucleotide + ") \t- Neighbors:";
        for(Node n : neighbors) {
            ret = ret + " " + Integer.toString(n.getID()) + "(" + n.getNucleotide() + ")";
        }
        return ret;
    }


}
