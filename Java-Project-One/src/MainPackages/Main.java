/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackages;

import java.util.Arrays;
import SLAG.Matrix;
import SLAG.Vector;
import SPHY.FMath;
import SPHY.Vector2;

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
//       
////       double x = -3*Math.PI;
////       while (x < 3*Math.PI) {
////           double bhas = FMath.bhaskaraSin(x);
////           double poly1 = FMath.polynomialSin(x);
////           double poly2 = FMath.polynomialSin2(x);
////           double math = Math.sin(x);
////           //System.out.println((float)fast + " | " + (float)math);
////           System.out.println(FMath.maxError(math, poly2));
////           x += Math.PI/4;
////       }
//
////       double x = -1;
////       while (x < 1) {
////           double tayl = FMath.taylorAsin(x);
////           double math = Math.acos(x);
////           //System.out.println((float)fast + " | " + (float)math);
////           System.out.println(math);
////           //System.out.println(FMath.maxError(math, tayl));
////           x += Math.PI/20;
////       }
//
//        
//        Vector2 vec = new Vector2(1,2);
//        Vector2 vec2 = new Vector2(6,7);
//        Vector2.useFastMath = false;
//        
//       double x = 0;
//       while (x < 10) {
//           double fast = FMath.approxSqrt((float)x);
//           double math = Math.sqrt(x);
//           //System.out.println((float)fast + " | " + (float)math);
//           System.out.print(FMath.maxError(math, fast) + " ");
//           x += 1;
//       }
//           System.out.println("-------------");
//       x = -Math.PI/2+0.5;
//       while (x < Math.PI/2-0.5) {
//           double fast = FMath.quickAtan(x);
//           double math = Math.atan(x);
//           //System.out.println((float)fast + " | " + (float)math);
//           System.out.print(FMath.maxError(math, fast) + " ");
//           x += Math.PI/10;
//       }
//           System.out.println("-------------");
//       
//           
//        long startTime = System.currentTimeMillis();
//        for (long i=0; i<2000000; i+=1) {
//            vec.getProjVec(vec2);
//        }
//        long finishTime = System.currentTimeMillis();
//        System.out.println("That took: "+(finishTime-startTime)+ " ms");
//        
//        
//        Vector2.useFastMath = true;
//        startTime = System.currentTimeMillis();
//        for (long i=0; i<2000000; i+=1) {
//            vec.getProjVec(vec2);
//        }
//        finishTime = System.currentTimeMillis();
//        System.out.println("That took: "+(finishTime-startTime)+ " ms");
//
//       double[][] testarray = {{1,2,3},{4,5,6}};
//       double[][] testarray2 = {{11,3},{7,11}};
//       double[][] testarray3 = {{8,0,1},{0,3,5}};
//       double[][] testarray4 = {{5,2},{1,9},{6,5}};
//       Matrix testmatrix = new Matrix(testarray);
//       Matrix testmatrix2 = Matrix.add(testmatrix, testmatrix);
//       Matrix testmatrix3 = Matrix.multSca(testmatrix, 1, 2, 10);
//       Matrix testmatrix4 = Matrix.transpose(testmatrix);
//       
//       Matrix testmatrix5 = new Matrix(testarray2);
//       Matrix testmatrix6 = new Matrix(testarray3);
//       Matrix testmatrix7 = new Matrix(testarray4);
//       Matrix testmatrix8 = Matrix.multMat(testmatrix5, testmatrix6, testmatrix7); //good answer is [605 387][685 719]
//       
//       
//       double[] testarray5 = {6,5,8};
//       double[] testarray6 = {2,1,0};
//       
//       Vector testvector1 = new Vector(testarray5);
//       Vector testvector2 = new Vector(testarray6);
//       Vector testvector3 = Vector.add(testvector1, testvector2);
//       Vector testvector4 = testvector3.getClone();
//       testvector4.setMult(2);
//       double prodsca = Vector.prodSca(testvector1, testvector2);
//       
//       
//       System.out.println(Arrays.deepToString(testmatrix2.getElements()));
//       System.out.println(Arrays.deepToString(testmatrix3.getElements()));
//       System.out.println(Arrays.deepToString(testmatrix4.getElements()));
//       System.out.println(Arrays.deepToString(testmatrix8.getElements()));
//       System.out.println(Arrays.deepToString(testvector3.getElements()));
//       System.out.println(Arrays.deepToString(testvector4.getElements()));
//       System.out.println(prodsca);
//       
//       
       
    }
    
}
