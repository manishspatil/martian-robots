package com.manishpatil.martian.robots;

import com.manishpatil.martian.Coordinates;
import com.manishpatil.martian.Direction;
import com.manishpatil.martian.Surface;

public class Robot {

    private Coordinates coordinates, moveOffCoordinates;

    private Direction orientation;

    private Surface surface;

    public void init(Surface surface, Coordinates coordinates, Direction direction) {
        if (this.coordinates != null)
            throw new RuntimeException("Robot does not support re-initialization.");

        if (surface == null)
            throw new RuntimeException("Robot needs surface to stand!");

        if (!surface.isOnGrid(coordinates))
            throw new RuntimeException("Robot cannot initialised on Off-Grid coordinates.");

        if (direction == null)
            throw new RuntimeException("Robot cannot stand without direction.");

        this.surface = surface;
        this.surface.placeRobot(this);
        this.coordinates = coordinates;
        this.orientation = direction;
    }

    public void turnLeft() {
        orientation = orientation.onLeft();
    }

    public void turnRight() {
        orientation = orientation.onRight();
    }

    /**
     * Moves the Robot in forward of the direction if it is on the Surface.
     * <p>
     * If resulting forward command leads to off grid then leaves a
     * scent on grid.
     */
    public void moveForward() {
        if (!surface.isScentPresent(coordinates, orientation)) {
            Coordinates oldCoordinates = coordinates;
            coordinates = orientation.moveForward(oldCoordinates);

            // If Move leads Robot to go off grid then leave scent on grid
            if (!isOnGrid()) {
                moveOffCoordinates = oldCoordinates;
                surface.markScent(oldCoordinates, orientation);
            }
        }
    }

    public boolean isOnGrid() {
        return surface.isOnGrid(coordinates);
    }

    public String getPosition() {
        Coordinates coordinatesToReport = isOnGrid() ? coordinates : moveOffCoordinates;

        StringBuffer reportPosition = new StringBuffer(String.format("%d %d %s", coordinatesToReport.getX(),
                coordinatesToReport.getY(), orientation.toString()));

        if (!isOnGrid()) {
            reportPosition.append(" LOST");
        }

        return reportPosition.toString();
    }
}
