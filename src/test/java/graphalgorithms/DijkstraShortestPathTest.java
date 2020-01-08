package graphalgorithms;

import org.junit.Test;

import static org.junit.Assert.*;

public class DijkstraShortestPathTest extends SearchSetup {

    DijkstraShortestPath dijk;

    @Test
    public void testAtoJ() {
        dijk = new DijkstraShortestPath(transportGraph, "A", "J");
        dijk.search();
        System.out.println(dijk);
        dijk.printNodesInVisitedOrder();

        String path = "[A, G, J]";
        assertTrue(dijk.toString().contains(path));

//        String order = "[B, E, G, A, C, E, F, A, C, D, F, J, B, G, C, G, H, B, D, G, I, D, E, I, C, H, A, B, H]"; //TODO: Check if this is correct
//        assertEquals(order, dijk.nodesVisited.toString());
//
//        assertEquals(dijk.transfers, 1);

        System.out.println(dijk);
        dijk.printNodesInVisitedOrder();
    }

    @Test
    public void testAtoB() {
        dijk = new DijkstraShortestPath(transportGraph, "A", "B");
        dijk.search();

        assertFalse(dijk.nodesInPath.size() > 2);

        String path = "[A, B]";
        assertEquals(path, dijk.nodesInPath.toString());


        String order = "[B, E, G, B, A, C, D, F, J, A, B, H, G, D, E, I, B, G, C, H, C, G, H, B, D, G, I]";
        assertEquals(order, dijk.nodesVisited.toString());
        assertEquals(27, dijk.nodesVisited.size());

        assertEquals(0, dijk.transfers);

        System.out.println(dijk);
        dijk.printNodesInVisitedOrder();
        System.out.println();
    }



}