import java.util.regex.Pattern;

public class Reserva {

    // Constantes
    private static final Pattern COD_RESERVA_VALIDO =
            Pattern.compile("^RES-\\d{4}$");

    // Atributos
    private String codReserva;
    private Cliente cliente;
    private Tour tour;
    private int cantidadPersonas;
    private Pago pago;

    // Constructor
    public Reserva(String codReserva, Cliente cliente, Tour tour, int cantidadPersonas, Pago pago) {
        setCodReserva(codReserva);
        setCliente(cliente);
        setTour(tour);
        setCantidadPersonas(cantidadPersonas);
        setPago(pago);
    }

    // Getters
    public String getCodReserva() { return codReserva; }
    public Cliente getCliente() { return cliente; }
    public Tour getTour() { return tour; }
    public int getCantidadPersonas() { return cantidadPersonas; }
    public Pago getPago() { return pago; }

    // Setters
    public void setCodReserva(String codReserva) {
        //Se valida primero si el codigo de reserva es nulo
        if (codReserva == null) {
            throw new IllegalArgumentException("El código de reserva no puede ser nulo");
        }
        //Como segundo paso se valida que el code cumpla el formato RES-0000 definido en el Regex
        if (!COD_RESERVA_VALIDO.matcher(codReserva.trim()).matches()) {
            throw new IllegalArgumentException("Formato inválido, debe ser RES-0000: " + codReserva);
        }
        this.codReserva = codReserva.trim();
    }

    public void setCliente(Cliente cliente) {
        //Se valida que el cliente no sea nulo, ya que toda reserva pertenece a un cliente
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo");
        }
        this.cliente = cliente;
    }

    public void setTour(Tour tour) {
        //Se valida que el tour no sea nulo, ya que toda reserva es sobre un tour
        if (tour == null) {
            throw new IllegalArgumentException("El tour no puede ser nulo");
        }
        this.tour = tour;
    }

    public void setCantidadPersonas(int cantidadPersonas) {
        //Se valida primero que el tour ya este asignado, porque la cantidad depende de la capacidad del tour
        if (tour == null) {
            throw new IllegalStateException("Debe asignarse el tour antes que la cantidad de personas");
        }
        //Luego se valida que la cantidad sea de al menos 1 persona
        if (cantidadPersonas < 1) {
            throw new IllegalArgumentException("La cantidad de personas debe ser al menos 1");
        }
        //Por último se valida que la cantidad no supere la capacidad maxima del tour
        if (cantidadPersonas > tour.getCapacidadMaxima()) {
            throw new IllegalArgumentException("La cantidad supera la capacidad máxima del tour");
        }
        this.cantidadPersonas = cantidadPersonas;
    }

    public void setPago(Pago pago) {
        //Se valida primero si el pago es nulo
        if (pago == null) {
            throw new IllegalArgumentException("El pago no puede ser nulo");
        }
        //Se valida que el tour y la cantidad ya esten asignados, porque el total esperado depende de ambos
        if (tour == null) {
            throw new IllegalStateException("Debe asignarse el tour antes que el pago");
        }
        //Se calcula el total esperado y se valida que el pago cubra al menos ese monto
        double montoEsperado = tour.getPrecio() * cantidadPersonas;
        if (pago.getMonto() < montoEsperado) {
            throw new IllegalArgumentException(
                    "El monto del pago (" + pago.getMonto() + ") es menor al total esperado (" + montoEsperado + ")"
            );
        }
        this.pago = pago;
    }

    // Methods

    public double calcularTotal() {
        //Se calcula el total de la reserva multiplicando el precio del tour por la cantidad de personas
        return tour.getPrecio() * cantidadPersonas;
    }

    @Override
    public String toString() {
        //Se muestra la informacion de la reserva en un formato legible para el usuario final
        return "Reserva " + codReserva +
                " — Cliente: " + cliente.getNombre() +
                " | Tour: " + tour.getNombre() +
                " | Personas: " + cantidadPersonas +
                " | Total: ₡" + calcularTotal() +
                " | Estado: " + (estaPago() ? "Pagada" : "Pendiente");
    }
}