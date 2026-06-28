import java.time.LocalDate;
import java.util.Set;

public class Pago {

    private static final Set<String> TIPOS_DE_PAGO = Set.of("Efectivo", "Tarjeta", "SINPE");

    private double monto;
    private String metodoPago;
    private LocalDate fecha;

    public Pago(double monto, String metodoPago, LocalDate fecha) {
        setMonto(monto);
        setMetodoPago(metodoPago);
        setFecha(fecha);
    }

    // Getters
    public double getMonto() { return monto; }
    public String getMetodoPago() { return metodoPago; }
    public LocalDate getFecha() { return fecha; }

    // Setters
    public void setMonto(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a cero");
        }
        if (monto < 25000) {
            throw new IllegalArgumentException("El monto mínimo es de ₡25,000 colones, debido a que este es el precio minimo de los tours");
        }
        this.monto = monto;
    }

    public void setMetodoPago(String metodoPago) {
        if (metodoPago == null) {
            throw new IllegalArgumentException("El método de pago no puede ser nulo");
        }
        if (!TIPOS_DE_PAGO.contains(metodoPago.trim())) {
            throw new IllegalArgumentException("Método de pago inválido: " + metodoPago + " Los metodos de pago validos son: efectivo, tarjeta o SINPE movil");
        }
        this.metodoPago = metodoPago.trim();
    }

    public void setFecha(LocalDate fecha) {
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha no puede ser nula");
        }
        if (fecha.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha del pago no puede ser en el futuro");
        }
        this.fecha = fecha;
    }

    //Methods

}