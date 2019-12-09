import model.TransportGraph;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BuilderTest {
    @Test
    public void addLineTest(){
        String[] redLine = {"red", "metro", "A", "B", "C", "D"};
        String[] blueLine = {"blue", "metro", "E", "B", "F", "G"};
        String[] greenLine = {"green", "metro", "H", "I", "C", "G", "J"};
        String[] yellowLine = {"yellow", "bus", "A", "E", "H", "D", "G", "A"};

        TransportGraph tg = new TransportGraph.Builder()
                .addLine(redLine)
                .addLine(blueLine)
                .addLine(greenLine)
                .addLine(yellowLine)
                .buildStationSet()
                .addLinesToStations()
                .buildConnections()
                .build();

        assertEquals("Graph with 10 vertices and 15 edges: \n" +
                "A: B-E-G\n" +
                "B: A-C-E-F\n" +
                "C: B-D-G-I\n" +
                "D: C-G-H\n" +
                "E: A-B-H\n" +
                "F: B-G\n" +
                "G: A-C-D-F-J\n" +
                "H: D-E-I\n" +
                "I: C-H\n" +
                "J: G\n", tg.toString());

    }

}
