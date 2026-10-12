// Subclase que representa simulaciones físicas con resolución de malla y obligatoriedad de GPU[cite: 12, 13, 20].
public class SimulacionFisica extends Trabajo {
    private int resolucionMalla; // 1 a 50 millones

    public SimulacionFisica(String codigo, String nombreEstudio, String nombreProyecto, double horasEstimadas, String horario, int resolucionMalla) {
        super(codigo, nombreEstudio, nombreProyecto, horasEstimadas, horario);
        this.resolucionMalla = resolucionMalla;
    }

    public int getResolucionMalla() {
        return resolucionMalla;
    }

    public void setResolucionMalla(int resolucionMalla) {
        this.resolucionMalla = resolucionMalla;
    }

    
    @Override
    public double calcularCosto() {
        return 40.0 + (this.horasEstimadas * 22.0) + 65.0;
    }

    @Override
    public int calcularTiempoEntrega() {
        return 18; 
    }

    @Override
    public int getMaxIntentos() {
        return 1;
    }

    @Override
    public boolean CompatibleConNodo(Nodo nodo) {
        return nodo.isTieneGpu();
    }

   
}