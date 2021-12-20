package de.hda.tdpro.core;

public interface IntersectionObservable {

    void addEnemyObserver(EnemyObserver o);

    void removeEnemyObserver(EnemyObserver o);

    void notifyObservers();

}
