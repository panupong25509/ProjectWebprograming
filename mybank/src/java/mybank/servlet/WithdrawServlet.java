/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybank.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import mybank.jpa.controller.History;
import mybank.jpa.model.controller.AccountJpaController;
import mybank.jpa.model.controller.HistoryJpaController;
import mybank.jpa.model.controller.exceptions.RollbackFailureException;

/**
 *
 * @author Joknoi
 */
public class WithdrawServlet extends HttpServlet {

    @PersistenceUnit(unitName = "mybankPU")
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
        session.setAttribute("error", "");
        int money = Integer.parseInt(request.getParameter("money"));
        AccountJpaController accountCtrl = new AccountJpaController(utx, emf);
        HistoryJpaController historyCtrl = new HistoryJpaController(utx, emf);
        Account accountSess = (Account) session.getAttribute("account");
        Account account = accountCtrl.findAccount(accountSess.getAccountid());
        if (account.getBalance() >= money) {
            account.setBalance(account.getBalance() - money);
            session.setAttribute("account", account);
            session.setAttribute("error", "");
            History history = new History(account, "withdraw", money, account.getBalance());
            try {
                accountCtrl.edit(account);
                historyCtrl.create(history);
            } catch (RollbackFailureException ex) {
                Logger.getLogger(DepositServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(DepositServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            session.setAttribute("error", "withdraw error");
        }
        response.sendRedirect("Login");
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
