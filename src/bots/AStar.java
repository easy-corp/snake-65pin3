package bots;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import snakes.Bot;
import snakes.Coordinate;
import snakes.Direction;
import snakes.Snake;

public class AStar implements Bot {

    private List<Direction> direcoes;

    //Realiza um movimento segundo o algoritmo A*
    public AStar() {
        //Cria lista de movimentos
        this.direcoes = new ArrayList<>();
        direcoes.add(Direction.UP);
        direcoes.add(Direction.DOWN);
        direcoes.add(Direction.LEFT);
        direcoes.add(Direction.RIGHT);
    }

    @Override
    public Direction chooseDirection(Snake snake, Snake opponent, Coordinate mazeSize, Coordinate apple) {
        List<NossaCoordenada> abertas = new ArrayList<>();       //Celulas possiveis de serem visitadas
        List<NossaCoordenada> expandidas = new ArrayList<>();    //Celualas ja visitadas
        NossaCoordenada atual;                                   //A coordenada atual do loop
        
        //Adiciona a cabeça da cobra(origem) a lista de células abertas
        //O custo nesse caso é 0
        abertas.add(new NossaCoordenada(snake.getHead(), 0));

        //Enquanto houverem células abertas (a serem visitadas)
        while (abertas.size() > 0) {
            //Nos movemos para a celula com menor g (custo)
            atual = getMenorCusto(abertas);
            expandidas.add(atual);

            //Verifica se chegou ao local desejado
            if (atual.equals(apple)) {
                break;
            } else {
                //Se não chegou ao local desejado
                //Verifica os movimentos possiveis nos arredores
                for (Direction d : this.direcoes) {
                    Coordinate proximoMovimento = snake.getHead().moveTo(d);

                    if (!proximoMovimento.equals(getAntesDaCabeca(snake)) &&  //Não pode mover para tras e se matar
                        proximoMovimento.inBounds(mazeSize) &&                //Não pode mover fora do tabuleiro
                        !snake.elements.contains(proximoMovimento) &&         //Não pode mover para cima do seu corpo
                        !opponent.elements.contains(proximoMovimento) &&      //Não pode mover para cima do inimigo
                        !abertas.contains(proximoMovimento) &&                //Não voltamos a células já abertas
                        !expandidas.contains(proximoMovimento)                //Não voltamos a células já expandidas
                    ) {
                        //Adicionamos a coordenada do possivel movimento a lista de possiveis celulas
                        //O custo é determinado pelo custo da célula atual + 1
                        abertas.add(new NossaCoordenada(snake.getHead().moveTo(d), (atual.getCusto() + 1)));
                    }
                }
            }
        }

        //VERIFICAR RETORNO//
        return null;
    }

    //Localiza a coordenada contraria a cabeça, que seria um movimento mortal
    public Coordinate getAntesDaCabeca(Snake snake) {
        Iterator<Coordinate> it = snake.body.iterator(); //Iterando sobre o deque que representa o corpo da cobra
        it.next(); //Coordenada da cabeça
        Coordinate antesCabeca = it.next(); //Coordenada mortal

        return antesCabeca;
    }

    //Recupera a coordenada de menor custo dentro da lista
    public NossaCoordenada getMenorCusto(List<NossaCoordenada> coordenadas) {
        NossaCoordenada menorCusto = coordenadas.get(0);

        for (NossaCoordenada nc : coordenadas) {
            if (nc.getCusto() < menorCusto.getCusto()) {
                menorCusto = nc;
            }
        }

        return menorCusto;
    }
    
}
