package sockets;

import java.net.*;
import java.io.*;

public class ServerSide extends Thread {
	private ServerSocket servercon;

	public ServerSide(int port) throws IOException {
		servercon = new ServerSocket(port);
		servercon.setSoTimeout(10000);
	}

	public void run() {
		while (true) {
			try {
				System.out.println("Connecting on port " + servercon.getLocalPort());
				Socket server = servercon.accept();
				
				System.out.println("Connected to " + server.getRemoteSocketAddress());
				
				DataInputStream in = new DataInputStream(
						server.getInputStream());
				System.out.println(in.readUTF());
				
				DataOutputStream out = new DataOutputStream(
						server.getOutputStream());
				out.writeUTF("You have connected to " + server.getLocalSocketAddress() + "\nGreat job!");
				server.close();
				
			} catch (SocketTimeoutException s) {
				System.out.println("Connection lost");
				break;
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}
	}

	public static void main(String[] args) {
		int port = Integer.parseInt("8006");
		try {
			Thread t = new ServerSide(port);
			t.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}