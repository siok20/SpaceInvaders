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
public class Starship extends Avatar {
    public int points = 0;
    
    public Misil bala;

    public Starship(int type) {
        super(1, 8);
        
        try {
            super.setIcon(AvatarLoader.loadIcon("src/main/java/starship" + type + ".txt"));
            bala = new Misil(1 ,"laser");
            
        } catch (IOException e) {
            System.out.println("Error al cargar el avatar: " + e.getMessage());
        }
    }
    
    
}
