package grupofp.modelo;

import grupofp.controlador.Controlador;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.procedure.ProcedureOutputs;
import org.hibernate.query.Query;
import org.hibernate.result.ResultSetOutput;


import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/*
La clase Datos es la clase principal del paquete del modelo, puesto que
contiene y gestiona todos los datos de la aplicación y es el enlace entre
el controlador y el resto de las clases del modelo ya que el controlador solo
llamará a los métodos de esta clase
 */
public class Datos {
    private ListaArticulos listaArticulos;
    private ListaClientes listaClientes;
    private ListaPedidos listaPedidos;
    // Variable de MySQLConnector class para conectar a la BBDD
    private Connection dbConnection;

    private SessionFactory sessionFactory;


    public Datos() {


        // Conexión a la BBDD
        MySQLConnector connector = new MySQLConnector();
        dbConnection = connector.getConnection();

        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        sessionFactory = configuration.buildSessionFactory();

        listaArticulos = new ListaArticulos();
        listaClientes = new ListaClientes();
        listaPedidos = new ListaPedidos();

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

public void aniadirArticulo(String id, String descripcion, float precio, float gastosEnvio, int tiempoPreparacion) {
    Articulo nuevoArticulo = new Articulo(id, descripcion, precio, gastosEnvio, tiempoPreparacion);

    // Abre la sesión de Hibernate
    try (Session session = sessionFactory.openSession()) {
        // Inicia la transacción
        Transaction transaction = session.beginTransaction();

        // Guarda el nuevo Articulo en la base de datos
        session.save(nuevoArticulo);

        // Confirma la transacción
        transaction.commit();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
public ArrayList<String> recorrerTodosArticulos() {
    ArrayList<String> arrArticulos = new ArrayList<>();

    try (Session session = sessionFactory.openSession()) {
        // Comienza la transacción
        Transaction transaction = session.beginTransaction();

        // Ejemplo de recuperación de todos los registros de la tabla Articulo
        Query<Articulo> query = session.createQuery("FROM Articulo", Articulo.class);
        List<Articulo> articulos = query.list();

        // Procesa los registros recuperados
        for (Articulo articulo : articulos) {
            String idArticulo = articulo.getIdArticulo();
            String descripcion = articulo.getDescripcion();
            float precio = articulo.getPrecio();
            float gastosEnvio = articulo.getGastosEnvio();
            int tiempoPreparacion = articulo.getTiempoPreparacion();

            String articuloInfo = "Articulo{" +
                    "idArticulo='" + idArticulo + '\'' +
                    ", descripcion='" + descripcion + '\'' +
                    ", precio=" + precio +
                    ", gastosEnvio=" + gastosEnvio +
                    ", tiempoPreparacion=" + tiempoPreparacion +
                    '}';
            arrArticulos.add(articuloInfo);
        }

        // Finaliza la transacción
        transaction.commit();
    } catch (Exception e) {
        e.printStackTrace();
    }


    return arrArticulos;
}

public void aniadirCliente(String nombre, String domicilio, String nif, String email, Float descuento) {

    Cliente nuevoCliente = null;
    if (descuento != null) {
        nuevoCliente = new ClientePremium(nombre, domicilio, nif, email, descuento);
    }
    else {
        nuevoCliente = new ClienteEstandar(nombre, domicilio, nif, email);
    }

    // Abre la sesión de Hibernate
    try (Session session = sessionFactory.openSession()) {
        // Inicia la transacción
        Transaction transaction = session.beginTransaction();

        // Guarda el nuevo Cliente en la base de datos
        session.save(nuevoCliente);

        // Confirma la transacción
        transaction.commit();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    public ArrayList<String> recorrerTodosClientes() {
        ArrayList<String> arrClientes = new ArrayList<>();
              try (Session session = sessionFactory.openSession()) {
            // Comienza la transacción
            Transaction transaction = session.beginTransaction();

            Query<Cliente> query = session.createQuery("FROM Cliente", Cliente.class);
            List<Cliente> clientes = query.list();

            // Procesa los registros recuperados
            for (Cliente cliente : clientes) {
                int idCliente = cliente.getId();
                String nombre = cliente.getNombre();
                String domicilio = cliente.getDomicilio();
                String nif = cliente.getNif();
                String email = cliente.getEmail();

                String clienteInfo = "Cliente{" +
                        "idCliente='" + idCliente + '\'' +
                        ", nombre='" + nombre + '\'' +
                        ", domicilio=" + domicilio +
                        ", nif=" + nif +
                        ", email=" + email +
                        '}';
                arrClientes.add(clienteInfo);
            }

            // Finaliza la transacción
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arrClientes;
    }
    public ArrayList recorrerClienteE() {
        ArrayList<String> arrClienteEstandar = new ArrayList<>();

        try (Session session = sessionFactory.openSession()) {
            // Comienza la transacción
            Transaction transaction = session.beginTransaction();

            ProcedureCall procedureCall = session.createStoredProcedureCall("obtenerClientesEstandar");

            ProcedureOutputs procedureOutputs = procedureCall.getOutputs();
            ResultSetOutput resultSetOutput = (ResultSetOutput) procedureOutputs.getCurrent();

            // Procesa los registros recuperados
            resultSetOutput.getResultList().forEach(row -> {
                if (row instanceof Object[]) {
                    Object[] columns = (Object[]) row;
                    int id = (int)columns[0];
                    String nombre = (String)columns[1];
                    String domicilio = (String)columns[2];
                    String nif = (String)columns[3];
                    String email = (String)columns[4];

                    arrClienteEstandar.add("ID: " + id + " Nombre: " + nombre + " Domicilio: " + domicilio + " NIF: " + nif + " Email: " + email);
                }
            });
            // Finaliza la transacción
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrClienteEstandar;
    }
    public ArrayList recorrerClienteP() {
        ArrayList<String> arrClientePremium = new ArrayList<>();
               try (Session session = sessionFactory.openSession()) {
            // Comienza la transacción
            Transaction transaction = session.beginTransaction();

            ProcedureCall procedureCall = session.createStoredProcedureCall("obtenerClientesPremium");

            ProcedureOutputs procedureOutputs = procedureCall.getOutputs();
            ResultSetOutput resultSetOutput = (ResultSetOutput) procedureOutputs.getCurrent();

            // Procesa los registros recuperados
            resultSetOutput.getResultList().forEach(row -> {
                if (row instanceof Object[]) {
                    Object[] columns = (Object[]) row;
                    int id = (int)columns[0];
                    String nombre = (String)columns[1];
                    String domicilio = (String)columns[2];
                    String nif = (String)columns[3];
                    String email = (String)columns[4];
                    float descuento = (float)columns[6];

                    arrClientePremium.add("ID: " + id + " Nombre: " + nombre + " Domicilio: " + domicilio + " NIF: " + nif + " Email: " + email + " Descuento: " + descuento);
                }
            });
            // Finaliza la transacción
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arrClientePremium;
    }

    public boolean aniadirPedido(int numPedido, int cantidad, LocalDateTime fecha, String email, String id) {
        Cliente cliente = obtenerClientePorEmail(email);
        Articulo articulo = obtenerArticuloPorId(id);

        if (cliente != null && articulo != null) {
            Pedido nuevoPedido = new Pedido(numPedido, cantidad, fecha, cliente, articulo);

            // Abre la sesión de Hibernate
            try (Session session = sessionFactory.openSession()) {
                // Inicia la transacción
                Transaction transaction = session.beginTransaction();

                // Guarda el nuevo Pedido en la base de datos
                session.save(nuevoPedido);

                // Confirma la transacción
                transaction.commit();
                System.out.println("Pedido agregado con éxito a la base de datos.");
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Error al agregar pedido a la base de datos: " + e.getMessage());
            }
        } else {
            System.err.println("No se pudo agregar el pedido porque el cliente o el artículo no fueron encontrados.");
        }
        return false;
    }
    private Cliente obtenerClientePorEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            // Comienza la transacción
            Transaction transaction = session.beginTransaction();

            Query<Cliente> query = session.createQuery("FROM Cliente WHERE email = :email", Cliente.class);
            query.setParameter("email", email);
            Cliente cliente = query.uniqueResult();

            // Finaliza la transacción
            transaction.commit();

            return cliente;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al obtener cliente por email: " + e.getMessage());
        }
        return null;
    }
    private Articulo obtenerArticuloPorId(String id) {
        try (Session session = sessionFactory.openSession()) {
            // Comienza la transacción
            Transaction transaction = session.beginTransaction();

            Query<Articulo> query = session.createQuery("FROM Articulo WHERE idArticulo = :id", Articulo.class);
            query.setParameter("id", id);
            Articulo articulo = query.uniqueResult();

            // Finaliza la transacción
            transaction.commit();

            return articulo;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al obtener artículo por ID: " + e.getMessage());
        }
        return null;
    }

    public Articulo dameArticulo(String id){
        String selectQuery = "SELECT * FROM articulo WHERE idArticulo = ?";
        Articulo articulo = new Articulo();

        try (PreparedStatement preparedStatement = dbConnection.prepareStatement(selectQuery)) {
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                articulo.setIdArticulo(resultSet.getString("idArticulo"));
                articulo.setDescripcion(resultSet.getString("descripcion"));
                articulo.setPrecio(resultSet.getFloat("precio"));
                articulo.setGastosEnvio(resultSet.getFloat("gastosEnvio"));
                articulo.setTiempoPreparacion(resultSet.getInt("tiempoPreparacion"));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el artículo desde la base de datos: " + e.getMessage());
        }
        return articulo;
    }
    public boolean existeCliente(String email) {
        String selectQuery = "SELECT COUNT(*) FROM cliente WHERE email = ?";

        try (PreparedStatement preparedStatement = dbConnection.prepareStatement(selectQuery)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar la existencia del cliente en la base de datos: " + e.getMessage());
        }
        return false;
    }
    public int dameCliente(String email){
            String selectQuery = "SELECT idCliente FROM cliente WHERE email = ?";
            int clienteId = -1;

            try (PreparedStatement preparedStatement = dbConnection.prepareStatement(selectQuery)) {
                preparedStatement.setString(1, email);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    clienteId = resultSet.getInt("idCliente");
                }
            } catch (SQLException e) {
                System.err.println("Error al obtener el ID del cliente desde la base de datos: " + e.getMessage());
            }

            return clienteId;
    }
public void aniadirClientePedido(int numPedido, int cantidad, LocalDateTime fecha, String email, String idArticulo) {
    int clienteId = dameCliente(email);
    Articulo articulo = dameArticulo(idArticulo);

    if (clienteId != -1 && articulo.getIdArticulo() != null) {
        String insertPedidoQuery = "INSERT INTO pedido (numPedido, cantidad, fecha, idCliente, idArticulo) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = dbConnection.prepareStatement(insertPedidoQuery)) {
            preparedStatement.setInt(1, numPedido);
            preparedStatement.setInt(2, cantidad);
            preparedStatement.setObject(3, fecha);
            preparedStatement.setInt(4, clienteId);
            preparedStatement.setString(5, articulo.getIdArticulo());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Pedido agregado con éxito.");
            }
        } catch (SQLException e) {
            System.err.println("Error al agregar pedido a la base de datos: " + e.getMessage());
        }
    } else {
        System.err.println("No se pudo agregar el pedido porque el cliente o el artículo no fueron encontrados.");
    }
}
    public void borrarPedido(int numPedido) {
        String deletePedidoQuery = "DELETE FROM pedido WHERE numPedido = ?";

        try (PreparedStatement preparedStatement = dbConnection.prepareStatement(deletePedidoQuery)) {
            preparedStatement.setInt(1, numPedido);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Pedido eliminado con éxito.");
            } else {
                System.err.println("No se pudo eliminar el pedido, el número de pedido no fue encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar el pedido de la base de datos: " + e.getMessage());
        }
    }
    public ArrayList<String> pendientes() {
        ArrayList<String> arrPedido = new ArrayList<>();

        try (Session session = sessionFactory.openSession()) {
            // Comienza la transacción
            Transaction transaction = session.beginTransaction();

            // Ejemplo de recuperación de todos los registros de la tabla Pedido
            Query<Pedido> query = session.createQuery("FROM Pedido", Pedido.class);
            List<Pedido> pedidos = query.list();

            // Procesa los registros recuperados
            for (Pedido pedido : pedidos) {
                if (!pedido.pedidoEnviado()) {
                    int idPedido = pedido.getId();
                    int numPedido = pedido.getNumPedido();
                    int cantidad = pedido.getCantidad();
                    LocalDateTime fecha = pedido.getFecha();
                    int idCliente = pedido.getCliente().getId();
                    int idArticulo = Integer.parseInt(pedido.getArticulo().getIdArticulo());

                    arrPedido.add("ID: " + idPedido + " Número de pedido: " + numPedido + " Cantidad: " + cantidad +
                            " Fecha: " + fecha + " IDCliente: " + idCliente + " IDArticulo " + idArticulo);
                }
            }

            // Finaliza la transacción
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrPedido;
    }
    public ArrayList<String> filtroPendiente(String email){
        ArrayList<String> filtro = new ArrayList<>();
        for(Pedido p : listaPedidos.lista){
            if(p.getCliente().getEmail().equals(email) && p.pedidoEnviado() == false){
                filtro.add(p.toString());
            }
        }
        return filtro;
    }
    public ArrayList<String> enviados() {
        ArrayList<String> arrPedido = new ArrayList<>();

        String selectQuery = "SELECT * from pedido";

        try (PreparedStatement preparedStatement = dbConnection.prepareStatement(selectQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Procesando pedidos...");
            while (resultSet.next()) {
                int id = resultSet.getInt("idPedido");
                int numPedido = resultSet.getInt("numPedido");
                int cantidad = resultSet.getInt("cantidad");
                LocalDate fecha = resultSet.getDate("fecha").toLocalDate();
                int idCliente = resultSet.getInt("idCliente");
                int idArticulo = resultSet.getInt("idArticulo");

                if (fecha.isBefore(LocalDate.now().minusDays(1))) {
                    arrPedido.add("ID: " + id + " Número de pedido: " + numPedido + " Cantidad: " + cantidad + " Fecha: " + fecha + " IDCliente: " + idCliente + " IDArticulo " + idArticulo);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al recuperar los clientes de la base de datos: " + e.getMessage());
        }
        return arrPedido;
    }
    public ArrayList<String> filtroEnviado(String email){
        ArrayList<String> filtro = new ArrayList<>();
        for(Pedido p : listaPedidos.lista){
            if(p.getCliente().getEmail().equals(email) && p.pedidoEnviado() == true){
                filtro.add(p.toString());
            }
        }
        return filtro;
    }
}
