package graphalgorithms;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DepthFirstSearchTest extends SearchSetup{

    DepthFirstSearch dfs;

    @Test
    public void testAtoJ(){
        dfs = new DepthFirstSearch(transportGraph, "A", "J");
        dfs.search();

        String path = "[A, B, C, D, G, J]";
        assertEquals(path , dfs.nodesInPath.toString());


        String order = "[A, B, C, D, G, F, J, H, E, I]";
        assertEquals(order, dfs.nodesVisited.toString());

        assertEquals(2, dfs.transfers);

        System.out.println(dfs);
        dfs.printNodesInVisitedOrder();
        System.out.println();
    }

    @Test
    public void testAtoB(){
        dfs = new DepthFirstSearch(transportGraph, "A", "B");
        dfs.search();

        String path = "[A, B]";
        assertEquals(path, dfs.nodesInPath.toString());


        String order = "[A, B]";
        assertEquals(order, dfs.nodesVisited.toString());

        assertEquals(0, dfs.transfers);

        System.out.println(dfs);
        dfs.printNodesInVisitedOrder();
        System.out.println();

    }

    @Test
    public  void testAtoA() {

        dfs = new DepthFirstSearch(transportGraph, "A" , "A");
        dfs.search();

        assertEquals(1, dfs.nodesVisited.size());   //Because you check the firstIndex
        assertEquals(0, dfs.nodesInPath.size());
        assertEquals(0, dfs.transfers);

        System.out.println(dfs);
        dfs.printNodesInVisitedOrder();
        System.out.println();
    }
}