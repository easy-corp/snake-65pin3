package control.bots;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import control.snakes.Bot;
import control.snakes.Coordinate;
import control.snakes.Direction;
import control.snakes.Snake;

public class AStar implements Bot {

    private List<Direction> direcoes;

    //Realiza um movimento segundo o algoritmo A*
    public AStar() {
        //Cria lista de movimentos
        this.direcoes = new ArrayList<>();
        direcoes.add(Direction.UP);
        direcoes.add(Direction.DOWN);
        direcoes.add(Direction.RIGHT);
        direcoes.add(Direction.LEFT);
    }

    @Override
    public Direction chooseDirection(Snake snake, Snake opponent, Coordinate mazeSize, Coordinate apple) {
        List<NossaCoordenada> abertas = new ArrayList<>();       //Celulas possiveis de serem visitadas
        List<NossaCoordenada> expandidas = new ArrayList<>();    //Celulas ja visitadas
        NossaCoordenada atual = null;                            //A coordenada atual do loop
        
        //Adiciona a cabeça da cobra(origem) a lista de células abertas
        //O custo nesse caso é 0
        abertas.add(new NossaCoordenada(snake.getHead(), 0.0));

        while(abertas.size() > 0) {
            //Pega a de menor custo dentre as abertas
            //Tira ela da lista de abertas e adiciona ela a lista de expandidas
            atual = getMenorCusto(abertas, apple);
            abertas.remove(atual);
            expandidas.add(atual);

            //Se chegou ao local da maçã
            if (atual.getCoordinate().equals(apple)) {
                break;
            } else {
                //Se não chegou ao local desejado
                //Verifica os movimentos possiveis nos arredores
                for (Direction d : this.direcoes) {
                    Coordinate proximoMovimento = atual.getCoordinate().moveTo(d);

                    if (!proximoMovimento.equals(getAntesDaCabeca(snake)) &&  //Não pode mover para tras e se matar
                        proximoMovimento.inBounds(mazeSize) &&                //Não pode mover fora do tabuleiro
                        !snake.elements.contains(proximoMovimento) &&         //Não pode mover para cima do seu corpo
                        !opponent.elements.contains(proximoMovimento) &&      //Não pode mover para cima do inimigo
                        !contemCoordenada(abertas, proximoMovimento) &&       //Não voltamos a células já abertas
                        !contemCoordenada(expandidas, proximoMovimento)       //Não voltamos a células já expandidas
                    ) {
                        //Adicionamos a coordenada do possivel movimento a lista de possiveis celulas
                        //O custo é determinado pelo custo da célula atual + 1
                        NossaCoordenada novaCoordenada = new NossaCoordenada(proximoMovimento, (atual.getCusto() + (14.0 / 3.5)));

                        abertas.add(novaCoordenada);

                        //Adicionamos a coorenada atual como Parente da nova
                        //Usaremos isso depois para criar o caminho
                        novaCoordenada.setParente(atual);
                    }
                }
            }
        }

        //Constroi o caminho inverso, por entre as celulas expandidas
        List<NossaCoordenada> caminho = constroiCaminho(expandidas);
        NossaCoordenada movimento = caminho.get(caminho.size() - 1);

        System.out.println("jogando");

        //Verifica a direção para ir do ponto onde a cobra está até o ponto desejado
        return getDirection(snake.getHead(), movimento.getCoordinate());
    }

    //Modo que analisa somente as possibilidades ao redor
    public Direction modoIngenuo(Snake snake, Snake opponent, Coordinate mazeSize, Coordinate apple) {
        List<NossaCoordenada> abertas = new ArrayList<>();       //Celulas possiveis de serem visitadas
        NossaCoordenada atual;                                   //A coordenada atual do loop
        
        //Adiciona a cabeça da cobra(origem) a lista de células abertas
        //O custo nesse caso é 0
        abertas.add(new NossaCoordenada(snake.getHead(), 0.0));
        atual = getMenorCusto(abertas, apple);
        
        //Se não chegou ao local desejado
        //Verifica os movimentos possiveis nos arredores
        for (Direction d : this.direcoes) {
            Coordinate proximoMovimento = snake.getHead().moveTo(d);

            if (!proximoMovimento.equals(getAntesDaCabeca(snake)) &&  //Não pode mover para tras e se matar
                proximoMovimento.inBounds(mazeSize) &&                //Não pode mover fora do tabuleiro
                !snake.elements.contains(proximoMovimento) &&         //Não pode mover para cima do seu corpo
                !opponent.elements.contains(proximoMovimento)        //Não pode mover para cima do inimigo
            ) {
                //Adicionamos a coordenada do possivel movimento a lista de possiveis celulas
                //O custo é determinado pelo custo da célula atual + 1
                abertas.add(new NossaCoordenada(proximoMovimento, (atual.getCusto() + (14.0 / 3.5))));
            }
        }

        //Verifica a direção para ir do ponto onde a cobra está até o ponto desejado
        return getDirection(snake.getHead(), getMenorCusto(abertas, apple).getCoordinate());
    }

    //Localiza a coordenada contraria a cabeça, que seria um movimento mortal
    public Coordinate getAntesDaCabeca(Snake snake) {
        Iterator<Coordinate> it = snake.body.iterator(); //Iterando sobre o deque que representa o corpo da cobra
        it.next(); //Coordenada da cabeça
        Coordinate antesCabeca = it.next(); //Coordenada mortal

        return antesCabeca;
    }

    //Recupera a coordenada de menor custo dentro da lista
    //Ignora custo 0
    //Custo = Custo + Distância Euclidiana
    public NossaCoordenada getMenorCusto(List<NossaCoordenada> coordenadas, Coordinate apple) {
        NossaCoordenada menorCusto = coordenadas.get(0);

        for (NossaCoordenada nc : coordenadas) {
            if (menorCusto.getCusto() == 0) {
                menorCusto = nc;
            } else if ((nc.getCusto() + getDistanciaEuclidiana(nc.getCoordinate(), apple)) < 
                       (menorCusto.getCusto() + getDistanciaEuclidiana(menorCusto.getCoordinate(), apple))) {
                menorCusto = nc;
            }
        }

        return menorCusto;
    }

    //Calcula a Distância Euclidiana entre o ponto atual e a maçã
    public Double getDistanciaEuclidiana(Coordinate nossaPosicao, Coordinate maca) {
        //Posição atual
        Double x1 = (double) nossaPosicao.getX();
        Double y1 = (double) nossaPosicao.getY();
        
        //Posição objetivo (maçã)
        Double x2 = (double) maca.getX();
        Double y2 = (double) maca.getY();

        //Fórmula da Distância Euclidiana
        double distancia = Math.sqrt((Math.pow((x1 - x2), 2)) + (Math.pow(y1 - y2, 2)));
        
        return distancia;
    }

    //Retorna direção para chegar ao ponto X
    public Direction getDirection(Coordinate snake, Coordinate coordenada) {

        if (snake.getX() == coordenada.getX()) {
            if (snake.getY() < coordenada.getY()) {
                //Vai para cima
                return direcoes.get(0);
            } else {
                //Vai para baixo
                return direcoes.get(1);
            }
        } else {
            if (snake.getX() < coordenada.getX()) {
                //Vai para direita
                return direcoes.get(2);
            } else {
                //Vai para esquerda
                return direcoes.get(3);
            }
        }
    }

    //Verificada se determinada coordenada esta entre os elementos da lista
    public boolean contemCoordenada(List<NossaCoordenada> lista, Coordinate coordenada) {
        for (NossaCoordenada nc : lista) {
            if (nc.getCoordinate().equals(coordenada)) {
                return true;
            }
        }

        return false;
    }
    
    //Constroi caminho inverso, da maçã até a cobra
    public List<NossaCoordenada> constroiCaminho(List<NossaCoordenada> expandidas) {
        List<NossaCoordenada> caminho = new ArrayList<>();
        NossaCoordenada celula = expandidas.get(expandidas.size() - 1);

        //Enquanto houverem parentes para serem verificados
        while (celula.getParente() != null) {
            caminho.add(celula);

            celula = celula.getParente();
        }

        return caminho;
    }
    
}
