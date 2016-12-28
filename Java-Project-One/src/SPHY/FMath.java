/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SPHY;

/**
 * Fast math function approximation library
 * @author bowen
 */
public class FMath {
    
    public static final double PI = 3.141592653589793;
    public static final double PI2 = 6.283185307179586;
    public static final double PIh = 1.570796326794897;
    public static final double sqrt2 = 1.414213562373095;
    public static final double E = 2.718281828459045;
                                   
    /* Sine
    5 * 1/30
    5 * 1/600
    5 * 1/1008
    5 * 1/1814400
    See Java Implementation : http://code.metager.de/source/xref/openjdk/jdk7/jdk/src/share/native/java/lang/fdlibm/src/k_sin.c
    Other fast approx : http://krisgarrett.net/papers/l2approx.pdf
    http://lab.polygonal.de/2007/07/18/fast-and-accurate-sinecosine-approximation/
    */
    
    public static double bhaskaraSin(double rad) {
        double s = 1;
        if (rad < 0) {rad = -rad; s = -s;}
        if (rad > PI2) {rad %= PI2;}
        if (rad > PI) {rad %= PI; s = -s;}
        
        double a = rad*(PI-rad);
        double b = 49.34802200544679; //5*PI^2
        
        return s*(16*a)/(b - 4*a);
    }
    public static double bhaskaraCos(double rad) {
        return bhaskaraSin(PIh + rad);
    }
    public static double bhaskaraTan(double rad) {
        return bhaskaraSin(rad)/bhaskaraCos(rad);
    }
    
    
    private static double polynomialSin(double rad) {
        double s = 1;
        if (rad < 0) {rad = -rad; s = -s;}
        if (rad > PI2) {rad %= PI2;}
        if (rad > PI) {rad %= PI; s = -s;}
        double a = 1.273239544735163; // 4/pi
        double b = 0.4052847345693511; // 4/(pi^2)
        
        return s*(a * rad - b * rad * rad);
    }
    public static double polynomialSin2(double rad) {
        double sin = polynomialSin(rad); //get first precision
        if (sin < 0){
            sin = .225 * (sin *-sin - sin) + sin;
        } else {
            sin = .225 * (sin * sin - sin) + sin;
        }
        return sin;
    }
    private static double polynomialCos(double rad) {
        return polynomialSin(PIh + rad);
    }
    public static double polynomialCos2(double rad) {
        return polynomialSin2(PIh + rad);
    }
    public static double polynomialTan(double rad) {
        return polynomialSin(rad)/polynomialCos(rad);
    }
    public static double polynomialTan2(double rad) {
        return polynomialSin2(rad)/polynomialCos2(rad);
    }
    
    public static double taylorAsin(double x) {
        double a1 = x;
        double a2 = x*x;
        double a3 = a2*x;
        double a5 = a2*a3;
        double a7 = a2*a5;
        double a9 = a2*a7;
        
        double c3 = 0.16666666666666666;
        double c5 = 0.075;
        double c7 = 0.044642857142857144;
        double c9 = 0.030381944444444444;
        
//        for (int i=0; i<9; i++) { //get the constants from i
//            double c = FMath.factorial(2*i) / (Math.pow(4, i) * FMath.factorial(i)* FMath.factorial(i) * (2*i+1));
//            System.out.println(c);
//        }
        return a1 + c3*a3 + c5*a5 + c7*a7 + c9*a9;
    }
    public static double taylorAcos(double x) {
        return PIh - taylorAsin(x);
    }
    public static double taylorAtan(double x) {
        double a1 = x;
        double a2 = x*x;
        double a3 = a2*x;
        double a5 = a2*a3;
        double a7 = a2*a5;
        double a9 = a2*a7;
        //add more for more precision
        return a1 - a3/3 + a5/5 - a7/7 + a9/9;
        
    }
    
    
    public static long factorial(int n) {
        long fact = 1; // this  will be the result
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }
    
    public static double pow(double d, double d1) {
        if ((int)d1 < d1) {
            return Math.pow(d, d1);
        }
        switch ((int)d1) {
            case 0:
                return 1;
            case 1:
                return d;
            case 2:
                return d*d;
            case 3:
                return d*d*d;
            case 4:
                return d*d*d*d;
            case 5:
                return d*d*d*d*d;
            case 6:
                return d*d*d*d*d*d;
            case 7:
                return d*d*d*d*d*d*d;
            default:
                return Math.pow(d, d1);
        }
    }
    
    public static double fAbs(double x) {
        if (x < 0) return -x;
        return x;
    }
    
    public static double maxError(double referenceValue, double... testValues) {
        double maxerror = 0;
        for (int i=0; i<testValues.length; i++) {
            double thiserror = fAbs(referenceValue-testValues[i]);
            if (thiserror > maxerror) {
                maxerror = thiserror;
            }
        }
        return maxerror;
    }
    
    public static boolean isWithinBounds(double errorBound, double referenceValue, double... testValues) {
        if (maxError(referenceValue, testValues) > errorBound) {
            return false;
        }
        return true;
    }
    
}
