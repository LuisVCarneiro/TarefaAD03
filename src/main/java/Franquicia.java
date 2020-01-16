
public class Franquicia {
    Tendas tendas = new Tendas();
    Clientes clientes = new Clientes();

    public Franquicia() {
    }
    
    public Franquicia (Tendas tendas, Clientes clientes){
        this.clientes = clientes;
        this.tendas = tendas;
    }

    public Tendas getTendas() {
        return tendas;
    }

    public void setTendas(Tendas tendas) {
        this.tendas = tendas;
    }

    public Clientes getClientes() {
        return clientes;
    }

    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }
    
}
