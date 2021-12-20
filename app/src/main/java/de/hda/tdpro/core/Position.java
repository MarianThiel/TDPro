package de.hda.tdpro.core;

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
}
