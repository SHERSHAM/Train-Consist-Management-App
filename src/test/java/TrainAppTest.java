import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TrainAppTest {

    @Test
    public void testPassengerBogieValidCapacity() throws InvalidCapacityException {
        // Given
        String name = "Sleeper";
        int capacity = 72;

        // When
        PassengerBogie bogie = new PassengerBogie(name, capacity);

        // Then
        assertEquals(name, bogie.name);
        assertEquals(capacity, bogie.capacity);
    }

    @Test
    public void testPassengerBogieInvalidCapacity() {
        // Given
        String name = "Invalid";
        int capacity = 0;

        // When & Then
        InvalidCapacityException exception = assertThrows(InvalidCapacityException.class, () -> {
            new PassengerBogie(name, capacity);
        });

        assertEquals("Capacity must be greater than zero", exception.getMessage());
    }

    @Test
    public void testBogieCreation() {
        // Given
        String name = "Cargo";
        int capacity = 100;

        // When
        Bogie bogie = new Bogie(name, capacity);

        // Then
        assertEquals(name, bogie.name);
        assertEquals(capacity, bogie.capacity);
    }

    @Test
    public void testGoodsBogieCreation() {
        // Given
        String type = "Rectangular";
        String cargo = "Coal";

        // When
        GoodsBogie bogie = new GoodsBogie(type, cargo);

        // Then
        assertEquals(type, bogie.type);
        assertEquals(cargo, bogie.cargo);
    }

    @Test
    public void testGoodsBogieToString() {
        // Given
        GoodsBogie bogie = new GoodsBogie("Cylindrical");

        // When
        String result = bogie.toString();

        // Then
        assertEquals("Cylindrical bogie carries no cargo", result);
    }
}