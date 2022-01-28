package de.hda.tdpro.core.tower;

import android.graphics.Canvas;

import java.util.Arrays;


import de.hda.tdpro.core.Drawable;
import de.hda.tdpro.core.Game;
import de.hda.tdpro.core.Intersectable;
import de.hda.tdpro.core.Position;
import de.hda.tdpro.core.TimingUnit;
import de.hda.tdpro.core.enemy.Enemy;
import de.hda.tdpro.core.factories.TowerFactory;
import de.hda.tdpro.core.tower.upgrades.MetaUpgrade;
import de.hda.tdpro.core.tower.upgrades.SimpleUpgrade;
import de.hda.tdpro.core.tower.upgrades.TowerDecorator;

public class TowerManager implements Drawable, TimingUnit, Intersectable {

    private final int MAX_TOWER_CAPACITY;

    private final Tower[] towers;

    private int lastIndexInserted;




    public TowerManager(int MAX_TOWER_NUMBER) {
        this.MAX_TOWER_CAPACITY = MAX_TOWER_NUMBER;
        towers = new Tower[MAX_TOWER_NUMBER];
        lastIndexInserted = 0;
        Arrays.fill(towers, null);

    }

    private int getIndex(Tower tower){
        for(int i = 0; i < MAX_TOWER_CAPACITY; i++){
            if(towers[i].equals(tower)){
                return i;
            }
        }
        return -1;
    }
    public boolean upgradeTower(Tower t, MetaUpgrade upgrade){

        int i = getIndex(t);
        if(upgradePossible(i)){

            towers[i].setActive(false);
            towers[i] = new SimpleUpgrade(towers[i],upgrade);

            return true;
        }

        return false;
    }

    public Tower placeTower(TowerType type, Position position){
                if(lastIndexInserted < MAX_TOWER_CAPACITY){
                    switch (type){
                        case FIRE_TOWER:
                            towers[lastIndexInserted] = TowerFactory.getInstance().createFireTower();
                            break;
                        case EARTH_TOWER:
                            towers[lastIndexInserted] = TowerFactory.getInstance().createEarthTower();
                            break;
                    }
                    towers[lastIndexInserted].setPos(position);
                    towers[lastIndexInserted].startAiming();
                    Tower t = towers[lastIndexInserted];
                    ++lastIndexInserted;
                    return t;
                }else
                return null;
    }

    public Tower getTower(int i){
        if(i >= 0 && i < towers.length){
            return towers[i];
        }
        return null;
    }
    public Tower getTowerByPosition(int x, int y){
        for (Tower  t : towers) {
            if (t != null) {
                if (t.intersects(new Position(x, y))) {
                    return t;
                }
            }
        }
        return null;
    }

    private boolean upgradePossible(int i){
        return towers[i].getLevel()<towers[i].getMaxLevel();
    }

    public void addTowerAsListener(Enemy e){
        for(int i = 0; i < MAX_TOWER_CAPACITY; i++){
            if(towers[i]!=null)
            e.addEnemyObserver(towers[i]);
        }
    }

    public void deselectAllTowers(){
        for (Tower t : towers){
            if(t!=null)
                t.setActive(false);
        }
    }


    @Override
    public void draw(Canvas canvas) {
        for (int i = 0; i < MAX_TOWER_CAPACITY; i++){
            if(towers[i] != null){
                towers[i].draw(canvas);
            }
        }
    }

    @Override
    public void speedUp() {

        for(Tower t : towers){
            if(t != null){
                t.getSphere().setSpeedFactor(Game.FF_FACTOR);
            }
        }

    }

    @Override
    public void slowDown() {


        for(Tower t : towers){
            if(t != null){
               t.getSphere().setSpeedFactor(1);
            }
        }
    }

    @Override
    public void pause(){
        for(Tower t : towers){
            if(t!=null){
                t.stopAiming();
            }
        }
    }
    @Override
    public void resume(){
        for(Tower t: towers){
            if (t != null){
                t.startAiming();
            }
        }
    }

    @Override
    public boolean intersects(Position position) {
        for(Tower t : towers){
            if(t!=null){
                if(t.intersects(position)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void showBorders(boolean v) {

    }
}
