/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MathExt;

import java.util.Arrays;

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
    }
    public static Matrix toMatrix(Tensor tensor2) {
        if (tensor2.getDimensions() > 2) {
            throw new IndexOutOfBoundsException("Cannot convert 3rd or higher order tensor into matrix.");
        }
        return new Matrix(tensor2);
    }
    public static Matrix toRowMatrix(Tensor tensor1) {
        if (tensor1.getDimensions() > 1) {
            throw new IndexOutOfBoundsException("Tensor is not of 1st order.");
        }
        return new Matrix(Tensor.join(tensor1));
    }
    public static Matrix toColumnMatrix(Tensor tensor1) {
        if (tensor1.getDimensions() > 1) {
            throw new IndexOutOfBoundsException("Tensor is not of 1st order.");
        }
        return Matrix.transpose(new Matrix(Tensor.join(tensor1)));
    }
    public static Matrix shell(Matrix matrix) {
        return new Matrix(matrix.getDimensionSize(2), matrix.getDimensionSize(1));
    }
    public static Matrix clone(Matrix matrix) {
        return new Matrix(Tensor.clone(matrix));
    }
    public static Matrix neg(Matrix matrix) {
        return new Matrix(Tensor.neg(matrix));
    }
    public static Matrix transpose(Matrix matrix) {
        int rows = matrix.getDimensionSize(1); //swap dimension 1 and 2
        int columns = matrix.getDimensionSize(2);
        Matrix newMatrix = new Matrix(rows, columns);
        
        for (int i=0; i<rows; i++) {
            for (int j=0; j<columns; j++) {
                newMatrix.set(matrix.get(j, i), i, j);
            }
        }
        return newMatrix;
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
    public static Matrix mult(Matrix... matrixArray) {
        Matrix multMatrix = Matrix.clone(matrixArray[0]); //save the leftmost matrix
        int multMatrixRows = multMatrix.getDimensionSize(2);
        int multMatrixColumns = multMatrix.getDimensionSize(1);
        for (int k=1; k<matrixArray.length; k++) { //multiply the leftmost matrix with each matrix on the right of it
            
            int nextMatrixRows = matrixArray[k].getDimensionSize(2);
            int nextMatrixColumns = matrixArray[k].getDimensionSize(1);
            
            if (multMatrixColumns == nextMatrixRows) { //if the two matrices can be multiplied
                Matrix newMatrix = new Matrix(multMatrixRows, nextMatrixColumns); //create the shell of the new matrix
                int n = multMatrixColumns;
                
                int newMatrixRows = newMatrix.getDimensionSize(2);
                int newMatrixColumns = newMatrix.getDimensionSize(1);

                for (int i=0; i<newMatrixRows; i++) { //matrix multiplication
                    for (int j=0; j<newMatrixColumns; j++) {
                        double component = 0;
                        for (int r=0; r<n; r++) {
                            component += multMatrix.get(i, r) * matrixArray[k].get(r, j);
                        }
                        newMatrix.set(i, j, component);
                    }
                }
                multMatrix = newMatrix; //save the new matrix as the leftmost matrix, and repeat
            } else {
                throw new IllegalArgumentException("Multiplication of different-sized matrices.");
            }
        }
        return multMatrix;
    }
    final public static boolean isSameDimensions(Matrix... matrixArray) {
        int rows = matrixArray[0].getDimensionSize(2);
        int columns = matrixArray[0].getDimensionSize(1);
        
        for (int n=1; n<matrixArray.length; n++) {
            if (matrixArray[n].getDimensionSize(2) != rows || matrixArray[n].getDimensionSize(1) != columns) {
                return false;
            }
        }
        return true;
    }
    public double getNorm(int p, int q) { //Implement PQ norm
        return 0;
    }
    final public void add(Matrix matrix) {
        if (isSameDimensions(this, matrix)) {
            for (int i=0, length=getElementsLength(); i<length; i++) {
                set(i, get(i)+matrix.get(i));
            }
        } else {
            throw new IndexOutOfBoundsException("Different matrix sizes.");
        }
    }
    final public void sub(Matrix matrix) {
        if (isSameDimensions(this, matrix)) {
            for (int i=0, length=getElementsLength(); i<length; i++) {
                set(i, get(i)-matrix.get(i));
            }
        } else {
            throw new IndexOutOfBoundsException("Different matrix sizes.");
        }
    }
    final public void prod(Matrix matrix) {
        if (isSameDimensions(this, matrix)) {
            for (int i=0, length=getElementsLength(); i<length; i++) {
                set(i, get(i)*matrix.get(i));
            }
        } else {
            throw new IndexOutOfBoundsException("Different matrix sizes.");
        }
    }
    final public void div(Matrix matrix) {
        if (isSameDimensions(this, matrix)) {
            for (int i=0, length=getElementsLength(); i<length; i++) {
                double divisor = matrix.get(i);
                if (divisor != 0) {
                    set(i, get(i)/divisor);
                } else {
                    throw new IllegalArgumentException("Divide by zero.");
                }
            }
        } else {
            throw new IndexOutOfBoundsException("Different matrix sizes.");
        }
    }
    public double get(int m, int n) {
        if (isWithinBounds(m, n)) {
            int index = m * getDimensionSize(1) + n;
            return get(index);
        } else {
            throw new IndexOutOfBoundsException("Invalid matrix index.");
        }
    }
    public void set(int m, int n, double c) {
        if (isWithinBounds(m, n)) {
            int index = m * getDimensionSize(1) + n;
            set(index, c);
        } else {
            throw new IndexOutOfBoundsException("Invalid matrix index.");
        }
    }
    public void setFrom(Matrix matrix) {
        super.setFrom(matrix);
    }
    public void copyFrom(Matrix matrix) {
        super.copyFrom(matrix);
    }
    private boolean isWithinBounds(int m, int n) {
        if (m >= 0 && m < getDimensionSize(2) && n >= 0 && n < getDimensionSize(1)) {
            return true;
        } else {
            return false;
        }
    }
}
