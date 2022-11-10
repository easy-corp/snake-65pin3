package control.bots;

import control.snakes.Coordinate;

public class NossaCoordenada {
    
    private Coordinate coordinate;    //A coordenada
    private Double custo;             //O custo
    private NossaCoordenada parente;  //O objeto que gerou esse aqui, usado para reconstruir o caminho

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

    public void setParente(NossaCoordenada parente) {
        this.parente = parente;
    }

    public NossaCoordenada getParente() {
        return this.parente;
    }

}
