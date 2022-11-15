import Thread.*;

public class Main {

    static final ThreadPool ThreadPool = new ThreadPool(64, 100);
    public int num = 0;

    public static void main(String[] args) {
        System.out.println("Loading...");
        //ServerSocket serverSocket = new ServerSocket(47777);

        //启动一个控制台进程
        ThreadPool.execute(new ConnectionConsole());
        //启动一个Msql进程
        ThreadPool.execute(new ConnectionMysql());
        //启动一个Tcp接收进程
        ThreadPool.execute(new ConnectionTcp());
        //启动一个Http接收进程
        ThreadPool.execute(new ConnectionHttp());
        //循环接收消息
        // while (true) {
        // Socket socket = serverSocket.accept();
        //System.out.println("Connect:" + socket.getInetAddress());
        //serverThreadPool.execute(new TcpServer(socket));
        // }
    }
}