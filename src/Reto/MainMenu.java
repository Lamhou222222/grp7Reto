package Reto;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MainMenu {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        

        //iniciar nuestra applicacion con saludo a los clientes
        System.out.println("Bienvenido al sistema de alquileres");

        while (true) {
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
                //verificar si los datos del cliente existen en el base de datos o ya tiene la cluenta 
                if (Usuario.login(dni, contrasena)) {
                    System.out.println("¡Inicio de sesión exitoso!");
                    Tienda.mostrarOficinas(dni);
                    break;
                } else {
                    System.out.println("DNI o contraseña incorrectos. Intenta de nuevo.");
                }
                
                //la segunda opcion es crear cuenta en nuestra programa 
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
                
                //salir del boucle a gracia de intruccion break;
            } else if (opcion == 3) {
                System.out.println("Gracias por usar nuestro sistema. ¡Hasta pronto!");
                break;
                //avisar que el cliente tiene que elegir la opcion entre 1,2 y 3
            } else {
                System.out.println("Opción no válida. Por favor, elige una opción válida.");
            }
        }

        sc.close();
    }

 

  
}

