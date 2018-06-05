package network;

import models.User;

import java.sql.*;
import java.util.ArrayList;

public class JDBC {
    private static String url = "jdbc:mysql://localhost:3306/spms";
    private static String password = "spms";
    private static String username = "spms_user";

    private static Connection connection;

    private static void connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection =  DriverManager.getConnection(url, username,password);
        }catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    public static boolean authenticate(String username, String password,String table) {
        String query = "select username,passw from "+table+" where username='"+username+"' and passw='"+password+"'";
        try {
            connect();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            Boolean is_authenticate = resultSet.next();
            statement.close();
            connection.close();
            return is_authenticate;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean checkUser(String username) {
        String query = "select username from users where username='"+username+"'";
        try {
            connect();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            Boolean is_exist= resultSet.next();
            statement.close();
            connection.close();
            return is_exist;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    public static ResultSet fetch(String query){
        connect();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs;
            rs = statement.executeQuery(query);
            statement.close();
            connection.close();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static int insert(String table, User user){
        connect();
        try {
            String qurey = "insert into "+table+" values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(qurey);
            preparedStatement.setString(1,user.getFirst_name());
            preparedStatement.setString(2,user.getLast_name());
            preparedStatement.setString(3,user.getAddress());
            preparedStatement.setString(4,user.getTell());
            preparedStatement.setBoolean(5,user.getSex());
            preparedStatement.setString(6,user.getAge());
            preparedStatement.setBoolean(7,user.isCustomer());
            preparedStatement.setString(8,user.getUsername());
            preparedStatement.setString(9,user.getPassword());
            int result = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

}
