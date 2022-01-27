package de.hda.tdpro.core;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import java.util.HashMap;
import java.util.Map;

import de.hda.tdpro.R;
import de.hda.tdpro.StaticContext;
import de.hda.tdpro.core.enemy.Vector2D;
import de.hda.tdpro.core.misc.MiscType;
import de.hda.tdpro.core.tower.TowerType;
import de.hda.tdpro.core.tower.upgrades.UpgradeType;

/**
 * the ResourceLoader class creates all images  for towers and enemies
 */
public class ResourceLoader {

    private Map<String, Bitmap[]> animations;

    private Map<TowerType, Bitmap[]> towerBitmaps;

    private Map<UpgradeType,Bitmap> upgradeBadges;

    private Map<TowerType, Bitmap> projectiles;

    private Map<MiscType, Bitmap[]> miscs;

    private static int MAX_LEVEL_IMAGES = 5;

    private static ResourceLoader instance;

    private ResourceLoader() {
        initAnimations();
        initTowers();
        initUpgradeBadges();
    }

    public static ResourceLoader getInstance() {
        if(instance == null) instance = new ResourceLoader();
        return instance;
    }

    private void initAnimations(){
        animations = new HashMap<>();


        addL1TankResource();

        addEnemy2Resource();

        addL1BossResource();
        initMisc();
    }

    private void addL1BossResource() {
        Bitmap[] daem = new Bitmap[6];

        daem[0] = BitmapFactory.decodeResource(StaticContext.getContext().getResources(),R.drawable.daem1);
        daem[1] = BitmapFactory.decodeResource(StaticContext.getContext().getResources(),R.drawable.daem2);
        daem[2] = BitmapFactory.decodeResource(StaticContext.getContext().getResources(),R.drawable.daem3);
        daem[3] = BitmapFactory.decodeResource(StaticContext.getContext().getResources(),R.drawable.daem4);
        daem[4] = BitmapFactory.decodeResource(StaticContext.getContext().getResources(),R.drawable.daem5);
        daem[5] = BitmapFactory.decodeResource(StaticContext.getContext().getResources(),R.drawable.daem6);
        animations.put("L1BOSS",daem);
    }

    private void addEnemy2Resource() {
        Bitmap[] nm = new Bitmap[10];
        float scaleFactor = 0.7f;

        nm[0] = scale(BitmapFactory.decodeResource(StaticContext.getContext().getResources(),R.drawable.run1),scaleFactor);

        nm[1] = scale(BitmapFactory.decodeResource(StaticContext.getContext().getResources(),R.drawable.run2),scaleFactor);

        nm[2] = scale(BitmapFactory.decodeResource(StaticContext.getContext().getResources(),R.drawable.run3),scaleFactor);

        nm[3] = scale(BitmapFactory.decodeResource(StaticContext.getContext().getResources(),R.drawable.run4),scaleFactor);
        nm[4] = scale(BitmapFactory.decodeResource(StaticContext.getContext().getResources(),R.drawable.run5),scaleFactor);
        nm[5] = scale(BitmapFactory.decodeResource(StaticContext.getContext().getResources(),R.drawable.run6),scaleFactor);
        nm[6] = scale(BitmapFactory.decodeResource(StaticContext.getContext().getResources(),R.drawable.run7),scaleFactor);
        nm[7] = scale(BitmapFactory.decodeResource(StaticContext.getContext().getResources(),R.drawable.run8),scaleFactor);
        nm[8] = scale(BitmapFactory.decodeResource(StaticContext.getContext().getResources(),R.drawable.run9),scaleFactor);
        nm[9] = scale(BitmapFactory.decodeResource(StaticContext.getContext().getResources(),R.drawable.run10),scaleFactor);

        animations.put("NM",nm);
    }

    private Bitmap scale(Bitmap b, float f){
        return Bitmap.createScaledBitmap(b,(int)(b.getWidth()*f),(int)(b.getHeight()*f),true);
    }

    private void addL1TankResource() {

        Bitmap[] l1tank = new Bitmap[7];
        l1tank[0] = BitmapFactory.decodeResource(StaticContext.getContext().getResources(), R.drawable.ghost_idle1);
        l1tank[1] = BitmapFactory.decodeResource(StaticContext.getContext().getResources(), R.drawable.ghost_idle2);
        l1tank[2] = BitmapFactory.decodeResource(StaticContext.getContext().getResources(), R.drawable.ghost_idle3);
        l1tank[3] = BitmapFactory.decodeResource(StaticContext.getContext().getResources(), R.drawable.ghost_idle4);
        l1tank[4] = BitmapFactory.decodeResource(StaticContext.getContext().getResources(), R.drawable.ghost_idle5);
        l1tank[5] = BitmapFactory.decodeResource(StaticContext.getContext().getResources(), R.drawable.ghost_idle6);
        l1tank[6] = BitmapFactory.decodeResource(StaticContext.getContext().getResources(), R.drawable.ghost_idle7);
        animations.put("L1TANK",l1tank);
    }

    private void initTowers(){
        towerBitmaps = new HashMap<>();

        projectiles = new HashMap<>();

        projectiles.put(TowerType.FIRE_TOWER,BitmapFactory.decodeResource(StaticContext.getContext().getResources(),R.drawable.proj_fire));


        Bitmap[] firetower = new Bitmap[2];
        firetower[0] = scale(BitmapFactory.decodeResource(StaticContext.getContext().getResources(), R.drawable.f_tower),0.15f);

        firetower[1] = BitmapFactory.decodeResource(StaticContext.getContext().getResources(), R.drawable.t_fire2);
        firetower[1] = Bitmap.createScaledBitmap(firetower[1],90,90,false);

        towerBitmaps.put(TowerType.FIRE_TOWER, firetower);
    }

    private void initUpgradeBadges(){
        upgradeBadges = new HashMap<>();

        Bitmap b;

        b = BitmapFactory.decodeResource(StaticContext.getContext().getResources(), R.drawable.badge_dmg_1);
        upgradeBadges.put(UpgradeType.L1_DMG_UPGRADE,b);

        b = BitmapFactory.decodeResource(StaticContext.getContext().getResources(), R.drawable.badge_vel_1);
        upgradeBadges.put(UpgradeType.L1_VEL_UPGRADE,b);

        b = BitmapFactory.decodeResource(StaticContext.getContext().getResources(), R.drawable.badge_rng_1);
        upgradeBadges.put(UpgradeType.L1_RNG_UPGRADE,b);


        b = BitmapFactory.decodeResource(StaticContext.getContext().getResources(), R.drawable.badge_dmg_2);
        upgradeBadges.put(UpgradeType.L2_DMG_UPGRADE,b);

        b = BitmapFactory.decodeResource(StaticContext.getContext().getResources(), R.drawable.badge_vel_2);
        upgradeBadges.put(UpgradeType.L2_VEL_UPGRADE,b);

        b = BitmapFactory.decodeResource(StaticContext.getContext().getResources(), R.drawable.badge_rng_2);
        upgradeBadges.put(UpgradeType.L2_RNG_UPGRADE,b);


        b = BitmapFactory.decodeResource(StaticContext.getContext().getResources(), R.drawable.badge_dmg_3);
        upgradeBadges.put(UpgradeType.L3_DMG_UPGRADE,b);

        b = BitmapFactory.decodeResource(StaticContext.getContext().getResources(), R.drawable.badge_vel_3);
        upgradeBadges.put(UpgradeType.L3_VEL_UPGRADE,b);

        b = BitmapFactory.decodeResource(StaticContext.getContext().getResources(), R.drawable.badge_rng_3);
        upgradeBadges.put(UpgradeType.L3_RNG_UPGRADE,b);
    }

    private void initMisc(){
        miscs = new HashMap<>();

        MiscType type = MiscType.BASE;
        Bitmap[] bases = new Bitmap[1];
        bases[0] = scale(BitmapFactory.decodeResource(StaticContext.getContext().getResources(),R.drawable.base),0.5f);
        miscs.put(type,bases);
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

    public Bitmap getUpgradeBadge(UpgradeType type){
        return upgradeBadges.get(type);
    }

    public Bitmap getProjectile(TowerType t){
        return projectiles.get(t);
    }

    public Bitmap getRotated(Bitmap b, Position p1, Position p2){
        Vector2D v1 = new Vector2D(p1.getxVal(),p1.getyVal());
        Vector2D v2 = new Vector2D(p1.getxVal(),p1.getyVal() - 300);
        Vector2D v3 = new Vector2D(p2.getxVal(),p2.getyVal());

        v2 = v2.dif(v1);
        v3 = v3.dif(v1);

        double arc = v2.getArc(v3);
        Matrix m = new Matrix();
        m.setRotate((float)arc,p1.getxVal(),p1.getyVal());

        return Bitmap.createBitmap(b,0,0,b.getWidth(),b.getHeight(),m,true);
    }


    public Bitmap getRandomMisc(MiscType type){
        Bitmap[] bitmaps = miscs.get(type);
        return bitmaps[0];
    }
}
