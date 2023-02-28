package io.aptech.Model;

import io.aptech.Entity.User;
import io.aptech.Generic.DAORepository;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ForgotPasswordStatement implements DAORepository<User> {
    private Connection connection = MySQLConnection.getConnection();

    public void create() {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS tempCode (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "email VARCHAR(255) NOT NULL," +
                    "code VARCHAR(7) NOT NULL )";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet check(String email, String phone) {
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM tbl_user WHERE user_email =? AND user_phone =?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, phone);
            rs = pst.executeQuery();
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public void insert(User user) {

    }

    public void insertTempCode(String email, String code) {
        try {
            String sql = "INSERT INTO tempCode (email, code) VALUES (?,?)";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, code);
            pst.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {

    }

    @Override
    public User getById(int id) {
        return null;
    }

    public ResultSet checkVerifyCode(String verifyCode) {
        ResultSet rs = null ;
        try {
            String sql = "SELECT * FROM tempCode WHERE code =?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, verifyCode);
            rs = pst.executeQuery();
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public void delete(User user) {

    }

    public void dropTempCode() {
        try {
            String sql = "DROP TABLE tempCode";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ObservableList<User> getAll() {
        return null;
    }
}
