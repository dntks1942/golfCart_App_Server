package golfCartServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import app.App;
import vehicle.Vehicle;

public class Server {
  public static void main(String[] args) throws IOException {
  

    App app = new App();
    Vehicle receiver = new Vehicle();

    app.start();
    receiver.start();
    // 여기까지하면 이제 3개(main+sender+receiver)의 스레드가 작동하는 멀티스레드 프로그램
  } // main
} // class