package de.hda.tdpro.core.enemy;

import android.util.Log;

public class GaussMatrix {

    double[][] matrix;
    double []v;

    double det;
    public GaussMatrix(Vector2D a1, Vector2D a2,Vector2D a3, Vector2D a4) {
        matrix = new double[2][2];


        matrix[0][0] = a2.x;
        matrix[1][0] = a2.y;


        matrix[0][1] = a3.x;
        matrix[1][1] = a3.y;

        v = new double[2];
        v[0] = a4.x + ((-1)*a1.x);
        v[1] = a4.y + ((-1)*a1.y);


    }

    double[][] getCramMatrix(int i){
        double[][] d = new double[2][2];
        for(int j = 0; j < matrix.length; j++){
            for(int k = 0 ;k < matrix.length; k++){
                if(k == i){
                    d[j][k] = v[j];
                }else{
                    d[j][k] = matrix[j][k];
                }
            }
        }

        return d;
    }

    public double determine(double[][] d){
        return (d[0][0] * d[1][1]) - (d[1][0] * d[0][1]);
    }

    public double getX1(){
        return determine(getCramMatrix(0))/determine(matrix);
    }
    public double getX2(){
        return determine(getCramMatrix(1))/determine(matrix);
    }


}
