package com.smk.dao;

import com.smk.model.Schedule;
import com.smk.model.dto.ScheduleDTO;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

public class ScheduleDao implements Dao<Schedule, Integer> {
    private final Optional<Connection> conn;

    public ScheduleDao() {
        conn = JdbcConnection.getConn();
    }

    @Override
    public Optional<Schedule> get(int id) {
        return Optional.empty();
    }

    @Override
    public Collection<Schedule> getAll() {
        return null;
    }

    @Override
    public Optional<Integer> save(Schedule schedule) {
        return Optional.empty();
    }

    @Override
    public void update(Schedule schedule) {

    }

    @Override
    public void delete(Schedule schedule) {

    }

    @Override
    public Collection<Schedule> search(String keyword) {
        return null;
    }

    public Collection<ScheduleDTO> searchSchedule(long departureId, long arrivalId, Date departureDate) {
        Collection<ScheduleDTO> schedules = new LinkedList<>();
        String query = "SELECT schedule.*, location_departure.name departure," +
                " location_arrival.name arrival " +
                "FROM schedule inner join location location_departure " +
                "ON schedule.departure_id = location_departure.id" +
                "INNER JOIN location location_arrival ON schedule.arrival_id = location_arrival.id" +
                "WHERE departure_id = ? AND arrival_id = ? AND departure_date::timestamptz::date = ?;";
        conn.ifPresent(c -> {
            try {
                PreparedStatement ps = c.prepareStatement(query);
                ps.setLong(1, departureId);
                ps.setLong(2, arrivalId);
                ps.setDate(3, departureDate);

                ResultSet rs = ps.executeQuery();
                while(rs.next()) {
                    ScheduleDTO result = new ScheduleDTO();
                    result.setId(rs.getInt("id"));
                    result.setDepartureLocation(rs.getString("departure"));
                    result.setArrivalLocation(rs.getString("arrival"));
                    result.setDepartureDate(rs.getDate("departure_date"));
                    result.setFlightNumber(rs.getString("flight_number"));

                    schedules.add(result);
                }
            }catch(SQLException e) {
                e.printStackTrace();
            }
        });

        return schedules;
    }
}
