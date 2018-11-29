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
import java.util.List;
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
public class SearchServlet extends HttpServlet {

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
        String search = request.getParameter("search").toLowerCase();
        String[] brand = {"nike", "adidas", "champion", "fila"};
        String[] color = {"red", "black", "white", "pink", "green", "gray", "blue", "purple", "orange"};
        String[] type = {"sneaker", "slipper", "shirt", "pant", "short", "hood" , "bra"};
        String have = null;
        ProductsJpaController productCtrl = new ProductsJpaController(utx, emf);

        List<Products> product = productCtrl.findByBrand(search.toUpperCase());
        for (String brandHave : brand) {
            if (search.equals(brandHave)) {
                have = search;
            }
        }
        if (have != null) {
            //หาตามbrand
            product = productCtrl.findByBrand(search.toUpperCase());
        } else {
            for (String colorHave : color) {
                if (search.equals(colorHave)) {
                    have = search;
                }
            }
            if (have != null) {
                //หาตามcolor
                product = productCtrl.findByColor(search.toUpperCase());
            } else {
                if (search.equals("cloth")) {
                    //หาตามtype(shirt,pant,short)
                    product = productCtrl.findByCloth();
                } else if (search.equals("shoes")) {
                    //หาตามtype(sneaker,slipper)
                    product = productCtrl.findByShoes();
                } else {
                    for (String typeHave : type) {
                        if (search.equals(typeHave)) {
                            have = search;
                        }
                    }
                    if (have != null) {
                        //หาตามtype
                        product = productCtrl.findByType(search.toUpperCase());
                    } else {
                        //หาตามชื่อสินค้า
                        product = productCtrl.findByProductName(search.toUpperCase());

                    }
                }

            }
        }
        request.setAttribute("products", product);
        getServletContext().getRequestDispatcher("/Home.jsp").forward(request, response);
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
