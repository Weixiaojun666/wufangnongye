package Thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ConnectionConsole extends Thread {

    public ConnectionConsole() {

    }


    public static void println(String msg, String type) {
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");// a为am/pm的标记
        Date date = new Date();// 获取当前时间
        System.out.println(" [ " + sdf.format(date) + " / " + type + " / " + "]" + " : " + msg);
    }

    @Override
    public void run() {

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String buffer = scanner.next();
            //for (int i = 0; i < TCPServer.threadList.size(); i++) {
            //  ServerThread thread = TCPServer.threadList.get(i);
            // thread.sendMessage(buffer);
            // }

        }

    }
}