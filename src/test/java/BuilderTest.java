import model.TransportGraph;
import org.junit.Test;

public class BuilderTest {
    @Test
    public void addLineTest(){
        String[] redLine = {"red", "metro", "A", "B", "C", "D"};
        String[] blueLine = {"blue", "metro", "E", "B", "F", "G"};



        TransportGraph tg = new TransportGraph.Builder()
                .addLine(redLine)
                .addLine(blueLine)
                .buildStationSet()
                .addLinesToStations()
                .buildConnections()
                .build();

        System.out.println(tg.toString());
    }

}
