package de.hda.tdpro.core;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.HashMap;
import java.util.Map;

import de.hda.tdpro.R;
import de.hda.tdpro.StaticContext;
import de.hda.tdpro.core.tower.TowerType;

/**
 * the ResourceLoader class creates all images  for towers and enemies
 */
public class ResourceLoader {

    private Map<String, Bitmap[]> animations;

    private Map<TowerType, Bitmap[]> towerBitmaps;

    private static int MAX_LEVEL_IMAGES = 5;

    private static ResourceLoader instance;

    private ResourceLoader() {
        initAnimations();
        initTowers();
    }

    public static ResourceLoader getInstance() {
        if(instance == null) instance = new ResourceLoader();
        return instance;
    }

    private void initAnimations(){
        animations = new HashMap<>();

        Bitmap[] l1tank = new Bitmap[7];
        l1tank[0] = BitmapFactory.decodeResource(StaticContext.getContext().getResources(), R.drawable.ghost_idle1);
        l1tank[1] = BitmapFactory.decodeResource(StaticContext.getContext().getResources(), R.drawable.ghost_idle2);
        l1tank[2] = BitmapFactory.decodeResource(StaticContext.getContext().getResources(), R.drawable.ghost_idle3);
        l1tank[3] = BitmapFactory.decodeResource(StaticContext.getContext().getResources(), R.drawable.ghost_idle4);
        l1tank[4] = BitmapFactory.decodeResource(StaticContext.getContext().getResources(), R.drawable.ghost_idle5);
        l1tank[5] = BitmapFactory.decodeResource(StaticContext.getContext().getResources(), R.drawable.ghost_idle6);
        l1tank[6] = BitmapFactory.decodeResource(StaticContext.getContext().getResources(), R.drawable.ghost_idle7);
        animations.put("L1TANK",l1tank);

        Bitmap[] nm = new Bitmap[4];

        nm[0] = BitmapFactory.decodeResource(StaticContext.getContext().getResources(),R.drawable.nm1);
        nm[0] = Bitmap.createScaledBitmap(nm[0],100,100,false);
        nm[1] = BitmapFactory.decodeResource(StaticContext.getContext().getResources(),R.drawable.nm2);
        nm[1] = Bitmap.createScaledBitmap(nm[1],100,100,false);
        nm[2] = BitmapFactory.decodeResource(StaticContext.getContext().getResources(),R.drawable.nm3);
        nm[2] = Bitmap.createScaledBitmap(nm[2],100,100,false);
        nm[3] = BitmapFactory.decodeResource(StaticContext.getContext().getResources(),R.drawable.nm4);
        nm[3] = Bitmap.createScaledBitmap(nm[3],100,100,false);

        animations.put("NM",nm);

        Bitmap[] daem = new Bitmap[6];

        daem[0] = BitmapFactory.decodeResource(StaticContext.getContext().getResources(),R.drawable.daem1);
        daem[1] = BitmapFactory.decodeResource(StaticContext.getContext().getResources(),R.drawable.daem2);
        daem[2] = BitmapFactory.decodeResource(StaticContext.getContext().getResources(),R.drawable.daem3);
        daem[3] = BitmapFactory.decodeResource(StaticContext.getContext().getResources(),R.drawable.daem4);
        daem[4] = BitmapFactory.decodeResource(StaticContext.getContext().getResources(),R.drawable.daem5);
        daem[5] = BitmapFactory.decodeResource(StaticContext.getContext().getResources(),R.drawable.daem6);
        animations.put("L1BOSS",daem);
    }

    private void initTowers(){
        towerBitmaps = new HashMap<>();
        Bitmap[] firetower = new Bitmap[2];
        firetower[0] = BitmapFactory.decodeResource(StaticContext.getContext().getResources(), R.drawable.t_fire1);
        firetower[0] = Bitmap.createScaledBitmap(firetower[0],90,90,false);
        firetower[1] = BitmapFactory.decodeResource(StaticContext.getContext().getResources(), R.drawable.t_fire2);
        firetower[1] = Bitmap.createScaledBitmap(firetower[1],90,90,false);

        towerBitmaps.put(TowerType.FIRE_TOWER, firetower);
    }

    public Bitmap[] getAnimation(String name){
        return animations.get(name);
    }

    public Bitmap[] getTowerImages(TowerType type){
        return towerBitmaps.get(type);
    }

    public static int getMaxLevelImages() {
        return MAX_LEVEL_IMAGES;
    }
}
