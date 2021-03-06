package de.hda.tdpro;
import org.junit.Test;
import static org.junit.Assert.*;

import de.hda.tdpro.core.path.Position;
import de.hda.tdpro.core.enemy.Enemy;
import de.hda.tdpro.core.tower.FireTower;
import de.hda.tdpro.core.tower.Tower;
import de.hda.tdpro.core.tower.TowerManager;
import de.hda.tdpro.core.tower.TowerType;
import de.hda.tdpro.core.tower.upgrades.MetaUpgrade;

public class TowerTestBench {

    @Test
    public void placeTowerTest(){
        TowerManager tm = new TowerManager(1);
        tm.placeTower(TowerType.FIRE_TOWER, null);

        assertNotNull(tm.getTower(0));
        assertTrue(tm.getTower(0) instanceof FireTower);
    }

    @Test
    public void upgradeTowerTest(){
        TowerManager tm = new TowerManager(1);
        tm.placeTower(TowerType.FIRE_TOWER, null);
        tm.upgradeTower(tm.getTower(0),new MetaUpgrade("test",10,0,0, 100));
        assertEquals(tm.getTower(0).getDamage(),110);
        assertEquals(tm.getTower(0).getLevel(),2);
    }

    @Test
    public void maxLevelTowerTest(){
        TowerManager tm = new TowerManager(1);
        tm.placeTower(TowerType.FIRE_TOWER, null);
        tm.upgradeTower(tm.getTower(0),new MetaUpgrade("test",10,0,0, 100));
        tm.upgradeTower(tm.getTower(0),new MetaUpgrade("test",10,0,0, 100));
        tm.upgradeTower(tm.getTower(0),new MetaUpgrade("test",10,0,0, 100));
        tm.upgradeTower(tm.getTower(0),new MetaUpgrade("test",10,0,0, 100));
        assertEquals(tm.getTower(0).getDamage(),140);
        assertEquals(tm.getTower(0).getLevel(),5);
    }

    @Test
    public void intersectionCenterTest(){
        TowerManager tm = new TowerManager(1);
        tm.placeTower(TowerType.FIRE_TOWER, null);
        Tower t = tm.getTower(0);
        Enemy enemy = new Enemy(1,1,1);
        enemy.setPosition(new Position(10,10));
        t.setPos(new Position(10,10));
        t.onEnemyMovement(enemy);
        assertEquals(1, t.getSphere().getNumberOfIntersectedEnemies());
    }

    @Test
    public void  intersectionFrameTest(){
        TowerManager tm = new TowerManager(1);
        tm.placeTower(TowerType.FIRE_TOWER, null);
        Tower t = tm.getTower(0);
        Enemy enemy = new Enemy(1,1,1);
        enemy.setPosition(new Position(10,10));
        t.setPos(new Position(10,60));
        t.onEnemyMovement(enemy);
        assertEquals(1, t.getSphere().getNumberOfIntersectedEnemies());
    }

    @Test
    public void  intersectionFailTest(){
        TowerManager tm = new TowerManager(1);
        tm.placeTower(TowerType.FIRE_TOWER, null);
        Tower t = tm.getTower(0);
        Enemy enemy = new Enemy(1,1,1);
        enemy.setPosition(new Position(10,10));
        t.setPos(new Position(10,61));
        t.onEnemyMovement(enemy);
        assertEquals(0, t.getSphere().getNumberOfIntersectedEnemies());
    }
}
