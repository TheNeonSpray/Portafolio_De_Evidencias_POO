//Representa a un guía turístico con su experiencia e idioma.
public class Guia {

    //Attributes

    private String nombre;
    private String idioma;
    private int aniosExperiencia;

    //Constructor

    public Guia(String nombre, String idioma, int aniosExperiencia) {
        this.nombre = nombre;
        this.idioma = idioma;
        this.aniosExperiencia = aniosExperiencia;
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
        this.nombre = nombre;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public void setAniosExperiencia(int aniosExperiencia) {
        this.aniosExperiencia = aniosExperiencia;
    }

    //Methods
}
