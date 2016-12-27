package MainPackages;

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
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    System.out.println("Triggered");

                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    System.out.println("Triggered");

                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    System.out.println("Triggered");

                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    System.out.println("Triggered");

                }
            }
        });
        
    }

}
