package control.bots;

import control.snakes.Bot;
import control.snakes.Coordinate;
import control.snakes.Direction;
import control.snakes.Snake;

public class MortalBot implements Bot {

    @Override
    public Direction chooseDirection(Snake snake, Snake opponent, Coordinate mazeSize, Coordinate apple) {
        return Direction.LEFT;
    }

}
