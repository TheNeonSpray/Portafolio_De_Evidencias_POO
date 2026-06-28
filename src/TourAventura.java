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
        //Se valida primero si el nivel de dificultad es nulo
        if (nivelDificultad == null) {
            throw new IllegalArgumentException("El nivel de dificultad no puede ser nulo");
        }
        //Luego se normaliza el texto para aceptar mayusculas o minusculas y se valida que sea uno de los niveles permitidos
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
        //Se devuelve el texto tal cual si esta vacio, para evitar errores al tomar el primer caracter
        if (nivel.isEmpty()) return nivel;
        //Se pone la primera letra en mayuscula y el resto en minuscula
        return Character.toUpperCase(nivel.charAt(0)) + nivel.substring(1).toLowerCase();
    }

    public void setRequiereEquipoEspecial(boolean requiereEquipoEspecial) {
        this.requiereEquipoEspecial = requiereEquipoEspecial;
    }

    // Métodos
    @Override
    public double getPrecio() {
        //Si el tour requiere equipo especial se le suma el costo extra al precio base, si no se cobra solo el precio base
        return requiereEquipoEspecial ? getPrecioBase() + COSTO_EQUIPO_ESPECIAL : getPrecioBase();
    }

    @Override
    public String toString() {
        //Se reutiliza el toString del padre y se le agregan los datos propios del tour de aventura
        return super.toString() +
                " | Tipo: Aventura" +
                " | Dificultad: " + nivelDificultad +
                " | Equipo especial: " + (requiereEquipoEspecial ? "Sí" : "No");
    }
}