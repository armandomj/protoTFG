package proconex.game.send;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import proconex.game.conversor.Conversor;


public class SendConnectionController implements SendConnectionManager {
    
	private int puertoServidor = 5557;
	private String direcServidor = "192.168.1.33";
	private DatagramSocket socket;
	private DatagramPacket paquete;
	private byte[] mensaje = new byte[1024];
	
	public  SendConnectionController(DatagramSocket ds){
		socket = ds;
		paquete = new DatagramPacket(mensaje, mensaje.length);
	}

	public void sendString(String dato) {
		try {
			System.out.println("2");
			Conversor conv = new Conversor();
			System.out.println("3");
			mensaje = conv.passObjectToByte(dato);
			//se crea el datagrama que contendrá al mensaje
			//paquete.setData(mensaje);
			//paquete.setAddress(InetAddress.getByName(direcServidor));
			//paquete.setPort(puertoServidor);
			System.out.println("4");
			paquete = new DatagramPacket(mensaje, mensaje.length, InetAddress.getByName(direcServidor), puertoServidor);
			System.out.println("5");
			//Envia el paquete.
			socket.send(paquete);
			System.out.println("6");
		} catch (IOException e) {
			e.printStackTrace();			
		}
	}
}
