package de.hda.tdpro.core;

import java.util.List;

import de.hda.tdpro.core.tower.upgrades.MetaTower;
import de.hda.tdpro.core.tower.upgrades.MetaUpgrade;

public class GameConfig {

    private List<MetaTower> towers;

    private List<MetaUpgrade> upgrades;

    private int initialAmountOfHealth;

    private int initialAmountOfGold;

    private int amountOfDiamonds;

    private static GameConfig instance;

    private GameConfig() {
        readConfigFile();
    }

    public int getInitialAmountOfHealth() {
        return initialAmountOfHealth;
    }

    public int getInitialAmountOfGold() {
        return initialAmountOfGold;
    }

    public int getAmountOfDiamonds() {
        return amountOfDiamonds;
    }

    public static GameConfig getInstance() {
        if(instance == null)
            instance = new GameConfig();
        return instance;
    }

    private void readConfigFile(){

    }
}
