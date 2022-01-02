package de.hda.tdpro.core;

import android.graphics.Canvas;

/**
 * Class which implements this interface can be drawn on the screen
 */
public interface Drawable {
    /**
     * draws on Canvas
     * @param canvas the canvas to paint
     */
    void draw(Canvas canvas);
}
