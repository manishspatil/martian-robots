package com.manishpatil.martian.robots;

import com.manishpatil.martian.Surface;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ControllerTest {

    private static String SampleInputFilePath;

    @BeforeAll
    static void init() {
        File file = new File("src/test/resources/SampleInput.txt");
        SampleInputFilePath = file.getAbsolutePath();
    }

    @BeforeEach
    void setUp() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        resetSurface();
    }

    private static void resetSurface() throws NoSuchFieldException, IllegalAccessException {
        Field instance = Surface.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    // TODO: Need to finish this test for completeness
    @Test
    void testExecuteCommands() {
        Surface surface = Surface.getInstance();
        Controller controller = new Controller(Surface.getInstance(), SampleInputFilePath);
        controller.executeCommands();

        List<Robot> robots = surface.getPlacedRobots();
        List<String> robotPositions = robots.stream().map(Robot::getPosition).collect(Collectors.toList());

        List<String> expectedPositions = Arrays.asList(new String[]{"1 1 E", "3 3 N LOST", "2 3 S"});
        assertEquals(expectedPositions, robotPositions);
    }
}