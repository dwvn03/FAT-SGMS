package data;

import models.Student;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDataSource extends DataSourceImpl<Student> {
    @Override
    public Student getModelFromResultSet(ResultSet rs) throws SQLException {
        return Student.builder()
                .id(rs.getInt("id"))
                .dept_code(rs.getString("dept_code"))
                .user(null)
                .build();
    }

    public List<Student> getModelsFromResultSet(ResultSet rs) throws SQLException {
        List<Student> students = new ArrayList<>();

        while (rs.next()) {
            User u = User.builder()
                    .id(rs.getInt("user_id"))
                    .name(rs.getString("name"))
                    .email(rs.getString("email"))
                    .avatar(rs.getString("avatar"))
                    .gender(rs.getString("gender"))
                    .dob(rs.getDate("dob"))
                    .role(rs.getString("role"))
                    .build();

            Student student = Student.builder()
                    .id(rs.getInt("id"))
                    .dept_code(rs.getString("dept_code"))
                    .user(u)
                    .build();

            students.add(student);
        }

        return students;
    }

    @Override
    public void insert(Student model) {

    }

    @Override
    public void update(Student model) {

    }

    @Override
    public void delete(Student model) {

    }

    @Override
    public Student get(int id) {
        return null;
    }

    public Student getStudentByUserId(int userId) {
        String sql = "SELECT * FROM Student WHERE user_id = ?";

        try (Connection connection = this.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();

            if (rs.next())
                return getModelFromResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Student> getStudentsByGroupId(int groupId) {
        String sql =
            """
            SELECT st.*, u.* FROM [Student] st
            INNER JOIN [User] u ON u.id = st.user_id
            INNER JOIN [Student_group] sg ON sg.student_id = st.id
            WHERE sg.group_id = ?
            """;

        try (Connection connection = this.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, groupId);
            ResultSet rs = statement.executeQuery();

            return getModelsFromResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Student> all() {
        return null;
    }
}
