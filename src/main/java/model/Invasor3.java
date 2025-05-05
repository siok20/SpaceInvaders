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
public class Invasor3 extends Avatar {
    public Misil misil;
    
    public Invasor3() {
        super(3,3);
        
        try {
            super.setIcon(AvatarLoader.loadIcon("src/main/java/invasor3.txt"));
            misil = new Misil(3,"bomb3");
        } catch (IOException e) {
            System.out.println("Error al cargar el avatar: " + e.getMessage());
        }
    }
    
    
}
