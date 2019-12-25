package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class LocationTest {

    @Test
    public void testDist() {
        Location l1 = new Location(0,0);
        Location l2 = new Location(12,5);

        assertEquals(13 * Location.TRAVEL_TIME_PER_SQUARE, Location.travelTime(l1, l2), 0);
    }

    @Test
    public void testFromAssignmentC() {
        Location l1 = new Location(3,5);
        Location l2 = new Location(3,6);

        assertEquals(1 * Location.TRAVEL_TIME_PER_SQUARE, Location.travelTime(l1, l2), 0);
    }
}