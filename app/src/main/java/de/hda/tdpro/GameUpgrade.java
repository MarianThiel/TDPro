package de.hda.tdpro;

import de.hda.tdpro.core.persistence.ConfigWriter;

public class GameUpgrade {

    private String key;

    private int costs;

    private int value;

    private float multi;

    private int clevel;

    public GameUpgrade(String key, int costs, int value, float multi, int clevel) {
        this.key = key;
        this.costs = costs;
        this.value = value;
        this.multi = multi;
        this.clevel = clevel;
    }

    public GameUpgrade(String key){
        this.key = key;
    }


    public int getCosts() {
        return costs;
    }

    public int getValue() {
        return value;
    }

    public float getMulti() {
        return multi;
    }

    public String getKey() {
        return key;
    }

    public int getCurrentLevel(){
        return clevel;
    }
    public void readFromConfig(){
        GameUpgrade g = ConfigWriter.getInstance().readGameUpgrade(key);

        costs = g.getCosts();
        value = g.getValue();
        multi = g.getMulti();
        clevel = g.getCurrentLevel();
    }

    public void writeToConfig(){
        ConfigWriter.getInstance().writeGameUpgrade(this);
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setCosts(int costs) {
        this.costs = costs;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setMulti(float multi) {
        this.multi = multi;
    }

    public void setCurrentLevel(int clevel) {
        this.clevel = clevel;
    }
}
