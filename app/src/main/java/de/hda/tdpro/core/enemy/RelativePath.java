package de.hda.tdpro.core.enemy;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import de.hda.tdpro.core.Position;

/**
 * @author Marian Thiel
 * A RelativePath differs from a regular Path  in generating the result positions
 * The relative aspect of the path is that points are relative to each other
 * e.g Point (0,50) -> (50,0) means: first 50 px on y-axis (P1) then 50px on x-axis (P2) results in a endpoint of (50,50)
 */
public class RelativePath extends Path{

    public RelativePath() {
    }

    @Override
    public List<Position> generateAllPositions() {
        Vector2D sum = new Vector2D(start.getX(), start.getY());
        Vector2D vStart;
        Vector2D vEnd;
        List<Position> lst = new LinkedList<>();
        for (AscPoint p = start; p.getNextPoint() != null; p = p.getNextPoint()){
            vStart = sum;
            vEnd = vStart.add(new Vector2D(p.getNextPoint().getX(),p.getNextPoint().getY()));

            Vector2D l = vEnd.dif(vStart);
            l.normalize();
            Vector2D term = l.mul(0);
            for(int i = 0; term.compareTo(vStart.dif(vEnd))<=0; i = i+4){ //each 4th waypoint
                term = l.mul(i);
                Vector2D t = vStart.add(term);
                lst.add(new Position((int) t.x,(int) t.y));

            }
            sum = vEnd;
        }
        List<Position> noDupList = new ArrayList<>();
        for(Position p : lst){
            if(!noDupList.contains(p)){
                noDupList.add(p);
            }
        }
        return noDupList;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint p = new Paint();
        p.setColor(Color.parseColor("#4d320c"));
        p.setStrokeWidth(100);
        Vector2D sum = new Vector2D(start.getX(), start.getY());
        Vector2D vStart;
        Vector2D vEnd;
        for(AscPoint pt = start; pt.getNextPoint() != null; pt = pt.getNextPoint()){
            vStart = sum;
            vEnd = vStart.add(new Vector2D(pt.getNextPoint().getX(),pt.getNextPoint().getY()));
            sum = vEnd;
            canvas.drawLine((float) vStart.x,(float) vStart.y,(float) vEnd.x,(float) vEnd.y,p);
            canvas.drawCircle((float) vEnd.x,(float) vEnd.y,50,p);
        }
    }
}
