package de.hda.tdpro.core.enemy;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

import de.hda.tdpro.core.Position;

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
            sum = vStart.add(vEnd);
            Vector2D l = sum.mul(1);
            l.normalize();
            Vector2D term = l.mul(0);
            for(int i = 0; term.compareTo(sum)<=0; i++){
                term = l.mul(i);
                Vector2D t = vStart.add(term);
                lst.add(new Position((int) t.x,(int) t.y));
                //Log.println(Log.ASSERT,"PATH",term.x + "|" + term.y);
            }
        }

        return lst;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint p = new Paint();
        p.setColor(Color.LTGRAY);
        p.setStrokeWidth(80);
        Vector2D sum = new Vector2D(start.getX(), start.getY());
        Vector2D vStart;
        Vector2D vEnd;
        for(AscPoint pt = start; pt.getNextPoint() != null; pt = pt.getNextPoint()){
            vStart = sum;
            vEnd = vStart.add(new Vector2D(pt.getNextPoint().getX(),pt.getNextPoint().getY()));
            sum = vStart.add(vEnd);
            canvas.drawLine((float) vStart.x,(float) vStart.y,(float) vEnd.x,(float) vEnd.y,p);
        }
    }
}
