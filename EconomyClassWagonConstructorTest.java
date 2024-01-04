package Lab8;


import Lab6.EconomyClassWagon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EconomyClassWagonConstructorTest {

    @Test
    public void testConstructSuccess() {
        try {
            EconomyClassWagon wagon = new EconomyClassWagon(20, 10);
        } catch (WagonOverloadedException e) {
            fail();
        }
    }

    /**
     * Tests the construction failure scenarios when the wagon is expected to be overloaded.
     */
    @Test
    public void testConstructFailsOverloadedMoreLuggage() {
        WagonOverloadedException e = assertThrows(WagonOverloadedException.class, () -> {
            EconomyClassWagon wagon = new EconomyClassWagon(101, 60);
        });
        assertEquals(e.getMessage(), "Too many people or luggage to load to a wagon");
    }

    @Test
    public void testConstructFailsOverloadedMorePeople() {
        WagonOverloadedException e = assertThrows(WagonOverloadedException.class, () -> {
            EconomyClassWagon wagon = new EconomyClassWagon(60, 101);
        });
        assertEquals(e.getMessage(), "Too many people or luggage to load to a wagon");
    }

    /**
     * Tests the construction failure scenarios when negative arguments are provided.
     */
    @Test
    public void testConstructFailsNegativePassengerCount() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            EconomyClassWagon wagon = new EconomyClassWagon(-1, 40);
        });
        assertEquals(e.getMessage(), "Passenger count cannot be less than zero");
    }

    @Test
    public void testConstructFailsNegativeLuggageCount() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            EconomyClassWagon wagon = new EconomyClassWagon(44, -84);
        });
        assertEquals(e.getMessage(), "Luggage count cannot be less than zero");
    }


}
