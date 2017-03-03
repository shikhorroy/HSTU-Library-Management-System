/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rasha.controller;

import com.rasha.dao.BooksDao;
import com.rasha.model.Books;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author LittleBird
 */
public class BooksController extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */

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
        PrintWriter out = response.getWriter();

        if (request.getParameter("action") != null && request.getParameter("action").equals("delete")) {
            BooksDao dao = new BooksDao();
            dao.deleteBook(request.getParameter("isbn"));

            List<Books> allBooks = dao.getAllBooks();
            dao.closeConnection();
            request.setAttribute("allBooks", allBooks);
            request.getRequestDispatcher("books.jsp").forward(request, response);

        } else if (request.getParameter("action") != null && request.getParameter("action").equals("update")) {

            BooksDao dao = new BooksDao();
            Books book = dao.getBookById(request.getParameter("isbn"));

            dao.closeConnection();
            request.setAttribute("book", book);
            request.getRequestDispatcher("newbook.jsp?action=update").forward(request, response);

        } else if (request.getParameter("create") != null) {

            Books book = new Books();
            BooksDao dao = new BooksDao();

            book.setIsbn(request.getParameter("isbn"));
            book.setBookname(request.getParameter("bookname"));
            book.setAuthorname(request.getParameter("authorname"));
            book.setCategory(request.getParameter("category"));
            book.setSelfno(Integer.valueOf(request.getParameter("selfno")));

//            out.println(student.getId() + " " + student.getGender() + " " + student.getDept());
            boolean flag = dao.addBooks(book);

            if (flag) {
                request.setAttribute("flag", "reg");
            } else {
                request.setAttribute("exists", "yes");
            }
            List<Books> allBooks = dao.getAllBooks();
            dao.closeConnection();
            request.setAttribute("allBooks", allBooks);
            request.setAttribute("isbn", book.getIsbn());
            request.getRequestDispatcher("books.jsp").forward(request, response);

        } else if (request.getParameter("update") != null) {

//            out.println("update");
            Books book = new Books();
            BooksDao dao = new BooksDao();

            book.setIsbn(request.getParameter("isbn"));
            book.setBookname(request.getParameter("bookname"));
            book.setAuthorname(request.getParameter("authorname"));
            book.setCategory(request.getParameter("category"));
            book.setSelfno(Integer.valueOf(request.getParameter("selfno")));

//            out.println(student.getId() + " " + student.getMs()+ " " + student.getDept());
            boolean flag = dao.updateBooks(book);
            if (flag) {
                request.setAttribute("flag", "update");
            }
            List<Books> allBooks = dao.getAllBooks();
            dao.closeConnection();
            request.setAttribute("allBooks", allBooks);
            request.setAttribute("isbn", book.getIsbn());
            request.getRequestDispatcher("books.jsp").forward(request, response);

        } else {
//            out.println(request.getParameter("action"));
            BooksDao dao = new BooksDao();
            List<Books> allBooks = dao.getAllBooks();
            dao.closeConnection();
            request.setAttribute("allBooks", allBooks);
            request.getRequestDispatcher("books.jsp").forward(request, response);
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
