package com.manishpatil.martian;

import java.util.Objects;

class Scent {

    private Coordinates coordinates;

    private Direction orientation;

    Scent(Coordinates coordinates, Direction orientation) {
        this.coordinates = coordinates;
        this.orientation = orientation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scent scent = (Scent) o;
        return Objects.equals(coordinates, scent.coordinates) &&
                Objects.equals(orientation, scent.orientation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinates, orientation);
    }
}
