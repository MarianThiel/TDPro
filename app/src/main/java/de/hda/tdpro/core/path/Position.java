package de.hda.tdpro.core.path;

import java.util.Objects;

/**
 * Position on the Screen in pixels
 */
public class Position {
    private int xVal;
    private int yVal;


    public Position(int xVal, int yVal) {
        this.xVal = xVal;
        this.yVal = yVal;
    }

    public int getxVal() {
        return xVal;
    }

    public void setxVal(int xVal) {
        this.xVal = xVal;
    }

    public int getyVal() {
        return yVal;
    }

    public void setyVal(int yVal) {
        this.yVal = yVal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return xVal == position.xVal && yVal == position.yVal;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xVal, yVal);
    }
}
