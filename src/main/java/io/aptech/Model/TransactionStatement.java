package io.aptech.Model;

import io.aptech.Entity.Budget;
import io.aptech.Entity.Category;
import io.aptech.Entity.Transactions;
import io.aptech.Enum.CategoryTransaction;
import io.aptech.Generic.DAORepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class TransactionStatement implements DAORepository<Transactions> {
    private static Connection connection= MySQLConnection.getConnection();
    private static ResultSet rs;
    @Override
    public void insert(Transactions transactions) {

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
    public Category getCategory(int userId , int id){
        Category category = new Category();
        AddBudgetStatement addBudgetStatement = new AddBudgetStatement();
        Budget budget = addBudgetStatement.getById(userId);
        try{
            String sql = "SELECT * FROM tbl_category WHERE budget_id = ? and id = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, budget.getId());
            pst.setInt(2, id);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                category.setId(rs.getInt("id"));
                category.setBudget(budget);
                category.setCategoryTransaction(CategoryTransaction.valueOf(rs.getString("category_transactions")));
                category.setStart(rs.getDate("start_date"));
                category.setEnd(rs.getDate("end_date"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return category;
    }
    @Override
    public ObservableList<Transactions> getAll() {
        return null;
    }
    public ObservableList<Transactions> getAllTransactions(int userId) {
        ObservableList<Transactions> transactions = FXCollections.observableArrayList();
        try{
            String sql = "SELECT * FROM tbl_transactions where 1";
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Transactions transaction = new Transactions();
                transaction.setId(rs.getInt("id"));
                transaction.setDate(Date.valueOf(rs.getString("transaction_date")));
                transaction.setDescription(rs.getString("description"));
                transaction.setAmount(rs.getInt("amount"));
                transaction.setCategory(getCategory(userId,rs.getInt("category_id")));
                transactions.add(transaction);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return transactions;
    }
    public ObservableList<Transactions> searchTransactions(int userId,Transactions transactions){
        ObservableList<Transactions> transactions1 = FXCollections.observableArrayList();
        try{
            String sql = "";
            Date date = Date.valueOf("1000-01-28");
            if(transactions.getDate().equals(date) && transactions.getAmount() == 0 && transactions.getCategory().getId() == 0&& transactions.getDescription().length() < 1){
                sql = "select * from tbl_transactions where 1";
                PreparedStatement pst = connection.prepareStatement(sql);
                rs = pst.executeQuery();
            }else if(transactions.getDate().equals(date) && transactions.getAmount() == 0 && transactions.getCategory().getId() == 0&& transactions.getDescription().length() >= 1) {
                sql = "select * from tbl_transactions where description = ?";
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setString(1, transactions.getDescription().replace(" ","%"));
                rs =pst.executeQuery();
            }else if(transactions.getDate().equals(date) && transactions.getAmount() == 0 && transactions.getCategory().getId() != 0 && transactions.getDescription().length() < 1){
                sql = "select * from tbl_transactions where category_id = ?";
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setInt(1, transactions.getCategory().getId());
                rs =pst.executeQuery();
            }else if(transactions.getDate().equals(date) && transactions.getAmount() != 0 && transactions.getCategory().getId() == 0&& transactions.getDescription().length() < 1){
                sql = "select * from tbl_transactions where amount = ?";
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setInt(1, transactions.getAmount());
                rs =pst.executeQuery();
            } else if (!transactions.getDate().equals(date) && transactions.getAmount() == 0 && transactions.getCategory().getId() == 0&& transactions.getDescription().length() < 1) {
                sql = "select * from tbl_transactions where transaction_date = ?";
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setDate(1,transactions.getDate());
                rs =pst.executeQuery();
            } else if (transactions.getDate().equals(date) && transactions.getAmount() == 0 && transactions.getCategory().getId() != 0&& transactions.getDescription().length() >= 1) {
                sql = "select * from tbl_transactions where description = ? and category_id = ?";
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setString(1, transactions.getDescription().replace(" ","%"));
                pst.setInt(2, transactions.getCategory().getId());
                rs =pst.executeQuery();
            }  else if (transactions.getDate().equals(date) && transactions.getAmount() != 0 && transactions.getCategory().getId() == 0&& transactions.getDescription().length() >= 1) {
                sql = "select * from tbl_transactions where description = ? and amount = ?";
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setString(1, transactions.getDescription().replace(" ","%"));
                pst.setInt(2, transactions.getAmount());
                rs =pst.executeQuery();
            } else if (!transactions.getDate().equals(date) && transactions.getAmount() == 0 && transactions.getCategory().getId() == 0&& transactions.getDescription().length() >= 1) {
                sql = "select * from tbl_transactions where description = ? and transaction_date = ?";
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setString(1, transactions.getDescription().replace(" ","%"));
                pst.setDate(2,transactions.getDate());
                rs =pst.executeQuery();
            } else if (transactions.getDate().equals(date) && transactions.getAmount() != 0 && transactions.getCategory().getId() != 0&& transactions.getDescription().length() < 1) {
                sql = "select * from tbl_transactions where category_id = ? and amount = ?";
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setInt(1, transactions.getCategory().getId());
                pst.setInt(2, transactions.getAmount());
                rs =pst.executeQuery();
            } else if (!transactions.getDate().equals(date) && transactions.getAmount() == 0 && transactions.getCategory().getId() != 0&& transactions.getDescription().length() < 1) {
                sql = "select * from tbl_transactions where category_id = ? and transaction_date = ?";
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setInt(1, transactions.getCategory().getId());
                pst.setDate(2,transactions.getDate());
                rs =pst.executeQuery();
            } else if (!transactions.getDate().equals(date) && transactions.getAmount() != 0 && transactions.getCategory().getId() == 0&& transactions.getDescription().length() < 1) {
                sql = "select * from tbl_transactions where and amount = ? and transaction_date = ?";
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setInt(1, transactions.getAmount());
                pst.setDate(2,transactions.getDate());
                rs =pst.executeQuery();
            } else if (!transactions.getDate().equals(date) && transactions.getAmount() != 0 && transactions.getCategory().getId() != 0&& transactions.getDescription().length() < 1) {
                sql = "select * from tbl_transactions where amount = ? and transaction_date = ? and category_id = ?";
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setInt(1, transactions.getAmount());
                pst.setDate(2,transactions.getDate());
                pst.setInt(3, transactions.getCategory().getId());
                rs =pst.executeQuery();
            } else if (!transactions.getDate().equals(date) && transactions.getAmount() == 0 && transactions.getCategory().getId() != 0&& transactions.getDescription().length() >= 1) {
                sql = "select * from tbl_transactions where description = ? and transaction_date = ? and category_id = ?";
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setString(1, transactions.getDescription().replace(" ","%"));
                pst.setDate(2,transactions.getDate());
                pst.setInt(3, transactions.getCategory().getId());
                rs =pst.executeQuery();
            } else if (!transactions.getDate().equals(date) && transactions.getAmount() != 0 && transactions.getCategory().getId() == 0&& transactions.getDescription().length() >= 1) {
                sql = "select * from tbl_transactions where description = ? and transaction_date = ? and amount = ?";
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setString(1, transactions.getDescription().replace(" ","%"));
                pst.setDate(2,transactions.getDate());
                pst.setInt(3, transactions.getAmount());
                rs =pst.executeQuery();
            } else if (transactions.getDate().equals(date) && transactions.getAmount() != 0 && transactions.getCategory().getId() != 0&& transactions.getDescription().length() >= 1) {
                sql = "select * from tbl_transactions where amount = ? and category_id = ? and description = ?";
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setInt(1, transactions.getAmount());
                pst.setInt(2, transactions.getCategory().getId());
                pst.setString(3, transactions.getDescription().replace(" ","%"));
                rs =pst.executeQuery();
            } else if (!transactions.getDate().equals(date) && transactions.getAmount() != 0 && transactions.getCategory().getId() != 0&& transactions.getDescription().length() >= 1) {
                sql = "select * from tbl_transactions where amount = ? and category_id = ? and description = ? and transaction_date = ?";
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setInt(1, transactions.getAmount());
                pst.setInt(2, transactions.getCategory().getId());
                pst.setString(3, transactions.getDescription().replace(" ","%"));
                pst.setDate(4, transactions.getDate());
                rs =pst.executeQuery();
            }
            while (rs.next()) {
                Transactions transaction = new Transactions();
                transaction.setId(rs.getInt("id"));
                transaction.setDate(Date.valueOf(rs.getString("transaction_date")));
                transaction.setDescription(rs.getString("description"));
                transaction.setAmount(rs.getInt("amount"));
                transaction.setCategory(getCategory(userId,rs.getInt("category_id")));
                transactions1.add(transaction);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return transactions1;
    }
    public ObservableList<Transactions> getTransactions(String date,int userId) {
        ObservableList<Transactions> transactions = FXCollections.observableArrayList();
        try{
            String sql = "SELECT * FROM tbl_transactions where transaction_date like ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, "%"+date+"%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Transactions transaction = new Transactions();
                transaction.setId(rs.getInt("id"));
                transaction.setDate(Date.valueOf(rs.getString("transaction_date")));
                transaction.setDescription(rs.getString("description"));
                transaction.setAmount(rs.getInt("amount"));
                transaction.setCategory(getCategory(userId,rs.getInt("category_id")));
                transactions.add(transaction);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return transactions;
    }
}
