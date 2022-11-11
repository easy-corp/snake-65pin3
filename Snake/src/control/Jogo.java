package control;

import javax.swing.SwingUtilities;

import control.snakes.SnakeGame;
import view.PaneJogo;

public class Jogo implements Runnable {

    private PaneJogo canvas;;
    private SnakeGame game;
    private final int TIME_LIMIT_PER_GAME = 3*60*1000;  // time limit in milliseconds

    private boolean running = false;

    public Jogo(SnakeGame game, PaneJogo canvas) {
        this.game = game;
        this.canvas = canvas;
    }

    @Override
    public void run() {
        running = true;
        System.out.println("Começou");
        
        // SwingUtilities.invokeLater(new Runnable() {
        //     public void run() {
        //        canvas.repaint();
        //     }
        // });

        canvas.repaint();
        
        long startTime = System.currentTimeMillis();
        while(running) {
            long t = System.currentTimeMillis();

            try {
                running = game.runOneStep();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            // SwingUtilities.invokeLater(new Runnable() {
            //     public void run() {
            //        canvas.repaint();
            //     }
            // });
                    
            canvas.repaint();

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
    }

}
