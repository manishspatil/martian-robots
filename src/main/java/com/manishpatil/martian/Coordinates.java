package com.manishpatil.martian;

import java.util.Objects;

public class Coordinates {
    private int xCoordinate, yCoordinate;

    public Coordinates(int x, int y) {
        xCoordinate = x;
        yCoordinate = y;
    }

    public Coordinates(Coordinates coordinates) {
        xCoordinate = coordinates.xCoordinate;
        yCoordinate = coordinates.yCoordinate;
    }

    public int getX() {
        return xCoordinate;
    }

    public int getY() {
        return yCoordinate;
    }

    public Coordinates increaseX() {
        Coordinates newCoordinates = new Coordinates(this);
        newCoordinates.xCoordinate++;
        return newCoordinates;
    }

    public Coordinates increaseY() {
        Coordinates newCoordinates = new Coordinates(this);
        newCoordinates.yCoordinate++;
        return newCoordinates;
    }

    public Coordinates decreaseX() {
        Coordinates newCoordinates = new Coordinates(this);
        newCoordinates.xCoordinate--;
        return newCoordinates;
    }

    public Coordinates decreaseY() {
        Coordinates newCoordinates = new Coordinates(this);
        newCoordinates.yCoordinate--;
        return newCoordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return xCoordinate == that.xCoordinate &&
                yCoordinate == that.yCoordinate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xCoordinate, yCoordinate);
    }

    @Override
    public String toString() {
        return String.format("%d, %d", xCoordinate, yCoordinate);
    }
}
