package de.hda.tdpro.core.enemy;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import de.hda.tdpro.core.Position;

public class RelativePoint extends AscPoint{
    public RelativePoint(int xVal, int yVal) {
        super(xVal, yVal);
    }

    @Override
    List<Position> calculatePositions() {
        List<Position> l = new LinkedList<>();
        if(getNextPoint() != null){
            Vector2D v1 = new Vector2D(getX(),getY());
            Vector2D v2 = new Vector2D(getNextPoint().getX(),getNextPoint().getY());

            Vector2D target = v1.add(v2);
            Vector2D norm = target;
            norm.normalize();
            Vector2D variant = norm.mul(0);
            for(double i = 0; variant.compareTo(target) <= 0; i++){
                variant = norm.mul(i);
                Vector2D v = variant.add(v1);
                l.add(new Position((int) v.x,(int) v.y));
            }
        }
        return l;


    }
}
