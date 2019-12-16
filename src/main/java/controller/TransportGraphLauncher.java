package controller;

import graphalgorithms.BreadthFirstPath;
import graphalgorithms.DepthFirstSearch;
import model.Station;
import model.TransportGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

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
        System.out.println(transportGraph);

        //Uncommented to test the DepthSerach algorithm
        DepthFirstSearch bfsTest = new DepthFirstSearch(transportGraph, "E", "J");
        bfsTest.search();
        System.out.println(bfsTest);
        bfsTest.printNodesInVisitedOrder();
        System.out.println();

        // TODO: 16-12-19 A.5


        TreeMap<Station, List<Station>> visited = new TreeMap();


        for (Station station : transportGraph.getStationList()) {
            visited.putIfAbsent(station, new ArrayList());
            for (Station station1 : transportGraph.getStationList()) {
                if (station != station1 && !visited.get(station1).contains(station)) {
                    visited.get(station).add(station1);

                    bfsTest = new DepthFirstSearch(transportGraph, station.getStationName(), station1.getStationName());
                    bfsTest.search();
                    bfsTest.pathTo(transportGraph.getIndexOfStationByName(station1.getStationName()));
                    System.out.println(bfsTest);

                }
            }
        }


//        Uncommented to test the BreathFirst algorithm
        BreadthFirstPath dfpTest = new BreadthFirstPath(transportGraph, "E", "J");
        dfpTest.search();
        System.out.println(dfpTest);
        dfpTest.printNodesInVisitedOrder();


//

    }

}
