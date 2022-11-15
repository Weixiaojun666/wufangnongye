package Thread;


import Thread.Socket.TcpServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ConnectionTcp extends Thread {
    public static final List<TcpServer> threadList = new ArrayList<>();
    public static final List<Integer> masterList = new ArrayList<>();

    public ConnectionTcp() {
    }

    @Override
    public void run() {
        ConnectionConsole.println("Tcp Server start...", "INFO");
        try (ServerSocket serverSocket = new ServerSocket(47777)) {
            while (true) {
                Socket socket = serverSocket.accept();
                ConnectionConsole.println("Tcp Connect:" + socket.getInetAddress(), "INFO");
                ThreadPool.execute(new TcpServer(socket));
            }

        } catch (IOException e) {
            ConnectionConsole.println(String.valueOf(e), "ERROR");
            throw new RuntimeException(e);
        }

    }
}