//Guarda la información de un lugar de Costa Rica (nombre, provincia, descripción).
public class Destino {
    //Atributos
    private String nombre;
    private String provincia;
    private String descripcion;

    //Constructor

    public Destino(String nombre, String provincia, String descripcion) {
        this.nombre = nombre;
        this.provincia = provincia;
        this.descripcion = descripcion;
    }

    //Getters

    public String getNombre() {
        return nombre;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    //Setters

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    //Methods
}
