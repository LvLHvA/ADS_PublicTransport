package graphalgorithms;

import model.TransportGraph;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BreadthFirstPathTest {

    TransportGraph transportGraph;


    String[] redLine = {"red", "metro", "A", "B", "C", "D"};
    String[] blueLine = {"blue", "metro", "E", "B", "F", "G"};
    String[] greenLine = {"green", "metro", "H", "I", "C", "G", "J"};
    String[] yellowLine = {"yellow", "bus", "A", "E", "H", "D", "G", "A"};

    @Before
    public void setUp() {
        transportGraph = new TransportGraph.Builder()
                .addLine(redLine)
                .addLine(blueLine)
                .addLine(greenLine)
                .addLine(yellowLine)
                .buildStationSet()
                .addLinesToStations()
                .buildConnections()
                .build();
    }

    @Test
    public void testAtoJ() {
        BreadthFirstPath bf = new BreadthFirstPath(transportGraph, "A", "J");
        bf.search();

        String path = "[A, G, J]";
        assertTrue(bf.toString().contains(path));

        String order = "[B, E, G, C, F, D, I, H, J]";
        assertTrue(bf.nodesVisited.toString().contains(order));

        assertEquals(bf.transfers, 1);
    }

    @Test
    public void testAtoB() {
        BreadthFirstPath bf = new BreadthFirstPath(transportGraph, "A", "B");
        bf.search();

        assertFalse(bf.nodesInPath.size() > 2);

        String path = "[A, B]";
        assertEquals(bf.nodesInPath.toString(), path);


        String order = "[B]";
        assertEquals(bf.nodesVisited.toString(), order);
        assertEquals(1, bf.nodesVisited.size());

        assertEquals(bf.transfers, 0);
    }
}