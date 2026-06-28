import java.time.LocalDate;

//Guarda el monto, el método y la fecha de pago de una reserva.
public class Pago {

    public static final String[] tipoDePago = {"Efectivo", "Tarjeta", "SINPE"};

    //Attributes
    private double monto;
    private String metodoPago;
    private LocalDate fecha;

    //Constructor

    public Pago(double monto, String metodoPago, LocalDate fecha) {
        this.monto = monto;
        this.metodoPago = metodoPago;
        this.fecha = fecha;
    }

    //Getters

    public double getMonto() {
        return monto;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    //Setters

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public void setMetodoPago(String metodoPago) {
        if (metodoPago == null){
            this.metodoPago = "Efectivo";
            return;
        }
        for (String tipoPago : tipoDePago){
            if (metodoPago.equals(tipoPago)) {
                this.metodoPago = metodoPago;
                return;
            }
        }
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    //Methods
}
