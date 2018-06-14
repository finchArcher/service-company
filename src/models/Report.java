package models;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import java.sql.Date;
import java.sql.Timestamp;

public class Report {
    private String provider_id;
    private String customer_id;
    private String description;
    private int cost;
    private Timestamp date;
    private int rate;

    public Report(){

    }
    public Report(String provider_id, String customer_id, String description, int cost, Timestamp date, int rate) {
        this.provider_id = provider_id;
        this.customer_id = customer_id;
        this.description = description;
        this.cost = cost;
        this.date = date;
        this.rate = rate;
    }
    public Report(Report report){
        this.provider_id = report.getProvider_id();
        this.customer_id = report.getCustomer_id();
        this.description = report.getDescription();
        this.cost = report.getCost();
        this.date = report.getDate();
    }


    public String getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(String provider_id) {
        this.provider_id = provider_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
