package data;

import models.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatusDataSource extends DataSourceImpl<Status> {
    @Override
    public Status getModelFromResultSet(ResultSet rs) throws SQLException {
        return null;
    }

    public List<Status> getModelsFromResultSet(ResultSet rs) throws SQLException {
        ArrayList<Status> statuses = new ArrayList<>();

        while (rs.next()) {
            User u = User.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .email(rs.getString("email"))
                    .avatar(rs.getString("avatar"))
                    .gender(rs.getString("gender"))
                    .dob(rs.getDate("dob"))
                    .build();

            Student st = Student.builder()
                    .id(rs.getInt("student_id"))
                    .user(u)
                    .build();

            Status s = Status.builder()
                    .student(st)
                    .attended(rs.getBoolean("attended"))
                    .build();

            statuses.add(s);
        }

        return statuses;
    }

    @Override
    public void insert(Status model) {

    }

    @Override
    public void update(Status model) {

    }

    public boolean updateStatuses(int sessionId, String[] studentIds, String[] attended) {
        String sql =
                """
                UPDATE [Status]
                SET attended = ?
                WHERE session_id = ? AND student_id = ?
                """;

        try (Connection connection = this.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);

            for (int i = 0; i < studentIds.length; i++) {
                statement.setBoolean(1, attended[i].equals("1"));
                statement.setInt(2, sessionId);
                statement.setInt(3, Integer.parseInt(studentIds[i]));
                statement.addBatch();
            }

            statement.executeBatch();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public void delete(Status model) {

    }

    @Override
    public Status get(int id) {
        return null;
    }

    public List<Status> getStatusesBySessionId(long sessionId) {
        String sql =
                """
                SELECT s.*, u.*
                FROM [Status] s
                INNER JOIN [Student] st ON st.id = s.student_id
                INNER JOIN [User] u ON u.id = st.user_id
                WHERE s.session_id = ?
                """;

        try (Connection connection = this.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, sessionId);
            ResultSet rs = statement.executeQuery();

            return getModelsFromResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Status> getStatusesFromStudentIdAndGroupId(int studentId, int groupId) {
        String sql =
                """
                SELECT ses.date, g.name as group_name, time_slot_id, t.start_time, t.end_time, s.attended, u.name as student_name, u.email, i.name as instructor_name
                FROM [Session] ses
                INNER JOIN [Time_slot] t ON ses.time_slot_id = t.id
                INNER JOIN [Group] g ON ses.group_id = g.id
                INNER JOIN [Status] s ON ses.id = s.session_id
                INNER JOIN [Student] st ON s.student_id = st.id
                INNER JOIN [User] u ON u.id = st.user_id
                INNER JOIN [USER] i ON i.id = ses.instructor_id
                WHERE s.student_id = ? AND ses.group_id = ?
                """;

        try (Connection connection = this.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, studentId);
            statement.setInt(2, groupId);
            ResultSet rs = statement.executeQuery();

            List<Status> statuses = new ArrayList<>();

            while (rs.next()) {
                TimeSlot ts = TimeSlot.builder()
                        .id(rs.getInt("time_slot_id"))
                        .start_time(rs.getTime("start_time"))
                        .end_time(rs.getTime("end_time"))
                        .build();

                User student_user = User.builder()
                        .name(rs.getString("student_name"))
                        .email(rs.getString("email"))
                        .build();

                Student st = Student.builder()
                        .user(student_user)
                        .build();

                User instructor = User.builder()
                        .name(rs.getString("instructor_name"))
                        .build();

                Group g = Group.builder()
                        .name(rs.getString("group_name"))
                        .build();

                Session ses = Session.builder()
                        .date(rs.getDate("date"))
                        .timeSlot(ts)
                        .group(g)
                        .instructor(instructor)
                        .build();

                Status s = Status.builder()
                        .attended(rs.getBoolean("attended"))
                        .session(ses)
                        .student(st)
                        .build();

                statuses.add(s);
            }

            return statuses;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Status> all() {
        return null;
    }
}
