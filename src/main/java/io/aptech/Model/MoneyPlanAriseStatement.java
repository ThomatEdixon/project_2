package io.aptech.Model;

import io.aptech.Generic.DAORepository;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MoneyPlanAriseStatement implements DAORepository {
    private Connection connection = MySQLConnection.getConnection();
    public void updateType(String type,String data,int idUser) {
        try {
            System.out.println(type + "type");
            String sql = "UPDATE tbl_moneyPlanArise SET "+type+" = ?  WHERE user_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1,data);
            stm.setInt(2,idUser);
            stm.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void create() {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS tbl_moneyPlanArise (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    " user_id int not null," +
                    "o_foodDrink int," +
                    "o_clothes int ," +
                    "o_petroleum int ," +
                    "foreign key (user_id) references tbl_user(id)" +
                    ");";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.executeUpdate();
        }catch (SQLException e) {
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
            String sql = "SELECT * FROM tbl_moneyPlanArise WHERE user_id = ?";
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
