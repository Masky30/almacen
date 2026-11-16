import java.util.ArrayList;
import java.util.Iterator;

public class Inventario {

    public static final int STOCK_CRITICO = 5;

    private ArrayList<Producto> productos;

    public Inventario() {
        this.productos = new ArrayList<Producto>();
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public Producto buscarProducto(String nombre) {
        Iterator<Producto> it = productos.iterator();
        while (it.hasNext()) {
            Producto p = it.next();
            if (p.getNombre().equalsIgnoreCase(nombre.trim())) {
                return p;
            }
        }
        return null;
    }

    public void registrarProducto(String nombre, int cantidad) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que 0.");
        }
        Producto existente = buscarProducto(nombre);
        if (existente != null) {
            existente.sumarStock(cantidad);
        } else {
            productos.add(new Producto(nombre.trim(), cantidad));
        }
    }

    public void venderProducto(String nombre, int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que 0.");
        }
        Producto producto = buscarProducto(nombre);
        if (producto == null) {
            throw new IllegalArgumentException("El producto no existe en el inventario.");
        }
        if (cantidad > producto.getStock()) {
            throw new IllegalArgumentException("Stock insuficiente. Disponible: " + producto.getStock());
        }
        producto.restarStock(cantidad);
    }

    public ArrayList<Producto> obtenerStockCritico() {
        ArrayList<Producto> criticos = new ArrayList<Producto>();
        Iterator<Producto> it = productos.iterator();
        while (it.hasNext()) {
            Producto p = it.next();
            if (p.getStock() <= STOCK_CRITICO) {
                criticos.add(p);
            }
        }
        return criticos;
    }
}
