package controller;

import graphalgorithms.BreadthFirstPath;
import model.TransportGraph;

public class TransportGraphLauncher {

    public static void main(String[] args) {
        String[] redLine = {"red", "metro", "A", "B", "C", "D"};
        String[] blueLine = {"blue", "metro", "E", "B", "F", "G"};
        String[] greenLine = {"green", "metro", "H", "I", "C", "G", "J"};
        String[] yellowLine = {"yellow", "bus", "A", "E", "H", "D", "G", "A"};

        // TODO Use the builder to build the graph from the String array.
        TransportGraph transportGraph = new TransportGraph.Builder()
                .addLine(redLine)
                .addLine(blueLine)
                .addLine(greenLine)
                .addLine(yellowLine)
                .buildStationSet()
                .addLinesToStations()
                .buildConnections()
                .build();

//        Uncomment to test the builder:
        //System.out.println(transportGraph);

//        Uncommented to test the DepthFirstPath algorithm
        BreadthFirstPath dfpTest = new BreadthFirstPath(transportGraph, "A", "F");
        dfpTest.search();
        System.out.println(dfpTest);
        dfpTest.printNodesInVisitedOrder();
        System.out.println();

        //TODO: Change to Depth First
//        Uncommented to test the BreadthFirstPath algorithm
        /*BreadthFirstPath bfsTest = new BreadthFirstPath(transportGraph, "A", "J");
        bfsTest.search();
        System.out.println(bfsTest);
        bfsTest.printNodesInVisitedOrder();*/

    }
}
