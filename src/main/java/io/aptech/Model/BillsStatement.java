package io.aptech.Model;

import io.aptech.Entity.Bills;
import io.aptech.Generic.DAORepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BillsStatement implements DAORepository<Bills> {
    private static final Connection connection = MySQLConnection.getConnection();
    public void create(){
        try{
            String sql = "CREATE TABLE IF NOT EXISTS tbl_bills(" +
                    "id int PRIMARY KEY AUTO_INCREMENT" +
                    ",e_name varchar(255) NOT NULL" +
                    ",e_status varchar(20) NOT NULL " +
                    ",e_start_date date NOT NULL" +
                    ",e_end_date date NOT NULL" +
                    ",e_spent double NOT NULL)";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @Override
    public void insert(Bills bills) {

    }

    @Override
    public void update(Bills bills) {

    }

    @Override
    public Bills getById(int id) {
        return null;
    }

    @Override
    public void delete(Bills bills) {

    }

    @Override
    public ObservableList<Bills> getAll() {
        ObservableList<Bills> bills = FXCollections.observableArrayList();
        try {
            String sql = " select * from tbl_bills where 1 ";
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs =  pst.executeQuery();
            while(rs.next()){
                Bills bill = new Bills();
                bill.setId(rs.getInt("id"));
                bill.setSpent(rs.getInt("e_spent"));
                bill.setName(rs.getString("e_name"));
                bill.setStartDate(rs.getDate("e_start_date"));
                bill.setEndDate(rs.getDate("e_end_date"));
                bills.add(bill);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bills;
    }
    public ObservableList<Bills> getRunning() {
        ObservableList<Bills> bills = FXCollections.observableArrayList();
        try {
            String sql = " select * from tbl_bills where e_status LIKE 'Running' ";
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs =  pst.executeQuery();
            while(rs.next()){
                Bills bill = new Bills();
                bill.setId(rs.getInt("id"));
                bill.setSpent(rs.getInt("e_spent"));
                bill.setName(rs.getString("e_name"));
                bill.setStartDate(rs.getDate("e_start_date"));
                bill.setEndDate(rs.getDate("e_end_date"));
                bills.add(bill);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bills;
    }
    public ObservableList<Bills> getFinished() {
        ObservableList<Bills> bills = FXCollections.observableArrayList();
        try {
            String sql = " select * from tbl_bills where e_status LIKE 'Finished'";
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs =  pst.executeQuery();
            while(rs.next()){
                Bills bill = new Bills();
                bill.setId(rs.getInt("id"));
                bill.setSpent(rs.getInt("e_spent"));
                bill.setName(rs.getString("e_name"));
                bill.setStartDate(rs.getDate("e_start_date"));
                bill.setEndDate(rs.getDate("e_end_date"));
                bills.add(bill);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bills;
    }
}
