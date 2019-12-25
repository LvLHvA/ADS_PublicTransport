package graphalgorithms;

import model.Connection;
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
     *
     * @param endVertex The station (vertex) as an index
     */
    public void pathTo(int endVertex) {
        //Builds path from startIndex --> vertex
        //Is called only once after search is finished.

        if (!hasPathTo(endVertex)) return;               // No path possible so returning

        Line currentLine = null;
        for (int i = endVertex; i != startIndex; i = edgeTo[i]) {
            Station currentStation = graph.getStation(i);
            Station nextStation = graph.getStation(edgeTo[i]);

            nodesInPath.add(currentStation);        //Adding connected station to path

            //Setting currentLine when on first iteration
            if (currentLine == null) {
                currentLine = currentStation.getCommonLine(nextStation);
                continue;
            }

            //Checking if next station is on current line
            boolean sameLine = currentLine.getStationsOnLine().contains(nextStation);

            if (!sameLine) {
                //Adding 1 to transfers and setting currentLine to the next Line
                transfers++;
                currentLine = currentStation.getCommonLine(nextStation);
            }

        }

        nodesInPath.add(graph.getStation(startIndex));    //Adding source station to path
        Collections.reverse(nodesInPath);                 //Reversing because the loops adds in reverse order.


    }

    /**
     * Method to count the number of transfers in a path of vertices.
     * Uses the line information of the connections between stations.
     * If to consecutive connections are on different lines there was a transfer.
     */
    public void countTransfers() {
        // Implemented in pathTo
        //Explained in report
    }


    public List<Connection> getConnectionsForStation(int fromStationIndex) {

        List<Connection> list = new ArrayList();

        for (Integer adjacentVertex : graph.getAdjacentVertices(fromStationIndex)) {
            list.add(graph.getConnection(fromStationIndex, adjacentVertex));
        }

        return list;
    }


    /**
     * Method to print all the nodes that are visited by the search algorithm implemented in one of the subclasses.
     */
    public void printNodesInVisitedOrder() {
        System.out.print("Nodes in visited order: ");
        for (Station vertex : nodesVisited) {
            System.out.print(String.format("(%s)-", vertex.toString()));
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
