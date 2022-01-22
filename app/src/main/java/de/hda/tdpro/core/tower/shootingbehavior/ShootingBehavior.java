package de.hda.tdpro.core.tower.shootingbehavior;

import java.util.Queue;

import de.hda.tdpro.core.enemy.Enemy;

public interface ShootingBehavior {

    void shoot(Queue<Enemy> queue , int dmg);
}
