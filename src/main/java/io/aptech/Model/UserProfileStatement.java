package io.aptech.Model;

import io.aptech.Entity.User;
import io.aptech.Enum.UserGender;
import io.aptech.Generic.DAORepository;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserProfileStatement implements DAORepository<User> {
    private Connection connection = MySQLConnection.getConnection();

    @Override
    public void insert(User user) {

    }

    @Override
    public void update(User user) {
        try {
            String sql = "UPDATE tbl_user SET gender = ? , full_name = ?, user_email = ?, user_phone = ?, user_image = ? WHERE id =?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, String.valueOf(user.getGender()));
            pst.setString(2, user.getFullName());
            pst.setString(3, user.getEmail());
            pst.setString(4, user.getPhone());
            pst.setString(5, user.getImage());
            pst.setInt(6, user.getId());
            pst.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public User getById(int id) {
        return null;
    }

    public User getUserById(int id) {
        User user = new User();
        try{
            String sql = "SELECT * FROM tbl_user WHERE id =?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
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


        }catch (SQLException e) {
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
