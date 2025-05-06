/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package spaceinvaders;

/**
 *
 * @author siok
 */
public class SpaceInvaders {

    public static void main(String[] args) {
        System.out.println("Hello Invader!");
        
        Table tb = new Table();
        
        tb.setupLevel();
        System.out.println(tb);
        
        tb.addStarship(19, 13);
        tb.updateFrame();
        tb.moveStarship(1, 19, 2);
        tb.printTable();
        
    }
}
