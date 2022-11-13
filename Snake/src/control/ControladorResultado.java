package control;

import java.util.ArrayList;
import java.util.List;
import view.TelaResultado;

public class ControladorResultado {

    TelaResultado tela;

    /*public static void main(String[] args) {
        List<String> teste = new ArrayList<>();
        teste.add("5");
        teste.add("7");
        teste.add("60");
        ControladorResultado cont = new ControladorResultado("BFS", "AStarIngenuos", teste);
        cont.exibeTela();
    }*/

    public ControladorResultado(String bot0, String bot1, boolean bot0time, boolean bot1time, List<String> result) {
        tela = new TelaResultado(bot0, bot1);

        int tempo = Integer.parseInt(result.get(2));
        int apple0 = Integer.parseInt(result.get(0));
        int apple1 = Integer.parseInt(result.get(1));

        tela.getTempo().setText(String.valueOf(tempo));
        tela.getBot0().setText(result.get(0));
        tela.getBot1().setText(result.get(1));

        // Se um dos dois deu time out
        if (bot0time || bot1time) {
            if (bot0time && bot1time) {
                tela.getVencedor().setText("Empate");
                tela.getTimeOut().setText("Ambos");
            } else if (bot0time) {
                tela.getVencedor().setText(bot1);
                tela.getTimeOut().setText(bot0);
            } else {
                tela.getVencedor().setText(bot0);
                tela.getTimeOut().setText(bot1);
            }
        } else {
            tela.getTimeOut().setText("Nenhum");
            if (apple0 > apple1) {
                tela.getVencedor().setText(bot0);
            } else if (apple1 > apple0) {
                tela.getVencedor().setText(bot1);
            } else {
                tela.getVencedor().setText("Empate");
            }
        }
    }

    public void exibeTela() {
        tela.setVisible(true);
    }

}