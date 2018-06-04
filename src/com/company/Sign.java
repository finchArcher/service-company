package com.company;

import java.sql.ResultSet;
import java.util.Scanner;

public class Sign {
    public static boolean authenticate(String username, String password,String table){
        String query = "select username,passw from "+table+" where username='"+username+"' and passw='"+password+"'";
        ResultSet rs = JDBC.fetch(query);
        return (rs != null) ? true:false;
    }

}
