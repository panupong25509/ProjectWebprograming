/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comfortpay.servlet;

import comfortpay.model.Account;
import comfortpay.model.controller.AccountJpaController;
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
import org.jboss.weld.servlet.SessionHolder;

/**
 *
 * @author Joknoi
 */
public class LoginPopupServlet extends HttpServlet {

    @PersistenceUnit(unitName = "ComfortPayPU")
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
        String path = request.getParameter("path");
        int pathLength = path.length();
        String pathServlet = path.substring(0, pathLength-4);
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (session.getAttribute("account") == null) {
            if (username != null || password != null) {
                AccountJpaController accountCtrl = new AccountJpaController(utx, emf);
                Account account = (Account) accountCtrl.findByUsername(username);
                if (account != null) {
                    if (account.getPassword().equals(password)) {
                        session.setAttribute("account", account);
                        if (path == null || path == "") {
                            getServletContext().getRequestDispatcher("/Home.jsp").forward(request, response);
                        } else {
                            response.sendRedirect(pathServlet);
                        }
                    } else {
                        request.setAttribute("path", path);
                        getServletContext().getRequestDispatcher("/Login").forward(request, response);
                    }
                } else {
                    request.setAttribute("path", path);
                    getServletContext().getRequestDispatcher("/Login").forward(request, response);
                }
            } else {
                request.setAttribute("path", path);
                getServletContext().getRequestDispatcher("/Login").forward(request, response);
            }
        } else {
            if (path == null || path == "") {
                getServletContext().getRequestDispatcher("/Home.jsp").forward(request, response);
            } else {
                response.sendRedirect(pathServlet);
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
