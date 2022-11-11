package control.snakes;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import control.Jogo;
import view.TelaJogo;

/**
 * Implements tournament of the snake game with several rounds
 */
public class SnakesUIMain {
    private static FileWriter results_fw;
    private static int[][] total_results_table;
    private SnakeGame game;
    private TelaJogo window;
    private ArrayList<Bot> bots;
    private BotLoader loader;

    //Game settings
    private Coordinate mazeSize = new Coordinate(14, 14);
    private Coordinate head0 = new Coordinate(2, 7);
    private Direction tailDirection0 = Direction.DOWN;
    private Coordinate head1 = new Coordinate(11, 6);
    private Direction tailDirection1 = Direction.UP;
    private int snakeSize = 4;
    
    //a number associated to each player in bots ArrayList
    ArrayList<Integer> playerNumber;

    //points earned by each player
    ArrayList<Integer> points;
    ArrayList<String> bots_names;

    public SnakesUIMain() throws IOException, InterruptedException {
        this.bots = new ArrayList<>();
        this.loader = new BotLoader();
        this.playerNumber = new ArrayList<>();
        this.points = new ArrayList<>();
        this.bots_names = new ArrayList<>();

        // bots.add(loader.getBotClass("control.bots.RandomBot"));
        bots.add(loader.getBotClass("control.bots.LoopBot"));
        bots.add(loader.getBotClass("control.bots.AStar"));

        criarTela();
        acaoTeclas();

        start_round_robin_tournament(bots); 
    }

    public void acaoTeclas() {
        this.window.getCanvas().addAcaoTeclas(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                
                if (key == KeyEvent.VK_ENTER) {
                    try {
                        start_round_robin_tournament(bots);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                } 
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

        });
    }

    public void criarTela() {
       Bot bot0 = this.bots.get(0);
       Bot bot1 = bots.get(1);

       this.game = new SnakeGame(this.mazeSize, this.head0, this.tailDirection0, this.head1, this.tailDirection1, this.snakeSize, bot0, bot1);
       this.window = new TelaJogo(game);
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
                
                Thread t = new Thread(new Jogo(this.game, this.window.getCanvas()));

                t.start();
                t.join();

                // Thread.sleep(1000); // to allow users see the result
                // window.closeWindow();

                float time_taken = (float) (System.currentTimeMillis() - game.startTime) / 1000;
                
                results_fw.write(game.name0 + " vs " + game.name1 + " : " + game.gameResult + "");

                results_fw.write(" (Time taken: " + time_taken + ")\n");
                
                System.out.print(game.name0 + " vs " + game.name1 + " : " + game.gameResult);
                System.out.println(" (Time taken: " + time_taken + ")");

                // add the result of the game to total points
                points.set(playerNumber.get(i), points.get(playerNumber.get(i)) + Integer.parseInt(game.gameResult.substring(0, 1)));
                points.set(playerNumber.get(bots.size() - i - 1), points.get(playerNumber.get(bots.size() - i - 1)) + Integer.parseInt(game.gameResult.substring(game.gameResult.length() - 1)));

                // add to the total results table
                total_results_table[playerNumber.get(i)][playerNumber.get(bots.size() - i - 1)] += Integer.parseInt(game.gameResult.substring(0, 1));
                total_results_table[playerNumber.get(bots.size() - i - 1)][playerNumber.get(i)] += Integer.parseInt(game.gameResult.substring(game.gameResult.length() - 1));

                // resets human move
                SnakeCanvas.reset_move = true;
                SnakeCanvas.human_move = null;
            }

            // shuffle players in special way
            Bot buffer_player = bots.get(1);
            int buffer_player_number = playerNumber.get(1);
            
            for (int i = 2; i < bots.size(); i++) {
                // swap elements
                Bot t = buffer_player;
                int t_number = buffer_player_number;

                buffer_player = bots.get(i);
                buffer_player_number = playerNumber.get(i);
                bots.set(i, t);
                playerNumber.set(i, t_number);
            }
            
            // set 1-st player by last player(stored in the buffer)
            bots.set(1, buffer_player);
            playerNumber.set(1, buffer_player_number);
        }

        results_fw.write("\n-------------------------------------------\n\n");
        // get and print the results
        for (int i = 0; i < bots.size(); i++) {
            if (bots.get(i) == null) continue;
            
            System.out.println(bots_names.get(playerNumber.get(i)) + " earned: " + points.get(playerNumber.get(i)).toString());
            
            results_fw.write(bots_names.get(playerNumber.get(i)) + " earned: " + points.get(playerNumber.get(i)).toString() + "\n");
        }
    }
}

