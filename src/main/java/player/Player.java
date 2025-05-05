
package player;

/**
 *
 * @author siok
 */
public class Player {

    public static void main(String[] args) {
        char[][] invader1 = {
    {'\u253C', '\u2565', '\u2565', '\u253C'},  // ┼╥╥┼
    {'\u2554', '\u256C', '\u256C', '\u2557'},  // ╔╬╬╗
    {'\u255A', '\u2569', '\u2569', '\u255D'}   // ╚╩╩╝
};

        printInvader(invader1);
    }
    
    public static void printInvader(char[][] invader) {
        for (char[] row : invader) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
}
