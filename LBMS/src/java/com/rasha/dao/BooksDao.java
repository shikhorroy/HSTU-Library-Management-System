/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rasha.dao;

import com.rasha.model.Books;
import com.rasha.model.Students;
import com.rasha.util.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LittleBird
 */
public class BooksDao {

    final String TABLE = "books";
    private Connection connection = null;

    public BooksDao() {
        connection = Database.getConnection();
    }

    public boolean addBooks(Books book) {

        String sql = "INSERT into " + TABLE + "(isbn, `book name`, `author name`, category, `self no`) "
                + "VALUES(?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, book.getIsbn());
            ps.setString(2, book.getBookname());
            ps.setString(3, book.getAuthorname());
            ps.setString(4, book.getCategory());
            ps.setInt(5, book.getSelfno());

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e);
            return false;
        }
        return true;
    }

    public void deleteBook(String bookId) {
        String sql = "DELETE from " + TABLE + " WHERE isbn = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, bookId);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e);
        }
    }

    public List<Books> getAllBooks() {

        List<Books> books = new ArrayList<>();
        String sql = "SELECT * from " + TABLE;

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                Books book = new Books();

                book.setIsbn(rs.getString(1));
                book.setBookname(rs.getString(2));
                book.setAuthorname(rs.getString(3));
                book.setCategory(rs.getString(4));
                book.setSelfno(rs.getInt(5));

                books.add(book);
            }
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e);
        }

        return books;
    }

    public Books getBookById(String bookId) {

        Books book = new Books();
        String sql = "SELECT * from " + TABLE + " WHERE isbn = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, bookId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                book.setIsbn(rs.getString(1));
                book.setBookname(rs.getString(2));
                book.setAuthorname(rs.getString(3));
                book.setCategory(rs.getString(4));
                book.setSelfno(rs.getInt(5));
            }

        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e);
        }

        return book;
    }

    public boolean updateBooks(Books book) {
        String sql = "UPDATE " + TABLE
                + " SET isbn = ?, `book name` = ?, `author name` = ?, category = ?, `self no` = ? WHERE isbn = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, book.getIsbn());
            ps.setString(2, book.getBookname());
            ps.setString(3, book.getAuthorname());
            ps.setString(4, book.getCategory());
            ps.setInt(5, book.getSelfno());
            ps.setString(6, book.getIsbn());

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
        } catch (SQLException ex) {
            Logger.getLogger(BooksDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
