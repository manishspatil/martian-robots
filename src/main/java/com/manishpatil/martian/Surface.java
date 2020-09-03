package com.manishpatil.martian;

import com.manishpatil.martian.robots.Robot;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Surface empowers with methods to set it's size and to check whether a coordinate is On the Grid or not.
 */

public class Surface {

    private static final int MAX_UPPER_RIGHT_X_COORDINATE = 50;
    private static final int MAX_UPPER_RIGHT_Y_COORDINATE = 50;

    private static Surface instance;

    private Coordinates lowerLeftCoordinates = new Coordinates(0, 0);
    private Coordinates upperRightCoordinates;

    private List<Robot> robots = new LinkedList<>();

    private Surface() {
    }

    public static synchronized Surface getInstance() {
        if (instance == null) {
            instance = new Surface();
        }

        return instance;
    }

    public Coordinates getLowerLeftCoordinates() {
        return lowerLeftCoordinates;
    }

    public Coordinates getUpperRightCoordinates() {
        return upperRightCoordinates;
    }

    public void init(int upperRightX, int upperRightY) {
        System.err.println("In Surface.init");
        if (isInitialized()) {
            throw new RuntimeException("Surface does not support re-initialization.");
        }
        setSize(upperRightX, upperRightY);
    }

    public boolean isInitialized() {
        return upperRightCoordinates != null;
    }

    public boolean isOnGrid(Coordinates coordinates) {
        int xPosition = coordinates.getX();
        int yPosition = coordinates.getY();
        return (xPosition >= lowerLeftCoordinates.getX() && xPosition <= upperRightCoordinates.getX())
                && (yPosition >= lowerLeftCoordinates.getY() && yPosition <= upperRightCoordinates.getY());
    }

    public void placeRobot(Robot robot) {
        robots.add(robot);
    }

    public List<Robot> getPlacedRobots() {
        return Collections.unmodifiableList(robots);
    }

    private void setSize(int upperRightX, int upperRightY) {
        if (isSizeValid(upperRightX, upperRightY)) {
            this.upperRightCoordinates = new Coordinates(upperRightX, upperRightY);
        } else {
            throw new RuntimeException("Invalid coordinates provided for initializing Surface's sizing.");
        }
    }

    private boolean isSizeValid(int upperRightX, int upperRightY) {
        return upperRightX > 0 && upperRightX <= MAX_UPPER_RIGHT_X_COORDINATE && upperRightY > 0 && upperRightY <= MAX_UPPER_RIGHT_Y_COORDINATE;
    }
}
