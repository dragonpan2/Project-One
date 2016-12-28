package MainPackages;

import SPHY.Vector2;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

/**
 *
 * @author Lin-Li
 */
public class Window extends JFrame {

    World world = new World();

    public Window() {

        this.add(world);
        this.setTitle("ProjectOne");
        this.setSize(1400, 1000);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        keyEventAdd();
        new Thread(world).start();
    }

    public void keyEventAdd() {
        
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                    //System.out.println("Triggered");
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    world.vec1.setElement(1, world.vec1.getElement(1)-10);
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    world.vec1.setElement(1, world.vec1.getElement(1)+10);

                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    world.vec1.setElement(0, world.vec1.getElement(0)-10);

                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    world.vec1.setElement(0, world.vec1.getElement(0)+10);

                }
                world.vec3.setElements(world.vec1.getProjVec(world.vec2));
                world.vec4.setElements(world.vec1.getRejVec(world.vec2));
            }
        });
        
    }

}
