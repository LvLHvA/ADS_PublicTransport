package model;

/**
 * This class represents a coordinate on the TransportGraph
 *
 * @author koenlippe
 */
public class Location {
    
    protected static final double TRAVEL_TIME_PER_SQUARE = 1.5;

    private int x;
    private int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static double travelTime(Location from, Location to) {
        return Math.sqrt(Math.pow(to.x - from.x, 2) + Math.pow(to.y - from.y, 2)) * TRAVEL_TIME_PER_SQUARE;
    }

    public static double travelTime(Station from, Station to) {
        return Math.sqrt(
                        Math.pow(to.getLocation().x - from.getLocation().x, 2) +
                        Math.pow(to.getLocation().y - from.getLocation().y, 2))
                * TRAVEL_TIME_PER_SQUARE;
    }

    @Override
    public String toString() {
        return String.format("(%d,%d)", x, y);
    }
}
