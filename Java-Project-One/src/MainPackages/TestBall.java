package MainPackages;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

/**
 *
 * @author Lin-Li
 */
public class TestBall extends JComponent {

    public TestBall() {
        
        this.setSize(100, 100);
        
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.white);
        g.fillOval(0, 0, 20, 20);
    }
    
}
