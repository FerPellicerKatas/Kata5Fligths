package main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Kata5P1 {

    public static void main(String[] args) throws IOException, SQLException {
        Kata5P1 app = new Kata5P1();
        app.selectAll();
    }
    
    private Connection connect() {
        String url = "jdbc:sqlite:flights.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
    public void selectAll(){
        String sql = "SELECT * FROM flights";
        try (Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
                while (rs.next()) {
                    System.out.println(rs.getInt("DAY_OF_WEEK") + "\t" +
                                       rs.getString("DEP_TIME") + "\t" +
                                       rs.getString("DEP_DELAY") + "\t" +
                                       rs.getString("ARR_TIME") + "\t" +
                                       rs.getString("ARR_DELAY") + "\t" +
                                       rs.getString("CANCELLED") + "\t" +
                                       rs.getString("DIVERTED") + "\t" +
                                       rs.getString("AIR_TIME") + "\t" +
                                       rs.getString("DISTANCE"));
                }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}