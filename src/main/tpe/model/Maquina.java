package tpe.model;

public class Maquina {
    private String nombre;
    private int cantPiezas;

    public Maquina(String nombre, int cantPiezas) {
        this.nombre = nombre;
        this.cantPiezas = cantPiezas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantPiezas() {
        return cantPiezas;
    }

    public void setCantPiezas(int cantPiezas) {
        this.cantPiezas = cantPiezas;
    }

    @Override
    public String toString() {
        return "(" + nombre + ", " + cantPiezas + ")";
    }
}
