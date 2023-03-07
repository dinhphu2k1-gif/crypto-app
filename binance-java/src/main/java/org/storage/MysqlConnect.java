package org.storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MysqlConnect {
    public static Connection connection;
    private static String DB_URL = "jdbc:mysql://localhost:3306/crypto";
    private static String USER_NAME = "phukaioh";
    private static String PASSWORD = "12012001";

    public MysqlConnect() {
        initConnection();
    }

    public void initConnection() {
        if (connection == null) {
            synchronized (MysqlConnect.class) {
                if (connection == null) {
                    try {
                        connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
                        System.out.println("connect successfully!");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void insert(List<Object> data) {
        String sql = "INSERT INTO trades VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement query = connection.prepareStatement(sql);

            query.setString(1, (String) data.get(0)); //eventType
            query.setLong(2, (Long) data.get(1)); //eventTime
            query.setString(3, (String) data.get(2)); //symbol
            query.setLong(4, (Long) data.get(3)); //tradeId
            query.setString(5, (String) data.get(4)); //price
            query.setString(6, (String) data.get(5));//quantity
            query.setLong(7, (Long) data.get(6)); //buyOrderId
            query.setLong(8, (Long) data.get(7)); //sellOrderId
            query.setLong(9, (Long) data.get(8)); //tradeTime
            query.setBoolean(10, (Boolean) data.get(9)); //isBuy
            query.setBoolean(11, (Boolean) data.get(10)); // ignore

            query.execute();
            System.out.println("insert success!!!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        MysqlConnect mysqlConnect = new MysqlConnect();
    }
}
