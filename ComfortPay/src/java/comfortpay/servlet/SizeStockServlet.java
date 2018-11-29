/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comfortpay.servlet;

import comfortpay.model.Products;
import comfortpay.model.controller.ProductsJpaController;
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
import javax.transaction.UserTransaction;

/**
 *
 * @author Joknoi
 */
public class SizeStockServlet extends HttpServlet {
    @PersistenceUnit (unitName = "ComfortPayPU")
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
        String productCode = request.getParameter("productcode").toUpperCase();
        String productName = request.getParameter("productname").toUpperCase();
        String productType = request.getParameter("type").toUpperCase();
        String productBand = request.getParameter("band").toUpperCase();
        String productColor = request.getParameter("color").toUpperCase();
        String productPrice = request.getParameter("price").toUpperCase();
        Products product = new Products(productCode, productName, productType, productBand, productColor, Double.parseDouble(productPrice));
        ProductsJpaController pdCtrl = new ProductsJpaController(utx, emf);
        try {
            pdCtrl.create(product);
        } catch (Exception ex) {
            Logger.getLogger(ConfirmAddToStockServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("product", product);
        getServletContext().getRequestDispatcher("/SelectSize.jsp").forward(request, response);
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
