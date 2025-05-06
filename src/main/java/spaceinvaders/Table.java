/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spaceinvaders;

import java.util.Random;
import model.*;

/**
 *
 * @author siok
 */
public class Table {
    Avatar[][] table = new Avatar[20][15];
    private int count = 0;

    public Table() {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                table[i][j] = new Blank();
            }
        }
        this.setupLevel();
    }
    
    private boolean inBounds(int i, int j) {
        return i >= 0 && i < table.length && j >= 0 && j < table[0].length;
    }
    
    private Boolean isBlank(int i, int j){
        return table[i][j] instanceof Blank;
    }
    
    private Boolean isMisil(int i, int j){
        return table[i][j] instanceof Misil;
    }
    
    private Boolean isInvasor(int i, int j){
        return table[i][j] instanceof Invasor1 ||
                table[i][j] instanceof Invasor2 ||
                table[i][j] instanceof Invasor3 ||
                table[i][j] instanceof FinalBoss;
    }
    
    private Boolean isAlive(int i, int j){
        return table[i][j].isAlive();
    }
    
    private void remove(int i, int j){
        if(!this.isAlive(i, j)){
            table[i][j] = new Blank();
        }
    }
    
    //Mover de la posicion (i,j) hacia (x,y)
    public void move(int i, int j, int x, int y){
        //Verificar las correctas coordenadas
        if (!inBounds(i,j) || !inBounds(x,y)) {
            System.out.println("Movimiento fuera de límites");
            return;
        }   
        //Un misil impacta en un objeto que no esta vacio (muro, alien o nave)
        if( this.isMisil(i,j) && !this.isBlank(x,y)){
            this.remove(i,j);
            this.table[x][y].getDamage(this.table[i][j].damage);
            this.remove(x,y);
            return;
        }
        //Caso en el que se intenta mover hacia un (muro, alien o nave)
        if(!this.isBlank(x,y)){
            System.out.println("Movimiento invalido");
            return;
        }
        
        //Mover un avatar hacia un espacio en blanco
       this.table[x][y] = this.table[i][j];
       this.table[i][j] = new Blank();
        
    }
    
    private Misil typeMisil(int i, int j){
        if(table[i][j] instanceof Invasor1){
            return (new Invasor1()).misil;
        }
        else if(table[i][j] instanceof Invasor2){
            return (new Invasor2()).misil;
        }
        else if(table[i][j] instanceof FinalBoss){
            return (new FinalBoss()).misil;
        }
        else if(table[i][j] instanceof Invasor3){
            return (new Invasor3()).misil;
        }
        else if(table[i][j] instanceof Starship){
            return (new Starship(1)).misil;
        }
        return new Misil(1 , "bomb1", false);
    }
    
    public void addStarship(int i, int j){
        this.table[i][j] = new Starship(Starship.ID %3 + 1);
    }
    
    public void moveStarship(int id, int ix, int jx){
        int x = 0,y = 0;
        for (int i = table.length-1; i >= 0; i--) {
            for (int j = 0; j < table[i].length; j++) {
                if(this.table[i][j] instanceof Starship && ((Starship)this.table[i][j]).id == id){
                    this.move(i, j, i + ix, j + jx);
                    x = i+ix;
                    y = j+jx;
                    
                    if(this.count % 3 == 0){
                        int down = x - 1;
                        if (inBounds(down, y)) {
                            if (isBlank(down, y)) {
                                table[down][y] = typeMisil(x, y);
                                table[down][y].shoot = true;
                            } 
                        }
                    }
                    return;
                }
            }
        }
        
    }
    
    public void moveStarship(int id, String move){
        switch (move) {
            case "UP":
                this.moveStarship(id, -1, 0);
                break;
            case "DOWN":
                this.moveStarship(id, 1, 0);
                break;
            case "LEFT":
                this.moveStarship(id, 0, -1);
                break;
            case "RIGHT":
                this.moveStarship(id, 0, 1);
                break;
        }
    }
    
    public void updateFrame() {
        Random rand = new Random();

        // Etapa 1: Movimiento de Invasores
        for (int i = table.length - 1; i >= 0; i--) {
            for (int j = (count % 2 == 0 ? table[0].length - 1 : 0); 
                 (count % 2 == 0 ? j >= 0 : j < table[0].length); 
                 j += (count % 2 == 0 ? -1 : 1)) {

                if (isInvasor(i, j) && isAlive(i, j)) {
                    int nextJ = count % 2 == 0 ? j + 1 : j - 1;
                    if (inBounds(i, nextJ)) {
                        move(i, j, i, nextJ);
                    }
                }
            }
        }

        // Etapa 2: Disparo de Invasores
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if (isInvasor(i, j) && isAlive(i, j)) {
                    if (rand.nextDouble() < 0.01) {
                        System.out.println("n");
                        int down = i + 1;
                        if (inBounds(down, j)) {
                            if (isBlank(down, j)) {
                                table[down][j] = typeMisil(i, j);
                                table[down][j].shoot = true;
                            } else if (isInvasor(down, j)) {
                                table[down][j].shootBehind = true;
                                table[down][j].shoot = true;
                            }
                        }
                    }
                }
            }
        }

        // Etapa 3: Movimiento de Misiles
        for (int i = table.length - 1; i >= 0; i--) {
            for (int j = 0; j < table[i].length; j++) {
                if (isMisil(i, j)) {
                    
                    int nextI = ((Misil)table[i][j]).fromPlayer ? i - 1 : i + 1;
                    if (inBounds(nextI, j)) {
                        move(i, j, nextI, j);
                    } else {
                        // Fuera del tablero, eliminar
                        table[i][j] = new Blank();
                    }
                }
                else if (table[i][j].shootBehind){
                    if(this.isInvasor(i, j)){
                        if (inBounds(i+1, j)) {
                            table[i+1][j] = this.typeMisil(i, j);
                        }
                    }
                    else{
                        if (inBounds(i-1, j)) {
                           table[i-1][j] = this.typeMisil(i, j);
                        }
                    }
                }
            }
        }

        // Etapa 4: Limpieza de entidades muertas
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                remove(i, j);
            }
        }

        count++; // actualiza la dirección de movimiento
    }
    
    public void setupLevel() {
        // FinalBoss en la fila superior, columna central
        int midCol = table[0].length / 2;
        table[0][midCol] = new FinalBoss();

        // Invasores grandes (Invasor3) - fila 1 y 2
        for (int i = 1; i <= 2; i++) {
            for (int j = 2; j < table[i].length - 2; j += 2) {
                table[i][j] = new Invasor3();
            }
        }

        // Invasores medianos (Invasor2) - fila 3 y 4
        for (int i = 3; i <= 4; i++) {
            for (int j = 1; j < table[i].length - 1; j += 2) {
                table[i][j] = new Invasor2();
            }
        }

        // Invasores pequeños (Invasor1) - fila 5 y 6
        for (int i = 5; i <= 6; i++) {
            for (int j = 2; j < table[i].length-1; j += 2) {
                table[i][j] = new Invasor1();
            }
        }

        // Colocar muros defensivos (Wall) - fila 15, en columnas espaciadas
        for (int j = 2; j < table[0].length; j += 5) {
            table[12][j] = new Wall();
            table[13][j] = new Wall(); // doble capa de muro
        }
}
    
    public void printTable(){
        for (int i = 0; i < table.length; i++) {
            for (int t=0; t<3; t++){
                for (int j = 0; j < table[i].length; j++) {
                    table[i][j].printRow(t);
               }
                System.out.println();
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < table.length; i++) {
            for (int t=0; t<3; t++){
                for (int j = 0; j < table[i].length; j++) {
                    sb.append(table[i][j].getRow(t));
               }
                sb.append('\n');
            }
        }
        
        return sb.toString();
    }
    
}
