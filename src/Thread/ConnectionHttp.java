package Thread;

import Thread.Socket.HttpServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ConnectionHttp extends Thread {


    public ConnectionHttp() {
    }

    @Override
    public void run() {
        ConnectionConsole.println("Http Server start...", "INFO");
        while (true) {
            try (ServerSocket serverSocket = new ServerSocket(48080)) {
                Socket socket = serverSocket.accept();
                ConnectionConsole.println("Http Connect:" + socket.getInetAddress(), "INFO");
                ThreadPool.execute(new HttpServer(socket));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
