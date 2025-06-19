package tpe.service;
import tpe.model.Maquina;
import tpe.model.Solucion;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/*
 * Breve explicación de la estrategia de resolución:
 * El algoritmo Greedy (Voraz) construye una solución tomando la decisión localmente
 * óptima en cada paso, con la esperanza de encontrar un óptimo global. Para este problema
 * (con máquinas reutilizables), la heurística es siempre seleccionar la máquina que produce
 * la mayor cantidad de piezas en cada puesta en marcha.
 *
 * - Cuáles son los candidatos:
 * En cada paso de la construcción de la secuencia, todos los tipos de máquinas disponibles
 * (es decir, la lista inicial de máquinas que se pueden utilizar) son candidatos.
 *
 * - Estrategia de selección de candidatos:
 * Las máquinas se ordenan una única vez, de mayor a menor cantidad de piezas que producen.
 * En cada paso del algoritmo, siempre se selecciona la máquina que se encuentra
 * al principio de esta lista ordenada (es decir, la más productiva).
 *
 * - Consideraciones respecto a encontrar o no solución:
 * - Si existe al menos una máquina que produce una cantidad positiva de piezas y 'piezasObjetivo'
 * es mayor que cero, este algoritmo Greedy garantizará que se alcance o la 'piezasObjetivo'.
 * Cuando se selecciona un candidato y la suma total supera 'piezasObjetivo', ese número se descarta de la lista de candidatos.
 * Así, el algoritmo va limpiando automáticamente los elementos que no pueden aportar a una solución válida.
 */

public class GreedySolucion {
    private int piezasObjetivo;
    private int piezasActuales;
    private int puestasEnMarchaActual;
    private List<Maquina> maquinasDisponibles;
    private Solucion solucion;
    private int estadosGenerados; // metrica para el costo

    public Solucion solucion(int piezasObjetivo, List<Maquina> maquinasDisponibles) {
        this.piezasObjetivo = piezasObjetivo;
        this.maquinasDisponibles = maquinasDisponibles;
        this.piezasActuales = 0;
        this.puestasEnMarchaActual = 0;
        this.solucion = null;
        this.estadosGenerados = 0;
        List<Maquina> secuenciaActual = new LinkedList<>();

        maquinasDisponibles.sort(Comparator.comparingInt(Maquina::getCantPiezas).reversed());
        greedy(maquinasDisponibles, secuenciaActual, piezasActuales, puestasEnMarchaActual);
        return solucion;
    }

    private void greedy(List<Maquina> maquinasDisponibles, List<Maquina> secuenciaActual, int piezasActuales, int puestasEnMarchaActual) {
        while (!maquinasDisponibles.isEmpty()) {
            if (piezasActuales == piezasObjetivo) {
                solucion = new Solucion(secuenciaActual, piezasActuales, puestasEnMarchaActual, estadosGenerados);
                maquinasDisponibles.clear();
            } else {
                // si es factible
                if ((piezasActuales + maquinasDisponibles.getFirst().getCantPiezas()) <= piezasObjetivo) {
                    secuenciaActual.add(maquinasDisponibles.getFirst());
                    piezasActuales += maquinasDisponibles.getFirst().getCantPiezas();
                    puestasEnMarchaActual++;
                } else {
                    // si no es factible, es mayor a cant de piezas
                    maquinasDisponibles.removeFirst();
                }
            }
            estadosGenerados++;
        }
    }
}

