package grupofp.modelo;

import java.util.ArrayList;

public class Datos {
    private ListaArticulos listaArticulos;
    private ListaClientes listaClientes;
    private ListaPedidos listaPedidos;
    public Datos (){
        listaArticulos = new ListaArticulos ();
        listaClientes = new ListaClientes();
        listaPedidos = new ListaPedidos ();
    }

    public ListaArticulos getListaArticulos() {
        return listaArticulos;
    }

    public void setListaArticulos(ListaArticulos listaArticulos) {
        this.listaArticulos = listaArticulos;
    }

    public ListaClientes getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(ListaClientes listaClientes) {
        this.listaClientes = listaClientes;
    }

    public ListaPedidos getListaPedidos() {
        return listaPedidos;
    }

    public void setListaPedidos(ListaPedidos listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

    @Override
    public String toString() {
        return "Datos{" +
                "listaArticulos=" + listaArticulos +
                ", listaClientes=" + listaClientes +
                ", listaPedidos=" + listaPedidos +
                '}';
    }
}
