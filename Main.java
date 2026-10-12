import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Sistema sistema = new Sistema();
        int opcion = 0;

        while (opcion != 9) {
            System.out.println("\n||GESTION PROGRAMA RENDERGT||");
            System.out.println("1. Registrar Nodo.");
            System.out.println("2. Cotizar Trabajo.");
            System.out.println("3. Registrar Trabajo (Imagen Fija | Animacion | Simulacion)");
            System.out.println("4. Crear Lote y Asignar Trabajo");
            System.out.println("5. Iniciar Lote");
            System.out.println("6. Registrar Resultado de Trabajo (EXITO|FALLO)");
            System.out.println("7. Ver Reporte Financiero y de Trabajos");
            System.out.println("8. Ver Reporte de Lotes Abiertos y Capacidad");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opcion: ");

            
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine();

                if (opcion == 1) {
                    
                    String cod = "";
                    while (cod.equals("")) {
                        System.out.print("Codigo del nodo: ");
                        cod = scanner.nextLine();
                        if (cod.equals("")) {
                            System.out.println("Error: El código no puede estar vacío.");
                        }
                    }
                    
                    
                    double cap = 0;
                    boolean capValida = false;
                    while (!capValida) {
                        System.out.print("Capacidad maxima de horas: ");
                        if (scanner.hasNextDouble()) {
                            cap = scanner.nextDouble();
                            scanner.nextLine();
                            if (cap > 0) {
                                capValida = true;
                            } else {
                                System.out.println("Error: La capacidad debe ser mayor a 0.");
                            }
                        } else {
                            System.out.println("Error: Debe ingresar un número válido.");
                            scanner.next();
                        }
                    }

                    
                    boolean gpu = false;
                    boolean gpuValida = false;
                    while (!gpuValida) {
                        System.out.print("Tiene GPU (true/false): ");
                        if (scanner.hasNextBoolean()) {
                            gpu = scanner.nextBoolean();
                            scanner.nextLine();
                            gpuValida = true;
                        } else {
                            System.out.println("Error: Debe ingresar true o false.");
                            scanner.next();
                        }
                    }

                    if (sistema.registrarNodo(new Nodo(cod, cap, gpu))) {
                        System.out.println("Nodo registrado exitosamente.");
                    } else {
                        System.out.println("Error: Codigo de nodo duplicado.");
                    }

                    }
                    
                    else if (opcion == 2 || opcion == 3) {
                    
                    
                    int tipo = 0;
                    boolean tipoValido = false;
                    while (!tipoValido) {
                        System.out.println("Tipo de trabajo: 1. Imagen Fija | 2. Animacion | 3. Simulacion Fisica | 4. Animacion con Postproduccion");
                        System.out.print("Seleccione una opcion valida (1-4): ");
                        if (scanner.hasNextInt()) {
                            tipo = scanner.nextInt();
                            scanner.nextLine();
                            if (tipo >= 1 && tipo <= 4) {
                                tipoValido = true;
                            } else {
                                System.out.println("Error: Opción fuera de rango (debe ser 1, 2, 3 o 4).");
                            }
                        } else {
                            System.out.println("Error: Ingrese un número entero válido.");
                            scanner.next();
                        }
                    }

                    // 2. Se piden los datos comunes
                    String cod = "";
                    while (cod.equals("")) {
                        System.out.print("Codigo del trabajo: ");
                        cod = scanner.nextLine();
                        if (cod.equals("")) {
                            System.out.println("Error: El código no puede estar vacío.");
                        }
                    }

                    String est = "";
                    while (est.equals("")) {
                        System.out.print("Nombre del estudio: ");
                        est = scanner.nextLine();
                        if (est.equals("")) {
                            System.out.println("Error: El nombre del estudio no puede estar vacío.");
                        }
                    }

                    String proy = "";
                    while (proy.equals("")) {
                        System.out.print("Nombre del proyecto: ");
                        proy = scanner.nextLine();
                        if (proy.equals("")) {
                            System.out.println("Error: El nombre del proyecto no puede estar vacío.");
                        }
                    }
                    
                    double hrs = 0;
                    boolean hrsValidas = false;
                    while (!hrsValidas) {
                        System.out.print("Horas estimadas: ");
                        if (scanner.hasNextDouble()) {
                            hrs = scanner.nextDouble();
                            scanner.nextLine();
                            if (hrs > 0) {
                                hrsValidas = true;
                            } else {
                                System.out.println("Error: Las horas deben ser mayores a 0.");
                            }
                        } else {
                            System.out.println("Error: Ingrese un valor numérico válido.");
                            scanner.next();
                        }
                    }

                    String hor = "";
                    boolean horValido = false;
                    while (!horValido) {
                        System.out.print("Horario (DIURNO / NOCTURNO): ");
                        hor = scanner.nextLine();
                        if (hor.equalsIgnoreCase("DIURNO") || hor.equalsIgnoreCase("NOCTURNO")) {
                            horValido = true;
                        } else {
                            System.out.println("Error: Debe escribir DIURNO o NOCTURNO.");
                        }
                    }


                    Trabajo t = null;
                    if (tipo == 1) {
                        t = new ImagenFija(cod, est, proy, hrs, hor);
                    } else if (tipo == 2) {
                        int fotos = 0;
                        boolean fotosValidas = false;
                        while (!fotosValidas) {
                            System.out.print("Cantidad de fotogramas: ");
                            if (scanner.hasNextInt()) {
                                fotos = scanner.nextInt();
                                scanner.nextLine();
                                if (fotos > 0) {
                                    fotosValidas = true;
                                } else {
                                    System.out.println("Error: Los fotogramas deben ser mayores a 0.");
                                }
                            } else {
                                System.out.println("Error: Ingrese un número entero.");
                                scanner.next();
                            }
                        }
                        t = new Animacion(cod, est, proy, hrs, hor, fotos);
                    } else if (tipo == 3) {
                        int malla = 0;
                        boolean mallaValida = false;
                        while (!mallaValida) {
                            System.out.print("Resolucion de malla (1-50 millones): ");
                            if (scanner.hasNextInt()) {
                                malla = scanner.nextInt();
                                scanner.nextLine();
                                if (malla >= 1 && malla <= 50) {
                                    mallaValida = true;
                                } else {
                                    System.out.println("Error: La resolución debe estar entre 1 y 50.");
                                }
                            } else {
                                System.out.println("Error: Ingrese un número entero.");
                                scanner.next();
                            }
                        }
                        t = new SimulacionFisica(cod, est, proy, hrs, hor, malla);
                    } else if (tipo == 4) {
                       
                        int fotos = 0;
                        boolean fotosValidas = false;
                        while (!fotosValidas) {
                            System.out.print("Cantidad de fotogramas: ");
                            if (scanner.hasNextInt()) {
                                fotos = scanner.nextInt();
                                scanner.nextLine();
                                if (fotos > 0) {
                                    fotosValidas = true;
                                } else {
                                    System.out.println("Error: Los fotogramas deben ser mayores a 0.");
                                }
                            } else {
                                System.out.println("Error: Ingrese un número entero.");
                                scanner.next();
                            }
                        }

                        
                        double recursos = 0;
                        boolean recursosValidos = false;
                        while (!recursosValidos) {
                            System.out.print("Valor de recursos licenciados:");
                            if (scanner.hasNextDouble()) {
                                recursos = scanner.nextDouble();
                                scanner.nextLine();
                                if (recursos >= 0) {
                                    recursosValidos = true;
                                } else {
                                    System.out.println("Error: El valor no puede ser negativo.");
                                }
                            } else {
                                System.out.println("Error: Ingrese un valor numérico válido.");
                                scanner.next();
                            }
                        }

                    
                        t = new AnimacionPostPro(cod, est, proy, hrs, hor, fotos, recursos);
                    }

                    if (t != null) {
                        boolean reglaValida = true;
                        if (t instanceof ImagenFija && t.getHorasEstimadas() > 2.0) {
                            reglaValida = false;
                        }
                        if (t instanceof SimulacionFisica && t.getHorario().equalsIgnoreCase("DIURNO")) {
                            reglaValida = false;
                        }

                        if (!reglaValida) {
                            System.out.println("Error: El trabajo viola restricciones de negocio.");
                        } else {
                            if (opcion == 2) {
                                System.out.println("\n--- COTIZACIÓN ---");
                                System.out.println("Costo estimado: Q" + t.calcularCosto());
                                System.out.println("Tiempo estimado de entrega: " + t.calcularTiempoEntrega() + " horas");
                            } else {
                                if (sistema.registrarTrabajo(t)) {
                                    System.out.println("Trabajo registrado con exito. Costo inicial cobrado: Q" + t.calcularCosto());
                                } else {
                                    System.out.println("Error: No se pudo registrar (verifique si el código del trabajo ya existe).");
                                }
                            }
                        }
                    }
                    
                
                

                } else if (opcion == 4) {
                   
                    String idLote = "";
                    while (idLote.equals("")) {
                        System.out.print("ID del nuevo lote: ");
                        idLote = scanner.nextLine();
                        if (idLote.equals("")) {
                            System.out.println("Error: El ID del lote no puede estar vacío.");
                        }
                    }

                   
                    String codNodo = "";
                    while (codNodo.equals("")) {
                        System.out.print("Codigo del nodo asociado: ");
                        codNodo = scanner.nextLine();
                        if (codNodo.equals("")) {
                            System.out.println("Error: El código del nodo no puede estar vacío.");
                        }
                    }

                    Lote lote = sistema.crearLote(idLote, codNodo);
                    if (lote != null) {
                        System.out.println("Lote creado en preparacion.");
                        
                        String codTrabajo = "";
                        while (codTrabajo.equals("")) {
                            System.out.print("Codigo de trabajo en cola a agregar: ");
                            codTrabajo = scanner.nextLine();
                            if (codTrabajo.equals("")) {
                                System.out.println("Error: El código del trabajo no puede estar vacío.");
                            }
                        }

                        if (sistema.asignarTrabajoALote(idLote, codTrabajo)) {
                            System.out.println("Trabajo agregado al lote correctamente.");
                        } else {
                            System.out.println("Error al asignar trabajo (incompatible con GPU, capacidad excedida o no existe).");
                        }
                    } else {
                        System.out.println("Error: Nodo ocupado, no encontrado o ID de lote duplicado.");
                    }

                } else if (opcion == 5) {
                    
                    String idLote = "";
                    while (idLote.equals("")) {
                        System.out.print("ID del lote a iniciar: ");
                        idLote = scanner.nextLine();
                        if (idLote.equals("")) {
                            System.out.println("Error: El ID no puede estar vacío.");
                        }
                    }

                    if (sistema.iniciarLote(idLote)) {
                        System.out.println("Lote iniciado en ejecucion exitosamente.");
                    } else {
                        System.out.println("Error al iniciar el lote (verifique que exista y no este vacio).");
                    }

                } else if (opcion == 6) {
                 
                    String idLote = "";
                    while (idLote.equals("")) {
                        System.out.print("ID del lote: ");
                        idLote = scanner.nextLine();
                        if (idLote.equals("")) {
                            System.out.println("Error: El ID no puede estar vacío.");
                        }
                    }

                    String codTrab = "";
                    while (codTrab.equals("")) {
                        System.out.print("Codigo del trabajo: ");
                        codTrab = scanner.nextLine();
                        if (codTrab.equals("")) {
                            System.out.println("Error: El código no puede estar vacío.");
                        }
                    }
                    
                   
                    boolean exito = false;
                    boolean exitoValido = false;
                    while (!exitoValido) {
                        System.out.print("Fue exitoso? (true = COMPLETADO, false = FALLIDO): ");
                        if (scanner.hasNextBoolean()) {
                            exito = scanner.nextBoolean();
                            scanner.nextLine();
                            exitoValido = true;
                        } else {
                            System.out.println("Error: Debe ingresar exactamente true o false.");
                            scanner.next();
                        }
                    }

                    if (sistema.registrarResultadoTrabajo(idLote, codTrab, exito)) {
                        System.out.println("Resultado registrado correctamente.");
                    } else {
                        System.out.println("Error al registrar resultado.");
                    }

                } else if (opcion == 7) {
                    System.out.println("\nREPORTE FINANCIERO Y GENERAL");
                    System.out.println("Ingresos por Registro Inicial: Q" + sistema.getIngresosRegistro());
                    System.out.println("Ingresos por Recargos de Reintentos: Q" + sistema.getIngresosReintentos());
                    System.out.println("Total Ingresos Generados: Q" + (sistema.getIngresosRegistro() + sistema.getIngresosReintentos()));
                    System.out.println("Total Trabajos Registrados: " + sistema.getTrabajos().size());
                    System.out.println("Total Lotes Creados: " + sistema.getLotes().size());

                } else if (opcion == 8) {
                    System.out.println(sistema.obtenerReporteLotesAbiertos());
                    System.out.println(sistema.obtenerConteoTrabajosPorEstado());
                }
            } else {
                System.out.println("Error: Ingrese un número de opción válido del menú principal.");
                scanner.next(); // Limpia la opción inválida
            }
        }
        scanner.close();
        System.out.println("Fin del programa.");
    }
}