package models;

public class User {
    private String first_name;
    private String last_name;
    private String address;
    private String tell;
    private String age;
    private boolean sex;
    private String username;
    private String password;
    private boolean customer;
    private int rate;

    public User() {
    }

    public User(String username, String password,Boolean customer) {
        this.username = username;
        this.password = password;
        this.customer = customer;
    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.customer = customer;
    }

    public User(String first_name, String last_name,
                String address, String tell,
                String age, boolean sex,boolean customer, String username, String password) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.tell = tell;
        this.age = age;
        this.sex = sex;
        this.customer = customer;
        this.username = username;
        this.password = password;
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

    public String getTell() {
        return tell;
    }

    public void setTell(String tell) {
        this.tell = tell;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public boolean getSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public boolean getCustomer() {
        return customer;
    }

    public void setCustomer(boolean customer) {
        customer = customer;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRate() {
        return rate;
    }
    public void setRate(int rate) {
        this.rate = rate;
    }
}
