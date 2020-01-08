package graphalgorithms;

import org.junit.Test;

import static org.junit.Assert.*;

public class BreadthFirstPathTest extends SearchSetup {

    BreadthFirstPath bfs;

    @Test
    public void testAtoJ() {
        bfs = new BreadthFirstPath(transportGraph, "A", "J");
        bfs.search();

        String path = "[A, G, J]";
        assertTrue(bfs.toString().contains(path));

        String order = "[A, B, E, G, C, F, H, D, J]";
        assertEquals(order, bfs.nodesVisited.toString());

        assertEquals(bfs.transfers, 1);

        System.out.println(bfs);
        bfs.printNodesInVisitedOrder();
        System.out.println();
    }

    @Test
    public void testAtoB() {
        bfs = new BreadthFirstPath(transportGraph, "A", "B");
        bfs.search();

        assertFalse(bfs.nodesInPath.size() > 2);

        String path = "[A, B]";
        assertEquals(path, bfs.nodesInPath.toString());


        String order = "[A, B]";
        assertEquals(order, bfs.nodesVisited.toString());
        assertEquals(2, bfs.nodesVisited.size());

        assertEquals(0, bfs.transfers);

        System.out.println(bfs);
        bfs.printNodesInVisitedOrder();
        System.out.println();
    }
    
    @Test
    public void testAtoA() {
        bfs = new BreadthFirstPath(transportGraph, "A" , "A");
        bfs.search();

        assertEquals(1, bfs.nodesVisited.size()); //Because you check the firstIndex
        assertEquals(0, bfs.nodesInPath.size());
        assertEquals(0, bfs.transfers);

        System.out.println(bfs);
        bfs.printNodesInVisitedOrder();
        System.out.println();
    }
}