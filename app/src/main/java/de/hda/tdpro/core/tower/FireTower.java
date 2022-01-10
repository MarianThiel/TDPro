package de.hda.tdpro.core.tower;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import de.hda.tdpro.R;
import de.hda.tdpro.StaticContext;
import de.hda.tdpro.core.ResourceLoader;

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
        img = ResourceLoader.getInstance().getTowerImages(TowerType.FIRE_TOWER);
        // img = BitmapFactory.decodeResource(StaticContext.getContext().getResources(), R.drawable.firetower);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawBitmap(img[0], getPos().getxVal()-(img[0].getWidth()/2),getPos().getyVal()-(img[0].getHeight()/2),null);

    }
}
