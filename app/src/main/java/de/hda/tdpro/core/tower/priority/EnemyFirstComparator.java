package de.hda.tdpro.core.tower.priority;

import java.util.Comparator;

import de.hda.tdpro.core.enemy.Enemy;

public class EnemyFirstComparator implements Comparator<Enemy> {
    @Override
    public int compare(Enemy o1, Enemy o2) {
        if (o1.getPositionAsIndex() > o2.getPositionAsIndex()) return -1;
        if (o1.getPositionAsIndex() == o2.getPositionAsIndex()) return 0;
        else return 1;
    }
}
