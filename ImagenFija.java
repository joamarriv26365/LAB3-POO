
public class ImagenFija extends Trabajo {

    // Constructor, no hay nuevos atributos.
    public ImagenFija(String codigo, String nombreEstudio, String nombreProyecto, double horasEstimadas, String horario) {
        super(codigo, nombreEstudio, nombreProyecto, horasEstimadas, horario);
    }



    // Calculo de costo para la imagen.
    @Override
    public double calcularCosto() {
        if (horario.equalsIgnoreCase("DIURNO")) {
            return 45.0;
        } else {
            return 30.0;
        }
    }

    // Cálculo de tiempo de entrega para la imagen.
    @Override
    public int calcularTiempoEntrega() {
        if (horario.equalsIgnoreCase("DIURNO")) {
            return 4;
        } else {
            return 10;
        }
    }

    // Límite máximo de reintentos permitidos para la imagen.
    @Override
    public int getMaxIntentos() {
        return 3;
    }
}