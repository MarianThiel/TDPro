package de.hda.tdpro.core.tower.shootingbehavior;

import java.util.Queue;

import de.hda.tdpro.core.enemy.Enemy;

public class AreaShooting extends AbstractShootingBehavior{
    @Override
    protected Enemy hit(Queue<Enemy> queue, int dmg) {

        Enemy [] enemies = new Enemy[64];
                enemies = queue.toArray(enemies);

        for (Enemy e : enemies){
            if(e != null)
                e.setHp(e.getHp()-dmg);
        }

        return null;
    }
}
