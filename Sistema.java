import java.util.ArrayList;

public class Sistema {
    private ArrayList<Trabajo> trabajos;
    private ArrayList<Nodo> nodos;
    private ArrayList<Lote> lotes;
    private double ingresosRegistro;
    private double ingresosReintentos;

    public Sistema() {
        this.ingresosRegistro = 0;
        this.ingresosReintentos = 0;
        this.lotes = new ArrayList<>();
        this.nodos = new ArrayList<>();
        this.trabajos = new ArrayList<>();
    }

    
    //registra un trabajo en el sistema, validando restricciones de tipo y horario, y actualizando ingresos por registro.
    public boolean registrarTrabajo(Trabajo t) {
        if (buscarTrabajo(t.getCodigo()) != null)
             return false;
        if (t instanceof ImagenFija && t.getHorasEstimadas() > 2.0) 
            return false;
        if (t instanceof SimulacionFisica && t.getHorario().equalsIgnoreCase("DIURNO")) 
            return false;

        ingresosRegistro += t.calcularCosto();
        trabajos.add(t);
        return true;
    }

    public boolean registrarNodo(Nodo n) {
        if (buscarNodo(n.getCodigo()) != null)
             return false;
        nodos.add(n);
        return true;
    }

    public Lote crearLote(String idLote, String codigoNodo) {
        if (buscarLote(idLote) != null) 
            return null;
        Nodo nodo = buscarNodo(codigoNodo);
        if (nodo == null)
             return null;

        for (int i = 0; i < lotes.size(); i++) {
            Lote l = lotes.get(i);
            if (l.getNodoAsignado().getCodigo().equalsIgnoreCase(codigoNodo)) {
                if (!l.getEstado().equalsIgnoreCase("CERRADO")) 
                    return null;
            }
        }

        Lote nuevo = new Lote(idLote, nodo);
        lotes.add(nuevo);
        return nuevo;
    }

    public boolean asignarTrabajoALote(String idLote, String codigoTrabajo) {
        Lote lote = buscarLote(idLote);
        Trabajo trabajo = buscarTrabajo(codigoTrabajo);
        if (lote == null || trabajo == null) 
            return false;
        if (!trabajo.getEstado().equalsIgnoreCase("COLA")) 
            return false;
        return lote.agregarTrabajo(trabajo);
    }

    public boolean iniciarLote(String idLote) {
        Lote lote = buscarLote(idLote);
        if (lote == null) 
            return false;
        double recargos = lote.iniciarLote();
        if (recargos < 0) 
            return false;
        if (recargos > 0) ingresosReintentos += recargos;
        return true;
    }

    public boolean registrarResultadoTrabajo(String idLote, String codigoTrabajo, boolean exito) {
        Lote lote = buscarLote(idLote);
        if (lote == null || !lote.getEstado().equalsIgnoreCase("EN_EJECUCION")) 
            return false;

        Trabajo t = null;
        ArrayList<Trabajo> listaTrabajos = lote.getTrabajos();
        for (int i = 0; i < listaTrabajos.size(); i++) {
            if (listaTrabajos.get(i).getCodigo().equalsIgnoreCase(codigoTrabajo)) {
                t = listaTrabajos.get(i);
                break;
            }
        }

        if (t == null) 
            return false;

        if (exito) {
            t.setEstado("COMPLETADO");
        } else {
            t.registrarIntento();
            if (t.getIntentosRealizados() < t.getMaxIntentos()) {
                t.setEstado("COLA");
            } else {
                t.setEstado("CANCELADO");
            }
        }
        return true;
    }

    public String obtenerReporteLotesAbiertos() {
        String reporte = "\n||REPORTE LOTES NO CERRADOS||\n";
        boolean encontrados = false;
        
        for (int i = 0; i < lotes.size(); i++) {
            Lote l = lotes.get(i);
            if (!l.getEstado().equalsIgnoreCase("CERRADO")) {
                encontrados = true;
                reporte += "Lote ID: " + l.getIdLote() 
                        + " | Estado: " + l.getEstado()
                        + " | Nodo: " + l.getNodoAsignado().getCodigo()
                        + " | Horas Asignadas: " + l.calcularHorasAsignadas()
                        + " | Capacidad Disponible: " + l.calcularCapacidadDisponible() + " hrs\n";
            }
        }
        
        if (!encontrados) {
            reporte += "No hay lotes activos o abiertos en este momento.\n";
        }
        return reporte;
    }

    public String obtenerConteoTrabajosPorEstado() {
        int fijas = 0;
        int animaciones = 0;
        int postpro = 0;
        int simulaciones = 0;
        
        for (int i = 0; i < trabajos.size(); i++) {
            Trabajo t = trabajos.get(i);
            if (t instanceof AnimacionPostPro) {
                postpro++;
            } else if (t instanceof Animacion) {
                animaciones++;
            } else if (t instanceof ImagenFija) {
                fijas++;
            } else if (t instanceof SimulacionFisica) {
                simulaciones++;
            }
        }

        return "\n|| CONTADOR DE TRABAJOS POR CATEGORIA ||\n"
             + "- Imágenes Fijas: " + fijas + "\n"
             + "- Animaciones: " + animaciones + "\n"
             + "- Animaciones con Postproducción: " + postpro + "\n"
             + "- Simulaciones Físicas: " + simulaciones + "\n"
             + "Total general de trabajos en sistema: " + trabajos.size();
    }

    public Trabajo buscarTrabajo(String codigo) {
        for (int i = 0; i < trabajos.size(); i++) {
            if (trabajos.get(i).getCodigo().equalsIgnoreCase(codigo))
                 return trabajos.get(i);
        }
        return null;
    }

    public Nodo buscarNodo(String codigo) {
        for (int i = 0; i < nodos.size(); i++) {
            if (nodos.get(i).getCodigo().equalsIgnoreCase(codigo)) 
                return nodos.get(i);
        }
        return null;
    }

    public Lote buscarLote(String idLote) {
        for (int i = 0; i < lotes.size(); i++) {
            if (lotes.get(i).getIdLote().equalsIgnoreCase(idLote))
                 return lotes.get(i);
        }
        return null;
    }

    public ArrayList<Trabajo> getTrabajos() {
        return trabajos;
    }

    public void setTrabajos(ArrayList<Trabajo> trabajos) {
        this.trabajos = trabajos;
    }

    public ArrayList<Nodo> getNodos() {
        return nodos;
    }

    public void setNodos(ArrayList<Nodo> nodos) {
        this.nodos = nodos;
    }

    public ArrayList<Lote> getLotes() {
        return lotes;
    }

    public void setLotes(ArrayList<Lote> lotes) {
        this.lotes = lotes;
    }

    public double getIngresosRegistro() {
        return ingresosRegistro;
    }

    public void setIngresosRegistro(double ingresosRegistro) {
        this.ingresosRegistro = ingresosRegistro;
    }

    public double getIngresosReintentos() {
        return ingresosReintentos;
    }

    public void setIngresosReintentos(double ingresosReintentos) {
        this.ingresosReintentos = ingresosReintentos;
    }

    
}