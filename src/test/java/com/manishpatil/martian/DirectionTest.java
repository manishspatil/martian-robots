package com.manishpatil.martian;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DirectionTest {

    Coordinates position;

    @BeforeEach
    void setUp() {
        position = new Coordinates(2, 3);
    }

    @DisplayName("Should Forward for North Direction successfully")
    @Test
    void testNorthForward() {
        Coordinates newPosition = Direction.North.moveForward(position);

        assertTrue(newPosition.getX() == 2 && newPosition.getY() == 4);
    }

    @DisplayName("Should Forward for East Direction successfully")
    @Test
    void testEastForward() {
        Coordinates newPosition = Direction.East.moveForward(position);

        assertTrue(newPosition.getX() == 3 && newPosition.getY() == 3);
    }

    @DisplayName("Should Forward for South Direction successfully")
    @Test
    void testSouthForward() {
        Coordinates newPosition = Direction.South.moveForward(position);

        assertTrue(newPosition.getX() == 2 && newPosition.getY() == 2);
    }

    @DisplayName("Should Forward for West Direction successfully")
    @Test
    void tesWestForward() {
        Coordinates newPosition = Direction.West.moveForward(position);

        assertTrue(newPosition.getX() == 1 && newPosition.getY() == 3);
    }
}