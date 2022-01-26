package de.hda.tdpro.core.tower.projectiles;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.Log;

import de.hda.tdpro.core.Position;
import de.hda.tdpro.core.ResourceLoader;
import de.hda.tdpro.core.enemy.Enemy;
import de.hda.tdpro.core.enemy.Vector2D;
import de.hda.tdpro.core.tower.TowerType;

public class DefaultSingleProjectile extends AbstractProjectile{

    private int x;

    private  int y;

    private boolean hit;

    public DefaultSingleProjectile(Position src, Position dst, int dmg, float duration, Enemy[] enemies) {
        super(src, dst, dmg, duration, enemies);
        img = ResourceLoader.getInstance().getProjectile(TowerType.FIRE_TOWER);
        hit = false;
        Vector2D v1 = new Vector2D(src.getxVal(), src.getyVal());
        Vector2D v2 = new Vector2D(src.getxVal(), src.getyVal());
        Vector2D v3 = new Vector2D(dst.getxVal(), dst.getyVal());

        v2.y = v2.y - 300;

        v2 = v2.dif(v1);
        v3 = v3.dif(v1);
        double arc = v2.getArc(v3);

        Log.println(Log.ASSERT,"ARC", "A: " + v1.x + " " + v1.y + " " + v2.x + " " + v2.y + " " + v3.x + " " + v3.y + "ARC=" + arc);

        Matrix m = new Matrix();
        m.postRotate((int)arc);
        img = Bitmap.createBitmap(img,0,0,img.getWidth(),img.getHeight(),m,true);
    }


    @Override
    public void run() {
        Vector2D v1 = new Vector2D(src.getxVal(), src.getyVal());
        Vector2D v2 = new Vector2D(dst.getxVal(), dst.getyVal());

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
        enemies[0].setHp(enemies[0].getHp() - dmg);
    }

    @Override
    public void draw(Canvas canvas) {
        if(!hit){
            Vector2D v1 = new Vector2D(src.getxVal(),src.getyVal());
            Vector2D v2 = new Vector2D(dst.getxVal(), dst.getyVal());
            v2.normalize();

            canvas.drawBitmap(img,x,y,null);
        }

    }
}
