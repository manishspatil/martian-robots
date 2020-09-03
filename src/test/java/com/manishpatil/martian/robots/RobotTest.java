package com.manishpatil.martian.robots;

import com.manishpatil.martian.Coordinates;
import com.manishpatil.martian.Direction;
import com.manishpatil.martian.Surface;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class RobotTest {

    private static Surface surface;

    @BeforeAll
    static void init() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field instance = Surface.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);

        surface = Surface.getInstance();
        surface.init(50, 50);
    }

    @DisplayName("Robot's initialization must correctly initialize.")
    @Test
    void testInitialize() {
        Robot robot = new Robot();
        Coordinates coordinates = new Coordinates(10, 20);
        robot.init(surface, coordinates, Direction.East);
        String position = robot.getPosition();

        String expectedPosition = "10 20 E";
        assertEquals(expectedPosition, position, "Robot is not initialized successfully!");
    }

    @DisplayName("Robot's initialization throw an exception in case it ia attempted to placeRobot outside of Surface.")
    @Test
    void testInitializeThrowExceptionForOursideOfSurfacePlacement() {
        Robot robot = new Robot();
        Coordinates coordinates = new Coordinates(100, 200);

        Exception exception = Assertions.assertThrows(RuntimeException.class, () -> robot.init(surface, coordinates, Direction.East));

        assertEquals("Robot cannot initialised on Off-Grid coordinates.", exception.getMessage());
    }

    @DisplayName("Robot's re-initialization must throws exception.")
    @Test
    void testExpectedExceptionForReInitialization() {
        Robot robot = new Robot();
        Coordinates coordinates = new Coordinates(10, 20);
        robot.init(surface, coordinates, Direction.East);

        Coordinates newCoordinates = new Coordinates(20, 30);
        Exception exception = Assertions.assertThrows(RuntimeException.class, () -> robot.init(surface, newCoordinates, Direction.East));

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
        Robot robot = new Robot();
        Coordinates coordinates = new Coordinates(10, 20);
        robot.init(surface, coordinates, Direction.East);

        String expectedPosition = "10 20 E";

        assertEquals(expectedPosition, robot.getPosition(), "Robot is not is not correct Position!");
    }

    @Test
    void testTurnLeft() {
        Robot robot = new Robot();
        Coordinates coordinates = new Coordinates(10, 20);
        robot.init(surface, coordinates, Direction.East);

        robot.turnLeft();

        String expectedPosition = "10 20 N";
        assertEquals(expectedPosition, robot.getPosition(), "Robot did not turn Left correctly!");
    }

    @Test
    void testTurnRight() {
        Robot robot = new Robot();
        Coordinates coordinates = new Coordinates(10, 20);
        robot.init(surface, coordinates, Direction.East);

        robot.turnRight();

        String expectedPosition = "10 20 S";
        assertEquals(expectedPosition, robot.getPosition(), "Robot did not turn Right correctly!");
    }

    @Test
    void testMoveForwardTowardsEast() {
        Robot robot = new Robot();
        Coordinates coordinates = new Coordinates(10, 20);
        robot.init(surface, coordinates, Direction.East);

        robot.moveForward();

        String expectedPosition = "11 20 E";
        assertEquals(expectedPosition, robot.getPosition(), "Robot did not Move Forward correctly towards East!");
    }

    @Test
    void testMoveForwardTowardsWest() {
        Robot robot = new Robot();
        Coordinates coordinates = new Coordinates(10, 20);
        robot.init(surface, coordinates, Direction.West);

        robot.moveForward();

        String expectedPosition = "9 20 W";
        assertEquals(expectedPosition, robot.getPosition(), "Robot did not Move Forward correctly towards West!");
    }

    @Test
    void testMoveForwardTowardsSouth() {
        Robot robot = new Robot();
        Coordinates coordinates = new Coordinates(10, 20);
        robot.init(surface, coordinates, Direction.South);

        robot.moveForward();

        String expectedPosition = "10 19 S";
        assertEquals(expectedPosition, robot.getPosition(), "Robot did not Move Forward correctly towards South!");
    }

    @Test
    void testMoveForwardTowardsNorth() {
        Robot robot = new Robot();
        Coordinates coordinates = new Coordinates(10, 20);
        robot.init(surface, coordinates, Direction.North);

        robot.moveForward();

        String expectedPosition = "10 21 N";
        assertEquals(expectedPosition, robot.getPosition(), "Robot did not Move Forward correctly towards North!");
    }

    @Test
    void testMoveForwardOffTheGrid() {
        Robot robot = new Robot();
        Coordinates coordinates = new Coordinates(0, 50);
        robot.init(surface, coordinates, Direction.West);

        robot.moveForward();

        assertFalse(robot.isOnGrid(), "Robot did not Move Off the Grid!");
    }

    @Test
    void testMoveForwardOffTheGridDoesNotMoveDueToPreviousScent() {
        Robot robot = new Robot();
        Coordinates coordinates = new Coordinates(0, 49);
        robot.init(surface, coordinates, Direction.West);

        robot.moveForward();

        Robot robot2 = new Robot();
        coordinates = new Coordinates(0, 49);
        robot2.init(surface, coordinates, Direction.West);

        robot2.moveForward();

        System.out.println(robot2.getPosition());
        System.out.println(robot2.isOnGrid());

        assertTrue(robot2.isOnGrid(), "Robot moved Off the Grid despite of Scent!");
    }

    @Test
    void testMoveForwardOffTheGridShouldReportLastPosition() {
        Robot robot = new Robot();
        Coordinates coordinates = new Coordinates(0, 0);
        robot.init(surface, coordinates, Direction.South);

        robot.moveForward();

        String expectedPosition = "0 0 S LOST";

        assertTrue(!robot.isOnGrid() && expectedPosition.equals(robot.getPosition()),
                "Robot did not report last position corrected Move Off the Grid!");
    }

}