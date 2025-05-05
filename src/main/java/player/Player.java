
package player;

import java.io.IOException;
import model.AvatarLoader;
import model.Starship;

/**
 *
 * @author siok
 */
public class Player {

    public static void main(String[] args) {
        Starship sp = new Starship(3);
        
        sp.bala.printavatar();

        sp.printavatar();
        
    }
}
