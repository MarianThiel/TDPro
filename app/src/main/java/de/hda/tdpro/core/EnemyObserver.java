package de.hda.tdpro.core;

import de.hda.tdpro.core.enemy.Enemy;

public interface EnemyObserver {

    void onEnemyMovement(Enemy e);
}
