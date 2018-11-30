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
            <div class="col-12 text-left row m-0">
                <div class="col">
                    <div class="p-3 bg-warning" style="border-radius: 8px;">
                        <h3>Last Product</h3>
                        <div class="row">
                            <div class="col">
                                <img class="col" src="images/Products/${product.productcode}.jpg">
                            </div>
                            <p class="col">Product code: ${product.productcode}</p> 
                            <p class="col">Product code: ${product.productname}</p> 
                        </div>

                    </div>
                </div>
                <div class="col">
                    <div class="p-3 bg-dark text-light" style="border-radius: 8px;">
                        <form action="ConfirmAddToStock" method="post">
                            <%
                                Products product = (Products) request.getAttribute("product");
                                if (product.getProducttype().equals("SHIRT") || product.getProducttype().equals("BRA")
                                        || product.getProducttype().equals("HOOD") || product.getProducttype().equals("PANT")
                                        || product.getProducttype().equals("SHORT")) {
                            %>
                            <p>Size S <input class="form-control"  type="number" name="S" required></p>
                            <p>Size M <input class="form-control" type="number" name="M" required></p>
                            <p>Size L <input class="form-control" type="number" name="L" required></p>
                            <p>Size XL <input class="form-control" type="number" name="XL" required></p>
                                <% } else { %>
                            <p>Size 7 <input class="form-control" type="number" name="7" required></p>
                            <p>Size 7.5 <input class="form-control" type="number" name="7.5" required></p>
                            <p>Size 8 <input class="form-control" type="number" name="8" required></p>
                            <p>Size 8.5 <input class="form-control" type="number" name="8.5" required></p>
                            <p>Size 9 <input class="form-control" type="number" name="9" required></p>
                            <p>Size 9.5 <input class="form-control" type="number" name="9.5" required></p>
                            <p>Size 10 <input class="form-control" type="number" name="10" required></p>
                                <% }%>
                            <input type="hidden" value="${product.productid}" name="productid">
                            <button type="submit button" class="btn btn-success" style="width: 100%">CONFIRM</button>
                        </form>
                        <!--                                </div>  
                                                    </div>-->
                    </div>
                </div>
            </div>
    </body>
</html>
