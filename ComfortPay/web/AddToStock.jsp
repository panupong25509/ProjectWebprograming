<%-- 
    Document   : AddToStock
    Created on : Nov 29, 2018, 1:57:16 AM
    Author     : Joknoi
--%>

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
            <h1>Add to Stock</h1>
            <div class="col-12 text-left">
                <div class="col-8 mx-auto">
                    <h3>Last Product</h3>
                    <div class="row">
                        <div class="col">
                            <img class="col" src="images/PictureWebPro/adidasMencCloth/${product.productcode}.jpg">
                        </div>
                        <p class="col">Product code: ${product.productcode}</p> 
                        <p class="col">Product code: ${product.productname}</p> 
                    </div>
                    <div>
                        <h3>Add</h3>
                        <form action="SizeStock" method="post">
                            <p>${productCode}</p>
                            <input type="hidden" value="${productCode}" name="productcode">
                            Product Name : <input type="text" name="productname" required>
                            <br>
                            Product type : 
                            <select name="type" required>
                                <option value="SHIRT">SHIRT</option>
                                <option value="BRA">BRA</option>
                                <option value="HOOD">HOOD</option>
                                <option value="PLANT">PANT</option>
                                <option value="SHORT">SHORT</option>
                                <option value="SNEAKER">SNEAKER</option>
                                <option value="SLIPPER">SLIPPER</option>
                            </select>
                            <br>
                            Product band : 
                            <select name="band" required>
                                <option value="NIKE">NIKE</option>
                                <option value="ADIDAS">ADIDAS</option>
                                <option value="CHAMPION">CHAMPION</option>
                                <option value="FILA">FILA</option>
                            </select>
                            <br>
                            Color : <input type="text" name="color" required>
                            <br>
                            Price : <input type="text" name="price" required>
                            <br>
                            <input type="submit" value="Next">
                        </form>
                        
                    </div>
                </div>
            </div>
        </div> 
    </body>
</html>
