package grupofp.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "clientepremium")
public class ClientePremium extends Cliente{

    @Column(name = "descuento")
    private float descuento;
    public ClientePremium(String nombre, String domicilio, String nif, String email, float descuento) {
        super(nombre, domicilio, nif, email);
        this.descuento=descuento;
    }
    public ClientePremium() {}
    public float getDescuento() {
        return descuento;
    }
    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }
    @Override
    public String toString() {
        return "Cliente Premium{" +
                "Nombre: " + getNombre() +
                " Domicilio: " + getDomicilio() +
                " Nif: " + getNif() +
                " Email: " + getEmail() +
                " Descuento: " + descuento +
                '}';
    }
    public String tipoCliente(){
        return "Cliente Premium";
    }
    public float descuentoEnv(){
        return getDescuento();
    }
    public  float calcAnual(){
        float calcA = 30;
        return calcA;
    }
}
