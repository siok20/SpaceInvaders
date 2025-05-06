/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.IOException;

/**
 *
 * @author siok
 */
public class Misil extends Avatar{
    
    public int damage;
    public Boolean fromPlayer;

    public Misil(int damage, String filename, Boolean fromPlayer) {
        super(0, damage);
        
        try {
            super.setIcon(AvatarLoader.loadIcon("src/main/java/" + filename + ".txt"));
            this.damage = damage;
            this.fromPlayer = fromPlayer;
            
        } catch (IOException e) {
            System.out.println("Error al cargar el avatar: " + e.getMessage());
        }
    }
    
}
