/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackages;

import java.util.Arrays;
import SLAG.Matrix;
import SLAG.Vector;

/**
 *
 * @author Lin-Li
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Window window = new Window();
       
       double[][] testarray = {{1,2,3},{4,5,6}};
       double[][] testarray2 = {{11,3},{7,11}};
       double[][] testarray3 = {{8,0,1},{0,3,5}};
       double[][] testarray4 = {{5,2},{1,9},{6,5}};
       Matrix testmatrix = new Matrix(testarray);
       Matrix testmatrix2 = Matrix.add(testmatrix, testmatrix);
       Matrix testmatrix3 = Matrix.multSca(testmatrix, 1, 2, 10);
       Matrix testmatrix4 = Matrix.transpose(testmatrix);
       
       Matrix testmatrix5 = new Matrix(testarray2);
       Matrix testmatrix6 = new Matrix(testarray3);
       Matrix testmatrix7 = new Matrix(testarray4);
       Matrix testmatrix8 = Matrix.multMat(testmatrix5, testmatrix6, testmatrix7); //good answer is [605 387][685 719]
       
       
       double[] testarray5 = {6,5,8};
       double[] testarray6 = {2,1,0};
       
       Vector testvector1 = new Vector(testarray5);
       Vector testvector2 = new Vector(testarray6);
       Vector testvector3 = Vector.add(testvector1, testvector2);
       double prodsca = Vector.prodSca(testvector1, testvector2);
       
       
       System.out.println(Arrays.deepToString(testmatrix2.getElements()));
       System.out.println(Arrays.deepToString(testmatrix3.getElements()));
       System.out.println(Arrays.deepToString(testmatrix4.getElements()));
       System.out.println(Arrays.deepToString(testmatrix8.getElements()));
       System.out.println(Arrays.deepToString(testvector3.getElements()));
       System.out.println(prodsca);
       
       
       
    }
    
}
