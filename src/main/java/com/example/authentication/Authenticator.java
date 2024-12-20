package com.example.authentication;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Authenticator {
    public static boolean authenticate(Credentials credentials) {
        if(credentials == null)
            return false;
        try (Connection conn = getDataSource().getConnection()) {
            String sql = "SELECT * FROM users  WHERE username=? AND password=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, credentials.getUsername());
            stmt.setString(2, credentials.getPassword());
            ResultSet resultSet = stmt.executeQuery();
            return resultSet.next();
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    private static DataSource getDataSource() throws SQLException {
        MysqlDataSource ds = new MysqlDataSource();
        ds.setServerName("mysql");
        ds.setPort(3306);
        ds.setDatabaseName("data");
        ds.setUser("root");
        ds.setPassword("123456");
        ds.setUseSSL(false);
        ds.setAllowPublicKeyRetrieval(true);

        return ds;
    }
}