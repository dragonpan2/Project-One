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
    public static Vector add(Vector vector, double c) {
        return new Vector(Tensors.add(vector, c));
    }
    public static Vector sub(Vector... vectorArray) {
        return new Vector(Tensors.sub(vectorArray));
    }
    public static Vector sub(Vector vector, double c) {
        return new Vector(Tensors.sub(vector, c));
    }
    public static Vector prod(Vector... vectorArray) {
        return new Vector(Tensors.prod(vectorArray));
    }
    public static Vector prod(Vector vector, double c) {
        return new Vector(Tensors.prod(vector, c));
    }
    public static Vector div(Vector... vectorArray) {
        return new Vector(Tensors.div(vectorArray));
    }
    public static Vector div(Vector vector, double c) {
        return new Vector(Tensors.div(vector, c));
    }
    public static Vector mult(Matrix matrix, Vector vector) {
        return Tensors.toVector(Matrices.mult(matrix, Vectors.toColumnMatrix(vector)));
    }
    public static Vector transform(Vector vector, Matrix... matrixArray) {
        Vector newVector = vector.clone();
        for (int n=0; n<matrixArray.length; n++) {
            newVector.transform(matrixArray[n]);
        }
        return newVector;
    }
    public static double mult(Vector vector, Vector vector2) {
        if (vector.length() != vector2.length()) {
            throw new IllegalArgumentException("Cannot multiply vectors of different sizes.");
        }
        double sum = 0;
        for (int i=0, length=vector.length(); i<length; i++) {
            sum += vector.get(i) * vector2.get(i);
        }
        return sum;
        //return Matrices.mult(Vectors.toRowMatrix(vector), Vectors.toColumnMatrix(vector2)).get(0, 0); //TODO Implement faster algorithm
    }
    public static Vector cross(Vector vector, Vector vector2) {
        if (vector.length() != 3 || vector2.length() != 3) {
            throw new IllegalArgumentException("Cannot perform cross product on vectors of size other than 3.");
        } else {
            double s0 = vector.get(1)*vector2.get(2) - vector.get(2)*vector2.get(1);
            double s1 = vector.get(2)*vector2.get(0) - vector.get(0)*vector2.get(2);
            double s2 = vector.get(0)*vector2.get(1) - vector.get(1)*vector2.get(0);
            return new Vector(new double[] {s0, s1, s2});
        }
    }
    public static double scalar(Vector vector, Vector vector2) {
        return mult(vector, vector2)/vector2.norm();
    }
    public static Vector proj(Vector vector, Vector vector2) {
        double denom = mult(vector2, vector2);
        if (denom == 0) {
            return vector;
        }
        double scalar = mult(vector, vector2)/denom;
        return prod(vector2, scalar);
    }
    public static Vector rej(Vector vector, Vector vector2) {
        return sub(vector, proj(vector, vector2));
    }
    public static Matrix toRowMatrix(Vector vector) {
        return new Matrix(Tensors.join(vector));
    }
    public static Matrix toColumnMatrix(Vector vector) {
        return Matrices.transpose(Tensors.toMatrix(Tensors.join(vector)));
    }
}
