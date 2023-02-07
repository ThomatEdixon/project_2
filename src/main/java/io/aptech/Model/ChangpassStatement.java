package io.aptech.Model;

import io.aptech.Entity.User;
import io.aptech.Generic.DAORepository;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChangpassStatement implements DAORepository<User> {
    private final Connection connection = MySQLConnection.getConnection();

    public ResultSet checkk(String email, String phone) {
        ResultSet rsss= null;
        try {
            String sql = "SELECT * FROM tbl_user WHERE email =? AND phone =?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, phone);
            rsss= pst.executeQuery();
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return rsss;
    }

    @Override
    public void insert(User userr) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public User getById(int id) {
        return null;
    }

    @Override
    public void delete(User user) {

    }

    @Override
    public ObservableList<User> getAll() {
        return null;
    }

    public ResultSet changepasszz( String pass, String email){
        ResultSet rss = null;
        try {
            String sql = "Update  tbl_user set user_password=? where user_name=? ";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, pass);
            st.setString(2, email);
            st.executeUpdate();
            rss=st.executeQuery();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return rss ;
     }
}
