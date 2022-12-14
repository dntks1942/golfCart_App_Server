package vehicle;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Vehicle extends Thread {
    
	  @Override
	  public void run() {
		  int AppPort = 1235;
			ServerSocket socket;
			try {
				socket = new ServerSocket(AppPort);
				Socket socketUser = null;
				System.out.println("Appsocket : " + AppPort + "으로 서버가 열렸습니다");
				boolean cartConnected = false;
				boolean appConnected = false;
				while(true) {
					socketUser = socket.accept();
					System.out.println("Vehicle가 접속함 : " + socketUser.getInetAddress());
					
					InputStream input = socketUser.getInputStream();
					BufferedReader br = new BufferedReader(new InputStreamReader(input));
					
					String identification = br.readLine();
					
					OutputStream out = socketUser.getOutputStream();
					PrintWriter pr = new PrintWriter(out,true);
					System.out.println(identification);
					if(identification.equals("GOLFCART")) {
						pr.println("HELLO GOLFCART");
						cartConnected = true;
					}
					else if(identification.equals("PHONE")) {
						pr.println("HELLO PHONE");
					}
					else {
						pr.println("DENY");
					}
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	} // clas

