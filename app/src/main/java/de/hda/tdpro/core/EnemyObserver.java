package de.hda.tdpro.core;

import de.hda.tdpro.core.enemy.Enemy;

/**
 *
 * Observes an Enemy
 */
public interface EnemyObserver {
    /**
     * update on Enemy movement
     * @param e the Enemy which calls the method
     */
    void onEnemyMovement(Enemy e);
}
