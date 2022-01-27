package de.hda.tdpro.core;

/**
 * let Enemies be an observable
 *
 */
public interface EnemyObservable {

    void addEnemyObserver(EnemyObserver o);

    void removeEnemyObserver(EnemyObserver o);

    /**
     * notifies Observers when Observable is moving
     */
    void notifyOnMovement();

    /**
     * notifies observers when enemy died
     */
    void notifyEnemyDying();

    /**
     * notifies observers when enemy succeeded - means reached the endpoint
     */
    void notifyEnemySuccess();

}
