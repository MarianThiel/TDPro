package de.hda.tdpro.core.factories;

import android.content.res.Resources;
import android.graphics.Bitmap;
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
        Bitmap[] images = new Bitmap[4];

            images[0] = BitmapFactory.decodeResource(StaticContext.getContext().getResources(),R.drawable.nm1);
            images[0] = Bitmap.createScaledBitmap(images[0],120,120,false);
            images[1] = BitmapFactory.decodeResource(StaticContext.getContext().getResources(),R.drawable.nm2);
            images[1] = Bitmap.createScaledBitmap(images[1],120,120,false);
            images[2] = BitmapFactory.decodeResource(StaticContext.getContext().getResources(),R.drawable.nm3);
            images[2] = Bitmap.createScaledBitmap(images[2],120,120,false);
            images[3] = BitmapFactory.decodeResource(StaticContext.getContext().getResources(),R.drawable.nm4);
            images[3] = Bitmap.createScaledBitmap(images[3],120,120,false);
        return new Enemy((int)(100 + Math.random()*1500),0,(float) (50+ (Math.random()*70)), images);
    }

    public Enemy createL1Tank(){

        Bitmap[] images = new Bitmap[7];
        for(int i = 1; i <= images.length; i++){
            images[i-1] = BitmapFactory.decodeResource(StaticContext.getContext().getResources(),R.drawable.ghost_idle + i);
        }

        return new Enemy(1000,50, 50,images);
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
