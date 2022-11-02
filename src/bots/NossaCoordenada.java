package bots;

import snakes.Coordinate;

public class NossaCoordenada {
    
    private Coordinate coordinate;
    private int custo;

    public NossaCoordenada(Coordinate coordinate, int custo) {
        this.coordinate = coordinate;
        this.custo = custo;
    }

    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    public int getCusto() {
        return this.custo;
    } 

}
