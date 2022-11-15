package Thread.Socket;

import Thread.ConnectionConsole;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import static Thread.WebServer.WebServer.weburl;


public class HttpServer extends Thread {

    static OutputStream os;
    static PrintWriter pw;
    private static Socket socket;
    private final String master_id = "-1";

    public HttpServer(Socket socket) throws IOException {
        HttpServer.socket = socket;
        pw = new PrintWriter(socket.getOutputStream());
    }


    public static void Repletion(String data) {

        String newString = new String(data.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
        pw.println("HTTP/1.1 200 OK");
        pw.println("Content-Type:application/javascript");
        pw.println("charset=utf-8");
        pw.println("Content-Length:" + (newString.length() + 1));
        pw.println("Connection: close");
        pw.println("");
        pw.println(data);
        pw.println("");
        pw.println("");
        pw.flush();
    }

    @Override
    public void run() {

        try {

            InputStream inSocket = socket.getInputStream();
            int size = inSocket.available();
            byte[] buffer = new byte[size];
            inSocket.read(buffer);
            String request = new String(buffer);

            if (request.length() > 0) {
                String firstLineOfRequest = request.substring(0, request.indexOf("\r\n"));
                String[] heads = firstLineOfRequest.split(" ");
                String uri = heads[1];
                ConnectionConsole.println("Http url:" + uri, "INFO");
                weburl(uri, socket.getInetAddress());
            }
            //inSocket.close();
            // os.close();
            //socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
