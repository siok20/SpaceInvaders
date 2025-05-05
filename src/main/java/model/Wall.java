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
public class Wall extends Avatar {

    public Wall() {
        super(0,4);
        
        try {
            super.setIcon(AvatarLoader.loadIcon("src/main/java/wall.txt"));
        } catch (IOException e) {
            System.out.println("Error al cargar el avatar: " + e.getMessage());
        }
    }
    
    
}
