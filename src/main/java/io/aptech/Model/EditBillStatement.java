package io.aptech.Model;

import io.aptech.Entity.Bills;
import io.aptech.Entity.Events;
import io.aptech.Enum.StatusEvent;
import io.aptech.Generic.DAORepository;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

public class EditBillStatement implements DAORepository<Bills> {
    private static Connection connection = MySQLConnection.getConnection();
    @Override
    public void insert(Bills events) {

    }

    @Override
    public void update(Bills events) {
        try{
            String sql = "Update tbl_bills set e_name = ? " +
                    ",e_status = ? ,e_start_date = ? ,e_end_date = ? ,e_spent = ?" +
                    " WHERE id = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, events.getName());
            // Check status
            Calendar c = Calendar.getInstance();
            String[] info = String.valueOf(events.getEndDate()).split("-");
            if(Integer.parseInt(info[0]) < c.get(Calendar.YEAR)){
                pst.setString(2, String.valueOf(StatusEvent.Finished));
            } else if (Integer.parseInt(info[0]) == c.get(Calendar.YEAR)) {
                if(Integer.parseInt(info[1]) < c.get(Calendar.MONTH)){
                    pst.setString(2,String.valueOf(StatusEvent.Finished));
                }else if (Integer.parseInt(info[1]) == c.get(Calendar.MONTH)){
                    if(Integer.parseInt(info[2]) < c.get(Calendar.DATE) || Integer.parseInt(info[2]) == c.get(Calendar.DATE)){
                        pst.setString(2,String.valueOf(StatusEvent.Finished));
                    }else {
                        pst.setString(2,String.valueOf(StatusEvent.Running));
                    }
                }else{
                    pst.setString(2,String.valueOf(StatusEvent.Running));
                }
            }else {
                pst.setString(2,String.valueOf(StatusEvent.Running));
            }
            pst.setDate(3, events.getStartDate());
            pst.setDate(4, events.getEndDate());
            pst.setInt(5,events.getSpent());
            pst.setInt(6,events.getId());
            pst.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Bills getById(int id) {
        return null;
    }

    @Override
    public void delete(Bills events) {
        try{
            String sql = "DELETE FROM tbl_bills WHERE id = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, events.getId());
            pst.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public ObservableList<Bills> getAll() {
        return null;
    }
}
