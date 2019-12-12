package controller;

import graphalgorithms.BreadthFirstPath;
import graphalgorithms.DepthFirstSearch;
import model.TransportGraph;

public class TransportGraphLauncher {

    public static void main(String[] args) {
        String[] redLine = {"red", "metro", "A", "B", "C", "D"};
        String[] blueLine = {"blue", "metro", "E", "B", "F", "G"};
        String[] greenLine = {"green", "metro", "H", "I", "C", "G", "J"};
        String[] yellowLine = {"yellow", "bus", "A", "E", "H", "D", "G", "A"};


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

        //Uncommented to test the DepthSerach algorithm
        DepthFirstSearch bfsTest = new DepthFirstSearch(transportGraph, "E", "J");
        bfsTest.search();
        System.out.println(bfsTest);
        bfsTest.printNodesInVisitedOrder();
        System.out.println();

//        Uncommented to test the BreathFirst algorithm
        BreadthFirstPath dfpTest = new BreadthFirstPath(transportGraph, "E", "J");
        dfpTest.search();
        System.out.println(dfpTest);
        dfpTest.printNodesInVisitedOrder();


//

    }
}
