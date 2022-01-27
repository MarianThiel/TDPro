package de.hda.tdpro.core.tower.projectiles;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import de.hda.tdpro.core.Drawable;
import de.hda.tdpro.core.Position;
import de.hda.tdpro.core.enemy.Enemy;

public abstract class AbstractProjectile implements Runnable, Drawable {

    protected Position src;

    protected Position dst;

    protected int dmg;

    protected Enemy[] enemies;

    protected Bitmap img;

    protected float duration;

    protected Thread thread;

    public AbstractProjectile(Position src, Position dst, int dmg, float duration, Enemy[] enemies){
        this.src = src;
        this.dst = dst;
        this.dmg = dmg;
        this.enemies = enemies;
        this.duration = duration;
        thread = new Thread(this);
        thread.start();
    }





}
