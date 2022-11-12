/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import view.TelaAjuda;
import view.TelaConfig;

/**
 *
 * @author Luis
 */
public class ControladorConfig {
    
    private TelaConfig telaConfig;
    private Map<String, String> algoritmos;
    
    public ControladorConfig() {
        this.telaConfig = new TelaConfig();
        this.telaConfig.setVisible(true);
        this.algoritmos = new HashMap<>();
        
        this.preencherAlgoritmos();
        this.acaoBotoes();
    }
    
    public void preencherAlgoritmos() {
        this.algoritmos.put("Bot A*", "control.bots.AStar");
        this.algoritmos.put("Bot A* Ingênuo", "control.bots.AStarIngenuos");
        this.algoritmos.put("Bot Aleatório", "control.bots.RandomBot");
        this.algoritmos.put("Bot BFS", "control.bots.BFSBot");
        this.algoritmos.put("Bot Circular ", "control.bots.LoopBot");
        
        for (String alg : this.algoritmos.keySet()) {
            this.telaConfig.addItemCbAlg(alg);
        }      
    }
    
    public void acaoBotoes() {
        this.telaConfig.addAcaoBtnAjuda(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaAjuda telaAjuda = new TelaAjuda();
                telaAjuda.setVisible(true);
            }
        });

        this.telaConfig.addAcaoBtnJogar(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Algoritmos Utilizados
                String bot1 = telaConfig.getSelecionadoCbAlg1();
                String bot2 = telaConfig.getSelecionadoCbAlg2();
                
                //Parâmetros setados
                int botSize = telaConfig.getSpnSizeBot();
                double timeMov = telaConfig.getSpnTimeMov();
                int timeLimitGame = telaConfig.getSpnTimeLimiteGame();
                
                try {
                    ControladorJogo controladorJogo = new ControladorJogo(getBot(bot1), getBot(bot2), botSize, timeMov, timeLimitGame);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    
    //Recupera classe do bot dentro do Map a partir da chave
    public String getBot(String alg) {
        return this.algoritmos.get(alg);
    }
    
}
