package Thread.WebServer;

import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

import static Thread.ConnectionMysql.*;
import static Thread.Socket.HttpServer.Repletion;

public class WebServer {

    static Map<String, String> parameter = new HashMap<>();

    public static void weburl(String uri, InetAddress inetAddress) throws IOException {

        while (true) {
            String url = uri;

            if (uri.contains("?")) {
                url = uri.substring(0, uri.indexOf("?"));

                String tmp = uri.substring(uri.indexOf("?") + 1);
                while (tmp.contains("&")) {
                    String wkey = tmp.substring(0, tmp.indexOf("="));
                    String wvaule = tmp.substring(tmp.indexOf("=") + 1, tmp.indexOf("&"));
                    parameter.put(wkey, wvaule);
                    tmp = tmp.substring(tmp.indexOf("&") + 1);
                }
                if (tmp.contains("=")) {
                    String wkey = tmp.substring(0, tmp.indexOf("="));
                    String wvaule = tmp.substring(tmp.indexOf("=") + 1);
                    parameter.put(wkey, wvaule);
                }
            }

            //权限验证
            // if (!(parameter.containsKey("access_toke") && verificationkey(parameter.get("access_token"), inetAddress.toString()))) {
            //    Repletion("{\"code\":403,\"msg\":\"登录验证失败\",\"data\":\"\"}");
            //    break;
            // }


            if (url.equals("/api/main1")) {
                Repletion(Query_main1());
                break;
            }
            if (url.equals("/api/main2")) {
                Repletion(Query_main2());
                break;
            }
            if (url.equals("/api/device/list")) {
                Repletion(Query_data("select mi.master_id,master_sn,master_key,master_outcome,master_change,master_ip,master_time FROM master_information mi LEFT JOIN (SELECT master_id,master_change,master_ip,master_time from master_log  group BY master_id) AS ua ON mi.master_id =ua.master_id;"));
                break;
            }
            if (url.equals("/api/device/info")) {
                Repletion(Query_data("select master_id,equipment_id,sensor_log.sensor_id,numerical,sensor_time,sensor_type,sensor_unit from sensor_log left join sensor_information si on sensor_log.sensor_id = si.sensor_id;"));
                break;
            }
            if (url.equals("/api/device/debug")) {
                Repletion(Query_data("select * from issue_order"));
                break;
            }
            if (url.equals("/api/digital/analyze")) {
                Repletion(Query_analyze());
                break;
            }
            if (url.equals("/api/digital/device")) {
                Repletion(Query_data(""));
                break;
            }
            if (url.equals("/api/user/list")) {
                Repletion(Query_data(""));
                break;
            }
            if (url.equals("/api/administrators/list")) {
                Repletion(Query_data(""));
                break;
            }
            if (url.equals("/api/personal/info")) {
                Repletion(Query_data(""));
                break;
            }

//            if (!(parameter.containsKey("master") && verificationmaster(parameter.get("master"), inetAddress.toString()))) {
//                Repletion("{\"code\":403,\"msg\":\"403\",\"data\":\"\"}");
//                break;
//            }

            //下发
            if (url.equals("/api/report")) {
                //ST0201
                String message = "AT";
                System.out.println("1");
//                if (parameter.containsKey("command")) {
//                    System.out.println("2");
//                    message = parameter.get("command");
//                }
//                if (parameter.containsKey("switch")) {
//                    System.out.println("3");
////                    if (switchstatus(parameter.get("switch"))) {
////                        message = "CL" + parameter.get("switch");
////                    } else {
////                        message = "ST" + parameter.get("switch");
////                    }
//                    System.out.println("123");
//                }

                // TcpServer.sendMessage(message);
//                for (int j = 0; j < ConnectionTcp.masterList.size(); j++) {
//                    String master_id = ConnectionTcp.masterList.get(j).toString();
//                    if (master_id.equals((parameter.get("master")))) {
//                        TcpServer thread = ConnectionTcp.threadList.get(j);
//                        TcpServer.sendMessage("+" + message + "/");
//                    }
//                }
                Repletion("{\"code\":0,\"msg\":\"ok\",\"data\":\"\"}");
                break;
            }
            Repletion("{\"code\":404,\"msg\":\"404\",\"data\":\"\"}");
            break;
        }
    }
}
