package greetingServer;

//File Name GreetingServer.java
import java.net.*;


import java.io.*;

public class GreetingServer {
private ServerSocket serverSocket;
Socket cartSocket, appSocket;
boolean isAppReady, isCartReady;

public GreetingServer(int port) throws IOException {
   serverSocket = new ServerSocket(port);

}
public Socket getAppSocket() {
	return appSocket;
}

public Socket getCartSocket() {
	return cartSocket;
}


public void getConnection() {
   while(!(isAppReady && isCartReady)) {
      try {
         System.out.println("Waiting for client on port " + 
            serverSocket.getLocalPort() + "...");
         Socket server = serverSocket.accept();

         System.out.println("Just connected to " + server.getRemoteSocketAddress());
         DataInputStream in = new DataInputStream(server.getInputStream());
         
         int input = in.readInt();
         System.out.println(server.getInetAddress() + " : " + input);
         if(input == 1) {
        	 isAppReady = true;
        	 /*
        	 appIP = server.getInetAddress();
        	 appPort = server.getPort();
        	 */
        	 appSocket = server;
        	 if(isCartReady) {
                 DataOutputStream outToCart = new DataOutputStream(cartSocket.getOutputStream());
                 outToCart.writeInt(input);
        	 }
             DataOutputStream out = new DataOutputStream(server.getOutputStream());
             if(isCartReady) {
            	 //out.writeUTF("Cart is also ready!");
            	 out.writeInt(102);
             }
             else {
            	 //out.writeUTF("Cart is not ready yet!");
            	 out.writeInt(402);
             }
         }
         
         else if(input == 2) {
        	 isCartReady = true;
        	 /*
        	 cartIP = server.getInetAddress();
        	 cartPort = server.getPort();
        	 */
        	 cartSocket = server;
        	 if(isAppReady) {
                 DataOutputStream outToApp = new DataOutputStream(appSocket.getOutputStream());
                 //outToApp.writeInt("Cart is ready!");
                 outToApp.writeInt(102);
        	 }
             DataOutputStream out = new DataOutputStream(server.getOutputStream());
             if(isAppReady) {
            	 //out.writeUTF("App is also ready!");
            	 out.writeInt(101);
             }
             else {
            	 //out.writeUTF("App is not ready yet!");
            	 out.writeInt(401);
             }
         }
         
      } catch (SocketTimeoutException s) {
         System.out.println("Socket timed out!");
         break;
      } catch (IOException e) {
         e.printStackTrace();
         break;
      }
   }
}

public static void main(String [] args) throws IOException {
   int port = 1234;
   GreetingServer t;
   t = new GreetingServer(port);
   t.getConnection();
   System.out.println("Ready to communicate");
   Socket app = t.getAppSocket();
   Socket cart = t.getCartSocket();
   interTransmit AppToCart = new interTransmit(app, cart);
   interTransmit CartToApp = new interTransmit(cart, app);
   AppToCart.start();
   System.out.println("App to Cart start");
   CartToApp.start();
   System.out.println("Cart to App start");
}
}

class interTransmit extends Thread{
	Socket source, target;
	public interTransmit(Socket source, Socket target) throws IOException {
		this.source = source;
		this.target = target;
	}
	@Override
	public void run(){
		while(true) {
			DataInputStream in;
			try {
				in = new DataInputStream(source.getInputStream());
				int input = in.readInt();
		        System.out.println(source.getInetAddress() + " : " + input);
				DataOutputStream out;
				out = new DataOutputStream(target.getOutputStream());
				out.writeInt(input);
				System.out.println("Send!!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}

