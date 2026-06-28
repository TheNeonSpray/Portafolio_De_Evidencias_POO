//Recibe una reserva y produce el texto de la factura
public class GeneradorDeFacturas {

    // Methods
    public String generarFactura(Reserva reserva) {
        //Se valida que la reserva no sea nula antes de intentar generar la factura
        if (reserva == null) {
            throw new IllegalArgumentException("No se puede generar una factura de una reserva nula");
        }

        //Se obtienen los objetos internos de la reserva para armar el detalle de la factura
        Cliente cliente = reserva.getCliente();
        Tour tour = reserva.getTour();
        Pago pago = reserva.getPago();

        //Se construye el texto de la factura línea por línea usando un StringBuilder
        StringBuilder factura = new StringBuilder();
        factura.append("==============================================\n");
        factura.append("           COSTA RICA TOURS - FACTURA\n");
        factura.append("==============================================\n");
        factura.append("Reserva: ").append(reserva.getCodReserva()).append("\n");
        factura.append("\n");
        factura.append("CLIENTE\n");
        factura.append("  Nombre: ").append(cliente.getNombre()).append("\n");
        factura.append("  Cédula: ").append(cliente.getCedula()).append("\n");
        factura.append("  Correo: ").append(cliente.getCorreo()).append("\n");
        factura.append("  Teléfono: ").append(cliente.getTelefono()).append("\n");
        factura.append("\n");
        factura.append("DETALLE DEL TOUR\n");
        factura.append("  Tour: ").append(tour.getNombre()).append("\n");
        factura.append("  Destino: ").append(tour.getDestino().getNombre())
                .append(" (").append(tour.getDestino().getProvincia()).append(")\n");
        factura.append("  Guía: ").append(tour.getGuia().getNombre()).append("\n");
        factura.append("  Duración: ").append(tour.getDuracionDias()).append(" días\n");
        factura.append("  Precio por persona: ₡").append(tour.getPrecio()).append("\n");
        factura.append("  Cantidad de personas: ").append(reserva.getCantidadPersonas()).append("\n");
        factura.append("\n");
        factura.append("PAGO\n");
        factura.append("  Método: ").append(pago.getMetodoPago()).append("\n");
        factura.append("  Fecha: ").append(pago.getFecha()).append("\n");
        factura.append("\n");
        factura.append("----------------------------------------------\n");
        factura.append("  TOTAL: ₡").append(reserva.calcularTotal()).append("\n");
        factura.append("==============================================\n");
        factura.append("        ¡Gracias por viajar con nosotros!\n");
        factura.append("==============================================");

        return factura.toString();
    }
}