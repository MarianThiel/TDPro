package de.hda.tdpro.core.util;

import de.hda.tdpro.core.path.Position;

/**
 * @author Marian Thiel
 * @version 1.0
 *
 * Mathematical construct class for 2D vectors
 */
public class Vector2D implements Comparable<Vector2D>{

    public double x;

    public double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D(Vector2D v) {
        x = v.x;
        y = v.y;
    }

    public Vector2D(Position p){
        x = p.getxVal();
        y = p.getyVal();
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

    public double scalar(Vector2D v){
        return (x*v.x)+(y*v.y);
    }

    public double getArc(Vector2D v){
        double arc = (Math.acos(scalar(v)/(this.norm()*v.norm())))*(180/Math.PI);
        if(v.x < 0){
            arc = 360 - arc;
        }
        return arc;
    }

    public Vector2D getOrthogonal(Vector2D e){
        return this.dif(e.mul(scalar(e)));
    }

    public Vector2D getUnitNormalized(){

        double v1 = (-y)/(Math.sqrt((x*x)+(y*y)));
        double v2 = (x)/(Math.sqrt((x*x)+(y*y)));
        return new Vector2D(v1,v2);
    }

    @Override
    public int compareTo(Vector2D o) {
        double l1 = norm();
        double l2 = o.norm();

        return Double.compare(l1, l2);
    }
}
