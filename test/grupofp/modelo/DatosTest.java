import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import grupofp.modelo.Datos;
import grupofp.modelo.Articulo;
import grupofp.modelo.Cliente;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DatosTest {
    private Datos datos;

    @Before
    public void setUp() {
        datos = new Datos();
    }

    @Test
    public void testAniadirArticulo() {
        datos.aniadirArticulo("A001", "Artículo de prueba", 10.0f, 2.0f, 2);
        assertTrue(datos.getListaArticulos().getLista().stream().anyMatch(a -> a.getIdArticulo().equals("A001")));
    }

    @Test
    public void testAniadirCliente() {
        datos.aniadirCliente("Cliente1", "Dirección", "NIF001", "cliente1@example.com", 0.1f);
        assertTrue(datos.getListaClientes().getLista().stream().anyMatch(c -> c.getEmail().equals("cliente1@example.com")));
    }

    @Test
    public void testAniadirPedido() {
        boolean pedidoExiste = datos.aniadirPedido(1, 5, LocalDateTime.now(), "cliente1@example.com", "A001");
        assertFalse(pedidoExiste);
    }

    @Test
    public void testEliminarPedido() {
        datos.aniadirPedido(2, 3, LocalDateTime.now(), "cliente2@example.com", "A002");
        datos.borrarPedido(2);
        assertFalse(datos.getListaPedidos().getLista().stream().anyMatch(p -> p.getNumPedido() == 2));
    }

    @Test
    public void testRecorrerTodosArticulos() {
        datos.aniadirArticulo("A001", "Artículo de prueba", 10.0f, 2.0f, 2);
        ArrayList<String> articulos = datos.recorrerTodosArticulos();
        assertNotNull(articulos);
        assertTrue(articulos.size() > 0);
    }

    @Test
    public void testRecorrerTodosClientes() {
        datos.aniadirCliente("Cliente1", "Dirección", "NIF001", "cliente1@example.com", 0.1f);
        ArrayList<String> clientes = datos.recorrerTodosClientes();
        assertNotNull(clientes);
        assertTrue(clientes.size() > 0);
    }

    @Test
    public void testRecorrerClienteEstandar() {
        datos.aniadirCliente("Cliente2", "Dirección", "NIF002", "cliente2@example.com", null);
        ArrayList<String> clientesEstandar = datos.recorrerClienteE();
        assertNotNull(clientesEstandar);
        assertTrue(clientesEstandar.size() > 0);
    }

    @Test
    public void testRecorrerClientePremium() {
        datos.aniadirCliente("Cliente3", "Dirección", "NIF003", "cliente3@example.com", 0.2f);
        ArrayList<String> clientesPremium = datos.recorrerClienteP();
        assertNotNull(clientesPremium);
        assertTrue(clientesPremium.size() > 0);
    }
}
