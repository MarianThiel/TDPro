package de.hda.tdpro.core.tower.shootingbehavior;

import java.util.Queue;

import de.hda.tdpro.core.enemy.Enemy;

public class NormalShooting extends AbstractShootingBehavior{


    @Override
    protected Enemy hit(Queue<Enemy> queue, int dmg) {
        Enemy e = queue.peek();
        if (e != null) {
            e.setHp(e.getHp() - dmg);
            if(!e.isAlive())
                queue.poll();
            return e;
        }

        return null;
    }
}
