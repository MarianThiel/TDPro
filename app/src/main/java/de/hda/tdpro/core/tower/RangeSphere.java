package de.hda.tdpro.core.tower;

import android.graphics.Canvas;
import android.util.Log;

import java.util.Comparator;
import java.util.PriorityQueue;

import de.hda.tdpro.core.Drawable;
import de.hda.tdpro.core.Position;
import de.hda.tdpro.core.enemy.Enemy;
import de.hda.tdpro.core.tower.priority.EnemyHPMaxComparator;
import de.hda.tdpro.core.tower.priority.EnemyHPMinComparator;
import de.hda.tdpro.core.tower.priority.Priority;

/**
 * @author Marian Thiel
 * @version 1.2
 * RangeSphere is a part of Tower it holds all enemies which are in range and process them by setting the HP
 * if an Enemy has Zero HP or it get out of range it will be removed from the Sphere
 * the Sphere has a priority which enemy has to be processed
 */
public class RangeSphere implements Drawable {
    /**
     * range of the sphere
     */
    private final int range;
    /**
     * PriorityQueue to hold enemies and process them at periodic timestamps
     */
    private final PriorityQueue<Enemy> queue;
    /**
     * Comparator to set the sorting order in PriorityQueue
     */
    private final Comparator<Enemy> cmp;
    /**
     * not used yet
     */
    private ShootingBehavior shootingBehavior;
    /**
     * according tower
     */
    private final Tower tower;
    /**
     * Projectile to draw
     */
    Projectile projectile;

    /**
     * basic constructor
     * @param t according tower
     * @param range range as integer
     */
    public RangeSphere(Tower t, int range) {
        this.range = range;
        cmp = new EnemyHPMinComparator();
        queue = new PriorityQueue<>(cmp);
        this.tower = t;

    }

    /**
     * hits enemy of queue
     * can be extended to hit enemy by a specific ShootingBehavior
     *
     * @param dmg damage to deal
     */
    public void hitEnemy(int dmg){
        Enemy e = queue.peek();
        if(e != null){
            if(!removeDeadEnemy(e)){
                projectile =  new Projectile(tower.pos.getxVal(),tower.pos.getyVal(),e.getPosition().getxVal(),e.getPosition().getyVal(),0,null);
                e.setHp(e.getHp()-dmg);
            }
            if(e!=null)
            removeDeadEnemy(e);
        }

    }

    private boolean removeDeadEnemy(Enemy e){
        if(e.getHp() <= 0){
            queue.poll();
            return true;
        }
        return false;
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

    @Override
    public void draw(Canvas canvas) {
        if(projectile!=null)
        projectile.draw(canvas);
    }
}
