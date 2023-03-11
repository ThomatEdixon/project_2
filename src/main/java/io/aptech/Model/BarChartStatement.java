package io.aptech.Model;

import javafx.scene.chart.BarChart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class BarChartStatement {
    private Connection connection = MySQLConnection.getConnection();

    public int getDataToBarChart() {
        ResultSet rs = null;
        int total = 0;
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        String date = year + "-0" + month;

        try {
            String sql = "SELECT SUM(amount) as total from tbl_transactions where transaction_date LIKE ? ";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, "%" + date + "%");
            rs=pst.executeQuery();
            while (rs.next()){
                total += rs.getInt("total");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return total;
    }
    public int getDataToBarChart2() {
        ResultSet rs = null;
        int total = 0;
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        String date = year + "-0" + month;

        try {
            String sql = "SELECT SUM(amount) as total from tbl_transactions where transaction_date LIKE ? ";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, "%" + date + "%");
            rs=pst.executeQuery();
            while (rs.next()){
                total += rs.getInt("total");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return total;
    }

    public int getLastWeekDataToBarChart() {
        ResultSet rs = null;
        int total = 0;
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) +1;
        int today = calendar.get(Calendar.DATE);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        String date = "";

        int startDayOfWeek = today-day-7;
        if(startDayOfWeek < 0) {
            month -= 1;
        }

        int numberOfMonth = 0;
        if(month==1|| month==3|| month==5|| month==7|| month==8|| month==10|| month==12) {
            numberOfMonth= 31;
        } else if (month==4|| month==6|| month==9|| month==11) {
            numberOfMonth= 30;
        }else {
            numberOfMonth= 28;
        }
        for(int i = 0; i < 7; i++) {
            if(startDayOfWeek+i <= 0) {
                if(startDayOfWeek+i+numberOfMonth > numberOfMonth) {
                    date = year + "-0" + (month+1) + "-0" + (startDayOfWeek+i-numberOfMonth);
                }else {
                    date = year + "-0" + month + "-" + (startDayOfWeek+i+numberOfMonth);
                }
            }else if(startDayOfWeek < 10){
                date = year + "-0" + (month+1) + "-0" + (startDayOfWeek+i);
            }else {
                date = year + "-0" + (month+1) + "-" + (startDayOfWeek+i);
            }

            try {
                String sql = "SELECT amount from tbl_transactions where transaction_date LIKE ? ";
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setString(1, "%" + date + "%");
                rs=pst.executeQuery();
                while (rs.next()){
                    total += rs.getInt("amount");
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        return total;
    }

    public int getThisWeekDataToBarChart() {
        ResultSet rs = null;
        int total = 0;
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int today = calendar.get(Calendar.DATE);
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        for(int i = 0; i < 7; i++) {
            int startDayOfWeek = today-day;
            String date = "";
            if(startDayOfWeek+i < 10) {
                date = year + "-0" + month + "-0" + (startDayOfWeek+i);
            }else {
                date = year + "-0" + month + "-" + (startDayOfWeek+i);
            }


            try {
                String sql = "SELECT amount from tbl_transactions where transaction_date LIKE ? ";
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setString(1, "%" + date + "%");
                rs=pst.executeQuery();

                while (rs.next()){
                    total += rs.getInt("amount");
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        return total;
    }
}
