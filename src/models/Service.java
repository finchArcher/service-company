package models;

import java.sql.Date;

public class Service {
    private String provider_id;
    private String customer_id;
    private Date date;
    private String description;
    private int cost;
    private int rate;

    public Service(){}

    public Service(String provider_id, String customer_id,Date date, String description,int cost,int rate){
        this.provider_id = provider_id;
        this.customer_id = customer_id;
        this.date = date;
        this.description = description;
        this.cost = cost;
        this.rate = rate;
    }

    public Service(String provider_id, String description, int cost) {
        this.provider_id = provider_id;
        this.description = description;
        this.cost = cost;
    }


    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(String provider_id) {
        this.provider_id = provider_id;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
