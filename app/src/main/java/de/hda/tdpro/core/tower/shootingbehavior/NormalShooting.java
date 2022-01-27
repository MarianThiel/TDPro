package de.hda.tdpro.core.tower.shootingbehavior;

import java.util.Queue;

import de.hda.tdpro.core.enemy.Enemy;

public class NormalShooting extends AbstractShootingBehavior{


    @Override
    protected Enemy[] hit(Queue<Enemy> queue) {
        Enemy e = queue.peek();
        Enemy[] enemies = new Enemy[1];
        if (e != null) {
            enemies[0] = e;
            return enemies;
        }

        return null;
    }
}
