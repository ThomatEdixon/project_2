package io.aptech.Model;

import io.aptech.Entity.User;
import io.aptech.Enum.UserGender;
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
            String sql = "Insert into tbl_user (full_name,gender, user_password,user_email,user_phone, user_image)"
                    +" VALUES "
                    +"(?,?,?,?,?,?)";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, user.getFullName());
            pst.setString(2, String.valueOf(user.getGender()));
            pst.setString(3, user.getPassword());
            pst.setString(4, user.getEmail());
            pst.setString(5, user.getPhone());
            pst.setString(6, user.getImage());
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
    public ResultSet getByEmail(String email){
        ResultSet user = null;
        try {

            String sql = "select * from tbl_user where user_email = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1,email);
            user = stm.executeQuery();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    public User getUserById(int id){
        User user = new User();

        try {
            String sql = "SELECT * FROM tbl_user where id = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1,id);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                int user_id = rs.getInt("id");
                String fullName = rs.getString("full_name");
                String gender = rs.getString("gender");
                String user_email = rs.getString("user_email");
                String user_password = rs.getString("user_password");
                String user_phone = rs.getString("user_phone");
                String user_image = rs.getString("user_image");

                user.setId(user_id);
                user.setFullName(fullName);
                user.setGender(UserGender.valueOf(gender));
                user.setEmail(user_email);
                user.setPassword(user_password);
                user.setPhone(user_phone);
                user.setImage(user_image);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    public User getUserByEmail(String email){
        User user = new User();

        try {
            String sql = "SELECT * FROM tbl_user where user_email = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1,email);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                int user_id = rs.getInt("id");
                String fullName = rs.getString("full_name");
                String gender = rs.getString("gender");
                String user_email = rs.getString("user_email");
                String user_password = rs.getString("user_password");
                String user_phone = rs.getString("user_phone");
                String user_image = rs.getString("user_image");

                user.setId(user_id);
                user.setFullName(fullName);
                user.setGender(UserGender.valueOf(gender));
                user.setEmail(user_email);
                user.setPassword(user_password);
                user.setPhone(user_phone);
                user.setImage(user_image);
            }

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

    public void updatePasswordById(String password, int id) {
        try {
            String sql = "UPDATE tbl_user SET user_password =? WHERE id = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1,password);
            pst.setInt(2,id);
            pst.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}