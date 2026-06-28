import java.util.Set;

/**
 * Tour con nivel de dificultad que ajusta el precio según si requiere equipo especial.
 */
public class TourAventura extends Tour {

    // Constantes
    private static final Set<String> NIVELES = Set.of("Bajo", "Medio", "Alto");
    private static final double COSTO_EQUIPO_ESPECIAL = 15000;

    // Atributos
    private String nivelDificultad;
    private boolean requiereEquipoEspecial;

    // Constructor
    public TourAventura(String codigo, String nombre, int duracionDias, double precioBase,
                        int capacidadMaxima, Destino destino, Guia guia,
                        String nivelDificultad, boolean requiereEquipoEspecial) {
        super(codigo, nombre, duracionDias, precioBase, capacidadMaxima, destino, guia);
        setNivelDificultad(nivelDificultad);
        setRequiereEquipoEspecial(requiereEquipoEspecial);
    }

    // Getters
    public String getNivelDificultad() { return nivelDificultad; }
    public boolean isRequiereEquipoEspecial() { return requiereEquipoEspecial; }

    // Setters
    public void setNivelDificultad(String nivelDificultad) {
        if (nivelDificultad == null) {
            throw new IllegalArgumentException("El nivel de dificultad no puede ser nulo");
        }
        String nivelNormalizado = normalizeNivel(nivelDificultad.trim());
        if (!NIVELES.contains(nivelNormalizado)) {
            throw new IllegalArgumentException("Nivel inválido, debe ser: Bajo, Medio o Alto");
        }
        this.nivelDificultad = nivelNormalizado;
    }

    /**
     * Convierte la primera letra a mayúscula y el resto a minúscula.
     * Ejemplo: "BAJO" → "Bajo", "medio" → "Medio"
     */
    private String normalizeNivel(String nivel) {
        if (nivel.isEmpty()) return nivel;
        return Character.toUpperCase(nivel.charAt(0)) + nivel.substring(1).toLowerCase();
    }

    public void setRequiereEquipoEspecial(boolean requiereEquipoEspecial) {
        this.requiereEquipoEspecial = requiereEquipoEspecial;
    }

    // Métodos
    @Override
    public double getPrecio() {
        return requiereEquipoEspecial ? getPrecioBase() + COSTO_EQUIPO_ESPECIAL : getPrecioBase();
    }
}