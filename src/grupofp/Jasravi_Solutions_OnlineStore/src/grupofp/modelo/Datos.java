package grupofp.modelo;

import java.util.ArrayList;

public class Datos {
    private ArrayList<Cliente> cli = new ArrayList<Cliente>();
    private ArrayList<Articulo> art = new ArrayList<Articulo>();
    private ArrayList<Pedidos> pedido = new ArrayList<>();

    public Datos(ArrayList<Cliente> cli, ArrayList<Articulo> art, ArrayList<Pedidos> pedido) {
        this.cli = cli;
        this.art = art;
        this.pedido = pedido;
    }

    public ArrayList<Cliente> getCli() {
        return cli;
    }

    public void setCli(ArrayList<Cliente> cli) {
        this.cli = cli;
    }

    public ArrayList<Articulo> getArt() {
        return art;
    }

    public void setArt(ArrayList<Articulo> art) {
        this.art = art;
    }

    public ArrayList<Pedidos> getPedido() {
        return pedido;
    }

    public void setPedido(ArrayList<Pedidos> pedido) {
        this.pedido = pedido;
    }

    public void addArticulo(Articulo art){ this.art.add(art);}

    @Override
    public String toString() {
        return "Datos{" +
                "cli=" + cli +
                ", art=" + art +
                ", pedido=" + pedido +
                '}';
    }
}
