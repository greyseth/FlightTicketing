package com.smk.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

public class JdbcConnection {
    private static Optional<Connection> conn = Optional.empty();

    public static Optional<Connection> getConn() {
        if(conn.isEmpty()) {
            String url = "jdbc:postgresql://localhost:5432/FlightBooking";
            String user = "FlightBooking";
            String pw = "Fl19ht800k1n9";

            try {
                Class.forName("org.postgresql.Driver");
                conn = Optional.ofNullable(DriverManager.getConnection(url, user, pw));
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }
}
