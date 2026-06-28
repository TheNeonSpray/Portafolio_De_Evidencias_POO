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
        //Como segundo paso se valida si el nombre dado esta dentro de los caracteres que se colocaron en el Regex
        String cleanName = nombre.trim();
        if (!NOMBRE_VALIDO.matcher(cleanName).matches()) {
            throw new IllegalArgumentException("Nombre inválido: " + nombre);
        }
        this.nombre = cleanName; /* Si cumple con todo lo anterior entonces se le asigna correctamente el nombre como parametro a la variable nombre */
    }

    public void setIdioma(String idioma) {
        if (idioma == null) {
            throw new IllegalArgumentException("El idioma no puede ser nulo");
        }
        if (idioma.trim().isEmpty()) {
            throw new IllegalArgumentException("El idioma no puede estar vacío");
        }
        this.idioma = idioma.trim();
    }

    public void setAniosExperiencia(int aniosExperiencia) {
        if (aniosExperiencia < 1) {
            throw new IllegalArgumentException("Los años de experiencia deben ser al menos 1");
        }
        this.aniosExperiencia = aniosExperiencia;
    }

    //Methods
}
