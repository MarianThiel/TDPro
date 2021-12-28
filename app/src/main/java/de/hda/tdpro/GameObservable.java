package de.hda.tdpro;

import de.hda.tdpro.core.GameListener;

public interface GameObservable {

    void addGameListener(GameListener listener);

    void notifyOnSelection();
}
