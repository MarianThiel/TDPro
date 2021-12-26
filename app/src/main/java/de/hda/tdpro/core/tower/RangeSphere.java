package de.hda.tdpro.core.tower;

import android.util.Log;

import java.util.Comparator;
import java.util.PriorityQueue;

import de.hda.tdpro.core.Position;
import de.hda.tdpro.core.enemy.Enemy;
import de.hda.tdpro.core.tower.priority.EnemyHPMinComparator;
import de.hda.tdpro.core.tower.priority.Priority;

public class RangeSphere {

    private final int range;

    private final PriorityQueue<Enemy> queue;

    private final Comparator<Enemy> cmp;

    private ShootingBehavior shootingBehavior;

    private final Tower tower;

    public RangeSphere(Tower t, int range) {
        this.range = range;
        cmp = new EnemyHPMinComparator();
        queue = new PriorityQueue<>(cmp);
        this.tower = t;

    }

    public void hitEnemy(int dmg){
        Enemy e = queue.peek();
        e.setHp(e.getHp()-dmg);
        if(e.getHp()<=0){
            queue.poll();
            Log.println(Log.ASSERT,"enemy_targeting", "ENEMY_WAS_DEFEATED");
        }
    }

    public boolean intersects(Position p){
        Position pc = tower.getPos();
        int px = p.getxVal()-pc.getxVal();
        int py = p.getyVal()-pc.getyVal();
        double distance = Math.sqrt((px*px)+(py*py));

        return distance <= range;
    }

    public void targetEnemy(Enemy e){
        this.queue.add(e);
    }

    public int getNumberOfIntersectedEnemies(){
        return queue.size();
    }

    public boolean hasEnemyInside(){
        return !queue.isEmpty();
    }

    public boolean containsEnemy(Enemy e){
        return queue.contains(e);
    }

    public void releaseEnemy(Enemy e){
        queue.remove(e);
    }

    public void setPriority(Priority type) {

    }
}
