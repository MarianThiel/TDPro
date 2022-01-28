package de.hda.tdpro.core.factories;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import de.hda.tdpro.StaticContext;
import de.hda.tdpro.core.path.Path;
import de.hda.tdpro.core.path.RelativePath;

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

    public Path createRelativePathTest(){
        RelativePath p = new RelativePath();
        WindowManager wm = ((WindowManager)StaticContext.getContext().getSystemService(Context.WINDOW_SERVICE));

        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);

        int height = metrics.heightPixels;
        int width = metrics.widthPixels;
        if(height>width){
            int t = width;
            width = height;
            height = t;
        }

        p.addPoint(0,height/4);
        p.addPoint(width/4,0);
        p.addPoint(0,height/4);
        p.addPoint(width/4,0);
        p.addPoint(0,height/4);

        p.addPoint(width/4,0);
        p.addPoint(0,-(height/4));

        p.addPoint(width/4,0);


        return p;
    }

}
