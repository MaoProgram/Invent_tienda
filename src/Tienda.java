import java.util.Scanner;

public class Tienda {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userName = "";
        String password = "";

        while (true) {
            System.out.println("________________________________________");
            System.out.println("Menú de Usuario y Contraseña:");
            System.out.println("________________________________________");
            System.out.println("1. Establecer Usuario y Contraseña");
            System.out.println("2. Iniciar Sesión");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            int selector = scanner.nextInt();
            scanner.nextLine();

            switch (selector) {
                case 1:
                    System.out.print("Ingrese un nuevo nombre de usuario: ");
                    userName = scanner.nextLine();
                    System.out.print("Ingrese una nueva contraseña: ");
                    password = scanner.nextLine();
                    System.out.println("Usuario y contraseña actualizados.");
                    break;
                case 2:
                    System.out.print("Ingrese su nombre de usuario: ");
                    String inputUserName = scanner.nextLine();
                    System.out.print("Ingrese su contraseña: ");
                    String inputPassword = scanner.nextLine();

                    if (inputUserName.equals(userName) && inputPassword.equals(password)) {
                        System.out.println("Inicio de sesión exitoso.");
                        menu2();
                    } else {
                        System.out.println("Inicio de sesión fallido. Usuario o contraseña incorrectos.");
                    }
                    break;
                case 3:
                    System.out.println("Saliendo del programa.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                    break;
            }
        }
    }
    public static void menu1(){
        Scanner scanner = new Scanner(System.in);
        String userName = "";
        String password = "";

        while (true) {
            System.out.println("________________________________");
            System.out.println("Menú de Usuario y Contraseña:");
            System.out.println("________________________________");
            System.out.println("1. Establecer Usuario y Contraseña");
            System.out.println("2. Iniciar Sesión");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            int selector = scanner.nextInt();
            scanner.nextLine();

            switch (selector) {
                case 1:
                    System.out.print("Ingrese un nuevo nombre de usuario: ");
                    userName = scanner.nextLine();
                    System.out.print("Ingrese una nueva contraseña: ");
                    password = scanner.nextLine();
                    System.out.println("Usuario y contraseña actualizados.");
                    break;
                case 2:
                    System.out.print("Ingrese su nombre de usuario: ");
                    String inputUserName = scanner.nextLine();
                    System.out.print("Ingrese su contraseña: ");
                    String inputPassword = scanner.nextLine();

                    if (inputUserName.equals(userName) && inputPassword.equals(password)) {
                        System.out.println("Inicio de sesión exitoso.");
                        menu2();
                    } else {
                        System.out.println("Inicio de sesión fallido. Usuario o contraseña incorrectos.");
                    }
                    break;
                case 3:
                    System.out.println("Saliendo del programa.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                    break;
            }
        }
    }


    public  static void menu2() {
        Scanner VlIngresado = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n______________________");
            System.out.println("Menú:");
            System.out.println("________________________");
            System.out.println("1. Añadir Producto");
            System.out.println("2. Consultar Producto");
            System.out.println("3. Modificar Producto");
            System.out.println("4. Realizar Venta");
            System.out.println("5. Consultar Inventario");
            System.out.println("6. Salir");
            System.out.println("_________________________");
            System.out.print("Elija una opción: ");

            opcion = VlIngresado.nextInt();
            switch (opcion) {
                case 1:
                    Producto agregar = new Producto("001","Cesar",1500,30);
                    agregar.anadirproducto();
                    break;

                case 2:
                    Producto consultar = new Producto("001","Cesar",1500,30);
                    consultar.consultarProducto();
                    break;

                case 3:
                    Producto modificar = new Producto("001","Cesar",1500,30);
                    modificar.modificarProducto();
                    break;

                case 4:
                    Producto venta = new Producto("001","Cesar",1500,30);
                    venta.realizarVenta();
                    break;

                case 5:
                    Producto stock = new Producto("001","Cesar",1500,30);
                    stock.consultarInventario();
                    break;

                case 6:

                    System.out.println("Gracias por usar el sistema de la tienda.");
                    System.out.println("Bienvenido!! Menu de Inicio");
                    menu1();

                    break;

                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción del 1 al 5.");
                    break;
            }

        } while (opcion != 6);

        VlIngresado.close();
    }


}