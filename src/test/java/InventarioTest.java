import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InventarioTest {

    private Inventario inventario;

    @BeforeEach
    void preparar() {
        inventario = new Inventario();
    }

    @Test
    void registrarProductoNuevoLoAgregaAlInventario() {
        inventario.registrarProducto("Bebida 3L", 10);
        assertEquals(1, inventario.getProductos().size());
        assertEquals(10, inventario.buscarProducto("Bebida 3L").getStock());
    }

    @Test
    void registrarProductoExistenteSumaStock() {
        inventario.registrarProducto("Bebida 3L", 10);
        inventario.registrarProducto("Bebida 3L", 5);
        assertEquals(1, inventario.getProductos().size());
        assertEquals(15, inventario.buscarProducto("Bebida 3L").getStock());
    }

    @Test
    void venderProductoRestaStock() {
        inventario.registrarProducto("Bebida 3L", 10);
        inventario.venderProducto("Bebida 3L", 2);
        assertEquals(8, inventario.buscarProducto("Bebida 3L").getStock());
    }

    @Test
    void ventaSuperiorAlStockEsRechazada() {
        inventario.registrarProducto("Bebida 3L", 3);
        assertThrows(IllegalArgumentException.class, () -> inventario.venderProducto("Bebida 3L", 4));
        assertEquals(3, inventario.buscarProducto("Bebida 3L").getStock());
    }

    @Test
    void ventaDeProductoInexistenteEsRechazada() {
        assertThrows(IllegalArgumentException.class, () -> inventario.venderProducto("Pan", 1));
    }

    @Test
    void stockCriticoMuestraSoloProductosConCincoOMenos() {
        inventario.registrarProducto("Bebida 3L", 10);
        inventario.registrarProducto("Pan", 5);
        inventario.registrarProducto("Leche", 2);
        assertEquals(2, inventario.obtenerStockCritico().size());
    }
}
