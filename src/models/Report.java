package models;

import java.sql.Date;

public class Report {
    private int service_id;
    private int provider_id;
    private int customer_id;
    private String description;
    private int cost;
    private Date date;
    private int rate;

    public Report(){

    }
    public Report(int service_id, int provider_id, int customer_id, String description, int cost, Date date) {
        this.service_id = service_id;
        this.provider_id = provider_id;
        this.customer_id = customer_id;
        this.description = description;
        this.cost = cost;
        this.date = date;
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public int getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(int provider_id) {
        this.provider_id = provider_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
