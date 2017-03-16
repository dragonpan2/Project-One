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
    
    public Scene currentScene() {
        return currentScene;
    }
    public void refresh() {
        
    }
    public void setScene(Scene scene) {
        currentScene = scene;
    }
}
