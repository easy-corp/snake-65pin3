package control.bots;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import control.snakes.Bot;
import control.snakes.Coordinate;
import control.snakes.Direction;
import control.snakes.Snake;

public class RandomBot implements Bot {

    private List<Direction> direcoes;
    private Random random;

    //Realiza um movimento aleatório evitando perigos mortais
    public RandomBot() {
        //Cria lista de movimentos
        this.direcoes = new ArrayList<>();
        direcoes.add(Direction.UP);
        direcoes.add(Direction.DOWN);
        direcoes.add(Direction.LEFT);
        direcoes.add(Direction.RIGHT);

        this.random = new Random();
    }

    @Override
    public Direction chooseDirection(Snake snake, Snake opponent, Coordinate mazeSize, Coordinate apple) {
        //Localiza o movimento para direcao contraria a cabeça, que nao é possível
        Iterator<Coordinate> it = snake.body.iterator(); //Iterando sobre o deque que representa o corpo da cobra
        it.next(); //Coordenada da cabeça
        Coordinate antesCabeca = it.next(); //Coordenada mortal

        List<Direction> movimentosSafe = new ArrayList<>();
        //Retira dos movimentos possiveis aqueles que irão matar ela 
        for (Direction d : this.direcoes) {
            if (!snake.getHead().moveTo(d).equals(antesCabeca) &&        //Mover para tras e se matar
                snake.getHead().moveTo(d).inBounds(mazeSize) &&          //Mover para fora do tabuleiro
                !snake.elements.contains(snake.getHead().moveTo(d)) &&   //Mover para cima do seu corpo
                !opponent.elements.contains(snake.getHead().moveTo(d))   //Mover para cima do inimigo
            ) {
                movimentosSafe.add(d);
            }
        }

        //Se não tiver para onde ir
        //Retorna uma direção qualquer para matar o bot
        if (movimentosSafe.isEmpty()) {
            return Direction.DOWN;
        }

        //Escolhe um dentre os movimentos possiveis
        Direction proxMove = movimentosSafe.get(random.nextInt(movimentosSafe.size()));

        return proxMove;
    }
    
}
