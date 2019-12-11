package graphalgorithms;

import model.Line;
import model.Station;
import model.TransportGraph;

import java.util.*;

/**
 * Abstract class that contains methods and attributes shared by the DepthFirstPath en BreadthFirstPath classes
 */
public abstract class AbstractPathSearch {

    protected boolean[] marked;                     // Used for marking visited vertices
    protected int[] edgeTo;                         // To keep track of how edges are connected
    protected int transfers = 0;
    protected List<Station> nodesVisited;           //Is build in the search method
    protected List<Station> nodesInPath;
    protected LinkedList<Integer> verticesInPath;
    protected TransportGraph graph;
    protected final int startIndex;
    protected final int endIndex;



    public AbstractPathSearch(TransportGraph graph, String start, String end) {
        startIndex = graph.getIndexOfStationByName(start);
        endIndex = graph.getIndexOfStationByName(end);
        this.graph = graph;
        nodesVisited = new ArrayList<>();
        marked = new boolean[graph.getNumberOfStations()];
        edgeTo = new int[graph.getNumberOfStations()];
        nodesInPath = new ArrayList<>();
    }

    public abstract void search();

    /**
     * @param vertex Determines whether a path exists to the station as an index from the start station
     * @return
     */
    public boolean hasPathTo(int vertex) {
        return marked[vertex];
    }


    /**
     * Method to build the path to the vertex, index of a Station.
     * First the LinkedList verticesInPath, containing the indexes of the stations, should be build, used as a stack
     * Then the list nodesInPath containing the actual stations is build.
     * Also the number of transfers is counted.
     * @param endVertex The station (vertex) as an index
     */
    public void pathTo(int endVertex) {
        //Builds path from startIndex --> vertex
        //Is called only once after search is finished.

        if(!hasPathTo(endVertex)) return;               // No path possible so returning
        nodesInPath.add(graph.getStation(endVertex));   // Adding current vertex to nodes in path.

        if(endVertex != startIndex) {
            pathTo(edgeTo[endVertex]);                  // Calling pathTo again with the node connected
                                                        // to the current one
        } else {
            Collections.reverse(nodesInPath);           // Because this method 'walks back' the list needs
                                                        // to be inverted
        }



        //TODO: From book -> not working correctly -> dont know why
//        for(int i = endVertex; i != startIndex; i = edgeTo[i]) {
//            System.out.println(graph.getStation(endVertex));
//            nodesInPath.add(graph.getStation(endVertex));
//        }
//        nodesInPath.add(graph.getStation(startIndex));

        // TODO Count transfers...
    }

    /**
     * Method to count the number of transfers in a path of vertices.
     * Uses the line information of the connections between stations.
     * If to consecutive connections are on different lines there was a transfer.
     */
    public void countTransfers() {
        // TODO
    }


    /**
     * Method to print all the nodes that are visited by the search algorithm implemented in one of the subclasses.
     */
    public void printNodesInVisitedOrder() {
        System.out.print("Nodes in visited order: ");
        for (Station vertex : nodesVisited) {
            System.out.print(vertex.getStationName() + " ");
        }
        System.out.println();
    }

    @Override
    public String toString() {
        StringBuilder resultString = new StringBuilder(String.format("Path from %s to %s: ", graph.getStation(startIndex), graph.getStation(endIndex)));
        resultString.append(nodesInPath).append(" with " + transfers).append(" transfers");
        return resultString.toString();
    }

}
