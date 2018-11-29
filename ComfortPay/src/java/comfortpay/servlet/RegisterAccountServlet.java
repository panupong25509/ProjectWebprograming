 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comfortpay.servlet;

import comfortpay.model.Account;
import comfortpay.model.Address;
import comfortpay.model.controller.AccountJpaController;
import comfortpay.model.controller.AddressJpaController;
import comfortpay.model.controller.exceptions.RollbackFailureException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

/**
 *
 * @author Joknoi
 */
public class RegisterAccountServlet extends HttpServlet {

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
            throws ServletException, IOException, ParseException {
        HttpSession session = request.getSession();
        String username = request.getParameter("username").toUpperCase();
        String password = request.getParameter("password");
        String passwordAgain = request.getParameter("passwordAgain");
        String fname = request.getParameter("fname").toUpperCase();
        String lname = request.getParameter("lname").toUpperCase();
//        String dob = request.getParameter("dob");
//        Date date = new SimpleDateFormat("yyyy/mm/dd").parse(dob);
        String email = request.getParameter("email").toUpperCase();
        String address = request.getParameter("address").toUpperCase();
        String district = request.getParameter("district").toUpperCase();
        String province = request.getParameter("province").toUpperCase();
        String postcode = request.getParameter("postcode");
        AccountJpaController accountCtrl = new AccountJpaController(utx, emf);
        Account account = accountCtrl.findByUsername(username);
        if (account != null) {
            getServletContext().getRequestDispatcher("/Register").forward(request, response);
        } else {
            if (password.equals(passwordAgain)) {
                Account newAccount = new Account(username, password, fname, lname, email);
                Address newAddress = new Address(address, district, province, Integer.parseInt(postcode), newAccount);
                AddressJpaController addressCtrl = new AddressJpaController(utx, emf);
                try {
                    accountCtrl.create(newAccount);
                    addressCtrl.create(newAddress);
                } catch (RollbackFailureException ex) {
                    Logger.getLogger(RegisterAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(RegisterAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                account = accountCtrl.findByUsername(username);
                session.setAttribute("account", account);
                getServletContext().getRequestDispatcher("/MyAccount").forward(request, response);
            } else {
                getServletContext().getRequestDispatcher("/Register").forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(RegisterAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(RegisterAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
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
