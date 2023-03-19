package data;

import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDataSource extends DataSourceImpl<User> {
    @Override
    public User getModelFromResultSet(ResultSet rs) throws SQLException {
        return User.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .email(rs.getString("email"))
                    .avatar(rs.getString("avatar"))
                    .gender(rs.getString("gender"))
                    .dob(rs.getDate("dob"))
                    .role(rs.getString("role"))
                    .build();

    }

    @Override
    public void insert(User model) {

    }

    @Override
    public void update(User model) {

    }

    @Override
    public void delete(User model) {

    }

    @Override
    public User get(int id) {
        String sql = "SELECT * FROM [User] WHERE id = ?";

        try (Connection connection = this.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            System.out.println();
            if (rs.next())
                return getModelFromResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public User get(String email) {
        String sql = "SELECT * FROM [User] WHERE email = ?";

        try (Connection connection = this.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();

            if (rs.next())
                return getModelFromResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<User> all() {
        return null;
    }
}
