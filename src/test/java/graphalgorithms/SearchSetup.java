package graphalgorithms;

import model.TransportGraph;
import org.junit.Before;

public class SearchSetup {

    TransportGraph transportGraph;

    String[] redLine = {"red", "metro", "A", "B", "C", "D"};
    String[] blueLine = {"blue", "metro", "E", "B", "F", "G"};
    String[] greenLine = {"green", "metro", "H", "I", "C", "G", "J"};
    String[] yellowLine = {"yellow", "bus", "A", "E", "H", "D", "G", "A"};

    @Before
    public void setUp() {
        transportGraph = new TransportGraph.Builder()
                .addLine(redLine)
                .addLine(blueLine)
                .addLine(greenLine)
                .addLine(yellowLine)
                .buildStationSet()
                .addLinesToStations()
                .buildConnections()
                .build();
    }
}
