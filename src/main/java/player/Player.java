
package player;

import java.io.IOException;
import model.AvatarLoader;
import model.Starship;
import model.Wall;

/**
 *
 * @author siok
 */
public class Player {

    public static void main(String[] args) {
        Starship sp = new Starship(3);
        
        sp.misil.printAvatar();

        sp.printAvatar();
        
        Wall wall = new Wall();
        wall.printAvatar();
        
    }
}
