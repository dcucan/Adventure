package droz00.adventure.gui;

import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;
import static guru.nidi.graphviz.model.Factory.to;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import droz00.adventure.Game;
import droz00.adventure.places.Place;
import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.RankDir;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Link;
import javafx.scene.image.Image;


public class MapGenerator {



    private final Game game;

    private Graph graph;

    public MapGenerator(Game game){
        this.game = game;
    }

    public MapGenerator generate() {
        graph = graph("map").graphAttr().with(RankDir.LEFT_TO_RIGHT);

        Place currentPlace = game.getCurrentPlace();

        // Iterate over all places
        for (Place place : game.getPlaceManager().getPlaces()) {
            Link[] links = new Link[place.getNeighbors().size()];

            // Create array of all links to all neighbours
            int link = 0;
            for(Place neighbor : place.getNeighbors().values()) {
                links[link++] = to(node(neighbor.getName()));
            }

            // If current room set color to red
            Color color = place.equals(currentPlace) ? Color.RED : Color.BLACK;

            // Add current room to the graph with links
            graph = graph.with(node(place.getName()).with(color).link(links));
        }

        return this;
    }

    public InputStream toInputStream() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        try {
            Graphviz.fromGraph(graph).width(720).height(540).render(Format.PNG).toOutputStream(os);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        byte[] bytes = os.toByteArray();

        return new ByteArrayInputStream(bytes);
    }

    public Image toImage(){
        return new Image(generate().toInputStream());
    }
}
