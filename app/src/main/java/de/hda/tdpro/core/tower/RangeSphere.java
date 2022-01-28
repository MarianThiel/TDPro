package de.hda.tdpro.core.tower;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Comparator;
import java.util.PriorityQueue;

import de.hda.tdpro.core.Drawable;
import de.hda.tdpro.core.path.Position;
import de.hda.tdpro.core.enemy.Enemy;
import de.hda.tdpro.core.tower.priority.EnemyFirstComparator;
import de.hda.tdpro.core.tower.priority.EnemyHPMaxComparator;
import de.hda.tdpro.core.tower.priority.EnemyHPMinComparator;
import de.hda.tdpro.core.tower.priority.EnemyLastComparator;
import de.hda.tdpro.core.tower.priority.Priority;
import de.hda.tdpro.core.tower.shootingbehavior.ShootingBehavior;

/**
 * @author Marian Thiel
 * @version 1.3
 * RangeSphere is a part of Tower it holds all enemies which are in range and process them by setting the HP
 * if an Enemy has Zero HP or it get out of range it will be removed from the Sphere
 * the Sphere has a priority which enemy has to be processed
 */
public class RangeSphere implements Drawable, Runnable {

    /**
     * PriorityQueue to hold enemies and process them at periodic timestamps
     */
    private PriorityQueue<Enemy> queue;
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
    private Tower tower;
    /**
     * Projectile to draw
     */
    Projectile projectile;

    private boolean aiming;

    private Thread aimThread;

    private boolean queueBlock;

    private Priority priority;

    private float speedFactor;

    /**
     * basic constructor
     * @param t according tower
     *
     */
    public RangeSphere(Tower t, ShootingBehavior shootingBehavior) {
        cmp = new EnemyFirstComparator();
        queue = new PriorityQueue<>(cmp);
        this.tower = t;
        queueBlock = false;
        priority = Priority.FIRST;
        this.shootingBehavior = shootingBehavior;
        speedFactor = 1f;
        

    }

    /**
     * hits enemy of queue
     * can be extended to hit enemy by a specific ShootingBehavior
     *
     *
     */
    public void hitEnemy(){
        /*
        Enemy e = queue.peek();

        while(removeDeadEnemy(e)){
            e = queue.peek();
        }
        if(e!=null){
            tower.rotateTower(e.getEstimatedPosition(tower.getSpeed()));
            fireProjectile(e);
            e.setHp(e.getHp()-dmg);
        }



        removeDeadEnemy(e);

         */
        Enemy[] e =shootingBehavior.shoot(queue,tower.getDamage());
        tower.fire(e,tower.getDamage(),tower.getSpeed());
        if(tower.isRotatable() && e != null){
            tower.rotateTower(e[0].getPosition());
        }
    }



    public boolean intersects(Position p){
        Position pc = tower.getPos();
        int px = p.getxVal()-pc.getxVal();
        int py = p.getyVal()-pc.getyVal();
        double distance = Math.sqrt((px*px)+(py*py));

        return distance <= tower.getRadius();
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
        priority = type;
        switch (type){
            case MAX_HP:
                executePrioritySwap(new EnemyHPMaxComparator());
                break;
            case MIN_HP:
                executePrioritySwap(new EnemyHPMinComparator());
                break;
            case FIRST:
                executePrioritySwap(new EnemyFirstComparator());
                break;
            case LAST:
                executePrioritySwap(new EnemyLastComparator());
                break;
        }
    }

    public Priority getPriority(){
        return priority;
    }


    private void executePrioritySwap(Comparator<Enemy> cmp){
        setQueueBlock(true);
        queue.clear();
        queue = new PriorityQueue<>(cmp);
        setQueueBlock(false);
    }

    public Tower getTower() {
        return tower;
    }

    public void setTower(Tower tower) {
        this.tower = tower;
    }

    private void setQueueBlock(boolean v){
        queueBlock = v;
    }

    @Override
    public void draw(Canvas canvas) {
        if(projectile!=null)
            projectile.draw(canvas);
        if(tower.isActive()){
            Paint p = new Paint();
            p.setStyle(Paint.Style.FILL_AND_STROKE);
            p.setColor(Color.parseColor("#8fe9ff"));
            p.setStrokeWidth(10);
            p.setAlpha(80);
            canvas.drawCircle(tower.getPos().getxVal(),tower.getPos().getyVal(),tower.getRadius(),p);
        }
    }

    @Override
    public void run() {
        while(aiming){
            if(!queueBlock){
                if(hasEnemyInside()){

                    hitEnemy();
                }
                try {
                    Thread.sleep ((long) (1000/(tower.getSpeed() * speedFactor)));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void startAiming(){
        if(!aiming){
            aiming = true;
            aimThread = new Thread(this);
            aimThread.start();
        }

    }
    public void stopAiming(){
        if(aiming){
            aiming = false;
            aimThread.interrupt();
            try {
                aimThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setSpeedFactor(float f){
        speedFactor = f;
    }
}
