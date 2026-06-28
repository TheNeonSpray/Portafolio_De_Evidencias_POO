//Une un cliente con un tour, calcula el total y guarda su pago.
public class Reserva {

    //Attributes
    private String codReserva;
    private Cliente cliente; //*(referencia → agregación)*
    private Tour tour; //*(referencia → agregación)*
    private int cantidadPersonas;
    private Pago pago; //*(creado dentro → composición)*

    //Constructor

    public Reserva(String codReserva, Cliente cliente, Tour tour, int cantidadPersonas, Pago pago) {
        this.codReserva = codReserva;
        this.cliente = cliente;
        this.tour = tour;
        this.cantidadPersonas = cantidadPersonas;
        this.pago = pago;
    }

    //Getters

    public String getCodReserva() {
        return codReserva;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Tour getTour() {
        return tour;
    }

    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    public Pago getPago() {
        return pago;
    }

    //Setters

    public void setCodReserva(String codReserva) {
        this.codReserva = codReserva;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public void setCantidadPersonas(int cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    //Methods
}
