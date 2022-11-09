/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import view.TelaAjuda;
import view.TelaConfig;

/**
 *
 * @author Luis
 */
public class ControladorConfig {
    
    private TelaConfig telaConfig;
    
    public ControladorConfig() {
        this.telaConfig = new TelaConfig();
        this.telaConfig.setVisible(true);
        
        this.preencherAlgoritmos();
        this.acaoBotoes();
    }
    
    public void preencherAlgoritmos() {
        this.telaConfig.addItemCbAlg("Random Bot");
        this.telaConfig.addItemCbAlg("A* Bot");
        this.telaConfig.addItemCbAlg("BFS Bot");
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
                try {
                    ControladorJogo controladorJogo = new ControladorJogo();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    
}
