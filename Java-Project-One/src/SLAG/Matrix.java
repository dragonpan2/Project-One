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
public class Matrix {
    private double[][] elements;
    private int rows, columns;
    
    public Matrix(int m, int n) {
        this.elements = new double[m][n];
        this.rows = m;
        this.columns = n;
    }
    
    public Matrix(int n) {
        this.rows = n;
        this.columns = n;
        this.elements = new double[n][n];
        
        for (int i=0; i<n; i++) {
            this.elements[i][i] = 1;
        }
    }
    
    public Matrix(double[]... array) {
        if (array.length < 1) { //if there are less than 1 rows
            throw new IllegalArgumentException("Invalid number of rows.");
        } else if (array[0].length < 1) { //if there are less than 1 columns
            throw new IllegalArgumentException("Invalid number of columns.");
        }
        if (!Utils.checkLength(array)) {
            throw new IllegalArgumentException("Matrix has different row lengths.");
        }
        
        this.rows = array.length;
        this.columns = array[0].length;
        this.elements = new double[rows][columns];
        for (int i=0; i<rows; i++) {
            for (int j=0; j<columns; j++) {
                this.elements[i][j] = array[i][j];
            }
        }
    }
    
    public static boolean checkSameSize(Matrix... args) {
        for (int k=1; k<args.length; k++) {
            if (args[0].rows != args[k].rows || args[0].columns != args[k].columns) {
                return false;
            }
        }
        return true;
    }
    
    public static Matrix shell(Matrix matrix) {
        int columns = matrix.columns;
        int rows = matrix.rows;
        return new Matrix(rows, columns);
    }
    
    public static Matrix add(Matrix... args) {
        if (checkSameSize(args)) {
            
            Matrix newmatrix = args[0].getShell();
            
            for (int k=0; k<args.length; k++) {
                for (int j=0; j<args[k].columns; j++) {
                    for (int i=0; i<args[k].rows; i++) {
                        newmatrix.elements[i][j] += args[k].elements[i][j];
                    }
                }
            }
            return newmatrix;
            
        } else {
            return new Matrix(0,0);
        }

    }
    
    public static Matrix negate(Matrix matrix) {
        Matrix newmatrix = matrix.getShell();
        
        for (int j=0; j<matrix.columns; j++) {
            for (int i=0; i<matrix.rows; i++) {
                newmatrix.elements[i][j] = -matrix.elements[i][j];
            }
        }
        return newmatrix;
    }
    
    public static Matrix multSca(Matrix matrix, double... scalar) {
        double c = 1;
        for (int i=0; i<scalar.length; i++) {
            c *= scalar[i];
        }
        
        Matrix newmatrix = matrix.getShell();
        
        if (c == 0) { //if scalar is 0, return the new 0 matrix to save time
            return newmatrix;
        } else if (c == 1) { //if scalar is 1, return the old matrix to save time
            return matrix;
        }
        
        for (int j=0; j<matrix.columns; j++) {
            for (int i=0; i<matrix.rows; i++) {
                newmatrix.elements[i][j] = matrix.elements[i][j] * c;
            }
        }
        return newmatrix;
    }
    
    public static Matrix transpose(Matrix matrix) {
        Matrix newmatrix = new Matrix(matrix.columns, matrix.rows);
        
        for (int j=0; j<matrix.columns; j++) {
            for (int i=0; i<matrix.rows; i++) {
                newmatrix.elements[j][i] = matrix.elements[i][j];
            }
        }
        return newmatrix;
    }
    
    public static Matrix multMat(Matrix... args) {
        Matrix multmatrix = args[0]; //save the leftmost matrix
        for (int k=1; k<args.length; k++) { //multiply the leftmost matrix with each matrix on the right of it
            
            if (multmatrix.columns == args[k].rows) { //if the two matrices can be multiplied
                Matrix newmatrix = new Matrix(multmatrix.rows, args[k].columns); //create the shell of the new matrix
                int n = multmatrix.columns;

                for (int i=0; i<newmatrix.rows; i++) { //matrix multiplication
                    for (int j=0; j<newmatrix.columns; j++) {
                        double component = 0;
                        for (int r=0; r<n; r++) {
                            component += multmatrix.elements[i][r] * args[k].elements[r][j];
                        }
                        newmatrix.elements[i][j] = component;
                    }
                }
                multmatrix = newmatrix; //save the new matrix as the leftmost matrix, and repeat
            } else {
                return new Matrix(0,0); //if multiplication is illegal, return empty matrix
            }
        }
        return multmatrix;
    }
    
    public Matrix getShell() {
        return shell(this);
    }
    public Matrix getTranspose() {
        return transpose(this);
    }
    public double[][] getElements() {
        return elements;
    }
    public double getElement(int i, int j) {
        return elements[i][j];
    }
    public boolean getIsSquare() {
        return this.columns == this.rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }
    
}
