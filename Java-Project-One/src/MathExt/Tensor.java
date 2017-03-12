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
public class Tensor implements Dimensional {
    private int dimensions;
    private int[] dimSizes;
    private int[] dimLeap;
    private double[] elements;
    
    public Tensor(int[] sizeArray) {
        dimensions = sizeArray.length;
        dimSizes = sizeArray.clone();
        dimLeap = getDimLeap();
        elements = new double[getTotalElements()];
    }
    public Tensor(int[] sizeArray, double fill) {
        this(sizeArray);
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
    public Tensor(double[][][] elementArray3D) {
        this(new int[] {elementArray3D.length, elementArray3D[0].length, elementArray3D[0][0].length});
        int n = 0;
        for (int i=0; i<elementArray3D.length; i++) {
            for (int j=0; j<elementArray3D[0].length; j++) {
                for (int k=0; k<elementArray3D[0][0].length; k++) {
                    elements[n] = elementArray3D[i][j][k];
                    n++;
                }
            }
        }
    }
    public static Tensor join(Tensor... tensorArray) {
        if (sameDimensions(tensorArray)) {
            int[] newDimSizes = new int[tensorArray[0].dimSizes.length + 1];
            newDimSizes[0] = tensorArray.length;
            for (int i=0; i<tensorArray[0].dimSizes.length; i++) {
                newDimSizes[i+1] = tensorArray[0].dimSizes[i];
            }
            Tensor newTensor = new Tensor(newDimSizes);
            
            int n = 0;
            for (int i=0; i<tensorArray.length; i++) {
                for (int j=0; j<tensorArray[0].elements.length; j++) {
                        newTensor.elements[n] = tensorArray[i].elements[j];
                        n++;
                }
            }
            
            return newTensor;
        } else {
            throw new IndexOutOfBoundsException("Tensor of different sizes.");
        }
    }
    private static boolean sameDimensions(Tensor... tensorArray) {
        int[] firstDimSizes = tensorArray[0].dimSizes;
        for (int n=1; n<tensorArray.length; n++) {
            for (int i=0; i<tensorArray[n].dimSizes.length; i++) {
                if (firstDimSizes[i] != tensorArray[n].dimSizes[i]) {
                    return false;
                }
            }
        }
        return true;
    }
    private int[] getDimLeap() {
        int prod = 1;
        int[] dimLeap = new int[dimensions];
        for (int i=dimensions-1; i>=0; i--) {
            dimLeap[i] = prod;
            prod *= dimSizes[i];
        }
        return dimLeap;
    }
    private int getTotalElements() {
        int totalElements = 1;
        for (int i=0; i<dimSizes.length; i++) {
            totalElements *= dimSizes[i];
        }
        return totalElements;
    }
    public int getDimensions() {
        return dimensions;
    }
    public int[] getDimensionSizes() {
        return dimSizes.clone();
    }
    public int getDimensionSize(int d) {
        if (d > 0 && d <= dimensions) {
            return dimSizes[d-1];
        } else {
            throw new IndexOutOfBoundsException("Invalid tensor dimension index.");
        }
    }
    public double getNorm() { //Frobenius norm, generalised as vector norm for higher dimensions.
        if (dimensions < 1) {
            return 0;
        } else {
            double sqrsum = 0;
            for (int i=0; i<elements.length; i++) {
                double elem = elements[i];
                sqrsum += elem * elem;
            }
            return Math.sqrt(sqrsum);
        }
    }
    public double getNorm(int p) { //n-norm as vector norm
        if (p < 1) {
            throw new IllegalArgumentException("Invalid p parameter, must be greater or equal than 1.");
        } else if (dimensions < 1) {
            return 0;
        } else {
            double sqrsum = 0;
            for (int i=0; i<elements.length; i++) {
                double elem = elements[i];
                sqrsum += Math.pow(elem, p);
            }
            return Math.pow(sqrsum, 1/p);
        }
        
    }
    public double getMaxNorm() { //returns max norm, where p = inf
        double max = elements[0];
        for (int i=1; i<elements.length; i++) {
            double elem = elements[i];
            if (max < elem) {
                max = elem;
            }
        }
        return max;
    }
    public double getNorm(int p, int q) { //Implement PQ norm
        return 0;
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
        if (n.length < dimensions) {
            throw new IndexOutOfBoundsException("Invalid arguments, the number of index arguments must be equal to the number of dimensions.");
        }
        int leap = 0;
        for (int i=0; i<n.length; i++) {
            leap += dimLeap[i]*n[i];
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
    public final void fill(double c) {
        for (int i=0; i<elements.length; i++) {
            elements[i] = c;
        }
    }
    public final void zero() {
        fill(0);
    }
    @Override
    public String toString() {
        if (dimensions == 0) {
            return "[]\nTensor of dimension 0";
        } else if (dimensions == 1) {
            String string = new String();
            for (int i=0; i<elements.length; i++) { //
                string += elements[i] + " "; //Separate columns by a space
            }
            return "_\n" + string + "\n_\nTensor of dimension 1";
        } else if (dimensions == 2) {
            String string = new String();
            string += elements[0] + " ";
            for (int i=1; i<elements.length; i++) { //For all elements
                if (i%dimSizes[0] == 0) {
                    string += "\n"; //Separate rows by a newline
                }
                string += elements[i] + " "; //Separate columns by a space
            }
            
            return "_\n" + string + "\n_\nTensor of dimension 2";
        } else {
            return "_\n[...]\n_\nTensor of dimension " + dimensions;
        }
    }
}
