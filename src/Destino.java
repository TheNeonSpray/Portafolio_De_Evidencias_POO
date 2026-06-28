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
        //Se valida primero si el nombre es nulo
        if (nombre == null) {
            throw new IllegalArgumentException("El nombre no puede ser nulo, por favor agrega un nombre al Destino del Tour");
        }
        //Como segundo paso se valida que el nombre no quede vacio una vez quitados los espacios
        String cleanNombre = nombre.trim();
        if (cleanNombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        this.nombre = cleanNombre;
    }

    public void setProvincia(String provincia) {
        //Se valida primero si la provincia es nula
        if (provincia == null) {
            throw new IllegalArgumentException("La provincia no puede ser nula, por favor agrega una Provincia");
        }
        //Como segundo paso se valida que la provincia este dentro de la lista de provincias permitidas
        if (!PROVINCIAS_VALIDAS.contains(provincia.trim())) {
            throw new IllegalArgumentException("Provincia inválida: " + provincia);
        }
        this.provincia = provincia.trim();
    }

    public void setDescripcion(String descripcion) {
        //Se valida primero si la descripcion es nula
        if (descripcion == null) {
            throw new IllegalArgumentException("La descripcion no puede ser nula, por favor agrega una Descripcion del Destino");
        }
        //Luego se limpia la descripcion y se valida que no quede vacia
        String cleanDescripcion = descripcion.trim();
        if (cleanDescripcion.isEmpty()) {
            throw new IllegalArgumentException("La descripción no puede estar vacía");
        }
        //Por ultimo se valida que la descripcion tenga una longitud razonable
        if (cleanDescripcion.length() < 20 || cleanDescripcion.length() > 500) {
            throw new IllegalArgumentException("La descripción debe tener entre 20 y 500 caracteres");
        }
        this.descripcion = cleanDescripcion;
    }

    //Methods

    @Override
    public String toString() {
        //Se muestra la informacion del destino en un formato legible para el usuario final
        return nombre + " (" + provincia + ") — " + descripcion;
    }
}