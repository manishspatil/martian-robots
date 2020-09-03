package com.manishpatil.martian;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public abstract class Direction {

    public static Direction North = new Direction() {

        @Override
        public Coordinates moveForward(Coordinates coordinates) {
            return coordinates.increaseY();
        }

        @Override
        public Direction onLeft() {
            return Direction.West;
        }

        @Override
        public Direction onRight() {
            return Direction.East;
        }

        @Override
        public String toString() {
            return "N";
        }
    };

    public static Direction East = new Direction() {

        @Override
        public Coordinates moveForward(Coordinates coordinates) {
            return coordinates.increaseX();
        }

        @Override
        public Direction onLeft() {
            return Direction.North;
        }

        @Override
        public Direction onRight() {
            return Direction.South;
        }

        @Override
        public String toString() {
            return "E";
        }
    };

    public static Direction South = new Direction() {

        @Override
        public Coordinates moveForward(Coordinates coordinates) {
            return coordinates.decreaseY();
        }

        @Override
        public Direction onLeft() {
            return Direction.East;
        }

        @Override
        public Direction onRight() {
            return Direction.West;
        }

        @Override
        public String toString() {
            return "S";
        }
    };

    public static Direction West = new Direction() {

        @Override
        public Coordinates moveForward(Coordinates coordinates) {
            return coordinates.decreaseX();
        }

        @Override
        public Direction onLeft() {
            return Direction.South;
        }

        @Override
        public Direction onRight() {
            return Direction.North;
        }

        @Override
        public String toString() {
            return "W";
        }
    };

    private static HashMap<String, Direction> directions = new HashMap<>();

    static {
        directions.put("N", North);
        directions.put("E", East);
        directions.put("S", South);
        directions.put("W", West);
    }

    // public abstract int index();

    public abstract Coordinates moveForward(Coordinates xy);

    public abstract Direction onLeft();

    public abstract Direction onRight();

    public static List<Direction> allDirections = new LinkedList<Direction>(Arrays.asList(North, East, South, West));

    public static Direction getDirection(String direction) {
        if (direction == null || direction.isEmpty())
            return null;

        return directions.get(direction.toUpperCase());
    }
}
