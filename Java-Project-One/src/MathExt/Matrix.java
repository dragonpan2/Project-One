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
public class Matrix extends Tensor {
    public Matrix(int s) {
        super(new int[] {s,s});
    }
    public Matrix(int s, double fill) {
        super(new int[] {s,s}, fill);
    }
    public Matrix(int m, int n) {
        super(new int[] {m,n});
    }
    public Matrix(int m, int n, double fill) {
        super(new int[] {m,n}, fill);
    }
    public Matrix(double[][] elementArray2D) {
        super(elementArray2D);
    }
    protected Matrix(Tensor tensor) {
        super(tensor);
        if (tensor.getDimensions() > 2) {
            throw new IndexOutOfBoundsException("Cannot convert 3rd or higher order tensor into matrix.");
        }
    }
    
    public static Matrix shell(Matrix matrix) {
        return new Matrix(matrix.getDimensionSize(2), matrix.getDimensionSize(1));
    }
    public static Matrix clone(Matrix matrix) {
        return new Matrix(Tensor.clone(matrix));
    }
    public static Matrix add(Matrix... matrixArray) {
        return new Matrix(Tensor.add(matrixArray));
    }
    public static Matrix add(Matrix matrix, double c) {
        return new Matrix(Tensor.add(matrix, c));
    }
    public static Matrix sub(Matrix... matrixArray) {
        return new Matrix(Tensor.sub(matrixArray));
    }
    public static Matrix sub(Matrix matrix, double c) {
        return new Matrix(Tensor.sub(matrix, c));
    }
    public static Matrix prod(Matrix... matrixArray) {
        return new Matrix(Tensor.prod(matrixArray));
    }
    public static Matrix prod(Matrix matrix, double c) {
        return new Matrix(Tensor.prod(matrix, c));
    }
    public static Matrix div(Matrix... matrixArray) {
        return new Matrix(Tensor.div(matrixArray));
    }
    public static Matrix div(Matrix matrix, double c) {
        return new Matrix(Tensor.div(matrix, c));
    }
    public static Matrix neg(Matrix matrix) {
        return new Matrix(Tensor.neg(matrix));
    }
}
