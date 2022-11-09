package control.bots;

import control.snakes.Coordinate;

public class NossaCoordenada {
    
    private Coordinate coordinate;    //A coordenada
    private Double custo;             //O custo

    public NossaCoordenada(Coordinate coordinate, Double custo) {
        this.coordinate = coordinate;
        this.custo = custo;
    }

    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    public Double getCusto() {
        return this.custo;
    } 

}
