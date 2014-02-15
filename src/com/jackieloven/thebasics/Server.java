import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;

/** class implementing a server application to coordinate clients over a network */
public class Server implements ActionListener, Networked {
	/** networking port that server listens on */
	public static final int PORT = 44247;
	/** interval between timer ticks in milliseconds */
	public static final int UPDATE_RATE = 200;

	/** server socket used to set up connections with clients */
	private ServerSocket serverSocket;
	/** ArrayList of client connections */
	private ArrayList<NetComm> netComms;

	/** constructor for server class */
	public Server() throws IOException {
		// initialize server socket
		serverSocket = new ServerSocket(PORT);
		netComms = new ArrayList<NetComm>();
		// wait for clients to connect
		new Thread(new AcceptClientsThread()).start();
	}

	public static void main(String[] args) {
		Server server;
		try {
			server = new Server();
		}
		catch (Exception ex) {
			System.out.println("Error initializing server:");
			ex.printStackTrace();
			return;
		}
	}

	/** handle message received from clients */
	public void msgReceived(Object msgObj, NetComm sender) {
		int senderIndex;
		// find who sent the message
		for (senderIndex = 0; senderIndex < netComms.size(); senderIndex++) {
			if (sender == netComms.get(senderIndex)) break;
		}
		if (senderIndex == netComms.size()) {
			System.out.println("Warning: received message from unknown client: " + msgObj);
			return;
		}
		// handle message
		if (msgObj instanceof CloseConnectionMsg) {
			// close connection with client
			// (but don't call clients.get(i).close() because client might still receive the message and get confused)
			System.out.println("Client " + senderIndex + " has left");
			netComms.remove(senderIndex);
		}
		else if (msgObj instanceof String) {
			// broadcast message to all clients (for TestClient only, not used by factory managers)
			for (int i = 0; i < netComms.size(); i++) {
				netComms.get(i).write("Message from " + senderIndex + " to " + i + ": " + (String)msgObj);
			}
		}
		else {
			System.out.println("Warning: received unknown message from client " + senderIndex + ": " + msgObj);
		}
	}

	/** thread to accept new clients */
	private class AcceptClientsThread implements Runnable {
		/** wait for clients to connect */
		public void run() {
			while (true) { // loop exits when user presses ctrl+C
				try {
					Socket socket = serverSocket.accept();
					netComms.add(new NetComm(socket, Server.this));
					System.out.println("Client " + (netComms.size() - 1) + " has joined");
				}
				catch (Exception ex) {
					System.out.println("Error accepting new client connection");
					ex.printStackTrace();
				}
			}
		}
	}
}
