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

        if (surface != null && !surface.isOnGrid(coordinates))
            throw new RuntimeException("Robot cannot initialised on Off-Grid coordinates.");

        if (direction == null)
            throw new RuntimeException("Robot cannot stand without direction.");

        this.surface = surface;
        this.surface.placeRobot(this);
        this.coordinates = coordinates;
        this.orientation = direction;
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
