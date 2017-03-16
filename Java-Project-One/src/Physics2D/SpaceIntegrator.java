/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Physics2D;

import MathExt.Statistics;
import Physics2D.Objects.PointBody;

/**
 *
 * @author bowen
 */
public class SpaceIntegrator {
    final public static double G = 6.67408E-11; //m^3 * kg^-1 * s^-2 Gravitational Constant
    final public static double tp = 5.39116E-44; //Planck time
    final private static int stepsLength = 40;
    private static int stepsSum = 0;
    private static int stepsCount = 0;
    
    private PointBody[] objects;
    private double momentumSum;
    
    public SpaceIntegrator(PointBody... objects) {
        this.objects = objects;
        momentumSum = getMomentumSum(objects);
    }
    
    public static Vector2 getForce(PointBody other, PointBody target) {
        Vector2 forceVector = Vectors2.sub(target.position(), other.position());
        double normSquared = forceVector.normSquared();
        forceVector.setNorm(1);
        double scalar = (-G * other.mass() * target.mass())/normSquared;
        forceVector.prod(scalar);
        return forceVector;
    }
    
    public static void computeForces(PointBody[] objects) {
        for (int i=0; i<objects.length; i++) {
            Vector2[] forceVectors = new Vector2[objects.length];
            for (int n=0; n<objects.length; n++) {
                if (i != n) {
                    forceVectors[n] = getForce(objects[n], objects[i]);
                } else {
                    forceVectors[n] = new Vector2(0);
                }
            }
            Vector2 sumForces = Vectors2.add(forceVectors);
            objects[i].setForce(sumForces);
        }
    }
    
    public static void computeMotion(PointBody[] objects, double time) {
        for (int i=0; i<objects.length; i++) {
            objects[i].update(time);
        }
    }
    
    private static double getMomentumSum(PointBody[] objects) {
        double xSum = 0;
        double ySum = 0;
        
        for (int i=0; i<objects.length; i++) {
            xSum += objects[i].momentum(0);
            ySum += objects[i].momentum(1);
        }
        return Statistics.norm(xSum, ySum);
    }
    
    private static double getFutureMomentumSum(PointBody[] objects, double time) {
        double xSum = 0;
        double ySum = 0;
        
        for (int i=0; i<objects.length; i++) {
            xSum += objects[i].futureMomentum(0, time);
            ySum += objects[i].futureMomentum(1, time);
        }
        return Statistics.norm(xSum, ySum);
    }
    
    private double getMomentumSum() {
        return getMomentumSum(objects);
    }
    public void update(double time) {
        update(time, 2000);
    }
    public void update(double time, double precision) {
        if (precision < 0.1) {
            precision = 0.1;
        }
        
        int steps = 0;
        
        double futureMomentum = getFutureMomentumSum(objects, time);
        
        if (Math.abs(futureMomentum - momentumSum) < precision) {
            computeForces(objects);
            computeMotion(objects, time);
            steps++;
        } else {
            double remainingTime = time;
            while(remainingTime > 0) {
                computeForces(objects);
                
                futureMomentum = getFutureMomentumSum(objects, remainingTime);
                
                if (Math.abs(futureMomentum - momentumSum) > precision) {
                    double newTime = remainingTime;
                    while(Math.abs(futureMomentum - momentumSum) > precision) {
                        newTime /= 2;
                        futureMomentum = getFutureMomentumSum(objects, newTime);
                        //System.out.println(Math.abs(newTime));
                        //System.out.println(Math.abs(momentumSum - futureMomentum));
                        if (newTime <= 1E-12) {
                            newTime = 1E-12;
                            break;
                        }
                    }
                    computeMotion(objects, newTime);
                    remainingTime -= newTime;
                } else {
                    computeMotion(objects, remainingTime);
                    remainingTime = 0;

                }

                steps++;
                momentumSum = futureMomentum;
                
            }
            
        }
        
        printLoad(steps);
        
        
    }
    
    
    public static void printLoad(int steps) {
        stepsSum += steps;
        stepsCount++;
        if (stepsCount >= stepsLength) {
            stepsCount = 0;
            //System.out.println(stepsSum);
            int graphLength = (int)(stepsSum/40);
            if (graphLength > 25) {
                printRed(graphLength);
            } else if (graphLength > 10) {
                printYellow(graphLength);
            } else if (stepsSum > 40) {
                printGreen(graphLength);
            } else {
                printCyan();
            }
            stepsSum = 0;
        }
        
    }
    public static void printRed(int length) {
        for (int i=0; i<length; i++) {
            System.out.print((char)27 + "[31;40m█");
        }
        System.out.println("");
    }
    public static void printYellow(int length) {
        for (int i=0; i<length; i++) {
            System.out.print((char)27 + "[33;40m█");
        }
        System.out.println("");
    }
    public static void printGreen(int length) {
        for (int i=0; i<length; i++) {
            System.out.print((char)27 + "[32;40m█");
        }
        System.out.println("");
    }
    public static void printCyan() {
        System.out.println((char)27 + "[36;40m█");
    }
}
