import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static Inventario inventario = new Inventario();

    public static void main(String[] args) {
        int opcion = 0;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");
            switch (opcion) {
                case 1:
                    registrar();
                    break;
                case 2:
                    vender();
                    break;
                case 3:
                    verInventario();
                    break;
                case 4:
                    verStockCritico();
                    break;
                case 5:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 5);
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("===== ALMACÉN =====");
        System.out.println("1. Registrar / Reponer producto");
        System.out.println("2. Vender producto");
        System.out.println("3. Ver inventario");
        System.out.println("4. Ver stock crítico");
        System.out.println("5. Salir");
    }

    private static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void registrar() {
        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();
        int cantidad = leerEntero("Cantidad recibida: ");
        try {
            inventario.registrarProducto(nombre, cantidad);
            System.out.println("Producto registrado correctamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void vender() {
        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();
        int cantidad = leerEntero("Cantidad vendida: ");
        try {
            inventario.venderProducto(nombre, cantidad);
            System.out.println("Venta realizada correctamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void verInventario() {
        ArrayList<Producto> productos = inventario.getProductos();
        if (productos.isEmpty()) {
            System.out.println("El inventario está vacío.");
            return;
        }
        System.out.println("--- INVENTARIO ---");
        for (Producto p : productos) {
            System.out.println(p);
        }
    }

    private static void verStockCritico() {
        ArrayList<Producto> criticos = inventario.obtenerStockCritico();
        if (criticos.isEmpty()) {
            System.out.println("No hay productos con stock crítico.");
            return;
        }
        System.out.println("--- STOCK CRÍTICO (5 o menos) ---");
        for (Producto p : criticos) {
            System.out.println(p);
        }
    }
}
