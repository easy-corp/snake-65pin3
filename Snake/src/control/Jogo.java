package control;

import control.snakes.SnakeGame;
import view.TelaJogo;

public class Jogo implements Runnable {

    private TelaJogo tela;
    private SnakeGame game;
    private int timeLimitGame;  // time limit in milliseconds

    private boolean running = false;

    public Jogo(SnakeGame game, TelaJogo tela, int timeLimitGame) {
        this.game = game;
        this.tela = tela;
        this.timeLimitGame = timeLimitGame;
    }

    @Override
    public void run() {        
        running = true;
        
        this.tela.atualizarTela();
        
        long startTime = System.currentTimeMillis();
        
        while(running) {
            long t = System.currentTimeMillis();
            
            try {
                running = game.runOneStep();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                
            this.tela.atualizarTela();

            long elapsed = System.currentTimeMillis() - t;

            try {
                Thread.sleep(Math.max(200 - elapsed, 0));
            } catch (InterruptedException e) {
                if (game.gameResult != null)
                    game.gameResult = "interrupted";
                break;
            }

            //check for time limit
            if (System.currentTimeMillis() - startTime >= (timeLimitGame  * 1000)) {
                int snake0_size = game.snake0.body.size();
                int snake1_size = game.snake1.body.size();
                game.gameResult = (snake0_size > snake1_size ? 1 : 0) + " - " + (snake1_size > snake0_size ? 1 : 0);
                running = false;
                System.out.println("O tempo limite foi ultrapasado (" + timeLimitGame + " segundos). \n");
            }
        }

        System.out.println("--------------");
        System.out.println("Fim do jogo");
        
        for (String str : this.game.getGameResult()) {
            System.out.println(str);
        }
    }

}
