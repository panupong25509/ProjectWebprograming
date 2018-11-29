/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comfortpay.servlet;

import comfortpay.model.Products;
import comfortpay.model.Sizes;
import comfortpay.model.controller.ProductsJpaController;
import comfortpay.model.controller.SizesJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
public class ConfirmAddToStockServlet extends HttpServlet {

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
        String productid = request.getParameter("productid");
        ProductsJpaController pdCtrl = new ProductsJpaController(utx, emf);
        Products product = pdCtrl.findProducts(Integer.parseInt(productid));
        SizesJpaController sizeProduct = new SizesJpaController(utx, emf);
        if (product.getProducttype().equals("SHIRT") || product.getProducttype().equals("BRA")
                || product.getProducttype().equals("HOOD") || product.getProducttype().equals("PANT")
                || product.getProducttype().equals("SHORT")) {
            Sizes sizeS = new Sizes("S", Integer.parseInt(request.getParameter("S")), product);
            Sizes sizeM = new Sizes("M", Integer.parseInt(request.getParameter("M")), product);
            Sizes sizeL = new Sizes("L", Integer.parseInt(request.getParameter("L")), product);
            Sizes sizeXL = new Sizes("XL", Integer.parseInt(request.getParameter("XL")), product);
            Sizes[] sizeList = {sizeS, sizeM, sizeL, sizeXL};
            for (Sizes size : sizeList) {
                try {
                    sizeProduct.create(size);
                } catch (Exception ex) {
                    Logger.getLogger(ConfirmAddToStockServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            Sizes size7 = new Sizes("7", Integer.parseInt(request.getParameter("7")), product);
            Sizes size75 = new Sizes("7.5", Integer.parseInt(request.getParameter("7.5")), product);
            Sizes size8 = new Sizes("8", Integer.parseInt(request.getParameter("8")), product);
            Sizes size85 = new Sizes("8.5", Integer.parseInt(request.getParameter("8.5")), product);
            Sizes size9 = new Sizes("9", Integer.parseInt(request.getParameter("9")), product);
            Sizes size95 = new Sizes("9.5", Integer.parseInt(request.getParameter("9.5")), product);
            Sizes size10 = new Sizes("10", Integer.parseInt(request.getParameter("10")), product);
            Sizes[] sizeList = {size7, size75, size8, size85, size9, size95, size10};
            for (Sizes size : sizeList) {
                try {
                    sizeProduct.create(size);
                } catch (Exception ex) {
                    Logger.getLogger(ConfirmAddToStockServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        getServletContext().getRequestDispatcher("/AddToStock").forward(request, response);
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
