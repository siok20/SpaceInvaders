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
public class Invasor1 extends Avatar {
    public Misil misil;

    public Invasor1() {
        super(1,1);
        
        try {
            super.setIcon(AvatarLoader.loadIcon("src/main/java/invasor1.txt"));
            misil = new Misil(1, "bomb1", false);
        } catch (IOException e) {
            System.out.println("Error al cargar el avatar: " + e.getMessage());
        }
    }
    
    
}
