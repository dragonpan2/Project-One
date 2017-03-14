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
public abstract class Fast {
    
    public static final double PI = 3.141592653589793;
    public static final double PI2 = 6.283185307179586; // pi*2
    public static final double PIO2 = 1.570796326794897; // pi/2
    public static final double PIO4 = 0.7853981633974483096e0; // pi/4
    public static final double SQRT2 = 1.414213562373095;
    public static final double E = 2.718281828459045;
    
    //private static final double PIO2 = 1.5707963267948966135e0;
    //private static final double PIX2 = 6.2831853071795864766e0;
    //private static final double PIX4 = 12.566370614359172953e0;
    private static final double PIO180 = 0.01745329251994329e0; //PI divided by 180
    private static final double PIU180 = 57.2957795130823208e0; //180 divided by PI
    

    private static double mxatan(double d) { //taylor approx
        double p4 = .161536412982230228262e2;
        double p3 = .26842548195503973794141e3;
        double p2 = .11530293515404850115428136e4;
        double p1 = .178040631643319697105464587e4;
        double p0 = .89678597403663861959987488e3;
        
        double q4 = .5895697050844462222791e2;
        double q3 = .536265374031215315104235e3;
        double q2 = .16667838148816337184521798e4;
        double q1 = .207933497444540981287275926e4;
        double q0 = .89678597403663861962481162e3;
        
        double asq = d * d;
        double value = ((((p4 * asq + p3) * asq + p2) * asq + p1) * asq + p0);
        value = value / (((((asq + q4) * asq + q3) * asq + q2) * asq + q1) * asq + q0);
        return value * d;
    }


    private static double msatan(double a) {
        double sq2p1 = 2.414213562373095048802e0;
        double sq2m1 = .414213562373095048802e0;
        if (a < sq2m1) {
            return mxatan(a);
        } else if (a > sq2p1) {
            return PIO2 - mxatan(1/a);
        } else {
            return PIO4 + mxatan((a - 1)/(a + 1));
        }
    }

    public static double atan(double a) {
        return a > 0 ? msatan(a) : -msatan(-a);
    }

    public static double atan2(double y, double x) {
        if (y + x == y) return y >= 0 ? PIO2 : -PIO2;
        y = atan(y / x);
        return x < 0 ? y <= 0 ? y + PI : y - PI : y;
    }
    
    public static final double toDegrees(double radians) {
        return radians * PIU180;
    }
    public static final double toRadians(double degrees) {
        return degrees * PIO180;
    }
    
    
    public static float sqrt(float f) {
        final float xhalf = f * 0.5F;
        float y = Float.intBitsToFloat(0x5f375a86 - (Float.floatToIntBits(f) >> 1)); // evil floating point bit level hacking -- Use 0x5f375a86 instead of 0x5f3759df, due to slight accuracy increase. (Credit to Chris Lomont)
        y = y * (1.5F - (xhalf * y * y)); 	// Newton step, repeating increases accuracy
        y = y * (1.5F - (xhalf * y * y));
        return f * y;
    }
    
    public static float invSqrt(float f) {
        f = Float.intBitsToFloat(0x5f375a86 - (Float.floatToIntBits(f) >> 1)); // evil floating point bit level hacking -- Use 0x5f375a86 instead of 0x5f3759df, due to slight accuracy increase. (Credit to Chris Lomont)
        f = f * (1.5F - (0.5F * f * f * f)); 	// Newton step
        return -f;
    }

    public int pow(int base, int exp)
    {
        int result = 1;
        while (exp != 0)
        {
            if ((exp & 1) == 1)
                result *= base;
            exp >>= 1;
            base *= base;
        }

        return result;
    }
    public long pow(long base, long exp)
    {
        long result = 1;
        while (exp != 0)
        {
            if ((exp & 1) == 1)
                result *= base;
            exp >>= 1;
            base *= base;
        }

        return result;
    }
    
    public static double maxError(double referenceValue, double... testValues) {
        double maxerror = 0;
        for (int i=0; i<testValues.length; i++) {
            double thiserror = Math.abs(referenceValue-testValues[i]);
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
