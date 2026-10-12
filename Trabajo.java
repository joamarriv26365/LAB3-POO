// Clase abstracta base que representa cualquier tipo de trabajo de renderizado[cite: 12, 20, 42].
public abstract class Trabajo {
    // Atributos protegidos accesibles para la clase y sus subclases[cite: 20, 35].
    protected String codigo;
    protected String nombreEstudio;
    protected String nombreProyecto;
    protected double horasEstimadas;
    protected String horario; 
    protected String estado;  
    protected int intentosRealizados;

    // Constructor, this.estado es igual a "COLA" porque este esta por defecto en cola, y intentosRealizados es igual a 0 porque al crear un trabajo no se ha intentado ejecutar.
    public Trabajo(String codigo, String nombreEstudio, String nombreProyecto, double horasEstimadas, String horario) {
        this.codigo = codigo;
        this.nombreEstudio = nombreEstudio;
        this.nombreProyecto = nombreProyecto;
        this.horasEstimadas = horasEstimadas;
        this.horario = horario;
        this.estado = "COLA";
        this.intentosRealizados = 0;
    }

    



    public String getCodigo() {
        return codigo;
    }





    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }





    public String getNombreEstudio() {
        return nombreEstudio;
    }





    public void setNombreEstudio(String nombreEstudio) {
        this.nombreEstudio = nombreEstudio;
    }





    public String getNombreProyecto() {
        return nombreProyecto;
    }





    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }





    public double getHorasEstimadas() {
        return horasEstimadas;
    }





    public void setHorasEstimadas(double horasEstimadas) {
        this.horasEstimadas = horasEstimadas;
    }





    public String getHorario() {
        return horario;
    }





    public void setHorario(String horario) {
        this.horario = horario;
    }





    public String getEstado() {
        return estado;
    }





    public void setEstado(String estado) {
        this.estado = estado;
    }





    public int getIntentosRealizados() {
        return intentosRealizados;
    }





    public void setIntentosRealizados(int intentosRealizados) {
        this.intentosRealizados = intentosRealizados;
    }





    // Métodos abstractos que obligan a las subclases a implementar su propia versión.
    public abstract double calcularCosto();
    public abstract int calcularTiempoEntrega();
    public abstract int getMaxIntentos();

    // Método base para verificar si los nodos son compatibles.
    public boolean CompatibleConNodo(Nodo nodo) {
        return true;
    }

    // Sube el contador de intentos al fallar la ejecución.
    public void registrarIntento() {
        this.intentosRealizados++;
    }

}