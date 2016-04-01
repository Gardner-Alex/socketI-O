package sockets;

import java.net.*;
import java.io.*;

public class ClientSide {
	public static void main(String[] args) {
		String server = "localhost";
		int port = Integer.parseInt("8006");
		
		try {
			System.out.println("Connecting to host: " + server + " on port " + port);
			Socket client = new Socket(server, port);
			System.out.println("Connected to " + client.getRemoteSocketAddress());
			//sets output streams
			OutputStream output = client.getOutputStream();
			DataOutputStream out = new DataOutputStream(output);

			out.writeUTF("Message from " + client.getLocalSocketAddress());
			InputStream input = client.getInputStream();
			DataInputStream in = new DataInputStream(input);
			System.out.println("Server response " + in.readUTF());
			client.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
