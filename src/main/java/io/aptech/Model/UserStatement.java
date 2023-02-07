package io.aptech.Model;

import io.aptech.Entity.User;
import io.aptech.Generic.DAORepository;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

    @Override
    public void delete(User user) {

    }

    @Override
    public ObservableList<User> getAll() {
        return null;
    }
}
