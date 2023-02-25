package io.aptech.Model;

import io.aptech.Entity.Budget;
import io.aptech.Entity.Category;
import io.aptech.Entity.User;
import io.aptech.Generic.DAORepository;
import javafx.collections.ObservableList;

import java.sql.*;

public class AddBudgetStatement implements DAORepository<Budget> {
    private static Connection connection = MySQLConnection.getConnection();
    @Override
    public void insert(Budget budget) {
        try{
            String sql = "INSERT INTO tbl_budget(user_id,type_currency,balance,save_date)" +
                    "values(?,?,?,?)";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1,budget.getUser().getId());
            pst.setString(2,budget.getType());
            pst.setDouble(3,budget.getBalance());
            pst.setDate(4,budget.getDate());
            pst.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Budget budget) {

    }

    @Override
    public Budget getById(int id) {
        Budget budget = new Budget();
        try{
            String sql = "SELECT * FROM tbl_budget WHERE user_id = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1,id);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                int b_id = rs.getInt("id");
                int user_id = rs.getInt("user_id");
                String type = rs.getString("type_currency");
                Double balance = rs.getDouble("balance");
                Date date = rs.getDate("save_date");
                User user = new User();
                user.setId(user_id);
                budget.setId(b_id);
                budget.setUser(user);
                budget.setType(type);
                budget.setBalance(balance);
                budget.setDate(date);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return budget;
    }

    @Override
    public void delete(Budget budget) {

    }

    @Override
    public ObservableList<Budget> getAll() {
        return null;
    }
    public void insertCategory(Category category){
        try{
            String sql = "INSERT INTO tbl_category(budget_id,category_transactions,start_date,end_date)" +
                    "values(?,?,?,?)";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1,category.getBudget().getId());
            pst.setString(2, String.valueOf(category.getCategoryTransaction()));
            pst.setDate(3,category.getStart());
            pst.setDate(4,category.getEnd());
            pst.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
