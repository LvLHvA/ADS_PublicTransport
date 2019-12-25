package graphalgorithms;

import model.Connection;
import model.IndexMinPQ;
import model.Station;
import model.TransportGraph;

public class DijkstraShortestPath extends AbstractPathSearch {


    private double[] distTo;
    private IndexMinPQ<Double> queue;

    public DijkstraShortestPath(TransportGraph graph, String start, String end) {
        super(graph, start, end);
        queue = new IndexMinPQ<>(graph.getNumberOfStations());
        distTo = new double[graph.getNumberOfStations()];

        for (int i = 0; i < graph.getNumberOfStations(); i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }

        distTo[startIndex] = 0;
        queue.insert(startIndex, 0.0);

    }


    @Override
    public void search() {

        while (!queue.isEmpty()) {
            int v = queue.delMin();

            for (Connection connection : getConnectionsForStation(v)) {

                //To make sure we get the correct adjacent station instead of the station itself.
                int adjacentIndex;
                if (connection.getTo().equals(graph.getStation(v))) {
                    adjacentIndex = graph.getIndexOfStationByName(connection.getFrom().getStationName());
                } else {
                    adjacentIndex = graph.getIndexOfStationByName(connection.getTo().getStationName());
                }

                //Adding adjacentIndex to visited nodes
                nodesVisited.add(graph.getStation(adjacentIndex));



                if (distTo[adjacentIndex] > (distTo[v] + connection.getWeight())) {
                    //Setting new dist value
                    distTo[adjacentIndex] = (distTo[v] + connection.getWeight());
                    //Setting self to be the new fasest route.
                    edgeTo[adjacentIndex] = v;

                    if (queue.contains(adjacentIndex)) {
                        queue.changeKey(adjacentIndex, distTo[adjacentIndex]);
                    } else {
                        queue.insert(adjacentIndex, distTo[adjacentIndex]);
                    }
                }

            }
        }

        pathTo(endIndex);
    }

    @Override
    public boolean hasPathTo(int vertex) {
        return distTo[vertex] < Double.POSITIVE_INFINITY;
    }
}
