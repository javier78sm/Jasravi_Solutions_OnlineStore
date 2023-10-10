package grupofp.modelo;

public class ClientePremium extends Cliente{

    private Double descuento;

    public Premium(int codigoCliente, String nombre, String domicilio, String nif, String email, Double descuento) {
        super(codigoCliente, nombre, domicilio, nif, email);
        this.descuento = descuento;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    @Override
    public String toString() {
        return "Premium{" +
                "descuento=" + descuento +
                '}';
    }
}
