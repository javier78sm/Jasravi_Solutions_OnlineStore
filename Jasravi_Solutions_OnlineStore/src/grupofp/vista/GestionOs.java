package grupofp.vista;

import grupofp.controlador.Controlador;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class GestionOs {
    private Controlador controlador;

    Scanner teclado = new Scanner(System.in);
    public GestionOs() {
        controlador = new Controlador();
    }
    public void inicio(){
        boolean salir = false;
        char opcio;
        do {
            System.out.println("1. Gestión Articulos");
            System.out.println("2. Gestión Clientes");
            System.out.println("3. Gestión Pedidos");
            System.out.println("0. Salir");
            opcio = pedirOpcion();
            switch (opcio) {
                case '1':
                    gestionArticulos();
                    break;
                case '2':
                    gestionClientes();
                    break;
                case '3':
                    gestionPedidos();
                    break;
                case '0':
                    salir = true;
            }
        } while (!salir);
    }
    char pedirOpcion() {
        String resp;
        System.out.println("Elige una opción: ");
        resp = teclado.nextLine();
        if (resp.isEmpty()) {
            resp = " ";
        }
        return resp.charAt(0);
    }


    public void gestionArticulos(){
        boolean cancelar = false;
        char opcio;
        do {
            System.out.println("1. Añadir Articulo");
            System.out.println("2. Mostrar Articulos");
            System.out.println("0. Salir");
            opcio = pedirOpcion();
            switch (opcio) {
                case '1':
                    addArticulo();
                    break;
                case '2':
                    mostrarArticulos();
                    break;
                case '0':
                    cancelar = true;
            }
        } while (!cancelar);
    }

    void addArticulo(){
        System.out.printf("Añade Id de Artículo: ");
        String id = teclado.nextLine();
        System.out.println("Descripcion: ");
        String descripcion = teclado.nextLine();
        System.out.println("Precio: ");
        float precio = teclado.nextFloat();
        teclado.nextLine();
        System.out.println("Gastos de envío: ");
        float gastosEnvio = teclado.nextFloat();
        teclado.nextLine();
        System.out.printf("Tiempo de preparación: ");
        int tiempoPreparacion= teclado.nextInt();;
        teclado.nextLine();

        controlador.entradaArticulo(id,descripcion,precio,gastosEnvio,tiempoPreparacion);
        System.out.println("Se ha añadido el nuevo Articulo");
    }

    void mostrarArticulos(){
        ArrayList<String> aArt = controlador.recogerTodosArticulos();
        for(String a: aArt){
            System.out.println(a);
        }
    }

    public void gestionClientes(){
        boolean cancelar = false;
        char opcio;
        do {
            System.out.println("1. Añadir Cliente");
            System.out.println("2. Mostrar Clientes");
            System.out.println("3. Mostrar Clientes Estándar");
            System.out.println("4. Mostrar Clientes Premium");
            System.out.println("0. Salir");
            opcio = pedirOpcion();
            switch (opcio) {
                case '1':
                    addCliente();
                    break;
                case '2':
                    mostrarClientes();
                    break;
                case '3':
                    mostrarClientesEstandar();
                    break;
                case '4':
                    mostrarClientesPremium();
                    break;
                case '0':
                    cancelar = true;
            }
        } while (!cancelar);
    }

    void addCliente() {
        System.out.printf("Añade nombre del Cliente: ");
        String nombre = teclado.nextLine();
        System.out.println("Domicilio: ");
        String domicilio = teclado.nextLine();
        System.out.println("Nif: ");
        String nif = teclado.nextLine();
        System.out.println("Email: ");
        String email = teclado.nextLine();

        String tipo;
        do {
            System.out.println("(1) Estandar, (2) Premium");
            tipo = teclado.nextLine();
        } while (!"12".contains(tipo));
        switch (tipo) {
            case "1":
                controlador.entradaCliente(nombre, domicilio, nif, email, null);
                System.out.println("Se ha añadido nuevo cliente Estandar");
                break;
            case "2":
                System.out.println("Descuento: ");
                float descuento = teclado.nextFloat();
                /*
                El descuento siempre es de 20 según el caso Práctico, pero para poder comprobar
                si los métodos funcionan, pasaremos de forma variable el descuento
                 */
                teclado.nextLine();

                controlador.entradaCliente(nombre, domicilio, nif,email, descuento);
                System.out.println("Se ha añadido nuevo cliente Premium");
                break;
        }

    }

    void mostrarClientes(){
        ArrayList<String> cliT = controlador.recogerTodosClientes();
        for(String cli : cliT){
            System.out.println(cli);
        }
    }

    void mostrarClientesEstandar(){
        ArrayList<String> cliE = controlador.recogerClienteEstandar();
        for(String cli: cliE){
            System.out.println(cli);
        }
    }

    void mostrarClientesPremium(){
        ArrayList<String> cliP = controlador.recogerClientePremium();
        for(String cli: cliP){
            System.out.println(cli);
        }
    }

    public void gestionPedidos(){
        boolean cancelar = false;
        char opcio;
        do {
            System.out.println("1. Añadir Pedido");
            System.out.println("2. Eliminar Pedido");
            System.out.println("3. Mostrar Pedidos Pedientes de Envío");
            System.out.println("4. Mostrar Pedidos Enviados");
            System.out.println("0. Salir");
            opcio = pedirOpcion();
            switch (opcio) {
                case '1':
                    addPedido();
                    break;
                case '2':
                    eliminarPedido();
                    break;
                case '3':
                    mostrarPedidosPendientes();
                    break;
                case '4':
                    mostrarPedidosEnviados();
                    break;
                case '0':
                    cancelar = true;
            }
        } while (!cancelar);
    }

    public void addPedido(){
        System.out.printf("Número de pedido: ");
        int numPedido = teclado.nextInt();
        teclado.nextLine();
        System.out.println("Cantidad: ");
        int cantidad = teclado.nextInt();
        teclado.nextLine();
        /*
        Más adelante, la hora del pedido será igual a LocalDateTime.now(), ya que el
        pedido tendrá la fecha de cuando se añade al sistema. Para poder introducir pedidos
        de diferentes fechas, pediremos, de momento, introducir la fecha del pedido.
         */
        System.out.println("Fecha y hora del pedido: (dd/MM/yyyy/HH/mm)");
        String f = teclado.nextLine();
        LocalDateTime fecha = LocalDateTime.parse(f, DateTimeFormatter.ofPattern("dd/MM/yyyy/HH/mm"));
        System.out.println(fecha);
        System.out.println("Email del Cliente: ");
        String email = teclado.nextLine();
        System.out.println("Id de Articulo: ");
        String id = teclado.nextLine();
        if(!controlador.existeC(email)){
            addCliente();
        }
        controlador.entradaPedido(numPedido, cantidad, fecha, email, id);

        System.out.println("Se ha añadido el nuevo Pedido");
    }

    public void eliminarPedido(){
        System.out.printf("Indica el numero de pedido para eliminar: ");
        int numPedido = teclado.nextInt();
        teclado.nextLine();
        controlador.eliminarPedido(numPedido);
    }

    public void mostrarPedidosPendientes(){
        boolean cancelar = false;
        char opcio;
        do {
            System.out.println("1. Mostrar todos los Pedidos Pendientes");
            System.out.println("2. Filtrar por Cliente");
            System.out.println("0. Salir");
            opcio = pedirOpcion();
            switch (opcio) {
                case '1':
                    motrarTodosPendientes();
                    break;
                case '2':
                    filtrarClientePend();
                    break;
                case '0':
                    cancelar = true;
            }
        } while (!cancelar);
    }
    public void motrarTodosPendientes() {
        ArrayList<String> aTodosPend = controlador.todosPendientes();
        /*for (String tP : aTodosPend) {
            System.out.println(aTodosPend);
        }*/
        for (int i = 0; i < aTodosPend.size(); ++i) System.out.println(aTodosPend.get(i));
    }
    public void filtrarClientePend(){
        System.out.println("Introduce email del cliente: ");
        String email = teclado.nextLine();
        ArrayList<String> fClientePendiente = controlador.filtrarClientePendiente(email);
        for(String f : fClientePendiente){
            System.out.println(fClientePendiente);
        }
    }

    public void mostrarPedidosEnviados(){
        boolean cancelar = false;
        char opcio;
        do {
            System.out.println("1. Mostrar todos los Pedidos Enviados");
            System.out.println("2. Filtrar por Cliente");
            System.out.println("0. Salir");
            opcio = pedirOpcion();
            switch (opcio) {
                case '1':
                    motrarTodosEnviados();
                    break;
                case '2':
                    filtrarClienteEnv();
                    break;
                case '0':
                    cancelar = true;
            }
        } while (!cancelar);
    }
    public void motrarTodosEnviados() {
        ArrayList<String> aTodosEnv = controlador.todosEnviados();
        /*for (String tP : aTodosEnv) {
            System.out.println(aTodosEnv);
        }*/
        for (int i = 0; i < aTodosEnv.size(); ++i) System.out.println(aTodosEnv.get(i));
    }
    public void filtrarClienteEnv(){
        System.out.println("Introduce email del cliente: ");
        String email = teclado.nextLine();
        ArrayList<String> fClienteEnviado = controlador.filtrarClienteEnviado(email);
        for(String f : fClienteEnviado){
            System.out.println(fClienteEnviado);
        }
    }

    // EXCEPCIONES
    private char ControlMenu() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().charAt(0);
    }
}