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
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;



public class Cliente {
	/*
     * Constante que representa el puerto
     */
	public final static int PORT = 8888; 
	
	
	/*
     * Atributo que representa el puerto
     */
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
     *Atributo que representa el circulo perteneciente a cada cliente 
     */
	private Circulo circulo;
	
	
	/*
     * metodo constructor de la clase cliente
     */
	public Cliente(String host, int port, Circulo circulo) {
		this.host=host;
		this.port=port;
		this.circulo = circulo;
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
			System.out.println("Enviando Conexión...");
			socket = new Socket(host,port);
			System.out.println("Me conecté a servidor");
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void send(String mensaje) {
		
		try {
			
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			BufferedWriter bufw = new BufferedWriter(osw);
			PrintWriter out = new PrintWriter(bufw);
			out.println(mensaje);
			out.flush();
			
			InputStream is =socket.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(is);
			InputStreamReader isr = new InputStreamReader(bis);
			BufferedReader bufReader = new BufferedReader(isr);
			
			System.out.print(bufReader.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		System.out.println("iniciando cliente");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		Cliente cliente = new Cliente("localhost",PORT, new Circulo(20, 50,50,0,"DERACHA",0, false));
		
		cliente.connect();
		
		try {
			String linea = in.readLine();
			
			while(!linea.equals("") && linea != null) {
				
				cliente.send(linea);
				linea = in.readLine();
			}
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	
	

}
