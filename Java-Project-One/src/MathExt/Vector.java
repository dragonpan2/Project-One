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
public class Vector extends Tensor {
    public Vector(int n) {
        super(new int[] {n});
    }
    public Vector(int n, double fill) {
        super(new int[] {n}, fill);
    }
    public Vector(double[] elementArray) {
        super(elementArray);
    }
    protected Vector(Tensor tensor) {
        super(tensor);
    }
    public static Vector toVector(Tensor tensor1) {
        if (tensor1.getDimensions() > 1) {
            throw new IndexOutOfBoundsException("Cannot convert 2rd or higher order tensor into vector.");
        }
        return new Vector(tensor1);
    }
    public static Vector shell(Vector vector) {
        return new Vector(vector.getDimensionSize(1));
    }
    public static Vector clone(Vector vector) {
        return new Vector(Tensor.clone(vector));
    }
    public static Vector neg(Vector vector) {
        return new Vector(Tensor.neg(vector));
    }
    public static Vector add(Vector... vectorArray) {
        return new Vector(Tensor.add(vectorArray));
    }
    public static Vector add(Vector matrix, double c) {
        return new Vector(Tensor.add(matrix, c));
    }
    public static Vector sub(Vector... vectorArray) {
        return new Vector(Tensor.sub(vectorArray));
    }
    public static Vector sub(Vector matrix, double c) {
        return new Vector(Tensor.sub(matrix, c));
    }
    public static Vector prod(Vector... vectorArray) {
        return new Vector(Tensor.prod(vectorArray));
    }
    public static Vector prod(Vector matrix, double c) {
        return new Vector(Tensor.prod(matrix, c));
    }
    public static Vector div(Vector... vectorArray) {
        return new Vector(Tensor.div(vectorArray));
    }
    public static Vector div(Vector matrix, double c) {
        return new Vector(Tensor.div(matrix, c));
    }
    public static Vector mult(Matrix matrix, Vector vector) {
        return toVector(Matrix.mult(matrix, Matrix.toColumnMatrix(vector)));
    }
}
