/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Physics2D;

import Physics2D.Objects.Body;

/**
 *
 * @author bowen
 */
public class SpaceIntegrator {
    final public static double G = 6.67408E-11; //m^3 * kg^-1 * s^-2
    final private static int stepsLength = 40;
    private static int stepsSum = 0;
    private static int stepsCount = 0;
    
    private Body[] objects;
    private double momentumSum;
    
    public SpaceIntegrator(Body... objects) {
        this.objects = objects;
        momentumSum = getMomentumSum(objects);
    }
    
    public static Vector2 getForce(Body other, Body target) {
        Vector2 forceVector = Vectors2.sub(target.position(), other.position());
        double normSquared = forceVector.normSquared();
        forceVector.setNorm(1);
        double scalar = (-G * other.mass() * target.mass())/normSquared;
        forceVector.prod(scalar);
        return forceVector;
    }
    
    private static double getMomentumSum(Body[] objects) {
        double sum = 0;
        for (int i=0; i<objects.length; i++) {
            sum += objects[i].momentum();
        }
        return sum;
    }
    
    private double getMomentumSum() {
        return getMomentumSum(objects);
    }
    public void update(double time) {
        update(time, 1E20);
    }
    
    public void update(double time, double precision) {
        int steps = 0;
        double remainingTime = time;
        while (remainingTime > 0) {
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

            for (int i=0; i<objects.length; i++) {
                objects[i].update(remainingTime);
            }

            double newMomentum = getMomentumSum();
            if (Math.abs(momentumSum - newMomentum) > precision) {
                
                
                double newTime = remainingTime;
                while (Math.abs(momentumSum - newMomentum) > precision) {
                    //System.out.println(Math.abs(momentumSum - newMomentum));
                    for (int i=0; i<objects.length; i++) {
                        objects[i].revert();
                    }
                    newTime /= 2;
                    //System.out.println(newTime);
                    for (int i=0; i<objects.length; i++) {
                        objects[i].update(newTime);
                    }
                    newMomentum = getMomentumSum();
                }
                remainingTime -= newTime;
                steps++;
                //System.out.print(newTime + "|");
                //System.out.println("|Ministep, remaining time: " + remainingTime + "|");
            } else {
                steps++;
                remainingTime = 0;
            }
            momentumSum = newMomentum;
        }
        //System.out.println("Ministeps: |" + steps + "|");
        printLoad(steps);
    }
    
    public static void printLoad(int steps) {
        stepsSum += steps;
        stepsCount++;
        if (stepsCount > stepsLength) {
            stepsCount = 0;
            stepsSum /= 2500;
            int graphLength = (int)stepsSum;
            //System.out.println(stepsSum);
            if (graphLength > 25) {
                printRed(graphLength);
            } else if (graphLength > 10) {
                printYellow(graphLength);
            } else if (graphLength > 1) {
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
