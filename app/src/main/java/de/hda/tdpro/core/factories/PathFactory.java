package de.hda.tdpro.core.factories;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.view.WindowMetrics;

import de.hda.tdpro.StaticContext;
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
        WindowManager wm = ((WindowManager)StaticContext.getContext().getSystemService(Context.WINDOW_SERVICE));

        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);

        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        p.addStaticPoint(0,height/4);
        p.addStaticPoint(width/4,0);
        p.addStaticPoint(0,height/4);
        p.addStaticPoint(width/4,0);
        p.addStaticPoint(0,height/4);

        p.addStaticPoint(width/4,0);
        p.addStaticPoint(0,-(height/2));

        p.addStaticPoint(width/4,0);




        return p;
    }

}
