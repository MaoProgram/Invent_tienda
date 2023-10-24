import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<String, Integer> inventario = new HashMap<>();

    public void agregarProducto(Producto producto) {
        inventario.put(producto.getCodigo(), producto.getCantidad());
    }

    public void actualizarCantidad(String codigo, int nuevaCantidad) {
        inventario.put(codigo, nuevaCantidad);
    }

    public Map<String, Integer> getInventario() {
        return inventario;
    }
}