package com.example.clientetcpandroidprueba;


import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity {
	
	 Socket socket;
	 Button btn_Enviar;
	 EditText editText1;
	private static final int SERVERPORT = 5000;
	private static final String SERVER_IP = "192.168.0.5";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);		
		Levantar_XML();
		botones();
		new Thread(new ClientThread()).start();
	}

	
	
	private void botones() {
		btn_Enviar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					EditText et = (EditText) findViewById(R.id.editText1);
					String str = et.getText().toString();
					PrintWriter out = new PrintWriter(new BufferedWriter(
							new OutputStreamWriter(socket.getOutputStream())),
							true);
					out.println(str);
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}



	private void Levantar_XML() {
		btn_Enviar=(Button) findViewById(R.id.btn_enviar);
		editText1=(EditText) findViewById(R.id.editText1);
		
	}



	
	class ClientThread implements Runnable {

		@Override
		public void run() {

			try {
				InetAddress serverAddr = InetAddress.getByName(SERVER_IP);

				socket = new Socket(serverAddr, SERVERPORT);

			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}

	}
}
