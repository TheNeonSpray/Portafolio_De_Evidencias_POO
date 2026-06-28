//Recibe una reserva y produce el texto de la factura
public class GeneradorDeFacturas {
    //Methods
    public String generarFactura(Reserva reserva){
        return "Reserva{" +
                "codReserva='" + reserva.getCodReserva() + '\'' +
                ", cliente=" + reserva.getCliente() +
                ", tour=" + reserva.getTour() +
                ", cantidadPersonas=" + reserva.getCantidadPersonas() +
                ", pago=" + reserva.getPago() +
                '}';
    }
}
