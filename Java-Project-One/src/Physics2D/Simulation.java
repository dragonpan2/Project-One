/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Physics2D;

import Physics2D.Integrators.ExplicitEuler;
import Physics2D.Integrators.Integrator;
import Physics2D.Integrators.Integrator.IntegratorType;
import Physics2D.Integrators.Symplectic1;
import Physics2D.Integrators.Symplectic4;
import Physics2D.Objects.SpaceObject;
import World2D.Objects.DisplayObject;
import World2D.World;

/**
 *
 * @author bowen
 */
public class Simulation implements Runnable, World {
    private Thread thread;
    private SpaceObject[] objects;
    private Integrator integrator;
    
    private double updatesPerSecond; //How many "Big steps" per second
    private int miniSteps; //How many "Small Steps" per Big step
    private double secondsPerMiniStep; //How many seconds pass in each "Small Steps"
    private double initialRatio;
    private double ratio; //Ratio between simulated time and real time, higher is faster
    
    private int accel = 1;
    
    
    private boolean isPaused;
    
    public Simulation(IntegratorType integrator, double ratio, double updatesPerSecond, int miniSteps, SpaceObject... objects) {
        this.isPaused = true;
        this.objects = objects;
        this.initialRatio = ratio;
        this.ratio = ratio;
        this.updatesPerSecond = updatesPerSecond;
        this.secondsPerMiniStep = ratio/updatesPerSecond/miniSteps;
        this.miniSteps = (miniSteps > 0) ? miniSteps : 1;
        System.out.println(secondsPerMiniStep);
        switch(integrator) {
            case NOFORCE:
                break;
            case EXPLICITEULER:
                this.integrator = new ExplicitEuler();
                break;
            case SYMPLECTIC4:
                this.integrator = new Symplectic4();
                break;
            case SYMPLECTIC1:
                this.integrator = new Symplectic1();
                break;
        }
        this.thread = new Thread(this);
    }
    public void forward(int steps) {
        for (int i=0; i<steps; i++) {
            integrator.apply(objects, secondsPerMiniStep);
        }
    }
    public double forward(double seconds) { //takes the wished time, returns the time that passed in reality
        int steps = (int)(seconds/secondsPerMiniStep);
        for (int i=0; i<steps; i++) {
            integrator.apply(objects, secondsPerMiniStep);
        }
        return seconds * secondsPerMiniStep;
    }
    public void updateGraphicalPositions() {
        Vector2[] currentAccelerations = integrator.getCurrentAccelerations();
        for (int i=0; i<objects.length; i++) {
            objects[i].update();
        }
    }
    public void updateInterpolationSimulationTime(double time) { //Total time to interpolate before next physics Big Step
        for (int i=0; i<objects.length; i++) {
            objects[i].displayComponent.setInterpolationSimulationTime(time);
        }
    }
    @Override
    public void speedUp() {
        if (accel < 10E6) {
            accel *= 2;
            ratio = initialRatio * accel;
        }
    }
    @Override
    public void speedDown() {
        if (accel > 1) {
            accel /= 2;
            ratio = initialRatio * accel;
        }
    }
    @Override
    public double getSpeed() {
        return initialRatio * accel;
    }
    public void start() {
        this.thread.start();
        unpause();
    }
    public void pause() {
        this.isPaused = true;
    }
    public void unpause() {
        this.isPaused = false;
    }
    @Override
    public void run() {
        
        double desiredSleepms = 1000D/updatesPerSecond; //Desired sleep time in miliseconds
        double desiredSleepns = 1000000000D/updatesPerSecond;
        
        long startTime;
        long endTime;
        long sleepTime;
        
        double realLagRatio;
        
        while (true) {
            if (!isPaused) {
                
                startTime = System.nanoTime();
                forward(miniSteps*accel);
                updateGraphicalPositions();
                endTime = System.nanoTime();
                
                
                sleepTime = (long)(desiredSleepms*1000000) - (endTime-startTime);
                realLagRatio = desiredSleepns/(endTime-startTime)*ratio;
                //realLagRatio = 0;
                if (sleepTime < 0) {
                    sleepTime = 0;
                    System.out.println("Thread Overload");
                    speedDown();
                }
                long sleepms = Math.floorDiv(sleepTime, 1000000);
                int sleepns = (int)Math.floorMod(sleepTime, 1000000);
                
                try {
                    Thread.sleep(sleepms, sleepns);
                } catch (InterruptedException ex) {
                    System.out.println("Thread Error");
                }
                //updateInterpolationSimulationTime(0);
                updateInterpolationSimulationTime((realLagRatio > ratio) ? ratio : realLagRatio);
                
            }
        }
    }

    @Override
    public DisplayObject[] getDisplayObjects() {
        DisplayObject[] displayObjects = new DisplayObject[objects.length];
        for (int i=0; i<objects.length; i++) {
            displayObjects[i] = objects[i].getDisplayObject();
        }
        return displayObjects;
    }

    @Override
    public long getTicks() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
