package de.hda.tdpro.core.factories;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import de.hda.tdpro.R;
import de.hda.tdpro.StaticContext;
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
        return new Enemy((int)(100 + Math.random()*1500),0,(float) (50+ (Math.random()*70)), BitmapFactory.decodeResource(StaticContext.getContext().getResources(), R.drawable.test3));
    }

    public Enemy createL1Tank(){
        return new Enemy(1000,50, 50,BitmapFactory.decodeResource(StaticContext.getContext().getResources(),R.drawable.ghost_idle4));
    }

    public Enemy createEnemyByType(EnemyType type){
        Enemy e = null;
        switch (type){
            case RANDOM:
                e = createRandomEnemy();
                break;
            case L1TANK:
                e = createL1Tank();
        }
        return e;
    }

}
