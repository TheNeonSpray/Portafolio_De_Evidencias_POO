import java.util.Set;

/**
 * Tour cultural que ajusta el precio si incluye alimentación.
 */
public class TourCultural extends Tour {

    // Constantes
    private static final Set<String> TIPOS_CULTURALES = Set.of("Cafe", "Museo", "Gastronomia", "City Tour");
    private static final double COSTO_ALIMENTACION = 8500;

    // Atributos
    private boolean incluyeAlimentacion;
    private String temaCultural;

    // Constructor
    public TourCultural(String codigo, String nombre, int duracionDias, double precioBase,
                        int capacidadMaxima, Destino destino, Guia guia,
                        boolean incluyeAlimentacion, String temaCultural) {
        super(codigo, nombre, duracionDias, precioBase, capacidadMaxima, destino, guia);
        setIncluyeAlimentacion(incluyeAlimentacion);
        setTemaCultural(temaCultural);
    }

    // Getters
    public boolean isIncluyeAlimentacion() { return incluyeAlimentacion; }
    public String getTemaCultural() { return temaCultural; }

    // Setters
    public void setIncluyeAlimentacion(boolean incluyeAlimentacion) {
        this.incluyeAlimentacion = incluyeAlimentacion;
    }

    public void setTemaCultural(String temaCultural) {
        //Se valida primero si el tema cultural es nulo
        if (temaCultural == null) {
            throw new IllegalArgumentException("El tema cultural no puede ser nulo");
        }
        //Luego se normaliza el texto para aceptar mayusculas o minusculas y se valida que sea uno de los temas permitidos
        String temaCultuNormalizado = normalizeTemaCultural(temaCultural.trim());
        if (!TIPOS_CULTURALES.contains(temaCultuNormalizado)) {
            throw new IllegalArgumentException("Tema cultural inválido, debe ser: Cafe, Museo, Gastronomia o City Tour");
        }
        this.temaCultural = temaCultuNormalizado;
    }

    private String normalizeTemaCultural(String temaCultural) {
        //Se devuelve el texto tal cual si esta vacio, para evitar errores al recorrer las palabras
        if (temaCultural.isEmpty()) return temaCultural;
        //Se separa el texto en palabras para poder capitalizar cada una por separado (util para nombres como "City Tour")
        String[] palabras = temaCultural.split(" ");
        StringBuilder resultado = new StringBuilder();
        //Se recorre cada palabra y se pone su primera letra en mayuscula y el resto en minuscula
        for (String palabra : palabras) {
            if (!palabra.isEmpty()) {
                resultado.append(Character.toUpperCase(palabra.charAt(0)))
                        .append(palabra.substring(1).toLowerCase())
                        .append(" ");
            }
        }
        //Se quita el espacio sobrante del final antes de devolver el resultado
        return resultado.toString().trim();
    }

    // Métodos
    @Override
    public double getPrecio() {
        //Si el tour incluye alimentacion se le suma el costo extra al precio base, si no se cobra solo el precio base
        return incluyeAlimentacion ? getPrecioBase() + COSTO_ALIMENTACION : getPrecioBase();
    }

    @Override
    public String toString() {
        //Se reutiliza el toString del padre y se le agregan los datos propios del tour cultural
        return super.toString() +
                " | Tipo: Cultural" +
                " | Tema: " + temaCultural +
                " | Alimentación: " + (incluyeAlimentacion ? "Incluida" : "No incluida");
    }
}