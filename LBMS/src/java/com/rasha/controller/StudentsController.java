/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rasha.controller;

import com.rasha.dao.StudentsDao;
import com.rasha.model.Students;
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
public class StudentsController extends HttpServlet {

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
        PrintWriter out = response.getWriter();

        if (request.getParameter("action") != null && request.getParameter("action").equals("delete")) {
            StudentsDao dao = new StudentsDao();
            dao.deleteStudent(request.getParameter("id"));

            List<Students> allStudents = dao.getAllStudents();
            request.setAttribute("allStudents", allStudents);
            request.getRequestDispatcher("students.jsp").forward(request, response);

        } else if (request.getParameter("action") != null && request.getParameter("action").equals("update")) {

            StudentsDao dao = new StudentsDao();
            Students student = dao.getStudentById(request.getParameter("id"));

            request.setAttribute("student", student);
            request.getRequestDispatcher("newstudent.jsp?action=update").forward(request, response);

        } else if (request.getParameter("create") != null) {

            Students student = new Students();
            StudentsDao dao = new StudentsDao();

            student.setId(Integer.valueOf(request.getParameter("id")));
            student.setFirstName(request.getParameter("firstname"));
            student.setLastName(request.getParameter("lastname"));
            student.setEmail(request.getParameter("email"));
            student.setPhoneNo(request.getParameter("phoneno"));
            student.setAddress(request.getParameter("address"));

            student.setGender(Integer.valueOf(request.getParameter("gender")));
            student.setMs(Integer.valueOf(request.getParameter("ms")));
            student.setDept(request.getParameter("dept"));

//            out.println(student.getId() + " " + student.getGender() + " " + student.getDept());
            boolean flag = dao.addStudents(student);

            if (flag) {
                request.setAttribute("flag", "reg");
            } else {
                request.setAttribute("exists", "yes");
            }
            List<Students> allStudents = dao.getAllStudents();
            request.setAttribute("allStudents", allStudents);
            request.setAttribute("id", student.getId());
            request.getRequestDispatcher("students.jsp").forward(request, response);

        } else if (request.getParameter("update") != null) {

//            out.println("update");
            Students student = new Students();
            StudentsDao dao = new StudentsDao();

            student.setId(Integer.valueOf(request.getParameter("id")));
            student.setFirstName(request.getParameter("firstname"));
            student.setLastName(request.getParameter("lastname"));
            student.setEmail(request.getParameter("email"));
            student.setPhoneNo(request.getParameter("phoneno"));
            student.setAddress(request.getParameter("address"));

            student.setGender(Integer.valueOf(request.getParameter("gender")));
            student.setMs(Integer.valueOf(request.getParameter("ms")));
            student.setDept(request.getParameter("dept"));

//            out.println(student.getId() + " " + student.getMs()+ " " + student.getDept());
            boolean flag = dao.updateStudent(student);
            if (flag) {
                request.setAttribute("flag", "update");
            }
            List<Students> allStudents = dao.getAllStudents();
            request.setAttribute("allStudents", allStudents);
            request.setAttribute("id", student.getId());
            request.getRequestDispatcher("students.jsp").forward(request, response);

        } else {
//            out.println(request.getParameter("action"));
            StudentsDao dao = new StudentsDao();
            List<Students> allStudents = dao.getAllStudents();

            request.setAttribute("allStudents", allStudents);
            request.getRequestDispatcher("students.jsp").forward(request, response);
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
