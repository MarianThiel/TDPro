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

    /**
     * method returns a MetaUpgrade by Upgrade Type
     * this is where the default values are defined
     * @param type UpgradeType - type of Upgrade
     * @return the desired MetaUpgrade
     */
    public static MetaUpgrade getMetaUpgrade(UpgradeType type){
        switch (type){
            case L1_DMG_UPGRADE:
                return new MetaUpgrade("DMG_1", 10,0,0,10);
            case L1_RNG_UPGRADE:
                return new MetaUpgrade("RNG_1", 0,0,20,10);
            case L1_VEL_UPGRADE:
                return new MetaUpgrade("VEL_1", 0,0.3f,0,10);
        }
        return new MetaUpgrade("NULL", 0,0,0,0);
    }
}
