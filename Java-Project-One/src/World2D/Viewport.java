/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package World2D;

import javax.swing.JFrame;

/**
 *
 * @author bowen
 */
public class Viewport extends JFrame {
    private Scene scene;
    
    public Viewport() {
        this(new Scene());
    }
    public Viewport(Scene scene) {
        this.setTitle("Space!");
        this.setSize(1920, 1080);
        
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        this.scene = scene;
        this.add(scene);
        this.scene.activate();
    }
    public Scene getScene() {
        return scene;
    }
    public void setScene(Scene scene) {
        this.scene.deactivate();
        this.scene = scene;
        this.scene.activate();
    }
}
