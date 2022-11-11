/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import control.snakes.Bot;
import control.snakes.BotLoader;
import control.snakes.SnakesUIMain;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

/**
 *
 * @author Luis
 */
public class ControladorJogo {
    
    private SnakesUIMain snakesMain;
    private ArrayList<Bot> bots;
    private BotLoader loader;
    
    public ControladorJogo() throws IOException, InterruptedException {
        IniciarJogo();
    }

    public void IniciarJogo() throws IOException, InterruptedException {
        bots = new ArrayList<>();
        loader = new BotLoader();

        bots.add(loader.getBotClass("control.bots.RandomBot"));
        // bots.add(loader.getBotClass("bots.AStar"));
        bots.add(loader.getBotClass("control.bots.AStar"));

        this.snakesMain = new SnakesUIMain();
    }
    
}
