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
        if (codigo == null) {
            throw new IllegalArgumentException("El código del tour no puede ser nulo");
        }
        if (!COD_TOUR_VALIDO.matcher(codigo.trim()).matches()) {
            throw new IllegalArgumentException("Formato inválido, debe ser TOU-0000: " + codigo);
        }
        this.codigo = codigo.trim();
    }

    public void setNombre(String nombre) {
        if (nombre == null) {
            throw new IllegalArgumentException("El nombre del tour no puede ser nulo");
        }
        String cleanNombre = nombre.trim();
        if (cleanNombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre del tour no puede estar vacío");
        }
        if (cleanNombre.length() < 3 || cleanNombre.length() > 80) {
            throw new IllegalArgumentException("El nombre debe tener entre 3 y 80 caracteres");
        }
        this.nombre = cleanNombre;
    }

    public void setDuracionDias(int duracionDias) {
        if (duracionDias < 1) {
            throw new IllegalArgumentException("La duración mínima es 1 día");
        }
        if (duracionDias > 15) {
            throw new IllegalArgumentException("La duración máxima es 15 días");
        }
        this.duracionDias = duracionDias;
    }

    public void setPrecioBase(double precioBase) {
        if (precioBase <= 0) {
            throw new IllegalArgumentException("El precio base debe ser mayor a cero");
        }
        if (precioBase < 10000) {
            throw new IllegalArgumentException("El precio base mínimo por persona es ₡10,000");
        }
        if (precioBase > 100000) {
            throw new IllegalArgumentException("El precio base no puede superar ₡100,000 por persona");
        }
        this.precioBase = precioBase;
    }

    public void setCapacidadMaxima(int capacidadMaxima) {
        if (capacidadMaxima < 1) {
            throw new IllegalArgumentException("La capacidad máxima debe ser al menos 1 persona");
        }
        if (capacidadMaxima > 25) {
            throw new IllegalArgumentException("La capacidad máxima no puede superar 25 personas");
        }
        this.capacidadMaxima = capacidadMaxima;
    }

    public void setDestino(Destino destino) {
        if (destino == null) {
            throw new IllegalArgumentException("El destino no puede ser nulo");
        }
        this.destino = destino;
    }

    public void setGuia(Guia guia) {
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
}