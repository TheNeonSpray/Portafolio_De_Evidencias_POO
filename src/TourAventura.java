// Hereda de la clase Tour. Un tour con nivel de dificultad, ajusta el precio según el riesgo/equipo.
public class TourAventura extends Tour {

    public static final String[] NIVELES = {"Bajo", "Medio", "Alto"};

    // Attributes

    private String nivelDificultad;
    private boolean requiereEquipoEspecial;

    // Constructor

    public TourAventura(String codigo, String nombre, int duracionDias, double precioBase, Destino destino, Guia guia, String nivelDificultad, boolean requiereEquipoEspecial) {
        super(codigo, nombre, duracionDias, precioBase, destino, guia);
        setNivelDificultad(nivelDificultad);
        this.requiereEquipoEspecial = requiereEquipoEspecial;
    }

    // Getters

    public String getNivelDificultad() {
        return nivelDificultad;
    }

    public boolean isRequiereEquipoEspecial() {
        return requiereEquipoEspecial;
    }

    // Setters

    public void setNivelDificultad(String nivelDificultad) {
        if (nivelDificultad == null) {
            this.nivelDificultad = "Bajo";
            return;
        }

        for (String nivel : NIVELES) {
            if (nivelDificultad.equals(nivel)) {
                this.nivelDificultad = nivelDificultad;
                return;
            }
        }

        this.nivelDificultad = "Bajo";
    }

    public void setRequiereEquipoEspecial(boolean requiereEquipoEspecial) {
        this.requiereEquipoEspecial = requiereEquipoEspecial;
    }

    // Methods
}