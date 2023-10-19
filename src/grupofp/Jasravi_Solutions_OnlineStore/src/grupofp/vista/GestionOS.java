package grupofp.vista;

import grupofp.controlador.Controlador;
import java.util.Scanner;
public class GestionOS {
    private Controlador controlador;
    Scanner teclado = new Scanner(System.in);
    public GestionOS() {
        controlador = new Controlador();
    }
    public void inicio() {
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
// TO-BE-DONE
                    break;
                case '2':
// TO-BE-DONE
                    break;
                case '3':
// TO-BE-DONE
                    break;
                    case '0':
                    salir = true;
            }
        } while (!salir);
    }
    char pedirOpcion() {
        String resp;
        System.out.println("Elige una opción (1,2,3 o 0): ");
                resp = teclado.nextLine();
        if (resp.isEmpty()) {
            resp = " ";
        }
        return resp.charAt(0);
    }
}