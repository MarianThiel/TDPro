package de.hda.tdpro.core.factories;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import de.hda.tdpro.R;
import de.hda.tdpro.StaticContext;
import de.hda.tdpro.core.ResourceLoader;
import de.hda.tdpro.core.enemy.Enemy;
import de.hda.tdpro.core.enemy.EnemyType;

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

        return new Enemy(50,0,130, images);
    }

    public Enemy createL1Tank(){

        Bitmap[] images = ResourceLoader.getInstance().getAnimation("L1TANK");

        return new Enemy(100,50, 100,images);
    }

    public Enemy createL1Boss(){
        Bitmap[] images = ResourceLoader.getInstance().getAnimation("L1BOSS");
        return new Enemy(12000,50, 30,images);
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
