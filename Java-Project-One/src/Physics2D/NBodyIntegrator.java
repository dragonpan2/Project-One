/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Physics2D;

import MathExt.Algebra.Vectors;
import MathExt.Statistics;
import Physics2D.Objects.PointBody;

/**
 *
 * @author bowen
 */
public class NBodyIntegrator {
    final public static double G = 6.67408E-11; //m^3 * kg^-1 * s^-2 Gravitational Constant
    final public static double tp = 5.39116E-44; //Planck time
    final private static int stepsLength = 40;
    private static int stepsSum = 0;
    private static int stepsCount = 0;
    
    public enum Integrator {
        NOFORCE, EXPLICITEULER, SEMIIMPLICITEULER, EXPLICITMIDPOINT, LEAPFROG, RK4
    }
    
    private PointBody[] objects;
    private Vector2[] leapFrogCurrentAccel;
    private double momentumSum;
    private double energySum;
    
    public NBodyIntegrator(PointBody... objects) {
        this.objects = objects;
        momentumSum = getMomentumSum(objects);
        energySum = getEnergySum(objects);
    }
    
    public static Vector2 getForce(PointBody other, PointBody target) {
        Vector2 forceVector = Vectors2.sub(target.position(), other.position());
        double normSquared = forceVector.normSquared();
        forceVector.setNorm(1);
        double scalar = (-G * other.mass() * target.mass())/normSquared;
        forceVector.prod(scalar);
        return forceVector;
    }
    public static Vector2 getAcceleration(PointBody other, PointBody target) {
        Vector2 accelerationVector = Vectors2.sub(target.position(), other.position());
        double normSquared = accelerationVector.normSquared();
        accelerationVector.setNorm(1);
        double scalar = (-G * other.mass())/normSquared;
        accelerationVector.prod(scalar);
        return accelerationVector;
    }
    public static Vector2 getAcceleration(PointBody[] objects, int i) {
        Vector2[] accelerationVectors = new Vector2[objects.length];
        for (int n=0; n<objects.length; n++) {
            if (i != n) {
                accelerationVectors[n] = getAcceleration(objects[n], objects[i]);
            } else {
                accelerationVectors[n] = new Vector2(0);
            }
        }
        return Vectors2.add(accelerationVectors);
    }
    public static double getPotentialEnergy(PointBody other, PointBody target) {
        double distance = Vectors2.sub(target.position(), other.position()).norm();
        return (-G * other.mass() * target.mass())/distance;
    }
    
    public static Vector2[] computeForces(PointBody[] objects) {
        Vector2[] objectForces = new Vector2[objects.length];
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
            objectForces[i] = sumForces;
            //objects[i].setForce(sumForces);
        }
        return objectForces;
    }
    public static Vector2[] computeAccelerations(PointBody[] objects) {
        Vector2[] objectAccelerations = new Vector2[objects.length];
        for (int i=0; i<objects.length; i++) {
            Vector2[] accelerationVectors = new Vector2[objects.length];
            for (int n=0; n<objects.length; n++) {
                if (i != n) {
                    accelerationVectors[n] = getAcceleration(objects[n], objects[i]);
                } else {
                    accelerationVectors[n] = new Vector2(0);
                }
            }
            Vector2 sumAccelerations = Vectors2.add(accelerationVectors);
            objectAccelerations[i] = sumAccelerations;
        }
        return objectAccelerations;
    }
    /*
    public static void computeMotion(PointBody[] objects, double time) {
        for (int i=0; i<objects.length; i++) {
            objects[i].update(time);
        }
    }*/
    
    protected void integrateRK4(double time) {
        // try http://web.mit.edu/pkrein/Public/Final%20Paper%20UW324.pdf
        final double H = time;
        final double HO2 = H/2;
        final double HO6 = H/6;
        
        Vector2[] currentVelocities = new Vector2[objects.length];
        Vector2[] currentPositions = new Vector2[objects.length];
        Vector2[] vk1 = new Vector2[objects.length];
        Vector2[] vk2 = new Vector2[objects.length];
        Vector2[] vk3 = new Vector2[objects.length];
        Vector2[] vk4 = new Vector2[objects.length];
        Vector2[] rk1 = new Vector2[objects.length];
        Vector2[] rk2 = new Vector2[objects.length];
        Vector2[] rk3 = new Vector2[objects.length];
        Vector2[] rk4 = new Vector2[objects.length];
        
        
        for (int i=0; i<objects.length; i++) {
            currentVelocities[i] = objects[i].velocity().clone();
            currentPositions[i] = objects[i].position().clone();
        }
        
            vk1 = computeAccelerations(objects);
        for (int i=0; i<objects.length; i++) {
            rk1[i] = currentVelocities[i].clone();
        }
        
        for (int i=0; i<objects.length; i++) {
            objects[i].setPosition(Vectors2.add(currentPositions[i], Vectors2.prod(rk1[i], HO2)));
        }
            vk2 = computeAccelerations(objects);
            
        for (int i=0; i<objects.length; i++) {
            rk2[i] = Vectors2.add(currentVelocities[i], Vectors2.prod(vk1[i], HO2));
        }
        
        
        for (int i=0; i<objects.length; i++) {
            objects[i].setPosition(Vectors2.add(currentPositions[i], Vectors2.prod(rk2[i], HO2)));
        }
            vk3 = computeAccelerations(objects);
            
        for (int i=0; i<objects.length; i++) {
            rk3[i] = Vectors2.add(currentVelocities[i], Vectors2.prod(vk2[i], HO2));
        }
        
        
        for (int i=0; i<objects.length; i++) {
            objects[i].setPosition(Vectors2.add(currentPositions[i], Vectors2.prod(rk3[i], H)));
        }
            vk4 = computeAccelerations(objects);
            
        for (int i=0; i<objects.length; i++) {
            rk4[i] = Vectors2.add(currentVelocities[i], Vectors2.prod(vk3[i], H));
        }
        
        
        for (int i=0; i<objects.length; i++) {
            objects[i].setVelocity(Vectors2.add(currentVelocities[i], Vectors2.prod(Vectors2.add(vk1[i], Vectors2.prod(vk2[i], 2), Vectors2.prod(vk3[i], 2), vk4[i]), HO6)));
            objects[i].setPosition(Vectors2.add(currentPositions[i], Vectors2.prod(Vectors2.add(rk1[i], Vectors2.prod(rk2[i], 2), Vectors2.prod(rk3[i], 2), rk4[i]), HO6)));
        }
    }
    
    protected void integrateLeapFrog(double time) {
        if (leapFrogCurrentAccel == null) {
            leapFrogCurrentAccel = computeAccelerations(objects);
        } else if (leapFrogCurrentAccel.length != objects.length) {
            leapFrogCurrentAccel = computeAccelerations(objects);
        }
        Vector2[] velocityHalfArray = new Vector2[objects.length];
        
        for (int i=0; i<objects.length; i++) {
            Vector2 velocityHalf = Vectors2.add(objects[i].velocity(), Vectors2.prod(leapFrogCurrentAccel[i], time/2));
            objects[i].addPosition(Vectors2.prod(velocityHalf, time));
            velocityHalfArray[i] = velocityHalf;
        }
        
        leapFrogCurrentAccel = computeAccelerations(objects);
        
        for (int i=0; i<objects.length; i++) {
            objects[i].setVelocity(Vectors2.add(velocityHalfArray[i], Vectors2.prod(leapFrogCurrentAccel[i], time/2)));
        }
    }
    
    protected void integrateExplicitMidpoint(double time) {
        Vector2[] positionHalfArray = new Vector2[objects.length];
        
        for (int i=0; i<objects.length; i++) {
            positionHalfArray[i] = Vectors2.prod(objects[i].velocity(), time/2);
            objects[i].addPosition(positionHalfArray[i]);
        }
        Vector2[] accelerationHalfArray = computeAccelerations(objects); //Compute the midPoint acceleration
        
        for (int i=0; i<objects.length; i++) {
            objects[i].addPosition(Vectors2.neg(positionHalfArray[i]));
        }
        
        for (int i=0; i<objects.length; i++) {
            Vector2 newVelocity = Vectors2.add(objects[i].velocity(), Vectors2.prod(accelerationHalfArray[i], time));
            objects[i].addPosition(Vectors2.prod(Vectors2.add(objects[i].velocity(), newVelocity), time/2));
            objects[i].setVelocity(newVelocity);
        }
    }
    
    protected void integrateSemiImplicitEuler(double time) {
        Vector2[] currentAccel = computeAccelerations(objects);
        
        for (int i=0; i<objects.length; i++) {
            objects[i].addVelocity(Vectors2.prod(currentAccel[i], time));
            objects[i].addPosition(Vectors2.prod(objects[i].velocity(), time));
        }
    }
    
    protected void integrateExplicitEuler(double time) {
        Vector2[] currentAccel = computeAccelerations(objects);
        
        for (int i=0; i<objects.length; i++) {
            objects[i].addPosition(Vectors2.prod(objects[i].velocity(), time));
            objects[i].addVelocity(Vectors2.prod(currentAccel[i], time));
        }
    }
    
    protected void integrateNoForce(double time) {
        for (int i=0; i<objects.length; i++) {
            objects[i].addPosition(Vectors2.prod(objects[i].velocity(), time));
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
    
    private static double getEnergySum(PointBody[] objects) {
        double sum = 0;
        for (int i=0; i<objects.length; i++) {
            sum += objects[i].kineticEnergy();
            for (int n=0; n<objects.length; n++) {
                if (i != n) {
                    sum += getPotentialEnergy(objects[n], objects[i]);
                }
            }
        }
        return sum;
    }
    /*
    private static double getFutureMomentumSum(PointBody[] objects, double time) {
        double xSum = 0;
        double ySum = 0;
        
        for (int i=0; i<objects.length; i++) {
            xSum += objects[i].futureMomentum(0, time);
            ySum += objects[i].futureMomentum(1, time);
        }
        return Statistics.norm(xSum, ySum);
    }*/
    
    private double getMomentumSum() {
        return getMomentumSum(objects);
    }
    private double getEnergySum() {
        return getEnergySum(objects);
    }
    public void update(double time) {
        update(time, 1, Integrator.LEAPFROG);
    }
    public void update(double time, int subdiv) {
        update(time, subdiv, Integrator.LEAPFROG);
    }
    public void update(double time, Integrator integrator) {
        update(time, 1, integrator);
    }
    public void update(double time, int subDiv, Integrator integrator) {
        
        double divTime = time/subDiv;
        
        //double futureMomentum = getFutureMomentumSum(objects, time);
        //double oldEnergySum = getEnergySum();
        switch (integrator) {
            case RK4:
                for (int i=0; i<subDiv; i++) {
                    integrateRK4(divTime);
                }
                break;
            case LEAPFROG:
                for (int i=0; i<subDiv; i++) {
                    integrateLeapFrog(divTime);
                }
                break;
            case EXPLICITMIDPOINT:
                for (int i=0; i<subDiv; i++) {
                    integrateExplicitMidpoint(divTime);
                }
                break;
            case SEMIIMPLICITEULER:
                for (int i=0; i<subDiv; i++) {
                    integrateSemiImplicitEuler(divTime);
                }
                break;
            case EXPLICITEULER:
                for (int i=0; i<subDiv; i++) {
                    integrateExplicitEuler(divTime);
                }
                break;
            case NOFORCE:
                for (int i=0; i<subDiv; i++) {
                    integrateNoForce(divTime);
                }
                break;
        }
        for (int i=0; i<objects.length; i++) {
            objects[i].update();
        }
        
        //double newMomentumSum = getMomentumSum();
        //double newEnergySum = getEnergySum();
        
        //System.out.println(newMomentumSum - momentumSum);
        //System.out.println(newEnergySum - energySum);
        
        //momentumSum = newMomentumSum;
        
        //printLoad(subDiv);
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
