package mundo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;



public class Cliente {
	
	
	private int port;
	/*
     * Atributo que representa la direccion Ip
     */
	private String host;
	/*
     * Atributo que representa el Socket en el cual se van a conectar
    */
	private Socket socket;
	/*
     * Atributo que representa el mecanismo de lectura
     */
	private BufferedReader reader;
	/*
     *Atributo que representa el mecanismo de escritura por el cual se va a escribir el mensaje 
     */
	private PrintWriter writer;
	
	/*
     * metodo constructor de la clase cliente
     */
	public Cliente(String host, int port) {
		this.host=host;
		this.port=port;
	}
	
	
	
	
	/*
	 * 
     * metodo que s eencarga de conectarse al servidor
     *  @Param: String nombre, representa el nombre del jugador
     *   @Param: Int ip,
     */
	public void ConectarseAlJuego(String nombre, int direccionIp) {
		writer.println(nombre);
		
		try {
			String line = reader.readLine();
			System.out.println("Recibido en cliente: "+line);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
     * metodo que se encarga de hacer la conexión con el servidor
     * 
     */
	public void connect() {
		try {
			socket = new Socket(host,port);
			System.out.println("Me conecté a servidor");
			InputStream input = socket.getInputStream();
			OutputStream output = socket.getOutputStream();
			
			reader = new BufferedReader(new InputStreamReader(input));
			writer = new PrintWriter(new OutputStreamWriter(output),true);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		System.out.println("iniciando cliente");
		Scanner sc = new Scanner(System.in);
		Cliente cliente = new Cliente("127.0.0.1",8888);
		
		cliente.connect();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
				
					
					
					
				}
				//h
			}
		}).start();
		
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
