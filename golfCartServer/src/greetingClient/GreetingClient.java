package greetingClient;

//File Name GreetingClient.java
import java.net.*;
import java.util.Scanner;
import java.io.*;

public class GreetingClient {

	public static void main(String [] args) throws UnknownHostException, IOException {
	   System.out.println("Main start");
	   int port = 1234;
	   
	   Socket socket = new Socket("192.168.0.110", 1234);
	   System.out.println("socket created");
	   Sender sender = new Sender(socket);
	   System.out.println("Sender created");
	   Receiver receiver = new Receiver(socket);
	   System.out.println("Receiver created");
	   sender.start();
	   System.out.println("Sender run");
	   receiver.start();
	   System.out.println("Receiver run");
	}
}


class Receiver extends Thread{
	Socket socket;
	Receiver(Socket newsocket){
		socket = newsocket;
	}

	@Override
	public void run() {
		Scanner s = new Scanner(System.in);
		try {
			while(true) {
				int input = s.nextInt();
				DataOutputStream out;
				
				out = new DataOutputStream(socket.getOutputStream());
				out.writeInt(input);
				System.out.println("Send!!!");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
}
         

class Sender extends Thread{
	Socket socket;
	Sender(Socket newsocket){
		socket = newsocket;
	}
	@Override
	public void run() {
		try {
			while(true) {
		        DataInputStream in;
				in = new DataInputStream(socket.getInputStream());
		        int input = in.readInt();
		        System.out.println(socket.getInetAddress() + " : " + input);
			} 
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}



