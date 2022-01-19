package de.hda.tdpro.core.factories;

import android.graphics.Bitmap;

import de.hda.tdpro.core.ResourceLoader;
import de.hda.tdpro.core.enemy.Enemy;
import de.hda.tdpro.core.enemy.EnemyType;
import de.hda.tdpro.core.enemy.MetaEnemy;

/**
 * @author Marian Thiel
 * @version 1.0
 *
 * Singelton class
 * creates Enemies
 */
public class EnemyFactory {

    private static EnemyFactory instance;

    private EnemyFactory(){

    }

    public static EnemyFactory getInstance() {
        if(instance == null) {
            instance = new EnemyFactory();
        }
        return instance;
    }

    /**
     * creates random Enemy
     * @return a random Enemy
     */
    public Enemy createRandomEnemy(){
        Bitmap[] images = ResourceLoader.getInstance().getAnimation("NM");
        MetaEnemy meta = MetaEnemy.getMetaEnemy(EnemyType.RANDOM);
        return new Enemy(meta.getHp(), meta.getValue(), meta.getVel(), images);
    }

    public Enemy createL1Tank(){

        Bitmap[] images = ResourceLoader.getInstance().getAnimation("L1TANK");
        MetaEnemy meta = MetaEnemy.getMetaEnemy(EnemyType.L1TANK);
        return new Enemy(meta.getHp(), meta.getValue(), meta.getVel(), images);
    }

    public Enemy createL1Boss(){
        Bitmap[] images = ResourceLoader.getInstance().getAnimation("L1BOSS");
        MetaEnemy meta = MetaEnemy.getMetaEnemy(EnemyType.L1BOSS);
        return new Enemy(meta.getHp(), meta.getValue(), meta.getVel(), images);
    }


    public Enemy createEnemyByType(EnemyType type){
        Enemy e = null;
        switch (type){
            case RANDOM:
                e = createRandomEnemy();
                break;
            case L1TANK:
                e = createL1Tank();
                break;
            case L1BOSS:
                e = createL1Boss();
                break;
        }
        return e;
    }

}
