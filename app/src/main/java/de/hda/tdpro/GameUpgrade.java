package de.hda.tdpro;

public class GameUpgrade {

    private String key;

    private int costs;

    private int value;

    private float multi;


    public GameUpgrade(String key, int costs, int value, float multi) {
        this.key = key;
        this.costs = costs;
        this.value = value;
        this.multi = multi;
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

    public void readFromConfig(){
        GameUpgrade g = ConfigWriter.getInstance().readGameUpgrade(key);

        costs = g.getCosts();
        value = g.getValue();
        multi = g.getMulti();

    }

    public void writeToConfig(){
        ConfigWriter.getInstance().writeGameUpgrade(this);
    }

}
