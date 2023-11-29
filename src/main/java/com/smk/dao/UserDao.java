package com.smk.dao;

import com.smk.model.User;

import javax.swing.text.html.Option;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

public class UserDao implements Dao<User, Integer>{
    final Optional<Connection> conn;

    public UserDao () {
        conn = JdbcConnection.getConn();
    }

    @Override
    public Optional<User> get(int id) {
        return Optional.empty();
    }

    @Override
    public Collection<User> getAll() {
        return null;
    }

    @Override
    public Optional<Integer> save(User user) {
        return Optional.empty();
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }

    @Override
    public Collection<User> search(String keyword) {
        return null;
    }

    public Optional<User> login(String username, String password) {
        return conn.flatMap(c -> {
            Optional<User> loginUser = Optional.empty();
            String query = "SELECT * FROM " +
                    "users WHERE username = ? AND password = ?;";

            try {
                PreparedStatement ps = c.prepareStatement(query);
                ps.setString(1, username);
                ps.setString(2, password);

                ResultSet rs = ps.executeQuery();
                while(rs.next()) {
                    User result = new User();
                    result.setId(rs.getInt("id"));
                    result.setUsername(rs.getString("username"));
                    result.setPassword(rs.getString("password"));
                    result.setLevel(rs.getInt("level"));
                }
            }catch(SQLException e) {
                e.printStackTrace();
            }

            return loginUser;
        });
    }
}
