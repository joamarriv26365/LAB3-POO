public class Animacion extends Trabajo {
    protected int cantidadFotogramas;

    // Constructor 
    public Animacion(String codigo, String nombreEstudio, String nombreProyecto, double horasEstimadas, String horario, int cantidadFotogramas) {
        super(codigo, nombreEstudio, nombreProyecto, horasEstimadas, horario);
        this.cantidadFotogramas = cantidadFotogramas;
    }

    

    // Calcula las horas equivalentes según fotogramas (1 fotograma = 0.02 horas)
    public double calcularHorasFotogramas() {
        return this.cantidadFotogramas * 0.02;
    }

    // Determina las horas facturables usando Math.max y redondeo superior Math.ceil
    public double calcularHorasFacturables() {
        double horasFotos = calcularHorasFotogramas();
        double maxHoras = 0.0;
        
    
        if (this.horasEstimadas > horasFotos) {
            maxHoras = this.horasEstimadas;
        } else {
            maxHoras = horasFotos;
        }
        
        return Math.ceil(maxHoras);
    }

    // Cálculo polimórfico del costo por animación si el horario es diurno de
    @Override
    public double calcularCosto() {
        double tarifaBase = 0.0;
        
        
        if (horario.equalsIgnoreCase("DIURNO")) {
            tarifaBase = 60.0;
        } else {
            tarifaBase = 40.0;
        }
        
        return tarifaBase + (calcularHorasFacturables() * 15.0);
    }

    //max intentos de animación es 3, esto solo devuelve eso.

    @Override
    public int getMaxIntentos() {
        return 3;
    }

    @Override
    public int calcularTiempoEntrega() {
        int tiempo = 0;
        if (horario.equalsIgnoreCase("DIURNO")) {
            tiempo = 24;
        } else {
            tiempo = 36;
        }
        return tiempo;
    }

    public int getCantidadFotogramas() {
        return cantidadFotogramas;
    }

    public void setCantidadFotogramas(int cantidadFotogramas) {
        this.cantidadFotogramas = cantidadFotogramas;
    }
}