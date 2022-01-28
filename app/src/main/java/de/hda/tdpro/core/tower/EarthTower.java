package de.hda.tdpro.core.tower;

import android.graphics.Canvas;

import de.hda.tdpro.core.util.ResourceLoader;
import de.hda.tdpro.core.enemy.Enemy;
import de.hda.tdpro.core.tower.projectiles.DefaultAreaProjectile;
import de.hda.tdpro.core.tower.shootingbehavior.AreaShooting;

public class EarthTower extends Tower{

    public EarthTower(int radius, int damage, float speed, int price, int maxLevel) {
        super(radius, damage, speed, price, maxLevel);
        this.sphere = new RangeSphere(this, new AreaShooting());
        img = ResourceLoader.getInstance().getTowerImages(TowerType.EARTH_TOWER);
        current = img[0];
    }

    @Override
    public void fire(Enemy[] enemies, int damage, float vel) {
        if(enemies != null && enemies.length>0){
            //rotateTower(enemies[0].getPosition());

            //p = new Projectile(getPos().getxVal(),getPos().getyVal(),enemies[0],getDamage(), (int) getSpeed(),StaticContext.getContext());

            p = new DefaultAreaProjectile(getPos(),enemies[enemies.length-1].getPosition(),damage,vel,enemies);


        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawBitmap(current, getPos().getxVal()-(img[0].getWidth()/2),getPos().getyVal()-(img[0].getHeight()/2),null);
    }
}
