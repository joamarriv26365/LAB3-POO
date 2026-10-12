import java.util.ArrayList;

public class Lote {
    private String idLote;
    private Nodo nodoAsignado;
    private ArrayList<Trabajo> trabajos;
    private String estado; 


        //  consructor, con estado incial preparacion ya que así lo indican los requisitos funcionales.
    public Lote(String idLote, Nodo nodoAsignado) {
        this.idLote = idLote;
        this.nodoAsignado = nodoAsignado;
        this.trabajos = new ArrayList<Trabajo>();
        this.estado = "PREPARACION";
    }

    // suma los horas estimadas de todos los trabajos en el lote para calcular la carga total de trabajo
    public double calcularHorasAsignadas() {
        double total = 0.0;
        for (int i = 0; i < trabajos.size(); i++) {
            total += trabajos.get(i).getHorasEstimadas();
        }
        return total;
    }
        // calcula la capacidad disponible del nodo asignado restando las horas asignadas de la capacidad máxima del nodo
    public double calcularCapacidadDisponible() {
        return nodoAsignado.getCapacidadHorasMax() - calcularHorasAsignadas();
    }

    // Valida espacio y compatibilidad antes de agregar un trabajo a la lista
    public boolean agregarTrabajo(Trabajo t) {
        if (!estado.equals("PREPARACION")) 
            return false;
        if (!t.CompatibleConNodo(nodoAsignado)) 
            return false;
        if (calcularHorasAsignadas() + t.getHorasEstimadas() > nodoAsignado.getCapacidadHorasMax()) 
            return false;
        
        trabajos.add(t);
        t.setEstado("EN_LOTE");
        return true;
    }

    public boolean retirarTrabajo(Trabajo t) {
        if (!estado.equals("PREPARACION")) 
            return false;
        if (trabajos.remove(t)) {
            t.setEstado("COLA");
            return true;
        
        }
        return false;
    }

    // Pasa el lote a ejecución y calcula recargos de Q25 para trabajos que requieran reintentos.
    public double iniciarLote() {
        if (!estado.equals("PREPARACION") || trabajos.isEmpty()) return -1.0;
        
        this.estado = "EN EJECUCION";
        double recargosTotales = 0.0;

        for (int i = 0; i < trabajos.size(); i++) {
            Trabajo t = trabajos.get(i);
            t.setEstado("EN EJECUCION");
            if (t.getIntentosRealizados() > 0) {
                recargosTotales += 25.0; 
            }
        }
        return recargosTotales;
    }

    public void cerrarLote() {
        this.estado = "CERRADO";
    }

    public String getIdLote() {
        return idLote;
    }

    public void setIdLote(String idLote) {
        this.idLote = idLote;
    }

    public Nodo getNodoAsignado() {
        return nodoAsignado;
    }

    public void setNodoAsignado(Nodo nodoAsignado) {
        this.nodoAsignado = nodoAsignado;
    }

    public ArrayList<Trabajo> getTrabajos() {
        return trabajos;
    }

    public void setTrabajos(ArrayList<Trabajo> trabajos) {
        this.trabajos = trabajos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
}