package de.hda.tdpro.core.tower.upgrades;

/**
 * MetaUpgrade defines values for simple upgrades
 */
public class MetaUpgrade {

    private final String NAME;

    private final int DMG;

    private final float VEL;

    private final int RAD;

    private final int PRICE;

    public MetaUpgrade(String NAME, int DMG, float VEL, int RAD, int PRICE) {
        this.NAME = NAME;
        this.DMG = DMG;
        this.VEL = VEL;
        this.RAD = RAD;
        this.PRICE = PRICE;
    }

    public String getNAME() {
        return NAME;
    }

    public int getDMG() {
        return DMG;
    }

    public float getVEL() {
        return VEL;
    }

    public int getRAD() {
        return RAD;
    }

    public int getPRICE() {
        return PRICE;
    }
}
