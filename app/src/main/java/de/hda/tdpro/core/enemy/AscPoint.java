package de.hda.tdpro.core.enemy;

import java.util.ArrayList;
import java.util.List;

import de.hda.tdpro.core.Position;

/**
 * @author Marian Thiel
 * @version 1.0
 *
 * Class representing a Waypoint in a Path
 * It is used to calculate the Positions between two Points
 * is the ascending Point null the end of the path was reached
 */
public class AscPoint {

    private AscPoint nextPoint;

    private int xVal, yVal;

    public AscPoint(int xVal, int yVal) {
        this.xVal = xVal;
        this.yVal = yVal;
    }

    public AscPoint(int xVal, int yVal, AscPoint nextPoint) {
        this.xVal = xVal;
        this.yVal = yVal;
        this.nextPoint = nextPoint;
    }

    public int getX() {
        return xVal;
    }

    public void setX(int xVal) {
        this.xVal = xVal;
    }

    public int getY() {
        return yVal;
    }

    public void setY(int yVal) {
        this.yVal = yVal;
    }

    /**
     * method calculates the path as list of Positions to the next point
     * basic linear algebra - normalized direction vector from v2 - v1 was
     * build and multiplied by a factor
     *
     * @return a list with all the Positions if point is not the last point, an empty list if point is the last one
     */
    List<Position> calculatePositions(){

        List<Position> positions = new ArrayList<>();
        if(nextPoint== null){
            return positions;
        }
        Vector2D v1 = new Vector2D(getX(),getY());
        Vector2D v2 = new Vector2D(nextPoint.getX(), nextPoint.getY());

        Vector2D locVec = v2.dif(v1);
        locVec.normalize();
        int x=0,y=0;
        for(double i = 0; x != v2.x || y != v2.y; i = i + 1){
            Vector2D l = (locVec.mul(i));
            l = l.add(v1);
            x = (int) l.x;
            y = (int) l.y;
            positions.add(new Position(x,y));
        }
        return positions;

    }

    public AscPoint getNextPoint() {
        return nextPoint;
    }

    public void setNextPoint(AscPoint nextPoint) {
        this.nextPoint = nextPoint;
    }
}
