/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SLAG;


/**
 *
 * @author bowen
 */
public class Vector extends Matrix {
    
    public Vector(int columns) {
        super(1, columns);
    }
//    public Vector(int rows, boolean isColumn) {
//        super(rows, 1);
//    }
    public Vector(double[] array) {
        super(array);
    }
    
    public Vector(Matrix matrix) {
        super(matrix.getComponents()[0]);
    }
    
//    public static Vector toVector(Matrix matrix) {
//        return new Vector(matrix.getComponents()[0]);
//    }
    
    public static Vector add(Vector... args) {
        //return toVector(Matrix.add(args));
        return new Vector(Matrix.add(args));
    }
    
    public static Vector sub(Vector... args) {
        return new Vector(Matrix.sub(args));
    }
    
    public static Vector multSca(Vector vector, double... scalar) {
        return new Vector(Matrix.multSca(vector, scalar));
    }
    
    public static double prodSca(Vector... args) {
        if (!checkSameSize(args)) {
            return 0;
        }
        double scalarproduct = 0;
        for (int i=0; i<args[0].getColumns(); i++) {
            double product = args[0].getComponent(0, i);
            for (int k=1; k<args.length; k++) {
                product *= args[k].getComponent(0, i);
            }
            scalarproduct += product;
        }
        return scalarproduct;
    }
    
    public static double projSca(Vector vec1, Vector vec2) {
        return Vector.prodSca(vec1, vec2)/vec2.getNorm();
    }
    
    public static Vector projVec(Vector... args) {
        Vector vec1 = args[0];
        for (int k=1; k<args.length; k++) {
            Vector vec2 = args[k];
            double prod1 = Vector.prodSca(vec1, vec2);
            double prod2 = Vector.prodSca(vec2, vec2);
            if (prod2 == 0) return vec1;
            vec1 = Vector.multSca(vec2, prod1/prod2);
        }
        return vec1;
    }
    
    public static Vector rejVec(Vector... args) {
        Vector vec1 = args[0];
        for (int k=1; k<args.length; k++) {
            Vector vec2 = args[k];
            double prod1 = Vector.prodSca(vec1, vec2);
            double prod2 = Vector.prodSca(vec2, vec2);
            if (prod2 == 0) return vec1;
            vec1 = Vector.sub(vec1, Vector.multSca(vec2, prod1/prod2));
        }
        return vec1;
    }
    
    public static double norm(Vector vector) {
        double sum = 0;
        for (int i=0; i<vector.getColumns(); i++) {
            sum += Math.pow(vector.getComponent(0, i), 2);
        }
        return Math.sqrt(sum);
    }
    
    public double getNorm() {
        return norm(this);
    }
}
