package control.snakes;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import control.Jogo;
import java.awt.EventQueue;
import view.TelaJogo;

/**
 * Implements tournament of the snake game with several rounds
 */
public class SnakesUIMain {
    private static int[][] total_results_table;
    private SnakeGame game;
    private TelaJogo window;
    private ArrayList<Bot> bots;

    //Game settings
    private final Coordinate mazeSize = new Coordinate(14, 14);
    private final Coordinate head0 = new Coordinate(2, 7);
    private final Direction tailDirection0 = Direction.DOWN;
    private final Coordinate head1 = new Coordinate(11, 6);
    private final Direction tailDirection1 = Direction.UP;
    
    //Parâmetros do jogo
    private final int snakeSize;
    private final double timeMov;
    private final int timeLimitGame;
    
    //a number associated to each player in bots ArrayList
    private ArrayList<Integer> playerNumber;

    //points earned by each player
    private ArrayList<Integer> points;
    private ArrayList<String> bots_names;

    public SnakesUIMain(ArrayList<Bot> bots, int snakeSize, double timeMov, int timeLimitGame) throws IOException, InterruptedException {
        this.bots = bots;
        this.playerNumber = new ArrayList<>();
        this.points = new ArrayList<>();
        this.bots_names = new ArrayList<>();
        this.snakeSize = snakeSize;
        this.timeMov = timeMov;
        this.timeLimitGame = timeLimitGame;

//        bots.add(loader.getBotClass("control.bots.RandomBot"));
//        bots.add(loader.getBotClass("control.bots.LoopBot"));
//        bots.add(loader.getBotClass("control.bots.MortalBot"));
//        bots.add(loader.getBotClass("control.bots.AStar"));

        criarJogo();
        acaoTeclas();

        start_round_robin_tournament(bots);
    }

    public void acaoTeclas() {
        // this.window.getCanvas().addAcaoTeclas(new KeyListener() {

        //     @Override
        //     public void keyTyped(KeyEvent e) {

        //     }

        //     @Override
        //     public void keyPressed(KeyEvent e) {
        //         int key = e.getKeyCode();
                
        //         if (key == KeyEvent.VK_ENTER) {
        //             try {
        //                 start_round_robin_tournament(bots);
        //             } catch (Exception e1) {
        //                 e1.printStackTrace();
        //             }
        //         } 
        //     }

        //     @Override
        //     public void keyReleased(KeyEvent e) {

        //     }

        // });
    }
    
    public void criarJogo() {
        Bot bot0 = bots.get(0);
        Bot bot1 = bots.get(1);

        game = new SnakeGame(mazeSize, head0, tailDirection0, head1, tailDirection1, snakeSize, bot0, bot1, timeMov);
        criarTela();
    }

    public void criarTela() {
       window = new TelaJogo(game);
    }

    /**
     * Start tournament between bots
     *
     * @param bots Competitive bots
     * @throws InterruptedException Threads handler
     * @throws IOException          FileWriter handler
     */
    public void start_round_robin_tournament(ArrayList<Bot> bots) throws InterruptedException, IOException {
        for (int k = 0; k < bots.size() - 1; k++) {
            // play N / 2 rounds
            for (int i = 0; i < bots.size() / 2; i++) {
                // start the game between ith and N-i-1 bots
                Bot bot0 = bots.get(i);
                Bot bot1 = bots.get(bots.size() - i - 1);
                if (bot0 == null || bot1 == null) continue;

                Thread t = new Thread(new Jogo(game, window, timeLimitGame));

                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        t.start(); 
                    }
                });   
                
                t.join();
            }
        }        
    }
    
}

