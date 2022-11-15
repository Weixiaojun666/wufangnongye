package Thread;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.*;

public class ConnectionMysql extends Thread {
    private static Connection connection = null;

    public ConnectionMysql() {

    }

    public static String login(String username, String password) {


        return "403";
    }

    public static boolean verificationkey(String key, String ip) {


        return true;
    }

    public static boolean verificationmaster(String master, String key) {


        return true;
    }

    public static boolean switchstatus(String switch_id) {
        try {

            String sql = String.format("select switch_type from switch_information where switch_id=%s;", switch_id);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            PreparedStatement pst = connection.prepareStatement("update switch_information set switch_type=? where switch_id=?");
            pst.setString(2, switch_id);
            if (resultSet.getString("switch_type").equals("1")) {
                pst.setString(1, "0");
                pst.executeUpdate();
                return true;
            }
            pst.setString(1, "1");
            pst.executeUpdate();
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }



    public static String verify(String master_sn, String master_key) {
        try {
            String tmp = "-1";
            String sql = String.format("select master_id,master_outcome from master_information where master_sn='%s' and master_key='%s';", master_sn, master_key);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            System.out.println("#" + resultSet.getString(("master_outcome")) + "#" + resultSet.getString("master_id"));
            if (resultSet.getString("master_outcome").equals("1")) {
                tmp = resultSet.getString("master_id");
            }
            statement.close();
            return tmp;
        } catch (SQLException e) {
            return "-1";
        }
    }

    public static String Query_main1() {
        JSONArray array = linestring("select numerical,sensor_type from sensor_log LEFT JOIN sensor_information on sensor_log.sensor_id = sensor_information.sensor_id  where sensor_log.master_id=1 and sensor_log.equipment_id=1 and sensor_log.sensor_id=1 order by sensor_time DESC limit 1", false);
        String tmp = "\"n1\":" + array.toString() + ",";

        array = linestring("select numerical,sensor_type from sensor_log LEFT JOIN sensor_information on sensor_log.sensor_id = sensor_information.sensor_id  where sensor_log.master_id=1 and sensor_log.equipment_id=1 and sensor_log.sensor_id=2 order by sensor_time DESC limit 1;", false);
        tmp = tmp + "\"n2\":" + array.toString() + ",";

        array = linestring("select numerical,sensor_type from sensor_log LEFT JOIN sensor_information on sensor_log.sensor_id = sensor_information.sensor_id  where sensor_log.master_id=1 and sensor_log.equipment_id=1 and sensor_log.sensor_id=3 order by sensor_time DESC limit 1;", false);
        tmp = tmp + "\"n3\":" + array.toString() + ",";

        array = linestring("select numerical,sensor_type from sensor_log LEFT JOIN sensor_information on sensor_log.sensor_id = sensor_information.sensor_id  where sensor_log.master_id=1 and sensor_log.equipment_id=1 and sensor_log.sensor_id=4 order by sensor_time DESC limit 1;", false);
        tmp = tmp + "\"n4\":" + array.toString() + ",";

        array = linestring("select numerical,sensor_type from sensor_log LEFT JOIN sensor_information on sensor_log.sensor_id = sensor_information.sensor_id  where sensor_log.master_id=1 and sensor_log.equipment_id=1 and sensor_log.sensor_id=5 order by sensor_time DESC limit 1;", false);
        tmp = tmp + "\"n5\":" + array.toString() + ",";

        array = linestring("select numerical,sensor_type from sensor_log LEFT JOIN sensor_information on sensor_log.sensor_id = sensor_information.sensor_id  where sensor_log.master_id=1 and sensor_log.equipment_id=1 and sensor_log.sensor_id=6 order by sensor_time DESC limit 1;", false);
        tmp = tmp + "\"n6\":" + array.toString() + ",";

        array = linestring("select numerical,sensor_type from sensor_log LEFT JOIN sensor_information on sensor_log.sensor_id = sensor_information.sensor_id  where sensor_log.master_id=1 and sensor_log.equipment_id=1 and sensor_log.sensor_id=5 order by sensor_time DESC limit 1;", false);
        tmp = tmp + "\"n7\":" + array.toString() + ",";

        array = linestring("select numerical,sensor_type from sensor_log LEFT JOIN sensor_information on sensor_log.sensor_id = sensor_information.sensor_id  where sensor_log.master_id=1 and sensor_log.equipment_id=1 and sensor_log.sensor_id=6 order by sensor_time DESC limit 1;", false);
        tmp = tmp + "\"n8\":" + array.toString();

        tmp = tmp.replace("[", "");
        tmp = tmp.replace("]", "");
        return "{\"code\":0,\"msg\":\"\"" + ",\"data\":{" + tmp + "}}\r\n";
    }

    public static String Query_main2() {
        JSONArray array = linestring("select switch_outcome,switch_type from switch_information where switch_id =1;", false);
        String tmp = "\"s1\":" + array.toString() + ",";

        array = linestring("select switch_outcome,switch_type from switch_information where switch_id =2;", false);
        tmp = tmp + "\"s2\":" + array.toString() + ",";

        array = linestring("select switch_outcome,switch_type from switch_information where switch_id =3;", false);
        tmp = tmp + "\"s3\":" + array.toString() + ",";

        array = linestring("select switch_outcome,switch_type from switch_information where switch_id =4;", false);
        tmp = tmp + "\"s4\":" + array.toString() + ",";

        array = linestring("select switch_outcome,switch_type from switch_information where switch_id =5;", false);
        tmp = tmp + "\"s5\":" + array.toString() + ",";

        array = linestring("select switch_outcome,switch_type from switch_information where switch_id =6;", false);
        tmp = tmp + "\"s6\":" + array.toString() + ",";

        array = linestring("select switch_outcome,switch_type from switch_information where switch_id =7;", false);
        tmp = tmp + "\"s7\":" + array.toString() + ",";

        array = linestring("select switch_outcome,switch_type from switch_information where switch_id =8;", false);
        tmp = tmp + "\"s8\":" + array.toString();

        tmp = tmp.replace("[", "");
        tmp = tmp.replace("]", "");
        return "{\"code\":0,\"msg\":\"\"" + ",\"data\":{" + tmp + "}}\r\n";
    }

    public static String Query_analyze() {

        JSONArray array = linestring("select sensor_time from sensor_log where master_id=1 and equipment_id=1 and sensor_id=1 order by sensor_time DESC limit 10;", true);
        String tmp = "\"times\":" + array.toString() + ",";

        array = linestring("select numerical from sensor_log where master_id=1 and equipment_id=1 and sensor_id=1 order by sensor_time DESC limit 10;", true);
        tmp = tmp + "\"illumination\":" + array.toString() + ",";

        array = linestring("select numerical from sensor_log where master_id=1 and equipment_id=1 and sensor_id=2 order by sensor_time DESC limit 10;", true);
        tmp = tmp + "\"mq2\":" + array.toString() + ",";

        array = linestring("select numerical from sensor_log where master_id=1 and equipment_id=1 and sensor_id=3 order by sensor_time DESC limit 10;", true);
        tmp = tmp + "\"mq135\":" + array.toString() + ",";

        array = linestring("select numerical from sensor_log where master_id=1 and equipment_id=1 and sensor_id=4 order by sensor_time DESC limit 10;", true);
        tmp = tmp + "\"humidity\":" + array.toString() + ",";

        array = linestring("select numerical from sensor_log where master_id=1 and equipment_id=1 and sensor_id=5 order by sensor_time DESC limit 10;", true);
        tmp = tmp + "\"temperature\":" + array.toString() + ",";

        array = linestring("select numerical from sensor_log where master_id=1 and equipment_id=1 and sensor_id=4 order by sensor_time DESC limit 10;", true);
        tmp = tmp + "\"Thumidity\":" + array.toString() + ",";

        array = linestring("select numerical from sensor_log where master_id=1 and equipment_id=1 and sensor_id=5 order by sensor_time DESC limit 10;", true);
        tmp = tmp + "\"Ttemperature\":" + array.toString();

        return "{\"code\":0,\"msg\":\"\"" + ",\"data\":{" + tmp + "}}\r\n";
    }

    public static JSONArray linestring(String sql, boolean type) {
        try {
            Statement statement = connection.createStatement();
            JSONArray array = new JSONArray();
            JSONArray array0 = new JSONArray();
            ResultSet resultSet = statement.executeQuery(sql);
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                JSONObject jsonObj = new JSONObject();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnLabel(i);
                    String value = resultSet.getString(columnName);
                    jsonObj.put(columnName, value);
                    array0.put(value);
                }
                array.put(jsonObj);
            }
            resultSet.close();
            if (type) {
                return array0;
            }
            return array;
        } catch (SQLException e) {
            return null;
        }
    }


    public static String Query_data(String sql) {
        JSONArray array = linestring(sql, false);
        if (array != null) {
            return "{\"code\":0,\"msg\":\"\",\"count\":" + array.length() + ",\"data\":" + array + "}\r\n";
        }
        return "{\"code\":0,\"msg\":\"\",\"count\":0,\"data\":}\r\n";
    }

    public static void Record_sensor(String master_id, String equipment_id, String sensor_id, String numerical) {
        try {
            String sql = "insert into sensor_log (master_id,equipment_id,sensor_id,numerical,sensor_time) values (?,?,?,?,now())";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, master_id);
            preparedStatement.setString(2, equipment_id);
            preparedStatement.setString(3, sensor_id);
            preparedStatement.setString(4, numerical);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void Record_master(String master_id, String master_change, String master_ip) {
        try {
            String sql = "insert into master_log (master_id,master_change,master_ip,master_time) values (?,?,?,now())";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, master_id);
            preparedStatement.setString(2, master_change);
            preparedStatement.setString(3, master_ip);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        final String DB_URL = "";
        final String USER = "";
        final String PASS = "";
        try {
            ConnectionConsole.println("Mysql connection ...", "INFO");
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            //Statement statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Mysql ERROR");
            throw new RuntimeException(e);
        }
        while (true) {

        }
    }
}

