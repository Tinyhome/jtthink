package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by Tinyhome on 16/7/13.
 */

public class TinyPool {

    //连接字符串
    public static String conn_url = "jdbc:mysql://127.0.0.1:3306/jtthink?user=root&password=jiafabing&useUnicode=true&characterEncoding=utf-8";

    //驱动字符串
    public static String driver = "com.mysql.jdbc.Driver";
    //public static Connection pool = null;
    //连接池
    public static Map<Connection,String> pools = null;
    static {

        //反射
        try {
            pools = new HashMap<Connection, String>();
            Class.forName(driver);
            for(int i=1;i<=2;i++){
                //0代表空闲,1代表正在使用
                pools.put(DriverManager.getConnection(conn_url),"0");
            }
            //pool = DriverManager.getConnection(conn_url);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        boolean isConnectionAvailable = true;
        for (Entry<Connection, String> entry : pools.entrySet()) {
            synchronized (entry) {
                if (entry.getValue()=="0") {
                    entry.setValue("1");
                    return (Connection) entry.getKey();
                }
                isConnectionAvailable = false;
            }
        }
        if (!isConnectionAvailable) {
            Class.forName(driver);
            java.sql.Connection con = DriverManager.getConnection(conn_url);
            pools.put(con, "1");
            return con;
        }
        return null;
    }

    public  void closeConnection(java.sql.Connection connection) throws ClassNotFoundException, SQLException {
        for (Entry<Connection, String> entry : pools.entrySet()) {
            synchronized (entry) {
                if (entry.getKey().equals(connection)) {
                    //Getting Back the conncetion to Pool
                    entry.setValue("0");
                }
            }
        }
    }

}
