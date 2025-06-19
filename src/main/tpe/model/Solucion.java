package tpe.model;

import java.util.ArrayList;
import java.util.List;

public class Solucion {
    private List<Maquina> secuenciaMaquinas;
    private int piezasProducidas;
    private int puestasEnMarcha;
    private int metricaCosto;

    public Solucion(List<Maquina> secuenciaMaquinas, int piezasProducidas, int puestasEnMarcha, int metricaCosto) {
        this.secuenciaMaquinas = new ArrayList<>(secuenciaMaquinas);
        this.piezasProducidas = piezasProducidas;
        this.puestasEnMarcha = puestasEnMarcha;
        this.metricaCosto = metricaCosto;
    }

    public List<Maquina> getSecuenciaMaquinas() {
        return new ArrayList<>(secuenciaMaquinas);
    }

    public int getPiezasProducidas() {
        return piezasProducidas;
    }

    public int getPuestasEnMarcha() {
        return puestasEnMarcha;
    }

    public int getMetricaCosto() {
        return metricaCosto;
    }

    @Override
    public String toString() {
        return "Solucion{" +
                "secuenciaMaquinas=" + getSecuenciaMaquinas() +
                ", piezasProducidas=" + getPiezasProducidas() +
                ", puestasEnMarcha=" + getPuestasEnMarcha() +
                ", metricaCosto=" + getMetricaCosto() +
                '}';
    }
}
