/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.TelaInicial;
import view.TelaSobre;

/**
 *
 * @author Luis
 */
public class ControladorInicio {
    
    private TelaInicial telaInicial;
    
    public ControladorInicio() {
        this.telaInicial = new TelaInicial();
        this.telaInicial.setVisible(true);        
        
        this.acaoBotoes();
    }
    
    public void acaoBotoes() {
        this.telaInicial.addAcaoBtnIniciar(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControladorConfig controladorConfig = new ControladorConfig();
            }
        });

        this.telaInicial.addAcaoBtnSobre(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaSobre telaSobre = new TelaSobre();
                telaSobre.setVisible(true);
            }
        });
    }
    
}
