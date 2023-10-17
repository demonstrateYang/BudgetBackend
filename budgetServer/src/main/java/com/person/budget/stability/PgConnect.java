package com.person.budget.stability;

import org.postgresql.ds.PGConnectionPoolDataSource;
import org.postgresql.ds.PGPooledConnection;
import org.springframework.stereotype.Component;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
@Component
public class PgConnect {

    PGConnectionPoolDataSource pgDataSource;

    public PgConnect() throws SQLException, NamingException {

        PGConnectionPoolDataSource dataSource = new PGConnectionPoolDataSource();
//        dataSource.setDatabaseName("Personal");
//        dataSource.setUser("postgres");
//        dataSource.setPassword("secret");
//        dataSource.setServerNames(new String[]{"127.0.0.1"});
//        dataSource.setPortNumbers(new int[]{18300});
        dataSource.setUrl("jdbc:postgresql://8.134.49.6:18300/Personal?user=postgres&password=secret");
        pgDataSource = dataSource;
//        new InitialContext().rebind("DataSource", pgDataSource);
    }

    public  Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            if (pgDataSource.getConnection() == null){
                conn = pgDataSource.getPooledConnection().getConnection();
            }else{
                conn = pgDataSource.getConnection();
            }
            // use connection
        } catch (SQLException e) {
            e.printStackTrace();
            // log error
        }
        return conn;
    }
}
