package data;

import models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SessionDataSource extends DataSourceImpl<Session> {
    @Override
    public Session getModelFromResultSet(ResultSet rs) throws SQLException {
        return null;
    }

    public List<Session> getModelsFromResultSet(ResultSet rs) throws SQLException {
        List<Session> sessions = new ArrayList<>();

        while (rs.next()) {
            TimeSlot ts = TimeSlot.builder()
                    .id(rs.getInt("time_slot_id"))
                    .start_time(rs.getTime("start_time"))
                    .end_time(rs.getTime("end_time"))
                    .build();

            Course c = Course.builder()
                    .name(rs.getString("course_name"))
                    .build();

            Room r = Room.builder()
                    .name(rs.getString("room_name"))
                    .build();

            Group g = Group.builder()
                    .course(c)
                    .build();

            Status s = Status.builder()
                    .attended(rs.getBoolean("attended"))
                    .build();

            Session ses = Session.builder()
                    .date(rs.getDate("date"))
                    .timeSlot(ts)
                    .group(g)
                    .room(r)
                    .status(Collections.singletonList(s))
                    .build();

            sessions.add(ses);
        }

        return sessions;
    }

    @Override
    public void insert(Session model) {

    }

    @Override
    public void update(Session model) {

    }

    @Override
    public void delete(Session model) {

    }

    @Override
    public Session get(int id) {
        return null;
    }

    public List<Session> getSchedule(int studentId, Date from, Date to) {
        String sql =
            """
            SELECT ses.date, c.name as course_name, time_slot_id, t.start_time, t.end_time, r.name as room_name, s.attended
            FROM [Session] ses
            INNER JOIN [Time_slot] t ON ses.time_slot_id = t.id
            INNER JOIN [Group] g ON ses.group_id = g.id
            INNER JOIN [Course] c ON g.course_id = c.id
            INNER JOIN [Room] r ON ses.room_id = r.id
            INNER JOIN [User] i ON ses.instructor_id = i.id
            INNER JOIN [Status] s ON ses.id = s.session_id
            WHERE s.student_id = ? AND ses.date BETWEEN ? AND ?
            """;

        try (Connection connection = this.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, studentId);
            statement.setDate(2, from);
            statement.setDate(3, to);
            ResultSet rs = statement.executeQuery();

            return getModelsFromResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Session> all() {
        return null;
    }
}
