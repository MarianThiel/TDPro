package de.hda.tdpro.core.factories;

import android.content.Context;

import de.hda.tdpro.core.tower.FireTower;

public class TowerFactory {
    private static TowerFactory instance = null;

    private TowerFactory(){

    }

    public static TowerFactory getInstance(){
        if(instance == null){
            instance = new TowerFactory();
        }
        return instance;
    }

    public FireTower createFireTower(){

        return new FireTower(200,50,4,80);
    }
}

