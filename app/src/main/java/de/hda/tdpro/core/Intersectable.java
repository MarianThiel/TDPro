package de.hda.tdpro.core;

import de.hda.tdpro.core.path.Position;

public interface Intersectable {
    boolean intersects(Position position);

    void showBorders(boolean v);
}
