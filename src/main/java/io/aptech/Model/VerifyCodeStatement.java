package io.aptech.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VerifyCodeStatement{
    private Connection connection = MySQLConnection.getConnection();

    public ResultSet checkCode(String code){
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM tempCode WHERE code =?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, code);
            rs = pst.executeQuery();
        }catch (SQLException e) {
           e.printStackTrace();
        }
        return rs;
    }
}
