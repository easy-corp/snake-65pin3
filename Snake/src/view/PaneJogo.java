/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import control.snakes.Coordinate;
import control.snakes.Direction;
import control.snakes.Snake;
import control.snakes.SnakeGame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.ImageIcon;

/**
 *
 * @author Luis
 */
public class PaneJogo extends javax.swing.JPanel {

    private static final int CELL_SIZE = 40;
    private static final int PAD = 2;
    private static final int SMALLER_PAD = 6;
    private static final int SMALL_PAD = 6;
    private static final int ApplePad = 10;
    private static final Color color0 = new Color(92, 192, 255);
    private static final Color color1 = new Color(255, 255, 255);
    private static final Color bodyColor0 = new Color(92, 192, 255);
    private static final Color bodyColor1 = new Color(255, 255, 255);
    private static final Color backgroundColor = new Color(0, 0, 0);
    private static final Color borderColor = new Color(22, 50, 76);
    private static final Color appleColor = Color.red;
    private static ImageIcon apple;
    public static Boolean reset_move = true;
    public Dimension renderSize;
    private SnakeGame game;
    // added by Serpentine:
    public static ArrayList<Direction> move_queue = new ArrayList<>(); // used in Human player
    public static Direction human_move = Direction.DOWN; // used in human_subject_2 player
    private static final int font_size_score = 32;  // used to be 28
    private static final int font_size_names = 26;  // used to be 15
    
    /**
     * Creates new form PaneJogo
     */
    
    public PaneJogo(SnakeGame game) {
        initComponents();

        this.game = game;
        java.net.URL imageURL = getClass().getResource("../control/snakes/images/apple.png");

        if (imageURL != null) {
            Image appleImage = new ImageIcon(imageURL).getImage().getScaledInstance(CELL_SIZE - 10, CELL_SIZE - 10, Image.SCALE_SMOOTH);
            apple = new ImageIcon(appleImage);
        }
        
        renderSize = new Dimension((game.mazeSize.x + 2) * CELL_SIZE, (game.mazeSize.y + 2) * CELL_SIZE);
    
    }
    
    private void fillCellWithPad(Graphics2D g, Coordinate cell, Color color, int pad) {
        g.setColor(color);
        g.fillRect((cell.x + 1) * CELL_SIZE + pad, (cell.y + 1) * CELL_SIZE + pad, CELL_SIZE - 2 * pad, CELL_SIZE - 2 * pad);
    }
    
    private void fillCell(Graphics2D g, Coordinate cell, Color color) {
        fillCellWithPad(g, cell, color, PAD);
    }
    
    private void fillSmallerCell(Graphics2D g, Coordinate cell, Color color) {
        fillCellWithPad(g, cell, color, SMALLER_PAD);
    }
    
    private void fillSmallCell(Graphics2D g, Coordinate cell, Color color) {
        fillCellWithPad(g, cell, color, SMALL_PAD);
    }
    
    public void paint(Graphics g) {
        Graphics2D gg = (Graphics2D) g; //bufferStrategy.getDrawGraphics();
        gg.clearRect(0, 0, renderSize.width, renderSize.height);
        render(gg);

        //bufferStrategy.show();
    }
    
    public void drawString(Graphics2D g, String s, int x, int y) {
        g.drawString(s, x - getFontMetrics(g.getFont()).stringWidth(s), y);
    }
    
    public void paintIcon(Graphics2D g, ImageIcon i, int x, int y) {
        i.paintIcon(this, g, x - i.getIconWidth(), y);
    }
    
    public void fillRect(Graphics2D g, int x, int y, int width, int height) {
        g.fillRect(x - width, y, width, height);
    }

    private void render(Graphics2D g) {

        g.setColor(borderColor);
        g.fillRect(0, 0, renderSize.width, renderSize.height);
        g.setColor(backgroundColor);
        g.fillRect(CELL_SIZE, CELL_SIZE, renderSize.width - 2 * CELL_SIZE, renderSize.height - 2 * CELL_SIZE);

        fillCellWithPad(g, game.appleCoordinate, appleColor, ApplePad);

        Iterator<Coordinate> it = game.snake0.body.stream().iterator();
        while (it.hasNext()) {
            Coordinate bp = it.next();
            fillCell(g, bp, color0);
            fillSmallerCell(g, bp, bodyColor0); //print body
        }

        it = game.snake1.body.stream().iterator();
        while (it.hasNext()) {
            Coordinate bp = it.next();
            fillCell(g, bp, color1);
            fillSmallerCell(g, bp, bodyColor1); //print body
        }

        fillSmallCell(g, game.snake1.getHead(), new Color(0, 0, 0)); //head
        fillSmallCell(g, game.snake0.getHead(), new Color(0, 0, 0)); //head

        //Print the score on score board
        g.setFont(new Font("Comic Sans MS", Font.PLAIN, font_size_score));
        g.setColor(new Color(228, 255, 224));
        // commented out by Serpentine
        //g.drawString(game.gameResult, renderSize.width / 2 - getFontMetrics(g.getFont()).stringWidth(game.gameResult) / 2, renderSize.height - 10); //game results

        //Print snakes symbol on score board
        g.setColor(new Color(92, 192, 255)); //outer
        g.fillRect(renderSize.width / 2 - 3 * CELL_SIZE + 14, renderSize.height - CELL_SIZE + 11, CELL_SIZE - 18, CELL_SIZE - 18);
        g.setColor(new Color(92, 192, 255)); //inner
        g.fillRect(renderSize.width / 2 - 3 * CELL_SIZE + 18, renderSize.height - CELL_SIZE + 15, CELL_SIZE - 26, CELL_SIZE - 26);
        g.setColor(new Color(255, 255, 255)); //outer
        fillRect(g, renderSize.width / 2 + 3 * CELL_SIZE - 14, renderSize.height - CELL_SIZE + 11, CELL_SIZE - 18, CELL_SIZE - 18);
        g.setColor(new Color(255, 255, 255)); //inner
        fillRect(g, renderSize.width / 2 + 3 * CELL_SIZE - 18, renderSize.height - CELL_SIZE + 15, CELL_SIZE - 26, CELL_SIZE - 26);

        //Print apple symbols on score board
        apple.paintIcon(this, g, renderSize.width / 2 - 25, renderSize.height - CELL_SIZE + 3);
        paintIcon(g, apple, renderSize.width / 2 + 2 * CELL_SIZE, renderSize.height - CELL_SIZE + 3);

        //Print apple counts on score board
        g.drawString(Integer.toString(game.appleEaten0), renderSize.width / 2 - CELL_SIZE, renderSize.height - 10);
        drawString(g, Integer.toString(game.appleEaten1), renderSize.width / 2 + CELL_SIZE, renderSize.height - 10);

        //Print bot names on score board
        g.setFont(new Font("Comic Sans MS", Font.PLAIN, font_size_names));
        g.drawString(game.name0, CELL_SIZE, renderSize.height - 10);
        drawString(g, game.name1, renderSize.width - CELL_SIZE, renderSize.height - 10);
    } 
    
    // public void addAcaoTeclas(KeyListener acao) {
    //     this.addKeyListener(acao);
    // }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {
        
        setBackground(new java.awt.Color(255, 255, 255));
        

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 640, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 640, Short.MAX_VALUE)
        );
    }
    
}
