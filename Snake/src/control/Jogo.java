package control;

import javax.swing.SwingUtilities;

import control.snakes.SnakeGame;
import java.awt.EventQueue;
import view.PaneJogo;
import view.TelaJogo;

public class Jogo implements Runnable {

    private TelaJogo tela;
    private SnakeGame game;
    private final int TIME_LIMIT_PER_GAME = 3*60*1000;  // time limit in milliseconds

    private boolean running = false;

    public Jogo(SnakeGame game, TelaJogo tela) {
        this.game = game;
        this.tela = tela;
    }

    @Override
    public void run() {        
        running = true;
        System.out.println("Começou");
        
//         this.tela.getCanvas().repaint();
         this.tela.atualizarTela();
        // this.tela.remove(this.tela.getCanvas());
        // this.tela.setCanvas(new PaneJogo(game));
        
        long startTime = System.currentTimeMillis();
        while(running) {
            long t = System.currentTimeMillis();
            
            try {
                running = game.runOneStep();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                    
//             this.tela.getCanvas().repaint
            this.tela.atualizarTela();
            // this.tela.remove(this.tela.getCanvas());
            // this.tela.setCanvas(new PaneJogo(game));

            long elapsed = System.currentTimeMillis() - t;

            try {
                Thread.sleep(Math.max(200 - elapsed, 0));
            } catch (InterruptedException e) {
                if (game.gameResult != null)
                    game.gameResult = "interrupted";
                break;
            }

            //check for time limit
            if (System.currentTimeMillis() - startTime >= TIME_LIMIT_PER_GAME) {
                int snake0_size = game.snake0.body.size();
                int snake1_size = game.snake1.body.size();
                game.gameResult = (snake0_size > snake1_size ? 1 : 0) + " - " + (snake1_size > snake0_size ? 1 : 0);
                running = false;
                System.out.println("Round time left (" + (TIME_LIMIT_PER_GAME / 1000) + "seconds) \n");
            }
        }
        
        System.out.println("terminou a thread");
    }

//    public void iniciarjogo() {
//        running = true;
//        System.out.println("Começou");
//        
////         this.tela.getCanvas().repaint();
//         this.tela.atualizarTela();
//        // this.tela.remove(this.tela.getCanvas());
//        // this.tela.setCanvas(new PaneJogo(game));
//        
//        long startTime = System.currentTimeMillis();
//        while(running) {
//            long t = System.currentTimeMillis();
//    
//            try {
//                running = game.runOneStep();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//                    
////             this.tela.getCanvas().repaint
//            this.tela.atualizarTela();
//            // this.tela.remove(this.tela.getCanvas());
//            // this.tela.setCanvas(new PaneJogo(game));
//
//            long elapsed = System.currentTimeMillis() - t;
//
//            try {
//                Thread.sleep(Math.max(200 - elapsed, 0));
//            } catch (InterruptedException e) {
//                if (game.gameResult != null)
//                    game.gameResult = "interrupted";
//                break;
//            }
//
//            //check for time limit
//            if (System.currentTimeMillis() - startTime >= TIME_LIMIT_PER_GAME) {
//                int snake0_size = game.snake0.body.size();
//                int snake1_size = game.snake1.body.size();
//                game.gameResult = (snake0_size > snake1_size ? 1 : 0) + " - " + (snake1_size > snake0_size ? 1 : 0);
//                running = false;
//                System.out.println("Round time left (" + (TIME_LIMIT_PER_GAME / 1000) + "seconds) \n");
//            }
//        }
//        
//        System.out.println("terminou a thread");
//    }

}
