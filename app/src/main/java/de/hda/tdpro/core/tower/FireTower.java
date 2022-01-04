package de.hda.tdpro.core.tower;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import de.hda.tdpro.R;
import de.hda.tdpro.StaticContext;

/**
 * @author Marian Thiel
 * @version 1.1
 * special tower overrides draw method to draw different tower image
 * inits the sphere
 */
public class FireTower extends Tower {
    public FireTower(int radius, int damage, float speed, int price) {
        super(radius, damage, speed, price);
        this.sphere = new RangeSphere(this, radius);
        img = BitmapFactory.decodeResource(StaticContext.getContext().getResources(), R.drawable.firetower);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawBitmap(img, getPos().getxVal()-(img.getWidth()/2),getPos().getyVal()-(img.getHeight()/2),null);

    }
}
