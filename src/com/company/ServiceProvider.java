package com.company;

public class ServiceProvider {
    private int id;
    private String first_name;
    private String last_name;
    private String address;
    private Long tell;
    private int age;
    private boolean sex;
    private int rate;

    public ServiceProvider(int id, String first_name, String last_name,
                           String address, Long tell, int age, boolean sex, int rate){
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.tell = tell;
        this.age = age;
        this.sex = sex;
        this.rate = rate;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getTell() {
        return tell;
    }

    public void setTell(Long tell) {
        this.tell = tell;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
