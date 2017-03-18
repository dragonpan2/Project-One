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
public class NBodyFutureOrbit implements Runnable, World, FutureSimulation {
    private Thread thread;
    private SpaceObject[] objects;
    private SpaceObject[] smallObjects;
    private SpaceObject[] bigObjects;
    
    private double[] orbitalPeriods;
    
    private Line[] lines;
    private Line[] orbitLines;
    private Line[] pathLines;
    private Integrator integrator;
    
    private double updatesPerSecond; //How many "Calculations" per second
    private int futureTimeSteps; //How many steps does the integrator calculate for the amount of time
    private double totalFutureTime;
    
    //private double smallestOrbitalPeriod;
    private boolean updateOrbits;
    
    private double ratio; //Ratio between simulated time and real time, higher is faster (same as initialRatio * accel from NBodySimulation)
    
    
    private boolean isPaused;
    
    
    public NBodyFutureOrbit(IntegratorType integrator, double totalFutureTime, int futureTimeSteps, double updatesPerSecond, SpaceObject[] smallObjects, SpaceObject[] bigObjects, double[] orbitalPeriods) {
        this.isPaused = true;
        this.futureTimeSteps = futureTimeSteps;
        this.updatesPerSecond = updatesPerSecond;
        this.totalFutureTime = totalFutureTime;
        this.ratio = totalFutureTime;
        
        this.smallObjects = smallObjects;
        this.bigObjects = bigObjects;
        
        this.orbitalPeriods = orbitalPeriods;
        this.updateOrbits = false;
        //this.smallestOrbitalPeriod = orbitalPeriods[1];
        
        this.objects = new SpaceObject[bigObjects.length + smallObjects.length];
        for (int i=0; i<objects.length; i++) {
            if (i < smallObjects.length) {
                objects[i] = smallObjects[i];
            } else {
                objects[i] = bigObjects[i-smallObjects.length];
            }
        }
        this.orbitLines = new Line[(futureTimeSteps+Math.floorDiv(futureTimeSteps, 100))*bigObjects.length];
        for (int i=0; i<orbitLines.length; i++) {
            orbitLines[i] = new Line();
        }
        
        this.pathLines = new Line[(futureTimeSteps-1)*smallObjects.length];
        for (int i=0; i<pathLines.length; i++) {
            pathLines[i] = new Line();
        }
        
        this.lines = new Line[orbitLines.length + pathLines.length];
        for (int i=0; i<lines.length; i++) {
            if (i < pathLines.length) {
                lines[i] = pathLines[i];
            } else {
                lines[i] = orbitLines[i-pathLines.length];
            }
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
    
    private void updateOrbitPositions() {
        
        Vector2[][] positionTime = new Vector2[bigObjects.length][futureTimeSteps];
        for (int i=0; i<bigObjects.length; i++) {
            positionTime[i] = Integrator.getFutureSingle(bigObjects, i, this.integrator, orbitalPeriods[i]/futureTimeSteps, futureTimeSteps+Math.floorDiv(futureTimeSteps, 100));
        }
        int linei = 0;
        
        //final double orbitThreshold = 1E9;
        //final int timeTriggerThreshold = 100;
        //final int timeOverlapThreshold = 10;
        
        for (int n=0; n<bigObjects.length; n++) {
            //boolean isPeriodic = false;
            //int timeOverlapCounter = 0;
            for (int i=0; i<positionTime[n].length-1; i++) {
                //if (i > timeTriggerThreshold) {
                    //if (isWithinThreshold(positionTime[n][0].get(0), positionTime[n][1].get(1), positionTime[n][i].get(0), positionTime[n][i].get(1), orbitThreshold)) {
                        //isPeriodic = true;
                    //}
                //}
                //if (isPeriodic && timeOverlapCounter > timeOverlapThreshold) {
                    //orbitLines[linei].hide();
                //} else {
                    //if (isPeriodic) {
                        //timeOverlapCounter++;
                    //}
                    orbitLines[linei].setPos(positionTime[n][i].get(0), positionTime[n][i].get(1), positionTime[n][i+1].get(0), positionTime[n][i+1].get(1));
                    orbitLines[linei].show();
                //}
                
                linei++;
            }
                //orbitLines[linei].setPos(positionTime[n][positionTime[n].length-1].get(0), positionTime[n][positionTime[n].length-1].get(1), positionTime[n][0].get(0), positionTime[n][0].get(1));
                //orbitLines[linei].show();
        }
    }
    
    public void forceUpdateOrbitPositions() {
        this.updateOrbits = true;
    }
    
    private void updatePathPositions() { //TODO implement variable timesteps for path positions based on velocity
        Vector2[][] positionTime = Integrator.getFuture(objects, this.integrator, totalFutureTime/futureTimeSteps, futureTimeSteps);
        int linei = 0;
        
        final double orbitThreshold = 1E9;
        final int timeTriggerThreshold = 100;
        final int timeOverlapThreshold = 10;
        
        for (int n=0; n<smallObjects.length; n++) {
            System.out.println(n);
            boolean isPeriodic = false;
            int timeOverlapCounter = 0;
            for (int i=0; i<positionTime[n].length-1; i++) {
                if (i > timeTriggerThreshold) {
                    if (isWithinThreshold(positionTime[n][0].get(0), positionTime[n][1].get(1), positionTime[n][i].get(0), positionTime[n][i].get(1), orbitThreshold)) {
                        isPeriodic = true;
                    }
                }
                if (isPeriodic && timeOverlapCounter > timeOverlapThreshold) {
                    pathLines[linei].hide();
                } else {
                    if (isPeriodic) {
                        timeOverlapCounter++;
                    }
                    pathLines[linei].setPos(positionTime[n][i].get(0), positionTime[n][i].get(1), positionTime[n][i+1].get(0), positionTime[n][i+1].get(1));
                    pathLines[linei].show();
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
        
        updateOrbitPositions();
        
        while (true) {
            if (!isPaused) {
                
                startTime = System.nanoTime();
                if (updateOrbits) {
                    updateOrbitPositions();
                }
                step();
                updatePathPositions();
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setBodies(SpaceObject[] bodies) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
