
package MainPackages;

import javax.swing.JFrame;

/**
 *
 * @author Lin-Li
 */
public class Window extends JFrame {
    
    World world = new World();

    public Window()  {
        
        this.add(world);
        this.setTitle("ProjectOne");
        this.setSize(1400, 1000);
        this.setVisible(true);
    }
    
    
}
