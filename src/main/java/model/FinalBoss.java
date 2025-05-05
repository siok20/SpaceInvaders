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
public class FinalBoss extends Avatar {
    public Misil misil;

    public FinalBoss() {
        super(4,8);
        
        try {
            super.setIcon(AvatarLoader.loadIcon("src/main/java/finalboss.txt"));
            misil = new Misil(4,"rpg");
        } catch (IOException e) {
            System.out.println("Error al cargar el avatar: " + e.getMessage());
        }
    }
    
    
}
