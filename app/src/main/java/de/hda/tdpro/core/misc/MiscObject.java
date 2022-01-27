package de.hda.tdpro.core.misc;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import de.hda.tdpro.core.Drawable;
import de.hda.tdpro.core.Intersectable;
import de.hda.tdpro.core.Position;
import de.hda.tdpro.core.ResourceLoader;

public class MiscObject implements Drawable, Intersectable {

    private Position position;

    private int width;

    private int height;

    private MiscType type;

    private Bitmap img;

    public MiscObject(Position position, MiscType type) {
        this.position = position;
        this.type = type;
        img = ResourceLoader.getInstance().getRandomMisc(type);

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(img, (float) (position.getxVal() - (img.getWidth()/1.7)),position.getyVal()-(img.getHeight()-90),null);
    }


    @Override
    public boolean intersects(Position position) {
        return false;
    }

    @Override
    public void showBorders(boolean v) {

    }
}
