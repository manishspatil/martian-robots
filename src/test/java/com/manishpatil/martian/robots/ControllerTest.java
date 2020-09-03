package com.manishpatil.martian.robots;

import com.manishpatil.martian.Surface;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Field;

class ControllerTest {

    private static String testFilePath = "";

    @BeforeAll
    static void init() {
        File file = new File("src/test/resources/SampleInput.txt");
        testFilePath = file.getAbsolutePath();
    }

    @BeforeEach
    void setUp() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
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
        Controller controller = new Controller(Surface.getInstance(), testFilePath);
        controller.executeCommands();

    }
}