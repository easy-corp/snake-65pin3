/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import control.snakes.Bot;
import control.snakes.BotLoader;
import control.snakes.Coordinate;
import control.snakes.Direction;
import control.snakes.SnakeCanvas;
import control.snakes.SnakeGame;
import control.snakes.SnakesUIMain;
import control.snakes.SnakesWindow;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Luis
 */
public class ControladorJogo {
    
    private SnakesUIMain snakesMain;
    private ArrayList<Bot> bots;
    private BotLoader loader;
    
    public ControladorJogo() throws IOException, InterruptedException {
        this.snakesMain = new SnakesUIMain();
        
        IniciarJogo();
    }

    public void IniciarJogo() throws IOException, InterruptedException {
        //if (args.length < 2) {
        //    System.err.println("You must provide two classes implementing the Bot interface.");
        //    System.exit(1);
        //}

        bots = new ArrayList<>();
        loader = new BotLoader();

        // Old code
        // bots.add(loader.getBotClass("minmax.minmax"));
        // bots.add(loader.getBotClass("finalBot.AdderBoaCobra"));

        bots.add(loader.getBotClass("control.bots.RandomBot"));
        // bots.add(loader.getBotClass("bots.AStar"));
        bots.add(loader.getBotClass("control.bots.AStar"));

        //for (String arg : args) {
        //    bots.add(loader.getBotClass(arg));
        //}

        this.snakesMain.start_tournament_n_times(1, bots);
    }
    
}
