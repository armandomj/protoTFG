package proconex.game;

import proconex.game.receive.ReceiveConnectionManager;
import proconex.game.send.SendConnectionController;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class AnimationController extends Thread implements ReceiveConnectionManager, OnClickListener {
	
	private boolean stopping = false;
	private SendConnectionController sCController;
	private TextView visorText;
	private EditText editText;	
	private MainActivity ma;
	private String str;
	
	public AnimationController (MainActivity m, EditText et, SendConnectionController cc) {
		//Se inicializan los parametros.
		sCController = cc;
		editText = et;
		ma = m;
		visorText = (TextView) ma.findViewById(R.id.textView1);
	}
	
	@Override
	public void run() {
		while (!stopping) {
			try {
				sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}	
	
	//Evento que salta cuando recibimos un mensaje.
	public void RecieveString(String dato) {
		System.out.println("Llamada funcion hecha");
		str = dato;
		ma.runOnUiThread(new Runnable() {
			public void run() {
				visorText.append("\n");
				visorText.append(str);
			}
		});
	}

	//Evento para capturar el evento de eviar el texto.
	public void onClick(View v) {
		System.out.println("1");
		sCController.sendString(editText.getText().toString());
	}	
}
