package proconex.game;

import java.net.DatagramSocket;
import java.net.SocketException;

import proconex.game.receive.ReceiveConnectionController;
import proconex.game.send.SendConnectionController;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	ThreadPolicy tp = ThreadPolicy.LAX;
    	StrictMode.setThreadPolicy(tp);
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //Declaración de los elementos de la pantalla.
        Button boton = (Button) findViewById(R.id.button1);
//        TextView visorText = (TextView) findViewById(R.id.textView1);
        EditText editText = (EditText) findViewById(R.id.editText1);
        
        DatagramSocket socket;
        try {
			socket = new DatagramSocket();
			
	        //Creación del SendConnectionController.
	        SendConnectionController sCController = new SendConnectionController(socket);	        
	        //Creación del animation controller (hilo).
	        AnimationController aController = new AnimationController(MainActivity.this, editText, sCController);	        
	        //Creación del ReceiveConnectionController (hilo).
	        ReceiveConnectionController rCController = new ReceiveConnectionController(aController, socket);
	        boton.setOnClickListener(aController);
	        aController.start();
	        rCController.start();
		} catch (SocketException e) {
			e.printStackTrace();
		}
        
        //clienteArmada cliente = new clienteArmada(visorText, editText);
        //boton.setOnClickListener(cliente);       
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
