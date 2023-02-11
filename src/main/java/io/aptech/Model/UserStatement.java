package io.aptech.Model;

import io.aptech.Entity.User;
import io.aptech.Generic.DAORepository;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserStatement implements DAORepository<User> {
    private Connection connection = MySQLConnection.getConnection();
    @Override
    public void insert(User user) {
        try{
            String sql = "Insert into tbl_user (full_name,user_name,user_password,email,phone)"
                    +" VALUES "
                    +"(?,?,?,?,?)";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, user.getFullName());
            pst.setString(2, user.getUsername());
            pst.setString(3, user.getPassword());
            pst.setString(4, user.getEmail());
            pst.setString(5, user.getPhone());
            pst.executeUpdate();
        }catch (SQLException e){
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
    public ResultSet getBYUserName(String userName){
        ResultSet user = null;
        try {
            String sql = "select * from tbl_user where email = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1,userName);
            user = stm.executeQuery();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void delete(User user) {

    }

    @Override
    public ObservableList<User> getAll() {
        return null;
    }
}
