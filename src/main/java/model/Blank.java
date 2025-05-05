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
public class Blank extends Avatar {

    public Blank() {
        super(0,0);
        
        try {
            super.setIcon(AvatarLoader.loadIcon("src/main/java/blank.txt"));
        } catch (IOException e) {
            System.out.println("Error al cargar el avatar: " + e.getMessage());
        }
    }
    
    
}
