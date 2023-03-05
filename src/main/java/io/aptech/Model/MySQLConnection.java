package io.aptech.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    private static final String host = "jdbc:mysql://localhost:3306/project2";
    private static final String username = "myapp";
    private static final String password = "Tien2003";
    private static Connection connection;
    public static Connection getConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(host,username,password);
        }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return connection;
    }
    public static void closeConnection(){
        try{
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
