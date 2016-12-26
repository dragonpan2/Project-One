/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackages;

import java.util.Arrays;

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
       Matrix testmatrix = new Matrix(testarray);
       Matrix testmatrix2 = Matrix.add(testmatrix, testmatrix);
       Matrix testmatrix3 = Matrix.multSca(testmatrix, 1, 2, 10);
       Matrix testmatrix4 = Matrix.transpose(testmatrix);
       
       
       System.out.println(Arrays.deepToString(testmatrix.getComponents()));
       System.out.println(Arrays.deepToString(testmatrix2.getComponents()));
       System.out.println(Arrays.deepToString(testmatrix3.getComponents()));
       System.out.println(Arrays.deepToString(testmatrix4.getComponents()));
       
       
       
    }
    
}
