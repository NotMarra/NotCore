package com.notmarra.notcore.utils;

import com.notmarra.notcore.NotCore;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Database {
    private HikariDataSource hikari;

    public static Database database = null;

    public Database(String host, String port, String database, String username, String password, String poolname) {
        hikari = new HikariDataSource();
        hikari.setJdbcUrl("jdbc:mysql://" + host + ":" + port + "/" + database);
        hikari.setUsername(username);
        hikari.setPassword(password);
        hikari.setDriverClassName("com.mysql.jdbc.Driver");
        hikari.setMaximumPoolSize(10);
        hikari.setConnectionTimeout(10000);
        hikari.setLeakDetectionThreshold(10000);
        hikari.setPoolName(poolname);
    }

    public static Database getInstance() {
        return database;
    }

    public HikariDataSource getHikari() {
        return hikari;
    }

    public void disconnectFromDB() {
        hikari.close();
    }

    public void intializeTable(String sql) {
        try {
            hikari.getConnection().createStatement().executeUpdate(sql);
        } catch (Exception e) {
            NotCore.getInstance().getLogger().info("Error creating table: " + e.getMessage());
        }
    }

    public String stmtDB(String sql, String... params) {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet result = null;

        try {
            connection = hikari.getConnection();
            stmt = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                stmt.setString(i + 1, params[i]);
            }
            stmt.execute();
            result = stmt.getResultSet();
            if (result != null && result.next()) {
                return result.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
