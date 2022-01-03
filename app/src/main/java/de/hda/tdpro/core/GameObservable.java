package de.hda.tdpro.core;

import de.hda.tdpro.core.GameListener;

/**
 * Observer patter
 * Role: ObservableSubject
 * Notifies Observers on event
 */
public interface GameObservable {
    /**
     * Adds an Observer to the List
     * @param listener the observer
     */
    void addGameListener(GameListener listener);

    /**
     * notifies Observer when tower was selected
     */
    void notifyOnSelection();

    void notifyOnGameOver();
}
