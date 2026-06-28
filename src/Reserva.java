import java.time.LocalDate;
import java.util.regex.Pattern;

public class Reserva {

    private static final Pattern COD_RESERVA_VALIDO =
            Pattern.compile("^RES-\\d{4}$");

    private String codReserva;
    private Cliente cliente;
    private Tour tour;
    private int cantidadPersonas;
    private Pago pago;

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
        if (codReserva == null) {
            throw new IllegalArgumentException("El código de reserva no puede ser nulo");
        }
        if (!COD_RESERVA_VALIDO.matcher(codReserva.trim()).matches()) {
            throw new IllegalArgumentException("Formato inválido, debe ser RES-0000: " + codReserva);
        }
        this.codReserva = codReserva.trim();
    }

    public void setCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo");
        }
        this.cliente = cliente;
    }

    public void setTour(Tour tour) {
        if (tour == null) {
            throw new IllegalArgumentException("El tour no puede ser nulo");
        }
        this.tour = tour;
    }

    public void setCantidadPersonas(int cantidadPersonas) {
        if (cantidadPersonas < 1) {
            throw new IllegalArgumentException("La cantidad de personas debe ser al menos 1");
        }
        if (cantidadPersonas > tour.getCapacidadMaxima()) {
            throw new IllegalArgumentException("La cantidad supera la capacidad máxima del tour");
        }
        this.cantidadPersonas = cantidadPersonas;
    }

    public void setPago(Pago pago) {
        if (pago == null) {
            throw new IllegalArgumentException("El pago no puede ser nulo");
        }
        double montoEsperado = tour.getPrecio() * cantidadPersonas;
        if (pago.getMonto() < montoEsperado) {
            throw new IllegalArgumentException(
                    "El monto del pago (" + pago.getMonto() + ") es menor al total esperado (" + montoEsperado + ")"
            );
        }
        this.pago = pago;
    }

    //Methods

    public double calcularTotal() {
        return tour.getPrecio() * cantidadPersonas;
    }

    public void registrarPago(double valor, String metodo, LocalDate fecha) {
        Pago pago = new Pago(valor, metodo, fecha);
    }

    public boolean estaPago() {
        return pago != null;
    }
}