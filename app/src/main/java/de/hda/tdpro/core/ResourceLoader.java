package de.hda.tdpro.core;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.HashMap;
import java.util.Map;

import de.hda.tdpro.R;
import de.hda.tdpro.StaticContext;

public class ResourceLoader {

    private Map<String, Bitmap[]> animations;

    private static ResourceLoader instance;

    private ResourceLoader() {
        initAnimations();
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
        nm[1] = BitmapFactory.decodeResource(StaticContext.getContext().getResources(),R.drawable.nm2);
        nm[2] = BitmapFactory.decodeResource(StaticContext.getContext().getResources(),R.drawable.nm3);
        nm[3] = BitmapFactory.decodeResource(StaticContext.getContext().getResources(),R.drawable.nm4);

        animations.put("NM",nm);

    }

    public Bitmap[] getAnimation(String name){
        return animations.get(name);
    }
}
