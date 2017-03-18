/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package World2D;

/**
 *
 * @author bowen
 */
public class Camera {
    private Scene currentScene;
    private double xPos;
    private double yPos;
    private double scale;

    Camera(Scene currenScene) {
        this(currenScene, 0, 0, 1);
    }
    
    Camera(Scene currenScene, double x, double y, double s) {
        this.currentScene = currenScene;
        xPos = x;
        yPos = y;
        scale = s;
    }
    
    public Scene scene() {
        return currentScene;
    }
    public void setScene(Scene scene) {
        currentScene = scene;
    }

    public double getxPos() {
        return xPos;
    }
    public void setxPos(double xPos) {
        this.xPos = xPos;
    }
    public double getyPos() {
        return yPos;
    }
    public void setyPos(double yPos) {
        this.yPos = yPos;
    }
    public double getScale() {
        return scale;
    }
    public void setScale(double scale) {
        this.scale = scale;
    }
    
}
