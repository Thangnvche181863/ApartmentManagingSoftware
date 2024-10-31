/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author WuanTun
 */
public class DBContext {
    private static final Logger LOGGER = Logger.getLogger(DBContext.class.getName());

    public static Connection connection;
    private static final String url = "jdbc:sqlserver://localhost:1433;databaseName=AptMan";
    private static final String username = "sa";
    private static final String password = "123456";

    public DBContext(){

    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if (connection == null || connection.isClosed()) {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, username, password);
        }
        return connection;
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                LOGGER.log(Level.INFO, "Connection closed: {0}", conn);
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Error closing connection", e);
            }
        }
    }
}
