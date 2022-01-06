package de.hda.tdpro.core;

import de.hda.tdpro.core.tower.Tower;

public interface GameListener {

    void updateOnSelection();

    void updateOnGameOver();

    void updateOnChange();

    void updateOnGameWinning();

    void updateOnTowerPlacement();

}
