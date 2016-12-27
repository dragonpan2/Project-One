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
public class Utils {
    
    public static double[][] copy(double[]... array) {
        double[][] newArray = new double[array.length][];
        for (int i=0; i<array.length; i++) {
            newArray[i] = array[i].clone();
        }
        
        return new double[0][0];
    } 
    public static double[] copy(double[] array) {
        double[] newArray = array.clone();
        return newArray;
    } 
    
    
    public static boolean checkLength(double[]... array) { //checks if all rows are of the same dimension  
        for (int i=1; i<array.length; i++) { 
            if (array[0].length != array[i].length) {
                return false;
            }
        }
        return true;
    }
    
}
