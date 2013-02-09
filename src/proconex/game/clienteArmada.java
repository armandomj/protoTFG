package proconex.game;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import proconex.game.conversor.Conversor;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class clienteArmada implements OnClickListener{
	private TextView visorText;
	private EditText editText;
	public clienteArmada(TextView tv, EditText et) {
		visorText = tv;
		editText = et;
	}

	public void onClick(View v) {
		try {
			
			Conversor conv = new Conversor();
			DatagramSocket socket = new DatagramSocket();
			byte[] mensaje = new byte[1024];
			String frase = editText.getText().toString();
			mensaje = conv.passObjectToByte(frase);
			DatagramPacket paquete = new DatagramPacket(mensaje, mensaje.length, InetAddress.getByName("10.230.171.191"), 5557);
			socket.send(paquete);
			
			visorText.setText("antes de recibir");
			paquete = new DatagramPacket(mensaje, mensaje.length);
			socket.receive(paquete);
			visorText.setText("ha recivido");
			String resultado = conv.passByteToObject(paquete.getData()).toString();
			visorText.setText(resultado);			
			socket.close();		
			
			/*
			DatagramSocket soquito = new DatagramSocket();
			visorText.setText("antes de recibir");
			paquete = new DatagramPacket(mensaje, mensaje.length);
			soquito.receive(paquete);
			visorText.setText("ha recivido");
			String resultado = conv.passByteToObject(paquete.getData()).toString();
			visorText.setText(resultado);
			soquito.close();
			*/
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}

}
