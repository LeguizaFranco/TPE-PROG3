# 🏭 TPE: Optimización de Producción de Autopartes 🧩

## ✨ Descripción del Proyecto

Este Trabajo Práctico Especial (TPE) aborda un problema clásico de optimización en una fábrica de autopartes. El objetivo principal es determinar la **secuencia óptima de máquinas** a utilizar para producir una **cantidad específica de piezas**, considerando restricciones energéticas y características particulares de cada máquina.

---

## 📋 Contexto del Problema

En nuestra fábrica:

- Cada máquina produce una **cantidad fija y predefinida** de piezas por cada puesta en funcionamiento ⚙️.
- Una vez que una máquina inicia su ciclo, **no puede detenerse** hasta completarlo.
- Por razones de eficiencia energética, **solo una máquina puede funcionar a la vez** ⚡.
- Las máquinas son **reutilizables sin restricciones**: pueden activarse múltiples veces.

El desafío consiste en encontrar la mejor combinación de máquinas para **cumplir con el objetivo de producción**, aplicando dos enfoques algorítmicos complementarios.

---

## 🚀 Algoritmos Implementados

Se desarrollaron dos enfoques distintos para resolver el problema:

### 🔍 Backtracking (Vuelta Atrás)

- Explora todas las combinaciones posibles de máquinas.
- Garantiza una **solución exacta**: produce exactamente la cantidad objetivo de piezas.
- Puede hallar una **solución óptima** (por ejemplo, con la menor cantidad de puestas en marcha), si se aplican técnicas de poda adecuadas.
- Recorre un **árbol de decisiones**, descartando ramas no viables o subóptimas.

### 💰 Greedy (Voraz)

- Toma decisiones localmente óptimas: selecciona en cada paso la máquina más productiva disponible.
- Busca alcanzar o **superar** la cantidad objetivo con la menor cantidad de ejecuciones.
- **No garantiza una solución exacta**, ya que puede sobreproducir.
- Es eficiente computacionalmente y útil como aproximación rápida.

---

## 🗂️ Estructura del Proyecto

```
├── src/
│   └── main/
│       └── tpe/
│           ├── model/
│           │   ├── Maquina.java              # Representación de una máquina
│           │   └── Solucion.java             # Estructura para almacenar soluciones
│           ├── service/
│           │   ├── BacktrackingSolucion.java  # Implementación del algoritmo Backtracking
│           │   ├── GreedySolucion.java        # Implementación del algoritmo Greedy
│           │   └── FileReader.java            # Utilidad para cargar datos desde archivo
│           ├── Main.java                     # Clase principal que ejecuta las soluciones
│           └── resources/
│               └── config/
│                   └── maquinas.txt           # Archivo de configuración con datos de las máquinas

```

---

## 👨‍💻 Clase Principal: `Main.java`

La clase `Main.java` se encarga de:

- Leer los datos desde el archivo de configuración.
- Instanciar y ejecutar ambos algoritmos.
- Mostrar por consola las soluciones encontradas, formateadas para una lectura clara.


## 🛠️ Nota Técnica

- Los datos de entrada se encuentran en `resources/config/maquinas.txt`.

---

## 🧪 Ejecución

Para ejecutar el proyecto:

1. Verificá que `maquinas.txt` contenga los datos correctos.
2. Ejecutá `Main.java`.
3. Observá por consola la salida de los algoritmos Backtracking y Greedy.
---

## 👥 Integrantes

- **Franco Leguiza**  
  📧 francoleguiza002@gmail.com
- **Valentino Copperi**  
  📧 vcopperimorazzo@alumnos.exa.unicen.edu.ar

---
