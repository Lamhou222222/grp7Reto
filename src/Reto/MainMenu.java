package Reto;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MainMenu {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Bienvenido al sistema de alquileres");

        while (true) {
            System.out.println("\n1. Iniciar sesión");
            System.out.println("2. Registrar usuario");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");

            int opcion = sc.nextInt();
            sc.nextLine();

            if (opcion == 1) {
                System.out.print("Introduce tu DNI: ");
                String dni = sc.nextLine();
                System.out.print("Introduce tu contraseña: ");
                String contrasena = sc.nextLine();

                if (Usuario.login(dni, contrasena)) {
                    System.out.println("¡Inicio de sesión exitoso!");
                    Tienda.mostrarOficinas(dni);
                    break;
                } else {
                    System.out.println("DNI o contraseña incorrectos. Intenta de nuevo.");
                }
            } else if (opcion == 2) {
                System.out.print("Introduce tu DNI: ");
                String dni = sc.nextLine();
                System.out.print("Introduce tu nombre: ");
                String nombre = sc.nextLine();
                System.out.print("Introduce tu sexo (M/F): ");
                String sexo = sc.nextLine();
                System.out.print("Introduce tu contraseña: ");
                String contrasena = sc.nextLine();

                Usuario.register(dni, nombre, sexo, contrasena);
                System.out.println("Usuario registrado con éxito.");
            } else if (opcion == 3) {
                System.out.println("Gracias por usar nuestro sistema. ¡Hasta pronto!");
                break;
            } else {
                System.out.println("Opción no válida. Por favor, elige una opción válida.");
            }
        }

        sc.close();
    }

 

  
}

