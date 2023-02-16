package io.aptech.Model;

import io.aptech.Entity.Events;
import io.aptech.Enum.StatusEvent;
import io.aptech.Generic.DAORepository;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;

public class AddEventStatement implements DAORepository<Events> {
    private static final Connection connection = MySQLConnection.getConnection();
    @Override
    public void insert(Events events) {
        try{
            String sql = "INSERT INTO tbl_events(e_name,e_status,e_start_date,e_end_date,e_spent)"+
                    " VALUES (?,?,?,?,?)";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1,events.getName());
            // Check status
            Calendar c = Calendar.getInstance();
            String[] info = String.valueOf(events.getSpent()).split("-");
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
            pst.setDate(4, (Date) events.getEndDate());
            pst.setString(5, String.valueOf(events.getSpent()));
            pst.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Events events) {

    }

    @Override
    public Events getById(int id) {
        return null;
    }

    @Override
    public void delete(Events events) {

    }

    @Override
    public ObservableList<Events> getAll() {
        return null;
    }
}
