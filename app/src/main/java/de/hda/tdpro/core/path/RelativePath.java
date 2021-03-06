package de.hda.tdpro.core.path;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;

import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import de.hda.tdpro.R;
import de.hda.tdpro.StaticContext;
import de.hda.tdpro.core.util.GaussMatrix;
import de.hda.tdpro.core.util.Vector2D;

/**
 * @author Marian Thiel
 * A RelativePath differs from a regular Path  in generating the result positions
 * The relative aspect of the path is that points are relative to each other
 * e.g Point (0,50) -> (50,0) means: first 50 px on y-axis (P1) then 50px on x-axis (P2) results in a endpoint of (50,50)
 */
public class RelativePath extends Path {

    private Bitmap texture;

    private Shader shader;

    private final int PATH_WIDTH = 100;



    public RelativePath() {

        texture = BitmapFactory.decodeResource(StaticContext.getContext().getResources(), R.drawable.mud);
        shader = new BitmapShader(texture, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
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
        p.setShader(shader);
        //p.setColor(Color.parseColor("#4d320c"));
        p.setStrokeWidth(PATH_WIDTH);
        Vector2D sum = new Vector2D(start.getX(), start.getY());
        Vector2D vStart;
        Vector2D vEnd;
        for(AscPoint pt = start; pt.getNextPoint() != null; pt = pt.getNextPoint()){
            vStart = sum;
            vEnd = vStart.add(new Vector2D(pt.getNextPoint().getX(),pt.getNextPoint().getY()));
            sum = vEnd;
            canvas.drawLine((float) vStart.x,(float) vStart.y,(float) vEnd.x,(float) vEnd.y,p);
            canvas.drawCircle((float) vEnd.x,(float) vEnd.y,PATH_WIDTH/2,p);

        }

    }

    @Override
    public boolean intersects(Position position) {
        Vector2D sum = new Vector2D(start.getX(), start.getY());
        Vector2D vStart;
        Vector2D vEnd;
        Vector2D p = new Vector2D(position.getxVal(), position.getyVal());
        for(AscPoint pt = start; pt.getNextPoint() != null; pt = pt.getNextPoint()){
            vStart = sum;
            vEnd = vStart.add(new Vector2D(pt.getNextPoint().getX(),pt.getNextPoint().getY()));
            sum = vEnd;

            //s,e are final points


            Vector2D v1 = vEnd.dif(vStart);
            Vector2D u1 = v1.getUnitNormalized();
            Vector2D u2 = v1.getUnitNormalized().mul(-1);

            Vector2D pA = u2.mul((PATH_WIDTH/2)).add(vStart);
            Vector2D pB = u1.mul((PATH_WIDTH/2)).add(vStart);
            Vector2D pC = u2.mul((PATH_WIDTH/2)).add(vEnd);


            Vector2D vP = new Vector2D(position);

            Vector2D vAB = pB.dif(pA);
            Vector2D vAC = pC.dif(pA);

            GaussMatrix matrix = new GaussMatrix(pA,vAB,vAC,vP);
            Log.println(Log.ASSERT,"X1", " " + matrix.getX1());
            Log.println(Log.ASSERT,"X2", " " + matrix.getX2());
            double x1 = matrix.getX1();
            double x2 = matrix.getX2();

            if(x1 >= 0 && x1 <=1 && x2 >= 0 && x2 <= 1){
                return true;
            }

        }
        return false;
    }

    @Override
    public void showBorders(boolean v) {

    }
}
