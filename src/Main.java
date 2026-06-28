import java.time.LocalDate;
import java.util.Scanner;

//Muestra el menú, lee al usuario y llama a la agencia.
public class Main {

    //Se declara el scanner y el contador de reservas como estaticos para usarlos en todo el menu
    private static final Scanner sc = new Scanner(System.in);
    private static int contadorReservas = 1;

    public static void main(String[] args) {
        //Se crea la agencia y se precargan los datos de ejemplo
        AgenciaDeViajes agencia = new AgenciaDeViajes("Costa Rica Tours");
        precargarDatos(agencia);

        //Se controla el ciclo del menu con esta variable
        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            String opcion = sc.nextLine().trim();

            //Se envuelve cada opcion en un try/catch para mostrar los errores de validacion sin que el programa se caiga
            try {
                switch (opcion) {
                    case "1" -> registrarCliente(agencia);
                    case "2" -> agencia.mostrarCatalogo();
                    case "3" -> crearReserva(agencia);
                    case "4" -> generarFactura(agencia);
                    case "5" -> agencia.listarReservas();
                    case "0" -> {
                        salir = true;
                        System.out.println("¡Gracias por usar Costa Rica Tours!");
                    }
                    default -> System.out.println("Opción inválida, intente de nuevo.");
                }
            } catch (Exception e) {
                //Se captura cualquier error de validacion y se muestra su mensaje al usuario
                System.out.println("Error: " + e.getMessage());
            }

            System.out.println();
        }
    }

    //Se muestra el menu principal de opciones
    private static void mostrarMenu() {
        System.out.println("===== COSTA RICA TOURS =====");
        System.out.println("1. Registrar cliente");
        System.out.println("2. Ver catálogo de tours");
        System.out.println("3. Crear reserva (con su pago)");
        System.out.println("4. Generar factura de una reserva");
        System.out.println("5. Listar reservas");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    //Se piden los datos del cliente y se registra en la agencia
    private static void registrarCliente(AgenciaDeViajes agencia) {
        System.out.print("Nombre del cliente: ");
        String nombre = sc.nextLine();
        System.out.print("Cédula (9 dígitos): ");
        String cedula = sc.nextLine();
        System.out.print("Correo: ");
        String correo = sc.nextLine();
        System.out.print("Teléfono: ");
        String telefono = sc.nextLine();

        //Se crea el cliente (si algun dato es invalido, el constructor lanza la excepcion y la atrapa el main)
        Cliente cliente = new Cliente(nombre, cedula, correo, telefono);
        agencia.registrarCliente(cliente);
        System.out.println("Cliente registrado: " + cliente);
    }

    //Se crea una reserva pidiendo cliente, tour, cantidad de personas y el pago
    private static void crearReserva(AgenciaDeViajes agencia) {
        //Se valida primero que existan clientes registrados antes de crear una reserva
        if (agencia.getClientes().isEmpty()) {
            System.out.println("Debe registrar al menos un cliente antes de crear una reserva.");
            return;
        }

        //Se muestran los clientes disponibles para que el usuario elija por numero
        System.out.println("--- Clientes disponibles ---");
        for (int i = 0; i < agencia.getClientes().size(); i++) {
            System.out.println((i + 1) + ". " + agencia.getClientes().get(i));
        }
        System.out.print("Seleccione el número de cliente: ");
        int indiceCliente = Integer.parseInt(sc.nextLine().trim()) - 1;
        Cliente cliente = agencia.getClientes().get(indiceCliente);

        //Se muestra el catálogo y se pide el codigo del tour deseado
        agencia.mostrarCatalogo();
        System.out.print("Escriba el código del tour (ej. TOU-0001): ");
        String codigoTour = sc.nextLine();
        //Se busca el tour por codigo (lanza excepcion si no existe)
        Tour tour = agencia.buscarTourPorCodigo(codigoTour);

        //Se pide la cantidad de personas
        System.out.print("Cantidad de personas: ");
        int cantidad = Integer.parseInt(sc.nextLine().trim());

        //Se calcula el total esperado para mostrarselo al usuario antes de pedir el pago
        double total = tour.getPrecio() * cantidad;
        System.out.println("Total a pagar: ₡" + total);

        //Se piden los datos del pago
        System.out.print("Método de pago (Efectivo, Tarjeta, SINPE): ");
        String metodo = sc.nextLine();
        //Se crea el pago con el monto total, el metodo elegido y la fecha de hoy
        Pago pago = new Pago(total, metodo, LocalDate.now());

        //Se genera el codigo automatico de la reserva y se crea la reserva en la agencia
        String codReserva = generarCodigoReserva();
        Reserva reserva = agencia.crearReserva(codReserva, cliente, tour, cantidad, pago);
        System.out.println("Reserva creada con éxito:");
        System.out.println(reserva);
    }

    //Se genera la factura de una reserva elegida por el usuario
    private static void generarFactura(AgenciaDeViajes agencia) {
        //Se valida primero que existan reservas antes de generar una factura
        if (agencia.getReservas().isEmpty()) {
            System.out.println("No hay reservas registradas para facturar.");
            return;
        }

        //Se muestran las reservas disponibles para que el usuario elija por numero
        System.out.println("--- Reservas disponibles ---");
        for (int i = 0; i < agencia.getReservas().size(); i++) {
            System.out.println((i + 1) + ". " + agencia.getReservas().get(i));
        }
        System.out.print("Seleccione el número de reserva: ");
        int indice = Integer.parseInt(sc.nextLine().trim()) - 1;
        Reserva reserva = agencia.getReservas().get(indice);

        //Se usa el generador de facturas (aqui se ve la dependencia) y se imprime el resultado
        GeneradorDeFacturas generador = new GeneradorDeFacturas();
        System.out.println(generador.generarFactura(reserva));
    }

    //Se arma el codigo de la reserva en formato RES-0000 usando el contador
    private static String generarCodigoReserva() {
        String codigo = String.format("RES-%04d", contadorReservas);
        contadorReservas++;
        return codigo;
    }

    //Se precargan datos de ejemplo para poder probar el sistema sin tener que crear todo a mano
    private static void precargarDatos(AgenciaDeViajes agencia) {

        //Se crean dos destinos de ejemplo
        Destino arenal = new Destino("Volcán Arenal", "Alajuela",
                "Caminata guiada por los senderos del parque nacional con vista al volcán.");
        Destino manuelAntonio = new Destino("Manuel Antonio", "Puntarenas",
                "Parque nacional con playas y senderos llenos de fauna costarricense.");
        Destino zonaDeLosSantos = new Destino("Zona DeLosSantos", "San Jose","Ingreso a diferentes fincas de cafe, guia sobre los procesos de produccion y tour en carreta");

        //Se crean dos guías de ejemplo
        Guia lara = new Guia("Lara Croft", "Español, Japonés, Mandarín, Ruso, Francés, Alemán, Italiano, Árabe, Maorí", 30);
        Guia nathan = new Guia("Nathan Drake", "Inglés, Español, Latin, Indonesio", 36);

        //Se crean tres tours de ejemplo, mezclando aventura y cultural para mostrar el polimorfismo
        TourAventura caminata = new TourAventura("TOU-0001", "Caminata en Arenal", 2, 65000,
                12, arenal, lara, "Alto", true);
        TourCultural zonaSantos = new TourCultural("TOU-0002", "Visita hacienda Cafetalera", 1, 30000,
                25, zonaDeLosSantos, nathan, true, "Cafe");
        TourAventura kayak = new TourAventura("TOU-0003", "Kayak en Manuel Antonio", 1, 40000,
                15, manuelAntonio, lara, "Medio", false);

        //Se agregan los guías y los tours a la agencia
        agencia.agregarGuia(lara);
        agencia.agregarGuia(nathan);
        agencia.agregarTour(caminata);
        agencia.agregarTour(zonaSantos);
        agencia.agregarTour(kayak);
    }
}