/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cigist.framework.conn;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author user
 */
public class CigistConnection {

    private Connection conn = null;

    public Connection getConnection() throws FileNotFoundException, IOException {

//        String JDBC_URL = "jdbc:mysql://localhost:3306/metapro";
//        String username = "root";
//        String password = "root";
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            conn = DriverManager.getConnection(JDBC_URL, username, password);
//        } catch (SQLException e) {
//            System.out.println("Erorr Connection Please Check Your Database !");
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(CigistConnection.class.getName()).log(Level.SEVERE, null, ex);
//        }
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return conn;
        }
//        103.200.7.141
// newe server
// CREATE USER 'metapro'@'103.77.78.131' IDENTIFIED BY 'metapro123
        try {
            conn = DriverManager.getConnection("jdbc:mysql://202.158.20.139:3306/metapro", "metapro", "metapro");
             // conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/metapro?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root");
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("Failed to make connection!");
            return conn;
        }

//          EIHT PROPERITES
//        Properties props = new Properties();
//        FileInputStream fis = null;
//        Connection conn = null;
//        try {
//            fis = new FileInputStream("config.properties");
//            props.load(fis);
//
//            // load the Driver Class
//            Class.forName(props.getProperty("DB_DRIVER_CLASS"));
//
//            // create the connection now
//            conn = DriverManager.getConnection(props.getProperty("DB_URL"),
//                    props.getProperty("DB_USERNAME"),
//                    props.getProperty("DB_PASSWORD"));
//        } catch (IOException | ClassNotFoundException | SQLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        return conn;
    }

    public void closeConnection() throws SQLException {
        //close DB connection here
        conn.close();
    }
}
