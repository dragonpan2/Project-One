/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package World2D.Objects;

import World2D.Camera;
import javax.swing.JComponent;

/**
 *
 * @author bowen
 */
public interface DisplayObject {
    public JComponent getJComponent();
    public void update(Camera camera);
    public void setInterpolationSimulationTime(double dst);
    public void setInterpolationFrameTime(double dft);
}
