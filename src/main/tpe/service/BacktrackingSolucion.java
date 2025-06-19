package tpe.service;
import tpe.model.Maquina;
import tpe.model.Solucion;
import java.util.LinkedList;
import java.util.List;

/*
 * SOLUCIÓN BACKTRACKING
 * -Breve explicación de la estrategia de resolución:
 * El algoritmo de Backtracking (o Vuelta Atrás) es una técnica de búsqueda sistemática
 * que explora todas las posibles combinaciones de máquinas para encontrar una solución
 * óptima (generalmente, la que produce EXACTAMENTE las piezas objetivo con el menor
 * número de puestas en marcha). Construye una solución de forma incremental, y si en
 * algún punto determina que el camino actual no puede llevar a una solución válida
 * o mejor que la encontrada hasta ahora, "retrocede" (backtracks) para probar otra alternativa.
 *
 *
 * - Cómo se genera el árbol de exploración:
 * Cada nodo en el árbol de exploración representa un estado parcial de la solución,
 * es decir, una secuencia de máquinas ya elegidas y las piezas producidas hasta ese punto.
 * Los hijos de un nodo se generan al considerar cada máquina disponible como el "siguiente candidato"
 * para ser añadida a la secuencia actual. La profundidad del árbol está limitada por
 * la cantidad máxima de puestas en marcha (piezasObjetivo / minPiezasPorMaquina, como estimación).
 *
 *
 * - Cuáles son los estados finales y estados solución:
 * - Estado final: Es un estado donde se ha tomado una decisión para cada "posición" en la secuencia
 * (o cuando se ha alcanzado/excedido el objetivo de piezas), o cuando no quedan más máquinas
 * para considerar en una rama particular.
 *
 * - Estado solución: Es un estado final donde la cantidad de piezas producidas es
 * EXACTAMENTE igual a 'piezasObjetivo', y opcionalmente, esta solución es
 * la que minimiza las 'puestasEnMarcha' entre todas las soluciones exactas encontradas.
 *
 *
 * - Posibles podas (poda por rama o por factibilidad):
 * - Poda por exceso de piezas: Si la 'cantidadDePiezasActuales' ya es mayor que 'piezasObjetivo',
 * esa rama se poda, ya que no puede llevar a una solución exacta (no podemos "quitar" piezas).
 *
 * - Poda por límite de puestas en marcha: Si la 'cantidadDePuestasEnMarchaActuales'
 * ya es igual o mayor que el número de puestas en marcha de la 'mejorSolucion' encontrada hasta el momento,
 * y la 'cantidadDePiezasActuales' aún no es igual a 'piezasObjetivo', se poda la rama.
 * (Esto optimiza la búsqueda de la *mejor* solución exacta).
 */


public class BacktrackingSolucion {
    private int piezasObjetivo;
    private List<Maquina> maquinasDisponibles;
    private Solucion mejorSolucion;
    private int estadosGenerados; // metrica para el costo


    public Solucion solucion(int piezasObjetivo, List<Maquina> maquinasDisponibles) {
        this.piezasObjetivo = piezasObjetivo;
        this.maquinasDisponibles = maquinasDisponibles;
        this.mejorSolucion = null;
        this.estadosGenerados = 0;

        List<Maquina> secuenciaActual = new LinkedList<>();
        backtrack(secuenciaActual, 0, 0);
        return mejorSolucion;
    }

    private void backtrack(List<Maquina> secuenciaActual, int piezasActuales, int puestasEnMarchaActual) {
        // Contar cada "estado" o "nodo" visitado
        estadosGenerados++;

        // Poda 1: Si ya encontramos una solución y la actual requiere más o igual puestas en marcha,
        // no tiene sentido seguir por este camino.
        if (mejorSolucion != null && puestasEnMarchaActual >= mejorSolucion.getPuestasEnMarcha()) {
            return;
        }

        // Poda 2: Si nos exedimos del total de piezas que tenemos como objetivo, este camino es inválido.
        if (piezasActuales > piezasObjetivo) {
            return;
        }

        // Caso base: Si alcanzamos el objetivo de piezas
        if (piezasActuales == piezasObjetivo) {
            if (mejorSolucion == null || puestasEnMarchaActual < mejorSolucion.getPuestasEnMarcha()) {
                mejorSolucion = new Solucion(new LinkedList<>(secuenciaActual), piezasActuales, puestasEnMarchaActual, estadosGenerados);
            }
        }

        // Si todavía no llegamos al objetivo (piezasObjetivo), exploramos más maquinas.
        for (Maquina maquina : maquinasDisponibles) {
            secuenciaActual.add(maquina);
            backtrack(secuenciaActual, piezasActuales + maquina.getCantPiezas(), puestasEnMarchaActual + 1);
            secuenciaActual.removeLast();
        }
    }

}
