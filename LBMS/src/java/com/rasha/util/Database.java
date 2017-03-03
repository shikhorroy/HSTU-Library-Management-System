/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rasha.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author LittleBird
 */
public class Database {

    static final String DATABASE = "lbms";

    private static String URL = "jdbc:mysql://localhost:3306/" + DATABASE;
    private static String USER = "root";
    private static String PASS = "1";

    public static Connection getConnection() {

        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e);
        }
        return connection;
    }
}
