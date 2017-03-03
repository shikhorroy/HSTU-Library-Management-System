/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rasha.controller;

import com.rasha.dao.BooksDao;
import com.rasha.dao.BorrowDao;
import com.rasha.dao.StudentsDao;
import com.rasha.model.Books;
import com.rasha.model.Borrow;
import com.rasha.model.Students;
import java.io.IOException;
import java.io.PrintWriter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author LittleBird
 */
public class BorrowController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            if (request.getParameter("submit") != null) {
                String studentId = request.getParameter("studentid");
                String bookId = request.getParameter("bookid");

                BooksDao booksDao = new BooksDao();
                StudentsDao studentsDao = new StudentsDao();

                Books book = booksDao.getBookById(bookId);
                Students student = studentsDao.getStudentById(studentId);

                if (student.getId() == 0) {
                    out.println("Student Not Found!!!");
                } else {
                    if (book.getSelfno() == 0) {
                        out.println("Book Not Found!!!");
                    } else {
                        request.setAttribute("book", book);
                        request.setAttribute("student", student);

                        request.getRequestDispatcher("confirmborrow.jsp").forward(request, response);
                    }
                }
            } else if (request.getParameter("confirm") != null) {

                Borrow borrow = new Borrow();
                borrow.setStudentId(request.getParameter("studentid"));
                borrow.setBookId(request.getParameter("bookid"));

                String borrowdateStr = request.getParameter("borrowdate");
                String returndateStr = request.getParameter("returndate");

                Date borrowDate = (Date) new SimpleDateFormat("dd/MM/yyyy").parse(borrowdateStr);
                Date returnDate = (Date) new SimpleDateFormat("dd/MM/yyyy").parse(returndateStr);

//                out.println(borrowDate + " " + returnDate);
                borrow.setBorrowDate(new java.sql.Date(borrowDate.getTime()));
                borrow.setReturnDate(new java.sql.Date(returnDate.getTime()));

                BorrowDao dao = new BorrowDao();
                boolean flag = dao.borrowBook(borrow);
//                if (flag) {
//                    out.println("Successfull!!!");
//                } else {
//                    out.println("Fail!!!");
//                }
                request.setAttribute("flag", flag);
                request.getRequestDispatcher("borrow.jsp").forward(request, response);
            } else if (request.getParameter("notconfirm") != null) {

                request.getRequestDispatcher("borrow.jsp").forward(request, response);
            }

        } catch (ParseException ex) {
            Logger.getLogger(BorrowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
