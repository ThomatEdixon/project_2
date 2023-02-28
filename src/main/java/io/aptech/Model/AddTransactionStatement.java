package io.aptech.Model;

import io.aptech.Entity.Budget;
import io.aptech.Entity.Category;
import io.aptech.Entity.Transactions;
import io.aptech.Enum.CategoryTransaction;
import io.aptech.Generic.DAORepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddTransactionStatement implements DAORepository<Transactions> {
    private static Connection connection = MySQLConnection.getConnection();
    @Override
    public void insert(Transactions transactions) {
        try{
            String sql = "INSERT INTO tbl_transactions" +
                    "(transaction_date,category_id,amount,description)" +
                    "VALUES(?,?,?,?) ";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setDate(1,transactions.getDate());
            pst.setInt(2,transactions.getCategory().getId());
            pst.setInt(3,transactions.getAmount());
            pst.setString(4,transactions.getDescription());
            pst.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void update(Transactions transactions) {

    }

    @Override
    public Transactions getById(int id) {
        return null;
    }

    @Override
    public void delete(Transactions transactions) {

    }

    @Override
    public ObservableList<Transactions> getAll() {
        return null;
    }
    public ObservableList<Category> getCategories(int id) {
        ObservableList<Category> categories = FXCollections.observableArrayList();
        try {
            String sql = "select tbl_category.id , tbl_category.budget_id , tbl_category.category_transactions,tbl_category.start_date" +
                    ",tbl_category.end_date from tbl_budget inner join tbl_category on tbl_category.budget_id = tbl_budget.id " +
                    "inner join tbl_user on tbl_budget.user_id = tbl_user.id where tbl_user.id = ? ";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1,id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Category category =new Category();
                category.setId(rs.getInt("tbl_category.id"));
                Budget budget   =new Budget();
                budget.setId(rs.getInt("tbl_category.budget_id"));
                category.setBudget(budget);
                category.setCategoryTransaction(CategoryTransaction.valueOf(rs.getString("tbl_category.category_transactions")));
                category.setStart(rs.getDate("tbl_category.start_date"));
                category.setEnd(rs.getDate("tbl_category.end_date"));
                categories.add(category);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return categories;
    }
    public Category getCategoriesByCategoryTransaction(int id, String categoryTransaction) {
        Category category = new Category();
        try {
            String sql = "select tbl_category.id , tbl_category.budget_id , tbl_category.category_transactions,tbl_category.start_date" +
                    ",tbl_category.end_date from tbl_budget inner join tbl_category on tbl_category.budget_id = tbl_budget.id " +
                    "inner join tbl_user on tbl_budget.user_id = tbl_user.id where tbl_user.id = ? AND tbl_category.category_transactions = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1,id);
            pst.setString(2,categoryTransaction);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                category.setId(rs.getInt("tbl_category.id"));
                Budget budget   =new Budget();
                budget.setId(rs.getInt("tbl_category.budget_id"));
                category.setBudget(budget);
                category.setCategoryTransaction(CategoryTransaction.valueOf(rs.getString("tbl_category.category_transactions")));
                category.setStart(rs.getDate("tbl_category.start_date"));
                category.setEnd(rs.getDate("tbl_category.end_date"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return category;
    }
}
