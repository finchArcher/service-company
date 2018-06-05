package network;

import models.User;

import java.sql.*;
import java.util.ArrayList;

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
            statement.close();
            connection.close();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void insert(String table, User user){
        connect();
        try {
            String qurey = "insert into "+table+" valuse(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(qurey);
            preparedStatement.setInt(1,user.getId());
            preparedStatement.setString(2,user.getFirst_name());
            preparedStatement.setString(3,user.getLast_name());
            preparedStatement.setString(4,user.getAddress());
            preparedStatement.setLong(5,user.getTell());
            preparedStatement.setBoolean(6,user.getSex());
            preparedStatement.setInt(7,user.getAge());
            preparedStatement.setBoolean(8,user.isCustomer());
            preparedStatement.setString(9,user.getUsername());
            preparedStatement.setString(10,user.getPassword());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
