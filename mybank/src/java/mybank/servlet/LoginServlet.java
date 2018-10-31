/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybank.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;
import mybank.jpa.controller.Account;
import mybank.jpa.model.controller.AccountJpaController;

/**
 *
 * @author Joknoi
 */
public class LoginServlet extends HttpServlet {
    @PersistenceUnit (unitName = "mybankPU")
    EntityManagerFactory emf;
    @Resource
    UserTransaction utx;
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
        HttpSession session = request.getSession(true);
        if(session.getAttribute("account")!=null){
            response.sendRedirect("MyAccount.jsp");
        }else{
            if(request.getParameter("id") == null || request.getParameter("pin") == null) {
                response.sendRedirect("Login.jsp");
            } else {
                int id = Integer.parseInt(request.getParameter("id"));
                int pin = Integer.parseInt(request.getParameter("pin"));
                AccountJpaController accountCtrl = new AccountJpaController(utx, emf);
                Account account = accountCtrl.findAccount(id);
                if(account != null && account.getPin() == pin){
                    session.setAttribute("account", account);
                    session.setAttribute("error", "");
                    response.sendRedirect("MyAccount.jsp");
                } else {
                    session.setAttribute("error", "ID Or PIN invalid");
                    response.sendRedirect("Login.jsp");
                }
            }
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
        processRequest(request, response);
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
