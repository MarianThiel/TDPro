package de.hda.tdpro.core.enemy;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;

import de.hda.tdpro.core.Drawable;
import de.hda.tdpro.core.Position;

/**
 * @author Marian Thiel
 * @version 1.0
 *
 * Class representing the path of a map or an Enemy
 * works like a linked list
 */
public class Path implements Drawable {
    AscPoint start;

    public Path() {

    }

    /**
     * adds a Point to Path
     * @param x X-Coordinate
     * @param y Y-Coordinate
     */
    public void addPoint(int x, int y){
        if(start == null){
            start = new AscPoint(x,y);

        }else
        for(AscPoint p = start; p != null; p = p.getNextPoint()){
            if(p.getNextPoint()==null){
                p.setNextPoint(new AscPoint(x,y));
                return;
            }
        }
    }

    /**
     * Generates all point of the path by calling calculatePosition on each point
     * @return
     */
    public List<Position> generateAllPositions(){
        List<Position> list = new ArrayList<>();
        for (AscPoint p = start; p!= null; p = p.getNextPoint()){
            list.addAll(p.calculatePositions());
        }
        List<Position> noDupList = new ArrayList<>();
        for(Position p : list){
            if(!noDupList.contains(p)){
                noDupList.add(p);
            }
        }
        return noDupList;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint p = new Paint();
        p.setColor(Color.LTGRAY);
        p.setStrokeWidth(40);

        for(AscPoint pt = start; pt.getNextPoint() != null; pt = pt.getNextPoint()){
            canvas.drawLine(pt.getX(),pt.getY(),pt.getNextPoint().getX(),pt.getNextPoint().getY(),p);
            canvas.drawCircle(pt.getNextPoint().getX(),pt.getNextPoint().getY(),20,p);
        }
    }
}
