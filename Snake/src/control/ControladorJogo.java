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
    
    public ControladorJogo(String bot1, String bot2, int botSize, double timeMov, int timeLimitGame) throws IOException, InterruptedException {
        IniciarJogo(bot1, bot2, botSize, timeMov, timeLimitGame);
    }

    public void IniciarJogo(String bot1, String bot2, int botSize, double timeMov, int timeLimitGame) throws IOException, InterruptedException {
        bots = new ArrayList<>();
        loader = new BotLoader();

        bots.add(loader.getBotClass(bot1));
        bots.add(loader.getBotClass(bot2));

        this.snakesMain = new SnakesUIMain(bots, botSize, timeMov, timeLimitGame);
    }
    
}
