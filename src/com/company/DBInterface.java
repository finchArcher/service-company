package com.company;

import models.Report;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBInterface {

    public static boolean authenticate(String username, String password,String table){
        String query = "select username,passw from "+table+" where username='"+username+"' and passw='"+password+"'";
        ResultSet rs = JDBC.fetch(query);
        return (rs != null) ? true:false;
    }

    public static ArrayList<Report> fetchReport(String username) throws SQLException {
        String query = "select service_id,provider_id,customer_id,description,cost,_date,rate from "+
                "reports where provider_id="+username+"";
        ResultSet resultSet = JDBC.fetch(query);
        Report report = new Report();
        ArrayList<Report> reports = new ArrayList<Report>();
        while (resultSet.next()){
            report.setService_id(resultSet.getInt("service_id"));
            report.setProvider_id(resultSet.getInt("provider_id"));
            report.setCustomer_id(resultSet.getInt("customer_id"));
            report.setDescription(resultSet.getString("description"));
            report.setCost(resultSet.getInt("cost"));
            report.setDate(resultSet.getDate("_date"));
            report.setRate(resultSet.getInt("rate"));
            reports.add(report);
        }
        return reports;
    }

}
