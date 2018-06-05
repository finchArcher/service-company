package models;

import java.sql.Date;

public class Service {
    private String id;
    private String provider_id;
    private String customer_id;
    private int cost;
    private Date date;
    private String description;

    public Service(String id, String provider_id, int cost, String description){
        this.id = id;
        this.provider_id = provider_id;
        this.cost = cost;
        this.description = description;
    }

    public Service(String provider_id, int cost, String description){
        this.provider_id = provider_id;
        this.cost = cost;
        this.description = description;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
