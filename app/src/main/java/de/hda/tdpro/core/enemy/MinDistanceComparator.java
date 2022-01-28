package de.hda.tdpro.core.enemy;

import java.util.Comparator;

import de.hda.tdpro.core.Position;

public class MinDistanceComparator implements Comparator<Enemy> {

    private Position src;

    public MinDistanceComparator(Position position) {
        src = position;
    }

    @Override
    public int compare(Enemy o1, Enemy o2) {
        float d1 = getDistance(src,o1.getPosition());
        float d2 = getDistance(src,o2.getPosition());

        return -1*Float.compare(d1, d2);
    }

    private float getDistance(Position p1, Position p2){
        Vector2D v1 = new Vector2D(p1.getxVal(),p1.getyVal());
        Vector2D v2 = new Vector2D(p2.getxVal(),p2.getyVal());
        v2 = v2.dif(v1);
        return (float)v2.norm();
    }
}
