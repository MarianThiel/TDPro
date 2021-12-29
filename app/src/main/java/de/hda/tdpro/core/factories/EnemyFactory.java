package de.hda.tdpro.core.factories;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import de.hda.tdpro.R;
import de.hda.tdpro.core.enemy.Enemy;

public class EnemyFactory {

    private static EnemyFactory instance;
    private final Resources res;
    private EnemyFactory(Resources res){
        this.res = res;
    }

    public static EnemyFactory getInstance(Resources res) {
        if(instance == null) {
            instance = new EnemyFactory(res);
        }
        return instance;
    }

    public Enemy createRandomEnemy(){
        return new Enemy((int)(100 + Math.random()*1500),0,(float) (50 + (Math.random()*175)), BitmapFactory.decodeResource(res, R.drawable.test3));
    }
}
