import java.util.regex.Pattern;

/**
 * Clase abstracta que define los atributos y validaciones comunes a todos los tours.
 * Cada subclase es responsable de calcular su precio final mediante getPrecio().
 */
public abstract class Tour {

    // Constantes
    private static final Pattern COD_TOUR_VALIDO =
            Pattern.compile("^TOU-\\d{4}$");
    private static final Pattern NOMBRE_VALIDO =
            Pattern.compile("^[\\p{L}\\d][\\p{L}\\d \\-']{1,78}[\\p{L}\\d]$");

    // Atributos
    private String codigo;
    private String nombre;
    private int duracionDias;
    private double precioBase;
    private int capacidadMaxima;
    private Destino destino;
    private Guia guia;

    // Constructor
    public Tour(String codigo, String nombre, int duracionDias, double precioBase,
                int capacidadMaxima, Destino destino, Guia guia) {
        setCodigo(codigo);
        setNombre(nombre);
        setDuracionDias(duracionDias);
        setPrecioBase(precioBase);
        setCapacidadMaxima(capacidadMaxima);
        setDestino(destino);
        setGuia(guia);
    }

    // Getters
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public int getDuracionDias() { return duracionDias; }
    public double getPrecioBase() { return precioBase; }
    public int getCapacidadMaxima() { return capacidadMaxima; }
    public Destino getDestino() { return destino; }
    public Guia getGuia() { return guia; }

    // Setters
    public void setCodigo(String codigo) {
        //Se valida primero si el codigo es nulo
        if (codigo == null) {
            throw new IllegalArgumentException("El código del tour no puede ser nulo");
        }
        //Como segundo paso se valida que el codigo cumpla el formato TOU-0000 definido en el Regex
        if (!COD_TOUR_VALIDO.matcher(codigo.trim()).matches()) {
            throw new IllegalArgumentException("Formato inválido, debe ser TOU-0000: " + codigo);
        }
        this.codigo = codigo.trim();
    }

    public void setNombre(String nombre) {
        //Se valida primero si el nombre es nulo
        if (nombre == null) {
            throw new IllegalArgumentException("El nombre del tour no puede ser nulo");
        }
        //Luego se limpia el nombre y se valida que no quede vacio
        String cleanNombre = nombre.trim();
        if (cleanNombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre del tour no puede estar vacío");
        }
        //Por último se valida que el nombre cumpla el formato permitido por el Regex (largo entre 3 y 80 y caracteres válidos)
        if (!NOMBRE_VALIDO.matcher(cleanNombre).matches()) {
            throw new IllegalArgumentException("Nombre de tour inválido: " + nombre);
        }
        this.nombre = cleanNombre;
    }

    public void setDuracionDias(int duracionDias) {
        //Se valida primero que la duracion no sea menor a 1 dia
        if (duracionDias < 1) {
            throw new IllegalArgumentException("La duración mínima es 1 día");
        }
        //Como segundo paso se valida que la duracion no supere los 15 dias
        if (duracionDias > 15) {
            throw new IllegalArgumentException("La duración máxima es 15 días");
        }
        this.duracionDias = duracionDias;
    }

    public void setPrecioBase(double precioBase) {
        //Se valida primero que el precio base no sea menor al minimo permitido
        if (precioBase < 25000) {
            throw new IllegalArgumentException("El precio base mínimo por persona es ₡25,000");
        }
        //Como segundo paso se valida que el precio base no supere el máximo permitido
        if (precioBase > 150000) {
            throw new IllegalArgumentException("El precio base no puede superar ₡150,000 por persona");
        }
        this.precioBase = precioBase;
    }

    public void setCapacidadMaxima(int capacidadMaxima) {
        //Se valida primero que la capacidad sea de al menos 1 persona
        if (capacidadMaxima < 1) {
            throw new IllegalArgumentException("La capacidad máxima debe ser al menos 1 persona");
        }
        //Como segundo paso se valida que la capacidad no supere las 25 personas
        if (capacidadMaxima > 25) {
            throw new IllegalArgumentException("La capacidad máxima no puede superar 25 personas");
        }
        this.capacidadMaxima = capacidadMaxima;
    }

    public void setDestino(Destino destino) {
        //Se valida que el destino no sea nulo, ya que todo tour debe tener un destino
        if (destino == null) {
            throw new IllegalArgumentException("El destino no puede ser nulo");
        }
        this.destino = destino;
    }

    public void setGuia(Guia guia) {
        //Se valida que el guia no sea nulo, ya que todo tour debe tener un guia asignado
        if (guia == null) {
            throw new IllegalArgumentException("El guía no puede ser nulo");
        }
        this.guia = guia;
    }

    /**
     * Calcula el precio final por persona incluyendo cualquier costo extra.
     * Cada subclase lo implementa según sus propias reglas.
     */
    public abstract double getPrecio();

    @Override
    public String toString() {
        //Se muestra la información del tour en un formato legible para el usuario final
        return "[" + codigo + "] " + nombre +
                " — " + destino.getNombre() + " (" + destino.getProvincia() + ")" +
                " | Duración: " + duracionDias + " días" +
                " | Guía: " + guia.getNombre() +
                " | Capacidad: " + capacidadMaxima + " personas" +
                " | Precio: ₡" + getPrecio();
    }
}