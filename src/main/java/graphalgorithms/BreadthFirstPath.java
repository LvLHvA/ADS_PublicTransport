package graphalgorithms;

import model.IndexMinPQ;
import model.TransportGraph;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class BreadthFirstPath extends AbstractPathSearch {

    public BreadthFirstPath(TransportGraph graph, String start, String end) {
        super(graph, start, end);

    }

    @Override
    public void search() {
        searchRecursive(startIndex);
        System.out.println(Arrays.toString(edgeTo));
        pathTo(endIndex);

//        Arrays.stream(edgeTo).map(e -> graph.getStation())
    }

    public void searchRecursive(int source) {
        Queue<Integer> queue = new PriorityQueue<>();
        marked[source] = true;                          // Mark the source as we are at it currently
        queue.add(source);                              // Put it onto queue

        while(!queue.isEmpty()) {
            int vertex = queue.remove();
            for (int adjacentVertex : graph.getAdjacentVertices(vertex)) {
                if(!marked[adjacentVertex]) {           // For every node we have not been yet
                    edgeTo[adjacentVertex] = vertex;    // Save last edge on shortest path
                    marked[adjacentVertex] = true;      // Mark it because it is known and added to shortest path.
                    queue.add(adjacentVertex);
                }
            }
        }

    }
}
