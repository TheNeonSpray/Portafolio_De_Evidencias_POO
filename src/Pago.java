import java.time.LocalDate;
import java.util.Set;

public class Pago {

    // Constantes
    private static final Set<String> TIPOS_DE_PAGO = Set.of("Efectivo", "Tarjeta", "SINPE");
    private static final double MONTO_MINIMO = 25000;

    // Atributos
    private double monto;
    private String metodoPago;
    private LocalDate fecha;

    // Constructor
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
        //Se valida primero que el monto no sea menor al minimo permitido, que corresponde al precio minimo de un tour
        if (monto < MONTO_MINIMO) {
            throw new IllegalArgumentException("El monto mínimo es de ₡25,000 colones, debido a que este es el precio minimo de los tours");
        }
        this.monto = monto;
    }

    public void setMetodoPago(String metodoPago) {
        //Se valida primero si el metodo de pago es nulo
        if (metodoPago == null) {
            throw new IllegalArgumentException("El método de pago no puede ser nulo");
        }
        //Luego se normaliza el texto para aceptar mayusculas o minusculas y se valida que sea uno de los metodos permitidos
        String metodoNormalizado = normalizeMetodoPago(metodoPago.trim());
        if (!TIPOS_DE_PAGO.contains(metodoNormalizado)) {
            throw new IllegalArgumentException("Método de pago inválido: " + metodoPago + ". Los metodos de pago validos son: Efectivo, Tarjeta o SINPE");
        }
        this.metodoPago = metodoNormalizado;
    }

    public void setFecha(LocalDate fecha) {
        //Se valida primero si la fecha es nula
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha no puede ser nula");
        }
        //Como segundo paso se valida que la fecha del pago no sea en el futuro
        if (fecha.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha del pago no puede ser en el futuro");
        }
        this.fecha = fecha;
    }

    // Methods

    /**
     * Normaliza el método de pago para aceptar el texto sin importar las mayúsculas.
     * Las siglas conocidas (como SINPE) se devuelven completamente en mayúscula.
     * Ejemplo: "efectivo" → "Efectivo", "sinpe" → "SINPE"
     */
    private String normalizeMetodoPago(String metodo) {
        //Se devuelve el texto tal cual si esta vacio, para evitar errores al tomar el primer caracter
        if (metodo.isEmpty()) return metodo;
        //Se revisa primero si el texto corresponde a una sigla conocida que debe ir toda en mayuscula
        if (metodo.equalsIgnoreCase("SINPE")) {
            return "SINPE";
        }
        //Para los demás casos se pone la primera letra en mayúscula y el resto en minúscula
        return Character.toUpperCase(metodo.charAt(0)) + metodo.substring(1).toLowerCase();
    }

    @Override
    public String toString() {
        //Se muestra la información del pago en un formato legible para el usuario final
        return "Pago de ₡" + monto + " — Método: " + metodoPago + " | Fecha: " + fecha;
    }
}