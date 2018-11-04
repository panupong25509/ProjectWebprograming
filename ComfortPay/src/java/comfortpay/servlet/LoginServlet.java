/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comfortpay.servlet;

import comfortpay.jpa.model.Account;
import comfortpay.jpa.model.Profile;
import comfortpay.jpa.model.controller.AccountJpaController;
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

/**
 *
 * @author Joknoi
 */
public class LoginServlet extends HttpServlet {

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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (session.getAttribute("account") == null) {
            if (username != null || password != null) {
                AccountJpaController accountCtrl = new AccountJpaController(utx, emf);
                Account account = accountCtrl.findAccount(username);
                
                if (account != null) {
                    if (account.getPassword().equals(password)) {
                        session.setAttribute("account", account);
                        Profile profile = account.getProfile();
                        session.setAttribute("profile", profile);
                        if (path == null || path == "") {
                            getServletContext().getRequestDispatcher("/Home.jsp").forward(request, response);
                            response.sendRedirect(path);
                        } else {
                            response.sendRedirect(path);
                        }
                    } else {
                        request.setAttribute("path", path);
                        getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("path", path);
                    getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("path", path);
                getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
            }
        } else {
            if (path == null || path == "") {
                getServletContext().getRequestDispatcher("/Home.jsp").forward(request, response);
            } else {
                response.sendRedirect(path);
            }
        }

//        if (path != null || path != "") {
//            if (session.getAttribute("account") == null) {
//                if (username != null || password != null) {
//                    AccountJpaController accountCtrl = new AccountJpaController(utx, emf);
//                    Account account = accountCtrl.findAccount(username);
//                    if (account != null) {
//                        if (account.getPassword().equals(password)) {
//                            session.setAttribute("account", account);
//                            response.sendRedirect("Home.jsp");
//                        } else {
//                            request.setAttribute("path", path);
//                            getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
//                        }
//                    } else {
//                        request.setAttribute("path", path);
//                        getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
//                    }
//                } else {
//                    request.setAttribute("path", path);
//                    getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
//                }
//            } else {
//                response.sendRedirect(path);
//            }
//        } else {
//            if (session.getAttribute("account") == null) {
//                if (username != null || password != null) {
//                    AccountJpaController accountCtrl = new AccountJpaController(utx, emf);
//                    Account account = accountCtrl.findAccount(username);
//                    if (account != null) {
//                        if (account.getPassword().equals(password)) {
//                            session.setAttribute("account", account);
//                            getServletContext().getRequestDispatcher("/Home.jsp").forward(request, response);
//                        } else {
//                            getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
//                        }
//                    } else {
//                        getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
//                    }
//                } else {
//                    getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
//                }
//            } else {
//                getServletContext().getRequestDispatcher("/Home.jsp").forward(request, response);
//            }
//        }
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
