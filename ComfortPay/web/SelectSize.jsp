<%-- 
    Document   : SelectSize
    Created on : Nov 29, 2018, 2:53:47 AM
    Author     : Joknoi
--%>

<%@page import="comfortpay.model.Products"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="Components/Navbar.jsp"/>
        <div class="col-8 mx-auto text-center" style="padding-top: 130px;" >
            <h1>Select Size</h1>
            <div class="col-12 text-left">
                <p>${product.productcode}</p>
                <div class="col-8 mx-auto">
                    <form action="ConfirmAddToStock" method="post">
                        <%
                            Products product = (Products) request.getAttribute("product");
                            if (product.getProducttype().equals("SHIRT") || product.getProducttype().equals("BRA")
                               || product.getProducttype().equals("HOOD") || product.getProducttype().equals("PLANT")
                               || product.getProducttype().equals("SHORT")) {
                        %>
                        <p>Size S <input type="number" name="S" required></p>
                        <p>Size M <input type="number" name="M" required></p>
                        <p>Size L <input type="number" name="L" required></p>
                        <p>Size XL <input type="number" name="XL" required></p>
                            <% } else { %>
                        <p>Size 7 <input type="number" name="7" required></p>
                        <p>Size 7.5 <input type="number" name="7.5" required></p>
                        <p>Size 8 <input type="number" name="8" required></p>
                        <p>Size 8.5 <input type="number" name="8.5" required></p>
                        <p>Size 9 <input type="number" name="9" required></p>
                        <p>Size 9.5 <input type="number" name="9.5" required></p>
                        <p>Size 10 <input type="number" name="10" required></p>
                            <% }%>
                        <input type="hidden" value="${product.productid}" name="productid">
                        <input type="submit" value="Comfirm">
                    </form>
                </div>  
            </div>
        </div>
    </body>
</html>
