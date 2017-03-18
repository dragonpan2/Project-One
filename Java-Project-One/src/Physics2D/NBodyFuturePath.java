/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Physics2D;

import Physics.FutureSimulation;
import Physics.Integrators.ExplicitEuler;
import Physics.Integrators.Integrator;
import Physics.Integrators.Integrator.IntegratorType;
import Physics.Integrators.Symplectic1;
import Physics.Integrators.Symplectic4;
import Physics.Simulation;
import Physics2D.Objects.SpaceObject;
import World2D.Objects.DisplayObject;
import World2D.Objects.Line;
import World2D.World;

/**
 *
 * @author bowen
 */
public class NBodyFuturePath implements Runnable, World, FutureSimulation {
    private Thread thread;
    private SpaceObject[] objects;
    private Line[] lines;
    private Integrator integrator;
    
    private double updatesPerSecond; //How many "Calculations" per second
    private int futureTimeSteps; //How many steps does the integrator calculate for the amount of time
    private double totalFutureTime;
    
    private double ratio; //Ratio between simulated time and real time, higher is faster (same as initialRatio * accel from NBodySimulation)
    
    
    private boolean isPaused;
    
    
    public NBodyFuturePath(IntegratorType integrator, double totalFutureTime, int futureTimeSteps, double updatesPerSecond, SpaceObject... objects) {
        this.isPaused = true;
        this.objects = objects;
        this.futureTimeSteps = futureTimeSteps;
        this.updatesPerSecond = updatesPerSecond;
        this.totalFutureTime = totalFutureTime;
        this.ratio = totalFutureTime;
        
        lines = new Line[(futureTimeSteps-1)*objects.length];
        for (int i=0; i<lines.length; i++) {
            lines[i] = new Line();
        }
        
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
    @Override
    public void step() {
        compute();
    }
    public void compute() {
        //Vector2[][] positionTime = integrator.getFuture(objects, ratio/stepsPerRatioTime, futureRatioSteps*stepsPerRatioTime);
    }
    private static boolean isWithinThreshold(double x0, double y0, double x, double y, double threshold) {
        return ((Math.abs(x-x0) < threshold) && (Math.abs(y-y0) < threshold));
    }
    
    private void updateSpatialPositions() {
        Vector2[][] positionTime = integrator.getFuture(objects, totalFutureTime/futureTimeSteps, futureTimeSteps);
        int linei = 0;
        
        final double orbitThreshold = 1E9;
        final int timeTriggerThreshold = 100;
        final int timeOverlapThreshold = 10;
        
        for (int n=0; n<positionTime.length; n++) {
            boolean isPeriodic = false;
            int timeOverlapCounter = 0;
            for (int i=0; i<positionTime[n].length-1; i++) {
                if (i > timeTriggerThreshold) {
                    if (isWithinThreshold(positionTime[n][0].get(0), positionTime[n][1].get(1), positionTime[n][i].get(0), positionTime[n][i].get(1), orbitThreshold)) {
                        isPeriodic = true;
                    }
                }
                if (isPeriodic && timeOverlapCounter > timeOverlapThreshold) {
                    lines[linei].hide();
                } else {
                    if (isPeriodic) {
                        timeOverlapCounter++;
                    }
                    lines[linei].setPos(positionTime[n][i].get(0), positionTime[n][i].get(1), positionTime[n][i+1].get(0), positionTime[n][i+1].get(1));
                    lines[linei].show();
                }
                
                linei++;
            }
        }
    }
    @Override
    public void start() {
        this.thread.start();
        unpause();
    }
    @Override
    public void pause() {
        this.isPaused = true;
    }
    @Override
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
        
        
        while (true) {
            if (!isPaused) {
                
                startTime = System.nanoTime();
                step();
                updateSpatialPositions();
                endTime = System.nanoTime();
                
                sleepTime = (long)(desiredSleepms*1000000) - (endTime-startTime);
                
                if (sleepTime < 0) {
                    sleepTime = 0;
                    System.out.println("FutureSimulation Thread Overload");
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
        DisplayObject[] displayObjects = lines;
        return displayObjects;
    }

    @Override
    public Simulation getSimulation() {
        return this;
    }

    @Override
    public long getTicks() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getSpeed() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void speedUp() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void speedDown() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setRatio(double ratio) {
        this.ratio = ratio;
    }

    @Override
    public void setBodies(SpaceObject[] bodies) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
