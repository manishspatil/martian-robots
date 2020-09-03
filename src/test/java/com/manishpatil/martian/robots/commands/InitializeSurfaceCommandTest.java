package com.manishpatil.martian.robots.commands;

import com.manishpatil.martian.Coordinates;
import com.manishpatil.martian.Surface;
import com.manishpatil.martian.robots.commands.InitializeSurfaceCommand;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class InitializeSurfaceCommandTest {

    private Surface surface = Surface.getInstance();

    @BeforeAll
    static void init() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        resetSurface();
    }

    private static void resetSurface() throws NoSuchFieldException, IllegalAccessException {
        Field instance = Surface.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    @DisplayName("Surface's initialization throws an exception while attempting to initialize Surface with Upper Right X " +
            "Maximum size.")
    @Test
    @Order(1)
    void testExpectedExceptionWhenInvalidInstructionProvided() {
        InitializeSurfaceCommand command = new InitializeSurfaceCommand(Surface.getInstance(), "Foo bar");
        Exception exception = Assertions.assertThrows(RuntimeException.class, () -> command.execute());

        String expcetedMessage = "Invalid instruction provided for Initializing Surface. Correct instruction is the" +
                " upper-right coordinates of the Surface.";
        assertEquals(expcetedMessage, exception.getMessage());
        System.out.println(exception.getMessage());
    }

    @DisplayName("Surface's initialization throws an exception while attempting to initialize Surface with Upper Right X " +
            "Maximum size.")
    @Test
    @Order(2)
    void testExpectedExceptionWithMoreThanAllowedUpperRightXCoordinate() {
        InitializeSurfaceCommand command = new InitializeSurfaceCommand(Surface.getInstance(), "51 1");
        Exception exception = Assertions.assertThrows(RuntimeException.class, () -> command.execute());

        assertEquals("Invalid coordinates provided for initializing Surface's sizing.", exception.getMessage());
    }

    @DisplayName("Surface's initialization throws an exception while attempting to initialize Surface with Upper Right Y " +
            "Maximum size.")
    @Test
    @Order(3)
    void testExpectedExceptionWithMoreThanAllowedUpperRightYCoordinate() {
        InitializeSurfaceCommand command = new InitializeSurfaceCommand(Surface.getInstance(), "1 51");
        Exception exception = Assertions.assertThrows(RuntimeException.class, () -> command.execute());

        assertEquals("Invalid coordinates provided for initializing Surface's sizing.", exception.getMessage());
    }

    @DisplayName("Surface's initialization must initialize successfully with Upper Right Coordinates.")
    @Test
    @Order(4)
    void testInitialize() {
        InitializeSurfaceCommand command = new InitializeSurfaceCommand(Surface.getInstance(), "50 50");
        command.execute();

        Coordinates lowerLeftCoordinates = surface.getLowerLeftCoordinates();
        Coordinates upperRightCoordinates = surface.getUpperRightCoordinates();

        assertEquals(0, lowerLeftCoordinates.getX(), "Lower Left X Coordinate is not equal to 0.");
        assertEquals(0, lowerLeftCoordinates.getY(), "Lower Left Y Coordinate is not equal to 0.");
        assertEquals(50, upperRightCoordinates.getX(), "Upper Right X Coordinate is not equal to 50.");
        assertEquals(50, upperRightCoordinates.getY(), "Upper Right Y Coordinate is not equal to 50.");
    }

    @DisplayName("Surface's re-initialization must throws exception.")
    @Test
    @Order(5)
    void testExpectedExceptionForReInitialization() {
        InitializeSurfaceCommand command = new InitializeSurfaceCommand(Surface.getInstance(), "10 10");
        Exception exception = Assertions.assertThrows(RuntimeException.class, () ->  command.execute());

        assertEquals("Surface does not support re-initialization.", exception.getMessage());
    }

}