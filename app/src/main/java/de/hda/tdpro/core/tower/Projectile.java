package de.hda.tdpro.core.tower;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.Log;

import de.hda.tdpro.core.Drawable;
import de.hda.tdpro.core.util.ResourceLoader;
import de.hda.tdpro.core.enemy.Enemy;
import de.hda.tdpro.core.util.Vector2D;

/**
 * graphical representation of a projectile
 */
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

    private Bitmap img;

    private int dmg;

    private Enemy enemy;
    public Projectile(int xStart, int yStart, Enemy enemy,int dmg, int duration, Context context){
        this.xStart = xStart;
        this.yStart = yStart;
        this.enemy = enemy;
        this.xEnd = enemy.getPosition().getxVal();
        this.yEnd = enemy.getPosition().getyVal();
        this.duration = duration;
        this.dmg = dmg;

        x = xStart;
        y = yStart;
        v = new Vector2D(xEnd,yEnd);
        v.normalize();

        img = ResourceLoader.getInstance().getProjectile(TowerType.FIRE_TOWER);

        Vector2D v1 = new Vector2D(xStart,yStart);
        Vector2D v2 = new Vector2D(xStart,yStart);
        Vector2D v3 = new Vector2D(xEnd,yEnd);

        v2.y = v2.y - 300;

        v2 = v2.dif(v1);
        v3 = v3.dif(v1);
        double arc = v2.getArc(v3);

        Log.println(Log.ASSERT,"ARC", "A: " + v1.x + " " + v1.y + " " + v2.x + " " + v2.y + " " + v3.x + " " + v3.y + "ARC=" + arc);

        Matrix m = new Matrix();
        m.postRotate((int)arc);
        img = Bitmap.createBitmap(img,0,0,img.getWidth(),img.getHeight(),m,true);
        t = new Thread(this);
        t.start();
    }

    @Override
    public void draw(Canvas canvas) {
        if(!hit){
            Vector2D v1 = new Vector2D(xStart,yStart);
            Vector2D v2 = new Vector2D(xEnd, yEnd);
            v2.normalize();

            canvas.drawBitmap(img,x,y,null);
            //Paint p = new Paint();
            //p.setStrokeWidth(5);
            //p.setColor(Color.RED);
            //canvas.drawCircle(x,y,8,p);
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
        for(double i = 0; v3.compareTo(v2.dif(v1)) < 0 ; i = i + duration){ // terminates if norm of v3 >= norm of v1
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
        enemy.setHp(enemy.getHp() - dmg);
    }
}
