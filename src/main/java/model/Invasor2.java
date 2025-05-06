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
public class Invasor2 extends Avatar {
    public Misil misil;

    public Invasor2() {
        super(2,2);
        
        try {
            super.setIcon(AvatarLoader.loadIcon("src/main/java/invasor2.txt"));
            misil = new Misil(2, "bomb2", false);
        } catch (IOException e) {
            System.out.println("Error al cargar el avatar: " + e.getMessage());
        }
    }
    
    
}
