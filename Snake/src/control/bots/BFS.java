package control.bots;

import control.snakes.Bot;
import control.snakes.Coordinate;
import control.snakes.Direction;
import control.snakes.Snake;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BFS implements Bot {

    // Classe auxiliar de noh para a busca em largura
    private class No {

        private final Coordinate coordenada;
        private No pai;

        private No(Coordinate coord) {
            coordenada = coord;
        }

        private Coordinate getCoordenada() {
            return coordenada;
        }

        private No getPai() {
            return pai;
        }

        private void setPai(No no) {
            pai = no;
        }

        @Override
        public String toString() {
            return coordenada.toString();
        }

    }

    private final List<Direction> direcoes;
    private Coordinate origem;
    private Coordinate destino;
    private Coordinate maze;
    private Snake snake;
    private Snake opponent;

    // Lista de coordenadas que foram visitadas
    private List<Coordinate> abertos;

    public BFS() {
        // Cria lista de movimentos
        this.direcoes = new ArrayList<>();
        direcoes.add(Direction.UP);
        direcoes.add(Direction.DOWN);
        direcoes.add(Direction.RIGHT);
        direcoes.add(Direction.LEFT);
    }

    @Override
    public Direction chooseDirection(Snake snake, Snake opponent, Coordinate mazeSize, Coordinate apple) {
        origem = snake.getHead();
        destino = apple;
        maze = mazeSize;
        this.snake = snake.clone();
        this.opponent = opponent;
        abertos = new ArrayList<>();

        No folha = executaBuscaLargura();

        // Se achou o noh folha
        if (folha != null) {
            return origem.getDirection(getProximaCoordenada(folha));
        }

        // Se nao encontrou o caminho retorna uma direcao valida
        return getDirecaoValida();
    }

    /**
     * Retorna o no folha
     * @return No
     */
    private No executaBuscaLargura() {
        // No raiz
        No raiz = new No(origem);

        // Busca os primeiros sucessores a partir da raiz
        // Remove a cauda sempre antes do proximo movimento
        snake.body.removeLast();
        List<No> sucessores = getSucessores(raiz, new ArrayList());

        // Variavel auxiliar para salvar os novos sucessores a partir dos atuais
        List<No> newSucessores = new ArrayList<>();

        // Enquanto houverem sucessores
        while (true) {

            for (No sucessor : sucessores) {
                // Verifica se eh o destino
                if (sucessor.getCoordenada().equals(destino)) {
                    return sucessor;
                }
            }

            // Remove a cauda sempre antes do proximo movimento
            if (!snake.body.isEmpty()) {
                snake.body.removeLast();
            }

            for (No sucessor : sucessores) {
                // senao cria novos nos para percorrer depois
                newSucessores.addAll(getSucessores(sucessor, sucessores));
            }

            // Atribui os novos sucessores
            sucessores = new ArrayList(newSucessores);

            if (sucessores.isEmpty()) {
                return null;
            }

            newSucessores.clear();
        }
    }

    /**
     * Retorna os sucessores a partir do no
     * @param no
     * @param todosSucessores
     * @return List<No>
     */
    private List<No> getSucessores(No no, List<No> todosSucessores) {
        List<No> sucessores = new ArrayList<>();

        for (Direction d : direcoes) {

            Coordinate sucessor = no.getCoordenada().moveTo(getDirecao(d));

            // Se esta dentro do maze
            if (sucessor.inBounds(maze)) {

                // Se nao possui o proprio corpo
//                if (!snake.elements.contains(sucessor)) {
                if (!snake.elements.contains(sucessor)) {

                    // Se nao possui o corpo do oponente
                    if (!opponent.elements.contains(sucessor)) {

                        // Se nao passou por essa coordenada em algum outro ramo
                        if (!abertos.contains(sucessor)) {
                            No noSucessor = new No(sucessor);
                            noSucessor.setPai(no);

                            sucessores.add(noSucessor);
                            abertos.add(sucessor);
                        }
                    }
                }
            }
        }

        return sucessores;
    }

    /**
     * Retorna a proxima coordenada com base no noh folha
     * @param folha
     * @return Coordinate
     */
    private Coordinate getProximaCoordenada(No folha) {
        No pai = folha.getPai();

        while (pai.getPai() != null) {
            folha = pai;
            pai = pai.getPai();
        }

        return folha.getCoordenada();
    }

    /**
     * Retorna uma direcao que nao morre (na maioria das vezes)
     * @return Direction
     */
    private Direction getDirecaoValida() {
        for (Direction d : direcoes) {

            Coordinate sucessor = origem.moveTo(getDirecao(d));

            // Se esta dentro do maze
            if (sucessor.inBounds(maze)) {

                // Se nao possui o proprio corpo
                if (!snake.elements.contains(sucessor)) {

                    // Se nao possui o corpo do oponente
                    if (!opponent.elements.contains(sucessor)) {
                        return getDirecao(d);
                    }
                }
            }
        }

        // Se nao ha para onde ir, nao importa para onde vai (Dolzan, 2022)
        return Direction.LEFT;
    }

    /**
     * Retorna a direcao tratando a ordem de up ou down
     * @param dir
     * @return Direction
     */
    private Direction getDirecao(Direction dir) {
        switch (dir) {
            case UP -> {
                return Direction.DOWN;
            }
            case DOWN -> {
                return Direction.UP;
            }
        }

        return dir;
    }

}