package de.hda.tdpro.core.enemy;

/**
 * @author Marian Thiel
 * @version 1.0
 *
 * Mathematical construct class for 2D vectors
 */
public class Vector2D {

    public double x;

    public double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D mul(double val){
        //this.x = x*val;
        //this.y = y*val;
        return new Vector2D(x*val,y*val);
    }
    public Vector2D add(Vector2D v){
        //this.x += v.x;
        //this.y += v.y;
        return new Vector2D(x+v.x,y+v.y);
    }
    public Vector2D dif(Vector2D v){
        //this.x -= v.x;
        //this.y -= v.y;
        return new Vector2D(x-v.x,y-v.y);
    }
    public void normalize(){
        double f = 1/norm();
        x = f*x;
        y = f*y;
    }
    public double norm(){

        return Math.sqrt((x*x)+(y*y));
    }

}
