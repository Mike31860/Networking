package model;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Servidor {
	
	/*
     * Constante que representa el puerto
     */
	public final static int PORT = 8888; 
	
	
	/*
	 * Atributo que representa el puerto a utilizar
	 */
	private int puerto;
	
	/*
	 * Atributo de la clase Receptor necesario para recibir la información
	 */
	private ArrayList<Receptor> receptor;

	/*
	 * Método constructor de la clase
	 */
	private Servidor() {
		
		puerto = PORT;
		receptor = new ArrayList<Receptor>();
	}
	
	/*
	 * Método que envía la respuesta al usuario
	 */
	public void send(String mensaje, Socket socket){
		try {
			System.out.println("Enviando mensaje...");
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			BufferedWriter bw = new BufferedWriter(osw);
			PrintWriter pw = new PrintWriter(osw);
			pw.println(mensaje);
			pw.flush();
			System.out.println("Mensaje devuelto");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/*
	 * Hilo que procesa la información enviada por el usuario
	 */
	public class Receptor extends Thread{
		
		/*
		 * Atributo de tipo Socket
		 */
		Socket socket;
		/*
		 * Atributo que representa el numero de sesion
		 */
		int id;
		/*
		 * Atributo que representa el estado de la sesion
		 */
		boolean activo;
		
		
		/*
		 * Constructor del hilo
		 */
		public Receptor(Socket socket, int id) {
			this.socket = socket;
			this.id = id;
			
		}
		
		/*
		 * Método que retorna el numero de sesion
		 */
		public long getId() {
			return id;
		}
		
		/*
		 * Método que retorna el estado de la sesion
		 */
		public boolean getActivo() {
			return activo;
		}
		
		@Override
		/*
		 * Método que inicia el hilo
		 * (non-Javadoc)
		 * @see java.lang.Thread#run()
		 */
		public void run(){
			
			try {
				InputStream is =socket.getInputStream();
				BufferedInputStream bis = new BufferedInputStream(is);
				InputStreamReader isr = new InputStreamReader(bis);
				BufferedReader bufReader = new BufferedReader(isr);
				
				while (true) {
					
					String linea = bufReader.readLine();
					if(linea !=null && !linea.equals("")) {
						
						String procesado = "holaaaa";

						send("Hilo " + id+": " + procesado, socket);
					
					}
					
					
				}
			} catch (Exception e) {
				System.out.println(e.getLocalizedMessage());
				
				// TODO: handle exception
			}
		}
		
	}
	
	
	public static void main(String[] args) {
		
	
	try {
		System.out.println("Esperando conexion.....");
		
		Servidor servidor = new Servidor();
		ServerSocket server = new ServerSocket(PORT);
	
	for(int i = 0; i < 5; i++) {
		
		Socket socket;
		socket = server.accept();
		System.out.println("Nueva conexion entrante: " + i);
		servidor.receptor.add(servidor.new Receptor(socket, i));
		System.out.println("Conexion establecida");
		if(i >= 1) {
			if(!servidor.receptor.get(0).activo) {
				servidor.receptor.get(0).activo = true;
				servidor.receptor.get(0).start();
				System.out.println("Hilo 0 corriendo");
			}
			
			if(!servidor.receptor.get(i).activo) {
				servidor.receptor.get(i).activo = true;
				servidor.receptor.get(i).start();
				System.out.println("Hilo " + i + "corriendo");
			}
			
			
		}
		
		
	}
		
		
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
		
		
		
	}
	

}
