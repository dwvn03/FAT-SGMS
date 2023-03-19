package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class DataSourceImpl<T> {
    public DataSourceImpl() {
    }

    public Connection getConnection() throws SQLException {
        return DBCPDataSource.getConnection();
    }

    public abstract T getModelFromResultSet(ResultSet rs) throws SQLException;

    public abstract void insert(T model);

    public abstract void update(T model);

    public abstract void delete(T model);

    public abstract T get(int id);

    public abstract List<T> all() ;
}
