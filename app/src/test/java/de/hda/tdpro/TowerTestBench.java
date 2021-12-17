package de.hda.tdpro;
import org.junit.Test;
import static org.junit.Assert.*;

import de.hda.tdpro.core.tower.FireTower;
import de.hda.tdpro.core.tower.Tower;
import de.hda.tdpro.core.tower.TowerManager;
import de.hda.tdpro.core.tower.TowerType;
import de.hda.tdpro.core.tower.UpgradeType;

public class TowerTestBench {

    @Test
    public void placeTowerTest(){
        TowerManager tm = new TowerManager(1);
        tm.placeTower(0, TowerType.FIRE_TOWER);

        assertNotNull(tm.getTower(0));
        assertTrue(tm.getTower(0) instanceof FireTower);
    }

    @Test
    public void upgradeTowerTest(){
        TowerManager tm = new TowerManager(1);
        tm.placeTower(0, TowerType.FIRE_TOWER);
        tm.upgradeTower(0, UpgradeType.DMG_UPGRADE);
        assertEquals(tm.getTower(0).getDamage(),110);
        assertEquals(tm.getTower(0).getLevel(),2);
    }

    @Test
    public void maxLevelTowerTest(){
        TowerManager tm = new TowerManager(1);
        tm.placeTower(0, TowerType.FIRE_TOWER);
        tm.upgradeTower(0, UpgradeType.DMG_UPGRADE);
        tm.upgradeTower(0, UpgradeType.DMG_UPGRADE);
        tm.upgradeTower(0, UpgradeType.DMG_UPGRADE);
        tm.upgradeTower(0, UpgradeType.DMG_UPGRADE);
        assertEquals(tm.getTower(0).getDamage(),140);
        assertEquals(tm.getTower(0).getLevel(),5);
    }
}
