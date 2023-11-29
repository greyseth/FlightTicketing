package com.smk.dao;

import com.smk.model.Location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

public class LocationDao implements Dao<Location, Integer> {
    private final Optional<Connection> conn;

    public LocationDao() {
        conn = JdbcConnection.getConn();
    }

    @Override
    public Optional<Location> get(int id) {
        return null;
    }

    @Override
    public Collection<Location> getAll() {
        Collection<Location> returnCol = new LinkedList<>();
        String query = "SELECT * FROM location";
        conn.ifPresent(c -> {
            try {
                PreparedStatement ps = c.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while(rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");

                    Location newLoc = new Location();
                    newLoc.setId(id);
                    newLoc.setName(name);
                    returnCol.add(newLoc);
                }
            }catch(SQLException e) {
                e.printStackTrace();
            }
        });

        return returnCol;
    }

    @Override
    public Optional<Integer> save(Location location) {
        return null;
    }

    @Override
    public void update(Location location) {

    }

    @Override
    public void delete(Location location) {

    }

    @Override
    public Collection<Location> search(String keyword) {
        return null;
    }
}
