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
    final private int dimensions;
    final private int[] dimSizes;
    final private int[] dimLeap;
    private double[] elements;
    
    private Tensor(int[] sizeArray, boolean partialClone) {
        dimensions = sizeArray.length;
        dimSizes = sizeArray.clone();
        dimLeap = getDimLeap();
    }
    private Tensor(int[] sizeArray, int totalElements) {
        dimensions = sizeArray.length;
        dimSizes = sizeArray.clone();
        dimLeap = getDimLeap();
        elements = new double[totalElements];
    }
    public Tensor(int[] sizeArray) {
        dimensions = sizeArray.length;
        dimSizes = sizeArray.clone();
        dimLeap = getDimLeap();
        elements = new double[totalElements(sizeArray)];
    }
    public Tensor(int[] sizeArray, double fill) {
        this(sizeArray);
        this.fill(fill);
    }
    protected Tensor(Tensor tensor) {
        dimensions = tensor.dimensions;
        dimSizes = tensor.dimSizes;
        dimLeap = tensor.dimLeap;
        elements = tensor.elements;
    }
    public Tensor(double[] elementArray) {
        this(new int[] {elementArray.length}, true); //Partial clone
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
    final public static Tensor clone(Tensor tensor) {
        Tensor newTensor = new Tensor(tensor.dimSizes, true); //Partial clone
        newTensor.elements = tensor.elements.clone();
        return newTensor;
    }
    final public static Tensor join(Tensor... tensorArray) {
        if (isSameDimensions(tensorArray)) {
            int[] newDimSizes = new int[tensorArray[0].dimensions + 1];
            newDimSizes[0] = tensorArray.length;
            for (int i=0; i<tensorArray[0].dimensions; i++) {
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
    final public static Tensor[] split(Tensor tensor) {
        Tensor[] tensorArray = new Tensor[tensor.dimSizes[0]];
        int[] newDimSizes = new int[tensor.dimensions - 1];
        for (int i=0; i<newDimSizes.length; i++) {
            newDimSizes[i] = tensor.dimSizes[i+1];
        }
        int totalElements = totalElements(newDimSizes);
        
        for (int n=0; n<tensorArray.length; n++) {
            tensorArray[n] = new Tensor(newDimSizes, totalElements);
            int leap = tensor.dimLeap[0];
            tensorArray[n].elements = Arrays.copyOfRange(tensor.elements, n*leap, (n+1)*leap);
        }
        
        return tensorArray;
    }
    final public static Tensor add(Tensor... tensorArray) {
        /*if (isSameDimensions(tensorArray)) {
            Tensor newTensor = Tensor.clone(tensorArray[0]);
            
            for (int n=1; n<tensorArray.length; n++) {
                for (int i=0; i<tensorArray[0].elements.length; i++) {
                    newTensor.elements[i] += tensorArray[n].elements[i];
                }
            }
            
            return newTensor;
        } else */if (isSameSubDimensions(tensorArray)) {
            Tensor newTensor = Tensor.clone(tensorArray[0]);
            
            for (int n=1; n<tensorArray.length; n++) {
                int subLength = tensorArray[n].elements.length;
                for (int i=0; i<tensorArray[0].elements.length; i++) {
                    newTensor.elements[i] += tensorArray[n].elements[i%subLength];
                }
            }
            
            return newTensor;
        } else {
            throw new IndexOutOfBoundsException("Incompatible tensor sizes.");
        }
    }
    final public static Tensor add(Tensor tensor, double c) {
        Tensor newTensor = Tensor.clone(tensor);
        for (int i=0; i<tensor.elements.length; i++) {
            newTensor.elements[i] += c;
        }
        return newTensor;
    }
    final public static Tensor sub(Tensor... tensorArray) {
        if (isSameSubDimensions(tensorArray)) {
            Tensor newTensor = Tensor.clone(tensorArray[0]);
            
            for (int n=1; n<tensorArray.length; n++) {
                int subLength = tensorArray[n].elements.length;
                for (int i=0; i<tensorArray[0].elements.length; i++) {
                    newTensor.elements[i] -= tensorArray[n].elements[i%subLength];
                }
            }
            
            return newTensor;
        } else {
            throw new IndexOutOfBoundsException("Incompatible tensor sizes.");
        }
    }
    final public static Tensor sub(Tensor tensor, double c) {
        Tensor newTensor = Tensor.clone(tensor);
        for (int i=0; i<tensor.elements.length; i++) {
            newTensor.elements[i] -= c;
        }
        return newTensor;
    }
    final public static Tensor prod(Tensor... tensorArray) {
        if (isSameSubDimensions(tensorArray)) {
            Tensor newTensor = Tensor.clone(tensorArray[0]);
            
            for (int n=1; n<tensorArray.length; n++) {
                int subLength = tensorArray[n].elements.length;
                for (int i=0; i<tensorArray[0].elements.length; i++) {
                    newTensor.elements[i] *= tensorArray[n].elements[i%subLength];
                }
            }
            
            return newTensor;
        } else {
            throw new IndexOutOfBoundsException("Incompatible tensor sizes.");
        }
    }
    final public static Tensor prod(Tensor tensor, double c) {
        Tensor newTensor = Tensor.clone(tensor);
        for (int i=0; i<tensor.elements.length; i++) {
            newTensor.elements[i] *= c;
        }
        return newTensor;
    }
    final public static Tensor div(Tensor... tensorArray) {
        if (isSameSubDimensions(tensorArray)) {
            Tensor newTensor = Tensor.clone(tensorArray[0]);
            
            for (int n=1; n<tensorArray.length; n++) {
                int subLength = tensorArray[n].elements.length;
                for (int i=0; i<tensorArray[0].elements.length; i++) {
                    double divisor = tensorArray[n].elements[i%subLength];
                    if (divisor != 0) {
                        newTensor.elements[i] /= divisor;
                    } else {
                        throw new IllegalArgumentException("Divide by zero.");
                    }
                }
            }
            
            return newTensor;
        } else {
            throw new IndexOutOfBoundsException("Incompatible tensor sizes.");
        }
    }
    final public static Tensor div(Tensor tensor, double c) {
        Tensor newTensor = Tensor.clone(tensor);
        for (int i=0; i<tensor.elements.length; i++) {
            if (c != 0) {
                newTensor.elements[i] /= c;
            } else {
                throw new IllegalArgumentException("Divide by zero.");
            }
        }
        return newTensor;
    }
    final public static Tensor neg(Tensor tensor) {
        Tensor newTensor = new Tensor(tensor.dimSizes);
        for (int i=0; i<newTensor.elements.length; i++) {
            newTensor.elements[i] = -tensor.elements[i];
        }
        return newTensor;
    }
    final public static boolean isSameDimensions(Tensor... tensorArray) {
        int[] firstDimSizes = tensorArray[0].dimSizes;
        
        for (int n=1; n<tensorArray.length; n++) {
            if (!Arrays.equals(firstDimSizes, tensorArray[n].dimSizes)) {
                return false;
            }
        }
        return true;
    }
    final public static boolean isSameSubDimensions(Tensor... tensorArray) {
        int[] firstDimSizes = tensorArray[0].dimSizes;
        int firstDimLength = tensorArray[0].dimensions;
        
        
        for (int n=1; n<tensorArray.length; n++) {
            int[] subDimSizes = tensorArray[n].dimSizes;
            int subDimLength = tensorArray[n].dimensions;
            
            if (firstDimLength < subDimLength) {
                return false;
            }
            for (int i=0; i<subDimLength; i++) {
                if (firstDimSizes[firstDimLength-1-i] != subDimSizes[subDimLength-1-i]) {
                    return false;
                }
            }
        }
        return true;
    }
    private static int totalElements(int[] sizeArray) {
        int totalElements = 1;
        for (int i=0; i<sizeArray.length; i++) {
            totalElements *= sizeArray[i];
        }
        return totalElements;
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
    public int getElementsLength() {
        return elements.length;
    }
    public int getDimensions() {
        return dimensions;
    }
    public int[] getDimensionSizes() {
        return dimSizes.clone();
    }
    public int getDimensionSize(int d) {
        if (d > 0 && d <= dimensions) {
            return dimSizes[dimensions-d];
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
    /*private int getDimLeap(int k) { //Get the leap at the nth dimension
        if (k > dimensions) {
            throw new IndexOutOfBoundsException("Invalid tensor dimension index.");
        }
        
        int prod = 1;
        for (int i=(dimensions - k + 1); i<dimensions; i++) {
            prod *= dimSizes[i];
        }
        return prod;
    }*/
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
    @Override
    final public Tensor clone() {
        return Tensor.clone(this);
    }
    final public Tensor getClone() {
        return Tensor.clone(this);
    }
    final public Tensor getNeg() {
        return Tensor.neg(this);
    }
    final public double get(int i) {
        if (i >= 0 && i < elements.length) {
            return elements[i];
        } else {
            throw new IndexOutOfBoundsException("Invalid tensor index.");
        }
    }
    final public void set(int i, double c) {
        if (i >= 0 && i < elements.length) {
            elements[i] = c;
        } else {
            throw new IndexOutOfBoundsException("Invalid tensor index.");
        }
    }
    public void setFrom(Tensor tensor) {
        if (Tensor.isSameDimensions(this, tensor)) {
            elements = tensor.elements;
        } else {
            throw new IndexOutOfBoundsException("Different tensor sizes.");
        }
    }
    public void copyFrom(Tensor tensor) {
        setFrom(tensor.clone());
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
    final public void fill(double c) {
        for (int i=0; i<elements.length; i++) {
            elements[i] = c;
        }
    }
    final public void zero() {
        fill(0);
    }
    final public void negate() {
        for (int i=0; i<elements.length; i++) {
            elements[i] = -elements[i];
        }
    }
    final public void add(Tensor tensor) {
        if (isSameSubDimensions(new Tensor[] {this, tensor})) {
            int subLength = tensor.elements.length;
            for (int i=0; i<elements.length; i++) {
                elements[i] += tensor.elements[i%subLength];
            }
        } else {
            throw new IndexOutOfBoundsException("Incompatible tensor sizes.");
        }
    }
    final public void add(double c) {
        for (int i=0; i<elements.length; i++) {
            elements[i] += c;
        }
    }
    final public void sub(Tensor tensor) {
        if (isSameSubDimensions(new Tensor[] {this, tensor})) {
            int subLength = tensor.elements.length;
            for (int i=0; i<elements.length; i++) {
                elements[i] -= tensor.elements[i%subLength];
            }

        } else {
            throw new IndexOutOfBoundsException("Incompatible tensor sizes.");
        }
    }
    final public void sub(double c) {
        for (int i=0; i<elements.length; i++) {
            elements[i] -= c;
        }
    }
    final public void prod(Tensor tensor) {
        if (isSameSubDimensions(new Tensor[] {this, tensor})) {
            int subLength = tensor.elements.length;
            for (int i=0; i<elements.length; i++) {
                elements[i] *= tensor.elements[i%subLength];
            }

        } else {
            throw new IndexOutOfBoundsException("Incompatible tensor sizes.");
        }
    }
    final public void prod(double c) {
        for (int i=0; i<elements.length; i++) {
            elements[i] *= c;
        }
    }
    final public void div(Tensor tensor) {
        if (isSameSubDimensions(new Tensor[] {this, tensor})) {
            int subLength = tensor.elements.length;
            for (int i=0; i<elements.length; i++) {
                double divisor = tensor.elements[i%subLength];
                if (divisor != 0) {
                    elements[i] /= divisor;
                } else {
                    throw new IllegalArgumentException("Divide by zero.");
                }
            }

        } else {
            throw new IndexOutOfBoundsException("Incompatible tensor sizes.");
        }
    }
    final public void div(double c) {
        for (int i=0; i<elements.length; i++) {             
            if (c != 0) {
                elements[i] /= c;
            } else {
                throw new IllegalArgumentException("Divide by zero.");
            }
        }
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
                if (i%dimSizes[1] == 0) {
                    string += "\n"; //Separate rows by a newline
                }
                string += elements[i] + " "; //Separate columns by a space
            }
            
            return "_\n" + string + "\n_\nTensor of dimension 2";
        } else {
            return "_\n[...]\n_\nTensor of dimension " + dimensions;
        }
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Tensor.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final Tensor other = (Tensor) obj;
        if ((this.elements == null) ? (other.elements != null) : !Arrays.equals(this.elements, other.elements)) {
            return false;
        }
        if ((this.dimSizes == null) ? (other.dimSizes != null) : !Arrays.equals(this.dimSizes, other.dimSizes)) {
            return false;
        }
        return true;
    }
}
