package com.domain.project.core;

import static playn.core.PlayN.*;

import playn.core.ResourceCallback;

import java.lang.Integer;

import java.util.Map;
import java.util.HashMap;

import java.io.ByteArrayInputStream;


import playn.core.GroupLayer;

public class Graph {

    private Map<Integer, Node> nodes;
    private Map<Integer, Edge> edges;

    private final String PATH = "graph_data/";

    public Graph() {
        this.nodes = new HashMap<Integer, Node>();
        this.edges = new HashMap<Integer, Edge>();
    }

    public void generateGraph(String filename, GroupLayer groupLayer) {
        parseGraphFile(filename);

    }

    private void parseGraphFile(String filename) {

        assets().getText(PATH + filename, new ResourceCallback<String>() {
            @Override
            public void done(String resource) {
                String[] entries = null;
                String[] subEntries = null;
                ByteArrayInputStream entry = null;
                Node node = null;
                Nucleotide nucl = Nucleotide.A;

                int nodeID, neighborID, lineNumber;
                int data;

                entries = resource.split("\n");
                for(String s1 : entries) {
                    if(s1.startsWith("%")) continue;

                    s1 = s1.replaceAll("\\s+", "");
                    s1 = s1.replaceAll("([0-9]*)([ATUGC])([0-9]*)(\\([AB]\\))-([ATUGC])([0-9]*)(\\([AB]\\))-([a-zA-Z]*)-(0)", "$1 $2 $3 $4 $5 $6 $7 $8 $9"); 
                    subEntries = s1.split(" ");

//                    for(String s2 : subEntries) {
//                        System.out.println(s2);
//                    }

                    //parse first node
                    //parse nucleotide
                    switch(subEntries[1].charAt(0)) {
                        case 'A':
                            nucl = Nucleotide.A;
                            break;

                        case 'T':
                            nucl = Nucleotide.T;
                            break;

                        case 'U':
                            nucl = Nucleotide.U;
                            break;

                        case 'G':
                            nucl = Nucleotide.G;
                            break;

                        case 'C':
                            nucl = Nucleotide.C;
                            break;
                    }

                    //parse nodeID
                    nodeID = Integer.parseInt(subEntries[2]);

                    addNode(nodeID, nucl);

                    //parse second node
                    //assign neighbor 
                    switch(subEntries[4].charAt(0)) {
                        case 'A':
                            nucl = Nucleotide.A;
                            break;

                        case 'T':
                            nucl = Nucleotide.T;
                            break;

                        case 'U':
                            nucl = Nucleotide.U;
                            break;

                        case 'G':
                            nucl = Nucleotide.G;
                            break;

                        case 'C':
                            nucl = Nucleotide.C;
                            break;
                    }
                    neighborID = Integer.parseInt(subEntries[5]);

                    addNode(neighborID, nucl);

                }

            }

            @Override
            public void error(Throwable e) {
                System.err.println("error loading graph data " + e);
            }
        });
    }

    public void addNode(int id, Nucleotide nucl) {
        Node n = new Node(id, nucl);
        this.nodes.put(new Integer(id), n); 

        System.out.println(n);
    }

}
