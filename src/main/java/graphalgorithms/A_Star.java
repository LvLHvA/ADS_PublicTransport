package graphalgorithms;

import model.TransportGraph;

public class A_Star extends AbstractPathSearch  implements Comparable<A_Star> {

    public A_Star(TransportGraph graph, String start, String end) {
        super(graph, start, end);
    }

    @Override
    public void search() {

    }



    @Override
    public int compareTo(A_Star other) {
        return Integer.compare(this.nodesInPath.size() , other.nodesInPath.size());
    }
}
