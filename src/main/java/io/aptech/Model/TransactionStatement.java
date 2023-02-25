package io.aptech.Model;

import io.aptech.Entity.Transactions;
import io.aptech.Generic.DAORepository;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionStatement implements DAORepository<Transactions> {
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
}
