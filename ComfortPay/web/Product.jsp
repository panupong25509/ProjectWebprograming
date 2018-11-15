<%-- 
    Document   : Product
    Created on : Nov 16, 2018, 1:45:44 AM
    Author     : Techin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product</title>
    </head>
    <body>
        <h1>Product</h1>
        <p>ProductName : ${product.productname}</p>
        <p>ProductCode : ${product.productcode}</p>
        <p>ProductType : ${product.producttype}</p>
        <p>Size : ${product.size}</p>
        <p>Color : ${product.color}</p>
        <p>Price : ${product.price}</p>
        
        <form action="AddProduct" method="post">
            <input type="hidden" name="productcode" value="${product.productcode}"> 
            <input type="submit">
        </form>

    </body>
</html>
