package tpe;
import tpe.model.Maquina;
import tpe.model.Solucion;
import tpe.service.BacktrackingSolucion;
import tpe.service.FileReader;
import tpe.service.GreedySolucion;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception {
        // Instanciamos el FileReader
        FileReader fileReader = new FileReader();

        // Cargar configuración de máquinas y piezas totales desde el archivo

        Map<String, Object> config = fileReader.readConfigFile();
        int piezasTotales = (int) config.get("piezasTotales");
        List<Maquina> maquinas = (List<Maquina>) config.get("maquinas");

        // Las validaciones de piezasTotales <= 0 y maquinas.isEmpty() ya se hacen
        // en FileReader y lanzan excepciones

        System.out.println("--------------------------------------------------");
        System.out.println("Iniciando resolución del problema para " + piezasTotales + " piezas.");
        System.out.println("Máquinas disponibles: " + maquinas);
        System.out.println("--------------------------------------------------");

        // --- Resolución con Backtracking ---
        System.out.println("\n--- Resolución con Backtracking ---");
        BacktrackingSolucion backtrackingSolucion = new BacktrackingSolucion();
        Solucion backtrackingSolution = backtrackingSolucion.solucion(piezasTotales, maquinas);

        if (backtrackingSolution != null) {
            System.out.println("✔️ Solución Backtracking encontrada:");
            System.out.println("   Secuencia de máquinas: " + backtrackingSolution.getSecuenciaMaquinas());
            System.out.println("   Piezas producidas: " + backtrackingSolution.getPiezasProducidas() +
                    ", Puestas en funcionamiento: " + backtrackingSolution.getPuestasEnMarcha());
            System.out.println("   Métrica (estados generados): " + backtrackingSolution.getMetricaCosto());
        } else {
            System.out.println("❌ No se encontró una solución válida con Backtracking para " + piezasTotales + " piezas.");
        }

        // --- Resolución con Greedy ---
        System.out.println("\n--- Resolución con Greedy ---");
        GreedySolucion greedySolucion = new GreedySolucion();
        Solucion greedySolution = greedySolucion.solucion(piezasTotales, maquinas);

        if (greedySolution != null) {
            System.out.println("✔️ Solución Greedy encontrada:");
            System.out.println("   Secuencia de máquinas: " + greedySolution.getSecuenciaMaquinas());
            System.out.println("   Piezas producidas: " + greedySolution.getPiezasProducidas() +
                    ", Puestas en funcionamiento: " + greedySolution.getPuestasEnMarcha());
            System.out.println("   Métrica (candidatos considerados): " + greedySolution.getMetricaCosto());
        } else {
            System.out.println("❌ No se encontró una solución válida con Greedy para " + piezasTotales + " piezas.");
        }
    }
}
