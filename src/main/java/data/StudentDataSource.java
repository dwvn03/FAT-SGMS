package data;

import models.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    @Override
    public List<Student> all() {
        return null;
    }
}
