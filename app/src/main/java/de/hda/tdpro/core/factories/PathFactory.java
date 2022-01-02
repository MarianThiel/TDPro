package de.hda.tdpro.core.factories;

import de.hda.tdpro.core.enemy.Path;
import de.hda.tdpro.core.enemy.RelativePath;

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
        path.addStaticPoint(0,0);
        path.addStaticPoint(50,300);
        path.addStaticPoint(300,500);
        path.addStaticPoint(500,550);
        path.addStaticPoint(500,650);
        path.addStaticPoint(1000,750);
        path.addStaticPoint(1000,450);
        path.addStaticPoint(500,450);
        path.addStaticPoint(500,250);
        path.addStaticPoint(1500,250);
        path.addStaticPoint(1500,1250);

        return path;
    }

    public Path createRelativePathTest(){
        RelativePath p = new RelativePath();
        p.addStaticPoint(0,0);
        p.addStaticPoint(0,400);
        p.addStaticPoint(300,0);



        return p;
    }

}
