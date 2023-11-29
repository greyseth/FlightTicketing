package com.smk.dao;

import com.smk.model.Booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

public class BookingDao implements Dao<Booking, Integer>{
    private final Optional<Connection> conn;

    public BookingDao() {
        conn = JdbcConnection.getConn();
    }

    @Override
    public Optional<Booking> get(int id) {
        return Optional.empty();
    }

    @Override
    public Collection<Booking> getAll() {
        Collection<Booking> books = new LinkedList<>();
        conn.ifPresent(c -> {
            String query = "SELECT * FROM booking;";
            try {
                PreparedStatement ps = c.prepareStatement(query);

                ResultSet rs = ps.executeQuery();
                while(rs.next()) {
                    Booking result = new Booking();
                    result.setId(rs.getInt("id"));
                    result.setScheduleId(rs.getInt("schedule_id"));
                    result.setName(rs.getString("name"));
                    result.setPrice(rs.getDouble("price"));
                    result.setPesawatId(rs.getInt("pesawat_id"));

                    books.add(result);
                }
            }catch(SQLException e) {
                e.printStackTrace();
            }
        });

        return books;
    }

    @Override
    public Optional<Integer> save(Booking booking) {
        return Optional.empty();
    }

    @Override
    public void update(Booking booking) {

    }

    @Override
    public void delete(Booking booking) {

    }

    @Override
    public Collection<Booking> search(String keyword) {
        Collection<Booking> books = new LinkedList<>();
        conn.ifPresent(c -> {
            String query = "SELECT * FROM booking WHERE name LIKE" +
                    "CONCAT('%', ?, '%');";
            try {
                PreparedStatement ps = c.prepareStatement(query);
                ps.setString(1, keyword);

                ResultSet rs = ps.executeQuery();
                while(rs.next()) {
                    Booking result = new Booking();
                    result.setId(rs.getInt("id"));
                    result.setScheduleId(rs.getInt("schedule_id"));
                    result.setName(rs.getString("name"));
                    result.setPrice(rs.getDouble("price"));
                    result.setPesawatId(rs.getInt("pesawat_id"));

                    books.add(result);
                }
            }catch(SQLException e) {
                e.printStackTrace();
            }
        });

        return books;
    }
}
