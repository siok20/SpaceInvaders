/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author siok
 */
public class AvatarLoader {
    public static char[][] loadIcon(String filename) throws IOException {
        char[][] icon = new char[3][4];
        Path path = Paths.get(filename);
        BufferedReader reader = Files.newBufferedReader(path);

        String line;
        int row = 0;
        while ((line = reader.readLine()) != null && row < 4) {
            for (int col = 0; col < Math.min(4, line.length()); col++) {
                icon[row][col] = line.charAt(col);
            }
            row++;
        }
        reader.close();
        return icon;
    }
}
