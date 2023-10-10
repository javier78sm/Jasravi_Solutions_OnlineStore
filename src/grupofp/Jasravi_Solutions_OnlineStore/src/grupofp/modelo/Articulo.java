package grupofp.modelo;

import java.time.LocalDate;

public class Articulo {
    private String codigo;
    private String descripcion;
    private Double precio;
    private  Double gastosEnvio;
    private LocalDate preparacionEnvio;

    /**
     * CREAMOS EL CONSTRUCTOR
     * @param codigo
     * @param descripcion
     * @param precio
     * @param gastosEnvio
     * @param preparacionEnvio
     */
    public Articulo(String codigo, String descripcion, Double precio, Double gastosEnvio, LocalDate preparacionEnvio) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.gastosEnvio = gastosEnvio;
        this.preparacionEnvio = preparacionEnvio;
    }

    /***
     *
     * @return
     */
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getGastosEnvio() {
        return gastosEnvio;
    }

    public void setGastosEnvio(Double gastosEnvio) {
        this.gastosEnvio = gastosEnvio;
    }

    public LocalDate getPreparacionEnvio() {
        return preparacionEnvio;
    }

    public void setPreparacionEnvio(LocalDate preparacionEnvio) {
        this.preparacionEnvio = preparacionEnvio;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "ARTICULO{" +
                "codigo='" + codigo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", gastosEnvio=" + gastosEnvio +
                ", preparacionEnvio=" + preparacionEnvio +
                '}';
    }
}
