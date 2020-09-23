/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

/**
 *
 * @author majogrance
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Bd {

    private static final String url = "jdbc:postgresql://127.0.0.1:5432/call_system";
    private static final String user = "postgres";
    private static final String password = "postgres";
 
    /**
     * @return objeto Connection 
     */
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    } 
}
