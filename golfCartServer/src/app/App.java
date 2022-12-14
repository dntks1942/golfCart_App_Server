package app;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class App extends Thread {
	@Override
	public void run() {
		int AppPort = 1234;
		ServerSocket socket;
		try {
			socket = new ServerSocket(AppPort);
			Socket socketUser = null;
			System.out.println("Appsocket : " + AppPort + "으로 서버가 열렸습니다");
			boolean cartConnected = false;
			boolean appConnected = false;
			
			while(true) {
				socketUser = socket.accept();
				System.out.println("App가 접속함 : " + socketUser.getInetAddress());
				getMessage(socketUser);
				sendMessage(socketUser);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void getMessage(Socket socketUser) throws IOException {
		InputStream input = socketUser.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(input));
		String identification = br.readLine();
		System.out.println(identification);
	}
	void sendMessage(Socket socketUser) throws IOException {
		OutputStream out = socketUser.getOutputStream();
		PrintWriter pr = new PrintWriter(out,true);
		Scanner s1 = new Scanner(System.in);
		pr.println(s1.next());
	}
} // class


