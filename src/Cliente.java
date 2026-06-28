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
        //Se valida que la cedula no venga nula para evitar un error más adelante
        if (cedula == null) {
            throw new IllegalArgumentException("La cédula no puede ser nula, por favor ingrese un número de cédula válido");
        }
        //Se limpian los espacios por si el usuario escribio alguno sin querer antes o después del número
        String cleanCedula = cedula.trim();
        //Luego se valida que la cedula tenga exactamente el formato que pide el Regex (9 dígitos sin guiones ni letras)
        if (!CEDULA_VALIDA.matcher(cleanCedula).matches()) {
            throw new IllegalArgumentException("Cédula inválida: " + cedula + ". Por favor ingrese un número de cédula válido sin guiones ni letras");
        }
        this.cedula = cleanCedula; /* Si pasa todas las validaciones, se asigna la cedula ya limpia a la variable */
    }

    public void setCorreo(String correo) {
        //Se valida que el correo no venga nulo
        if (correo == null) {
            throw new IllegalArgumentException("El correo no puede ser nulo");
        }
        //Se limpian espacios por si el usuario dejo alguno al inicio o al final
        String cleanCorreo = correo.trim();
        //Se valida que el correo cumpla con la estructura de un correo válido según el Regex
        if (!CORREO_VALIDO.matcher(cleanCorreo).matches()) {
            throw new IllegalArgumentException("Correo inválido: " + correo);
        }
        this.correo = cleanCorreo; /* Si todo esta correcto, se guarda el correo ya limpio */
    }

    public void setTelefono(String telefono) {
        //Se valida que el telefono no venga nulo
        if (telefono == null) {
            throw new IllegalArgumentException("El teléfono no puede ser nulo");
        }
        //Se limpian espacios por si el usuario escribio alguno sin querer
        String cleanTelefono = telefono.trim();
        //Luego se valida que el telefono cumpla con el formato de un número costarricense según el Regex
        if (!TELEFONO_VALIDO.matcher(cleanTelefono).matches()) {
            throw new IllegalArgumentException("Teléfono inválido: " + telefono);
        }
        this.telefono = cleanTelefono; /* Si pasa la validacion, se guarda el telefono ya limpio */
    }

    //Methods

    @Override
    public String toString() {
        //Se muestra la información del cliente en un formato legible para el usuario final
        return nombre + " — Cédula: " + cedula + " | Tel: " + telefono + " | Correo: " + correo;
    }
}