package com.jackieloven.thebasics;


import java.io.*;
import java.net.*;
import java.util.*;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;



/** class implementing a server application to coordinate clients over a network */
public class Server extends Activity implements Networked {
	
	/** networking port that server listens on */
	public static final int PORT = 44247;

	/** server socket used to set up connections with clients */
	private ServerSocket serverSocket;
	/** ArrayList of client connections */
	private ArrayList<NetComm> netComms;
	private boolean running;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.server);
		// initialize server socket
		try {
			serverSocket = new ServerSocket(PORT);
		}
		catch (IOException ex) {
			finish();
		}
		netComms = new ArrayList<NetComm>();
		// wait for clients to connect
		running = true;
		new Thread(new AcceptClientsThread()).start();
	}
	
	protected void onDestroy() {
		super.onDestroy();
		running = false;
	}

	/** handle message received from clients */
	public void msgReceived(Object msgObj, NetComm sender) {
		int senderIndex;
		// find who sent the message
		for (senderIndex = 0; senderIndex < netComms.size(); senderIndex++) {
			if (sender == netComms.get(senderIndex)) break;
		}
		if (senderIndex == netComms.size()) {
			showDialog("Warning: received message from unknown patient: " + msgObj);
			return;
		}
		senderIndex++;
		// handle message
		if (msgObj instanceof CloseConnectionMsg) {
			// close connection with client
			// (but don't call clients.get(i).close() because client might still receive the message and get confused)
			showDialog("Client " + senderIndex + " has left");
			netComms.remove(senderIndex);
		}
		else if (msgObj instanceof HurtMsg) {
			// notify nurse about patient
			HurtMsg msg = (HurtMsg)msgObj;
			showDialog("Patient " + senderIndex + "'s " + msg.bodyPart + " hurts. Please send a nurse over.");
		}
		else if (msgObj instanceof RestroomMsg) {
			showDialog("Patient " + senderIndex + " wants to use the restroom.");
		}
		else if (msgObj instanceof QuestionMsg) {
			showDialog("Patient " + senderIndex + " has a question.");
		}
		else {
			showDialog("Warning: received unknown message from patient " + senderIndex + ": " + msgObj);
		}
	}
	
	private void showDialog(final String text) {
		runOnUiThread(new Runnable() {
			public void run() {
				Dialog dialog = new Dialog(Server.this);
				dialog.setTitle(text);
				dialog.show();
			}
		});
	}

	/** thread to accept new clients */
	private class AcceptClientsThread implements Runnable {
		/** wait for clients to connect */
		public void run() {
			while (running) {
				try {
					Socket socket = serverSocket.accept();
					netComms.add(new NetComm(socket, Server.this));
					showDialog("Patient " + netComms.size() + " is online.");
				}
				catch (Exception ex) {
					ex.printStackTrace();
					break;
				}
			}
			try {
				serverSocket.close();
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
			finish();
		}
	}
}
