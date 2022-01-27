package de.hda.tdpro.core.tower.shootingbehavior;

import java.util.Queue;

import de.hda.tdpro.core.enemy.Enemy;

public class AreaShooting extends AbstractShootingBehavior{
    @Override
    protected Enemy[] hit(Queue<Enemy> queue) {

        Enemy [] enemies = new Enemy[queue.size()];
                enemies = queue.toArray(enemies);

       return enemies;
    }
}
