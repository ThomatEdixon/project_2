package io.aptech.Entity;

import java.sql.Date;

public class Transactions {
    private int id;
    private Category category;
    private Date date;
    private int amount;
    private String description;

    public Transactions() {
    }

    public Transactions(Category category, Date date, int amount, String description) {
        this.category = category;
        this.date = date;
        this.amount = amount;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
