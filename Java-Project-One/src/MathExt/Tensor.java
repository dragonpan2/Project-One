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
public class Tensor implements Dimensional {
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
            for (int j=0; j<elementArray2D[0].length; j++) {
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
    private int getDimLeap(int k) { //Get the leap at the nth dimension
        if (k > dimSizes.length) {
            throw new IndexOutOfBoundsException("Invalid tensor dimension index.");
        }
        
        int prod = 1;
        for (int i=(dimSizes.length - k + 1); i<dimSizes.length; i++) {
            prod *= dimSizes[i];
        }
        return prod;
    }
    private int getElemIndex(int[] n) {
        int leap = 0;
        for (int i=0; i<n.length; i++) {
            leap += getDimLeap(i+1)*n[i];
        }
        return leap;
    }
    private boolean isWithinBounds(int[] n) {
        if (n.length <= dimensions) {
            for (int i=0; i<n.length; i++) {
                if (n[i] >= dimSizes[i]) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }
    public double get(int... n) {
        if (isWithinBounds(n)) {
            int index = getElemIndex(n);
            return elements[index];
        } else {
            throw new IndexOutOfBoundsException("Invalid tensor index.");
        }
    }
    public void set(int[] n, double c) {
        if (isWithinBounds(n)) {
            int index = getElemIndex(n);
            elements[index] = c;
        } else {
            throw new IndexOutOfBoundsException("Invalid tensor index.");
        }
    }
    public void set(double c, int... n) {
        set(n, c);
    }
    @Override
    public String toString() {
        if (dimensions == 0) {
            return "[]";
        } else if (dimensions == 1) {
            String string = new String();
            for (int i=0; i<elements.length; i++) { //
                string += elements[i] + " "; //Separate columns by a space
            }
            return string;
        } else if (dimensions == 2) {
            /*String string = new String();
            for (int i=0; i<elements.length; i++) { //For all elements
                if (i%dimSizes[0] == 0) {
                    string += "\n";
                }
                string += elements[i] + " "; //Separate columns by a space
            }*/
            
            int rows = dimSizes[0];
            int columns = dimSizes[1];
            String string = new String();
            for (int i=0; i<rows; i++) { //For all elements
                for (int j=0; j<columns; j++) {
                    string += get(j,i) + " "; //Separate columns by a space
                }
                string += (i < rows-1) ? "\n" : ""; //Separate rows by a newline
            }
            return string;
        } else {
            return "Tensor of dimension 3 or more";
        }
    }
}
