package tpe.service;

import tpe.model.Maquina;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileReader {

    public Map<String, Object> readConfigFile() throws IOException, NumberFormatException, IllegalArgumentException {
        String filePath = "src/main/resources/config/maquinas.txt";
        int piezasTotales = 0;
        List<Maquina> maquinas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new java.io.FileReader(filePath))) {
            String line;
            int lineNumber = 0;
            boolean firstLineProcessed = false;

            while ((line = br.readLine()) != null) {
                lineNumber++;
                line = line.trim();

                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }

                if (!firstLineProcessed) {
                    try {
                        piezasTotales = Integer.parseInt(line);
                        if (piezasTotales <= 0) {
                            throw new IllegalArgumentException("La cantidad total de piezas (" + piezasTotales + ") en la línea " + lineNumber + " debe ser un número positivo.");
                        }
                        firstLineProcessed = true;
                    } catch (NumberFormatException e) {
                        throw new NumberFormatException("Formato inválido para la cantidad total de piezas en la línea " + lineNumber + ": '" + line + "'");
                    }
                } else {
                    String[] parts = line.split(",");
                    if (parts.length == 2) {
                        String nombre = parts[0].trim();
                        int piezasProduce;
                        try {
                            piezasProduce = Integer.parseInt(parts[1].trim());
                        } catch (NumberFormatException e) {
                            throw new NumberFormatException("Formato inválido para las piezas de la máquina '" + nombre + "' en la línea " + lineNumber + ": '" + parts[1] + "'");
                        }

                        if (piezasProduce > 0) {
                            maquinas.add(new Maquina(nombre, piezasProduce));
                        } else {
                            System.out.println("Advertencia: Máquina '" + nombre + "' en línea " + lineNumber + " descartada porque produce " + piezasProduce + " piezas (valor inválido o cero).");
                        }
                    } else {
                        System.err.println("Advertencia: Línea con formato inválido en la línea " + lineNumber + " (se espera 'Nombre,Piezas'): '" + line + "'");
                    }
                }
            }
        }

        if (piezasTotales == 0) {
            throw new IllegalArgumentException("El archivo debe especificar una cantidad total de piezas positiva como primera línea válida.");
        }
        if (maquinas.isEmpty()) {
            throw new IllegalArgumentException("No se encontraron máquinas válidas en el archivo de configuración.");
        }

        Map<String, Object> config = new HashMap<>();
        config.put("piezasTotales", piezasTotales);
        config.put("maquinas", maquinas);

        return config;
    }
}
