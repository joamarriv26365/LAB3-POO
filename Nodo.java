// Clase que modela los nodos de cómputo de la granja.
public class Nodo {
    private String codigo;
    private double capacidadHorasMax;
    private boolean tieneGpu;

    public Nodo(String codigo, double capacidadHorasMax, boolean tieneGpu) {
        this.codigo = codigo;
        this.capacidadHorasMax = capacidadHorasMax;
        this.tieneGpu = tieneGpu;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getCapacidadHorasMax() {
        return capacidadHorasMax;
    }

    public void setCapacidadHorasMax(double capacidadHorasMax) {
        this.capacidadHorasMax = capacidadHorasMax;
    }

    public boolean isTieneGpu() {
        return tieneGpu;
    }

    public void setTieneGpu(boolean tieneGpu) {
        this.tieneGpu = tieneGpu;
    }



    
}