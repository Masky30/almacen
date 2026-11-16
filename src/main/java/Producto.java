public class Producto {

    private String nombre;
    private int stock;

    public Producto(String nombre, int stock) {
        this.nombre = nombre;
        this.stock = stock;
    }

    public String getNombre() {
        return nombre;
    }

    public int getStock() {
        return stock;
    }

    public void sumarStock(int cantidad) {
        this.stock = this.stock + cantidad;
    }

    public void restarStock(int cantidad) {
        this.stock = this.stock - cantidad;
    }

    @Override
    public String toString() {
        return nombre + " - Stock: " + stock;
    }
}
