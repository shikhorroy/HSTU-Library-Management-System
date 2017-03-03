/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rasha.dao;

import com.rasha.model.User;
import com.rasha.util.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LittleBird
 */
public class UsersDao {

    final String USER_TABLE = "users";
    private Connection connection = null;

    public UsersDao() {
        connection = Database.getConnection();
    }

    public void checkUser(User user) {
        String sql = "SELECT `user name` from " + USER_TABLE + " WHERE `user name` = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                updateUser(user);
            } else {
                addUser(user);
            }
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e);
        }
    }

    public void addUser(User user) {
        String sql = "INSERT into " + USER_TABLE + "(`user name`, password, email) VALUES(?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e);
        }
    }

    public void deleteUser(String userId) {
        String sql = "DELETE from " + USER_TABLE + " WHERE `user name` = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, userId);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e);
        }
    }

    public void updateUser(User user) {
        String sql = "UPDATE " + USER_TABLE + " SET password = ?, email = ? WHERE `user name` = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, user.getPassword());
            ps.setString(2, user.getEmail());
            ps.setString(4, user.getUsername());

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        String sql = "SELECT * from " + USER_TABLE;

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                User user = new User();

                user.setUsername(rs.getString(1));
                user.setPassword(rs.getString(2));
                user.setEmail(rs.getString(3));

                users.add(user);
            }
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e);
        }
        return users;
    }

    public User getUserById(String userId) {
        User user = new User();
        String sql = "SELECT * from " + USER_TABLE + " WHERE `user name` = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                user.setUsername(rs.getString(1));
                user.setPassword(rs.getString(2));
                user.setEmail(rs.getString(3));
            }

        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e);
        }

        return user;
    }

    public void closeConnection() {

        try {
            connection.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
