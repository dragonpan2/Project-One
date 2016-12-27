/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackages;

/**
 *
 * @author bowen
 */
public class Matrix {
    private double[][] components;
    private int rows, columns;
    
    public Matrix(int diagSize) {
        this.rows = diagSize;
        this.columns = diagSize;
        this.components = new double[diagSize][diagSize];
        
        for (int n=0; n<diagSize; n++) {
            this.components[n][n] = 1;
        }
        
    }
    
    public Matrix(int rows, int columns) {
        this.components = new double[rows][columns];
        this.rows = rows;
        this.columns = columns;
    }
    
    
    public Matrix(double[]... args) {
        if (args.length < 1) { //if there are less than 1 columns
            return;
        } else if (args[0].length < 1) { //if there are less than 1 rows
            return;
        }
        for (int i=1; i<args.length; i++) { //check if all rows are of the same dimension
            if (args[i-1].length != args[i].length) {
                return;
            }
        }
        this.rows = args.length;
        this.columns = args[0].length;
        this.components = new double[rows][columns];
        for (int i=0; i<args.length; i++) {
            this.components[i] = args[i];
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
                        newmatrix.components[i][j] += args[k].components[i][j];
                    }
                }
            }
            return newmatrix;
            
        } else {
            return new Matrix(0,0);
        }

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
                newmatrix.components[i][j] = matrix.components[i][j] * c;
            }
        }
        return newmatrix;
    }
    
    public static Matrix transpose(Matrix matrix) {
        Matrix newmatrix = new Matrix(matrix.columns, matrix.rows);
        
        for (int j=0; j<matrix.columns; j++) {
            for (int i=0; i<matrix.rows; i++) {
                newmatrix.components[j][i] = matrix.components[i][j];
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
                            component += multmatrix.components[i][r] * args[k].components[r][j];
                        }
                        newmatrix.components[i][j] = component;
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
    public double[][] getComponents() {
        return components;
    }
    public double getComponent(int i, int j) {
        return components[i][j];
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
