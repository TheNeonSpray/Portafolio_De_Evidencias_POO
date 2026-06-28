import java.util.ArrayList;

//Administra las listas de tours, clientes, guías y reservas; orquesta las operaciones.
public class AgenciaDeViajes {

    //Attributes
    private String nombre;
    private ArrayList<Tour> tours;
    private ArrayList<Cliente> clientes;
    private ArrayList<Guia> guias;
    private ArrayList<Reserva> reservas;

    //Constructor

    public AgenciaDeViajes(String nombre) {
        setNombre(nombre);
        //Se crean las listas vacias al nacer la agencia para garantizar que nunca sean nulas
        this.tours = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.guias = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }

    //Getters

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Tour> getTours() {
        return tours;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public ArrayList<Guia> getGuias() {
        return guias;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    //Setters

    public void setNombre(String nombre) {
        //Se valida primero si el nombre es nulo
        if (nombre == null) {
            throw new IllegalArgumentException("El nombre de la agencia no puede ser nulo");
        }
        //Como segundo paso se valida que el nombre no quede vacio una vez quitados los espacios
        String cleanNombre = nombre.trim();
        if (cleanNombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre de la agencia no puede estar vacío");
        }
        this.nombre = cleanNombre;
    }

    //Methods

    public void agregarTour(Tour tour) {
        //Se valida que el tour no sea nulo antes de agregarlo a la lista
        if (tour == null) {
            throw new IllegalArgumentException("No se puede agregar un tour nulo");
        }
        this.tours.add(tour);
    }

    public void registrarCliente(Cliente cliente) {
        //Se valida que el cliente no sea nulo antes de agregarlo a la lista
        if (cliente == null) {
            throw new IllegalArgumentException("No se puede registrar un cliente nulo");
        }
        this.clientes.add(cliente);
    }

    public void agregarGuia(Guia guia) {
        //Se valida que guia no sea nulo antes de agregarlo a la lista
        if (guia == null) {
            throw new IllegalArgumentException("No se puede agregar un guía nulo");
        }
        this.guias.add(guia);
    }

    public Reserva crearReserva(String codReserva, Cliente cliente, Tour tour, int cantidadPersonas, Pago pago) {
        //Se crea la reserva con los datos recibidos
        Reserva reserva = new Reserva(codReserva, cliente, tour, cantidadPersonas, pago);
        //Se agrega la reserva a la lista y luego se devuelve la reserva creada
        this.reservas.add(reserva);
        return reserva;
    }

    public void mostrarCatalogo() {
        //Se valida primero si no hay tours registrados para avisar al usuario
        if (tours.isEmpty()) {
            System.out.println("No hay tours registrados en el catálogo.");
            return;
        }
        //Se recorre la lista de tours y se imprime cada uno usando su toString
        System.out.println("===== CATÁLOGO DE TOURS =====");
        for (Tour tour : tours) {
            System.out.println(tour);
        }
    }

    public void listarReservas() {
        //Se valida primero si no hay reservas registradas para avisar al usuario
        if (reservas.isEmpty()) {
            System.out.println("No hay reservas registradas.");
            return;
        }
        //Se recorre la lista de reservas y se imprime cada una usando su toString
        System.out.println("===== LISTA DE RESERVAS =====");
        for (Reserva reserva : reservas) {
            System.out.println(reserva);
        }
    }

    public Tour buscarTourPorCodigo(String codigo) {
        //Se valida primero si el codigo es nulo
        if (codigo == null) {
            throw new IllegalArgumentException("El código a buscar no puede ser nulo");
        }
        //Se recorre la lista de tours buscando uno cuyo codigo coincida con el buscado
        for (Tour tour : tours) {
            if (tour.getCodigo().equals(codigo.trim())) {
                return tour;
            }
        }
        //Si se termina de recorrer la lista sin encontrarlo, se lanza una excepción
        throw new IllegalArgumentException("No se encontró ningún tour con el código: " + codigo);
    }

    @Override
    public String toString() {
        //Se muestra un resumen de la agencia con la cantidad de elementos registrados en cada lista
        return "Agencia " + nombre +
                " — Tours: " + tours.size() +
                " | Clientes: " + clientes.size() +
                " | Guías: " + guias.size() +
                " | Reservas: " + reservas.size();
    }
}