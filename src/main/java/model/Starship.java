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
    public static int ID = 1;
    public int points = 0;
    
    public int id;
    
    public Misil misil;

    public Starship(int type) {
        super(1, 8);
        
        try {
            super.setIcon(AvatarLoader.loadIcon("src/main/java/starship" + type + ".txt"));
            misil = new Misil(1 ,"laser", true);
            this.id = Starship.ID;
            Starship.ID += 1;
            
        } catch (IOException e) {
            System.out.println("Error al cargar el avatar: " + e.getMessage());
        }
    }
    
    public void getpoints(int points){
        this.points += points;
    }
    
    public int getID(){
        return this.id;
    }
    
}
