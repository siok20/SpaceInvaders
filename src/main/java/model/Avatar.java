
package model;

/**
 *
 * @author siok
 */
public abstract class Avatar {
    protected char[][] icon = new char[4][3];
    public int health;
    public int damage;


    public Avatar(int damage, int health) {
        this.damage = damage;
        this.health = health;
    }

    public char[][] getIcon() {
        return icon;
    }

    public void setIcon(char[][] icon) {
        this.icon = icon;
    }
    
    public void printavatar() {
        for (char[] row : this.icon) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
    
    public Boolean getDamage(int damage){
        this.health -= damage;
        
        return this.isAlive();
    }
    
    public Boolean isAlive(){
        return this.health >= 0;
    }
}