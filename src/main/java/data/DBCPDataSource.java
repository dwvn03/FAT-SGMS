package data;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBCPDataSource {
    private static BasicDataSource ds = new BasicDataSource();

    static {
        ds.setDriver(new SQLServerDriver());
        ds.setUrl("jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=FAT_SGMS;encrypt=true;trustServerCertificate=true;useUnicode=true;characterEncoding=UTF-8");
        ds.setUsername("admin");
        ds.setPassword("amdin");
        ds.setMinIdle(5);
        ds.setMaxIdle(20);
        ds.setMaxOpenPreparedStatements(100);
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    private DBCPDataSource(){ }
}
