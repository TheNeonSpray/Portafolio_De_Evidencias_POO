//Esta clase abstracta se encarga de definir lo común a todos los tours y obligar a calcular el precio final en cada tipo.
public abstract class Tour {

    //Attributes
    private String codigo;
    private String nombre;
    private int duracionDias;
    private double precioBase;
    private Destino destino; //Un Tour tiene un **Destino* que existe por su cuenta y puede reasignarse → **agregación**.
    private Guia guia; //Un Tour tiene un **Guía** que existe por su cuenta y puede reasignarse → **agregación**.

    //Constructor

    public Tour(String codigo, String nombre, int duracionDias, double precioBase, Destino destino, Guia guia) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.duracionDias = duracionDias;
        this.precioBase = precioBase;
        this.destino = destino;
        this.guia = guia;
    }

    //Getters

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDuracionDias() {
        return duracionDias;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public Destino getDestino() {
        return destino;
    }

    public Guia getGuia() {
        return guia;
    }

    //Setters

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDuracionDias(int duracionDias) {
        this.duracionDias = duracionDias;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    public void setDestino(Destino destino) {
        this.destino = destino;
    }

    public void setGuia(Guia guia) {
        this.guia = guia;
    }

    //Methods
}
