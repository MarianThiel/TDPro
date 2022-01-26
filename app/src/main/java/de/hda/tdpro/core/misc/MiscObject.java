package de.hda.tdpro.core.misc;

import android.graphics.Canvas;

import de.hda.tdpro.core.Drawable;
import de.hda.tdpro.core.Intersectable;
import de.hda.tdpro.core.Position;

public class MiscObject implements Drawable, Intersectable {

    private Position position;

    private int width;

    private int height;

    private MiscType type;



    @Override
    public void draw(Canvas canvas) {

    }


    @Override
    public boolean intersects(Position position) {
        return false;
    }

    @Override
    public void showBorders(boolean v) {

    }
}
