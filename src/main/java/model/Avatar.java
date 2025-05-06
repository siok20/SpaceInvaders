
package model;

/**
 *
 * @author siok
 */
public abstract class Avatar {
    protected char[][] icon = new char[4][3];
    public int health;
    public int damage;
    public Boolean shoot;
    public Boolean shootBehind;


    public Avatar(int damage, int health) {
        this.damage = damage;
        this.health = health;
        this.shoot = false;
        this.shootBehind = false;
    }

    public char[][] getIcon() {
        return icon;
    }

    public void setIcon(char[][] icon) {
        this.icon = icon;
    }
    
    public void printAvatar() {
        for (char[] row : this.icon) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
    
    public void printRow(int i){
        for(char c: this.icon[i]){
            System.out.print(c);
        }
    }
    
    public String getRow(int i){
        StringBuilder sb = new StringBuilder();
        
        for(char c: this.icon[i]){
            sb.append(c);
        }
        
        return sb.toString();
    }
    
    public Boolean getDamage(int damage){
        this.health -= damage;
        
        return this.isAlive();
    }
    
    public Boolean isAlive(){
        return this.health >= 0;
    }
}