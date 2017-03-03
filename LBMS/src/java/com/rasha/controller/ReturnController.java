/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rasha.controller;

import com.rasha.dao.BooksDao;
import com.rasha.dao.BorrowDao;
import com.rasha.dao.ReturnDao;
import com.rasha.dao.StudentsDao;
import com.rasha.model.BRCombo;
import com.rasha.model.Books;
import com.rasha.model.Borrow;
import com.rasha.model.Return;
import com.rasha.model.Students;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
public class ReturnController extends HttpServlet {

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

            if (request.getParameter("fine") != null) {

                BorrowDao borrowDao = new BorrowDao();
                Borrow borrow = borrowDao.getBorrowById(request.getParameter("id"));

                String returnedDate = request.getParameter("returndate");
                Date returnDate = (Date) new SimpleDateFormat("dd/MM/yyyy").parse(returnedDate);

                ReturnDao returnDao = new ReturnDao();
                Return returnObj = new Return();
                returnObj.setId(borrow.getId());
                returnObj.setStudentId(borrow.getStudentId());
                returnObj.setBookId(borrow.getBookId());
                returnObj.setReturnDate(new java.sql.Date(returnDate.getTime()));
                returnObj.setFine(Integer.valueOf(request.getParameter("fine")));
//
                boolean flag = returnDao.addReturn(returnObj);
                if (flag) {
                    flag = borrowDao.updateStatusById(Integer.valueOf(request.getParameter("id")));
                    if (flag) {
                        
                        List<Borrow> allBorrows = borrowDao.getAllBorrowsToReturn();

                        BooksDao booksDao = new BooksDao();
                        StudentsDao studentsDao = new StudentsDao();

                        List<BRCombo> allBr = new ArrayList<>();

                        for (Borrow b : allBorrows) {

                            Books book = booksDao.getBookById(b.getBookId());
                            Students student = studentsDao.getStudentById(b.getStudentId());

                            BRCombo brc = new BRCombo();

                            brc.setId(b.getId());
                            brc.setTitle(book.getBookname());
                            brc.setAuthorName(book.getAuthorname());

                            brc.setStudentId(b.getStudentId());
                            brc.setStudentName(student.getFirstName() + " " + student.getLastName());

                            brc.setBorrowDate(b.getBorrowDate());
                            brc.setReturnDate(b.getReturnDate());

                            brc.setStatus(b.getStatus());

                            allBr.add(brc);
                        }

                        request.setAttribute("allBr", allBr);
                        request.setAttribute("flag", flag);
                        request.getRequestDispatcher("return.jsp").forward(request, response);
                    } else {
                        out.println("Error 1 !!!");
                    }
                } else {
                    out.println("Error 2 !!!");
                }

            } else {

                BorrowDao borrowDao = new BorrowDao();
                List<Borrow> allBorrows = borrowDao.getAllBorrowsToReturn();

                BooksDao booksDao = new BooksDao();
                StudentsDao studentsDao = new StudentsDao();

                List<BRCombo> allBr = new ArrayList<>();

                for (Borrow b : allBorrows) {

                    Books book = booksDao.getBookById(b.getBookId());
                    Students student = studentsDao.getStudentById(b.getStudentId());

                    BRCombo brc = new BRCombo();

                    brc.setId(b.getId());
                    brc.setTitle(book.getBookname());
                    brc.setAuthorName(book.getAuthorname());

                    brc.setStudentId(b.getStudentId());
                    brc.setStudentName(student.getFirstName() + " " + student.getLastName());

                    brc.setBorrowDate(b.getBorrowDate());
                    brc.setReturnDate(b.getReturnDate());

                    brc.setStatus(b.getStatus());

                    allBr.add(brc);
                }

                request.setAttribute("allBr", allBr);
                request.getRequestDispatcher("return.jsp").forward(request, response);
            }
        } catch (ParseException ex) {
            Logger.getLogger(ReturnController.class.getName()).log(Level.SEVERE, null, ex);
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
