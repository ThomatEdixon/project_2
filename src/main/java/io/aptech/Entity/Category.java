package io.aptech.Entity;

import io.aptech.Enum.CategoryTransaction;

import java.sql.Date;

public class Category {
    private int id;
    private Budget budget;
    private CategoryTransaction categoryTransaction;
    private Date start;
    private Date end;

    public Category() {
    }

    public Category(int id, Budget budget, CategoryTransaction categoryTransaction, Date start, Date end) {
        this.id = id;
        this.budget = budget;
        this.categoryTransaction = categoryTransaction;
        this.start = start;
        this.end = end;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public CategoryTransaction getCategoryTransaction() {
        return categoryTransaction;
    }

    public void setCategoryTransaction(CategoryTransaction categoryTransaction) {
        this.categoryTransaction = categoryTransaction;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
