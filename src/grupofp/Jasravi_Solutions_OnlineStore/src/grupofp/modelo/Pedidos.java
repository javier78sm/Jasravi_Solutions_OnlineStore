package grupofp.modelo;

import java.time.LocalDate;

public class Pedidos {
    private String codigoPedido;
    private LocalDate fechaCompra;
    private Cliente cli;
    private Articulo art;
    private int cantidad;


    public Pedidos(String codigoPedido, LocalDate fechaCompra, Cliente cli, Articulo art, int cantidad) {
        this.codigoPedido = codigoPedido;
        this.fechaCompra = fechaCompra;
        this.cli = cli;
        this.art = art;
        this.cantidad = cantidad;
    }

    public String getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(String codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Cliente getCli() {
        return cli;
    }

    public void setCli(Cliente cli) {
        this.cli = cli;
    }

    public Articulo getArt() {
        return art;
    }

    public void setArt(Articulo art) {
        this.art = art;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "PEDIDOS{" +
                "codigoPedido='" + codigoPedido + '\'' +
                ", fechaCompra=" + fechaCompra +
                ", cli=" + cli +
                ", art=" + art +
                ", cantidad=" + cantidad +
                '}';
    }
}
