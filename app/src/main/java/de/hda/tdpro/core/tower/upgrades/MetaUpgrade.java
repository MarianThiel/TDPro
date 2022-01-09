package de.hda.tdpro.core.tower.upgrades;

import de.hda.tdpro.core.tower.UpgradeType;

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

    public static MetaUpgrade getMetaUpgrade(UpgradeType type){
        switch (type){
            case L1_DMG_UPGRADE:
                return new MetaUpgrade("DMG_LVL_1", 10,0,0,0);
            case L1_RANGE_UPGRADE:
                return new MetaUpgrade("NULL", 0,0,0,0);
        }
        return new MetaUpgrade("NULL", 0,0,0,0);
    }
}
