/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MathExt;

/**
 *
 * @author bowen
 */
public abstract class Vectors {
    public static Vector shell(Vector vector) {
        return new Vector(vector.getDimensionSize(1));
    }
    public static Vector neg(Vector vector) {
        return new Vector(Tensors.neg(vector));
    }
    public static Vector add(Vector... vectorArray) {
        return new Vector(Tensors.add(vectorArray));
    }
    public static Vector add(Vector matrix, double c) {
        return new Vector(Tensors.add(matrix, c));
    }
    public static Vector sub(Vector... vectorArray) {
        return new Vector(Tensors.sub(vectorArray));
    }
    public static Vector sub(Vector matrix, double c) {
        return new Vector(Tensors.sub(matrix, c));
    }
    public static Vector prod(Vector... vectorArray) {
        return new Vector(Tensors.prod(vectorArray));
    }
    public static Vector prod(Vector matrix, double c) {
        return new Vector(Tensors.prod(matrix, c));
    }
    public static Vector div(Vector... vectorArray) {
        return new Vector(Tensors.div(vectorArray));
    }
    public static Vector div(Vector matrix, double c) {
        return new Vector(Tensors.div(matrix, c));
    }
    public static Vector mult(Matrix matrix, Vector vector) {
        return Tensors.toVector(Matrices.mult(matrix, Vectors.toColumnMatrix(vector)));
    }
    public static Matrix toRowMatrix(Vector vector) {
        return new Matrix(Tensors.join(vector));
    }
    public static Matrix toColumnMatrix(Vector vector) {
        return Matrices.transpose(Tensors.toMatrix(Tensors.join(vector)));
    }
}
