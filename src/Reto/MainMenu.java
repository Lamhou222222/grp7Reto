package Reto;

import java.sql.*;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MainMenu {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        

        //iniciar nuestra aplicacion con saludo a los clientes
        System.out.println("Bienvenido al sistema de alquileres");
        boolean salir = false;

        while (!salir) {  // Cambié la condición del while
            //el menu  principal
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Registrar usuario");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");
            
            //hay que elegir alguna opcion de las opciones abajo
            int opcion = sc.nextInt();
            sc.nextLine();
            
            //la primera opcion es introducir los datos del cliente 
            if (opcion == 1) {
                System.out.print("Introduce tu DNI: ");
                String dni = sc.nextLine();
                System.out.print("Introduce tu contraseña: ");
                String contrasena = sc.nextLine();
                //verificar si los datos del cliente existen en la base de datos o ya tiene la cuenta 
                if (Usuario.login(dni, contrasena)) {
                    System.out.println("¡Inicio de sesión exitoso!");
                    Tienda.mostrarOficinas(dni);
                } else {
                    System.out.println("DNI o contraseña incorrectos. Intenta de nuevo.");
                }
                
                //la segunda opcion es crear cuenta en nuestro programa 
            } else if (opcion == 2) {
                System.out.print("Introduce tu DNI: ");
                String dni = sc.nextLine();
                System.out.print("Introduce tu nombre: ");
                String nombre = sc.nextLine();
                System.out.print("Introduce tu sexo (H/M): ");
                String sexo = sc.nextLine();
                System.out.print("Introduce tu contraseña: ");
                String contrasena = sc.nextLine();

                Usuario.register(dni, nombre, sexo, contrasena);
                System.out.println("Usuario registrado con éxito.");
                
            //salir del bucle solo cuando se elija la opción 3
            } else if (opcion == 3) {
                System.out.println("Gracias por usar nuestro sistema. ¡Hasta pronto!");
                salir = true;  // Cambié el break por 'salir = true'
            } else {
                //avisar que el cliente tiene que elegir la opción entre 1,2 y 3
                System.out.println("Opción no válida. Por favor, elige una opción válida.");
            }
        }

        sc.close();
    }
}

