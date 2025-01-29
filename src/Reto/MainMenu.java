package Reto;

import java.util.Scanner;

public class MainMenu {
	
	
	public static void main(String[] args) {
Scanner scanner = new Scanner(System.in);
        
        // Mostrar mensaje de bienvenida
        System.out.println("Bienvenido al sistema de alquileres");
        
        // Menú principal
        while (true) {
            System.out.println("\n1. Iniciar sesión");
            System.out.println("2. Registrar usuario");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");
            
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Para consumir el salto de línea
            
            if (opcion == 1) {
                // Iniciar sesión
                System.out.print("Introduce tu DNI: ");
                String dni = scanner.nextLine();
                System.out.print("Introduce tu contraseña: ");
                String contrasena = scanner.nextLine();
                
                // Verificar login
                if (UsuarioLoginSignUp.login(dni, contrasena)) {
                    System.out.println("¡Inicio de sesión exitoso!");
                    mostrarOficinas(); // Mostrar oficinas disponibles
                    break; // Salir del ciclo de menú principal después de login exitoso
                } else {
                    System.out.println("DNI o contraseña incorrectos. Intenta de nuevo.");
                }
            } else if (opcion == 2) {
                // Registrar usuario
                System.out.print("Introduce tu DNI: ");
                String dni = scanner.nextLine();
                System.out.print("Introduce tu nombre: ");
                String nombre = scanner.nextLine();
                System.out.print("Introduce tu sexo (M/F): ");
                String sexo = scanner.nextLine();
                System.out.print("Introduce tu contraseña: ");
                String contrasena = scanner.nextLine();
                
                // Registrar usuario
                UsuarioLoginSignUp.register(dni, nombre, sexo, contrasena);
                System.out.println("Usuario registrado con éxito.");
            } else if (opcion == 3) {
                // Salir
                System.out.println("Gracias por usar nuestro sistema. ¡Hasta pronto!");
                break;
            } else {
                System.out.println("Opción no válida. Por favor, elige una opción válida.");
            }
        }
        
        scanner.close();
    }

    public static void mostrarOficinas() {
        // Mostrar las oficinas disponibles
        System.out.println("\nSeleccione una oficina:");
        System.out.println("1. Oficina Donostia");
        System.out.println("2. Oficina Irún");
        System.out.println("3. Salir");

        Scanner scanner = new Scanner(System.in);
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Para consumir el salto de línea

        switch (opcion) {
            case 1:
                System.out.println("Has seleccionado la Oficina Donostia");
                mostrarArticulos();
                break;
            case 2:
                System.out.println("Has seleccionado la Oficina Irún");
                mostrarArticulos();
                break;
            case 3:
                System.out.println("Volver al menú principal.");
                break;
            default:
                System.out.println("Opción no válida. Volviendo al menú principal.");
        }
    }

    public static void mostrarArticulos() {
        // Aquí mostrarías los artículos disponibles en la oficina seleccionada
        System.out.println("\nArtículos disponibles:");
        System.out.println("1. Automóvil Dacia (5 disponibles, precio: 20€/día)");
        System.out.println("2. Kayak (3 disponibles, precio: 15€/día)");
        System.out.println("3. Volver atrás");

        Scanner scanner = new Scanner(System.in);
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Para consumir el salto de línea

        switch (opcion) {
            case 1:
                System.out.println("Has seleccionado el Automóvil Dacia.");
                seleccionarFechas();
                break;
            case 2:
                System.out.println("Has seleccionado el Kayak.");
                seleccionarFechas();
                break;
            case 3:
                System.out.println("Volver atrás.");
                mostrarOficinas();
                break;
            default:
                System.out.println("Opción no válida. Volviendo al menú de artículos.");
        }
    }

    public static void seleccionarFechas() {
        // Simulación de la selección de fechas para la reserva
        System.out.println("\nSeleccione la fecha de inicio del alquiler:");
        System.out.println("Fecha inicio alquiler: dd/mm/aaaa");
        System.out.println("Días a alquilar:");
        System.out.println("1 día: 20€");
        System.out.println("2 días: 40€");
        System.out.println("3 días: 60€");
        System.out.println("4 días: 80€");

        Scanner scanner = new Scanner(System.in);
        int dias = scanner.nextInt();
        String fecha=scanner.nextLine();
        scanner.nextLine(); // Para consumir el salto de línea

        System.out.println("Has seleccionado " + dias + " días.");
        System.out.println("El precio total es: " + (dias * 20) + "€");
        System.out.println("la fecha de alquiler inicio es: " + fecha);
        
        System.out.println("¡Reserva realizada con éxito!");
	}

}
