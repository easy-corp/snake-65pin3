package control.bots;

import java.util.ArrayList;
import java.util.List;

import control.snakes.Bot;
import control.snakes.Coordinate;
import control.snakes.Direction;
import control.snakes.Snake;

public class LoopBot implements Bot {

    private List<Direction> movimentos;
    private int cont;

    //Realiza movimemnto de rodar ao redor do próprio corpo
    public LoopBot() {
        this.movimentos = new ArrayList<>();
        this.cont = 0;

        this.movimentos.add(Direction.LEFT);
        this.movimentos.add(Direction.UP);
        this.movimentos.add(Direction.RIGHT);
        this.movimentos.add(Direction.DOWN);
    }

    @Override
    public Direction chooseDirection(Snake snake, Snake opponent, Coordinate mazeSize, Coordinate apple) {
        Direction movimento = this.movimentos.get(cont);
        
        if (this.cont == (this.movimentos.size() - 1)) {
            this.cont = 0;
        } else {
            this.cont++;
        }

        return movimento;
    }
    
}
