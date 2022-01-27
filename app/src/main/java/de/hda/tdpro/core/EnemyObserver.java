package de.hda.tdpro.core;

import de.hda.tdpro.core.enemy.Enemy;

/**
 *
 * Observes an Enemy
 */
public interface EnemyObserver {
    /**
     * update on Enemy movement
     * @param e observed enemy
     */
    void onEnemyMovement(Enemy e);

    /**
     * update on Enemy success
     * @param e observed enemy
     */
    void onEnemySuccess(Enemy e);

    /**
     * update on Enemy dying
     * @param e
     */
    void onEnemyDying(Enemy e);
}
