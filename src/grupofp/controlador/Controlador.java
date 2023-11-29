package grupofp.controlador;

import grupofp.dao.DAOException;
import grupofp.modelo.Datos;

import java.time.LocalDateTime;
import java.util.ArrayList;


/*
La clase Controlador será utilizada para intervenir entre la vista y
los datos del modelo. Con este objetivo, la clase Controlador utilizará
una instancia de la clase Datos, por ser la clase principal de la capa modelo.
 */
public class Controlador{
    private Datos datos;

    public Controlador() {
        datos = new Datos ();
    }
    // TO-BE-DONE
    public Datos getDatos() {
        return datos;
    }

    public void setDatos(Datos datos) {
        this.datos = datos;
    }


    public void entradaArticulo(String id, String descripcion, float precio, float gastosEnvio, int tiempoPreparacion) {
        datos.aniadirArticulo(id, descripcion, precio, gastosEnvio, tiempoPreparacion);
    }
    public ArrayList recogerTodosArticulos(){
        ArrayList<String> arrArticulos = new ArrayList<>();
        arrArticulos= datos.recorrerTodosArticulos();
        return arrArticulos;
    }

    public void entradaCliente(String nombre, String domicilio, String nif, String email, Float descuento){
        if(descuento != null){
            datos.aniadirCliente(nombre, domicilio, nif, email, descuento);
        }else {
            datos.aniadirCliente(nombre, domicilio, nif, email, null);
        }

    }
    public ArrayList recogerTodosClientes(){
        ArrayList<String> arrClientes = new ArrayList<>();
        arrClientes= datos.recorrerTodosClientes();
        return arrClientes;
    }
    public ArrayList recogerClienteEstandar(){
        ArrayList<String> arrClienteEstandar = new ArrayList<>();
        arrClienteEstandar = datos.recorrerClienteE();
        return arrClienteEstandar;
    }

    public ArrayList recogerClientePremium(){
        ArrayList<String> arrClientePremium = new ArrayList<>();
        arrClientePremium = datos.recorrerClienteP();
        return arrClientePremium;
    }

    public boolean entradaPedido(int numPedido, int cantidad, LocalDateTime fecha, String email, String id) {
        boolean existe = datos.aniadirPedido(numPedido, cantidad, fecha, email, id);
        return existe;
    }


    public void eliminarPedido(int numPedido){
        datos.borrarPedido(numPedido);
    }

    public ArrayList<String> todosPendientes(){
        ArrayList<String> arrTodosPendientes = new ArrayList<>();
        arrTodosPendientes = datos.pendientes();
        return arrTodosPendientes;
    }
    public ArrayList<String> filtrarClientePendiente(String email){
        ArrayList<String> arrFiltroCliente = new ArrayList<>();
        arrFiltroCliente = datos.filtroPendiente(email);
        return arrFiltroCliente;
    }
    public ArrayList<String> todosEnviados(){
        ArrayList<String> arrTodosEnviados = new ArrayList<>();
        arrTodosEnviados = datos.enviados();
        return arrTodosEnviados;
    }
    public ArrayList<String> filtrarClienteEnviado(String email){
        ArrayList<String> arrFiltroCliente = new ArrayList<>();
        arrFiltroCliente = datos.filtroEnviado(email);
        return arrFiltroCliente;
    }
    public boolean existeC(String email){
        if(datos.existeCliente(email)){
            return true;
        }
        return false;
    }

    // EXCEPCIONES

    public class EmailValidationException extends Exception {
        public EmailValidationException(String message) {
            super(message);
        }
    }

//    public static class NIFValidationException extends Exception {
//        public NIFValidationException(String message) {
//            super(message);
//        }
//    }

}
