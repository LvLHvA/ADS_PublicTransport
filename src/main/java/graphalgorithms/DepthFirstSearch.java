package graphalgorithms;

import model.TransportGraph;

public class DepthFirstSearch extends AbstractPathSearch {

    public DepthFirstSearch(TransportGraph graph, String start, String end) {
        super(graph, start, end);
    }

    @Override
    public void search() {
        nodesVisited.add(graph.getStation(startIndex)); //Add starting index to visited
        searchRecursive(startIndex);
        pathTo(endIndex);
    }

    private void searchRecursive(int v) {

        if(v == endIndex) {
            return;
        }
        marked[v] = true;
        for (int adjacentVertex : graph.getAdjacentVertices(v)) {

            if (!marked[adjacentVertex]) {
                nodesVisited.add(graph.getStation(adjacentVertex));
                edgeTo[adjacentVertex] = v;
                marked[adjacentVertex] = true;


                if (adjacentVertex == endIndex) {
                    return;
                }
                searchRecursive(adjacentVertex);

            }
        }

    }
}
