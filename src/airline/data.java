
package airline;

import java.sql.*;

public class data {
    
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost/airline";
    static final String USER = "root";
    static final String PASS = "";
    Connection conn;
    Statement stmt;
    
    String getAvailableSeats(int flightNumber) {
        int id;
        int seats;
        String result = "";
        try{
               Class.forName(JDBC_DRIVER);
               conn = DriverManager.getConnection(DB_URL, USER, PASS);
               stmt = conn.createStatement();
               String sql = "SELECT * from flights where flight_Id = "+flightNumber;
               ResultSet rs = stmt.executeQuery(sql);
                
               if(rs.next()){
                   id = rs.getInt("flight_id");
                   seats = rs.getInt("seatsAvailable");
                   result = "Success. Seats Available for Flight Id " + id + " are "+seats+".";
               } else {
                   result = "Failure. No Such Flight.";
               }  
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        return result;
    }
    void insertRecord(int flightId, String Username, int seat) {
        try {
            stmt = conn.createStatement();
            String sql = "INSERT INTO reservations " +
                         "VALUES (" + flightId + ",'" +Username+ "',"+ seat+")";
            stmt.executeUpdate(sql);
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }
    int checkAvailaibility(int flightId) {
        int seat = -1;
        int seatsAvailable = 0;
        try{
           String sql = "SELECT * FROM flights where flight_id = "+flightId;
           ResultSet rs = stmt.executeQuery(sql);
           if(rs.next()) {
               seatsAvailable = rs.getInt("seatsAvailable");
               seat = rs.getInt("numberOfSeats");
           } else {
               return seat;
           }

           if(seatsAvailable > 0) {
               seat = seat - seatsAvailable + 1;
           } else {
               seat = -1;
           }
           rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return seat;
    }
    void updateSeats(int flightId) {
        int seats = 0;
        try {
            String sql = "SELECT * FROM flights where flight_id = "+flightId;
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()) {
               seats = rs.getInt("seatsAvailable");
            }
            seats = seats - 1;
            stmt = conn.createStatement();
            sql = "UPDATE flights " + "SET seatsAvailable = "+seats+" WHERE flight_id ="+String.valueOf(flightId);
            stmt.executeUpdate(sql);
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }
    String bookTicket(int flightId, String username) {
        String result = "";
        try{
           Class.forName(JDBC_DRIVER);
           conn = DriverManager.getConnection(DB_URL, USER, PASS);
           stmt = conn.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int seat = checkAvailaibility(flightId);
        if(seat != -1) {
            insertRecord(flightId, username, seat);
            updateSeats(flightId);
            result = "Success. Username: " + username + " has booked Flight Id "+ flightId +" with seat number " + seat;
        } else {
            result = "Failure. Tickets Full";
        }
        return result;
    }
    String addFlight(int flightId, int numberOfSeats) {
        String result = "";
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "SELECT * from flights where flight_Id = "+flightId;
            ResultSet rs = stmt.executeQuery(sql);

            if(rs.next()){
                result = "Failure. Flight Already Exists.";
            } else {
                sql = "INSERT INTO flights VALUES("+flightId+","+numberOfSeats+","+numberOfSeats+")";
                stmt.executeUpdate(sql);
                result = "Success. Flight Added.";
            }
         } catch (Exception e) {
             e.printStackTrace();
         }
        return result;
    }
}
