<%-- 
    Document   : Home.jsp
    Created on : Nov 3, 2018, 10:38:46 PM
    Author     : crtiexx
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <title>Comfort Pay</title>
    </head>
    <style>
        body {
            margin: 0;
        }
        #section1 {
            min-height: 100vh;
            padding-top: 30px;
        }
        #sec2 {
            background-color: white;
            height: 200vh;
            font-size: 18px;

        }
    </style>
    <body>
        <jsp:include page="Components/Navbar.jsp"/>
        <div id="section1">
            <img src="images/Home/TrackPantsWideFeature_lead_o0not9.jpg" width="100%">
        </div>
        <div id="sec2" class="pt-5">
            <div class="container">
                <div class="col-12 row">
                    <div class="col-3 mt-3 pt-3" style="position: sticky;height: 90vh;background-color: #53759A;color: #D5C4AD;font-size: 16px;border-radius: 12px;" >
                        <h3 class="text-center mt-5">SEARCH</h3>
                        <br>
                        <form action="SerachByFilter" method="post">
                            Name  <input type="text" class="form-control" name="search">
                            <hr>
                            <p>Brand</p>
                            <select class="ml-5 btn dropdown-toggle" name="brand" style="color: black; font-size: 16px;">
                                <option value="">Brand</option>
                                <option value="ADIDAS">Adidas</option>
                                <option value="Nike">Nike</option>
                                <option value="Champion">Champion</option>
                                <option value="Fila">Fila</option>
                            </select>
                            <hr>
                            <p>Type</p>
                            <select class="ml-5 btn dropdown-toggle" name="type" style="color: black;font-size: 16px;">
                                <option value="">Type</option>
                                <option value="SHIRT">Shirt</option>
                                <option value="BRA">Bra</option>
                                <option value="HOOD">Hood</option>
                                <option value="PANT">Pant</option>
                                <option value="SHORT">Short</option>
                                <option value="SNEAKER">Sneaker</option>
                                <option value="SLIPER">Sliper</option>
                            </select>
                            <hr>
                            Color  <input class="form-control" type="text" name="color">
                            <hr>
                            <p>Price</p>
                            <select class="ml-5 btn dropdown-toggle" style="font-size: 16px;color: black;" name="price">
                                <option value="0000-6000">Price</option>
                                <option value="0000-1000">0-1000</option>
                                <option value="1001-2000">1001-2000</option>
                                <option value="2001-3000">2001-3000</option>
                                <option value="3001-4000">3001-4000</option>
                                <option value="4001-5000">4001-5000</option>
                                <option value="5001-6000">5001-6000</option>
                            </select>
                            <hr>
                            <div class="text-center"><button type="button submit" class="btn" style="font-size: 16px;width: 100%;background-color:#D5C4AD;color: black">Search</button></a></div>
                        </form>
                    </div>
                    <div class="col-9 pl-5" style="">
                        <div class="row">
                            <c:forEach items="${products}" var = "product" >
                                <a href="Product?productid=${product.productid}" class="col-4 p-3 text-center text-dark">
                                    <div >
                                        <!--<button style="background-color: #ECEEF0; border: none">-->
                                        <div style="background-color: #ECEEF0;cursor: pointer" class="card bg-light">
                                            <img src="images/PictureWebPro/Products/${product.productcode}.jpg" width="100%">
                                            <div class="col-12 row m-0">
                                                <div class="col">${product.productname}</div>
                                                <div class="col">${product.price} BAHT</div>
                                            </div>
                                            
                                        </div>
                                        <!--                                        </button>-->
                                    </div>
                                </a>
                            </c:forEach>
                        </div>   
                    </div>

                </div>               
            </div>
        </div>
    </body>
</html>
