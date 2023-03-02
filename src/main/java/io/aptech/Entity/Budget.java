package io.aptech.Entity;

import java.sql.Date;

public class Budget {
    private int id;
    private User user;
    private String type;
    private int balance;
    private Date date;

    public Budget() {
    }

    public Budget(User user, String type, int balance, Date date) {
        this.user = user;
        this.type = type;
        this.balance = balance;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
