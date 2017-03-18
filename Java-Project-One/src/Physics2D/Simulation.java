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
    private double secondsPerStep;
    private double secondsBetweenSteps;
    
    private boolean isPaused;
    
    public Simulation(IntegratorType integrator, double timePerStep, double timeBetweenSteps, SpaceObject... objects) {
        this.isPaused = true;
        this.objects = objects;
        this.secondsPerStep = timePerStep;
        this.secondsBetweenSteps = timeBetweenSteps;
        switch(integrator) {
            case NOFORCE:
                break;
            case EXPLICITEULER:
                this.integrator = new ExplicitEuler();
                break;
            case SYMPLECTIC1:
                this.integrator = new Symplectic1();
                break;
        }
        this.thread = new Thread(this);
        updateInterpolationSimulationTime(); //TODO if simulation lags behind change the Interpolation Simulation time
    }
    public void forward(int steps) {
        for (int i=0; i<steps; i++) {
            integrator.apply(objects, secondsPerStep);
        }
    }
    public double forward(double seconds) { //takes the wished time, returns the time that passed in reality
        int steps = (int)(seconds/secondsPerStep);
        for (int i=0; i<steps; i++) {
            integrator.apply(objects, secondsPerStep);
        }
        return seconds * secondsPerStep;
    }
    public void updateGraphicalPositions() {
        for (int i=0; i<objects.length; i++) {
            objects[i].update();
        }
    }
    public void updateInterpolationSimulationTime() {
        for (int i=0; i<objects.length; i++) {
            objects[i].displayComponent.setInterpolationSimulationTime(secondsPerStep/secondsBetweenSteps);
        }
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
        
        double desiredSleepms = 1000*secondsBetweenSteps;
        
        long startTime;
        long endTime;
        long sleepTime;
        
        while (true) {
            if (!isPaused) {
                
                
                startTime = System.nanoTime();
                forward(1);
                updateGraphicalPositions();
                endTime = System.nanoTime();
                
                
                sleepTime = (long)(desiredSleepms*1000000) - (endTime-startTime);
                if (sleepTime < 0) {
                    sleepTime = 0;
                    System.out.println("Thread Overload");
                }
                long sleepms = Math.floorDiv(sleepTime, 1000000);
                int sleepns = (int)Math.floorMod(sleepTime, 1000000);
                
                try {
                    Thread.sleep(sleepms, sleepns);
                } catch (InterruptedException ex) {
                    System.out.println("Thread Error");
                }
                
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
