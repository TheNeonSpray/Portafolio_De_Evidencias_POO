import java.util.regex.Pattern;

//Representa a la persona que reserva (cédula, nombre, contacto, etc).
public class Cliente {

    private static final Pattern NOMBRE_VALIDO   = Pattern.compile("^(?=.{2,50}$)\\p{L}+([ '-]\\p{L}+)*$");
    private static final Pattern CEDULA_VALIDA   = Pattern.compile("^\\d{9}$");
    private static final Pattern CORREO_VALIDO   = Pattern.compile("^[\\w.+\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}$");
    private static final Pattern TELEFONO_VALIDO = Pattern.compile("^[2678]\\d{3}-?\\d{4}$");

    //Attributes

    private String nombre;
    private String cedula;
    private String correo;
    private String telefono;

    //Constructor

    public Cliente(String nombre, String cedula, String correo, String telefono) {
        setNombre(nombre);
        setCedula(cedula);
        setCorreo(correo);
        setTelefono(telefono);
    }

    //Getters

    public String getNombre() {
        return nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }

    //Setters

    public void setNombre(String nombre) {
        //Se valida primero si el nombre esta vacio
        if (nombre == null) {
            throw new IllegalArgumentException("El nombre no puede ser nulo");
        }
        //Como segundo paso se valida si el nombre dado esta dentro de los caracteres que se colocaron en el Regex
        String cleanName = nombre.trim();
        if (!NOMBRE_VALIDO.matcher(cleanName).matches()) {
            throw new IllegalArgumentException("Nombre inválido: " + nombre);
        }
        this.nombre = cleanName; /* Si cumple con todo lo anterior entonces se le asigna correctamente el nombre como parametro a la variable nombre */
    }

    public void setCedula(String cedula) {
        if  (cedula == null) {
            throw new IllegalArgumentException("La cedula no puede ser nula, por favo ingrese un numero de cedula valido");
        }
        if (!CEDULA_VALIDA.matcher(cedula).matches()) {
            throw new IllegalArgumentException("Cédula inválida: " + cedula + " Por favor ingrese un numero de cedula valido sin guiones o letras");
        }
        this.cedula = cedula;
    }

    public void setCorreo(String correo) {
        if (correo == null) {
            throw new IllegalArgumentException("El correo no puede ser nulo");
        }
        if (!CORREO_VALIDO.matcher(correo.trim()).matches()) {
            throw new IllegalArgumentException("Correo inválido: " + correo);
        }
        this.correo = correo.trim();
    }

    public void setTelefono(String telefono) {
        if (telefono == null) {
            throw new IllegalArgumentException("El teléfono no puede ser nulo");
        }
        if (!TELEFONO_VALIDO.matcher(telefono).matches()) {
            throw new IllegalArgumentException("Teléfono inválido: " + telefono);
        }
        this.telefono = telefono;
    }

    //Methods
}
