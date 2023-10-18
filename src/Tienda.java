import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Tienda {
    private static Map<String, Producto> BasedeDatos = new HashMap<>();
    private static double totalVentas = 0.0;
    private static Inventario inventario = new Inventario();

    public static void main(String[] args) {
        Scanner VlIngresado = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--------------------");
            System.out.println("Menú:");
            System.out.println("1. Añadir Producto");
            System.out.println("2. Consultar Producto");
            System.out.println("3. Realizar Venta");
            System.out.println("4. Consultar Inventario");
            System.out.println("5. Salir");
            System.out.println("--------------------");
            System.out.print("Elija una opción: ");

            opcion = VlIngresado.nextInt();
            switch (opcion) {
                case 1:
                    anadirproducto();
                    break;

                case 2:
                    consultarProducto();
                    break;

                case 3:
                    realizarVenta();
                    break;

                case 4:
                    consultarInventario();
                    break;

                case 5:
                    System.out.println("Gracias por usar el sistema de la tienda.");
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción del 1 al 5.");
                    break;
            }

        } while (opcion != 5);

        VlIngresado.close();
    }

    private static void anadirproducto() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ha elegido añadir producto");

        System.out.print("Ingrese el código del producto: ");
        String codigo = scanner.nextLine();

        System.out.print("Ingrese el nombre del producto: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese el precio del producto: ");
        double precio = Double.parseDouble(scanner.nextLine());

        System.out.print("Ingrese la cantidad en Stock: ");
        int cantidad = Integer.parseInt(scanner.nextLine());

        Producto producto = new Producto(codigo, nombre, precio, cantidad);
        BasedeDatos.put(codigo, producto);
        inventario.agregarProducto(producto);

        System.out.println("Producto agregado a la Base de Datos.");
    }

    private static void consultarProducto() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ha elegido consultar producto");

        System.out.print("Ingrese el código del producto: ");
        String consulta = scanner.nextLine();

        Producto producto = BasedeDatos.get(consulta);

        if (producto != null) {
            System.out.println("Información del Producto:");
            System.out.println("Código: " + producto.getCodigo());
            System.out.println("Nombre: " + producto.getNombre());
            System.out.println("Precio: $" + producto.getPrecio());
            System.out.println("Cantidad en inventario: " + producto.getCantidad());
        } else {
            System.out.println("Producto no encontrado en el inventario.");
        }
    }

    private static void realizarVenta() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ha elegido realizar venta");

        System.out.print("Ingrese el código del producto a vender: ");
        String codigoVenta = scanner.nextLine();

        Producto producto = BasedeDatos.get(codigoVenta);

        if (producto != null) {
            System.out.print("Ingrese la cantidad a vender: ");
            int cantidadVenta = Integer.parseInt(scanner.nextLine());

            if (cantidadVenta <= producto.getCantidad()) {
                double subtotal = cantidadVenta * producto.getPrecio();
                double iva = subtotal * 0.19;
                double totalVenta = subtotal + iva;

                System.out.println("Subtotal: $" + subtotal);
                System.out.println("IVA (19%): $" + iva);
                System.out.println("Total de la venta: $" + totalVenta);

                producto.setCantidad(producto.getCantidad() - cantidadVenta);
                totalVentas += totalVenta;

                inventario.actualizarCantidad(codigoVenta, producto.getCantidad());
            } else {
                System.out.println("No hay suficiente cantidad en Stock.");
            }
        } else {
            System.out.println("Producto no encontrado en la Base de Datos.");
        }
    }

    private static void consultarInventario() {
        System.out.println("\nInventario Actual:");
        Map<String, Integer> inventarioActual = inventario.getInventario();
        for (Map.Entry<String, Integer> entry : inventarioActual.entrySet()) {
            String codigo = entry.getKey();
            int cantidad = entry.getValue();
            System.out.println("Código: " + codigo + ", Cantidad en inventario: " + cantidad);
        }
    }
}