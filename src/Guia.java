import java.util.regex.Pattern;

//Representa a un guía turístico con su experiencia e idioma.
public class Guia {

    private static final Pattern NOMBRE_VALIDO =
            Pattern.compile("^(?=.{2,50}$)\\p{L}+([ '-]\\p{L}+)*$");

    //Attributes

    private String nombre;
    private String idioma;
    private int aniosExperiencia;

    //Constructor

    public Guia(String nombre, String idioma, int aniosExperiencia) {
        setNombre(nombre);
        setIdioma(idioma);
        setAniosExperiencia(aniosExperiencia);
    }

    //Getters

    public String getNombre() {
        return nombre;
    }

    public String getIdioma() {
        return idioma;
    }

    public int getAniosExperiencia() {
        return aniosExperiencia;
    }

    //Setters

    public void setNombre(String nombre) {
        //Se valida primero si el nombre esta vacio
        if (nombre == null) {
            throw new IllegalArgumentException("El nombre no puede ser nulo, por favor escriba un nombre para el guia");
        }
        //Como segundo paso se valida si el nombre dado está dentro de los caracteres que se colocaron en el Regex
        String cleanName = nombre.trim();
        if (!NOMBRE_VALIDO.matcher(cleanName).matches()) {
            throw new IllegalArgumentException("Nombre inválido: " + nombre);
        }
        this.nombre = cleanName; /* Si cumple con todo lo anterior entonces se le asigna correctamente el nombre como parámetro a la variable nombre */
    }

    public void setIdioma(String idioma) {
        //Se valida primero si el idioma es nulo
        if (idioma == null) {
            throw new IllegalArgumentException("El idioma no puede ser nulo");
        }
        //Luego se valida que el idioma no quede vacio una vez quitados los espacios
        if (idioma.trim().isEmpty()) {
            throw new IllegalArgumentException("El idioma no puede estar vacío");
        }
        this.idioma = idioma.trim();
    }

    public void setAniosExperiencia(int aniosExperiencia) {
        //Se valida primero que los años de experiencia no sean menores a 0. Un guía recien egresado puede laborar en la agencia.
        if (aniosExperiencia < 0) {
            throw new IllegalArgumentException("Los años de experiencia no pueden ser negativos");
        }
        //Como segundo paso se valida que los años de experiencia no superen un valor realista
        if (aniosExperiencia > 57) {
            throw new IllegalArgumentException("Los años de experiencia no pueden superar los 57");
        }
        this.aniosExperiencia = aniosExperiencia;
    }

    //Methods

    @Override
    public String toString() {
        //Se muestra la información del guia en un formato legible para el usuario final
        return nombre + " — Idioma: " + idioma + " | Experiencia: " + aniosExperiencia + " años";
    }
}