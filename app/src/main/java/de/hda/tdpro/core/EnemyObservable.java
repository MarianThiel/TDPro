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

}
