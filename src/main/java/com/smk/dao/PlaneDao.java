package com.smk.dao;

import com.smk.model.Plane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

public class PlaneDao implements Dao<Plane, Integer>{
    Optional<Connection> conn;

    public PlaneDao() {
        conn = JdbcConnection.getConn();
    }

    @Override
    public Optional<Plane> get(int id) {
        return Optional.empty();
    }

    @Override
    public Collection<Plane> getAll() {
        Collection<Plane> planes = new LinkedList<>();
        conn.ifPresent(c -> {
            String query = "SELECT * FROM planes;";
            try {
                PreparedStatement ps = c.prepareStatement(query);
//                ResultSet rs = ps.
            }catch(SQLException e) {
                e.printStackTrace();
            }
        });

        return planes;
    }

    @Override
    public Optional<Integer> save(Plane plane) {
        return Optional.empty();
    }

    @Override
    public void update(Plane plane) {

    }

    @Override
    public void delete(Plane plane) {

    }

    @Override
    public Collection<Plane> search(String keyword) {
        return null;
    }
}
