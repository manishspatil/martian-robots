package com.manishpatil.martian.robots;

import com.manishpatil.martian.Coordinates;
import com.manishpatil.martian.Direction;
import com.manishpatil.martian.Surface;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class RobotTest {

    Surface surface = Surface.getInstance();

    @BeforeEach
    void setUp() {
        if (!surface.isInitialized()) {
            surface.init(50, 50);
        }
    }

    @AfterEach
    void tearDown() {
    }

    @DisplayName("Robot's initialization must correctly initialize.")
    @Test
    void testInitialize() {
        Robot robot = new Robot();
        Coordinates coordinates = new Coordinates(10, 20);
        robot.init(surface, coordinates, Direction.East);
        String position = robot.getPosition();

        String expectedPosition = "10 20 E";
        assertTrue(expectedPosition.equals(position), "Robot is not initialized successfully!");
    }

    @DisplayName("Robot's initialization throw an exception in case it ia attempted to placeRobot outside of Surface.")
    @Test
    void testInitializeThrowExceptionForOursideOfSurfacePlacement() {
        Robot robot = new Robot();
        Coordinates coordinates = new Coordinates(100, 200);

        Exception exception = Assertions.assertThrows(RuntimeException.class, () -> {
            robot.init(surface, coordinates, Direction.East);
        });

        assertEquals("Robot cannot initialised on Off-Grid coordinates.", exception.getMessage());
    }

    @DisplayName("Robot's re-initialization must throws exception.")
    @Test
    void testExpectedExceptionForReInitialization() {
        Robot robot = new Robot();
        Coordinates coordinates = new Coordinates(10, 20);
        robot.init(surface, coordinates, Direction.East);

        Coordinates newCoordinates = new Coordinates(20, 30);
        Exception exception = Assertions.assertThrows(RuntimeException.class, () -> {
            robot.init(surface, newCoordinates, Direction.East);
        });

        assertEquals("Robot does not support re-initialization.", exception.getMessage());
    }

    @DisplayName("Robot's should be Placed on the Surface.")
    @Test
    void testIsOnGridForRobotPlacedOnSurface() {
        Robot robot = new Robot();
        Coordinates coordinates = new Coordinates(10, 20);
        robot.init(surface, coordinates, Direction.East);

        assertTrue(robot.isOnGrid(), "Robot is not Placed on surface!");
    }

    @Test
    void getPosition() {
    }
}