package controller;

import model.TransportGraph;

public class TransportGraphLauncher2 {

    public static void main(String[] args) throws ClassNotFoundException {
        String[] redLine = {"red", "metro", "Haven", "Marken", "Steigerplein", "Centrum", "Meridiaan", "Dukdalf", "Oostvaarders"};
        String[] blueLine = {"blue", "metro", "Trojelaan", "Coltranee Cirkel", "Meridiaan", "Robijnpark", "Violetplantsoen"};
        String[] purpleLine = {"purple", "metro", "Grote Sluis", "Grootzeil", "Coltrane Cirkel", "Centrum", "Swingstraat"};
        String[] greenLine = {"green", "metro", "Ymeerdijk", "Trojelaan", "Steigerplein", "Bachgracht", "Swingstraat", "Nobelplein"};
        String[] yellowLine = {"yellow", "bus", "Ymeerdijk", "Haven", "Nobelplein", "Violetplantsoen", "Oostvaarders", "Grote Sluis", "Ymeerdijk"};


        double[] redWeights = new double[]{4.5, 4.7, 6.1, 3.5, 5.4, 5.6};
        double[] blueWeights = new double[]{6.0, 5.3, 5.1, 3.3};
        double[] purpleWeights = new double[]{6.2, 5.2, 3.8, 3.6};
        double[] greenWeights = new double[]{5.0, 3.7, 6.9, 3.9, 3.4};
        double[] yellowWeights = new double[]{19, 37, 25, 22, 28, 26};


        TransportGraph transportGraph = new TransportGraph.Builder()
                .addLine(redLine)
                .addLine(blueLine)
                .addLine(purpleLine)
                .addLine(greenLine)
                .addLine(yellowLine)
                .buildStationSet()
                .addLinesToStations()
                .buildConnections()
                .addWeight(redLine, redWeights)
                .addWeight(blueLine, blueWeights)
                .addWeight(purpleLine, purpleWeights)
                .addWeight(greenLine, greenWeights)
                .addWeight(yellowLine, yellowWeights)
                .build();


//        System.out.println(transportGraph);


//        BreadthFirstPath bfsTest = new BreadthFirstPath(transportGraph, "Haven", "Oostvaarders");
//        bfsTest.search();
//        System.out.println(bfsTest);
//        bfsTest.printNodesInVisitedOrder();
//        System.out.println();
//
//        DepthFirstSearch dfsTest = new DepthFirstSearch(transportGraph, "Haven", "Oostvaarders");
//        bfsTest.search();
//        System.out.println(dfsTest);
//        bfsTest.printNodesInVisitedOrder();
//        System.out.println();
    }
}
