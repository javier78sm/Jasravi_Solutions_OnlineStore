package grupofp.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "clienteestandar")
public class ClienteEstandar extends Cliente{
    public ClienteEstandar(String nombre, String domicilio, String nif, String email) {
        super(nombre, domicilio, nif, email);
    }
    public ClienteEstandar() {
        super();
    }

    @Override
    public String toString() {
        return "Cliente Estándar{" +
                "Nombre: " + getNombre() +
                " Domicilio: " + getDomicilio() +
                " Nif: " + getNif() +
                " Email: " + getEmail() +
                '}';
    }

    public String tipoCliente(){
        return "Cliente Estandar";
    }

    public float descuentoEnv(){
        float descuentoEnv = 1;
        return descuentoEnv;
    }
    public  float calcAnual(){
        float calcA = 0;
        return calcA;
    }
}
