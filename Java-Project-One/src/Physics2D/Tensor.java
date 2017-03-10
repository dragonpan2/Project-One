/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Physics2D;

/**
 *
 * @author bowen
 */
public class Tensor implements Geometric {
    private int dimensions;
    private int[] dimSizes;
    private double[] elements;
    
    private int getTotalElements() {
        int totalElements = 1;
        for (int i=0; i<dimSizes.length; i++) {
            totalElements *= dimSizes[i];
        }
        return totalElements;
    }
    public Tensor(int[] sizeArray) {
        dimensions = sizeArray.length;
        dimSizes = sizeArray.clone();
        elements = new double[getTotalElements()];
    }
    public Tensor(int[] dimSizes, double fill) {
        this(dimSizes);
        this.fill(fill);
    }
    public Tensor(double[] elementArray) {
        this(new int[] {elementArray.length});
        elements = elementArray.clone();
    }
    public Tensor(double[][] elementArray2D) {
        this(new int[] {elementArray2D.length, elementArray2D[0].length});
        int n = 0;
        for (int i=0; i<elementArray2D.length; i++) {
            for (int j=0; j<elementArray2D[0].length; i++) {
                elements[n] = elementArray2D[i][j];
                n++;
            }
        }
    }
    public final void fill(double c) {
        for (int i=0; i<elements.length; i++) {
            elements[i] = c;
        }
    }
    public int getDimensions() {
        return dimensions;
    }
    public int getDimensionSize(int d) {
        return dimSizes[d-1];
    }
    public double getNorm() {
        if (dimensions == 1) {
            return 0; //TODO
        } else if (dimensions == 2) {

            int rows = dimSizes[0];
            int columns = dimSizes[1];
            double sqrsum = 0;
            for (int i=0; i<rows; i++) { //SIMPLIFY USING ONE FOR
                for (int j=0; j<columns; j++) {
                    double elem = get(i,j);
                    sqrsum += elem * elem;
                }          
            }
            return Math.sqrt(sqrsum);

        } else {
            return 0;
        }
    }
    
    public double get(int i, int j) {
        int m = dimSizes[0];
        int n = dimSizes[1];
        if (i<m && j<n) {
            return elements[i*n+j];
        } else {
            throw new IndexOutOfBoundsException("Indexe en dehors de la matrice.");
        }
    }
}
