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
                 switch(e.getKeyCode()) {
                    case KeyEvent.VK_W :
                        world.vec1.addToNorm(10);
                        break;
                    case KeyEvent.VK_S :
                        world.vec1.addToNorm(-10);
                        break;
                    case KeyEvent.VK_A :
                         world.vec1.addToRot(-Math.PI/20);
                        break;
                    case KeyEvent.VK_D :
                         world.vec1.addToRot(Math.PI/20);
                        break;
                    case KeyEvent.VK_UP :
                        world.vec2.addToNorm(10);
                        break;
                    case KeyEvent.VK_DOWN :
                        world.vec2.addToNorm(-10);
                        break;
                    case KeyEvent.VK_LEFT :
                         world.vec2.addToRot(-Math.PI/20);
                        break;
                    case KeyEvent.VK_RIGHT :
                         world.vec2.addToRot(Math.PI/20);
                        break;
                    case KeyEvent.VK_E :
                        if (Vector2.useFastMath) {
                            Vector2.useFastMath = false;
                            System.out.println("Now using precise maths");
                        } else {
                            Vector2.useFastMath = true;
                            System.out.println("Now using fast math approximation");
                        }
                        break;
                    default :
                        break;
                 }
                world.vec3.setElements(world.vec1.getProjVec(world.vec2));
                world.vec4.setElements(world.vec1.getRejVec(world.vec2));
            }
        });
        
    }

}
