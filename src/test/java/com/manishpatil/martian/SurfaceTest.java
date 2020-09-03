package com.manishpatil.martian;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SurfaceTest {

    private Surface surface = Surface.getInstance();

    @BeforeEach
    void setUp() {
    }

    @DisplayName("Surface's Lower Left Coordinates must be 0,0")
    @Test
    @Order(1)
    void testLowerLeftCoordinates() {
        Coordinates lowerLeftCoordinates = surface.getLowerLeftCoordinates();

        assertTrue(lowerLeftCoordinates.getX() == 0, "Lower Left X Coordinate is not equal to 0.");
        assertTrue(lowerLeftCoordinates.getY() == 0, "Lower Left Y Coordinate is not equal to 0.");
    }

    @DisplayName("Surface's initialization must initialize successfully with Upper Right Coordinates.")
    @Test
    @Order(2)
    void testInitialize() {
        surface.init(50, 50);
        Coordinates lowerLeftCoordinates = surface.getLowerLeftCoordinates();
        Coordinates upperRightCoordinates = surface.getUpperRightCoordinates();

        assertTrue(surface.isInitialized(), "Surface's state is not initialized!");
        assertTrue(lowerLeftCoordinates.getX() == 0, "Lower Left X Coordinate is not equal to 0.");
        assertTrue(lowerLeftCoordinates.getY() == 0, "Lower Left Y Coordinate is not equal to 0.");
        assertTrue(upperRightCoordinates.getX() == 50, "Upper Right X Coordinate is not equal to 50.");
        assertTrue(upperRightCoordinates.getY() == 50, "Upper Right Y Coordinate is not equal to 50.");
    }

    @DisplayName("Surface's re-initialization must throws exception.")
    @Test
    @Order(3)
    void testExpectedExceptionForReInitialization() {
        Exception exception = Assertions.assertThrows(RuntimeException.class, () -> {
            surface.init(20, 30);
        });

        assertEquals("Surface does not support re-initialization.", exception.getMessage());
    }

    @DisplayName("Given Off-Grid Coordinates should be placed on Surface.")
    @Test
    @Order(4)
    void testIsOnGridForOffGridCoordinates() {
        Coordinates coordinatesToCheck = new Coordinates(60, 100);

        assertFalse(surface.isOnGrid(coordinatesToCheck));
    }

    @DisplayName("Given on-Grid Coordinates should be placed on Surface.")
    @Test
    @Order(5)
    void testIsOnGridForValidGridCoordinates() {
        Coordinates coordinatesToCheck = new Coordinates(10, 20);
        assertTrue(surface.isOnGrid(coordinatesToCheck));
    }

    @Test
    void placeRobot() {
        // TODO: Need to Implement Robot
    }

    @Test
    void getPlacedRobots() {
        // TODO: Need to Implement Robots
    }
}