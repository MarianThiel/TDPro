package de.hda.tdpro.core.tower;

import android.graphics.Canvas;

import de.hda.tdpro.core.util.ResourceLoader;
import de.hda.tdpro.core.enemy.Enemy;
import de.hda.tdpro.core.tower.projectiles.DefaultSingleProjectile;
import de.hda.tdpro.core.tower.shootingbehavior.NormalShooting;

/**
 * @author Marian Thiel
 * @version 1.1
 * special tower overrides draw method to draw different tower image
 * inits the sphere
 */
public class FireTower extends Tower {
    public FireTower(int radius, int damage, float speed, int price, int maxLevel) {
        super(radius, damage, speed, price, maxLevel);
        this.sphere = new RangeSphere(this, new NormalShooting());
        img = ResourceLoader.getInstance().getTowerImages(TowerType.FIRE_TOWER);
        current = img[0];
        rotatable = true;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawBitmap(current, getPos().getxVal()-(img[0].getWidth()/2),getPos().getyVal()-(img[0].getHeight()/2),null);

    }

    @Override
    public void fire(Enemy[] enemies, int dmg, float vel) {
        if(enemies != null && enemies.length>0){
            //rotateTower(enemies[0].getPosition());

            //p = new Projectile(getPos().getxVal(),getPos().getyVal(),enemies[0],getDamage(), (int) getSpeed(),StaticContext.getContext());

            p = new DefaultSingleProjectile(getPos(),enemies[enemies.length-1].getPosition(),dmg,(int)vel,enemies);


        }

    }
}
