/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package World2D.Objects;

import World2D.Camera;
import java.awt.Color;
import javax.swing.JComponent;

/**
 *
 * @author bowen
 */
public interface DisplayObject {
    public enum DisplayObjectType {
        Circle, Line
    }
    //public JComponent getJComponent();
    public void update(Camera camera);
    
    public void setPos(double x, double y);
    
    public DisplayObjectType getType();
    public boolean isInView(int x0, int y0, int x1, int y1);
    
    public int getDix();
    public int getDiy();
    
    public Color getColor();
    
}
