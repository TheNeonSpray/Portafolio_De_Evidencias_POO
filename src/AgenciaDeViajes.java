import java.util.ArrayList;

//Administra las listas de tours, clientes, guías y reservas; orquesta las operaciones.
public class AgenciaDeViajes {

    //Attributes
    private String nombre;
    private ArrayList<Tour> tours;
    private ArrayList<Cliente> clientes;
    private ArrayList<Guia> guias;
    private ArrayList<Reserva> reservas;

    //Constructor

    public AgenciaDeViajes(String nombre, ArrayList<Tour> tours, ArrayList<Cliente> clientes, ArrayList<Guia> guias, ArrayList<Reserva> reservas) {
        this.nombre = nombre;
        this.tours = tours;
        this.clientes = clientes;
        this.guias = guias;
        this.reservas = reservas;
    }

    //Getters

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Tour> getTours() {
        return tours;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public ArrayList<Guia> getGuias() {
        return guias;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    //Setters

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTours(ArrayList<Tour> tours) {
        this.tours = tours;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public void setGuias(ArrayList<Guia> guias) {
        this.guias = guias;
    }

    public void setReservas(ArrayList<Reserva> reservas) {
        this.reservas = reservas;
    }

    //Methods
}
