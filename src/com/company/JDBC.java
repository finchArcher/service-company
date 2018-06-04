package com.company;

import java.sql.*;

public class JDBC {
    private static String url = "jdbc:mysql://localhost:3306/spms";
    private static String username = "spms_user";
    private static String password = "spms";
    private static Connection connection;

    private static void connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url,username,password);

        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static ResultSet fetch(String query){
        connect();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void insert(){

    }

}
