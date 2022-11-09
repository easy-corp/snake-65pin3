/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import control.snakes.SnakesUIMain;
import java.io.IOException;

/**
 *
 * @author Luis
 */
public class ControladorJogo {
    
    private SnakesUIMain snakesMain;
    
    public ControladorJogo() throws IOException, InterruptedException {
        this.snakesMain = new SnakesUIMain();
        this.snakesMain.comecarJogo();
    }
    
}
