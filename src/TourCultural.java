import java.util.Set;

/**
 * Tour cultural que ajusta el precio si incluye alimentación.
 */
public class TourCultural extends Tour {

    // Constantes
    private static final Set<String> TIPOS_CULTURALES = Set.of("Cafe", "Museo", "Gastronomia", "City Tour");
    private static final double COSTO_ALIMENTACION = 8500;

    // Atributos
    private boolean incluyeAlimentacion;
    private String temaCultural;

    // Constructor
    public TourCultural(String codigo, String nombre, int duracionDias, double precioBase,
                        int capacidadMaxima, Destino destino, Guia guia,
                        boolean incluyeAlimentacion, String temaCultural) {
        super(codigo, nombre, duracionDias, precioBase, capacidadMaxima, destino, guia);
        setIncluyeAlimentacion(incluyeAlimentacion);
        setTemaCultural(temaCultural);
    }

    // Getters
    public boolean isIncluyeAlimentacion() { return incluyeAlimentacion; }
    public String getTemaCultural() { return temaCultural; }

    // Setters
    public void setIncluyeAlimentacion(boolean incluyeAlimentacion) {
        this.incluyeAlimentacion = incluyeAlimentacion;
    }

    public void setTemaCultural(String temaCultural) {
        if (temaCultural == null) {
            throw new IllegalArgumentException("El tema cultural no puede ser nulo");
        }
        String temaCultuNormalizado = normalizeTemaCultural(temaCultural.trim());
        if (!TIPOS_CULTURALES.contains(temaCultuNormalizado)) {
            throw new IllegalArgumentException("Tema cultural inválido, debe ser: Cafe, Museo, Gastronomía o City Tour");
        }
        this.temaCultural = temaCultuNormalizado;
    }

    private String normalizeTemaCultural(String temaCultural) {
        if (temaCultural.isEmpty()) return temaCultural;
        String[] palabras = temaCultural.split(" ");
        StringBuilder resultado = new StringBuilder();
        for (String palabra : palabras) {
            if (!palabra.isEmpty()) {
                resultado.append(Character.toUpperCase(palabra.charAt(0)))
                        .append(palabra.substring(1).toLowerCase())
                        .append(" ");
            }
        }
        return resultado.toString().trim();
    }

    // Métodos
    @Override
    public double getPrecio() {
        return incluyeAlimentacion ? getPrecioBase() + COSTO_ALIMENTACION : getPrecioBase();
    }
}