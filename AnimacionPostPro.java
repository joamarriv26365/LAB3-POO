// Subclase que hereda directamente de Animacion aplicando reutilización y polimorfismo[cite: 12, 20].
public class AnimacionPostPro extends Animacion {
    private double valorRecursosLicenciados;

    // Constructor que inicializa atributos propios y pasa los de animación mediante super[cite: 6, 21, 39].
    public AnimacionPostPro(String codigo, String nombreEstudio, String nombreProyecto, double horasEstimadas, String horario, int cantidadFotogramas, double valorRecursosLicenciados) {
        super(codigo, nombreEstudio, nombreProyecto, horasEstimadas, horario, cantidadFotogramas);
        this.valorRecursosLicenciados = valorRecursosLicenciados;
    }

    public double getValorRecursosLicenciados() { 
        return valorRecursosLicenciados; }

        @Override
    public double calcularCosto() {
        double costoAnimacion = super.calcularCosto();
        return costoAnimacion + 80.0 + (this.valorRecursosLicenciados * 0.05);
    }

    @Override
    public int calcularTiempoEntrega() {
        return super.calcularTiempoEntrega() + 12;
    }

    @Override
    public int getMaxIntentos() {
        return 2;
    }

    

    public void setValorRecursosLicenciados(double valorRecursosLicenciados) {
        this.valorRecursosLicenciados = valorRecursosLicenciados;
    }
}