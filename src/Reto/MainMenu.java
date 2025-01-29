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

                if (UsuarioLoginSignUp.login(dni, contrasena)) {
                    System.out.println("¡Inicio de sesión exitoso!");
                    mostrarOficinas(dni);
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

                UsuarioLoginSignUp.register(dni, nombre, sexo, contrasena);
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

    public static void mostrarOficinas(String dni) {
        System.out.println("\nSeleccione una oficina:");
        System.out.println("1. Oficina Donostia");
        System.out.println("2. Oficina Irún");
        System.out.println("3. Salir");

        Scanner scanner = new Scanner(System.in);
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                System.out.println("Has seleccionado la Oficina Donostia");
                mostrarArticulos(dni);
                break;
            case 2:
                System.out.println("Has seleccionado la Oficina Irún");
                mostrarArticulos(dni);
                break;
            case 3:
                System.out.println("Volver al menú principal.");
                break;
            default:
                System.out.println("Opción no válida. Volviendo al menú principal.");
        }
    }

    public static void mostrarArticulos(String dni) {
        Connection conn = DBConnection.getConexion();
        if (conn == null) {
            System.out.println("Error al conectar a la base de datos.");
            return;
        }

        System.out.println("\nArtículos disponibles:");

        try {
            String sql = "SELECT cod_art, nombre, cantidad_disponible, precio_dia FROM articulo";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            int index = 1;
            while (rs.next()) {
                String cod_art = rs.getString("cod_art");
                String nombre = rs.getString("nombre");
                int cantidad = rs.getInt("cantidad_disponible");
                int precio = rs.getInt("precio_dia");

                System.out.println(index + ". " + nombre + " (" + cantidad + " disponibles, " + precio + "€/día)");
                index++;
            }
            System.out.println(index + ". Volver atrás");

            Scanner scanner = new Scanner(System.in);
            int opcion = scanner.nextInt();

            if (opcion >= 1 && opcion < index) {
                rs.absolute(opcion);
                String cod_art = rs.getString("cod_art");
                String nombre = rs.getString("nombre");
                int precio = rs.getInt("precio_dia");
                int cantidad = rs.getInt("cantidad_disponible");

                if (cantidad > 0) {
                    seleccionarFechas(dni, cod_art, nombre, precio);
                } else {
                    System.out.println("❌ No hay stock disponible para " + nombre);
                }
            } else if (opcion == index) {
                System.out.println("Volver atrás.");
                mostrarOficinas(dni);
            } else {
                System.out.println("Opción no válida.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void seleccionarFechas(String dni, String cod_art, String articulo, int precioPorDia) {
        System.out.print("\nSeleccione la fecha de inicio del alquiler (yyyy-mm-dd): ");

        Scanner scanner = new Scanner(System.in);
        String fecha = scanner.nextLine();

        System.out.println("Días a alquilar:");
        System.out.println("1 día: " + precioPorDia + "€");
        System.out.println("2 días: " + (2 * precioPorDia) + "€");
        System.out.println("3 días: " + (3 * precioPorDia) + "€");
        System.out.println("4 días: " + (4 * precioPorDia) + "€");
        System.out.print("Selecciona la cantidad de días: ");

        int dias = scanner.nextInt();
        int totalPrecio = dias * precioPorDia;

        System.out.println("Has seleccionado " + dias + " días.");
        System.out.println("El precio total es: " + totalPrecio + "€");
        System.out.println("La fecha de inicio del alquiler es: " + fecha);
        System.out.println("¡Reserva realizada con éxito!");

        guardarReserva(dni, fecha, dias, cod_art, totalPrecio);
    }

    public static void guardarReserva(String dni, String fecha, int dias, String cod_art, int totalPrecio) {
        Connection conn = DBConnection.getConexion();
        if (conn == null) {
            System.out.println("Error al conectar a la base de datos.");
            return;
        }

        try {
            // Generar ID de reserva único basado en el tiempo
            String idReserva = "R" + System.currentTimeMillis();

            // Obtener la fecha actual como fecha de reservación
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fechaReservacion = sdf.format(new Date());

            // Insertar en la tabla reserva
            String sqlReserva = "INSERT INTO reserva (id_reserva, fecha_inicio, dias, precio_total, fecha_reservacion, Dni_usuario) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmtReserva = conn.prepareStatement(sqlReserva);
            stmtReserva.setString(1, idReserva);
            stmtReserva.setString(2, fecha);
            stmtReserva.setInt(3, dias);
            stmtReserva.setInt(4, totalPrecio);
            stmtReserva.setString(5, fechaReservacion);
            stmtReserva.setString(6, dni);
            stmtReserva.executeUpdate();

            // Insertar en la tabla reservaarticulo
            String sqlReservaArticulo = "INSERT INTO ReservaArticulo (id_reserva, cod_art) VALUES (?, ?)";
            PreparedStatement stmtReservaArticulo = conn.prepareStatement(sqlReservaArticulo);
            stmtReservaArticulo.setString(1, idReserva);
            stmtReservaArticulo.setString(2, cod_art);
            stmtReservaArticulo.executeUpdate();

            // Restar 1 a la cantidad disponible en la tabla articulo
            String sqlUpdate = "UPDATE articulo SET cantidad_disponible = cantidad_disponible - 1 WHERE cod_art = ?";
            PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate);
            stmtUpdate.setString(1, cod_art);
            stmtUpdate.executeUpdate();

            System.out.println("✅ Reserva guardada y stock actualizado.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

