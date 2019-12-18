package graphalgorithms;

import model.TransportGraph;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class BreadthFirstPath extends AbstractPathSearch {

    public BreadthFirstPath(TransportGraph graph, String start, String end) {
        super(graph, start, end);

    }

    @Override
    public void search() {
        Queue<Integer> queue = new LinkedList<>();

        nodesVisited.add(graph.getStation(startIndex));
        marked[startIndex] = true;                          // Mark the source as we are at it currently
        queue.add(startIndex);                              // Put it onto queue

        whileloop:
        //Label
        while (!queue.isEmpty()) {
            int vertex = queue.remove();
            //Check if startIndex is equal to endIndex
            if (vertex == endIndex) {
                return;
            }

            for (int adjacentVertex : graph.getAdjacentVertices(vertex)) {
                if (!marked[adjacentVertex]) {           // For every node we have not been yet
                    nodesVisited.add(graph.getStation(adjacentVertex));
                    edgeTo[adjacentVertex] = vertex;    // Save last edge on shortest path
                    marked[adjacentVertex] = true;      // Mark it because it is known and added to shortest path.


                    queue.add(adjacentVertex);
                    if (adjacentVertex == endIndex) {
                        break whileloop;                    //Break to label
                    }
                }
            }
        }

        pathTo(endIndex);

    }
}
