/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comfortpay.servlet;

import comfortpay.model.Account;
import comfortpay.model.Cart;
import comfortpay.model.Orderlists;
import comfortpay.model.Orders;
import comfortpay.model.ProductLine;
import comfortpay.model.controller.AccountJpaController;
import comfortpay.model.controller.OrderlistsJpaController;
import comfortpay.model.controller.OrdersJpaController;
import comfortpay.model.controller.ProductsJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
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
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;

/**
 *
 * @author Joknoi
 */
public class ConfirmServlet extends HttpServlet {

    @PersistenceUnit(unitName = "ComfortPayPU")
    EntityManagerFactory emf;
    @Resource
    UserTransaction utx;
    /**
     * POrdersJpaControllerrocesses requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        Account accSess = (Account) session.getAttribute("account");
        AccountJpaController accCtrl = new AccountJpaController(utx, emf);
        Account account = accCtrl.findAccount(accSess.getAccountid());
        int numberForGen = 0;
        String orderCode = "0";
        OrdersJpaController orderCtrl = new OrdersJpaController(utx, emf);
        List<Orders> orders = orderCtrl.findOrdersEntities();
        
        if(orders != null){
            for (Orders order : orders) {
                orderCode = order.getOrdercode();
            }
        }
        
        numberForGen = Integer.parseInt(orderCode);
        numberForGen+=1;
        DecimalFormat df = new DecimalFormat("0000000");
        orderCode = df.format(numberForGen);
        Orders order = new Orders(orderCode, cart.getTotalPrice(), account);
        
        try {
            orderCtrl.create(order);
        } catch (Exception ex) {
            Logger.getLogger(ConfirmServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<ProductLine> lineItems = cart.getProductLine();
        OrderlistsJpaController orderListCtrl = new OrderlistsJpaController(utx, emf);
        for (ProductLine lineItem : lineItems) {
            Orderlists orderList = new Orderlists(lineItem.getQuantity(), lineItem.TotalPrice(), order, lineItem.getProduct(), lineItem.getSize());
            try {
                orderListCtrl.create(orderList);
            } catch (Exception ex) {
                Logger.getLogger(ConfirmServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        session.removeAttribute("cart");
        Orders orderDB = orderCtrl.findByOrderCode(orderCode);
        List<Orderlists> orderListDB = orderDB.getOrderlistsList();
        request.setAttribute("order", orderDB);
        request.setAttribute("orderlist", orderListDB);
        getServletContext().getRequestDispatcher("/Bill.jsp").forward(request, response);
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
