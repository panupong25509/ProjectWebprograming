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
                        <a href="MyAccount"><button type="button" class="btn btn-warning mt-3" style="width: 100%">BACK</button></a>
                </div>
                <div class="col">
                    <div>
                        <div class="p-3 bg-dark text-light" style="border-radius: 8px;">

                            <h3>Add</h3>
                            <form action="SizeStock" method="post">
                                <p>${productCode}</p>
                                <input type="hidden" value="${productCode}" name="productcode">
                                Product Name : <input class="form-control" type="text" name="productname" required>
                                <br>
                                Product type : 
                                <select name="type" class="ml-5 btn dropdown-toggle" required>
                                    <option value="SHIRT">SHIRT</option>
                                    <option value="BRA">BRA</option>
                                    <option value="HOOD">HOOD</option>
                                    <option value="PANT">PANT</option>
                                    <option value="SHORT">SHORT</option>
                                    <option value="SNEAKER">SNEAKER</option>
                                    <option value="SLIPPER">SLIPPER</option>
                                </select>
                                <br>
                                <br>
                                Product band : 
                                <select name="band" class="ml-5 btn dropdown-toggle" required>
                                    <option value="NIKE">NIKE</option>
                                    <option value="ADIDAS">ADIDAS</option>
                                    <option value="CHAMPION">CHAMPION</option>
                                    <option value="FILA">FILA</option>
                                </select>
                                <br>
                                Color : <input class="form-control" type="text" name="color" required>
                                <br>
                                Price : <input class="form-control" type="text" name="price" required>
                                <br>
                                <button type="button submit" class="btn btn-success" style="width: 100%">ADD PRODUCT</button>
                            </form>

                        </div>
                    </div>
                </div>
            </div>
        </div> 
    </body>
</html>
