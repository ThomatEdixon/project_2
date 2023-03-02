package io.aptech.Model;

import io.aptech.Generic.DAORepository;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BudgetStatement implements DAORepository {
    public Connection connection = MySQLConnection.getConnection();
    @Override
    public void insert(Object o) {

    }
    @Override
    public void update(Object o) {

    }

    @Override
    public Object getById(int id) {
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM tbl_budget WHERE user_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1,id);
            rs = stm.executeQuery();
            System.out.println("123456");


        }catch (SQLException e){
            e.printStackTrace();
        }
        return rs;
    }
    @Override
    public void delete(Object o) {
    }

    @Override
    public ObservableList getAll() {
        return null;
    }

    public void updateBalance(int _id, int money) {
        try {
            String sql = "UPDATE tbl_budget SET balance = ? WHERE user_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1,money);
            stm.setInt(2,_id);
             stm.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}

}

