package de.hda.tdpro.core;

public interface Intersectable {
    boolean intersects(Position position);

    void showBorders(boolean v);
}
