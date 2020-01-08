package graphalgorithms;

import model.*;

public class A_Star extends AbstractPathSearch  implements Comparable<A_Star> {


    private double[] distTo;
    private Line[] edgeToType;
    private IndexMinPQ<Double> queue;

    // In Minutes
    private final int METRO_METRO_PENALTY = 6;
    private final int METRO_BUS_PENALTY = 3;


    public A_Star(TransportGraph graph, String start, String end) {
        super(graph, start, end);
        queue = new IndexMinPQ<>(graph.getNumberOfStations());
        distTo = new double[graph.getNumberOfStations()];
        edgeToType = new Line[graph.getNumberOfStations()];

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

            if (v == endIndex) {

                distTo[v] = 0;
                nodesVisited.add(graph.getStation(v));
                pathTo(endIndex);
            } else {

                for (Connection connection : getConnectionsForStation(v)) {

                    //To make sure we get the correct adjacent station instead of the station itself.
                    int adjacentIndex;
                    if (connection.getTo().equals(graph.getStation(v))) {
                        adjacentIndex = graph.getIndexOfStationByName(connection.getFrom().getStationName());
                    } else {
                        adjacentIndex = graph.getIndexOfStationByName(connection.getTo().getStationName());
                    }

                    edgeToType[adjacentIndex] = connection.getLine();

                    //Adding adjacentIndex to visited nodes
                    nodesVisited.add(graph.getStation(adjacentIndex));


                    if (distTo[adjacentIndex] > (
                            distTo[v] + 
                            connection.getWeight() +
                            getTransferPenalty(v, adjacentIndex) +
                            Location.travelTime(graph.getStation(adjacentIndex), graph.getStation(v)))) {
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
        }
    }


    public int getTransferPenalty(int from, int to) {

        Line previousLine = edgeToType[from];
        Line nextLine = graph.getConnection(from, to).getLine();

        //Returning 0 if no previous line
        if (previousLine == null) {
            return 0;
        }


        if (previousLine.getType().equals("metro") && nextLine.getType().equals("metro")) {
            return METRO_METRO_PENALTY;
        } else if ((previousLine.getType().equals("bus") && nextLine.getType().equals("metro")) ||
                (previousLine.getType().equals("metro") && nextLine.getType().equals("bus"))) {
            // Either from bus -> metro or metro -> bus;
            return METRO_BUS_PENALTY;
        }
        return 0;
    }


    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }





    @Override
    public int compareTo(A_Star other) {
        return Integer.compare(this.nodesInPath.size() , other.nodesInPath.size());
    }
}
