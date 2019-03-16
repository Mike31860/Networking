package mundo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
	
	
	public final static int numeroPuerto = 6570;

	public static void main(String[] args) throws UnknownHostException, IOException {

		
		String usuario = "Por favor Digite su nombre";

		try {

			String ip = "localhost";

			Socket conexion = new Socket("localhost", 2000);
			BufferedReader lector = new BufferedReader(new InputStreamReader(conexion.getInputStream()));

			PrintWriter salir = new PrintWriter(conexion.getOutputStream(), true);
			salir.println(usuario);
			System.out.println(lector.readLine());
			lector.close();
			salir.close();
			conexion.close();

		

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
