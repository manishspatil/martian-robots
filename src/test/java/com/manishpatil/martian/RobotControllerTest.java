package com.manishpatil.martian;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RobotControllerTest {

    private static final ByteArrayOutputStream pseudoOutputStream = new ByteArrayOutputStream();
    private static final PrintStream originalOutputStream = System.out;

    private static String SampleInputFilePath;

    @BeforeAll
    static void init() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        File file = new File("src/test/resources/SampleInput.txt");
        SampleInputFilePath = file.getAbsolutePath();

        System.setOut(new PrintStream(pseudoOutputStream));

        resetSurface();
    }

    @BeforeEach
    void setUp() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        resetSurface();
        pseudoOutputStream.reset();
    }

    private static void resetSurface() throws NoSuchFieldException, IllegalAccessException {
        Field instance = Surface.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    @AfterAll
    public static void cleanUp() {
        System.setOut(originalOutputStream);
    }

    @Test
    void testRobotControllerForSampleInputs() {
        RobotController.main(new String[]{SampleInputFilePath});

        String expectedPositions = "1 1 E\r\n3 3 N LOST\r\n2 3 S\r\n";
        assertEquals(expectedPositions, pseudoOutputStream.toString());
    }

    @Test
    void testRobotControllerWithSampleInputwithNoBlankLines() {
        File file = new File("src/test/resources/SampleInputWithNoBlankLinks.txt");
        String sampleInputWithNoBlankLinks = file.getAbsolutePath();

        RobotController.main(new String[]{sampleInputWithNoBlankLinks});

        String expectedPositions = "1 1 E\r\n3 3 N LOST\r\n2 3 S\r\n";
        assertEquals(expectedPositions, pseudoOutputStream.toString());
    }

    @Test
    void testRobotControllerForInvalidInput() {
        File file = new File("src/test/resources/WayBeyondSurfaceInput.txt");
        String invalidInputsFilePath = file.getAbsolutePath();

        Exception exception = Assertions.assertThrows(RuntimeException.class,
                () -> RobotController.main(new String[]{invalidInputsFilePath}));

        assertEquals("Invalid coordinates provided for initializing Surface's sizing.", exception.getMessage());
    }
}