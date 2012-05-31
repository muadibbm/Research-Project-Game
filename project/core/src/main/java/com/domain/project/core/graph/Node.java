import java.util.List;
import java.util.ArrayList;

import static playn.core.PlayN.*;

import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.ResourceCallback;

enum Nucleotides {A, ADENINE, T, THYMINE, U, URACIL, G, GUANINE, C, CYTOSINE};

public class Node {
    private String img = "images/temp/pea.png";  //path to image file *** dependent on city?? 

    private ImageLayer layer;

    private int id;
    private Nucleotides nucleotide;
    private List<Node> neighbors;
    private Tuple2f coordinates;

    public Node(final GroupLayer graph_layer, final float x, final float y, Nucleotides n, int id) {
        Image image = assets().getImage(img);
        this.layer = graphics().createImageLayer(image);

        this.id = id;

        this.neighbors = new ArrayList<Node>();
        this.coordinates = new Tuple2f(x, y); //might not be necessary
        this.nucleotide = n;


        image.addCallback(new ResourceCallback<Image>() {
            @Override
            public void done(Image image) {
                layer.setOrigin(image.width() / 2.0f, image.height() / 2.0f);
                layer.setTranslation(x, y);
                graph_layer.add(layer);
            }

            @Override
            public void error(Throwable err) {
                System.err.println("error loading node image");
            }
        });
    }





}
