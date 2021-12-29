package de.hda.tdpro.core.tower;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import de.hda.tdpro.core.Drawable;
import de.hda.tdpro.core.Position;
import de.hda.tdpro.core.enemy.Vector2D;

public class Projectile implements Drawable, Runnable {

    Thread t;
    public int xStart;
    public int yStart;

    public int x, y;

    public int xEnd;
    public int yEnd;

    Vector2D v;

    public int duration;

    public boolean hit = false;

    public Projectile(int xStart, int yStart, int xEnd, int yEnd, int duration, Context context){
        this.xStart = xStart;
        this.yStart = yStart;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
        this.duration = duration;
        x = xStart;
        y = yStart;
        v = new Vector2D(xEnd,yEnd);
        v.normalize();

        t = new Thread(this);
        t.start();
    }

    @Override
    public void draw(Canvas canvas) {
        if(!hit){
            Vector2D v1 = new Vector2D(xStart,yStart);
            Vector2D v2 = new Vector2D(xEnd, yEnd);
            v2.normalize();

            Paint p = new Paint();
            p.setStrokeWidth(5);
            canvas.drawCircle(x,y,8,p);
        }


    }

    @Override
    public void run() {
        Vector2D v1 = new Vector2D(xStart,yStart);
        Vector2D v2 = new Vector2D(xEnd, yEnd);

        Vector2D locVec = v2.dif(v1);
        locVec.normalize();

        Vector2D v3 = locVec.mul(0);
        Vector2D l;
        for(double i = 0; v3.compareTo(v2.dif(v1)) < 0 ; i = i + 4){
            l = (locVec.mul(i));
            v3 = (locVec.mul(i));
            l = l.add(v1);
            x = (int) l.x;
            y = (int) l.y;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        hit = true;
    }
}
