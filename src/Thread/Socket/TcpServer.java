package Thread.Socket;

import Thread.ConnectionConsole;
import Thread.ConnectionMysql;
import Thread.ConnectionTcp;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TcpServer extends Thread {
    private static Socket socket;
    private String master_id = "-1";


    public TcpServer(Socket socket) {
        TcpServer.socket = socket;
    }

    public static void sendMessage(String message) {
        try {
            OutputStream os = socket.getOutputStream();
            os.write(message.getBytes());
        } catch (Exception var3) {
            System.out.println("ERROR：" + socket.getRemoteSocketAddress());
        }

    }

    public void run() {
        ConnectionTcp.threadList.add(this);
        try {
            for (int i = 1; i <= 3; i++) {
                socket.sendUrgentData(0);
                InputStream is = socket.getInputStream();
                byte[] bytes = new byte[64];
                int len = is.read(bytes);
                String msg = new String(bytes, 0, len);

                if (msg.length() >= 39 && msg.startsWith("wbxiot")) {
                    master_id = ConnectionMysql.verify(msg.substring(7, 22), msg.substring(23, 39));
                }
                if (master_id.equals("-1")) {
                    ConnectionConsole.println(socket.getRemoteSocketAddress() + ": verification failed! number:" + i, "INFO");
                } else {
                    //(String master_id,String master_change,String master_time, String master_ip)
                    ConnectionMysql.Record_master(master_id, "1", String.valueOf(socket.getInetAddress()));
                    ConnectionConsole.println(socket.getRemoteSocketAddress() + ": verification success! id:" + master_id, "INFO");
                    sendMessage("\n wbxiot:verification success!");
                    break;
                }

            }
            //若验证失败
            if (master_id.equals("-1")) {
                sendMessage("\n verification failed!");
                ConnectionConsole.println(socket.getRemoteSocketAddress() + ": failed!", "INFO");
                socket.shutdownInput();
                socket.shutdownOutput();
                socket.close();
                // this.socket.sendUrgentData(0);
            }
            //验证成功 进入循环接收阶段
            while (true) {
                socket.sendUrgentData(0);
                InputStream is = socket.getInputStream();
                byte[] bytes = new byte[128];
                int len = is.read(bytes);
                String msg = new String(bytes, 0, len);
                System.out.println("[" + master_id + "] : " + msg);
                msg = msg.substring(0, msg.length() - 1);
                msg = msg + "*";
                String st0;
                st0 = msg.substring(0, msg.indexOf("*"));
                msg = msg.substring(msg.indexOf("*") + 1);

                if (st0.charAt(0) == '-') {
                    //处理上报
                    String equipment_id = st0.substring(1, 3);
                    while (msg.contains("*")) {
                        st0 = msg.substring(0, msg.indexOf("*"));

                        String sensor_id = st0.substring(0, 2);
                        String numerical = st0.substring(2);
                        if (numerical.equals("")) numerical = String.valueOf(0);
                        //数据插入Mysql
                        ConnectionMysql.Record_sensor(master_id, equipment_id, sensor_id, numerical);

                        msg = msg.substring(msg.indexOf("*") + 1);
                    }
                }
                if (st0.charAt(0) == '=') {
                    //处理回复
                }
            }
        } catch (Exception e) {
            ConnectionMysql.Record_master(master_id, "0", String.valueOf(socket.getInetAddress()));
            ConnectionConsole.println("Disconnect:" + socket.getInetAddress(), "INFO");
            // System.out.println("Disconnect:" + this.socket.getRemoteSocketAddress());
            ConnectionTcp.threadList.remove(this);
            // throw new RuntimeException(e);
        }
    }
}