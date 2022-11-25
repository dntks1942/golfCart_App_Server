package golfCartServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;



public class Server_vesion2 {
	public static void main(String[] args) throws IOException {
		Listener app = new Listener("app");	
		Listener cart = new Listener("cart");
		cart.start();
		app.start();

	}
} // class



class SocketFactory extends Thread {
	int ServerPort;
	ServerSocket serversocket;
	SocketFactory(String device) throws IOException{
		serversocket = new ServerSocket(ServerPort);
	}
	@Override
	public void run() {
		Socket 
	}
	
	String getMessage(Socket socketUser) throws IOException {
		InputStream input = socketUser.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(input));
		String identification = br.readLine();
		return identification;
	}
	void sendMessage(Socket socketUser, String message) throws IOException {
		OutputStream out = socketUser.getOutputStream();
		PrintWriter pr = new PrintWriter(out,true);
		pr.println(message);
		System.out.println(message);
	}
	void passMessage(Socket currentUserSocket, Socket targetUserSocket) throws IOException {
		String message = getMessage(currentUserSocket);
		sendMessage(targetUserSocket, message);
	}
} // class