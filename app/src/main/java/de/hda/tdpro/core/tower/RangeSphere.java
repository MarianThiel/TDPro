package de.hda.tdpro.core.tower;

import java.util.Comparator;
import java.util.PriorityQueue;

import de.hda.tdpro.core.Position;
import de.hda.tdpro.core.enemy.Enemy;

public class RangeSphere {

    private final int range;

    private final PriorityQueue<Enemy> queue;

    private final Comparator<Enemy> cmp;

    private ShootingBehavior shootingBehavior;

    private final Tower tower;

    public RangeSphere(Tower t, int range) {
        this.range = range;
        cmp = new EnemyHPComparator();
        queue = new PriorityQueue<>(cmp);
        this.tower = t;
    }

    public void hitEnemy(int dmg){

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

}
