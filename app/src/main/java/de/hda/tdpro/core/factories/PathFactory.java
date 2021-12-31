package de.hda.tdpro.core.factories;

import de.hda.tdpro.core.enemy.Path;

public class PathFactory {
    private static PathFactory instance;

    private PathFactory() {

    }

    public static PathFactory getInstance() {

        if(instance==null){
            instance = new PathFactory();
        }

        return instance;
    }

    public Path createLevelOnePath(){
        Path path = new Path();
        path.addPoint(0,0);
        path.addPoint(50,300);
        path.addPoint(300,500);
        path.addPoint(500,550);
        path.addPoint(500,650);
        path.addPoint(1000,750);
        path.addPoint(1000,450);
        path.addPoint(500,450);
        path.addPoint(500,250);
        path.addPoint(1500,250);
        path.addPoint(1500,1250);

        return path;
    }

}
