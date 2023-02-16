package io.aptech.Model;

import io.aptech.Entity.Events;
import io.aptech.Generic.DAORepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class EventsStatement implements DAORepository<Events> {
    private static final Connection connection = MySQLConnection.getConnection();
    public void create(){
        try{
            String sql = "CREATE TABLE IF NOT EXISTS tbl_events(" +
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
    public void insert(Events events) {

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
        ObservableList<Events> events = FXCollections.observableArrayList();
        try {
            String sql = " select * from tbl_events where 1 ";
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs =  pst.executeQuery();
            while(rs.next()){
                Events event = new Events();
                event.setId(rs.getInt("id"));
                event.setSpent(rs.getInt("e_spent"));
                event.setName(rs.getString("e_name"));
                event.setStartDate(rs.getDate("e_start_date"));
                event.setEndDate(rs.getDate("e_end_date"));
                events.add(event);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return events;
    }
    public ObservableList<Events> getRunning() {
        ObservableList<Events> events = FXCollections.observableArrayList();
        try {
            String sql = " select * from tbl_events where e_status LIKE 'Running' ";
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs =  pst.executeQuery();
            while(rs.next()){
                Events event = new Events();
                event.setId(rs.getInt("id"));
                event.setSpent(rs.getInt("e_spent"));
                event.setName(rs.getString("e_name"));
                event.setStartDate(rs.getDate("e_start_date"));
                event.setEndDate(rs.getDate("e_end_date"));
                events.add(event);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return events;
    }
    public ObservableList<Events> getFinished() {
        ObservableList<Events> events = FXCollections.observableArrayList();
        try {
            String sql = " select * from tbl_events where e_status LIKE 'Finished'";
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs =  pst.executeQuery();
            while(rs.next()){
                Events event = new Events();
                event.setId(rs.getInt("id"));
                event.setSpent(rs.getInt("e_spent"));
                event.setName(rs.getString("e_name"));
                event.setStartDate(rs.getDate("e_start_date"));
                event.setEndDate(rs.getDate("e_end_date"));
                events.add(event);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return events;
    }
}
