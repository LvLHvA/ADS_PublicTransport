package graphalgorithms;

import model.Connection;
import model.IndexMinPQ;
import model.TransportGraph;

public class DijkstraShortestPath extends AbstractPathSearch {


    private double[] distTo;
    private IndexMinPQ<Double> queue;

    public DijkstraShortestPath(TransportGraph graph, String start, String end) {
        super(graph, start, end);
        System.out.println("NofStations: " + graph.getNumberOfStations());
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

        while(!queue.isEmpty()) {
            System.out.println(queue.size());
            int v = queue.delMin();

            for (Connection connection : getConnectionsForStation(v)) {


                //To make sure we get the corret adjacent station and the station itself.
                int adjecentToIndex = 0;
                if(connection.getTo().equals(graph.getStation(v))){
                    adjecentToIndex = graph.getIndexOfStationByName(connection.getFrom().getStationName());
                } else {
                    adjecentToIndex = graph.getIndexOfStationByName(connection.getTo().getStationName());
                }

                if(distTo[adjecentToIndex] > distTo[v] + connection.getWeight()){
                    distTo[adjecentToIndex] = distTo[v] + connection.getWeight();
                    edgeTo[adjecentToIndex] = adjecentToIndex;

                    if(queue.contains(adjecentToIndex)) {
                        queue.changeKey(adjecentToIndex, distTo[adjecentToIndex]);
                    } else {
                        queue.insert(adjecentToIndex, distTo[adjecentToIndex]);
                    }
                }

            }
        }

    }

    @Override
    public boolean hasPathTo(int vertex) {
        return distTo[vertex] < Double.POSITIVE_INFINITY;
    }
}
