import java.util.Set;

//Guarda la información de un lugar de Costa Rica (nombre, provincia, descripción).
public class Destino {

    //Constantes
    private static final Set<String> PROVINCIAS_VALIDAS = Set.of(
            "San Jose", "Alajuela", "Cartago", "Heredia",
            "Guanacaste", "Puntarenas", "Limon"
    );

    //Atributos
    private String nombre;
    private String provincia;
    private String descripcion;

    //Constructor

    public Destino(String nombre, String provincia, String descripcion) {
        setNombre(nombre);
        setProvincia(provincia);
        setDescripcion(descripcion);
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
        if (nombre == null) {
            throw new IllegalArgumentException("El nombre no puede ser nulo, por favor agrega un nombre al Destino del Tour");
        }
        this.nombre = nombre.trim();
    }

    public void setProvincia(String provincia) {
        if (provincia == null) {
            throw new IllegalArgumentException("La provincia no puede ser nula, por favor agrega una Provincia");
        }
        if (!PROVINCIAS_VALIDAS.contains(provincia.trim())) {
            throw new IllegalArgumentException("Provincia inválida: " + provincia);
        }
        this.provincia = provincia.trim();
    }

    public void setDescripcion(String descripcion) {
        if (descripcion == null) {
            throw new IllegalArgumentException("La descripcion no puede ser nula, por favor agrega una Descripcion del Destino");
        }
        String cleanDescripcion = descripcion.trim();
        if (cleanDescripcion.isEmpty()) {
            throw new IllegalArgumentException("La descripción no puede estar vacía");
        }
        if (cleanDescripcion.length() < 20 || cleanDescripcion.length() > 500) {
            throw new IllegalArgumentException("La descripción debe tener entre 20 y 500 caracteres");
        }
        this.descripcion = cleanDescripcion;
    }

    //Methods
}
