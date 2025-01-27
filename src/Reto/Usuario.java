package Reto;

public class Usuario {
	
	
	


	private static String Dni_usuario;
	private static String nombre ;
	private static char Sexo;
	private static String contrasenia;
	private static Reserva reserva;
	private static String rol;
	
	public Usuario( String Dni ,String nom,char s,String con,Reserva res,String r) {
		Usuario.Dni_usuario=Dni;
		Usuario.nombre=nom;
		Usuario.Sexo=s;
		Usuario.contrasenia=con;
		Usuario.reserva=res;
		Usuario.rol=r;
	}


	private static final String  ROL[]= {"Cliente","empleado"};
	
	
	public static String getDni_usuario() {
		return Dni_usuario;
	}


	public static void setDni_usuario(String dni_usuario) {
		Dni_usuario = dni_usuario;
	}


	public static String getNombre() {
		return nombre;
	}


	public static void setNombre(String nombre) {
		Usuario.nombre = nombre;
	}


	public static char getSexo() {
		return Sexo;
	}


	public static void setSexo(char sexo) {
		Sexo = sexo;
	}


	public static String getContrasenia() {
		return contrasenia;
	}


	public static void setContrasenia(String contrasenia) {
		Usuario.contrasenia = contrasenia;
	}


	public static Reserva getReserva() {
		return reserva;
	}


	public static void setReserva(Reserva reserva) {
		Usuario.reserva = reserva;
	}


	public static String getRol() {
		return rol;
	}


	public static void setRol(String rol) {
		Usuario.rol = rol;
	}
	
	
	
	public static void sexo(char sex) {
		if(sex!='M' && sex!='m') {
			Sexo='H';
		}
		else {
			Sexo=sex;
		}
	}
	
	
	
	public static boolean rolUsuario(String rol) {
		for(int k=0;k<2;k++) {
			if(ROL[k].equalsIgnoreCase(rol)) {
				return true;
			}
		}
		return false;
		
	}




	@Override
    public String toString() {
        return "Usuario{" +
                "Dni_usuario='" + Dni_usuario + '\'' +
                ", nombre='" + nombre + '\'' +
                ", Sexo=" + Sexo +
                ", contrasenia='" + contrasenia + '\'' +
                ", reserva='" + reserva + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }



	
	
	
	
}
