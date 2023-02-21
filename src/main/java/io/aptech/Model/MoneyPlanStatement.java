package io.aptech.Model;

import io.aptech.Entity.MoneyFixed;
import io.aptech.Generic.DAORepository;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MoneyPlanStatement implements DAORepository {
    private Connection connection = MySQLConnection.getConnection();
    public void updateType(String type,String data,int idUser) {
        try {
            System.out.println(type + "type");
            String sql = "UPDATE tbl_moneyplan SET "+type+" = ?  WHERE user_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1,data);
            stm.setInt(2,idUser);
            stm.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    @Override
    public void insert(Object o) {

    }

    @Override
    public void update(Object o) {

    }

    @Override
    public ResultSet getById(int id) {
        ResultSet rs= null;
        try {
            String sql = "SELECT * FROM tbl_moneyPlan WHERE user_id = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1,id);
            rs = pst.executeQuery();
        }catch (SQLException e) {
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
}
