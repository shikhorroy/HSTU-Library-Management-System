/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rasha.dao;

import com.rasha.model.Books;
import com.rasha.model.Return;
import com.rasha.util.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author LittleBird
 */
public class ReturnDao {

    final String TABLE = "returns";
    private Connection connection = null;

    public ReturnDao() {
        connection = Database.getConnection();
    }

    public boolean addReturn(Return returnObj) {

        String sql = "INSERT into " + TABLE + "(id, `student id`, `book id`, `return date`, fine) "
                + "VALUES(?, ?, ?, ?, ?)";
        try {
            BorrowDao dao = new BorrowDao();

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, returnObj.getId());
            ps.setString(2, returnObj.getStudentId());
            ps.setString(3, returnObj.getBookId());
            ps.setDate(4, returnObj.getReturnDate());
            ps.setInt(5, returnObj.getFine());

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e);
            return false;
        }
        return true;
    }

    public void closeConnection() {

        try {
            connection.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
