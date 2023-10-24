import java.util.Scanner;
import java.util.HashMap;  //Optimiza y organiza mejor la informacion guardada "clave:valor"
import java.util.Map;

public class Producto {

    private static Map<String, Producto> BasedeDatos = new HashMap<>();
    private static Inventory inventario = new Inventory();
    private static double totalVentas = 0.0;
    private String codigo;
    private String nombre;
    private double precio;
    private int cantidad;

    public Producto(String codigo, String nombre, double precio, int cantidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

        public void anadirproducto() {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Ha elegido añadir producto");

            System.out.print("Ingrese el código del producto: ");
            String codigo = scanner.nextLine();

            System.out.print("Ingrese el nombre del producto: ");
            String nombre = scanner.nextLine();

            System.out.print("Ingrese el precio del producto: ");

            boolean evaluar=true;
            while (evaluar) {
                double precio;
                try {
                    precio = Double.parseDouble(scanner.nextLine());
                    evaluar=false;
                } catch (NumberFormatException e) {
                    System.out.println("Ingrese solo valores numéricos para el precio.");
                    System.out.println("%%______________%%______________%%");
                    System.out.print("Ingrese el precio del producto: ");
                }
            }
            System.out.print("Ingrese la cantidad en Stock: ");
            int cantidad = Integer.parseInt(scanner.nextLine());

            Producto producto = new Producto(codigo, nombre, precio, cantidad);
            BasedeDatos.put(codigo, producto);  //en la clase Producto se crean nuevos objetos con los atributos requqridos
            inventario.agregarProducto(producto);

            System.out.println("Producto agregado a la Base de Datos.");
        }
        public void consultarProducto() {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Ha elegido consultar producto");

            System.out.print("Ingrese el código del producto: ");
            String consulta = scanner.nextLine();

            Producto producto = BasedeDatos.get(consulta);

            if (producto != null) {
                System.out.println("_______________________________");
                System.out.println("Información del Producto:");
                System.out.println("_______________________________");
                System.out.println("Código: " + producto.getCodigo());
                System.out.println("Nombre: " + producto.getNombre());
                System.out.println("Precio: $" + producto.getPrecio());
                System.out.println("Stock: " + producto.getCantidad());
            } else {
                System.out.println("Producto no encontrado en el inventario.");
            }
        }

    public void modificarProducto() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el código del producto que desea modificar: ");
        String codigo = scanner.nextLine();

        if (BasedeDatos.containsKey(codigo)) {
            Producto producto = BasedeDatos.get(codigo);

            // Muestra los atributos actuales del producto
            System.out.println("________________________");
            System.out.println("Producto actual:");
            System.out.println("________________________");
            System.out.println("Código: " + producto.getCodigo());
            System.out.println("Nombre: " + producto.getNombre());
            System.out.println("Precio: " + producto.getPrecio());
            System.out.println("Cantidad en Stock: " + producto.getCantidad());

            System.out.println("Ingrese los nuevos valores (deje en blanco para no cambiar):");

            System.out.print("Nuevo código: ");
            String nuevoCodigo = scanner.nextLine();
            if (!nuevoCodigo.isEmpty()) {
                producto.setCodigo(nuevoCodigo);
            }

            System.out.print("Nuevo nombre: ");
            String nuevoNombre = scanner.nextLine();
            if (!nuevoNombre.isEmpty()) {
                producto.setNombre(nuevoNombre);
            }

            System.out.print("Nuevo precio: ");
            String nuevoPrecioS = scanner.nextLine();
            if (!nuevoPrecioS.isEmpty()) {
                double nuevoPrecio = Double.parseDouble(nuevoPrecioS);
                producto.setPrecio(nuevoPrecio);
            }

            System.out.print("Nueva cantidad en Stock: ");
            String nuevaCantidadS = scanner.nextLine();
            if (!nuevaCantidadS.isEmpty()) {
                int nuevaCantidad = Integer.parseInt(nuevaCantidadS);
                producto.setCantidad(nuevaCantidad);
            }

            System.out.println("Producto modificado correctamente.");
        } else {
            System.out.println("Producto no encontrado en la Base de Datos.");
        }
    }

        public void realizarVenta() {
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

        public void consultarInventario() {

            System.out.println("\nInventario Actual:");
            Map<String, Integer> inventarioActual = inventario.getInventario();
            for (Map.Entry<String, Integer> entry : inventarioActual.entrySet()) {
                String codigo = entry.getKey();
                int cantidad = entry.getValue();
                System.out.println("Código: " + codigo + ", Cantidad en inventario: " + cantidad);
            }
        }
    }
