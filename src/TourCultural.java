//Hereda de Tour. Un tour cultural, ajusta el precio si incluye alimentación.
public class TourCultural extends Tour {

    public static final String[] TIPO_CULTURAL = {"Cafe", "Museo", "Gastronomia", "City Tour"};

    //Attributes
    private boolean incluyeAlimentacion;
    private String temaCultural;

    //Constructor

    public TourCultural(String codigo, String nombre, int duracionDias, double precioBase, Destino destino, Guia guia, boolean incluyeAlimentacion, String temaCultural) {
        super(codigo, nombre, duracionDias, precioBase, destino, guia);
        this.incluyeAlimentacion = incluyeAlimentacion;
        this.temaCultural = temaCultural;
    }

    //Getters

    public boolean isIncluyeAlimentacion() {
        return incluyeAlimentacion;
    }

    public String getTemaCultural() {
        return temaCultural;
    }

    //Setters

    public void setIncluyeAlimentacion(boolean incluyeAlimentacion) {
        this.incluyeAlimentacion = incluyeAlimentacion;
    }

    public void setTemaCultural(String temaCultural) {
        if (temaCultural != null) {
            this.temaCultural = "City Tour";
            return;
        }
        for (String tipoCultural : TIPO_CULTURAL) {
            if (temaCultural.equals(tipoCultural)) {
                this.temaCultural = temaCultural;
                return;
            }
        }
    }

    //Methods
}
