package de.hda.tdpro.core.tower;

import java.util.Comparator;

import de.hda.tdpro.core.enemy.Enemy;

public class EnemyHPComparator implements Comparator<Enemy> {
    @Override
    public int compare(Enemy o1, Enemy o2) {
        if(o1.getHp() < o2.getHp()){
            return -1;
        }
        if(o1.getHp() == o2.getHp()){
            return 0;
        }else{
            return 1;
        }
    }
}
