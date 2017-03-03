/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rasha.controller;

import com.rasha.dao.UsersDao;
import com.rasha.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author LittleBird
 */
public class LoginController extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
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

        response.setContentType("text/html;charset=UTF-8");

        if (request.getParameter("username").length() == 0) {
            request.setAttribute("errMsg", "User Name Can't be blank!!!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        if (request.getParameter("password").length() == 0) {
            request.setAttribute("errMsg", "Password Can't be blank!!!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        try (PrintWriter out = response.getWriter()) {

            User user;
            UsersDao dao = new UsersDao();

            HttpSession session = request.getSession(true);

            String username = request.getParameter("username");
            String password = request.getParameter("password");

            try {
                user = dao.getUserById(username);
                dao.closeConnection();

                if (user.getPassword().equals(password)) {
                    session.setAttribute("username", username);
                    request.getRequestDispatcher("home.jsp").forward(request, response);
                } else {
                    request.setAttribute("errMsg", "Username Password doesn't match!!!");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            } catch (Exception e) {
                request.setAttribute("errMsg", "No Username and Password found!!!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        }
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
