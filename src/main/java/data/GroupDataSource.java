package data;

import models.Group;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupDataSource extends DataSourceImpl<Group> {

    @Override
    public Group getModelFromResultSet(ResultSet rs) throws SQLException {
        return null;
    }

    public List<Group> getModelsFromResultSet(ResultSet rs) throws SQLException {
        List<Group> groups = new ArrayList<>();

        while (rs.next()) {
            Group group = Group.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .build();

            groups.add(group);
        }

        return groups;
    }

    @Override
    public void insert(Group model) {

    }

    @Override
    public void update(Group model) {

    }

    @Override
    public void delete(Group model) {

    }

    @Override
    public Group get(int id) {
        return null;
    }

    public List<Group> getGroupsByStudentId(int studentId) {
        String sql =
            """
            SELECT g.* FROM [Group] g
            INNER JOIN [Student_group] sg ON g.id = sg.group_id
            WHERE sg.student_id = ?
            """;

        try (Connection connection = this.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, studentId);
            ResultSet rs = statement.executeQuery();

            return getModelsFromResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Group> getGroupsByInstructorId(int instructorId) {
        String sql =
                """
                SELECT DISTINCT g.*
                FROM [Session] ses
                INNER JOIN [Group] g ON ses.group_id = g.id
                INNER JOIN [Status] s ON ses.id = s.session_id
                INNER JOIN [USER] i ON i.id = ses.instructor_id
                WHERE i.id = ?
                """;

        try (Connection connection = this.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, instructorId);
            ResultSet rs = statement.executeQuery();

            return getModelsFromResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Group> all() {
        return null;
    }
}
