package controller;

import graphalgorithms.A_Star;
import graphalgorithms.DepthFirstSearch;
import graphalgorithms.DijkstraShortestPath;
import model.Station;
import model.TransportGraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TransportGraphLauncher2 {

    public static void main(String[] args) throws Exception {
        String[] redLine = {"red", "metro", "Haven", "Marken", "Steigerplein", "Centrum", "Meridiaan", "Dukdalf", "Oostvaarders"};
        String[] blueLine = {"blue", "metro", "Trojelaan", "Coltrane Cirkel", "Meridiaan", "Robijnpark", "Violetplantsoen"};
        String[] purpleLine = {"purple", "metro", "Grote Sluis", "Grootzeil", "Coltrane Cirkel", "Centrum", "Swingstraat"};
        String[] greenLine = {"green", "metro", "Ymeerdijk", "Trojelaan", "Steigerplein", "Swingstraat", "Bachgracht", "Nobelplein"};
        String[] yellowLine = {"yellow", "bus", "Ymeerdijk", "Haven", "Nobelplein", "Violetplantsoen", "Oostvaarders", "Grote Sluis", "Ymeerdijk"};


        double[] redWeights = new double[]{4.5, 4.7, 6.1, 3.5, 5.4, 5.6};
        double[] blueWeights = new double[]{6.0, 5.3, 5.1, 3.3};
        double[] purpleWeights = new double[]{6.2, 5.2, 3.8, 3.6};
        double[] greenWeights = new double[]{5.0, 3.7, 6.9, 3.9, 3.4};
        double[] yellowWeights = new double[]{19, 37, 25, 22, 28, 26};


        // A lot of options for optimizations
        int[] redLocations = new int[]{14, 1, 12, 3, 10, 5, 8, 8, 6, 9, 3, 10, 0, 11};
        int[] blueLocations = new int[]{9, 3, 7, 6, 6, 9, 3, 3, 5, 14};
        int[] purpleLocations = new int[]{2, 3, 4, 6, 7, 6, 8, 8, 10, 9};
        int[] greenLocations = new int[]{9, 0, 9, 3, 10, 5, 10, 9, 11, 11, 12, 13};
        int[] yellowLocations = new int[]{9, 0, 14, 1, 12, 13, 5, 14, 0, 11, 2, 3, 9, 0};


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
                .addLocationToStationsForLine(redLocations, "red")
                .addLocationToStationsForLine(blueLocations, "blue")
                .addLocationToStationsForLine(purpleLocations, "purple")
                .addLocationToStationsForLine(greenLocations, "green")
                .addLocationToStationsForLine(yellowLocations, "yellow")
                .build();


//        System.out.println(transportGraph);


//        DijkstraShortestPath dijk = new DijkstraShortestPath(transportGraph, "Haven", "Coltrane Cirkel");
//        dijk.search();
//        System.out.println(dijk);
//        dijk.printNodesInVisitedOrder();
//        System.out.println();
//
//        BreadthFirstPath bfsTest = new BreadthFirstPath(transportGraph, "Haven", "Coltrane Cirkel");
//        bfsTest.search();
//        System.out.println(bfsTest);
//        bfsTest.printNodesInVisitedOrder();
//        System.out.println();
//
//
        DepthFirstSearch dfsTest = new DepthFirstSearch(transportGraph, "Haven", "Coltrane Cirkel");
        dfsTest.search();
        System.out.println(dfsTest);
        dfsTest.printNodesInVisitedOrder();
        System.out.println();

        A_Star aStar = new A_Star(transportGraph, "Haven", "Coltrane Cirkel");
        aStar.search();
        System.out.println(aStar);
        aStar.printNodesInVisitedOrder();
        System.out.println();


        // Overview //
//        List<DijkstraShortestPath> dijkstra = new ArrayList<>();
//
//        for (Station station1 : transportGraph.getStationList()) {
//            for (Station station2 : transportGraph.getStationList()) {
//                if(station1.equals(station2)) {
//                    continue;
//                }
//                DijkstraShortestPath dijk = new DijkstraShortestPath(transportGraph,
//                        station1.getStationName(),
//                        station2.getStationName());
//                dijk.search();
//                dijkstra.add(dijk);
//
//            }
//        }
//
//        Collections.sort(dijkstra);
//
//        for (int i = 0; i < 10; i++) {
//            System.out.println(dijkstra.get(i));
//        }

//        System.out.println();
//
//        List<A_Star> a_star = new ArrayList<>();
//
//        for (Station station1 : transportGraph.getStationList()) {
//            for (Station station2 : transportGraph.getStationList()) {
//                if(station1.equals(station2)) {
//                    continue;
//                }
//                A_Star aStar = new A_Star(transportGraph,
//                        station1.getStationName(),
//                        station2.getStationName());
//                aStar.search();
//                a_star.add(aStar);
//
//            }
//        }
//
//        Collections.sort(a_star);
//
//        for (int i = 0; i < 10; i++) {
//            System.out.println(a_star.get(i));
//        }




    }
}
