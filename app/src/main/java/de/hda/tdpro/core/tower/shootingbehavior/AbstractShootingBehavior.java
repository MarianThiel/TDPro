package de.hda.tdpro.core.tower.shootingbehavior;

import java.util.Queue;

import de.hda.tdpro.core.enemy.Enemy;

public abstract class AbstractShootingBehavior implements ShootingBehavior{


    @Override
    public Enemy[] shoot(Queue<Enemy> queue, int dmg) {
        while (removeDeadEnemy(queue));
        return hit(queue);
    }

    private boolean removeDeadEnemy(Queue<Enemy> queue){
        Enemy e = queue.peek();
        if(e != null && !e.isAlive()){
            queue.poll();
            return true;
        }
        return false;
    }

    protected abstract Enemy[] hit(Queue<Enemy> queue);


}
