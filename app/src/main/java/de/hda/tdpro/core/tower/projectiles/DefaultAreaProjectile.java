package de.hda.tdpro.core.tower.projectiles;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.Log;

import java.util.Arrays;

import de.hda.tdpro.R;
import de.hda.tdpro.StaticContext;
import de.hda.tdpro.core.path.Position;
import de.hda.tdpro.core.enemy.Enemy;
import de.hda.tdpro.core.util.Vector2D;
import de.hda.tdpro.core.tower.priority.EnemyLastComparator;

public class DefaultAreaProjectile extends AbstractProjectile{

    private float radius;

    private double maxSize;

    private boolean hit;

    private Shader shader;

    private Bitmap texture;

    public DefaultAreaProjectile(Position src, Position dst, int dmg, float duration, Enemy[] enemies) {
        super(src, dst, dmg, duration, enemies);
        radius = 0;

        texture = BitmapFactory.decodeResource(StaticContext.getContext().getResources(), R.drawable.crack);
        shader = new BitmapShader(texture, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        Arrays.sort(enemies,new EnemyLastComparator());
        this.dst = enemies[enemies.length-1].getPosition();
        Vector2D v1 = new Vector2D(src.getxVal(),src.getyVal());
        Vector2D v2 = new Vector2D(dst.getxVal(),dst.getyVal());

        v2 = v2.dif(v1);
        maxSize = v2.norm();

        hit = false;


    }

    @Override
    public void draw(Canvas canvas) {
        if(!hit){
            Paint p = new Paint();
           p.setShader(shader);
           //p.setColor(Color.GREEN);
            canvas.drawCircle(src.getxVal(),src.getyVal(),radius, p);
        }

    }

    @Override
    public void run() {
        Log.println(Log.ASSERT,"RADIUS", "" + radius + " " + duration);
        while(radius < maxSize){
            Log.println(Log.ASSERT,"RADIUS", "" + radius + " " + duration);
            radius = radius + 2*duration;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for(Enemy e : enemies){
            if(e != null){
                e.setHp(e.getHp() - dmg);
            }
        }
        hit = true;
    }
}
