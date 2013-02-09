package proconex.game.receive;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import proconex.game.AnimationController;
import proconex.game.conversor.Conversor;

public class ReceiveConnectionController extends Thread {
	
	private DatagramSocket socket;
	private DatagramPacket paquete;
	private AnimationController aController;
	private byte[] mensaje = new byte[1024];
	private boolean stopping = false;
	private Conversor conv;
	private String str;
	
	public ReceiveConnectionController(AnimationController ac, DatagramSocket ds) {
		//Inicializamos el socket.
		socket = ds;
		paquete = new DatagramPacket(mensaje, mensaje.length);
		aController = ac;
		conv = new Conversor();
	}
	
	public void run() {
		while(!stopping) {
			try {
				System.out.println("7");
				socket.receive(paquete);
				System.out.println("8");
				str = conv.passByteToObject(paquete.getData()).toString();
				aController.RecieveString(str);
				System.out.println("9");
				sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
